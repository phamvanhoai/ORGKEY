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

class AudioEngine(private val recordingDirectory: File,private val sampleBank:SampleBank?=null,preferNativeOutput:Boolean=false) {
    private val sampleRate = 48_000
    @Volatile var outputStatus: String = "Starting"
        private set
    private val chordVoices: Array<Array<(Int,Long)->Double>> = Array(2){channel->Array(InstrumentCatalog.names.size){index->
        {note,age->sampleBank?.instrument(index,note,age,channel) ?: 0.0}
    }
    }
    private val voices = ConcurrentHashMap<Int, Voice>()
    private val outputLoop: AudioOutputLoop
    @Volatile var masterVolume = 0.65f
    @Volatile var keysVolume = 0.78f
    @Volatile var chordVolume = 0.22f
    @Volatile var drumsVolume = 0.55f
    @Volatile var sustain = false
    @Volatile var rhythmPlaying = false
    @Volatile var bpm = 120
    @Volatile var instrument = 0
    @Volatile var chordIndex = 0
    @Volatile var referenceChords: List<PlayableChord> = emptyList()
    @Volatile var autoChord = false
    @Volatile var progression = 0
    @Volatile var rhythmStyle = 0
    @Volatile var effectType = 0
    @Volatile var effectMix = 0.28f
    @Volatile var isRecording = false

    private val recordingLock = Any()
    private var recordingData = ByteArrayOutputStream()
    private data class MidiEvent(val timeNanos: Long, val status: Int, val note: Int, val velocity: Int)
    private val midiEvents = mutableListOf<MidiEvent>()
    private var midiStartNanos = 0L
    private var midiRecordBpm = 120
    @Volatile private var midiPlaybackThread:Thread?=null

    private var transportFrame = 0L
    private var noiseState = 0x13579BDF
    private val stereoEffect = StereoEffect(sampleRate)
    private val padAges = AtomicIntegerArray(8).apply { for (i in 0 until length()) set(i, -1) }

