package com.hovait.orgx

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.DataOutputStream
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicIntegerArray
import kotlin.concurrent.thread
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin

class AudioEngine(private val recordingDirectory: File,private val sampleBank:SampleBank?=null,preferNativeOutput:Boolean=false,referenceDrumTracks:List<ReferenceDrumTrack> = emptyList()) {
    private val sampleRate = 48_000
    val styleNames=referenceDrumTracks.map{it.displayName}.ifEmpty{listOf("Pop","Rock","Dance")}
    val styleIds=referenceDrumTracks.map{it.group}.ifEmpty{listOf(0,1,2)}
    val styleTempos=referenceDrumTracks.map{it.tempo.coerceIn(40,240)}.ifEmpty{listOf(120,120,120)}
    private val referenceRhythm=sampleBank?.takeIf{referenceDrumTracks.isNotEmpty()}?.let{ReferenceRhythmPlayer(referenceDrumTracks,it,sampleRate)}
    @Volatile var outputStatus: String = "Starting"
        private set
    private val chordVoices: Array<Array<(Int,Long)->Double>> = Array(2){channel->Array(InstrumentCatalog.names.size){index->
        {note,age->importedInstrument?.sample(note,age,channel) ?: sampleBank?.instrument(index,note,age,channel) ?: 0.0}
    }
    }
    private val voices = ConcurrentHashMap<Int, Voice>()
    private val pitchState=MidiPitchState()
    private val outputLoop: AudioOutputLoop
    @Volatile var masterVolume = 0.65f
    @Volatile var keysVolume = 0.78f
    @Volatile var chordVolume = 0.22f
    @Volatile var drumsVolume = 0.55f
    @Volatile var sustain = false
        private set
    @Volatile var rhythmPlaying = false
    @Volatile var bpm = 120
    @Volatile var instrument = 0
    @Volatile var importedInstrument:SampledInstrument?=null
        set(value){if(field!==value)clearStsParts();field=value}
    @Volatile var importedInstrumentName:String?=null
    @Volatile var activeSetFile:File?=null
    @Volatile var activeSetIndex:ImportedSetIndex?=null
        set(value){
            if(field!==value){clearStsParts();setChordInputMode(0);keyboardChordInput.reset()}
            field=value
        }
    @Volatile var activeSetSounds:List<ImportedInstrument> = emptyList()
    @Volatile var importedRhythm:ReferenceRhythmPlayer?=null
    @Volatile var importedStyleSession:ImportedStyleSession?=null
    @Volatile var styleAutoFill=false
    @Volatile var importedRhythmName:String?=null
    @Volatile var chordIndex = 0
    @Volatile private var autoChordMap=AutoChordProgression(emptyList())
    @Volatile var referenceChords: List<PlayableChord> = emptyList()
        set(value){autoChordMap=AutoChordProgression(value);field=value}
    @Volatile var referenceChordTransposer:ReferenceChordTransposer?=null
    private val keyboardChordInput=KeyboardChordInput()
    private val stsParts=ImportedKeyboardPartQueue()
    fun setScriptSlider(sound:SampledInstrument,name:String,value:Int,completed:(Boolean)->Unit={}){
        stsParts.updateScriptSlider(sound,name,value,{importedInstrument===sound},completed)
    }
    private val stsWaiters=StsCommitWaiters()
    @Volatile private var stsPartsEnabled=false
    private val stsGeneration=java.util.concurrent.atomic.AtomicLong()
    @Volatile private var stsMusic:ImportedChannelSettings?=null
    @Volatile var currentStsGroup:ImportedStsBatch.Prepared?=null;private set
    private fun clearStsParts(){
        stsGeneration.incrementAndGet();stsPartsEnabled=false
        currentStsGroup=null
        stsParts.clear{stsMusic=null}
    }
    fun applyStsRequest(prepared:ImportedStsRequest.Prepared,expectedSound:SampledInstrument?):Boolean {
        if(importedInstrument!==expectedSound)return false
        return when(prepared){
            is ImportedStsRequest.Prepared.Single->applyPreparedSts(prepared.value,expectedSound)
            is ImportedStsRequest.Prepared.Group->
                if(currentStsGroup!==prepared.previous)false
                else applyPreparedStsBatch(prepared.value,prepared.previous)
        }
    }
    suspend fun awaitStsRequest(prepared:ImportedStsRequest.Prepared,expectedSound:SampledInstrument?):Boolean {
        if(prepared is ImportedStsRequest.Prepared.Single)return applyStsRequest(prepared,expectedSound)
        prepared as ImportedStsRequest.Prepared.Group
        if(importedInstrument!==expectedSound || currentStsGroup!==prepared.previous)return false
        val result=stsWaiters.register()
        if(result.isCompleted)return false
        if(!applyPreparedStsBatch(prepared.value,prepared.previous,{result.complete(it)},{!result.isCompleted})){
            result.complete(false);return false
        }
        return try{result.await()}finally{if(!result.isCompleted)result.cancel()}
    }
    fun applyPreparedStsBatch(prepared:ImportedStsBatch.Prepared,previous:ImportedStsBatch.Prepared?=null,
        completed:(Boolean)->Unit = {},stillWanted:()->Boolean = {true}):Boolean {
        if(activeSetFile!=prepared.file || activeSetIndex!==prepared.index)return false
        val parts=prepared.keyboardParts()
        if(parts.isEmpty())return false
        val generation=stsGeneration.get()
        stsParts.commit(parts,{accepted->
            if(!accepted && currentStsGroup==null)stsPartsEnabled=false
            completed(accepted)
        }){
            if(!stillWanted() || generation!=stsGeneration.get() || activeSetFile!=prepared.file || activeSetIndex!==prepared.index ||
                currentStsGroup!==previous)false
            else {
                prepared.master?.let{master->
                    master.chord?.let{snapshot->
                        val next=ImportedStsChordSettings.singles(snapshot.chordType,snapshot.chordSingle,chordSingles)
                        keyboardChordInput.configureChordMode(snapshot.chordType,next,referenceChords,chordDualMappings)
                        chordSingles=next;chordInputMode=snapshot.chordType
                    }
                    stsMusic=master.music
                    master.tempo?.let{bpm=it}
                }
                currentStsGroup=prepared
                importedInstrumentName=prepared.selection.master.name.ifBlank{"STS ${prepared.selection.slot+1}"}+" / ${parts.size} PARTS"
                true
            }
        }
        stsPartsEnabled=true
        return true
    }
    @Volatile var chordInputMode=0;private set
    @Volatile var chordDualMappings:Map<Int,Int> = emptyMap();private set
    private var chordSingles=listOf(145,290,548,1096,2192,545,1090,2180,265,532,1060,2084)
    @Synchronized fun setChordInputMode(mode:Int){
        require(mode in 0..2)
        keyboardChordInput.configureChordMode(mode,chordSingles,referenceChords,chordDualMappings)
        chordInputMode=mode
    }
    @Synchronized fun setChordDualMappings(values:Map<Int,Int>){
        require(values.all{(key,value)->ChordDualSettings.valid(key,value)})
        val snapshot=values.toMap()
        keyboardChordInput.configureChordMode(chordInputMode,chordSingles,referenceChords,snapshot)
        chordDualMappings=snapshot
    }
    @Volatile var leftChordEnabled=false;private set
    val detectedKeyboardChord:Int? get()=if(leftChordEnabled)keyboardChordInput.chord else null
    fun setLeftChordEnabled(enabled:Boolean){keyboardChordInput.reset();leftChordEnabled=enabled}
    fun keyboardNoteOn(note:Int,velocity:Float,splitNote:Int){
        if(leftChordEnabled && note<splitNote){
            if(voices.containsKey(MidiVoiceKey.key(note,0,false)))noteOff(note)
            keyboardChordInput.press(note,referenceChords)
        } else noteOn(note,velocity)
    }
    fun keyboardNoteOff(note:Int){
        val chordKey=keyboardChordInput.release(note)
        if(!chordKey || voices.containsKey(MidiVoiceKey.key(note,0,false)))noteOff(note)
    }
    /** Per-key aftertouch from the hardware piano surface; sound switching remains mode-dependent. */
    fun keyboardNotePressure(note:Int,pressure:Float){
        if(stsPartsEnabled)stsParts.pressure(note,pressure.coerceIn(0f,1f))
        val key=MidiVoiceKey.key(note,0,false)
        captureMidi(0xa0,note,(pressure.coerceIn(0f,1f)*127).toInt())
        // The original reopens Aftertouch with KeyTouchs[key]: the initial
        // note velocity. Pressure selects the alternate sound, not its gain.
        voices[key]?.let{if(it.imported!=null)it.sampledPlayback?.aftertouch(pressure)}
    }
    /** MIDI poly/channel pressure hook; applies only to imported SET voices. */
    fun midiNotePressure(note:Int,pressure:Float,channel:Int=0,playback:Boolean=false){
        if(StsMidiRouting.usesParts(stsPartsEnabled,channel,playback))stsParts.pressure(note,pressure.coerceIn(0f,1f),channel)
        val key=MidiVoiceKey.key(note,channel,playback)
        if(!playback)captureMidi(0xa0 or channel,note,(pressure.coerceIn(0f,1f)*127).toInt())
        voices[key]?.let{if(it.imported!=null)it.sampledPlayback?.aftertouch(pressure)}
    }
    private val liveModulation=IntArray(16)
    private val playbackModulation=IntArray(16)
    fun midiModulation(value:Int,channel:Int=0,playback:Boolean=false){
        require(value in 0..127 && channel in 0..15)
        (if(playback)playbackModulation else liveModulation)[channel]=value
        if(StsMidiRouting.usesParts(stsPartsEnabled,channel,playback))stsParts.modulate(value,channel)
        else voices.filterKeys{MidiVoiceKey.isChannel(it,channel,playback)}.values.forEach{it.sampledPlayback?.modulate(value)}
        if(!playback)captureMidi(0xb0 or channel,1,value)
    }
    fun midiChannelPressure(pressure:Float,channel:Int=0,playback:Boolean=false){
        require(channel in 0..15)
        if(StsMidiRouting.usesParts(stsPartsEnabled,channel,playback))stsParts.channelPressure(pressure.coerceIn(0f,1f),channel)
        if(!playback)captureMidi(0xd0 or channel,(pressure.coerceIn(0f,1f)*127).toInt(),0)
        voices.filterKeys{MidiVoiceKey.isChannel(it,channel,playback)}.values.forEach{
            if(it.imported!=null)it.sampledPlayback?.aftertouch(pressure)
        }
    }
    fun midiPitchBend(value:Int,channel:Int=0,playback:Boolean=false){
        pitchState.set(value,channel,playback)
        if(!playback){val bend=value.coerceIn(0,16383);captureMidi(0xe0 or channel,bend and 127,bend shr 7)}
        val semitones=MidiPitchBend.semitones(value)
        if(StsMidiRouting.usesParts(stsPartsEnabled,channel,playback))stsParts.pitchBend(semitones,channel)
        voices.filterKeys{MidiVoiceKey.isChannel(it,channel,playback)}.values.forEach{if(it.imported!=null)it.sampledPlayback?.pitchBend(semitones)}
    }
    fun selectedSetChord():Int? = if(referenceChords.isEmpty())null else if(autoChord)
        autoChordMap.chord(progression,harmonyClock.time) else (detectedKeyboardChord ?: chordIndex).coerceIn(referenceChords.indices)
    @Volatile var autoChord = false
    @Volatile var progression = 0
    @Volatile var rhythmStyle = 0
    @Volatile var effectType = 0
    @Volatile var keyConnectionMethod = 0
    @Volatile var effectMix = 0.28f
    @Volatile var isRecording = false

