package com.hovait.orgx

import kotlin.math.ceil

object SampleResampler {
    fun at(frames: ShortArray, position: Double): Double {
        if (!position.isFinite() || position < 0 || position >= frames.size) return 0.0
        val index = position.toInt()
        val fraction = position - index
        val next = if (index + 1 < frames.size) frames[index + 1].toDouble() else 0.0
        return (frames[index] * (1 - fraction) + next * fraction) / 32768.0
    }

    fun toRate(sample: SamplePcm, outputRate: Int): ShortArray {
        require(outputRate > 0 && sample.sampleRate > 0)
        if (sample.sampleRate == outputRate) return sample.frames
        val step = sample.sampleRate.toDouble() / outputRate
        val size = ceil(sample.frames.size / step).toInt()
        return ShortArray(size) { (at(sample.frames, it * step) * 32768.0).toInt().coerceIn(-32768, 32767).toShort() }
    }
}
