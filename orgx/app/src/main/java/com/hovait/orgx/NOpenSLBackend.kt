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

    override fun start() {
        check(!started) { "NOpenSL backend already started" }
        check(NativeReferenceLoader.tryLoad()) { "reference native audio unavailable" }
        check(NOpenSL.Start(null, sampleRate, bufferFrames, bufferCount, false)) { "NOpenSL.Start failed" }
        started = true
    }

    override fun write(samples: ShortArray, offset: Int, count: Int): Int {
        check(started) { "NOpenSL backend is not started" }
        require(offset == 0 && count <= samples.size) { "NOpenSL requires a contiguous buffer" }
        NOpenSL.Write(samples, count)
        return count
    }

    override fun stop() {
        if (started) runCatching { NOpenSL.Stop() }
        started = false
    }

    override fun close() = stop()
}
