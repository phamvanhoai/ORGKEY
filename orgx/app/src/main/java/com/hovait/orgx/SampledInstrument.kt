package com.hovait.orgx

import kotlin.math.pow

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int,val gain:Double=1.0,val pan:Int=0,val looping:Boolean=true,val tune:Int=0,val tempoSync:Boolean=false,val loopUntilRelease:Boolean=false)

class SampledInstrument(private val regions:List<InstrumentRegion>,private val outputRate:Int,private val exactNotes:Boolean=false,val envelope:ImportedEnvelope?=null,
    private val pitchMode:Int=if(exactNotes)2 else 0,private val tuning:List<Double> = emptyList(),private val tuningTranspose:Int=0,
    private val rawRelease:Int?=null,private val musicReleasePercent:Int=100,private val loadedReleasePercent:Int=100,
    private val loadedMusicTranspose:Int=0,private val loadedMusicOctave:Int=0) {
    init {
        regions.forEach {
            require(it.sample.frames.isNotEmpty() && it.gain.isFinite() && it.gain>=0)
            if(it.looping)require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size)
        }
    }
    private val roots=regions.map{it.root}
    private var layers:List<SampledInstrument> = emptyList()
    private var aftertouchInstrument:SampledInstrument?=null
    private var musicReleaseProvider:(()->Int)?=null
    private var musicState:ImportedMusicState?=null
    fun withMusicState(state:ImportedMusicState):SampledInstrument=also{
        if(musicState===state)return@also
        musicState=state
        layers.forEach{it.withMusicState(state)}
        aftertouchInstrument?.withMusicState(state)
        scriptSounds.values.forEach{it.withMusicState(state)}
        modeRoute?.sounds?.values?.forEach{it.withMusicState(state)}
    }
    /** Share Music state, but read its release only when a new playback envelope is built. */
    fun withMusicRelease(provider:()->Int):SampledInstrument=also{
        if(musicReleaseProvider===provider)return@also
        musicReleaseProvider=provider
        layers.forEach{it.withMusicRelease(provider)}
        aftertouchInstrument?.withMusicRelease(provider)
        scriptSounds.values.forEach{it.withMusicRelease(provider)}
        modeRoute?.sounds?.values?.forEach{it.withMusicRelease(provider)}
    }
    private var expressionEffects:List<ImportedExpressionEffect> = emptyList()
    var touchScript:ImportedSimpleScript.Result?=null;private set
    private var modeScript:ImportedModeScript?=null
    private var scriptSession:ImportedScriptSession?=null
    private var playbackRegistry=java.util.Collections.synchronizedMap(java.util.WeakHashMap<Playback,Boolean>())
    private fun sharePlaybackRegistry(other:SampledInstrument){
        other.playbackRegistry=playbackRegistry
        other.layers.forEach{sharePlaybackRegistry(it)}
        other.scriptSounds.values.forEach{sharePlaybackRegistry(it)}
        other.aftertouchInstrument?.let{sharePlaybackRegistry(it)}
    }
    private fun playingCount(includeReleased:Boolean):Int = synchronized(playbackRegistry){
        playbackRegistry.keys.count{it.countsAsPlaying(includeReleased)}
    }
    private var scriptSounds:Map<Int,SampledInstrument> = emptyMap()
    private var scriptDefaultSound=0
    private var scriptSoundRouting=false
    fun withScriptSounds(sounds:Map<Int,SampledInstrument>,defaultSound:Int)=also{
        scriptSounds=sounds.toMap();scriptDefaultSound=defaultSound.coerceAtLeast(0);scriptSoundRouting=true
        scriptSounds.values.forEach{sharePlaybackRegistry(it)}
    }
    private class ModeRoute(val sounds:Map<Int,SampledInstrument>,@Volatile var selected:Int)
    private var modeRoute:ModeRoute?=null
    val activeModeSound:SampledInstrument get()=modeRoute?.let{it.sounds[it.selected]} ?: this
    val activeMode:Int get()=modeRoute?.selected ?: 0
    private var ownModeSelection:ImportedSoundSelection.Mode?=null
    internal var sourceSelectionKey:String?=null
    val selectionKey:String? get()=modeSelection?.instrument?.let(ImportedSoundSelection::key) ?: sourceSelectionKey
    var modeSelection:ImportedSoundSelection.Mode?
        get()=if(activeModeSound!==this)activeModeSound.modeSelection else ownModeSelection
        internal set(value){ownModeSelection=value}
    fun withExitModes(modes:Map<Int,SampledInstrument>,selected:Int)=also{
        require(selected in modes)
        val route=ModeRoute(modes.toMap(),selected)
        modes.values.forEach{it.modeRoute=route;sharePlaybackRegistry(it)}
    }
    private fun modeAvailable(mode:Int)=modeRoute?.sounds?.containsKey(mode) ?: (mode==0)
    private fun exitMode(mode:Int){modeRoute?.let{if(mode in it.sounds)it.selected=mode}}
    fun selectMode(mode:Int):Boolean {
        val route=modeRoute ?: return mode==0
        if(mode !in route.sounds)return false
        route.selected=mode
        return true
    }
    @Volatile private var ownScriptStatus="No mode script"
    var scriptStatus:String
        get()=if(activeModeSound!==this)"Mode $activeMode: ${activeModeSound.scriptStatus}" else ownScriptStatus
        private set(value){ownScriptStatus=value}
    fun updateScriptModulation(level:Int){
        if(activeModeSound!==this)return activeModeSound.updateScriptModulation(level)
        require(level in -127..127)
        runCatching{scriptSession?.modulation(level){result->
            require(result.audio.isEmpty() && result.effects.all{it.slot in 2..3})
        }?.also{applyScriptEffects(it.effects)}}
    }
    fun updateScriptPitchBend(level:Int){
        if(activeModeSound!==this)return activeModeSound.updateScriptPitchBend(level)
        runCatching{scriptSession?.pitchBend(level,forceEvent=true){result->
            require(result.audio.isEmpty() && result.effects.all{it.slot in 2..3})
        }?.also{applyScriptEffects(it.effects)}}
    }
    fun updateScriptPedal(down:Boolean){
        if(activeModeSound!==this)return activeModeSound.updateScriptPedal(down)
        runCatching{scriptSession?.pedal(down){result->
            require(result.plays.isEmpty() && result.effects.all{it.slot in 2..3})
        }?.also{applyScriptEffects(it.effects)}}
    }
    val scriptSliders:List<ImportedScriptSession.Slider> get()=if(activeModeSound!==this)activeModeSound.scriptSliders else scriptSession?.sliders.orEmpty()
    val scriptSettingTitles:List<String> get()=if(activeModeSound!==this)activeModeSound.scriptSettingTitles else scriptSession?.settingTitles.orEmpty()
    val scriptSettings:List<ImportedScriptSession.Setting> get()=if(activeModeSound!==this)activeModeSound.scriptSettings else scriptSession?.settings.orEmpty()
    private var ownScriptSettingsKey:String?=null
    var scriptSettingsKey:String?
        get()=if(activeModeSound!==this)activeModeSound.scriptSettingsKey else ownScriptSettingsKey
        internal set(value){ownScriptSettingsKey=value}
    @Volatile private var ownScriptSliderValues:Map<String,Int> = emptyMap()
    val scriptSliderValues:Map<String,Int> get()=if(activeModeSound!==this)activeModeSound.scriptSliderValues else ownScriptSliderValues
    fun setScriptSlider(name:String,value:Int){if(activeModeSound!==this)return activeModeSound.setScriptSlider(name,value);checkNotNull(scriptSession){"No script session"}.setSlider(name,value);ownScriptSliderValues=ownScriptSliderValues+(name.lowercase() to value)}
    fun withModeScript(source:String,storedSettings:Map<String,String> = emptyMap())=also{
        modeScript=runCatching{ImportedModeScript.parse(source)}.getOrNull()
        val initialized=runCatching{ImportedScriptSession(source,expressionEffects,storedSettings=storedSettings,modeAvailable=::modeAvailable,onModeExit=::exitMode,playingCount=::playingCount)}
        scriptSession=initialized.getOrNull()
        scriptStatus=if(source.isBlank())"No mode script" else initialized.exceptionOrNull()?.let{"Script initialization failed: ${it.message}"} ?: "Mode script loaded; waiting for events"
        scriptSession?.synchronizeEffects(expressionEffects)
        scriptSession?.let{applyScriptEffects(it.initialEffects)}
        ownScriptSliderValues=scriptSession?.settingValues.orEmpty()
        touchScript=runCatching{ImportedSimpleScript.evaluate(ImportedModeScript.parse(source),"touch")}.getOrNull()
            ?.takeIf{result->result.plays.all{play->play.parameters.keys.all{it in setOf("octavemove","volume")}}}
    }
    private fun applyScriptEffects(changes:List<ImportedSimpleScript.EffectChange>){
                var updated=expressionEffects
                changes.forEach{change->
                    val old=updated.firstOrNull{it.slot==change.slot} ?: ImportedExpressionEffect.default(change.slot)
                    val next=when(change.property){
                        "enable"->old.copy(enabled=change.value!=0)
                        "startdelay"->old.copy(startDelay=change.value)
                        "bypedal"->old.copy(byPedal=change.value!=0)
                        "stopbyrelease"->old.copy(stopByRelease=change.value!=0)
                        "mode"->old.copy(mode=change.value.toByte().toInt())
                        "speed"->old.copy(speed=change.value)
                        "depth"->old.copy(depth=change.value)
                        else->old.copy(gain=change.value)
                    }
                    updated=updated.filterNot{it.slot==change.slot}+next
                }
                expressionEffects=updated
    }
    fun withExpressionEffects(effects:List<ImportedExpressionEffect>)=also{
        expressionEffects=effects.toList();scriptSession?.synchronizeEffects(effects)
        scriptSession?.let{applyScriptEffects(it.initialEffects)}
    }
    val keyConnectionEffect:ImportedExpressionEffect? get()=if(activeModeSound!==this)activeModeSound.keyConnectionEffect else expressionEffects.firstOrNull{it.slot==1 && it.enabled}
    val keyRepeatEffect:ImportedExpressionEffect? get()=if(activeModeSound!==this)activeModeSound.keyRepeatEffect else expressionEffects.firstOrNull{it.slot==0 && it.enabled}
    val outputSampleRate:Int get()=outputRate
    private var ownKeyboardSettings=ImportedChannelSettings()
    val keyboardSettings:ImportedChannelSettings get()=if(activeModeSound!==this)activeModeSound.keyboardSettings else ownKeyboardSettings
    private var keyboardMelodic=true
    fun withKeyboardSettings(settings:ImportedChannelSettings,melodic:Boolean)=also{ownKeyboardSettings=settings;keyboardMelodic=melodic}
    fun withAftertouch(alternate:SampledInstrument)=also{aftertouchInstrument=alternate;sharePlaybackRegistry(alternate)}
    /** Reuse immutable PCM while keeping mode/keyboard configuration isolated. */
    fun copySampleSource():SampledInstrument {
        require(layers.isEmpty()){"Copy each decoded source before composing mode layers"}
        return SampledInstrument(regions,outputRate,exactNotes,envelope,pitchMode,tuning,tuningTranspose,rawRelease,musicReleasePercent,loadedReleasePercent,loadedMusicTranspose,loadedMusicOctave)
            .also{copy->musicReleaseProvider?.let{copy.withMusicRelease(it)}}
            .also{copy->musicState?.let{copy.withMusicState(it)}}
    }
    fun keyboardNote(note:Int):Int=if(activeModeSound!==this)activeModeSound.keyboardNote(note) else if(!keyboardMelodic)note else
        (note.toLong()+keyboardSettings.transpose+keyboardSettings.octave.toLong()*12+
            (musicState?.settings?.let{it.transpose-loadedMusicTranspose+12L*(it.octave-loadedMusicOctave)} ?: 0L))
            .coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt()
    companion object {
        @Volatile var loopTempo=false
        @Volatile var tempo=120
            set(value){require(value in 40..240);field=value}
        /** Music.fPitchSpeed is shared by the original sampled output channels. */
        @Volatile var pitchSpeed=0
            set(value){require(value in 0..100);field=value}
        fun combine(sounds:List<SampledInstrument>):SampledInstrument {
            require(sounds.isNotEmpty())
            if(sounds.size==1)return sounds.single()
            return SampledInstrument(sounds.first().regions,sounds.first().outputRate).also{combined->combined.layers=sounds;sounds.forEach{combined.sharePlaybackRegistry(it)}}
        }
    }
    fun voice(note:Int,tune:Int=0,pan:Int=0,autoRelease:Boolean=false,effectsAllowed:Boolean=true,scriptVelocity:Int=100,initialScriptPedal:Boolean=false,scriptAllowed:Boolean=true,scriptId:Int=note,initialAftertouch:Boolean=false,releasePercent:Int?=null):Playback=
        if(activeModeSound!==this)activeModeSound.voice(note,tune,pan,autoRelease,effectsAllowed,scriptVelocity,initialScriptPedal,scriptAllowed,scriptId,initialAftertouch,releasePercent)
        else Playback(note,tune,pan,autoRelease,effectsAllowed,scriptAllowed=scriptAllowed,scriptVelocity=scriptVelocity,initialScriptPedal=initialScriptPedal,scriptId=scriptId,releasePercent=releasePercent).also{if(initialAftertouch)it.startAftertouch()}
    inner class Playback(private val note:Int,private val tune:Int,private val pan:Int,private val autoRelease:Boolean=false,private val effectsAllowed:Boolean=true,scriptAllowed:Boolean=true,private val scriptVelocity:Int=100,private val envelopeAutoRelease:Boolean?=null,private val envelopeOverrides:Map<String,Int> = emptyMap(),private val initialScriptPedal:Boolean=false,private val scriptId:Int=note,private val channelPanOverride:Int?=null,scriptPedalValue:Int?=null,private val releasePercent:Int?=null){
        private var scriptPedal=false
        private var playbackPedal=scriptPedalValue?.let{it!=0}
        private val ownsScriptEvents=scriptAllowed && scriptSession!=null
        private var scriptReleased=false
        private var releaseHandled=false
        private var forcedRelease=false
        private var countReleased=false
        private var forcedCut=false
        internal fun cut(){
            forceRelease();forcedCut=true;finished=true;left=0.0;right=0.0
            children.forEach{it.cut()};alternate?.cut()
            playbackRegistry.remove(this)
        }
        private var timerOwner:Long?=null
        private var timerFrames=0L
        fun forceRelease(){
            countReleased=true
            forcedRelease=true;scriptReleased=true;releaseHandled=false
            timerOwner?.let{scriptSession?.cancelTimer(it)}
            children.forEach{it.forceRelease()};alternate?.forceRelease()
        }
        private var balance=channelPanOverride ?: 0
        fun channelBalance(value:Int){balance=channelPanOverride ?: value;children.forEach{it.channelBalance(balance)};alternate?.channelBalance(balance)}
        private fun validateScriptAudio(result:ImportedSimpleScript.Result){
                require(result.effects.all{it.slot in 0..3}){"Unsupported script effect slot"}
                require(result.plays.all{play->play.parameters.keys.all{it in setOf("id","sound","key","velocity","balance","keymove","octavemove","volume","pan","pedal","tune","pitchmove","autorelease","attack","decay","sustain","release","connectkey","connectspeed")}})
                result.plays.forEach{command->
                    val sound=command.parameters["sound"]
                    require(!scriptSoundRouting && sound==null || (sound ?: scriptDefaultSound) in scriptSounds){"Script sound unavailable: ${sound ?: scriptDefaultSound}"}
                    val source=scriptSounds[sound ?: scriptDefaultSound] ?: this@SampledInstrument
                    val combinedTune=Math.addExact(tune,command.parameters["tune"] ?: 0)
                    val target=((command.parameters["key"] ?: note).toLong()+(command.parameters["keymove"] ?: 0)+12L*(command.parameters["octavemove"] ?: 0)).coerceIn(0,127).toInt()
                    val rate=source.pitchStep(target,combinedTune)
                    require(rate.isFinite() && rate>0){"Script pitch outside playback range"}
                }
        }
        private val scriptPlan=if(scriptAllowed)runCatching{
            scriptSession?.event("touch",mapOf("id" to scriptId,"balance" to pan,"key" to note,"velocity" to scriptVelocity,"pedal" to if(initialScriptPedal)1 else 0)){result->
                validateScriptAudio(result)
                require(result.plays.all{it.parameters["id"]==null || it.parameters["id"]==scriptId}){"Touch Play targets another note ID"}
            }?.also{result->
                applyScriptEffects(result.effects)
                if(result.timers.isNotEmpty())timerOwner=scriptSession?.timerToken
            }
        }.onSuccess{if(modeScript?.source?.isNotBlank()==true && it!=null)scriptStatus="Touch script active"}
            .onFailure{scriptStatus="Touch fallback: ${it.message}"}.getOrNull() else null
        private fun createScriptChild(command:ImportedSimpleScript.Play):Pair<Playback,Double>{
            val source=scriptSounds[command.parameters["sound"] ?: scriptDefaultSound] ?: this@SampledInstrument
            val key=((command.parameters["key"] ?: note).toLong()+(command.parameters["keymove"] ?: 0)+12L*(command.parameters["octavemove"] ?: 0)).coerceIn(0,127).toInt()
            val playback=source.Playback(key,Math.addExact(tune,command.parameters["tune"] ?: 0),
                (command.parameters["balance"] ?: 0).coerceIn(-100,100),
                command.parameters["autorelease"]?.takeIf{it>=0}?.let{it!=0} ?: autoRelease,effectsAllowed,false,scriptVelocity,
                command.parameters["autorelease"]?.takeIf{it>=0}?.let{it!=0},command.parameters,initialScriptPedal,command.parameters["id"] ?: scriptId,command.parameters["pan"]?.coerceIn(-100,100),command.parameters["pedal"] ?: 1,releasePercent)
            command.parameters["connectkey"]?.let{sourceKey->
                playback.connectFrom(sourceKey.coerceIn(0,127),ImportedExpressionEffect(1,true,0,false,false,0,
                    command.parameters["connectspeed"] ?: 100,0,0,emptyList()))
            }
            playback.scriptPitchMove(command.parameters["pitchmove"] ?: 0)
            val velocityRatio=(command.parameters["velocity"] ?: 100).coerceAtLeast(0).toDouble()/scriptVelocity.coerceAtLeast(1)
            return playback to ((command.parameters["volume"] ?: 100).coerceAtLeast(0)/100.0*velocityRatio)
        }
        private val scriptChildren=scriptPlan?.plays.orEmpty().map(::createScriptChild).toMutableList()
        private val initialChildren=if(scriptPlan!=null)scriptChildren.map{it.first}+
            (if(scriptPlan.handled)emptyList() else listOf(Playback(note,tune,pan,autoRelease,effectsAllowed,false,scriptVelocity,initialScriptPedal=initialScriptPedal,scriptId=scriptId,releasePercent=releasePercent)))
            else layers.map{it.Playback(note,tune,pan,autoRelease,effectsAllowed,scriptAllowed,scriptVelocity,envelopeAutoRelease,envelopeOverrides,initialScriptPedal,scriptId,channelPanOverride,scriptPedalValue,releasePercent)}
        private val children=initialChildren.toMutableList()
        private var step=if(note in 0..127 && !missing(note))pitchStep(note,tune) else 1.0
        private var connectionTarget=step
        private var connectionDelta=0.0
        private var connectionDelay=0
        private var connectionByPedal=false
        private var connectionStopByRelease=false
        private var connectionActive=false
        private var bendSemitones=0.0
        private var bendScriptLevel:Int?=null
        private var scriptPitchFactor=1.0
        private fun scriptPitchMove(value:Int){
            scriptPitchFactor=(1000L+value).coerceAtLeast(1)/1000.0
            children.forEach{it.scriptPitchMove(value)}
            alternate?.scriptPitchMove(value)
        }
        private var bentStep=step
        private var bendFactor=1.0
        private var bendFrames=0
        private var bendDelta=0.0
        private var hasRendered=false
        private fun advanceBend(){
            val target=2.0.pow(bendSemitones/12.0)
            if(pitchSpeed==0 || (loopTempo && selected[note].tempoSync)){bendFactor=target;bendFrames=0}
            else{
                if(bendFrames<=0){
                    bendFrames=(outputRate/100).coerceAtLeast(1)
                    bendDelta=(target-bendFactor)/(bendFrames.toDouble()*pitchSpeed)
                }
                bendFactor+=bendDelta;bendFrames--
                if(kotlin.math.abs(target-bendFactor)<1e-10)bendFactor=target
            }
            bentStep=step*bendFactor;hasRendered=true
        }
        private var aftertouch=false
        private var currentKey=note
        private var alternate:Playback?=null
        private var heldModulation:Int?=null
        fun pitchBend(semitones:Double,scriptLevel:Int?=null,notifyScript:Boolean=true){
            require(semitones.isFinite()){"Pitch bend must be finite"}
            require(scriptLevel==null || scriptLevel in -8192..8191)
            bendScriptLevel=scriptLevel
            if(ownsScriptEvents && notifyScript){
                val handled=runCatching{scriptSession?.pitchBend(
                    (scriptLevel ?: (semitones/2.0*8192).toInt()).coerceIn(-8192,8191)){result->
                    require(result.plays.isEmpty() && result.effects.all{it.slot in 2..3})
                }?.also{result->
                    applyScriptEffects(result.effects)
                    result.effects.map{it.slot}.distinct().forEach{slot->
                        val effect=expressionEffects.first{it.slot==slot}
                        if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                    }
                }?.handled==true}.getOrDefault(false)
                if(handled)return
            }
            val nextStep=step*2.0.pow(semitones/12.0)
            require(nextStep.isFinite() && nextStep>0){"Pitch bend exceeds supported playback range"}
            if(bendSemitones!=semitones)bendFrames=0
            bendSemitones=semitones
            if(!hasRendered || pitchSpeed==0){bendFactor=2.0.pow(semitones/12.0);bentStep=nextStep}
            children.forEach{it.pitchBend(semitones,scriptLevel,notifyScript)};alternate?.pitchBend(semitones,scriptLevel,notifyScript)
        }
        /** Channels._Connection: keep the current sample position and move its pitch to the new key. */
        fun connectTo(targetNote:Int,effect:ImportedExpressionEffect){
            require(targetNote in 0..127 && effect.slot==1)
            currentKey=targetNote
            scriptReleased=false
            releaseHandled=false
            // Channels._Connection reopens the existing channel (Close=false).
            // A previous forced release must not close the newly connected key.
            forcedRelease=false
            loopExited=false
            if(!effect.stopByRelease && effectiveEnvelope?.autoRelease==false)state?.reconnect()
            children.forEach{it.connectTo(targetNote,effect)};alternate?.connectTo(targetNote,effect)
            if(children.isNotEmpty())return
            val target=pitchStep(targetNote,tune,selected[note])
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
            step=if(effect.mode==1 && effect.playbackSpeed!=0)target else pitchStep(sourceNote,tune,selected[note])
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
        private fun applyOrderedAudio(result:ImportedSimpleScript.Result){
                children.removeAll{it.finished};scriptChildren.removeAll{it.first.finished}
                result.audio.forEach{command->when(command){
                    is ImportedSimpleScript.StopAll->children.forEach{if(command.cut)it.cut() else it.forceRelease()}
                    is ImportedSimpleScript.Stop->children.filter{it.scriptId==command.id}.forEach{if(command.cut)it.cut() else it.forceRelease()}
                    is ImportedSimpleScript.Play->if((command.parameters["id"] ?: scriptId)==scriptId){
                        val child=createScriptChild(command)
                        child.first.pitchBend(bendSemitones,bendScriptLevel);child.first.channelBalance(balance)
                        heldModulation?.let{child.first.modulate(it)}
                        children.add(child.first);scriptChildren.add(child)
                    }
                }}
        }
        /** Track event 4 runs Touch once, then OpenKey selects the alternate sound. */
        internal fun startAftertouch(){
            if(scriptPlan!=null){
                // A handled Touch owns its audio. Only the default OpenKey fallback changes sound.
                if(!scriptPlan.handled)children.lastOrNull()?.startAftertouch()
                return
            }
            if(aftertouch || aftertouchInstrument==null)return
            aftertouch=true
            alternate=aftertouchInstrument!!.voice(currentKey,tune,pan,autoRelease,effectsAllowed,
                scriptVelocity,scriptPedal,scriptAllowed=false,scriptId=scriptId,releasePercent=releasePercent)
        }
        fun aftertouch(pressure:Float){
            if(ownsScriptEvents){
                val handled=runCatching{scriptSession?.event("aftertouch",mapOf("id" to scriptId,"key" to currentKey,
                    "velocity" to (pressure.coerceIn(0f,1f)*127).toInt(),"keyvelocity" to scriptVelocity,"pedal" to if(scriptPedal)1 else 0)){result->
                    validateScriptAudio(result)
                    require(result.audio.all{command->when(command){
                        is ImportedSimpleScript.Play->(command.parameters["id"] ?: scriptId)==scriptId
                        is ImportedSimpleScript.Stop->command.id==scriptId
                        is ImportedSimpleScript.StopAll->false
                    }}){"Aftertouch audio must target its own note ID"}
                    require(children.count{!it.finished}+result.plays.size<=128){"Aftertouch exceeds voice limit"}
                    require(result.effects.all{it.slot in 2..3}){"Unsupported aftertouch effect slot"}
                }?.also{result->
                    applyScriptEffects(result.effects)
                    result.effects.map{it.slot}.distinct().forEach{slot->
                        val effect=expressionEffects.first{it.slot==slot}
                        if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                    }
                    applyOrderedAudio(result)
                    if(result.audio.isNotEmpty())scriptStatus="Aftertouch script active"
                }?.handled==true}.onFailure{scriptStatus="Aftertouch fallback: ${it.message}"}.getOrDefault(false)
                if(handled)return
            }
            if(scriptPlan!=null && children.isNotEmpty()){
                children.forEach{it.aftertouch(pressure)}
                return
            }
            // Original Track switches to InstMode.Aftertouch on the first
            // positive pressure event. It closes the old key and opens the
            // alternate sound once; pressure is not a 50% cross-fade gate.
            if(!aftertouch && pressure>0f && aftertouchInstrument!=null){
                aftertouch=true;alternate=aftertouchInstrument!!.voice(currentKey,tune,pan,autoRelease,effectsAllowed,scriptVelocity,scriptPedal,scriptId=scriptId,releasePercent=releasePercent);alternate?.pitchBend(bendSemitones,bendScriptLevel);alternate?.channelBalance(balance)
                alternate?.scriptPitchMove(((scriptPitchFactor-1.0)*1000).toInt())
                heldModulation?.let{alternate?.modulate(it)}
                if(scriptPedal)alternate?.scriptPedal(true)
            }
        }
        private var appliedModulation:Int?=null
        fun modulate(level:Int){
            require(level in -127..127)
            heldModulation=level
            alternate?.modulate(level)
            if(!ownsScriptEvents)return
            if(appliedModulation==level)return
            runCatching{scriptSession?.modulation(level){result->
                validateScriptAudio(result)
                require(result.effects.all{it.slot in 2..3})
                require(children.count{!it.finished}+result.plays.count{(it.parameters["id"] ?: scriptId)==scriptId}<=128){"Modulation exceeds voice limit"}
            }?.also{result->
                appliedModulation=level
                applyScriptEffects(result.effects)
                result.effects.map{it.slot}.distinct().forEach{slot->
                    val effect=expressionEffects.first{it.slot==slot}
                    if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                }
                applyOrderedAudio(result)
                scriptStatus="Modulation script active"
            }}.onFailure{scriptStatus="Modulation fallback: ${it.message}"}
        }
        fun scriptPedal(down:Boolean){
            if(playbackPedal!=null)playbackPedal=down
            children.forEach{it.scriptPedal(down)}
            alternate?.scriptPedal(down)
            if(scriptPedal==down)return
            scriptPedal=down
            if(!ownsScriptEvents)return
            runCatching{scriptSession?.pedal(down){result->
                require(result.plays.isEmpty() && result.effects.all{it.slot in 2..3})
            }?.also{result->
                applyScriptEffects(result.effects)
                result.effects.map{it.slot}.distinct().forEach{slot->
                    val effect=expressionEffects.first{it.slot==slot}
                    if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                }
            }}
        }
        private val effectiveEnvelope=envelope?.let{original->
            fun value(name:String,fallback:Int)=envelopeOverrides[name]?.takeIf{it>=0}?.coerceAtMost(if(name=="sustain")100 else 127) ?: fallback
            original.copy(attack=value("attack",original.attack),decay=value("decay",original.decay),
                sustain=value("sustain",original.sustain),release=if(rawRelease!=null || releasePercent!=null || musicReleaseProvider!=null)
                    (value("release",rawRelease ?: original.release).toLong()*(releasePercent ?: loadedReleasePercent)*(musicReleaseProvider?.invoke() ?: musicReleasePercent)/10000).coerceIn(0,100).toInt()
                    else value("release",original.release),
                autoRelease=envelopeAutoRelease ?: original.autoRelease)
        }
        private var state=effectiveEnvelope?.voice(outputRate)
        private var position=0.0
        private var loopExited=false
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
        internal fun countsAsPlaying(includeReleased:Boolean)=!finished && !forcedCut && !aftertouch && children.isEmpty() &&
            (scriptPlan==null || !scriptPlan.handled) && (includeReleased || !countReleased) && note in 0..127 && !missing(note)
        private var autoReleaseStarted=false
        init{playbackRegistry[this]=true}
        private fun advanceScriptTimer(){
            val owner=timerOwner ?: return
            val session=scriptSession ?: return
            if(!session.hasTimer(owner))return
            runCatching{
                session.advanceTimer(owner,timerFrames*1000/outputRate){result->
                    validateScriptAudio(result)
                    require(result.audio.all{command->when(command){
                        is ImportedSimpleScript.Play->(command.parameters["id"] ?: scriptId)==scriptId
                        is ImportedSimpleScript.Stop->command.id==scriptId
                        is ImportedSimpleScript.StopAll->false
                    }}){"Timer audio must target its own note ID"}
                    require(children.count{!it.finished}+result.plays.size<=128){"Script timer exceeds voice limit"}
                }?.let{result->
                    children.removeAll{it.finished}
                    scriptChildren.removeAll{it.first.finished}
                    applyScriptEffects(result.effects)
                    result.effects.map{it.slot}.distinct().forEach{slot->
                        val effect=expressionEffects.first{it.slot==slot}
                        if(slot==2)configurePitchShake(effect) else if(slot==3)configureVolumeShake(effect)
                    }
                    applyOrderedAudio(result)
                    if(result.timers.isNotEmpty())timerOwner=session.timerToken
                    scriptStatus="Timer script active"
                }
            }.onFailure{scriptStatus="Timer stopped: ${it.message}"}
        }
        fun next(noteOff:Boolean,pedal:Boolean=false,inheritedPitchOffset:Double=0.0,effectPedal:Boolean=pedal){
            if(forcedCut){left=0.0;right=0.0;finished=true;return}
            if(noteOff && ownsScriptEvents && !scriptReleased){
                scriptReleased=true
                runCatching{scriptSession?.event("release",mapOf("id" to scriptId,"key" to currentKey,"velocity" to scriptVelocity,"pedal" to if(scriptPedal)1 else 0)){result->
                    validateScriptAudio(result)
                    require(result.audio.all{command->when(command){
                        is ImportedSimpleScript.Play->(command.parameters["id"] ?: scriptId)==scriptId
                        is ImportedSimpleScript.Stop->command.id==scriptId
                        is ImportedSimpleScript.StopAll->false
                    }}){"Release audio must target its own note ID"}
                    require(children.count{!it.finished}+result.plays.size<=128){"Release script exceeds voice limit"}
                    require(result.effects.all{it.slot in 2..3}){"Unsupported release effect slot"}
                }?.also{result->
                    releaseHandled=result.handled
                    applyScriptEffects(result.effects)
                    result.effects.map{it.slot}.distinct().forEach{slot->
                        val effect=expressionEffects.first{it.slot==slot}
                        if(slot==2)configurePitchShake(effect) else configureVolumeShake(effect)
                    }
                    applyOrderedAudio(result)
                    if(result.audio.isNotEmpty())scriptStatus="Release script active"
                }}.onFailure{timerOwner?.let{scriptSession?.cancelTimer(it)};scriptStatus="Release fallback: ${it.message}"}
            }
            val effectivePedal=playbackPedal ?: pedal
            val effectiveEffectPedal=playbackPedal ?: effectPedal
            advanceScriptTimer()
            timerFrames++
            if(autoRelease && state==null && position>=if(selected[note].looping)selected[note].loopEnd else selected[note].sample.frames.size)autoReleaseStarted=true
            val effectiveNoteOff=forcedRelease || (noteOff && !releaseHandled)
            val released=effectiveNoteOff || autoReleaseStarted
            countReleased=released
            advanceBend()
            if(connectionActive && (!connectionByPedal || effectiveEffectPedal) && (!connectionStopByRelease || !released)){
                if(connectionDelay>0)connectionDelay-- else if(step!=connectionTarget){
                    step=if(connectionDelta==0.0 || kotlin.math.abs(connectionTarget-step)<=kotlin.math.abs(connectionDelta))connectionTarget else step+connectionDelta
                    bentStep=step*bendFactor
                }
            }
            pitchShake?.next(released,effectiveEffectPedal)
            val pitchOffset=inheritedPitchOffset+(pitchShake?.offset ?: 0.0)
            if(aftertouch){alternate?.next(effectiveNoteOff,effectivePedal,pitchOffset,effectiveEffectPedal);left=alternate?.left ?: 0.0;right=alternate?.right ?: 0.0;finished=alternate?.finished ?: true
            }else if(scriptPlan!=null && children.isEmpty()){
                left=0.0;right=0.0;finished=timerOwner?.let{scriptSession?.hasTimer(it)}!=true
            }else if(children.isNotEmpty()){
                children.forEach{it.next(effectiveNoteOff,effectivePedal,pitchOffset,effectiveEffectPedal)}
                fun gain(child:Playback)=scriptChildren.firstOrNull{it.first===child}?.second ?: 1.0
                left=children.sumOf{it.left*gain(it)};right=children.sumOf{it.right*gain(it)};finished=children.all{it.finished} && timerOwner?.let{scriptSession?.hasTimer(it)}!=true
            }else{
                val gain=state?.next(released,effectivePedal) ?: if(released)0.0 else 1.0
                val region=selected[note]
                if(region.loopUntilRelease && released && !loopExited){
                    if(region.looping && position>=region.loopEnd)
                        position=region.loopStart+(position-region.loopStart)%(region.loopEnd-region.loopStart)
                    loopExited=true
                }
                left=samplePosition(note,position,0,pan+balance,!loopExited)*gain;right=samplePosition(note,position,1,pan+balance,!loopExited)*gain
                val tempoFactor=if(loopTempo && selected[note].tempoSync)tempo/120.0 else 1.0
                position+=(bentStep*scriptPitchFactor*tempoFactor+pitchOffset/8192.0).coerceAtLeast(1.0/8192)
                finished=state?.finished==true || note !in 0..127 || missing(note) ||
                    ((!selected[note].looping || loopExited) && position>=selected[note].sample.frames.size) || (state==null && effectiveNoteOff)
            }
            if(children.isEmpty() && state==null && autoReleaseStarted)finished=true
            volumeShake?.let{it.next(released,effectiveEffectPedal);left*=it.left;right*=it.right}
            if(finished)playbackRegistry.remove(this)
        }
    }
    private val mapped=Array<Int?>(128){note->if(roots.isEmpty())null else when(pitchMode){
        1->SampleRegionMap.repeatedRoot(note,roots)
        2->note.takeIf{it in roots}
        else->SampleRegionMap.nearestRoot(note,roots)
    }}
    // Empty sound banks are valid in SETs. The placeholder is never mapped to
    // a MIDI key; it only keeps interpolation/envelope setup allocation-free.
    private val emptyRegion=InstrumentRegion(60,SamplePcm(outputRate,1,shortArrayOf(0)),0,1,looping=false)
    private val selected=Array(128){note->regions.firstOrNull{it.root==mapped[note]} ?: regions.firstOrNull() ?: emptyRegion}
    private fun pitchStep(note:Int,tune:Int=0,region:InstrumentRegion=selected[note]):Double {
        val key=if(pitchMode==1)region.root else note
        return if(tuning.isEmpty())SamplePitch.step(key,region.root,region.sample.sampleRate,outputRate,region.tune+tune)
        else SamplePitch.referenceStep(key,region.root,region.sample.sampleRate,outputRate,region.tune+tune,tuning,
            tuningTranspose+(musicState?.settings?.transpose ?: loadedMusicTranspose)-loadedMusicTranspose)
    }
    private val steps=DoubleArray(128){pitchStep(it)}
    private fun missing(note:Int)=mapped[note]==null
    internal fun keyDurationFrames(note:Int,aftertouch:Boolean):Int =
        if(activeModeSound!==this)activeModeSound.keyDurationFrames(note,aftertouch)
        else if(aftertouch && aftertouchInstrument!=null)aftertouchInstrument!!.durationFrames(note)
        else durationFrames(note)
    fun durationFrames(note:Int):Int {
        if(activeModeSound!==this)return activeModeSound.durationFrames(note)
        if(layers.isNotEmpty())return layers.maxOf{it.durationFrames(note)}
        if(note !in 0..127 || missing(note))return 0
        val region=selected[note]
        return if(region.looping)Int.MAX_VALUE else kotlin.math.ceil(region.sample.frames.size/steps[note]).coerceAtMost(Int.MAX_VALUE.toDouble()).toInt()
    }
    fun finished(note:Int,age:Long):Boolean {
        if(activeModeSound!==this)return activeModeSound.finished(note,age)
        if(layers.isNotEmpty())return layers.all{it.finished(note,age)}
        if(note !in 0..127)return true
        if(missing(note))return true
        val region=selected[note]
        return !region.looping && age*steps[note]>=region.sample.frames.size
    }
    fun sample(note:Int,age:Long,channel:Int=0):Double {
        if(activeModeSound!==this)return activeModeSound.sample(note,age,channel)
        if(layers.isNotEmpty())return layers.sumOf{it.sample(note,age,channel)}
        return sampleAt(note,age,channel,if(note in 0..127)steps[note] else 1.0)
    }
    private fun sampleAt(note:Int,age:Long,channel:Int,step:Double,notePan:Int=0):Double {
        return samplePosition(note,age*step,channel,notePan)
    }
    private fun samplePosition(note:Int,position:Double,channel:Int,notePan:Int=0,allowLoop:Boolean=true):Double {
        if(note !in 0..127 || position<0)return 0.0
        if(missing(note))return 0.0
        val region=selected[note]
        val pan=region.pan.toLong()+notePan
        val panGain=(if(channel==0)100L-pan else 100L+pan).coerceIn(0L,100L)
        val value=if(region.looping && allowLoop)SampleResampler.looped(region.sample,position,channel,region.loopStart,region.loopEnd)
            else SampleResampler.channel(region.sample,position,channel)
        return value*region.gain*panGain/100
    }
}
