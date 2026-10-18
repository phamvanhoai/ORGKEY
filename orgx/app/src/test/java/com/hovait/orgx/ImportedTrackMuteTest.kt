package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedTrackMuteTest {
    @Test fun muteReleasesCurrentVoicesSkipsNewNotesAndUnmuteAllowsFollowingNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100),
            ReferenceDrumEvent(13,0,-1,0,1,0),ReferenceDrumEvent(2,2,61,100,2,100),
            ReferenceDrumEvent(14,0,-1,0,20,0),ReferenceDrumEvent(2,3,62,50,20,100))
        val track=ReferenceDrumTrack("Mute",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000)
        player.next(0,120);assertEquals(1.0,player.left,0.0)
        repeat(15){player.next(0,120)};assertEquals(0.0,player.left,0.0)
        repeat(5){player.next(0,120)};assertEquals(.5,player.left,0.0)
        player.reset();player.next(0,120);assertEquals(1.0,player.left,0.0)
    }
}