    /** Apply a decoded SET snapshot without editing the source file. */
    fun applyPreparedSts(prepared:ImportedStsLoader.Prepared,expectedSound:SampledInstrument?):Boolean {
        if(activeSetFile!=prepared.file || activeSetIndex!==prepared.index || importedInstrument!==expectedSound)return false
        val snapshot=prepared.snapshot
        val nextSingles=ImportedStsChordSettings.singles(snapshot.chordType,snapshot.chordSingle,chordSingles)
        clearStsParts()
        keyboardChordInput.configureChordMode(snapshot.chordType,nextSingles,referenceChords,chordDualMappings)
        chordSingles=nextSingles
        chordInputMode=snapshot.chordType
        importedInstrument=prepared.sound
        importedInstrumentName=snapshot.name.ifBlank{prepared.index.instruments[snapshot.instrument].name}
        setSustainEnabled(snapshot.pedal!=0)
        return true
    }

    private val recordingLock = Any()
    private var recordingData = ByteArrayOutputStream()
    private data class MidiEvent(val timeNanos: Long, val status: Int, val note: Int, val velocity: Int)
    private val midiEvents = mutableListOf<MidiEvent>()
    private var midiStartNanos = 0L
    private var midiRecordBpm = 120
    @Volatile private var midiPlaybackThread:Thread?=null
    private val midiSessionGate=PlaybackSessionGate()

