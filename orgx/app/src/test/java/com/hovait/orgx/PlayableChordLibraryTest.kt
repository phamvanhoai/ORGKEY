package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class PlayableChordLibraryTest {
    @Test fun everyShippedChordPreservesItsPitchClassesForAllRoots() {
        val text = File(requireNotNull(System.getProperty("orgx.chordTable"))).readText()
        val rows = text.lineSequence().filter { it.isNotBlank() }.toList()
        val chords = PlayableChordLibrary.fromTable(text)
        assertEquals(37, rows.size)
        assertEquals(444, chords.size)
        rows.forEachIndexed { quality, row ->
            val fields = row.trim().split(Regex("\\s+"))
            val intervals = fields.drop(1).map(String::toInt).filter { it >= 0 }.toSet()
            repeat(12) { root ->
                val chord = chords[quality * 12 + root]
                assertEquals("${chord.name}: pitch classes", intervals.map { (it + root) % 12 }.toSet(), chord.notes.map { it % 12 }.toSet())
                assertEquals("${chord.name}: number of notes", intervals.size, chord.notes.size)
                org.junit.Assert.assertTrue(chord.notes.all { it in 0..127 })
            }
        }
    }
    @Test fun majorAndMinorAreTransposedWithoutChangingQuality() {
        val chords = PlayableChordLibrary.fromTable("M 0 4 7\r\nm 0 3 7\r\n")
        assertEquals(24, chords.size)
        assertEquals(listOf(48, 52, 55), chords.first { it.name == "C M" }.notes)
        assertEquals(listOf(50, 53, 57), chords.first { it.name == "D m" }.notes)
        assertEquals(listOf(59, 63, 66), chords.first { it.name == "B M" }.notes)
    }
}
