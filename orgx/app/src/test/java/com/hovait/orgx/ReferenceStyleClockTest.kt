package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class ReferenceStyleClockTest {
    @Test fun referenceTimeMatchesObservedTempoFormula() {
        assertEquals(1000L,ReferenceStyleClock(48000).advance(48000,120))
        assertEquals(500L,ReferenceStyleClock(48000).advance(48000,60))
        assertEquals(2000L,ReferenceStyleClock(48000).advance(48000,240))
    }
    @Test fun fractionalFramesDoNotAccumulateTruncationDrift() {
        val clock=ReferenceStyleClock(44100)
        repeat(44100){clock.advance(1,114)}
        assertEquals(950L,clock.time)
    }
    @Test fun tempoChangesPreservePositionAndResetClearsRemainder() {
        val clock=ReferenceStyleClock(48000)
        clock.advance(48000,120);clock.advance(48000,60)
        assertEquals(1500L,clock.time)
        clock.reset();assertEquals(0L,clock.time)
        assertEquals(1000L,clock.advance(48000,120))
    }
}
