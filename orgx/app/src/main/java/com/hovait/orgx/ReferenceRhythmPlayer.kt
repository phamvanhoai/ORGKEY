package com.hovait.orgx

/** Sample track playback; accompaniment is kept separate for mixer routing. DNC is not applied. */
class ReferenceRhythmPlayer(private val tracks:List<ReferenceDrumTrack>,
    private val drumLength:(Int)->Int,private val drum:(Int,Int,Int)->Double,sampleRate:Int,
    private val accompaniment:Boolean=false,private val mapNote:(Int)->Int={it},private val envelope:ImportedEnvelope?=null,
    private val sampled:SampledInstrument?=null,private val channelGain:(Int)->Double={1.0},private var fx:ImportedFxChain?=null,
    private val initiallyMuted:Boolean=false,private val initialChannel:ImportedChannelSettings?=null,
    private val expressionEffects:List<ImportedExpressionEffect> = emptyList(),private val initialPedal:Boolean=false) {
    private var volumeShake=expressionEffects.firstOrNull{it.slot==3}
    private fun resetExpression(){keyTouch=100;keyBalance=0;pitchMove=1000;volumeShake=expressionEffects.firstOrNull{it.slot==3};pedalDown=initialPedal}
    constructor(tracks:List<ReferenceDrumTrack>,bank:SampleBank,sampleRate:Int):
        this(tracks,bank::drumLength,bank::drum,sampleRate)
    private val clock=ReferenceStyleClock(sampleRate)
    private val sampleRateForEnvelope=sampleRate
    private var current=-1
    private var timeline:ReferenceDrumTimeline?=null
    private var loopOrigin=0L
    private var initialTime=0L
    private data class Voice(val note:Int,val gain:Double,var age:Int=0,var release:Int=-1,var pendingRelease:Boolean=false,val envelope:ImportedEnvelope.State?=null,val playback:SampledInstrument.Playback?=null,val panLeft:Double=1.0,val panRight:Double=1.0,val eventId:Int=0,var touchGain:Double=1.0)
    private val voices=LinkedHashMap<Long,Voice>()
    private var voiceSequence=0L
    private var eventMuted=initiallyMuted
    private var pedalDown=initialPedal
    private var pitchMove=1000
    private var keyBalance=0
    private var keyTouch=100
    private var channelVolume=initialChannel?.volume ?: 100
    private var channelPan=initialChannel?.pan ?: 0
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
    fun withGroupFx(settings:ImportedChannelSettings):ReferenceRhythmPlayer=also{groupFxSettings=settings;groupFx=ImportedFxChain(settings)}
    /** Move the live rhythm bus only when its DSP configuration is unchanged. */
    fun continueGroupFxFrom(previous:ReferenceRhythmPlayer){
        if(previous===this || groupFxSettings!=previous.groupFxSettings)return
        val live=previous.groupFx ?: return
        // A section can be selected again later; leave it an unallocated fresh bus.
        previous.groupFx=live.fresh()
        groupFx?.close();groupFx=live
    }
    fun withRhythmSettings(settings:ImportedChannelSettings,music:ImportedChannelSettings=ImportedChannelSettings()):ReferenceRhythmPlayer=also{
        rhythmSettings=settings;musicSettings=music
        children.forEach{it.withRhythmSettings(settings,music)}
    }
    /** Called once per audio frame after the drum/chord mixer, not once per output channel. */
    fun mixOutput(drumGain:Float,chordGain:Float){
        // SET balance/volume is latched in Voice.gain before channel FX, at OpenKey.
        val l=mixedLeft(drumGain,chordGain);val r=mixedRight(drumGain,chordGain)
        val processor=groupFx
        if(processor==null){outputLeft=l;outputRight=r}else{processor.process(l,r);outputLeft=processor.left;outputRight=processor.right}
    }
    companion object {
        fun combine(players:List<ReferenceRhythmPlayer>):ReferenceRhythmPlayer {
            require(players.isNotEmpty())
            return ReferenceRhythmPlayer(emptyList(),{0},{_,_,_->0.0},48000).also{it.children=players.toList()}
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
    fun close(){fx?.close();groupFx?.close();children.forEach{it.close()};releaseTails.forEach{it.close()};releaseTails.clear()}
    fun reset(){resetExpression();clock.reset();initialTime=0;timeline=null;current=-1;voices.clear();eventMuted=initiallyMuted;pedalDown=initialPedal;channelVolume=initialChannel?.volume ?: 100;channelPan=initialChannel?.pan ?: 0;releaseTails.forEach{it.close()};releaseTails.clear();left=0.0;right=0.0;loopOrigin=0;finished=false;fx?.reset();groupFx?.reset();outputLeft=0.0;outputRight=0.0;children.forEach{it.reset()}}
    fun startAt(referenceTime:Long,preserveGroupFx:Boolean=false,preserveVoices:Boolean=false){
        require(referenceTime>=0)
        if(preserveVoices){
            // GroupPlayStart calls Track.Start; same-group restart does not CloseAll.
            clock.reset();initialTime=referenceTime;timeline=null;current=-1;loopOrigin=0;finished=false
            left=0.0;right=0.0;outputLeft=0.0;outputRight=0.0
            if(!preserveGroupFx)groupFx?.reset()
            children.forEach{it.startAt(referenceTime,preserveVoices=true)}
            return
        }
        val live=if(preserveGroupFx)groupFx else null
        if(preserveGroupFx)groupFx=null
        reset();if(live!=null)groupFx=live
        initialTime=referenceTime;children.forEach{it.startAt(referenceTime)}
    }
    fun next(selection:Int,tempo:Int) {
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
        val duration=track.end-track.start
        var local=clock.time-loopOrigin+track.start
        if(track.bounded && duration>0 && local>=track.end){
            if(!track.loop){voices.clear();left=0.0;right=0.0;finished=true;return}
            loopOrigin+=(local-track.start)/duration*duration
            local=clock.time-loopOrigin+track.start
            timeline?.reset()
            // Closing channels starts their release; it does not erase the sample/FX state.
            voices.values.forEach{if(it.release<0)it.release=0}
        }
        timeline?.advance(local){event->
            if(event.type==51 && event.note>=0){
                keyTouch=event.note and 65535
                voices.values.filter{event.id==0 || it.eventId==event.id}.forEach{it.touchGain=keyTouch/100.0}
                return@advance
            }
            if(event.type==52 && event.note in -100..100){
                keyBalance=event.note
                voices.values.filter{event.id==0 || it.eventId==event.id}.forEach{it.playback?.channelBalance(keyBalance)}
                return@advance
            }
            if(event.type==53 && event.note>0){
                pitchMove=event.note
                val semitones=12*kotlin.math.log2(pitchMove/1000.0)
                voices.values.filter{event.id==0 || it.eventId==event.id}.forEach{it.playback?.pitchBend(semitones)}
                return@advance
            }
            if(ImportedExpressionAutomation.supported(event)){
                val initial=volumeShake ?: ImportedExpressionEffect(3,false,0,false,false,0,0,0,0,emptyList())
                volumeShake=ImportedExpressionAutomation.update(initial,event)
                voices.values.forEach{it.playback?.configureVolumeShake(checkNotNull(volumeShake))}
                return@advance
            }
            if(event.type==13){eventMuted=true;releaseAll();return@advance}
            if(event.type==14){eventMuted=false;return@advance}
            if(event.type==12){pedalDown=true;return@advance}
            if(event.type==11){pedalDown=false;voices.values.forEach{if(it.pendingRelease && it.release<0){it.pendingRelease=false;it.release=0}};return@advance}
            if(event.type==44){channelVolume=event.note;return@advance}
            if(event.type==45){channelPan=event.note;return@advance}
            val note=if(event.type==2)mapNote(event.note) else event.note
            if(event.type==2 && !eventMuted && drumLength(note)>0){
                if(voices.size>=128)voices.remove(voices.keys.first())
                val gain=ImportedNoteTouch.velocity(event.touch)*ImportedMusicParameters.rhythmGain(musicSettings,rhythmSettings,accompaniment)
                voices[voiceSequence++]=Voice(note,gain,envelope=envelope?.voice(sampleRateForEnvelope),playback=sampled?.voice(note,event.tune,ImportedNoteTouch.pan(event.touch))?.also{playback->volumeShake?.let{playback.configureVolumeShake(it)};expressionEffects.firstOrNull{it.slot==2 && it.enabled}?.let{playback.configurePitchShake(it)};playback.channelBalance(keyBalance);playback.pitchBend(12*kotlin.math.log2(pitchMove/1000.0))},
                    panLeft=ImportedNoteTouch.gain(event.touch,0),panRight=ImportedNoteTouch.gain(event.touch,1),eventId=event.id,touchGain=keyTouch/100.0)
            } else if(event.type==1)voices.values.forEach{if(it.eventId==event.id && it.release<0){if(pedalDown)it.pendingRelease=true else it.release=0}}
        }
        renderVoices(track.outputGain)
        if(!track.bounded && timeline?.exhausted==true && (timeline?.endsWithNoteOff==true || voices.isEmpty()))finished=true
        clock.advance(1,tempo)
    }
    private fun renderVoices(outputGain:Double){
        left=0.0;right=0.0
        val iterator=voices.values.iterator()
        while(iterator.hasNext()){
            val voice=iterator.next()
            if(voice.playback!=null){
                voice.playback.next(voice.release>=0,pedalDown)
                left+=voice.playback.left*voice.gain*voice.touchGain*currentChannelGain(0);right+=voice.playback.right*voice.gain*voice.touchGain*currentChannelGain(1)
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
        left*=outputGain;right*=outputGain
        fx?.let{it.process(left,right);left=it.left;right=it.right}
    }
}
