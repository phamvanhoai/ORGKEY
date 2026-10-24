package com.hovait.orgx

/** Audio-thread owned STS parts. Replacements affect future notes, not live tails. */
class ImportedKeyboardParts {
    data class Part(val id:Int,val sound:SampledInstrument,val pedal:Boolean=false,
        val enabled:Boolean=true,val fromKey:Int=0,val toKey:Int=127,val inputGain:Double=1.0,
        val pedalMode:Int=0)
    private data class Note(val part:Int,val channel:Int,var key:Int,val sound:SampledInstrument,
        val playback:SampledInstrument.Playback,val velocity:Double,var pedal:Boolean,
        val pedalMode:Int,var released:Boolean=false,val repeat:ImportedKeyRepeat?=null,var retired:Boolean=false)
    private var parts:Map<Int,Part> = emptyMap()
    private val notes=ArrayList<Note>()
    private val bends=DoubleArray(16)
    private val scriptBends=arrayOfNulls<Int>(16)
    private val modulation=IntArray(16)
    val activeNotes:Int get()=notes.size
    fun setScriptSlider(part:Int,expectedSound:SampledInstrument,name:String,value:Int):Boolean {
        val current=parts[part] ?: return false
        if(current.sound!==expectedSound)return false
        val slider=current.sound.scriptSliders.singleOrNull{it.name==name.lowercase()} ?: return false
        if(value !in slider.from..slider.to)return false
        current.sound.setScriptSlider(name,value)
        return true
    }
    fun replace(values:List<Part>){
        require(values.size<=4 && values.all{it.id in 1..4} && values.map{it.id}.distinct().size==values.size)
        require(values.all{it.inputGain.isFinite() && it.inputGain in 0.0..1.0})
        parts=values.associateBy{it.id}
    }
    /** Retire a keyboard set without erasing PCM/envelope or its mixer FX tails. */
    fun retire(){
        parts=emptyMap()
        notes.forEach{it.released=true;it.retired=true;it.playback.forceRelease()}
    }
    fun noteOn(key:Int,velocity:Double,channel:Int=0){
        require(channel in 0..15 && key in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        if(velocity==0.0){noteOff(key,channel);return}
        parts.values.filter{it.enabled && key>=it.fromKey && key<=it.toKey}.forEach{part->
            val connection=part.sound.keyConnectionEffect
            val previous=notes.lastOrNull{it.part==part.id && it.channel==channel && it.sound===part.sound && !it.retired && !it.playback.finished &&
                (!it.released || connection?.stopByRelease==false)}
            if(connection!=null && connection.mode>15 && previous!=null && (!connection.byPedal || part.pedal)){
                previous.key=key
                previous.released=false
                previous.pedal=part.pedal
                previous.playback.connectTo(part.sound.keyboardNote(key),connection)
                return@forEach
            }
            if(notes.size>=512)notes.removeAt(0)
            val playback=part.sound.voice(part.sound.keyboardNote(key),scriptVelocity=(velocity*127).toInt().coerceAtLeast(1),initialScriptPedal=part.pedal)
            if(connection!=null && connection.mode<=15 && previous!=null && (!connection.byPedal || part.pedal))
                playback.connectFrom(part.sound.keyboardNote(previous.key),connection)
            playback.pitchBend(bends[channel],scriptBends[channel])
            if(part.pedal)playback.scriptPedal(true)
            if(modulation[channel]!=0)playback.modulate(modulation[channel])
            notes.add(Note(part.id,channel,key,part.sound,playback,velocity*part.inputGain,part.pedal,part.pedalMode,
                repeat=ImportedKeyRepeat(part.sound.outputSampleRate)))
        }
    }
    fun noteOff(key:Int,channel:Int=0){
        require(channel in 0..15)
        notes.filter{it.channel==channel && it.key==key && !it.released}.forEach{it.released=true}
    }
    fun pedal(part:Int,down:Boolean){
        parts[part]?.sound?.updateScriptPedal(down)
        parts[part]?.let{parts=parts+(part to it.copy(pedal=down))}
        notes.filter{it.part==part}.forEach{
            it.playback.scriptPedal(down)
            it.pedal=down
        }
    }
    fun pedalAll(down:Boolean){
        (parts.keys+notes.map{it.part}).toSet().forEach{pedal(it,down)}
    }
    fun pitchBend(semitones:Double,channel:Int=0,scriptLevel:Int?=null){
        require(channel in 0..15 && semitones.isFinite() && kotlin.math.abs(semitones)<=48)
        require(scriptLevel==null || scriptLevel in -8192..8191)
        scriptBends[channel]=scriptLevel
        bends[channel]=semitones;notes.filter{it.channel==channel}.forEach{it.playback.pitchBend(semitones,scriptLevel)}
    }
    fun pressure(key:Int,value:Float,channel:Int=0){
        require(channel in 0..15 && value.isFinite() && value in 0f..1f)
        notes.filter{it.channel==channel && it.key==key && !it.released}.forEach{it.playback.aftertouch(value)}
    }
    fun channelPressure(value:Float,channel:Int=0){
        require(channel in 0..15 && value.isFinite() && value in 0f..1f)
        notes.filter{it.channel==channel && !it.released}.forEach{it.playback.aftertouch(value)}
    }
    fun modulate(level:Int,channel:Int=0){
        require(channel in 0..15 && level in -127..127)
        modulation[channel]=level
        parts.values.map{it.sound}.distinct().forEach{it.updateScriptModulation(level)}
        // Channels.CloseAll also reaches channels already in their release envelope.
        // Retired part voices remain here until their PCM/envelope finishes.
        notes.filter{it.channel==channel && !it.playback.finished}.forEach{it.playback.modulate(level)}
    }
    /** Caller begins/finishes the shared mixer once, including all other voices. */
    fun renderInto(mixer:ImportedKeyboardMixer){
        val iterator=notes.iterator()
        val repeated=ArrayList<Note>()
        while(iterator.hasNext()){
            val note=iterator.next()
            val pedalRelease=if(note.pedalMode==-1)note.pedal else note.pedalMode!=0
            note.playback.next(note.released,pedalRelease)
            mixer.add(note.sound,note.playback.left*note.velocity,note.playback.right*note.velocity)
            note.repeat?.configure(note.sound.keyRepeatEffect)
            if(!note.retired && note.repeat?.next(note.released,pedalRelease)==true){
                val playback=note.sound.voice(note.sound.keyboardNote(note.key),autoRelease=true,effectsAllowed=false,scriptAllowed=false)
                playback.pitchBend(bends[note.channel],scriptBends[note.channel])
                repeated.add(Note(note.part,note.channel,note.key,note.sound,playback,note.velocity,note.pedal,note.pedalMode))
            }
            if(note.playback.finished)iterator.remove()
        }
        notes.addAll(repeated)
    }
    fun reset(){notes.clear();bends.fill(0.0);scriptBends.fill(null);modulation.fill(0)}
}
