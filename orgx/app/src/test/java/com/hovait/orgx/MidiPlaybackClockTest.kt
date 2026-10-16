package com.hovait.orgx

import org.junit.Assert.assertEquals
import org.junit.Test

class MidiPlaybackClockTest {
    @Test fun tempoChangeWithinNoteGapIsAppliedAtItsTick() {
        val clock=MidiPlaybackClock(MidiSong(emptyList(),480,500000,
            listOf(MidiTempoEvent(480,1000000))))
        assertEquals(1500000L,clock.advance(960))
        assertEquals(0L,clock.advance(960))
    }
    @Test fun tickZeroTempoOverridesDefault() {
        val clock=MidiPlaybackClock(MidiSong(emptyList(),480,250000,listOf(MidiTempoEvent(0,250000))))
        assertEquals(250000L,clock.advance(480))
    }
    @Test fun manyShortIntervalsKeepFractionalTime() {
        val clock=MidiPlaybackClock(MidiSong(emptyList(),480,500000))
        var total=0L
        for(tick in 1L..480L)total+=clock.advance(tick)
        assertEquals(500000L,total)
    }
}
