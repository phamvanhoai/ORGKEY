package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class PlaybackSessionGateTest {
    @Test fun retiredWorkerCannotWriteOrCompleteReplacement() {
        val gate=PlaybackSessionGate();val old=gate.replace();val next=gate.replace()
        val calls=mutableListOf<String>()
        assertFalse(gate.runIfCurrent(old){calls.add("old note")})
        assertFalse(gate.runIfCurrent(old){calls.add("old completion")})
        assertTrue(gate.runIfCurrent(next){calls.add("new note")})
        assertEquals(listOf("new note"),calls)
    }
    @Test fun stopInvalidatesCurrentSession() {
        val gate=PlaybackSessionGate();val session=gate.replace()
        assertTrue(gate.runIfCurrent(session){})
        gate.replace();assertFalse(gate.runIfCurrent(session){fail("Retired session ran")})
    }
}
