package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedPedalTest {
    @Test fun pedalDefersNoteOffUntilPedalOff(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100),ReferenceDrumEvent(12,0,0,0,1,0),
            ReferenceDrumEvent(1,1,0,0,2,0),ReferenceDrumEvent(11,0,0,0,10,0))
        val track=ReferenceDrumTrack("Pedal",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000)
        player.next(0,120);assertEquals(1.0,player.left,0.0)
        repeat(7){player.next(0,120);assertEquals(1.0,player.left,0.0)}
        player.next(0,120);assertTrue(player.left<1.0)
        repeat(20){player.next(0,120)};assertEquals(0.0,player.left,0.0)
    }
}
