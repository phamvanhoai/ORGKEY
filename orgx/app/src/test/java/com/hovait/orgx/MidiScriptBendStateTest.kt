package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiScriptBendStateTest {
    @Test fun rawScriptBendKeepsChannelAndPlaybackIsolation(){
        val state=MidiPitchState()
        state.set(8242,3)
        state.set(8192-75,3,true)
        assertEquals(50,state.scriptLevel(3))
        assertEquals(-75,state.scriptLevel(3,true))
        assertEquals(0,state.scriptLevel(2))
        state.set(20000,4);assertEquals(8191,state.scriptLevel(4))
        state.set(-1,4);assertEquals(-8192,state.scriptLevel(4))
    }
}