    private var transportFrame = 0L
    private val harmonyClock=ReferenceStyleClock(sampleRate)
    @Volatile private var resetHarmony=true
    private var noiseState = 0x13579BDF
    private val stereoEffect = StereoEffect(sampleRate)
    private val outputLimiter=StereoPeakLimiter(sampleRate)
    private val padAges = AtomicIntegerArray(8).apply { for (i in 0 until length()) set(i, -1) }
    private val rhythmAges=IntArray(3){-1}
    private val rhythmGains=DoubleArray(3)

    private data class Voice(
        val note:Int,
        val instrument:Int,
        val playback:Boolean=false,
        val imported:SampledInstrument?=null,
        var phase: Double = 0.0,
        var pendingSustain: Boolean = false,
        var releasing: Boolean = false,
        var gain: Float = 0f,
        var velocity: Float = 1f,
        val recordingVelocity:Int=127,
        var ageFrames: Long = 0
    ) {
        val renderedNote=imported?.keyboardNote(note) ?: note
        val sampledPlayback=imported?.voice(renderedNote,scriptVelocity=recordingVelocity)
    }

    private val track by lazy { AudioTrack.Builder()
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        .setAudioFormat(
            AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setSampleRate(sampleRate)
                .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                .build()
        )
        .setBufferSizeInBytes(4096 * 2)
        .setTransferMode(AudioTrack.MODE_STREAM)
        .setPerformanceMode(AudioTrack.PERFORMANCE_MODE_LOW_LATENCY)
        .build() }

        private val accompanimentChords = arrayOf(
            intArrayOf(48, 52, 55), // C
            intArrayOf(50, 53, 57), // Dm
            intArrayOf(52, 55, 59), // Em
            intArrayOf(41, 45, 48), // F
            intArrayOf(43, 47, 50), // G
            intArrayOf(45, 48, 52), // Am
            intArrayOf(47, 50, 53), // Bdim
            intArrayOf(48, 52, 55, 58), // C7
            intArrayOf(50, 54, 57, 60), // D7
            intArrayOf(52, 56, 59, 62), // E7
            intArrayOf(41, 45, 48, 52), // Fmaj7
            intArrayOf(43, 47, 50, 53)  // G7
        )


