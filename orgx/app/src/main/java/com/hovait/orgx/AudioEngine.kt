package com.hovait.orgx

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sin

class AudioEngine(private val recordingDirectory: File) {
    private val sampleRate = 48_000
    private val voices = ConcurrentHashMap<Int, Voice>()
    private val running = AtomicBoolean(true)
    @Volatile var masterVolume = 0.65f
    @Volatile var keysVolume = 0.78f
    @Volatile var chordVolume = 0.22f
    @Volatile var drumsVolume = 0.55f
    @Volatile var sustain = false
    @Volatile var rhythmPlaying = false
    @Volatile var bpm = 120
    @Volatile var instrument = 0
    @Volatile var chordIndex = 0
    @Volatile var rhythmStyle = 0
    @Volatile var effectType = 0
    @Volatile var effectMix = 0.28f
    @Volatile var isRecording = false

    private val recordingLock = Any()
    private var recordingData = ByteArrayOutputStream()

    private var transportFrame = 0L
    private var noiseState = 0x13579BDF
    private val effectBuffer = DoubleArray(sampleRate)
    private var effectPosition = 0

    private data class Voice(var phase: Double = 0.0, var released: Boolean = false, var gain: Float = 1f)

    private val track = AudioTrack.Builder()
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
        .build()

    init {
        track.play()
        thread(name = "ORGX-Audio", priority = Thread.MAX_PRIORITY) { renderLoop() }
    }

    fun noteOn(note: Int) { voices[note] = Voice() }
    fun noteOff(note: Int) {
        if (sustain) voices[note]?.released = true else voices.remove(note)
    }
    fun releaseSustain() {
        sustain = false
        voices.entries.removeIf { it.value.released }
    }

    fun setRhythmEnabled(enabled: Boolean) {
        rhythmPlaying = enabled
        if (!enabled) transportFrame = 0L
    }

    fun startRecording() {
        synchronized(recordingLock) {
            recordingData.reset()
            isRecording = true
        }
    }

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
        file
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
        val position = (transportFrame % stepFrames).toDouble() / sampleRate
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

    private fun accompanimentSample(): Double {
        if (!rhythmPlaying) return 0.0
        val chords = arrayOf(
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
        val notes = chords[chordIndex.coerceIn(0, chords.lastIndex)]
        val time = transportFrame.toDouble() / sampleRate
        return notes.sumOf { note ->
            val frequency = 440.0 * 2.0.pow((note - 69) / 12.0)
            sin(2.0 * PI * frequency * time)
        } / notes.size
    }

    private fun processEffect(dry: Double): Double {
        if (effectType == 0) return dry
        val delayFrames = if (effectType == 1) (sampleRate * 0.28).toInt() else (sampleRate * 0.095).toInt()
        val readPosition = (effectPosition - delayFrames + effectBuffer.size) % effectBuffer.size
        val delayed = effectBuffer[readPosition]
        val feedback = if (effectType == 1) 0.38 else 0.67
        effectBuffer[effectPosition] = (dry + delayed * feedback).coerceIn(-2.0, 2.0)
        effectPosition = (effectPosition + 1) % effectBuffer.size
        return dry * (1.0 - effectMix) + delayed * effectMix
    }

    private fun renderLoop() {
        val frames = 256
        val output = ShortArray(frames * 2)
        while (running.get()) {
            for (frame in 0 until frames) {
                var mixed = 0.0
                voices.forEach { (note, voice) ->
                    val frequency = 440.0 * 2.0.pow((note - 69) / 12.0)
                    val sample = when (instrument) {
                        1 -> sin(voice.phase) * 0.58 + sin(voice.phase * 2.0) * 0.28 + sin(voice.phase * 4.0) * 0.14
                        2 -> sin(voice.phase) * 0.55 + sin(voice.phase * 2.0) * 0.25 + sin(voice.phase * 3.0) * 0.12 + sin(voice.phase * 5.0) * 0.08
                        else -> sin(voice.phase) * 0.72 + sin(voice.phase * 2.0) * 0.18 + sin(voice.phase * 3.0) * 0.10
                    }
                    mixed += sample * voice.gain
                    voice.phase = (voice.phase + 2.0 * PI * frequency / sampleRate) % (2.0 * PI)
                }
                val synth = mixed / voices.size.coerceAtLeast(1)
                val rhythm = rhythmSample()
                val accompaniment = accompanimentSample()
                val mixedOutput = synth * keysVolume + accompaniment * chordVolume + rhythm * drumsVolume
                val scaled = (processEffect(mixedOutput) * masterVolume * Short.MAX_VALUE)
                    .coerceIn(Short.MIN_VALUE.toDouble(), Short.MAX_VALUE.toDouble()).toInt().toShort()
                output[frame * 2] = scaled
                output[frame * 2 + 1] = scaled
            }
            capture(output)
            track.write(output, 0, output.size, AudioTrack.WRITE_BLOCKING)
        }
    }

    fun close() {
        running.set(false)
        track.stop()
        track.release()
    }
}
