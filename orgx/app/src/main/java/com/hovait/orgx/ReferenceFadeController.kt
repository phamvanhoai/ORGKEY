package com.hovait.orgx

/** Music.FadeStart measures duration in milliseconds. */
class ReferenceFadeController {
    private var gain = 1.0
    private var step = 0.0
    private var delayFrames = 0L

    @Synchronized fun start(durationMs: Int, delayMs: Int, sampleRate: Int, playing: Boolean) {
        if (durationMs <= 0 || sampleRate <= 0) return
        val frames = (durationMs.toLong() * sampleRate / 1000).coerceAtLeast(1)
        gain = if (playing) 1.0 else 0.0
        step = (if (playing) -1.0 else 1.0) / frames
        // Fade-out starts immediately; only fade-in waits before ramping.
        delayFrames = if (playing) 0 else delayMs.coerceAtLeast(0).toLong() * sampleRate / 1000
    }

    @Synchronized fun cancel() {
        gain = 1.0
        step = 0.0
        delayFrames = 0
    }

    @Synchronized fun advance(): Double {
        if (delayFrames > 0) delayFrames--
        else gain = (gain + step).coerceIn(0.0, 1.0)
        return gain
    }
}