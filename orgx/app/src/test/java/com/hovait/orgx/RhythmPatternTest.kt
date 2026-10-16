package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class RhythmPatternTest {
    @Test fun existingKickAndBackbeatPatternsArePreserved() {
        assertEquals(listOf(0,4),(0..7).filter{RhythmPattern.kick(0,it)})
        assertEquals(listOf(0,3,4,7),(0..7).filter{RhythmPattern.kick(1,it)})
        assertEquals(listOf(0,2,4,6),(0..7).filter{RhythmPattern.kick(2,it)})
        assertEquals(listOf(2,6),(0..7).filter{RhythmPattern.snare(it)})
    }
    @Test fun danceOffbeatHatAndRockSnareKeepTheirLevels() {
        assertEquals(0.18,RhythmPattern.gain(2,2,1),0.0)
        assertEquals(0.10,RhythmPattern.gain(2,2,0),0.0)
        assertEquals(0.40,RhythmPattern.gain(1,1,2),0.0)
        assertEquals(0.0,RhythmPattern.gain(1,1,0),0.0)
    }
}
