package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedPedalModeTest {
    @Test fun manualReleaseUsesNativeFastOrPedalDuration(){
        fun lifetime(pedal:Boolean):Int {
            val state=ImportedEnvelope(0,0,100,10,false).voice(1000)
            state.next(false)
            var frames=0
            while(!state.finished && frames<2000){state.next(true,pedal);frames++}
            return frames
        }
        assertEquals(100,lifetime(false))
        assertEquals(1000,lifetime(true))
    }
    @Test fun liftingDynamicPedalChangesAnExistingSlowTailToFastRelease(){
        val state=ImportedEnvelope(0,0,100,10,false).voice(1000)
        state.next(false)
        repeat(200){state.next(true,true)}
        assertFalse(state.finished)
        repeat(99){state.next(true,false)}
        assertFalse(state.finished)
        state.next(true,false)
        assertTrue(state.finished)
    }
}
