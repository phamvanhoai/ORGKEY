package com.hovait.orgx

/** n01.i receives normalized -100..100; s01 maps it using b44.f. */
object ImportedTrackFxAutomation {
    private fun maximum(module:Int,parameter:Int):Int?=when(module){
        4,6->when(parameter){1->99;2->100;else->null}
        20->100.takeIf{parameter in 1..2}
        1->when(parameter){1->150000;2->25000;else->null}
        9,10->150000.takeIf{parameter==1}
        16->1000.takeIf{parameter==1}
        17->50000.takeIf{parameter==1}
        23->when(parameter){1,3->100;2->0;else->null}
        15->200.takeIf{parameter==1}
        31->100.takeIf{parameter in 1..3}
        21->listOf(500,2000,100,95,100,180).getOrNull(parameter-1)
        8->listOf(300,90,100,255,16,256).getOrNull(parameter-1)
        7->100.takeIf{parameter in 1..8}
        29->100.takeIf{parameter in 1..7}
        2->listOf(100,100,0,5000,2000).getOrNull(parameter-1)
        else->null
    }
    fun supported(module:ImportedFxModule,event:ReferenceDrumEvent)=event.note==0 ||
        (maximum(module.type,event.note)!=null && (if(module.type==7)
            module.payload.size in 4..32 && module.payload.size%4==0 && event.note<=module.payload.size/4
            else module.payload.size==
            (when(module.type){9,10,15,16,17,31->4;23->9;21->24;8->28;2->17;else->8})) && event.touch in -100..100)
    fun apply(module:ImportedFxModule,event:ReferenceDrumEvent):ImportedFxModule {
        require(supported(module,event))
        if(event.note==0)return module.copy(enabled=event.touch!=0)
        val max=checkNotNull(maximum(module.type,event.note))
        val min=when{module.type==2->listOf(-100,-100,0,1,1)[event.note-1];module.type in listOf(8,29)->1;module.type==7->-100;module.type==21->listOf(5,10,5,-95,0,0)[event.note-1];module.type==15->50;module.type==31->-100;module.type==23 && event.note!=2->1;else->0}
        val value=Math.round((event.touch+100)/200.0f*(max-min)+min)
        val bytes=module.payload.toMutableList()
        if(module.type==2 && event.note==3){bytes[16]=if(value!=0)1 else 0;return module.copy(payload=bytes)}
        if(module.type==29){bytes[event.note-1]=value;return module.copy(payload=bytes)}
        if(module.type==31){bytes[3-event.note]=value and 255;return module.copy(payload=bytes)}
        if(module.type==23 && event.note==2){bytes[4]=value;return module.copy(payload=bytes)}
        val offset=when{module.type==2 && event.note>3->(event.note-2)*4;module.type==8 && event.note>1->event.note*4;module.type==23 && event.note==3->5;else->(event.note-1)*4}
        val stored=if(module.type==7 || module.type==2 && event.note<=2)value+100 else value
        repeat(4){bytes[offset+it]=(stored ushr (it*8)) and 255}
        return module.copy(payload=bytes)
    }
}

