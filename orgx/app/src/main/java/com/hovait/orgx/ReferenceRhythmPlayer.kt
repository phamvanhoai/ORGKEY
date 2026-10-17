package com.hovait.orgx

/** Sample track playback; accompaniment is kept separate for mixer routing. DNC is not applied. */
class ReferenceRhythmPlayer(private val tracks:List<ReferenceDrumTrack>,
    private val drumLength:(Int)->Int,private val drum:(Int,Int,Int)->Double,sampleRate:Int,
    private val accompaniment:Boolean=false,private val mapNote:(Int)->Int={it}) {
    constructor(tracks:List<ReferenceDrumTrack>,bank:SampleBank,sampleRate:Int):
        this(tracks,bank::drumLength,bank::drum,sampleRate)
    private val clock=ReferenceStyleClock(sampleRate)
    private var current=-1
    private var timeline:ReferenceDrumTimeline?=null
    private var loopOrigin=0L
    private var initialTime=0L
    private data class Voice(val note:Int,val gain:Double,var age:Int=0,var release:Int=-1)
    private val voices=LinkedHashMap<Int,Voice>()
    private val releaseFrames=(sampleRate/100).coerceAtLeast(1)
    private var children:List<ReferenceRhythmPlayer> = emptyList()
    companion object {
        fun combine(players:List<ReferenceRhythmPlayer>):ReferenceRhythmPlayer {
            require(players.isNotEmpty())
            return ReferenceRhythmPlayer(emptyList(),{0},{_,_,_->0.0},48000).also{it.children=players.toList()}
        }
    }
    var left=0.0;private set
    var right=0.0;private set
    var finished=false;private set
    fun mixedLeft(drumGain:Float,chordGain:Float):Double =
        if(children.isNotEmpty())children.sumOf{it.mixedLeft(drumGain,chordGain)}
        else left*(if(accompaniment)chordGain else drumGain)
    fun mixedRight(drumGain:Float,chordGain:Float):Double =
        if(children.isNotEmpty())children.sumOf{it.mixedRight(drumGain,chordGain)}
        else right*(if(accompaniment)chordGain else drumGain)
    fun reset(){clock.reset();initialTime=0;timeline=null;current=-1;voices.clear();left=0.0;right=0.0;loopOrigin=0;finished=false;children.forEach{it.reset()}}
    fun startAt(referenceTime:Long){require(referenceTime>=0);reset();initialTime=referenceTime;children.forEach{it.startAt(referenceTime)}}
    fun next(selection:Int,tempo:Int) {
        if(children.isNotEmpty()){
            left=0.0;right=0.0
            children.forEach{it.next(selection,tempo);left+=it.left;right+=it.right}
            finished=children.all{it.finished}
            return
        }
        if(tracks.isEmpty()){left=0.0;right=0.0;return}
        val selected=selection.coerceIn(tracks.indices)
        if(current!=selected){val offset=initialTime;reset();clock.seek(offset);current=selected;timeline=tracks[selected].timeline()}
        val track=tracks[selected]
        val duration=track.end-track.start
        var local=clock.time-loopOrigin+track.start
        if(duration>0 && local>=track.end){
            if(!track.loop){voices.clear();left=0.0;right=0.0;finished=true;return}
            loopOrigin+=(local-track.start)/duration*duration
            local=clock.time-loopOrigin+track.start
            timeline?.reset();voices.clear()
        }
        timeline?.advance(local){event->
            val note=if(event.type==2)mapNote(event.note) else event.note
            if(event.type==2 && drumLength(note)>0){
                if(voices.size>=128)voices.remove(voices.keys.first())
                voices[event.id]=Voice(note,event.touch.coerceAtLeast(0)/100.0)
            } else if(event.type==1)voices[event.id]?.release=0
        }
        left=0.0;right=0.0
        val iterator=voices.values.iterator()
        while(iterator.hasNext()){
            val voice=iterator.next()
            val gain=voice.gain*if(voice.release<0)1.0 else (1.0-voice.release.toDouble()/releaseFrames).coerceAtLeast(0.0)
            left+=drum(voice.note,voice.age,0)*gain
            right+=drum(voice.note,voice.age,1)*gain
            voice.age++
            if(voice.release>=0)voice.release++
            if(voice.age>=drumLength(voice.note) || voice.release>=releaseFrames)iterator.remove()
        }
        left*=track.outputGain;right*=track.outputGain
        clock.advance(1,tempo)
    }
}
