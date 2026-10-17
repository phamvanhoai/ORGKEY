package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedEnvelopeTest {
    @Test fun attackDecayAndSustainUseReferenceDurationUnits() {
        val state=ImportedEnvelope(1,1,50,1,false).voice(1000)
        repeat(50){state.next(false)};assertEquals(.5,state.gain,1e-9)
        repeat(50){state.next(false)};assertEquals(1.0,state.gain,1e-9)
        repeat(50){state.next(false)};assertEquals(.75,state.gain,1e-9)
        repeat(50){state.next(false)};assertEquals(.5,state.gain,1e-9)
        repeat(1000){state.next(false)};assertEquals(.5,state.gain,1e-9)
    }
    @Test fun zeroDecayRetainsPeakAsInReference() {
        val state=ImportedEnvelope(0,0,20,10,false).voice(1000)
        repeat(100){assertEquals(1.0,state.next(false),0.0)}
    }
    @Test fun noteOffReleaseUsesPedalZeroRateAndStartsAtCurrentGain() {
        val state=ImportedEnvelope(1,0,100,10,false).voice(1000)
        repeat(50){state.next(false)}
        assertEquals(.495,state.next(true),1e-9)
        repeat(99){state.next(true)}
        assertTrue(state.finished);assertEquals(0.0,state.gain,0.0)
    }
    @Test fun autoReleaseUsesLongerReleaseWithoutNoteOff() {
        val state=ImportedEnvelope(0,0,100,1,true).voice(1000)
        repeat(50){state.next(false)}
        assertEquals(.5,state.gain,1e-9)
        repeat(50){state.next(false)};assertTrue(state.finished)
    }
    @Test fun releaseDuringAttackDoesNotJumpToPeak() {
        val state=ImportedEnvelope(10,0,100,2,false).voice(1000)
        repeat(10){state.next(false)}
        assertTrue(state.next(true)<.01)
        repeat(20){state.next(true)};assertTrue(state.finished)
    }
    @Test fun importedStylePlayerKeepsSoundReleaseBeyondOldTenMillisecondCutoff() {
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(1,1,60,0,20,0))
        val track=ReferenceDrumTrack("SET",1,0,32,120,true,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{10000},{_,_,_->1.0},1000,
            envelope=ImportedEnvelope(0,0,100,10,false))
        repeat(41){player.next(0,120)}
        assertTrue("Release was cut at fixed 10 ms",player.left>.7)
        repeat(100){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
    }
}
