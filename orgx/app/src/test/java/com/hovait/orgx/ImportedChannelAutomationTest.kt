package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedChannelAutomationTest {
    @Test fun volumeAndPanEventsAffectHeldNoteAndResetRestoresHeader(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100),
            ReferenceDrumEvent(44,0,50,0,1,0),ReferenceDrumEvent(45,0,100,0,2,0))
        val track=ReferenceDrumTrack("Automation",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000,initialChannel=ImportedChannelSettings())
        player.next(0,120);assertEquals(1.0,player.left,0.0)
        player.next(0,120);assertEquals(.5,player.left,0.0);assertEquals(.5,player.right,0.0)
        player.next(0,120);assertEquals(0.0,player.left,0.0);assertEquals(.5,player.right,0.0)
        player.reset();player.next(0,120);assertEquals(1.0,player.left,0.0)
    }
}
