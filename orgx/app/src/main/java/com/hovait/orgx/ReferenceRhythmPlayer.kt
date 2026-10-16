package com.hovait.orgx

/** Plays the drum track only; accompaniment tracks and DNC scripts are separate. */
class ReferenceRhythmPlayer(private val tracks:List<ReferenceDrumTrack>,
    private val drumLength:(Int)->Int,private val drum:(Int,Int,Int)->Double,sampleRate:Int) {
    constructor(tracks:List<ReferenceDrumTrack>,bank:SampleBank,sampleRate:Int):
        this(tracks,bank::drumLength,bank::drum,sampleRate)
    private val clock=ReferenceStyleClock(sampleRate)
    private var current=-1
    private var timeline:ReferenceDrumTimeline?=null
    private var loopOrigin=0L
    private data class Voice(val note:Int,val gain:Double,var age:Int=0,var release:Int=-1)
    private val voices=LinkedHashMap<Int,Voice>()
    private val releaseFrames=(sampleRate/100).coerceAtLeast(1)
    var left=0.0;private set
    var right=0.0;private set
    fun reset(){clock.reset();timeline=null;current=-1;voices.clear();left=0.0;right=0.0;loopOrigin=0}
    fun next(selection:Int,tempo:Int) {
        if(tracks.isEmpty()){left=0.0;right=0.0;return}
        val selected=selection.coerceIn(tracks.indices)
        if(current!=selected){reset();current=selected;timeline=tracks[selected].timeline()}
        val track=tracks[selected]
        val duration=track.end-track.start
        var local=clock.time-loopOrigin+track.start
        if(duration>0 && local>=track.end){
            loopOrigin+=(local-track.start)/duration*duration
            local=clock.time-loopOrigin+track.start
            timeline?.reset();voices.clear()
        }
        timeline?.advance(local){event->
            if(event.type==2 && drumLength(event.note)>0){
                if(voices.size>=128)voices.remove(voices.keys.first())
                voices[event.id]=Voice(event.note,event.touch.coerceAtLeast(0)/100.0)
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
        clock.advance(1,tempo)
    }
}
