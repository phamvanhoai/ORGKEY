package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class ChordTableParserTest {
    @Test fun sentinelDoesNotAddANote() {
        assertEquals(145, ChordTableParser.parse("M 0 4 7 -1").getValue("M")[0])
    }
    @Test(expected = IllegalStateException::class)
    fun rejectsNonNumericInterval() { ChordTableParser.parse("M 0 typo 7") }
    @Test(expected = IllegalArgumentException::class)
    fun rejectsNegativeInterval() { ChordTableParser.parse("M -2 4 7") }
    @Test(expected = IllegalArgumentException::class)
    fun rejectsOversizedInterval() { ChordTableParser.parse("M 0 12") }
    @Test(expected = IllegalArgumentException::class)
    fun rejectsDuplicateNames() { ChordTableParser.parse("M 0 4 7\nM 0 3 7") }
    @Test(expected = IllegalArgumentException::class)
    fun rejectsSilentChord() { ChordTableParser.parse("M -1") }
}
