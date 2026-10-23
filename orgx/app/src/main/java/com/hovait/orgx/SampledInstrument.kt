package com.hovait.orgx

import kotlin.math.pow

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int,val gain:Double=1.0,val pan:Int=0,val looping:Boolean=true,val tune:Int=0)

class SampledInstrument(private val regions:List<InstrumentRegion>,private val outputRate:Int,private val exactNotes:Boolean=false,val envelope:ImportedEnvelope?=null,
    private val pitchMode:Int=if(exactNotes)2 else 0,private val tuning:List<Double> = emptyList(),private val tuningTranspose:Int=0) {
    init {
        require(regions.isNotEmpty())
        regions.forEach {
            require(it.sample.frames.isNotEmpty() && it.gain.isFinite() && it.gain>=0 && it.pan in -100..100)
            if(it.looping)require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size)
        }
    }
    private val roots=regions.map{it.root}
    private var layers:List<SampledInstrument> = emptyList()
    private var aftertouchInstrument:SampledInstrument?=null
    private var expressionEffects:List<ImportedExpressionEffect> = emptyList()
    var touchScript:ImportedSimpleScript.Result?=null;private set
    private var modeScript:ImportedModeScript?=null
    private var scriptSession:ImportedScriptSession?=null
    val scriptSliders:List<ImportedScriptSession.Slider> get()=scriptSession?.sliders.orEmpty()
    @Volatile var scriptSliderValues:Map<String,Int> = emptyMap();private set
    fun setScriptSlider(name:String,value:Int){checkNotNull(scriptSession){"No script session"}.setSlider(name,value);scriptSliderValues=scriptSliderValues+(name.lowercase() to value)}
    fun withModeScript(source:String)=also{
        modeScript=ImportedModeScript.parse(source)
        scriptSession=runCatching{ImportedScriptSession(source)}.getOrNull()
        scriptSliderValues=scriptSliders.associate{it.name to it.reset}
        touchScript=runCatching{ImportedSimpleScript.evaluate(ImportedModeScript.parse(source),"touch")}.getOrNull()
            ?.takeIf{result->result.plays.all{play->play.parameters.keys.all{it in setOf("octavemove","volume")}}}
    }
    private fun applyScriptEffects(changes:List<ImportedSimpleScript.EffectChange>){
                var updated=expressionEffects
                changes.forEach{change->
                    val old=updated.firstOrNull{it.slot==change.slot} ?: ImportedExpressionEffect(change.slot,false,0,false,false,0,0,0,0,emptyList())
                    val next=when(change.property){
                        "enable"->old.copy(enabled=change.value!=0)
                        "startdelay"->old.copy(startDelay=change.value)
                        "bypedal"->old.copy(byPedal=change.value!=0)
                        "stopbyrelease"->old.copy(stopByRelease=change.value!=0)
                        "mode"->old.copy(mode=change.value and 255)
                        "speed"->old.copy(speed=change.value)
                        "depth"->old.copy(depth=change.value)
                        else->old.copy(gain=change.value)
                    }
                    updated=updated.filterNot{it.slot==change.slot}+next
                }
                expressionEffects=updated
    }
    fun withExpressionEffects(effects:List<ImportedExpressionEffect>)=also{expressionEffects=effects.toList()}
    val keyConnectionEffect:ImportedExpressionEffect? get()=expressionEffects.firstOrNull{it.slot==1 && it.enabled}
    val keyRepeatEffect:ImportedExpressionEffect? get()=expressionEffects.firstOrNull{it.slot==0 && it.enabled}
    val outputSampleRate:Int get()=outputRate
    var keyboardSettings=ImportedChannelSettings();private set
    private var keyboardMelodic=true
    fun withKeyboardSettings(settings:ImportedChannelSettings,melodic:Boolean)=also{keyboardSettings=settings;keyboardMelodic=melodic}
    fun withAftertouch(alternate:SampledInstrument)=also{aftertouchInstrument=alternate}
    /** Reuse immutable PCM while keeping mode/keyboard configuration isolated. */
    fun copySampleSource():SampledInstrument {
        require(layers.isEmpty()){"Copy each decoded source before composing mode layers"}
        return SampledInstrument(regions,outputRate,exactNotes,envelope,pitchMode,tuning,tuningTranspose)
    }
    fun keyboardNote(note:Int):Int=if(!keyboardMelodic)note else
        (note.toLong()+keyboardSettings.transpose+keyboardSettings.octave.toLong()*12).coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt()
    companion object {
        fun combine(sounds:List<SampledInstrument>):SampledInstrument {
            require(sounds.isNotEmpty())
            if(sounds.size==1)return sounds.single()
            return SampledInstrument(sounds.first().regions,sounds.first().outputRate).also{it.layers=sounds}
        }
    }
    fun voice(note:Int,tune:Int=0,pan:Int=0,autoRelease:Boolean=false,effectsAllowed:Boolean=true,scriptVelocity:Int=100)=Playback(note,tune,pan,autoRelease,effectsAllowed,scriptVelocity=scriptVelocity)
    inner class Playback(private val note:Int,private val tune:Int,private val pan:Int,private val autoRelease:Boolean=false,private val effectsAllowed:Boolean=true,scriptAllowed:Boolean=true,private val scriptVelocity:Int=100,private val envelopeAutoRelease:Boolean?=null,private val envelopeOverrides:Map<String,Int> = emptyMap()){
        private val ownsScriptEvents=scriptAllowed && scriptSession!=null
        private var scriptReleased=false
        private var balance=0
        fun channelBalance(value:Int){balance=value;children.forEach{it.channelBalance(value)};alternate?.channelBalance(value)}
        private val scriptPlan=if(scriptAllowed)runCatching{
            scriptSession?.event("touch",mapOf("key" to note,"velocity" to scriptVelocity)){result->
                require(result.effects.all{it.slot in 0..3}){"Unsupported script effect slot"}
                require(result.plays.all{play->play.parameters.keys.all{it in setOf("key","velocity","keymove","octavemove","volume","pan","tune","pitchmove","autorelease","attack","decay","sustain","release","connectkey","connectspeed")}})
                result.plays.forEach{command->
                    val combinedTune=Math.addExact(tune,command.parameters["tune"] ?: 0)
                    val target=((command.parameters["key"] ?: note).toLong()+(command.parameters["keymove"] ?: 0)+12L*(command.parameters["octavemove"] ?: 0)).coerceIn(0,127).toInt()
                    val rate=pitchStep(target,combinedTune)
                    require(rate.isFinite() && rate>0){"Script pitch outside playback range"}
                }
            }?.also{result->
                applyScriptEffects(result.effects)
            }
        }.getOrNull() else null
        private val scriptChildren=scriptPlan?.plays.orEmpty().map{command->
            val key=((command.parameters["key"] ?: note).toLong()+(command.parameters["keymove"] ?: 0)+12L*(command.parameters["octavemove"] ?: 0)).coerceIn(0,127).toInt()
            val playback=Playback(key,Math.addExact(tune,command.parameters["tune"] ?: 0),
                (pan.toLong()+(command.parameters["pan"] ?: 0)).coerceIn(-100,100).toInt(),
                command.parameters["autorelease"]?.takeIf{it>=0}?.let{it!=0} ?: autoRelease,effectsAllowed,false,scriptVelocity,
                command.parameters["autorelease"]?.takeIf{it>=0}?.let{it!=0},command.parameters)
            command.parameters["connectkey"]?.let{sourceKey->
                playback.connectFrom(sourceKey.coerceIn(0,127),ImportedExpressionEffect(1,true,0,false,false,0,
                    command.parameters["connectspeed"] ?: 100,0,0,emptyList()))
            }
            playback.scriptPitchMove(command.parameters["pitchmove"] ?: 0)
            val velocityRatio=command.parameters["velocity"]?.let{it.coerceAtLeast(0).toDouble()/scriptVelocity.coerceAtLeast(1)} ?: 1.0
            playback to ((command.parameters["volume"] ?: 100).coerceAtLeast(0)/100.0*velocityRatio)
        }
        private val children=if(scriptPlan!=null)scriptChildren.map{it.first}+
            (if(scriptPlan.handled)emptyList() else listOf(Playback(note,tune,pan,autoRelease,effectsAllowed,false,scriptVelocity)))
            else layers.map{it.Playback(note,tune,pan,autoRelease,effectsAllowed,true,scriptVelocity,envelopeAutoRelease,envelopeOverrides)}
        private var step=if(note in 0..127 && !missing(note))pitchStep(note,tune) else 1.0
        private var connectionTarget=step
        private var connectionDelta=0.0
        private var connectionDelay=0
        private var connectionByPedal=false
        private var connectionStopByRelease=false
        private var connectionActive=false
        private var bendSemitones=0.0
        private var scriptPitchFactor=1.0
        private fun scriptPitchMove(value:Int){
            scriptPitchFactor=(1000L+value).coerceAtLeast(1)/1000.0
            children.forEach{it.scriptPitchMove(value)}
        }
        private var bentStep=step
        private var aftertouch=false
        private var alternate:Playback?=null
        fun pitchBend(semitones:Double){
            require(semitones.isFinite()){"Pitch bend must be finite"}
            val nextStep=step*2.0.pow(semitones/12.0)
            require(nextStep.isFinite() && nextStep>0){"Pitch bend exceeds supported playback range"}
            bendSemitones=semitones;bentStep=nextStep
            children.forEach{it.pitchBend(semitones)};alternate?.pitchBend(semitones)
        }
        /** Channels._Connection: keep the current sample position and move its pitch to the new key. */
        fun connectTo(targetNote:Int,effect:ImportedExpressionEffect){
            require(targetNote in 0..127 && effect.slot==1)
            children.forEach{it.connectTo(targetNote,effect)};alternate?.connectTo(targetNote,effect)
            if(children.isNotEmpty())return
            val target=pitchStep(targetNote,tune)
            connectionTarget=target
            connectionDelay=(effect.startDelay.coerceAtLeast(0).toLong()*outputRate/1000).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
            connectionByPedal=effect.byPedal
            connectionStopByRelease=effect.stopByRelease
            connectionActive=effect.enabled
            connectionDelta=if(effect.playbackSpeed==0)target-step else
                (target-step)*effect.playbackSpeed/(8.192*outputRate)
            if(effect.playbackSpeed==0){
                val lowMode=effect.mode and 15
                val restart=lowMode==1 || (lowMode==3 && (envelope?.autoRelease==true || !selected[note].looping))
                if(restart)restartConnectionSample()
            }
        }
        /** Low modes open a new target voice, but initialize pitch from the previous key. */
        fun connectFrom(sourceNote:Int,effect:ImportedExpressionEffect){
            require(sourceNote in 0..127 && effect.slot==1)
            children.forEach{it.connectFrom(sourceNote,effect)};alternate?.connectFrom(sourceNote,effect)
            if(children.isNotEmpty())return
            if(effect.mode>1 && selected[note].looping)position=selected[note].loopStart.toDouble()
            if(effect.playbackSpeed==0)position=0.0
            val target=pitchStep(note,tune)
            step=if(effect.mode==1 && effect.playbackSpeed!=0)target else pitchStep(sourceNote,tune)
            bentStep=step*2.0.pow(bendSemitones/12.0)
            connectionTarget=target
            connectionDelay=(effect.startDelay.coerceAtLeast(0).toLong()*outputRate/1000).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
            connectionByPedal=effect.byPedal
            connectionStopByRelease=effect.stopByRelease
            connectionActive=effect.enabled
            connectionDelta=if(effect.playbackSpeed==0)target-step else
                (target-step)*effect.playbackSpeed/(8.192*outputRate)
        }
        fun configureConnection(effect:ImportedExpressionEffect){
            require(effect.slot==1)
            children.forEach{it.configureConnection(effect)};alternate?.configureConnection(effect)
            connectionActive=effect.enabled
            connectionByPedal=effect.byPedal
            connectionStopByRelease=effect.stopByRelease
            connectionDelay=(effect.startDelay.coerceAtLeast(0).toLong()*outputRate/1000).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
            connectionDelta=if(effect.playbackSpeed==0)connectionTarget-step else
                (connectionTarget-step)*effect.playbackSpeed/(8.192*outputRate)
        }
        fun aftertouch(pressure:Float){
            if(ownsScriptEvents){
                val handled=runCatching{scriptSession?.event("aftertouch",mapOf("key" to note,
                    "velocity" to (pressure.coerceIn(0f,1f)*127).toInt(),"keyvelocity" to scriptVelocity)){result->
                    require(result.plays.isEmpty()){"Aftertouch audio commands are not supported yet"}
                    require(result.effects.all{it.slot in 2..3}){"Unsupported aftertouch effect slot"}
                }?.also{result->
                    applyScriptEffects(result.effects)
                    result.effects.map{it.slot}.distinct().forEach{slot->
                        val effect=expressionEffects.first{it.slot==slot}
                        if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                    }
                }?.handled==true}.getOrDefault(false)
                if(handled)return
            }
            // Original Track switches to InstMode.Aftertouch on the first
            // positive pressure event. It closes the old key and opens the
            // alternate sound once; pressure is not a 50% cross-fade gate.
            if(!aftertouch && pressure>0f && aftertouchInstrument!=null){
                aftertouch=true;alternate=aftertouchInstrument!!.voice(note,tune,pan,autoRelease,effectsAllowed,scriptVelocity);alternate?.pitchBend(bendSemitones);alternate?.channelBalance(balance)
            }
        }
        private val effectiveEnvelope=envelope?.let{original->
            fun value(name:String,fallback:Int)=envelopeOverrides[name]?.takeIf{it>=0}?.coerceAtMost(if(name=="sustain")100 else 127) ?: fallback
            original.copy(attack=value("attack",original.attack),decay=value("decay",original.decay),
                sustain=value("sustain",original.sustain),release=value("release",original.release),
                autoRelease=envelopeAutoRelease ?: original.autoRelease)
        }
        private var state=effectiveEnvelope?.voice(outputRate)
        private var position=0.0
        private fun restartConnectionSample(){
            position=0.0
            autoReleaseStarted=false
            if(effectiveEnvelope?.autoRelease==true)state=effectiveEnvelope.voice(outputRate)
        }
        private var pitchShake=expressionEffects.firstOrNull{effectsAllowed && it.slot==2 && it.enabled}?.let{ImportedPitchShake(it,outputRate)}
        fun configurePitchShake(effect:ImportedExpressionEffect){
            val current=pitchShake
            if(current==null)pitchShake=ImportedPitchShake(effect,outputRate) else current.configure(effect)
        }
        private var volumeShake=expressionEffects.firstOrNull{effectsAllowed && it.slot==3}?.let{ImportedVolumeShake(it,outputRate)}
        fun configureVolumeShake(effect:ImportedExpressionEffect){
            val current=volumeShake
            if(current==null)volumeShake=ImportedVolumeShake(effect,outputRate) else current.configure(effect)
        }
        var left=0.0;private set
        var right=0.0;private set
        var finished=false;private set
        private var autoReleaseStarted=false
        fun next(noteOff:Boolean,pedal:Boolean=false,inheritedPitchOffset:Double=0.0){
            if(noteOff && ownsScriptEvents && !scriptReleased){
                scriptReleased=true
                runCatching{scriptSession?.event("release",mapOf("key" to note,"velocity" to scriptVelocity)){result->
                    require(result.plays.isEmpty() && !result.handled){"Release audio commands are not supported yet"}
                    require(result.effects.all{it.slot in 2..3}){"Unsupported release effect slot"}
                }?.also{result->
                    applyScriptEffects(result.effects)
                    result.effects.map{it.slot}.distinct().forEach{slot->
                        val effect=expressionEffects.first{it.slot==slot}
                        if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                    }
                }}
            }
            if(autoRelease && state==null && position>=if(selected[note].looping)selected[note].loopEnd else selected[note].sample.frames.size)autoReleaseStarted=true
            val released=noteOff || autoReleaseStarted
            if(connectionActive && (!connectionByPedal || pedal) && (!connectionStopByRelease || !released)){
                if(connectionDelay>0)connectionDelay-- else if(step!=connectionTarget){
                    step=if(connectionDelta==0.0 || kotlin.math.abs(connectionTarget-step)<=kotlin.math.abs(connectionDelta))connectionTarget else step+connectionDelta
                    bentStep=step*2.0.pow(bendSemitones/12.0)
                }
            }
            pitchShake?.next(released,pedal)
            val pitchOffset=inheritedPitchOffset+(pitchShake?.offset ?: 0.0)
            if(aftertouch){alternate?.next(noteOff,pedal,pitchOffset);left=alternate?.left ?: 0.0;right=alternate?.right ?: 0.0;finished=alternate?.finished ?: true
            }else if(scriptPlan!=null && children.isEmpty()){
                left=0.0;right=0.0;finished=true
            }else if(children.isNotEmpty()){
                children.forEach{it.next(noteOff,pedal,pitchOffset)}
                fun gain(child:Playback)=scriptChildren.firstOrNull{it.first===child}?.second ?: 1.0
                left=children.sumOf{it.left*gain(it)};right=children.sumOf{it.right*gain(it)};finished=children.all{it.finished}
            }else{
                val gain=state?.next(released,pedal) ?: if(released)0.0 else 1.0
                left=samplePosition(note,position,0,pan+balance)*gain;right=samplePosition(note,position,1,pan+balance)*gain
                position+=(bentStep*scriptPitchFactor+pitchOffset/8192.0).coerceAtLeast(1.0/8192)
                finished=state?.finished==true || note !in 0..127 || missing(note) ||
                    (!selected[note].looping && position>=selected[note].sample.frames.size) || (state==null && noteOff)
            }
            if(children.isEmpty() && state==null && autoReleaseStarted)finished=true
            volumeShake?.let{it.next(released,pedal);left*=it.left;right*=it.right}
        }
    }
    private val mapped=Array(128){note->when(pitchMode){
        1->SampleRegionMap.repeatedRoot(note,roots)
        2->note.takeIf{it in roots}
        else->SampleRegionMap.nearestRoot(note,roots)
    }}
    private val selected=Array(128){note->regions.firstOrNull{it.root==mapped[note]} ?: regions.first()}
    private fun pitchStep(note:Int,tune:Int=0):Double {
        val key=if(pitchMode==1)mapped[note] ?: note else note
        val region=selected[note]
        return if(tuning.isEmpty())SamplePitch.step(key,region.root,region.sample.sampleRate,outputRate,region.tune+tune)
        else SamplePitch.referenceStep(key,region.root,region.sample.sampleRate,outputRate,region.tune+tune,tuning,tuningTranspose)
    }
    private val steps=DoubleArray(128){pitchStep(it)}
    private fun missing(note:Int)=mapped[note]==null
    fun durationFrames(note:Int):Int {
        if(layers.isNotEmpty())return layers.maxOf{it.durationFrames(note)}
        if(note !in 0..127 || missing(note))return 0
        val region=selected[note]
        return if(region.looping)Int.MAX_VALUE else kotlin.math.ceil(region.sample.frames.size/steps[note]).coerceAtMost(Int.MAX_VALUE.toDouble()).toInt()
    }
    fun finished(note:Int,age:Long):Boolean {
        if(layers.isNotEmpty())return layers.all{it.finished(note,age)}
        if(note !in 0..127)return true
        if(missing(note))return true
        val region=selected[note]
        return !region.looping && age*steps[note]>=region.sample.frames.size
    }
    fun sample(note:Int,age:Long,channel:Int=0):Double {
        if(layers.isNotEmpty())return layers.sumOf{it.sample(note,age,channel)}
        return sampleAt(note,age,channel,if(note in 0..127)steps[note] else 1.0)
    }
    private fun sampleAt(note:Int,age:Long,channel:Int,step:Double,notePan:Int=0):Double {
        return samplePosition(note,age*step,channel,notePan)
    }
    private fun samplePosition(note:Int,position:Double,channel:Int,notePan:Int=0):Double {
        if(note !in 0..127 || position<0)return 0.0
        if(missing(note))return 0.0
        val region=selected[note]
        val pan=region.pan+notePan
        val panGain=if(channel==0)minOf(100,100-pan) else minOf(100,100+pan)
        val value=if(region.looping)SampleResampler.looped(region.sample,position,channel,region.loopStart,region.loopEnd)
            else SampleResampler.channel(region.sample,position,channel)
        return value*region.gain*panGain/100
    }
}
