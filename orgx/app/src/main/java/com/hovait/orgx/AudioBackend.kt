package com.hovait.orgx

/** PCM backend boundary. Native/Oboe can implement this without changing AudioEngine's mixer. */
interface AudioBackend : AutoCloseable {
    val sampleRate:Int
    val channels:Int
    fun start()
    /** Writes interleaved PCM16 samples; returns samples consumed. */
    fun write(samples:ShortArray,offset:Int,count:Int):Int
    fun stop()
}
