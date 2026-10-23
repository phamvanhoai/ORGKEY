package com.hovait.orgx

/** Audio-thread owned STS parts. Replacements affect future notes, not live tails. */
class ImportedKeyboardParts {
    data class Part(val id:Int,val sound:SampledInstrument,val pedal:Boolean=false,
        val enabled:Boolean=true,val fromKey:Int=0,val toKey:Int=127,val inputGain:Double=1.0,
        val pedalMode:Int=0)
    private data class Note(val part:Int,val key:Int,val sound:SampledInstrument,
        val playback:SampledInstrument.Playback,val velocity:Double,var pedal:Boolean,
        val pedalMode:Int,var released:Boolean=false)
    private var parts:Map<Int,Part> = emptyMap()
    private val notes=ArrayList<Note>()
    private var bend=0.0
    val activeNotes:Int get()=notes.size
    fun replace(values:List<Part>){
        require(values.size<=4 && values.all{it.id in 1..4} && values.map{it.id}.distinct().size==values.size)
        require(values.all{it.inputGain.isFinite() && it.inputGain in 0.0..1.0})
        parts=values.associateBy{it.id}
    }
    fun noteOn(key:Int,velocity:Double){
        require(key in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        if(velocity==0.0){noteOff(key);return}
        parts.values.filter{it.enabled && key>=it.fromKey && key<=it.toKey}.forEach{part->
            if(notes.size>=512)notes.removeAt(0)
            val playback=part.sound.voice(part.sound.keyboardNote(key))
            playback.pitchBend(bend)
            notes.add(Note(part.id,key,part.sound,playback,velocity*part.inputGain,part.pedal,part.pedalMode))
        }
    }
    fun noteOff(key:Int){
        notes.filter{it.key==key && !it.released}.forEach{it.released=true}
    }
    fun pedal(part:Int,down:Boolean){
        parts[part]?.let{parts=parts+(part to it.copy(pedal=down))}
        notes.filter{it.part==part}.forEach{
            it.pedal=down
        }
    }
    fun pedalAll(down:Boolean){
        (parts.keys+notes.map{it.part}).toSet().forEach{pedal(it,down)}
    }
    fun pitchBend(semitones:Double){
        require(semitones.isFinite() && kotlin.math.abs(semitones)<=48)
        bend=semitones;notes.forEach{it.playback.pitchBend(semitones)}
    }
    fun pressure(key:Int,value:Float){
        require(value.isFinite() && value in 0f..1f)
        notes.filter{it.key==key && !it.released}.forEach{it.playback.aftertouch(value)}
    }
    fun channelPressure(value:Float){
        require(value.isFinite() && value in 0f..1f)
        notes.filter{!it.released}.forEach{it.playback.aftertouch(value)}
    }
    /** Caller begins/finishes the shared mixer once, including all other voices. */
    fun renderInto(mixer:ImportedKeyboardMixer){
        val iterator=notes.iterator()
        while(iterator.hasNext()){
            val note=iterator.next()
            val pedalRelease=if(note.pedalMode==-1)note.pedal else note.pedalMode!=0
            note.playback.next(note.released,pedalRelease)
            mixer.add(note.sound,note.playback.left*note.velocity,note.playback.right*note.velocity)
            if(note.playback.finished)iterator.remove()
        }
    }
    fun reset(){notes.clear();bend=0.0}
}
