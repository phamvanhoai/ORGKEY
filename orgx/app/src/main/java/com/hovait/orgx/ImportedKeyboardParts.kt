package com.hovait.orgx

/** Audio-thread owned STS parts. Replacements affect future notes, not live tails. */
class ImportedKeyboardParts {
    data class Part(val id:Int,val sound:SampledInstrument,val pedal:Boolean=false,
        val enabled:Boolean=true,val fromKey:Int=0,val toKey:Int=127,val inputGain:Double=1.0,
        val pedalMode:Int=0)
    private data class Note(val part:Int,val channel:Int,var key:Int,val sound:SampledInstrument,
        val playback:SampledInstrument.Playback,val velocity:Double,var pedal:Boolean,
        val pedalMode:Int,var released:Boolean=false)
    private var parts:Map<Int,Part> = emptyMap()
    private val notes=ArrayList<Note>()
    private val bends=DoubleArray(16)
    val activeNotes:Int get()=notes.size
    fun replace(values:List<Part>){
        require(values.size<=4 && values.all{it.id in 1..4} && values.map{it.id}.distinct().size==values.size)
        require(values.all{it.inputGain.isFinite() && it.inputGain in 0.0..1.0})
        parts=values.associateBy{it.id}
    }
    fun noteOn(key:Int,velocity:Double,channel:Int=0){
        require(channel in 0..15 && key in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        if(velocity==0.0){noteOff(key,channel);return}
        parts.values.filter{it.enabled && key>=it.fromKey && key<=it.toKey}.forEach{part->
            val connection=part.sound.keyConnectionEffect
            val previous=notes.lastOrNull{it.part==part.id && it.channel==channel && !it.released}
            if(connection!=null && connection.mode>15 && previous!=null && (!connection.byPedal || part.pedal)){
                previous.key=key
                previous.pedal=part.pedal
                previous.playback.connectTo(part.sound.keyboardNote(key),connection)
                return@forEach
            }
            if(notes.size>=512)notes.removeAt(0)
            val playback=part.sound.voice(part.sound.keyboardNote(key))
            playback.pitchBend(bends[channel])
            notes.add(Note(part.id,channel,key,part.sound,playback,velocity*part.inputGain,part.pedal,part.pedalMode))
        }
    }
    fun noteOff(key:Int,channel:Int=0){
        require(channel in 0..15)
        notes.filter{it.channel==channel && it.key==key && !it.released}.forEach{it.released=true}
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
    fun pitchBend(semitones:Double,channel:Int=0){
        require(channel in 0..15 && semitones.isFinite() && kotlin.math.abs(semitones)<=48)
        bends[channel]=semitones;notes.filter{it.channel==channel}.forEach{it.playback.pitchBend(semitones)}
    }
    fun pressure(key:Int,value:Float,channel:Int=0){
        require(channel in 0..15 && value.isFinite() && value in 0f..1f)
        notes.filter{it.channel==channel && it.key==key && !it.released}.forEach{it.playback.aftertouch(value)}
    }
    fun channelPressure(value:Float,channel:Int=0){
        require(channel in 0..15 && value.isFinite() && value in 0f..1f)
        notes.filter{it.channel==channel && !it.released}.forEach{it.playback.aftertouch(value)}
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
    fun reset(){notes.clear();bends.fill(0.0)}
}
