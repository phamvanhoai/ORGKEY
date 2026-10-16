package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceStyleEligibilityTest {
    private val events=List(16){ReferenceDrumEvent(2,it,36,100,it*100L,10)}
    private val track=ReferenceDrumTrack("Drum",1,0,32,120,true,0,2000,false,events)
    @Test fun allFourVariationSectionsAreSupported() {
        for(section in 32..35)assertTrue(ReferenceStyleEligibility.playable(track.copy(section=section),setOf(36)))
    }
    @Test fun unsupportedSectionsMissingSoundsAndInvalidLoopsAreExcluded() {
        assertFalse(ReferenceStyleEligibility.playable(track.copy(section=16),setOf(36)))
        assertFalse(ReferenceStyleEligibility.playable(track,emptySet()))
        assertFalse(ReferenceStyleEligibility.playable(track.copy(end=0),setOf(36)))
        assertFalse(ReferenceStyleEligibility.playable(track.copy(muted=true),setOf(36)))
        assertFalse(ReferenceStyleEligibility.playable(track.copy(loop=false),setOf(36)))
    }
}
