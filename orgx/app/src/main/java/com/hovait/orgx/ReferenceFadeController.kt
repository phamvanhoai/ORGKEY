package com.hovait.orgx

/** Music.FadeStart measures duration in milliseconds. */
class ReferenceFadeController {
    private var gain = 1.0
    private var step = 0.0
    private var delayFrames = 0L
    private var stopDelayFrames = 0L
    private var fadingOut = false
    private var stopRequested = false

    @Synchronized fun start(durationMs: Int, delayMs: Int, sampleRate: Int, playing: Boolean) {
        if (durationMs <= 0 || sampleRate <= 0) return
        val frames = (durationMs.toLong() * sampleRate / 1000).coerceAtLeast(1)
        gain = if (playing) 1.0 else 0.0
        step = (if (playing) -1.0 else 1.0) / frames
        fadingOut = playing
        stopRequested = false
        stopDelayFrames = delayMs.coerceAtLeast(0).toLong() * sampleRate / 1000
        // Fade-out starts immediately; only fade-in waits before ramping.
        delayFrames = if (playing) 0 else delayMs.coerceAtLeast(0).toLong() * sampleRate / 1000
    }

    @Synchronized fun cancel() {
        gain = 1.0
        step = 0.0
        delayFrames = 0
        fadingOut = false
        stopRequested = false
        stopDelayFrames = 0
    }

    @Synchronized fun advance(): Double {
        if (fadingOut && gain <= 0.0) {
            if (stopDelayFrames > 0) stopDelayFrames-- else stopRequested = true
        } else if (delayFrames > 0) delayFrames--
        else {
            gain = (gain + step).coerceIn(0.0, 1.0)
            if (fadingOut && gain <= 1e-12) {
                gain = 0.0
                if (stopDelayFrames == 0L) stopRequested = true
            }
        }
        return gain
    }

    @Synchronized fun takeStopRequest(): Boolean = stopRequested.also { stopRequested = false; if (it) fadingOut = false }
}
