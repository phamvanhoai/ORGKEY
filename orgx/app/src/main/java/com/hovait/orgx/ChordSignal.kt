package com.hovait.orgx

import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sin

/** Stateless sine accompaniment, matching ORGX's existing synthesis. */
object ChordSignal {
    private val frequencies = DoubleArray(128) { 440.0 * 2.0.pow((it - 69) / 12.0) }

    fun sample(notes: IntArray, frame: Long, sampleRate: Int): Double {
        if (notes.isEmpty()) return 0.0
        val time = frame.toDouble() / sampleRate
        var sum = 0.0
        for (note in notes) sum += sin(2.0 * PI * frequencies[note] * time)
        return sum / notes.size
    }
}
