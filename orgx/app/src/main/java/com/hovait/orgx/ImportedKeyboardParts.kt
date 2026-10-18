package com.hovait.orgx

/** Audio-thread owned STS parts. Replacements affect future notes, not live tails. */
class ImportedKeyboardParts {
    data class Part(val id:Int,val sound:SampledInstrument,val pedal:Boolean=false)
    private data class Note(val part:Int,val key:Int,val sound:SampledInstrument,
        val playback:SampledInstrument.Playback,val velocity:Double,var pedal:Boolean,
        var released:Boolean=false,var waiting:Boolean=false)
    private var parts:Map<Int,Part> = emptyMap()
    private val notes=ArrayList<Note>()
    val activeNotes:Int get()=notes.size
    fun replace(values:List<Part>){
        require(values.size<=4 && values.all{it.id in 1..4} && values.map{it.id}.distinct().size==values.size)
        parts=values.associateBy{it.id}
    }
    fun noteOn(key:Int,velocity:Double){
        require(key in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        if(velocity==0.0){noteOff(key);return}
        parts.values.forEach{part->
            if(notes.size>=512)notes.removeAt(0)
            notes.add(Note(part.id,key,part.sound,part.sound.voice(part.sound.keyboardNote(key)),velocity,part.pedal))
        }
    }
    fun noteOff(key:Int){
        notes.filter{it.key==key && !it.released}.forEach{
            if(it.pedal)it.waiting=true else it.released=true
        }
    }
    fun pedal(part:Int,down:Boolean){
        parts[part]?.let{parts=parts+(part to it.copy(pedal=down))}
        notes.filter{it.part==part}.forEach{
            it.pedal=down
            if(!down && it.waiting){it.waiting=false;it.released=true}
        }
    }
    /** Caller begins/finishes the shared mixer once, including all other voices. */
    fun renderInto(mixer:ImportedKeyboardMixer){
        val iterator=notes.iterator()
        while(iterator.hasNext()){
            val note=iterator.next()
            note.playback.next(note.released,note.pedal)
            mixer.add(note.sound,note.playback.left*note.velocity,note.playback.right*note.velocity)
            if(note.playback.finished)iterator.remove()
        }
    }
    fun reset(){notes.clear()}
}
