package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class PlayableChordLibraryTest {
    @Test fun majorAndMinorAreTransposedWithoutChangingQuality() {
        val chords = PlayableChordLibrary.fromTable("M 0 4 7\r\nm 0 3 7\r\n")
        assertEquals(24, chords.size)
        assertEquals(listOf(48, 52, 55), chords.first { it.name == "C M" }.notes)
        assertEquals(listOf(50, 53, 57), chords.first { it.name == "D m" }.notes)
        assertEquals(listOf(59, 63, 66), chords.first { it.name == "B M" }.notes)
    }
}