    init {
        val backend = if(preferNativeOutput) StartupFallbackBackend(sampleRate,
            preferred={NOpenSLBackend(sampleRate)},
            fallback={AudioTrackBackend(track,sampleRate)},
            onSelected={
                outputStatus=if(it) "NOpenSL (experimental)" else "AudioTrack (fallback)"
                android.util.Log.i("ORGX-Audio", "Backend: $outputStatus")
            })
        else AudioTrackBackend(track,sampleRate)
        outputLoop = AudioOutputLoop(start={backend.start();if(!preferNativeOutput)outputStatus="AudioTrack"},
            render=::renderBlock,write={samples,offset,count->backend.write(samples,offset,count)},release={
                try{backend.close()}finally{stsWaiters.close();importedKeyboardMixer.close();importedStyleSession?.close();pendingStyle.getAndSet(null)?.session?.close()}
            },
            onError = { outputStatus="Audio output error";android.util.Log.e("ORGX-Audio", "Audio output stopped", it) })
    }

    fun noteOn(note: Int, velocity: Float = 1f, voiceInstrument:Int=instrument,channel:Int=0,playback:Boolean=false) {
        if(StsMidiRouting.usesParts(stsPartsEnabled,channel,playback)){
            require(velocity.isFinite())
            val amount=velocity.coerceIn(0f,1f)
            if(amount==0f){noteOff(note,channel,playback);return}
            stsParts.noteOn(note,amount.toDouble(),channel)
            captureMidi(0x90 or channel,note,(amount*127).toInt().coerceAtLeast(1))
            return
        }
        val key=MidiVoiceKey.key(note,channel,playback)
        val music=activeSetIndex?.musicSettings ?: ImportedChannelSettings()
        val setBalance=if(!playback && importedInstrument!=null && !MidiVoiceKey.percussion(channel))ImportedMusicParameters.keyboardGain(music).toFloat() else 1f
        val velocityGain=VoiceVelocity.gain(velocity,playback)*setBalance
        if (voices.size >= 24 && !voices.containsKey(key)) {
            voices.maxByOrNull { it.value.ageFrames }?.key?.let { voices.remove(it) }
        }
        val voice=Voice(note=note,instrument=if(MidiVoiceKey.percussion(channel))-1 else InstrumentCatalog.validIndex(voiceInstrument),playback=playback,imported=if(playback)null else importedInstrument,velocity = velocityGain,recordingVelocity=(velocity.coerceIn(0f,1f)*127).toInt().coerceAtLeast(1))
        voice.sampledPlayback?.pitchBend(pitchState.semitones(channel,playback))
        if(!playback && sustain)voice.sampledPlayback?.scriptPedal(true)
        val modulation=(if(playback)playbackModulation else liveModulation)[channel]
        if(modulation!=0)voice.sampledPlayback?.modulate(modulation)
        voices[key]=voice
        if(!playback && voice.imported==null && !MidiVoiceKey.percussion(channel))
            captureMidi(0xc0 or channel,GmProgramMap.program(voiceInstrument),0)
        captureMidi(0x90 or channel,note,(velocity.coerceIn(0f,1f)*127).toInt().coerceAtLeast(1))
    }
    fun triggerPad(pad: Int) { if (pad in 0..7) padAges.set(pad, 0) }
    fun triggerSoundEffect(effect: SoundEffect) { sampleBank?.trigger(effect.key) }
    fun stopSoundEffects() { sampleBank?.trigger("") }
    fun noteOff(note: Int,channel:Int=0,playback:Boolean=false) {
        if(StsMidiRouting.usesParts(stsPartsEnabled,channel,playback))stsParts.noteOff(note,channel)
        voices[MidiVoiceKey.key(note,channel,playback)]?.let { voice ->
            if (sustain && !playback && !MidiVoiceKey.percussion(channel)) voice.pendingSustain = true else voice.releasing = true
        }
        captureMidi(0x80 or channel,note,0)
    }
    fun setSustainEnabled(enabled:Boolean) {
        if(sustain!=enabled)voices.values.filter{!it.playback}.forEach{it.sampledPlayback?.scriptPedal(enabled)}
        if(stsPartsEnabled)stsParts.pedal(enabled)
        if(sustain!=enabled)MidiRecordingPedal.messages(enabled).forEach{message->
            captureMidi(message[0].toInt() and 255,64,message[2].toInt() and 127)
        }
        sustain=enabled
        if(!enabled)voices.values.forEach { voice -> if (voice.pendingSustain) { voice.pendingSustain = false; voice.releasing = true } }
    }
    fun releaseSustain() = setSustainEnabled(false)

    fun setRhythmEnabled(enabled: Boolean) {
        if(enabled && !rhythmPlaying)restartStyleOnPlay.set(true)
        naturalStyleTail=false
        stoppedStyleReset.invalidate()
        rhythmPlaying = enabled
        if (!enabled) {transportFrame = 0L;resetHarmony=true}
    }
    private data class StyleSelection(val session:ImportedStyleSession?)
    private val pendingStyle=java.util.concurrent.atomic.AtomicReference<StyleSelection?>(null)
    fun selectImportedStyle(session:ImportedStyleSession){pendingStyle.getAndSet(StyleSelection(session))?.session?.close()}
    fun clearImportedStyle(){pendingStyle.getAndSet(StyleSelection(null))?.session?.close()}
    fun requestStyleSection(id:Int){
        val session=importedStyleSession ?: return
        if(!session.available(id))return
        val startNow=!rhythmPlaying
        session.press(id,running=!startNow,autoFill=styleAutoFill,repeatToLoop=styleRepeatToLoop)
        if(startNow)setRhythmEnabled(true)
    }
    @Volatile var styleRepeatToLoop=false

