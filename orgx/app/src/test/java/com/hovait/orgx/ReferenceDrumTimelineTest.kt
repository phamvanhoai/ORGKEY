package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceDrumTimelineTest {
    private fun event(type:Int,time:Long,length:Long)=ReferenceDrumEvent(type,42,36,100,time,length)
    @Test fun dispatchesOpenAndReleaseOnceInStableTimeOrder() {
        val timeline=ReferenceDrumTimeline(listOf(event(1,100,-1),event(2,0,100)))
        val output=mutableListOf<ReferenceDrumEvent>()
        timeline.advance(0,output::add);timeline.advance(0,output::add)
        timeline.advance(99,output::add);assertEquals(listOf(2),output.map{it.type})
        timeline.advance(100,output::add);assertEquals(listOf(2,1),output.map{it.type})
        timeline.reset();timeline.advance(0,output::add);assertEquals(3,output.size)
    }
    @Test fun seekingPastFinishedNoteSkipsOpenButKeepsRelease() {
        val timeline=ReferenceDrumTimeline(listOf(event(2,0,20),event(1,20,-1)))
        val output=mutableListOf<ReferenceDrumEvent>()
        timeline.advance(31,output::add)
        assertEquals(listOf(1),output.map{it.type})
    }
    @Test(expected=IllegalArgumentException::class)
    fun backwardSeekRequiresReset() {
        val timeline=ReferenceDrumTimeline(emptyList())
        timeline.advance(100){};timeline.advance(0){}
    }
}
