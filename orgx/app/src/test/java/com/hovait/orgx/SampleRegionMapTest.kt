package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.abs

class SampleRegionMapTest {
    @Test fun allMidiNotesChooseClosestPianoRegion() {
        val roots=listOf(42,49,54,61,66,73,78,85,90)
        repeat(128){note->val root=SampleRegionMap.nearestRoot(note,roots)
            assertEquals(roots.minOf{abs(it-note)},abs(root-note))}
        roots.forEach{assertEquals(it,SampleRegionMap.nearestRoot(it,roots))}
    }
    @Test fun tieUsesLowerRootRegardlessOfOrder() {
        assertEquals(60,SampleRegionMap.nearestRoot(62,listOf(64,60)))
    }
}