/** Ordered SET track DSP stages; state belongs to a track, never the sample bank. */
class ImportedFxChain(private var settings:ImportedChannelSettings,private val sampleRate:Int=48000):AutoCloseable {
    init {
        if(settings.fxEnabled)settings.modules.firstOrNull{it.enabled && it.type !in setOf(1,2,4,6,7,8,9,10,15,16,17,20,21,23,29,31)}?.let{
            throw IllegalArgumentException("Unsupported enabled SET FX module ${it.type}")
        }
    }
    fun fresh()=ImportedFxChain(settings,sampleRate)
    private interface Stage {
        var left:Double
        var right:Double
        fun process(l:Double,r:Double)
        fun reset()
        val active:Boolean
        fun close(){ }
    }
    private class PitchShift(shift:Int,rate:Int):Stage {
        private val processor=ImportedNativePitchShift(shift,rate)
        private val tailFrames=ImportedNativePitchShift.FLUSH_FRAMES.toLong()
        private var remaining=0L
        fun setShift(value:Int){processor.setShift(value)}
        override var left=0.0;override var right=0.0
        override val active get()=remaining>0
        override fun process(l:Double,r:Double){
            if(l!=0.0 || r!=0.0)remaining=tailFrames else if(remaining>0){
                remaining--
                if(remaining==0L)processor.reset()
            }
            if(remaining==0L){left=0.0;right=0.0;return}
            processor.process(l,r);left=processor.left;right=processor.right
        }
        override fun reset(){processor.reset();remaining=0;left=0.0;right=0.0}
        override fun close(){processor.close();remaining=0}
    }
    private class BandBooster(payload:List<Int>,sampleRate:Int):Stage {
        private val tailFrames=(sampleRate/10).coerceAtLeast(1)
        private val processor=ReferenceBandBooster(payload[0].toByte().toInt(),payload[1].toByte().toInt(),payload[2].toByte().toInt(),sampleRate)
        private var remaining=0
        override var left=0.0;override var right=0.0
        override val active get()=remaining>0
        override fun process(l:Double,r:Double){
            if(l!=0.0 || r!=0.0)remaining=tailFrames else if(remaining>0)remaining--
            left=processor.process((l*32768).toLong())/32768.0
            right=processor.process((r*32768).toLong())/32768.0
        }
        override fun reset(){processor.reset();remaining=0;left=0.0;right=0.0}
    }
    private class Reverb(private var payload:List<Int>,private val sampleRate:Int):Stage {
        private var native:ImportedNativeReverb?=null
        private var remaining=0L
        private var closed=false
        override var left=0.0;override var right=0.0
        override val active get()=remaining>0
        override fun process(l:Double,r:Double){
            check(!closed){"SET Reverb stage is closed"}
            if(l!=0.0 || r!=0.0)remaining=sampleRate.toLong()*(payload[1]*2L+1) else if(remaining>0)remaining--
            // Allocate only on the audio thread, when this stage first receives signal.
            val processor=native ?: if(remaining>0)ImportedNativeReverb(payload,sampleRate).also{native=it} else null
            if(processor==null){left=l;right=r;return}
            processor.process(l,r);left=processor.left;right=processor.right
        }
        override fun reset(){native?.reset();remaining=0;left=0.0;right=0.0}
        fun configure(updated:List<Int>){payload=updated;native?.configure(updated)}
        override fun close(){if(!closed){closed=true;native?.close();native=null;remaining=0}}
    }
    private class BandMove(from:Int,to:Int,private val delay:Int,speed:Int,private val loop:Boolean,sampleRate:Int):Stage {
        private val eq=Equalizer(List(8){if(it<4)100-from else 100+from},sampleRate)
        private var start=from.toFloat();private var target=to.toFloat();private var position=start
        private var elapsed=0f
        private val milliseconds=1000f/sampleRate
        private var step=(speed*1e-6f)*sampleRate/48000f*(if(from>to)-1f else 1f)
        override var left=0.0;override var right=0.0
        override val active get()=eq.active
        override fun reset(){ } // c inherits n01.c; retain movement and native EQ state.
        override fun process(l:Double,r:Double){
            if(kotlin.math.abs(l)>1000.0/6553600 || kotlin.math.abs(r)>1000.0/6553600){
                if(kotlin.math.abs(position-target)>kotlin.math.abs(step)){
                    if(elapsed>delay)position+=step else elapsed+=milliseconds
                    eq.move(position)
                }else if(loop){
                    step=-step;elapsed=0f
                    val old=start;start=target;target=old
                }
            }
            eq.process(l,r);left=eq.left;right=eq.right
        }
    }
    /** NEqualizer: eight parallel four-pole high-pass branches, summed / 4. */
    private class Equalizer(values:List<Int>,sampleRate:Int):Stage {
        private val frequency=floatArrayOf(1250f,2500f,3750f,5000f,6250f,7500f,8750f,10000f)
        private val angles=doubleArrayOf(Math.PI/8,Math.PI/4,Math.PI/2,Math.PI/2,Math.PI,Math.PI,Math.PI,Math.PI)
        private val coefficients=FloatArray(8){i->(2*kotlin.math.sin((frequency[i]*(1f/sampleRate)).toDouble()*angles[i])).toFloat()}
        private val gains=FloatArray(8){values.getOrElse(it){100}*.01f}
        fun configure(values:List<Int>){require(values.size==32);for(i in 0..7)gains[i]=values[i*4]/100f}
        fun move(position:Float){
            for(i in gains.indices)gains[i]=(if(i<4)100f-position else 100f+position).toInt()*.01f
        }
        private val history=Array(2){Array(8){FloatArray(4)}}
        private val delayed=Array(2){FloatArray(3)}
        override var left=0.0;override var right=0.0
        override val active get()=history.any{it.any{row->row.any{v->kotlin.math.abs(v)>1e-9f}}} || delayed.any{it.any{v->kotlin.math.abs(v)>1e-9f}}
        // sofeh.audio.d inherits the empty n01.c reset.
        override fun reset(){ }
        private fun channel(input:Double,c:Int):Double {
            val x=input.toFloat();var sum=0.0
            for(band in 0..7){
                val state=history[c][band];val coefficient=coefficients[band]
                var value=x
                for(stage in 0..3){
                    val base=if(stage==0)state[stage]+Float.fromBits(0x2f800000) else state[stage]
                    value=Math.fma(value-state[stage],coefficient,base)
                    state[stage]=value
                }
                sum+=((delayed[c][2]-value)*gains[band]).toDouble()
            }
            delayed[c][2]=delayed[c][1];delayed[c][1]=delayed[c][0];delayed[c][0]=x
            return sum/4
        }
        override fun process(l:Double,r:Double){left=channel(l,0);right=channel(r,1)}
    }
    /** NFlanger ARM64: shared modulation coefficient, independent all-pass states. */
    private class Flanger(values:List<Int>,private val sampleRate:Int):Stage {
        private val states=Array(2){FloatArray(values[4])}
        private var feedback=values[1].toFloat()
        private var start=values[2].toFloat()
        private var depth=values[3].toFloat()*-0.003921568859368563f
        private var wet=values[5].toFloat()
        private var increment=values[0].toFloat()*0.15707963705062866f/sampleRate
        fun configure(values:List<Int>){feedback=values[1].toFloat();start=values[2].toFloat();depth=values[3].toFloat()*-0.003921568859368563f;wet=values[5].toFloat();increment=values[0].toFloat()*0.15707963705062866f/sampleRate}
        private var frame=0;private var coefficient=0f
        private val previous=FloatArray(2)
        override var left=0.0;override var right=0.0
        override val active get()=previous.any{kotlin.math.abs(it)>1e-9f} || states.any{it.any{v->kotlin.math.abs(v)>1e-9f}}
        // sofeh.audio.e inherits n01.c(), which leaves the native instance untouched.
        override fun reset(){ }
        private fun channel(input:Double,c:Int):Double {
            var value=Math.fma(previous[c]*.01f,feedback,input.toFloat())
            for(i in states[c].indices){
                val old=states[c][i]
                val next=Math.fma(old,coefficient,value)
                states[c][i]=next
                value=Math.fma(-coefficient,next,old)
            }
            previous[c]=value
            return ((value*wet+input.toFloat()*(255f-wet))*0.003921568859368563f).toDouble()
        }
        override fun process(l:Double,r:Double){
            if(frame%20==0){
                val angle=Math.fma(increment,frame.toFloat(),start)
                val cosine=kotlin.math.cos(angle.toDouble()).toFloat()
                val exponential=kotlin.math.exp((cosine+cosine+2f).toDouble()).toFloat()
                coefficient=1f+(exponential-1f)*depth/53.598148345947266f
            }
            frame++
            left=channel(l,0);right=channel(r,1)
        }
    }
    /** r01.e: clip first, then smooth using the previous final output. */
    private class Distortion(private var gain:Int,private var soft:Boolean,private var cutoff:Int):Stage {
        private var level=(1.0/maxOf(1.0,kotlin.math.sqrt(gain.toDouble()/cutoff))).toFloat()
        private var smoothing=1f/cutoff
        fun configure(g:Int,s:Boolean,c:Int){gain=g;soft=s;cutoff=c;level=(1.0/maxOf(1.0,kotlin.math.sqrt(g.toDouble()/c))).toFloat();smoothing=1f/c}
        private var stateL=0.0;private var stateR=0.0
        override var left=0.0;override var right=0.0
        override val active get()=kotlin.math.abs(stateL)>1e-9 || kotlin.math.abs(stateR)>1e-9
        override fun reset(){stateL=0.0;stateR=0.0;left=0.0;right=0.0}
        private fun clip(input:Double):Double {
            val amplified=input*gain
            return if(soft)kotlin.math.tanh(amplified) else amplified.coerceIn(-1.0,1.0)
        }
        override fun process(l:Double,r:Double){
            stateL=(stateL*(1.0-smoothing)+clip(l)*smoothing)*level
            stateR=(stateR*(1.0-smoothing)+clip(r)*smoothing)*level
            left=stateL;right=stateR
        }
    }
    /** q01: fixed 10082-frame ring, fractional delay, independent stereo feedback. */
    private class Chorus(values:List<Int>,rate:Int):Stage {
        private val buffers=Array(2){FloatArray(10082)}
        private var cursor=0;private var phase=0f;private var energy=0.0
        private var depth=(values[1]/100f)*rate/1000f
        private var delay=values[2]*rate/1000f
        private var feedback=values[3]/100f
        private var mix=values[4]/100f
        private var spread=Math.toRadians(values[5].toDouble()).toFloat()
        private var increment=((values[0]/100f).toDouble()*2*Math.PI/rate).toFloat()
        fun configure(values:List<Int>,rate:Int){depth=(values[1]/100f)*rate/1000f;delay=values[2]*rate/1000f;feedback=values[3]/100f;mix=values[4]/100f;spread=Math.toRadians(values[5].toDouble()).toFloat();increment=((values[0]/100f).toDouble()*2*Math.PI/rate).toFloat()}
        override var left=0.0;override var right=0.0
        override val active get()=energy>1e-18
        // q01.c clears rings/cursor while retaining oscillator phase.
        override fun reset(){buffers.forEach{it.fill(0f)};cursor=0;energy=0.0;left=0.0;right=0.0}
        private fun read(position:Float,buffer:FloatArray):Float {
            var p=position
            while(p<0)p+=buffer.size
            p-=(kotlin.math.floor(p/buffer.size)*buffer.size)
            val index=p.toInt();val fraction=p-index
            return (buffer[(index+1)%buffer.size]-buffer[index])*fraction+buffer[index]
        }
        override fun process(l:Double,r:Double){
            val dl=delay+((kotlin.math.sin(phase)*.5f)+.5f)*depth
            val dr=delay+((kotlin.math.sin(phase+spread)*.5f)+.5f)*depth
            phase+=increment;if(phase>2*Math.PI)phase-=6.2831855f
            val wetL=read(cursor-dl,buffers[0]);val wetR=read(cursor-dr,buffers[1])
            for(c in 0..1){
                val old=buffers[c][cursor].toDouble()
                val value=(if(c==0)l else r).toFloat()+(if(c==0)wetL else wetR)*feedback
                buffers[c][cursor]=value
                energy=(energy-old*old+value.toDouble()*value).coerceAtLeast(0.0)
            }
            cursor=(cursor+1)%10082
            left=(wetL*mix+l.toFloat()*(1f-mix)).toDouble()
            right=(wetR*mix+r.toFloat()*(1f-mix)).toDouble()
        }
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
    private class Robot(var frequency:Int,private val sampleRate:Int):Stage {
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
        private var width=(volume/10000.0f)*6.5536f
        fun configure(volume:Int){width=(volume/10000.0f)*6.5536f}
        override var left=0.0;override var right=0.0
        override val active=false
        override fun reset(){left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            val centre=(l+r)/2.0
            left=((l-centre)*width+centre+l)/2.0
            right=((r-centre)*width+centre+r)/2.0
        }
    }
    private class Bass(var volume:Float,var frequency:Float):Stage{
        private var stateL=0f;private var stateR=0f
        override var left=0.0;override var right=0.0
        private var smoothing=1f/(frequency+1f)
        fun configure(gain:Int,frequency:Int){volume=gain/16f;this.frequency=frequency.toFloat();smoothing=1f/(this.frequency+1f)}
        override val active get()=kotlin.math.abs(stateL)>1e-9f || kotlin.math.abs(stateR)>1e-9f
        override fun reset(){stateL=0f;stateR=0f;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            stateL=(stateL*frequency+l.toFloat())*smoothing
            stateR=(stateR*frequency+r.toFloat())*smoothing
            left=l+stateL*volume;right=r+stateR*volume
        }
    }
    private class Echo(var feedback:Double,var delay:Int,val stereo:Boolean=false):Stage{
        // s01 retains all delay cells when the playback window changes.
        private val bufferL=DoubleArray(48001)
        private val bufferR=DoubleArray(48001)
        private var cursor=0
        private var phase=0
        private var direction=1
        private var energy=0.0
        override val active get()=energy>1e-18
        init { require(!stereo || delay>0){"Stereo Echo requires a positive delay"} }
        fun configure(gain:Int,newDelay:Int){
            require(!stereo || newDelay>0){"Stereo Echo requires a positive delay"}
            feedback=gain/100.0;delay=newDelay
            energy=0.0
            for(i in 0 until delay.coerceAtLeast(1))energy+=bufferL[i]*bufferL[i]+bufferR[i]*bufferR[i]
            // t01.a resets only the stereo sweep, not its delay memory/cursor.
            if(stereo){phase=0;direction=1}
        }
        override var left=0.0;override var right=0.0
        override fun reset(){bufferL.fill(0.0);bufferR.fill(0.0);cursor=0;energy=0.0;left=0.0;right=0.0}
        override fun process(l:Double,r:Double){
            if(cursor>=delay)cursor=0
            val echoL=bufferL[cursor]*feedback;val echoR=bufferR[cursor]*feedback
            left=l+echoL*(if(stereo)phase.toDouble()/delay else 1.0)
            right=r+echoR*(if(stereo)(delay-phase).toDouble()/delay else 1.0)
            // The feedback buffers receive the full echo, independent of output modulation.
            energy-=bufferL[cursor]*bufferL[cursor]+bufferR[cursor]*bufferR[cursor]
            bufferL[cursor]=l+echoL;bufferR[cursor]=r+echoR
            energy=(energy+bufferL[cursor]*bufferL[cursor]+bufferR[cursor]*bufferR[cursor]).coerceAtLeast(0.0)
            if(stereo){phase+=direction;if(phase==delay || phase==0)direction=-direction}
            cursor++
        }
    }
    private fun int(bytes:List<Int>,offset:Int):Int =
        bytes[offset] or (bytes[offset+1] shl 8) or (bytes[offset+2] shl 16) or (bytes[offset+3] shl 24)
    private var modules=if(!settings.fxEnabled)emptyList() else settings.modules.filter{it.enabled && it.type in listOf(20,6,4,17,9,10,16,1,21,23,8,7,2,29,31,15)}
    private var stages:List<Stage> = modules.map{
        if(it.type==15){
            require(it.payload.size==4){"Invalid Pitch Shift payload"}
            return@map PitchShift(int(it.payload,0),sampleRate)
        }
        if(it.type==31){
            require(it.payload.size==4 && it.payload.take(3).all{v->v.toByte().toInt() in -100..100}){"Invalid SET Band Booster parameters"}
            return@map BandBooster(it.payload,sampleRate)
        }
        if(it.type==29){
            require(it.payload.size==8 && it.payload.take(7).all{v->v in 1..100}){"Invalid SET Reverb parameters"}
            return@map Reverb(it.payload,sampleRate)
        }
        if(it.type==2){
            require(it.payload.size==17){"Invalid Band Move payload"}
            val from=int(it.payload,0)-100;val to=int(it.payload,4)-100
            val delay=int(it.payload,8);val speed=int(it.payload,12)
            require(from in -100..100 && to in -100..100 && delay in 1..5000 && speed in 1..2000 && it.payload[16] in 0..1 && sampleRate==48000){"Unsupported Band Move parameters"}
            return@map BandMove(from,to,delay,speed,it.payload[16]==1,sampleRate)
        }
        if(it.type==7){
            require(it.payload.size%4==0 && it.payload.size<=32){"Invalid Equalizer band count"}
            val values=List(it.payload.size/4){i->int(it.payload,i*4)}
            require(values.all{v->v in 0..200} && sampleRate==48000){"Unsupported Equalizer parameters"}
            return@map Equalizer(values,sampleRate)
        }
        if(it.type==8){
            require(it.payload.size==28){"Invalid Flanger payload"}
            val values=listOf(int(it.payload,0),int(it.payload,8),int(it.payload,12),int(it.payload,16),int(it.payload,20),int(it.payload,24))
            require(values[0] in 1..300 && values[1] in 1..90 && values[2] in 1..100 && values[3] in 1..255 && values[4] in 1..16 && values[5] in 1..256 && sampleRate>0){"Unsupported Flanger parameters"}
            return@map Flanger(values,sampleRate)
        }
        if(it.type==23){
            require(it.payload.size==9){"Invalid Distortion payload"}
            val gain=int(it.payload,0);val cutoff=int(it.payload,5)
            require(gain in 1..100 && cutoff in 1..100 && it.payload[4] in 0..1){"Unsupported Distortion parameters"}
            return@map Distortion(gain,it.payload[4]==1,cutoff)
        }
        if(it.type==21){
            require(it.payload.size==24){"Invalid Chorus payload"}
            val values=List(6){i->int(it.payload,i*4)}
            require(values[0] in 5..500 && values[1] in 10..2000 && values[2] in 5..100 &&
                values[3] in -95..95 && values[4] in 0..100 && values[5] in 0..180 && sampleRate>0){"Unsupported Chorus parameters"}
            return@map Chorus(values,sampleRate)
        }
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
    private var closed=false
    /** v01.a clears only the changed filter; x01 retains its frame counter. */
    fun updateFilterOrModulation(moduleIndex:Int,updated:ImportedFxModule):Boolean {
        check(!closed)
        val original=settings.modules.getOrNull(moduleIndex) ?: return false
        if(original.type !in listOf(1,7,8,9,10,16,17,21,23,29) || updated.type!=original.type ||
            original.enabled!=updated.enabled || original.realtime!=updated.realtime)return false
        require(updated.payload.size==when(updated.type){1->8;7->32;8->28;21->24;23->9;29->8;else->4})
        val value=int(updated.payload,0)
        val replacement:Stage=when(updated.type){
            1->{
                val width=int(updated.payload,4)
                require(value in 0..150000 && width in 0..25000 && sampleRate>0)
                BandPass(value,width,sampleRate)
            }
            7->{require(updated.payload.size==32 && (0..7).all{i->int(updated.payload,i*4) in 0..200});Equalizer(updated.payload.chunked(4).map{int(it,0)},sampleRate)}
            29->{require(updated.payload.take(7).all{it in 1..100});Reverb(updated.payload,sampleRate)}
            23->{val g=int(updated.payload,0);val c=int(updated.payload,5);require(g in 1..100 && c in 1..100 && updated.payload[4] in 0..1);Distortion(g,updated.payload[4]==1,c)}
            8->{require(sampleRate>0);Flanger(listOf(value,int(updated.payload,8),int(updated.payload,12),int(updated.payload,16),int(updated.payload,20),int(updated.payload,24)),sampleRate)}
            21->{require(sampleRate>0);Chorus(listOf(value,int(updated.payload,4),int(updated.payload,8),int(updated.payload,12),int(updated.payload,16),int(updated.payload,20)),sampleRate)}
            9,10->{require(value in 0..150000 && sampleRate>0);PassFilter(value,sampleRate,updated.type==9)}
            16->{require(value in 0..1000 && sampleRate>0);Robot(value,sampleRate)}
            else->{require(value in 0..50000);StereoWidth(value)}
        }
        if(settings.fxEnabled && original.enabled){
            val index=settings.modules.take(moduleIndex).count{it.enabled}
            val stage=stages.getOrNull(index) ?: return false
            when(updated.type){
                16->(stage as? Robot ?: return false).frequency=value
                17->(stage as? StereoWidth ?: return false).configure(value)
                7->(stage as? Equalizer ?: return false).configure(updated.payload)
                8->(stage as? Flanger ?: return false).configure(listOf(value,int(updated.payload,8),int(updated.payload,12),int(updated.payload,16),int(updated.payload,20),int(updated.payload,24)))
                21->(stage as? Chorus ?: return false).configure(listOf(value,int(updated.payload,4),int(updated.payload,8),int(updated.payload,12),int(updated.payload,16),int(updated.payload,20)),sampleRate)
                29->(stage as? Reverb ?: return false).configure(updated.payload)
                23->(stage as? Distortion ?: return false).configure(int(updated.payload,0),updated.payload[4]==1,int(updated.payload,5))
                else->{stage.close();stages=stages.toMutableList().also{it[index]=replacement}}
            }
            modules=modules.toMutableList().also{it[index]=updated}
        }
        settings=settings.copy(modules=settings.modules.toMutableList().also{it[moduleIndex]=updated})
        return true
    }
    fun updateEcho(moduleIndex:Int,updated:ImportedFxModule):Boolean {
        check(!closed)
        val original=settings.modules.getOrNull(moduleIndex) ?: return false
        if(original.type !in listOf(4,6) || updated.type!=original.type || updated.payload.size!=8 ||
            original.enabled!=updated.enabled || original.realtime!=updated.realtime)return false
        val gain=int(updated.payload,0);val frequency=int(updated.payload,4)
        require(gain in 0..99 && frequency in 0..100)
        val delay=minOf(48000,(frequency.toLong()*sampleRate/100).toInt())
        require(updated.type!=4 || delay>0){"Stereo Echo requires a positive delay"}
        if(settings.fxEnabled && original.enabled){
            val index=settings.modules.take(moduleIndex).count{it.enabled}
            val stage=stages.getOrNull(index) as? Echo ?: return false
            stage.configure(gain,delay)
            modules=modules.toMutableList().also{it[index]=updated}
        }
        settings=settings.copy(modules=settings.modules.toMutableList().also{it[moduleIndex]=updated})
        return true
    }
    fun updateBass(moduleIndex:Int,updated:ImportedFxModule):Boolean {
        check(!closed)
        val original=settings.modules.getOrNull(moduleIndex) ?: return false
        if(original.type!=20 || updated.type!=20 || updated.payload.size!=8 ||
            original.enabled!=updated.enabled || original.realtime!=updated.realtime)return false
        val gain=int(updated.payload,0);val frequency=int(updated.payload,4)
        require(gain in 0..100 && frequency in 0..100)
        if(settings.fxEnabled && original.enabled){
            val index=settings.modules.take(moduleIndex).count{it.enabled}
            val stage=stages.getOrNull(index) as? Bass ?: return false
            stage.configure(gain,frequency)
            modules=modules.toMutableList().also{it[index]=updated}
        }
        settings=settings.copy(modules=settings.modules.toMutableList().also{it[moduleIndex]=updated})
        return true
    }
    /** Pitch Shift passes its parameter to JNI per sample; no native reopen or other-stage reset. */
    fun updatePitchShift(moduleIndex:Int,updated:ImportedFxModule):Boolean {
        check(!closed)
        val original=settings.modules.getOrNull(moduleIndex) ?: return false
        if(original.type!=15 || updated.type!=15 || updated.payload.size!=4 ||
            original.enabled!=updated.enabled || original.realtime!=updated.realtime)return false
        val shift=int(updated.payload,0)
        require(shift in 50..200)
        if(settings.fxEnabled && original.enabled){
            val index=settings.modules.take(moduleIndex).count{it.enabled}
            val stage=stages.getOrNull(index) as? PitchShift ?: return false
            stage.setShift(shift)
            modules=modules.toMutableList().also{it[index]=updated}
        }
        settings=settings.copy(modules=settings.modules.toMutableList().also{it[moduleIndex]=updated})
        return true
    }
    val active get()=!closed && stages.any{it.active}
    fun reset(){check(!closed){"SET FX chain is closed"};stages.forEach{it.reset()};left=0.0;right=0.0}
    /** Track.Start -> z01.f(true) resets only modules marked realtime. */
    fun resetRealtime(){check(!closed){"SET FX chain is closed"};stages.forEachIndexed{index,stage->if(modules[index].realtime)stage.reset()}}
    override fun close(){
        if(closed)return
        closed=true
        try{stages.forEach{it.close()}}finally{left=0.0;right=0.0}
    }
    fun process(inputLeft:Double,inputRight:Double){
        check(!closed){"SET FX chain is closed"}
        left=inputLeft;right=inputRight
        for(stage in stages){
            stage.process(left,right);left=stage.left;right=stage.right
        }
    }
}
