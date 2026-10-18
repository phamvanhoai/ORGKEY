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
    /** o01/v01: second-order band pass, evaluated with Float coefficients/history. */
    private class BandPass(frequency:Int,width:Int,sampleRate:Int):Stage {
        private val radius=1f-(width/sampleRate.toFloat())*3f
        private val cosine=kotlin.math.cos((frequency/sampleRate.toFloat()).toDouble()*2*Math.PI).toFloat()*2f
        private val a1=radius*cosine
        private val a2=-radius*radius
        private val scale=((1f-a1)+radius*radius)/(2f-cosine)
        private val b=floatArrayOf(1f-scale,(scale-radius)*cosine,radius*radius-scale)
        private val x=Array(2){FloatArray(3)}
        private val y=Array(2){FloatArray(2)}
        init{require(b.all{it.isFinite()} && a1.isFinite() && a2.isFinite()){"Non-finite Band Pass coefficients"}}
        override var left=0.0;override var right=0.0
        override val active get()=x.any{row->row.any{kotlin.math.abs(it)>1e-9f}} || y.any{row->row.any{kotlin.math.abs(it)>1e-9f}}
        override fun reset(){x.forEach{it.fill(0f)};y.forEach{it.fill(0f)};left=0.0;right=0.0}
        private fun channel(input:Double,c:Int):Double {
            val h=x[c];val out=y[c]
            h[2]=h[1];h[1]=h[0];h[0]=input.toFloat()
            var value=0f
            for(i in b.indices)value=b[i]*h[i]+value
            value=a1*out[0]+value;value=a2*out[1]+value
            out[1]=out[0];out[0]=value
            return value.toDouble()
        }
        override fun process(l:Double,r:Double){left=channel(l,0);right=channel(r,1)}
    }
    /** x01: both channels share the same frame counter, reset every sampleRate frames. */
    private class Robot(private val frequency:Int,private val sampleRate:Int):Stage {
        private var frame=0
        override var left=0.0;override var right=0.0
        override val active=false
        override fun reset(){frame=0;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            if(frame>=sampleRate)frame=0
            val gain=kotlin.math.cos(((frequency*frame)/sampleRate).toDouble())
            left=l*gain;right=r*gain;frame++
        }
    }
    /** u01/v01: one-pole IIR, including the integer division in High pass.a(). */
    private class PassFilter(frequency:Int,sampleRate:Int,high:Boolean):Stage {
        private val feedback=kotlin.math.exp(-2*Math.PI*(if(high)(frequency/sampleRate).toDouble()
            else ((frequency*1000f/150000f)/sampleRate).toDouble())).toFloat()
        private val direct=if(high)(1f+feedback)/2f else 1f-feedback
        private val previous=if(high)-direct else 0f
        private var inputL=0f;private var inputR=0f
        private var outputL=0f;private var outputR=0f
        override var left=0.0;override var right=0.0
        override val active get()=kotlin.math.abs(outputL)>1e-9f || kotlin.math.abs(outputR)>1e-9f ||
            (previous!=0f && (inputL!=0f || inputR!=0f))
        override fun reset(){inputL=0f;inputR=0f;outputL=0f;outputR=0f;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            val nextL=l.toFloat();val nextR=r.toFloat()
            outputL=(direct*nextL+previous*inputL)+feedback*outputL
            outputR=(direct*nextR+previous*inputR)+feedback*outputR
            inputL=nextL;inputR=nextR;left=outputL.toDouble();right=outputR.toDouble()
        }
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
    private val stages:List<Stage> = if(!settings.fxEnabled)emptyList() else settings.modules.filter{it.enabled && it.type in listOf(20,6,4,17,9,10,16,1)}.map{
        if(it.type==1){
            require(it.payload.size==8){"Invalid Band Pass payload"}
            val frequency=int(it.payload,0);val width=int(it.payload,4)
            require(frequency in 0..150000 && width in 0..25000 && sampleRate>0){"Unsupported Band Pass parameters"}
            return@map BandPass(frequency,width,sampleRate)
        }
        if(it.type==16){
            require(it.payload.size==4){"Invalid Robot payload"}
            val frequency=int(it.payload,0)
            require(frequency in 0..1000 && sampleRate>0){"Unsupported Robot frequency"}
            return@map Robot(frequency,sampleRate)
        }
        if(it.type==9 || it.type==10){
            require(it.payload.size==4){"Invalid Pass Filter payload"}
            val frequency=int(it.payload,0)
            require(frequency in 0..150000 && sampleRate>0){"Unsupported Pass Filter frequency"}
            return@map PassFilter(frequency,sampleRate,it.type==9)
        }
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
