package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.*
import java.io.File

class ChordSignalTest {
    @Test fun sampledChordMixesSelectedNotesAndFadesAtBeatBoundaries() {
        val visited = mutableListOf<Int>()
        val voice: (Int, Long) -> Double = { note, age ->
            assertEquals(1000L, age)
            visited.add(note)
            if (note == 48) 0.6 else 0.2
        }
        assertEquals(0.4, ChordSignal.sampled(intArrayOf(48, 52), 1000, 24000, 48000, voice), 1e-9)
        assertEquals(listOf(48, 52), visited)
        val constant: (Int, Long) -> Double = { _, _ -> 1.0 }
        assertEquals(0.0, ChordSignal.sampled(intArrayOf(48), 0, 24000, 48000, constant), 0.0)
        assertEquals(0.0, ChordSignal.sampled(intArrayOf(48), 23999, 24000, 48000, constant), 0.0)
        assertEquals(0.0, ChordSignal.sampled(intArrayOf(48), 24000, 24000, 48000, constant), 0.0)
    }
    @Test fun a440HasCorrectFrequencyAndLevel() {
        val pcm = DoubleArray(48000) { ChordSignal.sample(intArrayOf(69), it.toLong(), 48000) }
        val crossings = (1 until pcm.size).count { pcm[it - 1] <= 0 && pcm[it] > 0 }
        assertEquals(440, crossings)
        assertEquals(sqrt(0.5), sqrt(pcm.sumOf { it * it } / pcm.size), 0.00001)
    }

    @Test fun allReferenceChordsProduceFiniteBoundedNonSilentSignal() {
        val text = File(requireNotNull(System.getProperty("orgx.chordTable"))).readText()
        PlayableChordLibrary.fromTable(text).forEach { chord ->
            var energy = 0.0
            repeat(2048) { frame ->
                val value = ChordSignal.sample(chord.renderNotes, frame.toLong(), 48000)
                assertTrue(chord.name, value.isFinite() && abs(value) <= 1.000001)
                energy += value * value
            }
            assertTrue("Silent chord: ${chord.name}", energy > 1.0)
        }
    }

    @Test fun emptyChordIsSilent() {
        assertEquals(0.0, ChordSignal.sample(intArrayOf(), 123, 48000), 0.0)
    }
}