    @Synchronized fun playMidi(song:MidiSong,onComplete:()->Unit){
        stopMidi()
        val session=midiSessionGate.replace()
        val playback=thread(start=false,name="ORGX-MIDI"){
            try {
                val clock=MidiPlaybackClock(song)
                val start=System.nanoTime();var elapsedMicros=0L
                song.events.forEach{event->
                    if(Thread.currentThread().isInterrupted)return@thread
                    elapsedMicros+=clock.advance(event.tick)
                    val deadline=start+elapsedMicros*1000
                    while(true){
                        val remaining=deadline-System.nanoTime()
                        if(remaining<=0)break
                        Thread.sleep(remaining/1_000_000,(remaining%1_000_000).toInt())
                    }
                    if(!midiSessionGate.runIfCurrent(session){
                        when(event.controller){
                            1->midiModulation(event.velocity,event.channel,true)
                            -2->midiPitchBend(event.note or (event.velocity shl 7),event.channel,true)
                            -3->midiNotePressure(event.note,event.velocity/127f,event.channel,true)
                            -4->midiChannelPressure(event.velocity/127f,event.channel,true)
                            else->if(event.on)noteOn(event.note,event.velocity/127f,GmProgramMap.instrument(event.program),event.channel,true)
                                else noteOff(event.note,event.channel,true)
                        }
                    })return@thread
                }
            } catch(_:InterruptedException){} finally {
                midiSessionGate.runIfCurrent(session){
                    playbackModulation.fill(0)
                    voices.values.filter{it.playback}.forEach{it.sampledPlayback?.modulate(0);it.releasing=true};midiPlaybackThread=null;onComplete()
                }
            }
        }
        midiPlaybackThread=playback;playback.start()
    }
    @Synchronized fun stopMidi(){
        val session=midiSessionGate.replace()
        midiPlaybackThread?.interrupt();midiPlaybackThread=null
        midiSessionGate.runIfCurrent(session){playbackModulation.fill(0);voices.values.filter{it.playback}.forEach{it.sampledPlayback?.modulate(0);it.releasing=true}}
    }

    fun startRecording() {
        synchronized(recordingLock) {
            recordingData.reset()
            midiEvents.clear();midiStartNanos=System.nanoTime();midiRecordBpm=bpm
            MidiModulation.recordingStart(liveModulation.copyOf()).forEach{event->
                midiEvents.add(MidiEvent(0,event.status,event.note,event.velocity))
            }
            if(sustain)MidiRecordingPedal.messages(true).forEach{message->
                midiEvents.add(MidiEvent(0,message[0].toInt() and 255,64,127))
            }
            pitchState.recordingMessages().forEach{message->
                midiEvents.add(MidiEvent(0,message[0].toInt() and 255,message[1].toInt() and 127,message[2].toInt() and 127))
            }
            voices.entries.filter{!it.value.playback && !it.value.releasing}.forEach{(key,voice)->
                if(voice.imported==null && !MidiVoiceKey.percussion(key/128))
                    midiEvents.add(MidiEvent(0,0xc0 or (key/128),GmProgramMap.program(voice.instrument),0))
                MidiRecordingStart.noteMessages(voice.note,voice.recordingVelocity,key/128,voice.pendingSustain).forEach{message->
                    midiEvents.add(MidiEvent(0,message[0].toInt() and 255,message[1].toInt() and 127,message[2].toInt() and 127))
                }
            }
            isRecording = true
        }
    }

    private fun captureMidi(status:Int,note:Int,velocity:Int){
        if(!isRecording)return
        synchronized(recordingLock){if(isRecording)midiEvents.add(MidiEvent(System.nanoTime()-midiStartNanos,status,note.coerceIn(0,127),velocity.coerceIn(0,127)))}
    }

    fun recordings(): List<File> = recordingDirectory.listFiles { file ->
        file.isFile && file.extension.equals("wav", true)
    }?.sortedByDescending { it.lastModified() } ?: emptyList()

