package com.hovait.orgx

import sofeh.audio.NOpenSL

/** Optional OpenSL backend. It is selected explicitly and falls back by the caller. */
class NOpenSLBackend(
    override val sampleRate: Int,
    override val channels: Int = 2,
    private val bufferFrames: Int = 256,
    private val bufferCount: Int = 4
) : AudioBackend {
    private var started = false
    private var submittedSamples = 0L

    override fun start() {
        check(!started) { "NOpenSL backend already started" }
        require(channels == 2 && sampleRate > 0)
        require(bufferFrames in 64..4096 && bufferCount in 3..8)
        check(NativeReferenceLoader.tryLoad()) { "reference native audio unavailable" }
        check(NOpenSL.Start(null, sampleRate, bufferFrames, bufferCount, false)) { "NOpenSL.Start failed" }
        started = true
        submittedSamples = 0
    }

    override fun write(samples: ShortArray, offset: Int, count: Int): Int {
        check(started) { "NOpenSL backend is not started" }
        require(offset == 0 && count > 0 && count <= samples.size && count % channels == 0) { "NOpenSL requires a contiguous stereo buffer" }
        NOpenSL.Write(samples, count)
        val previousSamples = submittedSamples
        submittedSamples += count
        if(submittedSamples == count.toLong()) android.util.Log.i("ORGX-Audio", "NOpenSL first write completed: $count samples")
        if(previousSamples < sampleRate.toLong()*channels && submittedSamples >= sampleRate.toLong()*channels)
            android.util.Log.i("ORGX-Audio", "NOpenSL streaming confirmed: $submittedSamples samples submitted")
        return count
    }

    override fun stop() {
        if (started) {
            runCatching { NOpenSL.Stop() }
                .onSuccess { android.util.Log.i("ORGX-Audio", "NOpenSL stopped: $submittedSamples samples submitted") }
                .onFailure { android.util.Log.e("ORGX-Audio", "NOpenSL stop failed", it) }
        }
        started = false
    }

    override fun close() = stop()
}
