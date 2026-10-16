package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class ChordSelectionTest {
    @Test fun savedNameSurvivesReordering() {
        assertEquals(1, ChordSelection.resolve(listOf("D m", "C M"), "C M", 0))
    }
    @Test fun legacyMinorSelectionIsMappedByMeaning() {
        assertEquals(0, ChordSelection.resolve(listOf("D m", "C M"), null, 1))
    }
    @Test fun missingNameAndOutOfRangeIndexAreSafe() {
        assertEquals(1, ChordSelection.resolve(listOf("C M", "D m"), "missing", 900))
        assertEquals(0, ChordSelection.resolve(emptyList(), null, 0))
    }
}