    fun stopRecording(): File? = synchronized(recordingLock) {
        if (!isRecording) return@synchronized null
        val endedAt=System.nanoTime()-midiStartNanos
        MidiRecordingEnd.messages(midiEvents.map{it.status and 15}).forEach{message->
            midiEvents.add(MidiEvent(endedAt,message[0].toInt() and 255,message[1].toInt() and 127,message[2].toInt() and 127))
        }
        isRecording = false
        val pcm = recordingData.toByteArray()
        if (pcm.isEmpty()) return@synchronized null
        recordingDirectory.mkdirs()
        val file = File(recordingDirectory, "ORGX-${System.currentTimeMillis()}.wav")
        FileOutputStream(file).use { output ->
            val dataSize = pcm.size
            fun writeInt(value: Int) {
                output.write(value and 0xff); output.write(value ushr 8 and 0xff)
                output.write(value ushr 16 and 0xff); output.write(value ushr 24 and 0xff)
            }
            fun writeShort(value: Int) { output.write(value and 0xff); output.write(value ushr 8 and 0xff) }
            output.write("RIFF".toByteArray()); writeInt(36 + dataSize); output.write("WAVE".toByteArray())
            output.write("fmt ".toByteArray()); writeInt(16); writeShort(1); writeShort(2)
            writeInt(sampleRate); writeInt(sampleRate * 4); writeShort(4); writeShort(16)
            output.write("data".toByteArray()); writeInt(dataSize); output.write(pcm)
        }
        writeMidi(File(recordingDirectory,file.nameWithoutExtension+".mid"),midiEvents.toList(),midiRecordBpm)
        file
    }

    private fun writeMidi(file:File,events:List<MidiEvent>,tempoBpm:Int){
        val trackData=ByteArrayOutputStream()
        DataOutputStream(trackData).use{track->
            fun variable(valueInput:Long){var value=valueInput.coerceAtLeast(0);var buffer=value and 0x7f;while(value.shr(7).also{value=it}>0){buffer=buffer.shl(8) or ((value and 0x7f) or 0x80)};while(true){track.writeByte((buffer and 0xff).toInt());if(buffer and 0x80L!=0L)buffer=buffer.shr(8)else break}}
            variable(0);track.writeByte(0xff);track.writeByte(0x51);track.writeByte(3)
            val microsPerQuarter=60_000_000/tempoBpm.coerceIn(40,240);track.writeByte(microsPerQuarter shr 16);track.writeByte(microsPerQuarter shr 8);track.writeByte(microsPerQuarter)
            var previousTick=0L
            events.forEach{event->val tick=event.timeNanos*tempoBpm.coerceIn(40,240)*480L/60_000_000_000L;variable(tick-previousTick);previousTick=tick;track.write(MidiChannelMessage.bytes(event.status,event.note,event.velocity))}
            variable(0);track.writeByte(0xff);track.writeByte(0x2f);track.writeByte(0)
        }
        DataOutputStream(FileOutputStream(file)).use{output->output.writeBytes("MThd");output.writeInt(6);output.writeShort(0);output.writeShort(1);output.writeShort(480);output.writeBytes("MTrk");output.writeInt(trackData.size());trackData.writeTo(output)}
    }

    private fun capture(output: ShortArray) {
        if (!isRecording) return
        synchronized(recordingLock) {
            if (!isRecording || recordingData.size() >= sampleRate * 4 * 300) return
            output.forEach { sample ->
                val value = sample.toInt()
                recordingData.write(value and 0xff)
                recordingData.write(value ushr 8 and 0xff)
            }
        }
    }

    private val stoppedStyleReset=StoppedStyleReset()
    private val restartStyleOnPlay=java.util.concurrent.atomic.AtomicBoolean(false)
    @Volatile private var naturalStyleTail=false
    private fun rhythmSample(): Double {
        val selected=pendingStyle.getAndSet(null)
        selected?.let{selection->
            naturalStyleTail=false
            val session=selection.session
            if(session!=null && rhythmPlaying)importedStyleSession?.let{session.continueFrom(it)}
            importedStyleSession?.close()
            importedStyleSession=session;importedRhythm=session?.player;importedRhythmName=session?.displayName
            session?.takeTempoChange()?.let{bpm=it}
        }
        if(restartStyleOnPlay.getAndSet(false) && rhythmPlaying){
            importedStyleSession?.preparePlay()
        }
        val resetStopped=stoppedStyleReset.shouldReset(rhythmPlaying,selected!=null)
        if (!rhythmPlaying) {
            if(naturalStyleTail && importedStyleSession?.finished==true){
                importedStyleSession?.renderTail()
                if(importedStyleSession?.hasTail==false){
                    naturalStyleTail=false
                    stoppedStyleReset.invalidate()
                }
                return 0.0
            }
            if(resetStopped){
                rhythmAges.fill(-1);referenceRhythm?.reset()
                val session=importedStyleSession
                if(session!=null){session.reset();importedRhythm=session.player;importedRhythmName=session.displayName}
                else importedRhythm?.reset()
            }
            return 0.0
        }
        importedStyleSession?.let{
            it.next(bpm.coerceIn(40,240));it.takeTempoChange()?.let{tempo->bpm=tempo};importedRhythm=it.player;importedRhythmName=it.displayName
            if(it.finished){naturalStyleTail=true;rhythmPlaying=false;transportFrame=0}else transportFrame++
            return 0.0
        }
        importedRhythm?.let{it.next(0,bpm.coerceIn(40,240));if(it.finished){rhythmPlaying=false;transportFrame=0}else transportFrame++;return 0.0}
        referenceRhythm?.let { it.next(rhythmStyle,bpm.coerceIn(40,240));transportFrame++;return 0.0 }
        val stepFrames = (sampleRate * 60.0 / bpm.coerceIn(40, 240) / 2.0).toLong().coerceAtLeast(1)
        val step = ((transportFrame / stepFrames) % 8).toInt()
        val stepOffset = transportFrame % stepFrames
        if(stepOffset==0L)sampleBank?.triggerMetronome(transportFrame==0L)
        val position = stepOffset.toDouble() / sampleRate
        transportFrame++
        if(sampleBank!=null){
            if(stepOffset==0L)for(pad in 0..2){
                val gain=RhythmPattern.gain(rhythmStyle,pad,step)
                if(gain>0){rhythmAges[pad]=0;rhythmGains[pad]=gain}
            }
            return 0.0
        }

        noiseState = noiseState * 1664525 + 1013904223
        val noise = ((noiseState ushr 8) / 8388607.5) - 1.0
        val kickHit = when (rhythmStyle) {
            1 -> step == 0 || step == 3 || step == 4 || step == 7
            2 -> step % 2 == 0
            else -> step == 0 || step == 4
        }
        val kick = if (kickHit) {
            sin(2.0 * PI * (72.0 - 32.0 * position.coerceAtMost(0.18)) * position) * exp(-position * 24.0)
        } else 0.0
        val snare = if (step == 2 || step == 6) noise * exp(-position * 30.0) else 0.0
        val hatLevel = if (rhythmStyle == 2 && step % 2 == 1) 0.18 else 0.10
        val hat = noise * exp(-position * 75.0)
        return kick * 0.70 + snare * (if (rhythmStyle == 1) 0.40 else 0.32) + hat * hatLevel
    }

