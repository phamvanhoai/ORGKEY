package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceSectionTimingTest {
    @Test fun unspecifiedMeterResolvesAsAPairWithoutChangingStoredSetData(){
        val raw=ImportedStyleGroup(1,100,"Main",32,true,0,6000,120,meterX=0,meterY=0)
        assertEquals(4 to 4,ReferenceSectionTiming.meter(raw))
        assertEquals(3 to 4,ReferenceSectionTiming.meter(raw,3,4))
        assertEquals(3 to 4,ReferenceSectionTiming.meter(raw.copy(meterX=7),3,4))
        assertEquals(7 to 8,ReferenceSectionTiming.meter(raw.copy(meterX=7,meterY=8),3,4))
        assertEquals(1500,ReferenceSectionTiming.scale(raw,2,3,4))
        assertEquals(0,raw.meterX);assertEquals(0,raw.meterY)
    }
    private val group=ImportedStyleGroup(1,100,"Main",32,true,0,8000,120)
    @Test fun referenceModesUseMeterAndLimitRatherThanWholeSection() {
        assertEquals(1000,ReferenceSectionTiming.scale(group,0))
        assertEquals(500,ReferenceSectionTiming.scale(group,1))
        assertEquals(2000,ReferenceSectionTiming.scale(group,2))
        assertEquals(8000,ReferenceSectionTiming.scale(group,3))
        assertEquals(1500,ReferenceSectionTiming.scale(group.copy(end=6000,meterX=3),0))
        assertEquals(10,ReferenceSectionTiming.scale(group.copy(end=875),0))
        assertEquals(-1,ReferenceSectionTiming.scale(group.copy(section=96),0))
    }
    @Test fun startPhaseAndWindowFollowReferenceEquations() {
        assertEquals(7L,ReferenceSectionTiming.startOffset(1007,group,1000,0))
        assertEquals(3007L,ReferenceSectionTiming.startOffset(3007,group,1000,1))
        assertTrue(ReferenceSectionTiming.inWindow(1010,1000,120))
        assertFalse(ReferenceSectionTiming.inWindow(1011,1000,120))
        assertTrue(ReferenceSectionTiming.inWindow(1020,1000,240))
    }
    @Test fun nonzeroStartSeekPreservesEventPhase() {
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,200,1200,false,
            listOf(ReferenceDrumEvent(2,1,60,100,200,1),ReferenceDrumEvent(2,2,61,100,700,1)))
        val notes=mutableListOf<Int>()
        val player=ReferenceRhythmPlayer(listOf(track),{1},{note,_,_->notes.add(note);1.0},1000)
        player.startAt(500);player.next(0,120)
        assertEquals(listOf(61,61),notes)
    }
}
