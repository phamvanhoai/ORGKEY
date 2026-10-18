package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedNoteTouchTest {
    @Test fun negativePackedTouchKeepsUnsignedVelocity(){
        val touch=(-100 shl 16) or 75
        assertEquals(.75,ImportedNoteTouch.velocity(touch),0.0)
        assertEquals(-100,ImportedNoteTouch.pan(touch))
        assertEquals(1.0,ImportedNoteTouch.gain(touch,0),0.0)
        assertEquals(0.0,ImportedNoteTouch.gain(touch,1),0.0)
    }
    @Test fun timelineUsesPerNotePanAndVelocityWithoutChangingOtherNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,(100 shl 16) or 50,0,100),
            ReferenceDrumEvent(2,2,61,(-100 shl 16) or 75,0,100))
        val track=ReferenceDrumTrack("Packed touch",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{100},{_,_,_->1.0},48000)
        player.next(0,120)
        assertEquals(.75,player.left,0.0);assertEquals(.5,player.right,0.0)
    }
}