    private fun padSample(): Double {
        if(sampleBank!=null)return 0.0
        var result = 0.0
        for (pad in 0..7) {
            val age = padAges.get(pad)
            if (age < 0) continue
            val time = age.toDouble() / sampleRate
            noiseState = noiseState * 1664525 + 1013904223
            val noise = ((noiseState ushr 8) / 8388607.5) - 1.0
            result += when (pad) {
                0 -> sin(2.0 * PI * (85.0 - 52.0 * time.coerceAtMost(0.25)) * time) * exp(-time * 18.0)
                1 -> noise * exp(-time * 22.0) + sin(2.0 * PI * 185.0 * time) * exp(-time * 30.0) * 0.25
                2 -> noise * exp(-time * 90.0) * 0.45
                3 -> noise * exp(-time * 16.0) * (0.65 + 0.35 * sin(2.0 * PI * 28.0 * time))
                4 -> sin(2.0 * PI * 105.0 * time) * exp(-time * 12.0)
                5 -> sin(2.0 * PI * 175.0 * time) * exp(-time * 14.0)
                6 -> noise * exp(-time * 4.5) * 0.55
                else -> sin(2.0 * PI * 520.0 * time) * exp(-time * 25.0) * 0.55
            }
            val duration = if (pad == 6) sampleRate else sampleRate / 2
            if (age >= duration) padAges.set(pad, -1) else padAges.incrementAndGet(pad)
        }
        return result
    }

    private fun accompanimentSample(channel:Int): Double {
        if (!rhythmPlaying || importedRhythm!=null) return 0.0
        val beatFrames = (sampleRate * 60.0 / bpm.coerceIn(40, 240)).toLong().coerceAtLeast(1)
        val activeChord = if (autoChord) AutoChordProgression.builtinChord(progression,harmonyClock.time) else chordIndex
        val library = referenceChords
        val notes = if (!autoChord && library.isNotEmpty()) {
            library[activeChord.coerceIn(0, library.lastIndex)].renderNotes
        } else accompanimentChords[activeChord.coerceIn(0, accompanimentChords.lastIndex)]
        return if (sampleBank != null) {
            ChordSignal.sampled(notes, transportFrame % beatFrames, beatFrames, sampleRate,chordVoices[channel][InstrumentCatalog.validIndex(instrument)])
        } else ChordSignal.sample(notes, transportFrame, sampleRate)
    }

