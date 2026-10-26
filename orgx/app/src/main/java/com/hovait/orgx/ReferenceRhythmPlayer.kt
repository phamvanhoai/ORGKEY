package com.hovait.orgx

import java.io.File

/** Sample track playback; accompaniment is kept separate for mixer routing. */
class ReferenceRhythmPlayer(private val tracks:List<ReferenceDrumTrack>,
    private val drumLength:(Int)->Int,private val drum:(Int,Int,Int)->Double,sampleRate:Int,
    private val accompaniment:Boolean=false,private val mapNote:(Int)->Int={it},private val envelope:ImportedEnvelope?=null,
    private val sampled:SampledInstrument?=null,private val channelGain:(Int)->Double={1.0},private var fx:ImportedFxChain?=null,
    private val initiallyMuted:Boolean=false,private val initialChannel:ImportedChannelSettings?=null,
    private val expressionEffects:List<ImportedExpressionEffect> = emptyList(),private val initialPedal:Boolean=false,
    private val expressionAllowed:Boolean=true,private val keyConnectionMethod:Int=0,private val pedalMode:Int=-1,
    private val storedTranspose:Int=initialChannel?.transpose ?: 0,private val storedOctave:Int=initialChannel?.octave ?: 0,
    private var fadeController:ReferenceFadeController=ReferenceFadeController(),
    private val stsSnapshotResolver:(Int)->ImportedSts?={null},private val stsChanges:List<Boolean> = ImportedStsPlan.defaultChanges,
    private val fixedMode:ImportedSts?=null,
    private val embeddedSetFile:File?=null) {
    private var liveMode=fixedMode
    private val modeChangeMask=List(21){it in 13..19}
    private fun applyLiveMode(){
        val mode=liveMode ?: return
        applySts(0,mode,modeChangeMask)
        currentChannel=currentChannel.copy(tune=mode.settings.tune)
        fx?.reset()
    }
    init{require(keyConnectionMethod in 0..1);require(stsChanges.size==21)}
    private var externalFade = false
    internal val storedEndTime:Long get()=maxOf(children.maxOfOrNull{it.storedEndTime} ?: 0L,
        tracks.maxOfOrNull{it.events.lastOrNull()?.let{event->event.time+event.length-1} ?: 0L} ?: 0L).coerceAtLeast(0)
    private var tempLoopCommand:(Int)->Unit={}
    private var jumpCommand:(Int)->Unit={}
    private var rhythmCommand:(Int)->Unit={}
    private var tempoCommand:(Int,Int)->Unit={_,_->}
    fun withTempoCommand(command:(Int,Int)->Unit):ReferenceRhythmPlayer=also{
        tempoCommand=command;children.forEach{it.withTempoCommand(command)}
    }
    fun withRhythmCommand(command:(Int)->Unit):ReferenceRhythmPlayer=also{
        rhythmCommand=command;children.forEach{it.withRhythmCommand(command)}
    }
    private var muteCommand:(Boolean)->Unit={}
    fun withMuteCommand(command:(Boolean)->Unit):ReferenceRhythmPlayer=also{
        muteCommand=command;children.forEach{it.withMuteCommand(command)}
    }
    fun withJumpCommand(command:(Int)->Unit):ReferenceRhythmPlayer=also{
        jumpCommand=command;children.forEach{it.withJumpCommand(command)}
    }
    fun withTempLoopCommand(command:(Int)->Unit):ReferenceRhythmPlayer=also{
        tempLoopCommand=command;children.forEach{it.withTempLoopCommand(command)}
    }
    private var musicState=ImportedMusicState()
    private var externalMusicState=false
    internal fun storedChord(mapper:ReferenceChordTransposer):Int?=musicState.chordMask?.let(mapper::find)
    internal fun storedChordOctave(fallback:Int)=if(musicState.chordMask!=null)musicState.chordOctave else fallback
    fun withMusicState(state:ImportedMusicState):ReferenceRhythmPlayer=also{
        if(musicState===state && externalMusicState)return@also
        musicState=state;externalMusicState=true;sampled?.withMusicState(state)
        children.forEach{it.withMusicState(state)}
    }
    private var musicReleaseProvider:(()->Int)?=null
    fun withMusicRelease(provider:()->Int):ReferenceRhythmPlayer=also{
        if(musicReleaseProvider===provider)return@also
        musicReleaseProvider=provider
        sampled?.withMusicRelease(provider)
        children.forEach{it.withMusicRelease(provider)}
    }
    fun withMusicFade(controller:ReferenceFadeController):ReferenceRhythmPlayer=also{
        if(externalFade && fadeController===controller)return@also
        fadeController=controller;externalFade=true
        children.forEach{it.withMusicFade(controller)}
        releaseTails.forEach{it.withMusicFade(controller)}
    }
    private var volumeShake=expressionEffects.firstOrNull{it.slot==3}
    private var pitchShakeEffect=expressionEffects.firstOrNull{it.slot==2}
    private var keyRepeatEffect=expressionEffects.firstOrNull{it.slot==0}
    private var keyConnectionEffect=expressionEffects.firstOrNull{it.slot==1}
    private fun resetExpression(){pitchScriptLevel=null;openedKeyIds.fill(null);transposeDelta=0;octaveDelta=0;modulationLevel=null;keyRepeatEffect=expressionEffects.firstOrNull{it.slot==0};keyConnectionEffect=expressionEffects.firstOrNull{it.slot==1};pitchShakeEffect=expressionEffects.firstOrNull{it.slot==2};keyTouch=100;keyBalance=0;pitchMove=1000;volumeShake=expressionEffects.firstOrNull{it.slot==3};pedalDown=initialPedal}
    constructor(tracks:List<ReferenceDrumTrack>,bank:SampleBank,sampleRate:Int):
        this(tracks,bank::drumLength,bank::drum,sampleRate)
    private val clock=ReferenceStyleClock(sampleRate)
    private val sampleRateForEnvelope=sampleRate
    private var current=-1
    private var embeddedSampled:SampledInstrument?=null
    private val embeddedCache=HashMap<Pair<Long,Int>,SampledInstrument>()
    private var timeline:ReferenceDrumTimeline?=null
    private var loopOrigin=0L
    private var initialTime=0L
    private data class Voice(var note:Int,val gain:Double,var age:Int=0,var release:Int=-1,var pendingRelease:Boolean=false,val envelope:ImportedEnvelope.State?=null,val playback:SampledInstrument.Playback?=null,val panLeft:Double=1.0,val panRight:Double=1.0,var eventId:Int=0,var touchGain:Double=1.0,val tune:Int=0,val samplePan:Int=0,val repeat:ImportedKeyRepeat?=null,var sourceNote:Int=note,var sourceLength:Long=0,var sourceTouch:Int=100,var sourceAftertouch:Boolean=false)
    private var chordTarget:(()->Int?)?=null
    private var lastChordTarget:Int?=null
    private var chordControl:()->Int={0}
    private var chordOctaveMove:()->Int={0}
    private var lastChordControl=0
    private var lastChordOctaveMove=0
    fun withChordFollowing(control:()->Int={0},octaveMove:()->Int={0},target:(()->Int?)?)=also{
        chordTarget=target;chordControl=control;chordOctaveMove=octaveMove;lastChordTarget=null
    }
    /** Tracks.GroupPlayChord reopens only held melodic notes of length >=1000. */
    private fun refreshChord(){
        val target=chordTarget?.invoke() ?: return
        val control=chordControl();val octave=chordOctaveMove()
        if(lastChordTarget==target && lastChordControl==control && lastChordOctaveMove==octave)return
        lastChordTarget=target;lastChordControl=control;lastChordOctaveMove=octave
        if(!accompaniment)return
        if(target<0){
            voices.values.forEach{voice->if(voice.release<0){voice.pendingRelease=false;voice.release=0;voice.playback?.forceRelease()}}
            return
        }
        voices.entries.forEach{entry->
            val voice=entry.value
            if(voice.release>=0 || voice.pendingRelease || voice.sourceLength<1000)return@forEach
            val note=mappedNote(voice.sourceNote)
            if(note==voice.note)return@forEach
            if(note !in 0..127){voice.playback?.cut();voice.release=0;return@forEach}
            val playback=sampled?.voice(note,voice.tune,voice.samplePan,scriptVelocity=voice.sourceTouch and 65535,
                initialScriptPedal=pedalDown,scriptAllowed=false,scriptId=voice.eventId,initialAftertouch=voice.sourceAftertouch,releasePercent=currentChannel.releasePercent)?.also{
                it.channelBalance(keyBalance);it.pitchBend(12*kotlin.math.log2(pitchMove/1000.0),pitchScriptLevel,notifyScript=pitchScriptLevel!=null)
                if(expressionAllowed){volumeShake?.let(it::configureVolumeShake);pitchShakeEffect?.let(it::configurePitchShake)}
            }
            voice.playback?.cut()
            entry.setValue(voice.copy(note=note,age=0,playback=playback,envelope=envelope?.voice(sampleRateForEnvelope)))
        }
    }
    private val voices=LinkedHashMap<Long,Voice>()
    internal val activeVoices:Int get()=voices.size+children.sumOf{it.activeVoices}
    private var voiceSequence=0L
    private var eventMuted=initiallyMuted
    private var performanceMuted=false
    private var frameStart:()->Unit={}
    private var trackGate:(Boolean)->Boolean={true}
    internal fun withFrameStart(action:()->Unit)=also{frameStart=action}
    internal fun withTrackGate(allowed:(Boolean)->Boolean)=also{trackGate=allowed}
    fun setPerformanceMute(chord:Boolean,drum:Boolean){
        children.forEach{it.setPerformanceMute(chord,drum)}
        performanceMuted=if(accompaniment)chord else drum
    }
    private var pedalDown=initialPedal
    private var tailPedalMode:Int?=null
    private var modulationLevel:Int?=null
    private var pitchScriptLevel:Int?=null
    private val openedKeyIds=arrayOfNulls<Int>(129)
    private var transposeDelta=0
    private var octaveDelta=0
    private fun mappedNote(note:Int):Int {
        val mapped=mapNote(note)
        return if(!accompaniment || chordTarget?.invoke()?.let{it<0}==true)mapped else
            (mapped.toLong()+transposeDelta+octaveDelta.toLong()*12+musicState.settings.transpose-musicSettings.transpose)
                .coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt()
    }
    private var pitchMove=1000
    private var keyBalance=0
    private var keyTouch=100
    private var channelVolume=initialChannel?.volume ?: 100
    private var channelPan=initialChannel?.pan ?: 0
    private var currentChannel=initialChannel ?: ImportedChannelSettings()
    private fun resetStsChannel(){
        val settings=initialChannel ?: ImportedChannelSettings()
        if(settings.fxEnabled!=currentChannel.fxEnabled || settings.modules!=currentChannel.modules){
            fx?.close();fx=ImportedFxChain(settings,sampleRateForEnvelope)
        }
        currentChannel=settings
    }
    private fun applySts(slot:Int,supplied:ImportedSts?=null,mask:List<Boolean> = stsChanges){
        val snapshot=supplied ?: stsSnapshotResolver(slot) ?: return
        val live=currentChannel.copy(volume=channelVolume,pan=channelPan,
            transpose=(initialChannel?.transpose ?: storedTranspose)+transposeDelta,
            octave=(initialChannel?.octave ?: storedOctave)+octaveDelta)
        val settings=ImportedStsChanges.channel(live,snapshot.settings,mask)
        if(settings.fxEnabled!=currentChannel.fxEnabled || settings.modules!=currentChannel.modules){
            fx?.close();fx=ImportedFxChain(settings,sampleRateForEnvelope)
        }
        currentChannel=settings
        channelVolume=settings.volume;channelPan=settings.pan
        transposeDelta=settings.transpose-(initialChannel?.transpose ?: storedTranspose)
        octaveDelta=settings.octave-(initialChannel?.octave ?: storedOctave)
        if(mask[19]){
            val effects=ImportedExpressionAutomation.applyLegacyKeyConnectionMethod(snapshot.effects,snapshot.sourceVersion ?: "1.4.6",keyConnectionMethod)
            fun effect(slot:Int)=effects.firstOrNull{it.slot==slot} ?: ImportedExpressionEffect(slot,false,0,false,false,0,0,0,0,emptyList())
            keyRepeatEffect=effect(0);keyConnectionEffect=effect(1);pitchShakeEffect=effect(2);volumeShake=effect(3)
            if(expressionAllowed)voices.values.forEach{voice->
                voice.repeat?.configure(checkNotNull(keyRepeatEffect))
                voice.playback?.let{it.configureConnection(checkNotNull(keyConnectionEffect));it.configurePitchShake(checkNotNull(pitchShakeEffect));it.configureVolumeShake(checkNotNull(volumeShake))}
            }
        }
    }
    private fun currentChannelGain(channel:Int)=if(initialChannel==null)channelGain(channel) else
        channelVolume/100.0*minOf(100,if(channel==0)100-channelPan else 100+channelPan)/100.0
    private val releaseFrames=(sampleRate/100).coerceAtLeast(1)
    private var children:List<ReferenceRhythmPlayer> = emptyList()
    private val releaseTails=ArrayList<ReferenceRhythmPlayer>()
    private var tailGain=1.0
    fun releaseVoicesFrom(previous:ReferenceRhythmPlayer){
        if(previous===this)return
        fun collect(source:ReferenceRhythmPlayer){
            source.children.forEach(::collect)
            releaseTails.addAll(source.releaseTails);source.releaseTails.clear()
            if(source.voices.isNotEmpty() || source.fx?.active==true){
                val gainL=source.currentChannelGain(0);val gainR=source.currentChannelGain(1)
                val tail=ReferenceRhythmPlayer(emptyList(),source.drumLength,source.drum,source.sampleRateForEnvelope,
                    source.accompaniment,source.mapNote,source.envelope,source.sampled,{if(it==0)gainL else gainR},source.fx)
                source.fx=source.fx?.fresh()
                // Retiring voices retain their channel's pedal/expression state.
                // The destination section has an independent channel state.
                tail.pedalDown=source.pedalDown
                tail.tailPedalMode=source.pedalMode
                tail.volumeShake=source.volumeShake
                tail.tailGain=source.tracks.getOrNull(source.current)?.outputGain ?: 1.0
                source.voices.values.forEach{if(it.release<0)it.release=0}
                source.voices.values.forEach{tail.voices[tail.voiceSequence++]=it};source.voices.clear()
                releaseTails.add(tail)
            }
        }
        collect(previous)
        while(releaseTails.size>32)releaseTails.removeAt(0).close()
    }
    private fun nextReleaseTails(){
        val iterator=releaseTails.iterator()
        while(iterator.hasNext()){
            val tail=iterator.next();tail.renderVoices(tail.tailGain)
            if(tail.voices.isEmpty() && tail.fx?.active!=true){tail.close();iterator.remove()}
        }
    }
    private var groupFx:ImportedFxChain?=null
    private var groupFxSettings:ImportedChannelSettings?=null
    private var rhythmSettings=ImportedChannelSettings()
    private var musicSettings=ImportedChannelSettings()
    var outputLeft=0.0;private set
    var outputRight=0.0;private set
    private var groupFxBaseSettings:ImportedChannelSettings?=null
    private var rhythmFxRevision=0L
    fun withGroupFx(settings:ImportedChannelSettings):ReferenceRhythmPlayer=also{groupFxBaseSettings=settings;groupFxSettings=settings;groupFx=ImportedFxChain(settings)}
    /** Move the live rhythm bus only when its DSP configuration is unchanged. */
    fun continueGroupFxFrom(previous:ReferenceRhythmPlayer){
        if(previous===this || groupFxBaseSettings!=previous.groupFxBaseSettings)return
        val live=previous.groupFx ?: return
        // A section can be selected again later; leave it an unallocated fresh bus.
        previous.groupFx=live.fresh()
        groupFx?.close();groupFx=live;groupFxSettings=previous.groupFxSettings;rhythmFxRevision=previous.rhythmFxRevision
    }
    fun withRhythmSettings(settings:ImportedChannelSettings,music:ImportedChannelSettings=ImportedChannelSettings()):ReferenceRhythmPlayer=also{
        rhythmSettings=settings;musicSettings=music
        if(!externalMusicState)musicState.configure(music)
        children.forEach{it.withRhythmSettings(settings,music)}
    }
    /** Called once per audio frame after the drum/chord mixer, not once per output channel. */
    fun mixOutput(drumGain:Float,chordGain:Float){
        // SET balance/volume is latched in Voice.gain before channel FX, at OpenKey.
        val l=mixedLeft(drumGain,chordGain);val r=mixedRight(drumGain,chordGain);val fadeGain=if(externalFade)1.0 else fadeController.advance()
        val settings=groupFxBaseSettings
        val enabled=musicState.rhythmFxEnabled ?: settings?.fxEnabled
        if(settings!=null && (enabled!=groupFxSettings?.fxEnabled || rhythmFxRevision!=musicState.rhythmFxRevision)){
            val updated=settings.copy(fxEnabled=enabled==true)
            groupFx?.close();groupFx=ImportedFxChain(updated,sampleRateForEnvelope);groupFxSettings=updated
            rhythmFxRevision=musicState.rhythmFxRevision
        }
        val processor=groupFx
        if(processor==null){outputLeft=l*fadeGain;outputRight=r*fadeGain}else{processor.process(l*fadeGain,r*fadeGain);outputLeft=processor.left;outputRight=processor.right}
    }
    companion object {
        fun combine(players:List<ReferenceRhythmPlayer>):ReferenceRhythmPlayer {
            require(players.isNotEmpty())
            return ReferenceRhythmPlayer(emptyList(),{0},{_,_,_->0.0},48000).also{parent->
                val fade=ReferenceFadeController();parent.fadeController=fade
                players.forEach{it.fadeController=fade};parent.children=players.toList()
                parent.musicState=players.first().musicState
                players.forEach{it.withMusicState(parent.musicState)}
            }
        }
    }
    var left=0.0;private set
    var right=0.0;private set
    var finished=false;private set
    val hasTail:Boolean get()=voices.isNotEmpty() || fx?.active==true || groupFx?.active==true ||
        children.any{it.hasTail} || releaseTails.any{it.hasTail}
    fun releaseAll(){children.forEach{it.releaseAll()};voices.values.forEach{if(it.release<0)it.release=0}}
    fun renderTail(){
        nextReleaseTails()
        if(children.isNotEmpty()){
            children.forEach{it.renderTail()};left=children.sumOf{it.left};right=children.sumOf{it.right}
        }else renderVoices(tracks.getOrNull(current)?.outputGain ?: tailGain)
    }
    fun mixedLeft(drumGain:Float,chordGain:Float):Double =
        (if(children.isNotEmpty())children.sumOf{it.mixedLeft(drumGain,chordGain)}
        else left*(if(accompaniment)chordGain else drumGain))+releaseTails.sumOf{it.mixedLeft(drumGain,chordGain)}
    fun mixedRight(drumGain:Float,chordGain:Float):Double =
        (if(children.isNotEmpty())children.sumOf{it.mixedRight(drumGain,chordGain)}
        else right*(if(accompaniment)chordGain else drumGain))+releaseTails.sumOf{it.mixedRight(drumGain,chordGain)}
    private fun discardVoices(){voices.values.forEach{it.playback?.cut()};voices.clear()}
    fun close(){discardVoices();embeddedSampled=null;embeddedCache.clear();left=0.0;right=0.0;outputLeft=0.0;outputRight=0.0;fx?.close();groupFx?.close();children.forEach{it.close()};releaseTails.forEach{it.close()};releaseTails.clear()}
    fun reset(){liveMode=fixedMode;resetStsChannel();resetExpression();clock.reset();initialTime=0;timeline=null;current=-1;discardVoices();embeddedSampled=null;embeddedCache.clear();eventMuted=initiallyMuted;pedalDown=initialPedal;channelVolume=initialChannel?.volume ?: 100;channelPan=initialChannel?.pan ?: 0;releaseTails.forEach{it.close()};releaseTails.clear();left=0.0;right=0.0;loopOrigin=0;finished=false;fx?.reset();groupFx?.reset();outputLeft=0.0;outputRight=0.0;children.forEach{it.reset()}}
    /** Track.Start rewinds events, closes old notes in release, and retains channel controls. */
    fun restartAt(referenceTime:Long){
        require(referenceTime>=0)
        clock.reset();initialTime=referenceTime;timeline=null;current=-1;loopOrigin=0;finished=false
        left=0.0;right=0.0;outputLeft=0.0;outputRight=0.0
        voices.values.forEach{if(it.release<0){it.pendingRelease=false;it.release=0}}
        fx?.resetRealtime()
        children.forEach{it.restartAt(referenceTime)}
    }
    fun startAt(referenceTime:Long,preserveGroupFx:Boolean=false,preserveVoices:Boolean=false){
        require(referenceTime>=0)
        if(preserveVoices){
            restartAt(referenceTime)
            if(!preserveGroupFx)groupFx?.reset()
            return
        }
        val live=if(preserveGroupFx)groupFx else null
        if(preserveGroupFx)groupFx=null
        reset();if(live!=null)groupFx=live
        initialTime=referenceTime;children.forEach{it.startAt(referenceTime)}
    }
    fun next(selection:Int,tempo:Int) {
        if(finished){renderTail();return}
        frameStart()
        nextReleaseTails()
        if(children.isNotEmpty()){
            left=0.0;right=0.0
            children.forEach{it.next(selection,tempo);left+=it.left;right+=it.right}
            finished=children.all{it.finished}
            return
        }
        if(tracks.isEmpty()){left=0.0;right=0.0;return}
        val selected=selection.coerceIn(tracks.indices)
        if(current!=selected){
            val offset=initialTime
            if(current>=0)reset()
            clock.seek(offset);current=selected;timeline=tracks[selected].timeline()
        }
        val track=tracks[selected]
        refreshChord()
        val duration=track.end-track.start
        var local=clock.time-loopOrigin+track.start
        if(track.bounded && duration>0 && local>=track.end){
            if(!track.loop){
                releaseAll();finished=true
                // GroupPlayNext closes retiring channels; their release and FX still render.
                renderVoices(track.outputGain)
                return
            }
            loopOrigin+=(local-track.start)/duration*duration
            local=clock.time-loopOrigin+track.start
            timeline?.reset()
            fx?.resetRealtime()
            // Closing channels starts their release; it does not erase the sample/FX state.
            voices.values.forEach{if(it.release<0)it.release=0}
        }
        // Tracks skips Track.PlayNext for a performance-muted group. Keep the
        // event cursor in place while the musical/audio clocks keep advancing.
        // On unmute, Timeline dispatches overdue controls and still-live notes,
        // skipping notes whose entire stored duration has already elapsed.
        val trackAllowed=trackGate(eventMuted)
        if(trackAllowed && !performanceMuted && !(accompaniment && lastChordTarget?.let{it<0}==true))timeline?.advance(local){event->
            if(event.type==51 && event.note>=0){
                keyTouch=event.note and 65535
                voices.values.filter{event.id==0 || it.eventId==event.id}.forEach{it.touchGain=keyTouch/100.0}
                // Continue through the normal note-open path: KeyData 101 carries
                // its own key/touch/length and opens this embedded section now.
            }
            if(event.type==52 && event.note in -100..100){
                keyBalance=event.note
                voices.values.filter{event.id==0 || it.eventId==event.id}.forEach{it.playback?.channelBalance(keyBalance)}
                return@advance
            }
            if(event.type==53 && event.note>0){
                pitchMove=event.note
                pitchScriptLevel=(event.touch-8192).coerceIn(-8192,8191)
                sampled?.updateScriptPitchBend(checkNotNull(pitchScriptLevel))
                val semitones=12*kotlin.math.log2(pitchMove/1000.0)
                voices.values.filter{event.id==0 || it.eventId==event.id}.forEach{it.playback?.pitchBend(semitones,pitchScriptLevel)}
                return@advance
            }
            if(event.type==43){
                pitchScriptLevel=null
                // Track.Play: round(KeyToPitch(Key/1000 + 12, 500)).
                pitchMove=Math.round(1000.0*Math.pow(2.0,event.note/12000.0)).toInt()
                val semitones=12*kotlin.math.log2(pitchMove/1000.0)
                voices.values.forEach{it.playback?.pitchBend(semitones,notifyScript=false)}
                return@advance
            }
            if(ImportedExpressionAutomation.supported(event)){
                if(event.id==0){
                    val initial=keyRepeatEffect ?: ImportedExpressionEffect(0,false,0,false,false,0,0,0,0,emptyList())
                    val updated=ImportedExpressionAutomation.update(initial,event)
                    keyRepeatEffect=updated
                    if(expressionAllowed)voices.values.forEach{it.repeat?.configure(updated)}
                    return@advance
                }
                if(event.id==1){
                    val initial=keyConnectionEffect ?: ImportedExpressionEffect(1,false,0,false,false,0,0,0,0,emptyList())
                    val updated=ImportedExpressionAutomation.update(initial,event,keyConnectionMethod)
                    keyConnectionEffect=updated
                    if(expressionAllowed)voices.values.forEach{it.playback?.configureConnection(updated)}
                    return@advance
                }
                if(event.id==2){
                    val initial=pitchShakeEffect ?: ImportedExpressionEffect(2,false,0,false,false,0,0,0,0,emptyList())
                    val updated=ImportedExpressionAutomation.update(initial,event)
                    pitchShakeEffect=updated
                    if(expressionAllowed)voices.values.forEach{it.playback?.configurePitchShake(updated)}
                    return@advance
                }
                val initial=volumeShake ?: ImportedExpressionEffect(3,false,0,false,false,0,0,0,0,emptyList())
                volumeShake=ImportedExpressionAutomation.update(initial,event)
                if(expressionAllowed)voices.values.forEach{it.playback?.configureVolumeShake(checkNotNull(volumeShake))}
                return@advance
            }
            if(event.type==13){eventMuted=true;releaseAll();return@advance}
            if(event.type==3){releaseAll();return@advance}
            if(event.type==64){
                applySts(event.note)
                return@advance
            }
            if(event.type==61 && event.note==0 && liveMode!=null){
                if(event.id!=0)applyLiveMode()
                return@advance
            }
            if(event.type==66){
                liveMode?.let{mode->
                    val module=mode.settings.modules.getOrNull(event.id)
                    if(module!=null && ImportedTrackFxAutomation.supported(module,event)){
                        val updated=mode.copy(settings=mode.settings.copy(modules=mode.settings.modules.toMutableList().also{
                            it[event.id]=ImportedTrackFxAutomation.apply(module,event)
                        }))
                        liveMode=updated
                        applyLiveMode()
                    }
                    return@advance
                }
                if(event.id in currentChannel.modules.indices){
                    val modules=currentChannel.modules.toMutableList()
                    val module=modules[event.id]
                    if(!ImportedTrackFxAutomation.supported(module,event))return@advance
                    modules[event.id]=ImportedTrackFxAutomation.apply(module,event)
                    val settings=currentChannel.copy(modules=modules)
                    val retained=if(event.note==0)false else when(module.type){
                        15->fx?.updatePitchShift(event.id,modules[event.id]) ?: false
                        20->fx?.updateBass(event.id,modules[event.id]) ?: false
                        4,6->fx?.updateEcho(event.id,modules[event.id]) ?: false
                        1,7,8,9,10,16,17,21,23,29,31->fx?.updateFilterOrModulation(event.id,modules[event.id]) ?: false
                        else->false
                    }
                    if(!retained){
                        fx?.close();fx=ImportedFxChain(settings,sampleRateForEnvelope)
                    }
                    currentChannel=settings
                }
                return@advance
            }
            if(event.type==211 || event.type==212){
                if(event.type==212) fadeController.cancel()
                else fadeController.start(event.note,event.touch,sampleRateForEnvelope,playing=true)
                return@advance
            }
            if(event.type==231){musicState.keyMove(event.note);return@advance}
            if(event.type==232){musicState.octaveMove(event.note);return@advance}
            if(event.type in 241..246){musicState.fxEvent(event.type);return@advance}
            if(event.type==202){
                musicState.chord(event.note,event.tune)
                return@advance
            }
            if(event.type==101){
                val section=event.embeddedSection
                val file=embeddedSetFile
                if(section!=null && file!=null)runCatching{
                    val key=section.wave.offset to section.wave.bytes
                    embeddedSampled=embeddedCache.getOrPut(key){
                        val pcm=ImportedSectionPcmLoader.load(file,section)
                        val end=section.loopEnd.coerceIn(1,pcm.frames.size)
                        val start=section.loopStart.coerceIn(0,(end-1).coerceAtLeast(0))
                        val region=InstrumentRegion(section.note,pcm,start,end,section.volume/100.0,section.pan,section.loop>0,section.tune)
                        SampledInstrument(listOf(region),sampleRateForEnvelope)
                    }
                }
                // Fall through so the event's own key/touch opens the embedded voice.
            }
            if(event.type==204){tempLoopCommand(event.note);return@advance}
            if(event.type==203){discardVoices();jumpCommand(event.note);return@advance}
            if(event.type==201){discardVoices();rhythmCommand(event.note);return@advance}
            if(event.type==221 || event.type==222){tempoCommand(event.type,event.note);return@advance}
            if(event.type==205 || event.type==206){muteCommand(event.type==205);return@advance}
            if(event.type==5){
                openedKeyIds.getOrNull(event.note+1)?.let{selected->voices.values.filter{it.eventId==selected && it.release<0}
                    .forEach{it.playback?.aftertouch(event.touch/127f)}}
                // Continue into the note-open path.
            }
            if(event.type==14){eventMuted=false;return@advance}
            if(event.type==12){
                pedalDown=true;sampled?.updateScriptPedal(true);voices.values.forEach{it.playback?.scriptPedal(true)}
                return@advance
            }
            if(event.type==11){
                pedalDown=false;sampled?.updateScriptPedal(false)
                voices.values.forEach{it.playback?.scriptPedal(false);if(it.pendingRelease && it.release<0){it.pendingRelease=false;it.release=0}}
                return@advance
            }
            if(event.type==44){channelVolume=event.note;return@advance}
            if(event.type==41){transposeDelta=event.note-storedTranspose;return@advance}
            if(event.type==42){octaveDelta=event.note-storedOctave;return@advance}
            if(event.type==45){channelPan=event.note;return@advance}
            if(event.type==46 || event.type==56){
                modulationLevel=event.note
                sampled?.updateScriptModulation(event.note)
                voices.values.forEach{it.playback?.modulate(event.note)}
                return@advance
            }
            val note=if(event.type in listOf(2,4,101))mappedNote(event.note) else event.note
            if(event.type in listOf(2,4,101) && event.note in 0..127){
                openedKeyIds[0]=event.id;openedKeyIds[event.note+1]=event.id
            }
            if(event.type in listOf(2,4,101) && !eventMuted && !performanceMuted &&
                (sampled?.keyDurationFrames(note,event.type==4) ?: drumLength(note))>0){
                val gain=ImportedNoteTouch.velocity(event.touch)*ImportedMusicParameters.rhythmGain(musicSettings,rhythmSettings,accompaniment)
                val connection=keyConnectionEffect?.takeIf{expressionAllowed && it.enabled}
                val previous=voices.values.lastOrNull{it.playback!=null && !it.playback.finished &&
                    (it.release<0 || connection?.stopByRelease==false)}
                if(connection!=null && connection.mode>15 && previous!=null && (!connection.byPedal || pedalDown)){
                    previous.release=-1;previous.pendingRelease=false
                    previous.note=note;previous.eventId=event.id;previous.sourceNote=event.note;previous.sourceLength=event.length;previous.sourceTouch=event.touch;previous.sourceAftertouch=event.type==4;previous.playback?.connectTo(note,connection)
                    return@advance
                }
                while(voices.size>=ImportedPolyphony.rhythm)voices.remove(voices.keys.first())?.playback?.cut()
                val samplePan=ImportedNoteTouch.pan(event.touch)
                val tune=event.tune+if(accompaniment)currentChannel.tune-(initialChannel?.tune ?: 0) else 0
                val playback=(embeddedSampled ?: sampled)?.voice(note,tune,samplePan,scriptVelocity=event.touch and 65535,
                    initialScriptPedal=pedalDown,scriptId=event.id,initialAftertouch=event.type==4,releasePercent=currentChannel.releasePercent)?.also{playback->
                    if(pedalDown)playback.scriptPedal(true)
                    modulationLevel?.let(playback::modulate)
                    if(connection!=null && connection.mode<=15 && previous!=null && (!connection.byPedal || pedalDown))playback.connectFrom(previous.note,connection)
                    if(expressionAllowed){volumeShake?.let{playback.configureVolumeShake(it)};pitchShakeEffect?.let{playback.configurePitchShake(it)}}
                    playback.channelBalance(keyBalance);playback.pitchBend(12*kotlin.math.log2(pitchMove/1000.0),pitchScriptLevel,notifyScript=pitchScriptLevel!=null)
                }
                voices[voiceSequence++]=Voice(note,gain,envelope=envelope?.voice(sampleRateForEnvelope),playback=playback,
                    panLeft=ImportedNoteTouch.gain(event.touch,0),panRight=ImportedNoteTouch.gain(event.touch,1),eventId=event.id,touchGain=keyTouch/100.0,
                    tune=tune,samplePan=samplePan,repeat=if(playback!=null && expressionAllowed)ImportedKeyRepeat(sampleRateForEnvelope).also{it.configure(keyRepeatEffect)} else null,
                    sourceNote=event.note,sourceLength=event.length,sourceTouch=event.touch,sourceAftertouch=event.type==4)
            } else if(event.type==1)voices.values.forEach{if(it.eventId==event.id && it.release<0){
                if(pedalDown && it.playback==null)it.pendingRelease=true else it.release=0
            }}
        }
        renderVoices(track.outputGain)
        if(!track.bounded && timeline?.exhausted==true && (timeline?.endsWithNoteOff==true || voices.isEmpty()))finished=true
        clock.advance(1,tempo)
    }
    private fun renderVoices(outputGain:Double){
        left=0.0;right=0.0
        val iterator=voices.values.iterator()
        val repeated=ArrayList<Voice>()
        while(iterator.hasNext()){
            val voice=iterator.next()
            if(voice.playback!=null){
                val mode=tailPedalMode ?: pedalMode
                val envelopePedal=if(mode==-1)pedalDown else mode!=0
                voice.playback.next(voice.release>=0,envelopePedal,effectPedal=pedalDown)
                left+=voice.playback.left*voice.gain*voice.touchGain*currentChannelGain(0);right+=voice.playback.right*voice.gain*voice.touchGain*currentChannelGain(1)
                if(voice.repeat?.next(voice.release>=0,pedalDown)==true){
                    val playback=(embeddedSampled ?: sampled)?.voice(voice.note,voice.tune,voice.samplePan,autoRelease=true,effectsAllowed=false,scriptAllowed=false,initialAftertouch=voice.sourceAftertouch,releasePercent=currentChannel.releasePercent)
                    if(playback!=null){playback.channelBalance(keyBalance);playback.pitchBend(12*kotlin.math.log2(pitchMove/1000.0),pitchScriptLevel,notifyScript=pitchScriptLevel!=null);repeated.add(voice.copy(playback=playback,envelope=null,release=-1,pendingRelease=false,repeat=null,sourceLength=0))}
                }
                if(voice.playback.finished)iterator.remove()
                continue
            }
            val gain=voice.gain*voice.touchGain*(voice.envelope?.next(voice.release>=0) ?: if(voice.release<0)1.0 else (1.0-voice.release.toDouble()/releaseFrames).coerceAtLeast(0.0))
            left+=drum(voice.note,voice.age,0)*gain*voice.panLeft*currentChannelGain(0)
            right+=drum(voice.note,voice.age,1)*gain*voice.panRight*currentChannelGain(1)
            voice.age++
            if(voice.release>=0)voice.release++
            if(voice.age>=drumLength(voice.note) || voice.envelope?.finished==true || (voice.envelope==null && voice.release>=releaseFrames))iterator.remove()
        }
        repeated.forEach{
            while(voices.size>=ImportedPolyphony.rhythm)voices.remove(voices.keys.first())?.playback?.cut()
            voices[voiceSequence++]=it
        }
        left*=outputGain;right*=outputGain
        fx?.let{it.process(left,right);left=it.left;right=it.right}
    }
}
