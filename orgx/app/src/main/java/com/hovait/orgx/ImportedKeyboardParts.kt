package com.hovait.orgx

/** Track.Play(202) fallback: OpenKey directly, with no Touch script dispatch. Audio thread only. */
class ImportedDirectChord {
    private var sound:SampledInstrument?=null
    private var mask=0
    private var pedal=false
    private var pedalMode=0
    private var gain=1.0
    private val voices=ArrayList<SampledInstrument.Playback>()
    fun select(source:SampledInstrument?,pitches:Int,pedal:Boolean=false,pedalMode:Int=0,gain:Double=1.0){
        this.pedal=pedal;this.pedalMode=pedalMode;this.gain=gain
        val next=pitches and 4095
        if(source===sound && next==mask)return
        cut();sound=source;mask=next
        if(source==null)return
        for(pitch in 0..11)if(next and (1 shl pitch)!=0){
            val key=source.keyboardNote(36+pitch)
            if(key in 0..127 && source.durationFrames(key)>0)
                voices.add(source.voice(key,scriptVelocity=100,scriptAllowed=false,scriptId=pitch,initialScriptPedal=pedal))
        }
    }
    fun renderInto(mixer:ImportedKeyboardMixer){
        val source=sound ?: return
        voices.forEach{it.next(false,if(pedalMode==-1)pedal else pedalMode!=0,effectPedal=pedal);mixer.add(source,it.left*gain,it.right*gain)}
    }
    fun cut(){voices.forEach{it.cut()};voices.clear();sound=null;mask=0}
}

/** Audio-thread owned STS parts. Replacements affect future notes, not live tails. */
class ImportedKeyboardParts(private val musicRelease:(()->Int)?=null,private val musicState:ImportedMusicState?=null) {
    data class Part(val id:Int,val sound:SampledInstrument,val pedal:Boolean=false,
        val enabled:Boolean=true,val fromKey:Int=0,val toKey:Int=127,val inputGain:Double=1.0,
        val pedalMode:Int=0,val chordPart:Boolean=false)
    private data class Note(val part:Int,val channel:Int,var key:Int,val sound:SampledInstrument,
        val playback:SampledInstrument.Playback,val velocity:Double,var pedal:Boolean,
        val pedalMode:Int,var released:Boolean=false,val repeat:ImportedKeyRepeat?=null,var retired:Boolean=false)
    private var parts:Map<Int,Part> = emptyMap()
    private val directChords=HashMap<Int,ImportedDirectChord>()
    /** Audio thread only; direct OpenKey keeps each part's own sound, mixer and pedal state. */
    fun renderDirectChord(mixer:ImportedKeyboardMixer,mask:Int){
        if(mask and 4095==0){if(directChords.isNotEmpty())cutDirectChords();return}
        val iterator=directChords.iterator()
        while(iterator.hasNext()){
            val entry=iterator.next()
            if(parts[entry.key]?.chordPart!=true){entry.value.cut();iterator.remove()}
        }
        parts.values.forEach{part->
            if(!part.chordPart)return@forEach
            musicRelease?.let{part.sound.withMusicRelease(it)}
            musicState?.let{part.sound.withMusicState(it)}
            val chord=directChords.getOrPut(part.id){ImportedDirectChord()}
            chord.select(part.sound,if(part.enabled)mask else 0,part.pedal,part.pedalMode,part.inputGain)
            chord.renderInto(mixer)
        }
    }
    private fun cutDirectChords(){directChords.values.forEach{it.cut()};directChords.clear()}
    private val notes=ArrayList<Note>()
    private fun addNote(note:Note){
        while(notes.count{it.part==note.part}>=ImportedPolyphony.music){
            notes.removeAt(notes.indexOfFirst{it.part==note.part}).playback.cut()
        }
        notes.add(note)
    }
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
        cutDirectChords()
        parts=emptyMap()
        notes.forEach{it.released=true;it.retired=true;it.playback.forceRelease()}
    }
    fun noteOn(key:Int,velocity:Double,channel:Int=0){
        require(channel in 0..15 && key in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        if(velocity==0.0){noteOff(key,channel);return}
        parts.values.filter{it.enabled && key>=it.fromKey && key<=it.toKey}.forEach{part->
            musicRelease?.let{part.sound.withMusicRelease(it)}
            musicState?.let{part.sound.withMusicState(it)}
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
            val playback=part.sound.voice(part.sound.keyboardNote(key),scriptVelocity=(velocity*127).toInt().coerceAtLeast(1),initialScriptPedal=part.pedal)
            if(connection!=null && connection.mode<=15 && previous!=null && (!connection.byPedal || part.pedal))
                playback.connectFrom(part.sound.keyboardNote(previous.key),connection)
            playback.pitchBend(bends[channel],scriptBends[channel])
            if(part.pedal)playback.scriptPedal(true)
            if(modulation[channel]!=0)playback.modulate(modulation[channel])
            addNote(Note(part.id,channel,key,part.sound,playback,velocity*part.inputGain,part.pedal,part.pedalMode,
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
            note.playback.next(note.released,pedalRelease,effectPedal=note.pedal)
            mixer.add(note.sound,note.playback.left*note.velocity,note.playback.right*note.velocity)
            note.repeat?.configure(note.sound.keyRepeatEffect)
            if(!note.retired && note.repeat?.next(note.released,note.pedal)==true){
                val playback=note.sound.voice(note.sound.keyboardNote(note.key),autoRelease=true,effectsAllowed=false,scriptAllowed=false)
                playback.pitchBend(bends[note.channel],scriptBends[note.channel])
                repeated.add(Note(note.part,note.channel,note.key,note.sound,playback,note.velocity,note.pedal,note.pedalMode))
            }
            if(note.playback.finished)iterator.remove()
        }
        repeated.forEach(::addNote)
    }
    fun reset(){cutDirectChords();notes.forEach{it.playback.cut()};notes.clear();bends.fill(0.0);scriptBends.fill(null);modulation.fill(0)}
}
