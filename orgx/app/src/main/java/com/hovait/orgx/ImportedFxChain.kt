package com.hovait.orgx

/** Ordered SET track DSP stages; state belongs to a track, never the sample bank. */
class ImportedFxChain(settings:ImportedChannelSettings,sampleRate:Int=48000) {
    private interface Stage {
        var left:Double
        var right:Double
        fun process(l:Double,r:Double)
        fun reset()
    }
    private class Bass(val volume:Float,val frequency:Float):Stage{
        private var stateL=0f;private var stateR=0f
        override var left=0.0;override var right=0.0
        val smoothing=1f/(frequency+1f)
        override fun reset(){stateL=0f;stateR=0f;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            stateL=(stateL*frequency+l.toFloat())*smoothing
            stateR=(stateR*frequency+r.toFloat())*smoothing
            left=l+stateL*volume;right=r+stateR*volume
        }
    }
    private class Echo(val feedback:Double,val delay:Int):Stage{
        private val bufferL=DoubleArray(delay.coerceAtLeast(1))
        private val bufferR=DoubleArray(delay.coerceAtLeast(1))
        private var cursor=0
        override var left=0.0;override var right=0.0
        override fun reset(){bufferL.fill(0.0);bufferR.fill(0.0);cursor=0;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            left=l+bufferL[cursor]*feedback;right=r+bufferR[cursor]*feedback
            bufferL[cursor]=left;bufferR[cursor]=right
            cursor=(cursor+1)%bufferL.size
        }
    }
    private fun int(bytes:List<Int>,offset:Int):Int =
        bytes[offset] or (bytes[offset+1] shl 8) or (bytes[offset+2] shl 16) or (bytes[offset+3] shl 24)
    private val stages:List<Stage> = if(!settings.fxEnabled)emptyList() else settings.modules.filter{it.enabled && it.type in listOf(20,6)}.map{
        require(it.payload.size==8)
        val volume=int(it.payload,0);val frequency=int(it.payload,4)
        require(volume in 0..(if(it.type==6)99 else 100) && frequency in 0..100){"Unsupported SET DSP parameters"}
        if(it.type==6)Echo(volume/100.0,minOf(48000,(frequency.toLong()*sampleRate/100).toInt()))
        else Bass(volume/16f,frequency.toFloat())
    }
    var left=0.0;private set
    var right=0.0;private set
    fun reset(){stages.forEach{it.reset()};left=0.0;right=0.0}
    fun process(inputLeft:Double,inputRight:Double){
        left=inputLeft;right=inputRight
        for(stage in stages){
            stage.process(left,right);left=stage.left;right=stage.right
        }
    }
}