    private val importedKeyboardMixer=ImportedKeyboardMixer()
    private fun renderBlock(output: ShortArray) {
        val frames = output.size / 2
            for (frame in 0 until frames) {
                stsParts.drain()
                importedKeyboardMixer.configureMusic(stsMusic ?: activeSetIndex?.musicSettings ?: ImportedChannelSettings())
                if(resetHarmony || !rhythmPlaying){harmonyClock.reset();resetHarmony=false}
                var mixedLeft = 0.0
                var mixedRight = 0.0
                var midiDrumLeft=0.0;var midiDrumRight=0.0;var melodicCount=0
                importedKeyboardMixer.beginFrame()
                voices.forEach { (note, voice) ->
                    val frequency = 440.0 * 2.0.pow((voice.note - 69) / 12.0)
                    voice.sampledPlayback?.next(voice.releasing,sustain)
                    val sample = voice.sampledPlayback?.left ?: (if(voice.instrument == -1)sampleBank?.drum(voice.note,voice.ageFrames.toInt(),0) else sampleBank?.instrument(voice.instrument,voice.note,voice.ageFrames)) ?: when (voice.instrument) {
                        1 -> sin(voice.phase) * 0.58 + sin(voice.phase * 2.0) * 0.28 + sin(voice.phase * 4.0) * 0.14
                        2 -> sin(voice.phase) * 0.55 + sin(voice.phase * 2.0) * 0.25 + sin(voice.phase * 3.0) * 0.12 + sin(voice.phase * 5.0) * 0.08
                        else -> sin(voice.phase) * 0.72 + sin(voice.phase * 2.0) * 0.18 + sin(voice.phase * 3.0) * 0.10
                    }
                    val sampleRight=voice.sampledPlayback?.right ?: (if(voice.instrument == -1)sampleBank?.drum(voice.note,voice.ageFrames.toInt(),1) else sampleBank?.instrument(voice.instrument,voice.note,voice.ageFrames,1)) ?: sample
                    voice.gain=if(voice.sampledPlayback!=null)1f else VoiceEnvelope.next(voice.gain,voice.releasing,voice.instrument == -1,sampleRate)
                    val naturalDecay = if (voice.instrument == 0 && voice.imported==null) exp(-voice.ageFrames.toDouble() / sampleRate * 0.32) else 1.0
                    val gain=voice.gain.coerceAtLeast(0f)*voice.velocity*naturalDecay
                    if(voice.instrument == -1){
                        midiDrumLeft+=sample*gain;midiDrumRight+=sampleRight*gain
                    } else if(voice.imported!=null){
                        importedKeyboardMixer.add(voice.imported,sample*gain,sampleRight*gain)
                    } else {
                        mixedLeft+=sample*gain;mixedRight+=sampleRight*gain;melodicCount++
                    }
                    voice.phase = (voice.phase + 2.0 * PI * frequency / sampleRate) % (2.0 * PI)
                    voice.ageFrames++
                    // Stateful playback owns completion, including switched Aftertouch
                    // samples and pitch bend. The primary sample's age is no longer authoritative.
                    if (voice.sampledPlayback?.finished==true || (voice.gain <= 0f && voice.releasing) || (voice.sampledPlayback==null && voice.instrument == -1 && voice.ageFrames >= (sampleBank?.drumLength(voice.note) ?: 0))) voices.remove(note, voice)
                }
                stsParts.renderInto(importedKeyboardMixer,drainCommands=false)
                importedKeyboardMixer.finishFrame()
                val rhythm = rhythmSample() + padSample()
                val mixedOutputLeft = VoiceMixer.mix(mixedLeft,melodicCount,midiDrumLeft,keysVolume,drumsVolume) + importedKeyboardMixer.left*keysVolume + accompanimentSample(0) * chordVolume + rhythm * drumsVolume
                val mixedOutputRight = VoiceMixer.mix(mixedRight,melodicCount,midiDrumRight,keysVolume,drumsVolume) + importedKeyboardMixer.right*keysVolume + accompanimentSample(1) * chordVolume + rhythm * drumsVolume
                val sound = sampleBank?.nextStereo() ?: 0L
                var padLeft=0.0;var padRight=0.0
                sampleBank?.let { bank->for(pad in 0..7){
                    val age=padAges.get(pad)
                    if(age>=0){padLeft+=bank.pad(pad,age,0);padRight+=bank.pad(pad,age,1)
                        padAges.compareAndSet(pad,age,if(age+1>=bank.padLength(pad))-1 else age+1)}
                }
                    for(pad in 0..2){
                        val age=rhythmAges[pad]
                        if(age>=0){
                            padLeft+=bank.pad(pad,age,0)*rhythmGains[pad]
                            padRight+=bank.pad(pad,age,1)*rhythmGains[pad]
                            rhythmAges[pad]=if(age+1>=bank.padLength(pad))-1 else age+1
                        }
                    }
                }
                val setPlayer=importedRhythm.takeIf{rhythmPlaying || naturalStyleTail}
                setPlayer?.mixOutput(drumsVolume,chordVolume)
                val styleLeft=setPlayer?.outputLeft ?: (if(rhythmPlaying)(referenceRhythm?.left ?: 0.0)*drumsVolume else 0.0)
                val styleRight=setPlayer?.outputRight ?: (if(rhythmPlaying)(referenceRhythm?.right ?: 0.0)*drumsVolume else 0.0)
                stereoEffect.process(mixedOutputLeft + (StereoSample.left(sound)+padLeft)*drumsVolume+styleLeft,
                    mixedOutputRight + (StereoSample.right(sound)+padRight)*drumsVolume+styleRight,effectType,effectMix)
                val setMasterGain=importedKeyboardMixer.musicVolume
                outputLimiter.process(stereoEffect.left*masterVolume*setMasterGain,stereoEffect.right*masterVolume*setMasterGain)
                fun pcm(value:Double)=(value * Short.MAX_VALUE)
                    .coerceIn(Short.MIN_VALUE.toDouble(),Short.MAX_VALUE.toDouble()).toInt().toShort()
                output[frame * 2] = pcm(outputLimiter.left)
                output[frame * 2 + 1] = pcm(outputLimiter.right)
                if(rhythmPlaying)harmonyClock.advance(1,bpm.coerceIn(40,240))
            }
            capture(output)

    }

    fun close() {
        stsWaiters.close()
        stopMidi()
        outputLoop.close()
        outputStatus="Stopping"
    }
}
