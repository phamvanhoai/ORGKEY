package com.hovait.orgx

/** Ordered SET track DSP stages; state belongs to a track, never the sample bank. */
class ImportedFxChain(private val settings:ImportedChannelSettings,private val sampleRate:Int=48000) {
    fun fresh()=ImportedFxChain(settings,sampleRate)
    private interface Stage {
        var left:Double
        var right:Double
        fun process(l:Double,r:Double)
        fun reset()
        val active:Boolean
    }
    /** y01: widen the side signal while preserving the mono centre. */
    private class StereoWidth(volume:Int):Stage {
        private val width=(volume/10000.0f)*6.5536f
        override var left=0.0;override var right=0.0
        override val active=false
        override fun reset(){left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            val centre=(l+r)/2.0
            left=((l-centre)*width+centre+l)/2.0
            right=((r-centre)*width+centre+r)/2.0
        }
    }
    private class Bass(val volume:Float,val frequency:Float):Stage{
        private var stateL=0f;private var stateR=0f
        override var left=0.0;override var right=0.0
        val smoothing=1f/(frequency+1f)
        override val active get()=kotlin.math.abs(stateL)>1e-9f || kotlin.math.abs(stateR)>1e-9f
        override fun reset(){stateL=0f;stateR=0f;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            stateL=(stateL*frequency+l.toFloat())*smoothing
            stateR=(stateR*frequency+r.toFloat())*smoothing
            left=l+stateL*volume;right=r+stateR*volume
        }
    }
    private class Echo(val feedback:Double,val delay:Int,val stereo:Boolean=false):Stage{
        private val bufferL=DoubleArray(delay.coerceAtLeast(1))
        private val bufferR=DoubleArray(delay.coerceAtLeast(1))
        private var cursor=0
        private var phase=0
        private var direction=1
        private var energy=0.0
        override val active get()=energy>1e-18
        init { require(!stereo || delay>0){"Stereo Echo requires a positive delay"} }
        override var left=0.0;override var right=0.0
        override fun reset(){bufferL.fill(0.0);bufferR.fill(0.0);cursor=0;energy=0.0;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            val echoL=bufferL[cursor]*feedback;val echoR=bufferR[cursor]*feedback
            left=l+echoL*(if(stereo)phase.toDouble()/delay else 1.0)
            right=r+echoR*(if(stereo)(delay-phase).toDouble()/delay else 1.0)
            // The feedback buffers receive the full echo, independent of output modulation.
            energy-=bufferL[cursor]*bufferL[cursor]+bufferR[cursor]*bufferR[cursor]
            bufferL[cursor]=l+echoL;bufferR[cursor]=r+echoR
            energy=(energy+bufferL[cursor]*bufferL[cursor]+bufferR[cursor]*bufferR[cursor]).coerceAtLeast(0.0)
            if(stereo){phase+=direction;if(phase==delay || phase==0)direction=-direction}
            cursor=(cursor+1)%bufferL.size
        }
    }
    private fun int(bytes:List<Int>,offset:Int):Int =
        bytes[offset] or (bytes[offset+1] shl 8) or (bytes[offset+2] shl 16) or (bytes[offset+3] shl 24)
    private val stages:List<Stage> = if(!settings.fxEnabled)emptyList() else settings.modules.filter{it.enabled && it.type in listOf(20,6,4,17)}.map{
        if(it.type==17){
            require(it.payload.size==4){"Invalid Stereo Width payload"}
            val volume=int(it.payload,0)
            require(volume in 0..50000){"Unsupported Stereo Width volume"}
            return@map StereoWidth(volume)
        }
        require(it.payload.size==8)
        val volume=int(it.payload,0);val frequency=int(it.payload,4)
        require(volume in 0..(if(it.type in listOf(4,6))99 else 100) && frequency in 0..100){"Unsupported SET DSP parameters"}
        if(it.type in listOf(4,6))Echo(volume/100.0,minOf(48000,(frequency.toLong()*sampleRate/100).toInt()),it.type==4)
        else Bass(volume/16f,frequency.toFloat())
    }
    var left=0.0;private set
    var right=0.0;private set
    val active get()=stages.any{it.active}
    fun reset(){stages.forEach{it.reset()};left=0.0;right=0.0}
    fun process(inputLeft:Double,inputRight:Double){
        left=inputLeft;right=inputRight
        for(stage in stages){
            stage.process(left,right);left=stage.left;right=stage.right
        }
    }
}
