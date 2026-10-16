package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class StyleSelectionTest {
    @Test fun identitySurvivesReordering() {
        assertEquals(1,StyleSelection.resolve(listOf(300,100,200),100,0))
    }
    @Test fun unavailableIdentityUsesDefaultInsteadOfDifferentStyleAtOldIndex() {
        assertEquals(0,StyleSelection.resolve(listOf(300,100),999,1))
    }
    @Test fun oldFilesKeepValidIndexAndInvalidIndexIsClamped() {
        assertEquals(1,StyleSelection.resolve(listOf(300,100),null,1))
        assertEquals(1,StyleSelection.resolve(listOf(300,100),null,99))
        assertEquals(0,StyleSelection.resolve(emptyList(),null,3))
    }
}
