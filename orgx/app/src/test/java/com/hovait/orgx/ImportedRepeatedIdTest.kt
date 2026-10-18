package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedRepeatedIdTest {
    @Test fun overlappingNotesWithSameIdAreSummedAndReleasedTogether(){
        val events=listOf(ReferenceDrumEvent(2,7,60,100,0,1000),ReferenceDrumEvent(2,7,61,50,0,1000),
            ReferenceDrumEvent(1,7,0,0,2,0))
        val track=ReferenceDrumTrack("Repeated ID",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000)
        player.next(0,120);assertEquals(1.5,player.left,0.0)
        repeat(20){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
    }
    @Test fun repeatedNoteOffDoesNotRestartReleaseEnvelope(){
        val events=listOf(ReferenceDrumEvent(2,7,60,100,0,1000),
            ReferenceDrumEvent(1,7,0,0,2,0),ReferenceDrumEvent(1,7,0,0,5,0))
        val track=ReferenceDrumTrack("Release",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000)
        repeat(13){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
    }
}
