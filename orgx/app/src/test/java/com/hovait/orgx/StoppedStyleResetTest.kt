package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StoppedStyleResetTest {
    @Test fun stoppedFramesResetOnlyOnceAndPlaybackRearmsReset(){
        val gate=StoppedStyleReset()
        assertTrue(gate.shouldReset(false))
        repeat(48000){assertFalse(gate.shouldReset(false))}
        assertFalse(gate.shouldReset(true))
        assertTrue(gate.shouldReset(false))
        assertFalse(gate.shouldReset(false))
    }
    @Test fun selectingAStoppedSessionResetsTheNewSessionOnce(){
        val gate=StoppedStyleReset();assertTrue(gate.shouldReset(false))
        assertTrue(gate.shouldReset(false,true));assertFalse(gate.shouldReset(false))
        assertFalse(gate.shouldReset(true,true));assertTrue(gate.shouldReset(false))
    }
}