    private data class Voice(
        val note:Int,
        val instrument:Int,
        var phase: Double = 0.0,
        var pendingSustain: Boolean = false,
        var releasing: Boolean = false,
        var gain: Float = 0f,
        var velocity: Float = 1f,
        var ageFrames: Long = 0
    )

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
        private val accompanimentProgressions = arrayOf(
            intArrayOf(0, 5, 3, 4),  // C Am F G
            intArrayOf(0, 4, 5, 3),  // C G Am F
            intArrayOf(5, 3, 0, 4)   // Am F C G
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
            render=::renderBlock,write={samples,offset,count->backend.write(samples,offset,count)},release={backend.close()},
            onError = { outputStatus="Audio output error";android.util.Log.e("ORGX-Audio", "Audio output stopped", it) })
    }

    fun noteOn(note: Int, velocity: Float = 1f) {
        if (voices.size >= 24 && !voices.containsKey(note)) {
            voices.maxByOrNull { it.value.ageFrames }?.key?.let { voices.remove(it) }
        }
        voices[note] = Voice(note=note,instrument=InstrumentCatalog.validIndex(instrument),velocity = velocity.coerceIn(0.15f, 1f))
        captureMidi(0x90,note,(velocity.coerceIn(0f,1f)*127).toInt().coerceAtLeast(1))
    }
    fun triggerPad(pad: Int) { if (pad in 0..7) { padAges.set(pad, 0);if(pad==6)sampleBank?.trigger("crash") } }
    fun triggerSoundEffect(effect: SoundEffect) { sampleBank?.trigger(effect.key) }
    fun stopSoundEffects() { sampleBank?.trigger("") }
    fun noteOff(note: Int) {
        voices[note]?.let { voice ->
            if (sustain) voice.pendingSustain = true else voice.releasing = true
        }
        captureMidi(0x80,note,0)
    }
    fun releaseSustain() {
        sustain = false
        voices.values.forEach { voice -> if (voice.pendingSustain) { voice.pendingSustain = false; voice.releasing = true } }
    }

    fun setRhythmEnabled(enabled: Boolean) {
        rhythmPlaying = enabled
        if (!enabled) transportFrame = 0L
    }

    fun playMidi(song:MidiSong,onComplete:()->Unit){
        stopMidi();val playback=thread(start=false,name="ORGX-MIDI"){
            try{var previous=0L;song.events.forEach{event->if(Thread.currentThread().isInterrupted)return@thread;val waitMicros=(event.tick-previous)*song.tempoMicros/song.division;previous=event.tick;if(waitMicros>0)Thread.sleep(waitMicros/1000,(waitMicros%1000*1000).toInt());if(event.on)noteOn(event.note,event.velocity/127f)else noteOff(event.note)}}
            catch(_:InterruptedException){}finally{voices.values.forEach{it.releasing=true};midiPlaybackThread=null;onComplete()}
        };midiPlaybackThread=playback;playback.start()
    }
    fun stopMidi(){midiPlaybackThread?.interrupt();midiPlaybackThread=null}

    fun startRecording() {
        synchronized(recordingLock) {
            recordingData.reset()
            midiEvents.clear();midiStartNanos=System.nanoTime();midiRecordBpm=bpm
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
            events.forEach{event->val tick=event.timeNanos*tempoBpm.coerceIn(40,240)*480L/60_000_000_000L;variable(tick-previousTick);previousTick=tick;track.writeByte(event.status);track.writeByte(event.note);track.writeByte(event.velocity)}
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

    private fun rhythmSample(): Double {
        if (!rhythmPlaying) return 0.0
        val stepFrames = (sampleRate * 60.0 / bpm.coerceIn(40, 240) / 2.0).toLong().coerceAtLeast(1)
        val step = ((transportFrame / stepFrames) % 8).toInt()
        val stepOffset = transportFrame % stepFrames
        if(stepOffset==0L)sampleBank?.triggerMetronome(transportFrame==0L)
        val position = stepOffset.toDouble() / sampleRate
        transportFrame++

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
        if (!rhythmPlaying) return 0.0
        val beatFrames = (sampleRate * 60.0 / bpm.coerceIn(40, 240)).toLong().coerceAtLeast(1)
        val bar = (transportFrame / (beatFrames * 4)).toInt()
        val activeChord = if (autoChord) accompanimentProgressions[progression.coerceIn(0, 2)][bar % 4] else chordIndex
        val library = referenceChords
        val notes = if (!autoChord && library.isNotEmpty()) {
            library[activeChord.coerceIn(0, library.lastIndex)].renderNotes
        } else accompanimentChords[activeChord.coerceIn(0, accompanimentChords.lastIndex)]
        return if (sampleBank != null) {
            ChordSignal.sampled(notes, transportFrame % beatFrames, beatFrames, sampleRate,chordVoices[channel][InstrumentCatalog.validIndex(instrument)])
        } else ChordSignal.sample(notes, transportFrame, sampleRate)
    }

    private fun renderBlock(output: ShortArray) {
        val frames = output.size / 2
            for (frame in 0 until frames) {
                var mixedLeft = 0.0
                var mixedRight = 0.0
                voices.forEach { (note, voice) ->
                    val frequency = 440.0 * 2.0.pow((note - 69) / 12.0)
                    val sample = sampleBank?.instrument(voice.instrument,voice.note,voice.ageFrames) ?: when (voice.instrument) {
                        1 -> sin(voice.phase) * 0.58 + sin(voice.phase * 2.0) * 0.28 + sin(voice.phase * 4.0) * 0.14
                        2 -> sin(voice.phase) * 0.55 + sin(voice.phase * 2.0) * 0.25 + sin(voice.phase * 3.0) * 0.12 + sin(voice.phase * 5.0) * 0.08
                        else -> sin(voice.phase) * 0.72 + sin(voice.phase * 2.0) * 0.18 + sin(voice.phase * 3.0) * 0.10
                    }
                    val sampleRight=sampleBank?.instrument(voice.instrument,voice.note,voice.ageFrames,1) ?: sample
                    if (voice.releasing) voice.gain -= 1f / (sampleRate * 0.12f)
                    else if (voice.gain < 1f) voice.gain = (voice.gain + 1f / (sampleRate * 0.008f)).coerceAtMost(1f)
                    val naturalDecay = if (voice.instrument == 0) exp(-voice.ageFrames.toDouble() / sampleRate * 0.32) else 1.0
                    val gain=voice.gain.coerceAtLeast(0f)*voice.velocity*naturalDecay
                    mixedLeft += sample*gain
                    mixedRight += sampleRight*gain
                    voice.phase = (voice.phase + 2.0 * PI * frequency / sampleRate) % (2.0 * PI)
                    voice.ageFrames++
                    if (voice.gain <= 0f && voice.releasing) voices.remove(note, voice)
                }
                val count=voices.size.coerceAtLeast(1)
                val rhythm = rhythmSample() + padSample()
                val mixedOutputLeft = mixedLeft/count * keysVolume + accompanimentSample(0) * chordVolume + rhythm * drumsVolume
                val mixedOutputRight = mixedRight/count * keysVolume + accompanimentSample(1) * chordVolume + rhythm * drumsVolume
                val sound = sampleBank?.nextStereo() ?: 0L
                stereoEffect.process(mixedOutputLeft + StereoSample.left(sound)*drumsVolume,
                    mixedOutputRight + StereoSample.right(sound)*drumsVolume,effectType,effectMix)
                fun pcm(value:Double)=(value * masterVolume * Short.MAX_VALUE)
                    .coerceIn(Short.MIN_VALUE.toDouble(),Short.MAX_VALUE.toDouble()).toInt().toShort()
                output[frame * 2] = pcm(stereoEffect.left)
                output[frame * 2 + 1] = pcm(stereoEffect.right)
            }
            capture(output)

    }

    fun close() {
        stopMidi()
        outputLoop.close()
        outputStatus="Stopping"
    }
}
