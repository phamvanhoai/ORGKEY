package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceRhythmPlayerTest {
    private fun track(events:List<ReferenceDrumEvent>,end:Long=1000)=ReferenceDrumTrack("Test",1,0,32,120,true,0,end,false,events)
    private fun hit(time:Long)=ReferenceDrumEvent(2,1,36,100,time,100)
    private fun player(track:ReferenceDrumTrack)=ReferenceRhythmPlayer(listOf(track),{10000},
        {_,age,channel->if(age==0)if(channel==0)1.0 else -1.0 else 0.0},1000)
    @Test fun schedulesHitsAtTempoAndWrapsLoop() {
        val player=player(track(listOf(hit(0),hit(500))))
        val hits=mutableListOf<Int>()
        repeat(1001){frame->player.next(0,120);if(player.left!=0.0){hits.add(frame);assertEquals(-player.left,player.right,0.0)}}
        assertEquals(listOf(0,500,1000),hits)
    }
    @Test fun doubleTempoHalvesFrameDistance() {
        val player=player(track(listOf(hit(0),hit(500))))
        val hits=mutableListOf<Int>()
        repeat(501){frame->player.next(0,240);if(player.left!=0.0)hits.add(frame)}
        assertEquals(listOf(0,250,500),hits)
    }
    @Test fun releaseFadesAndResetRemovesActiveVoices() {
        val events=listOf(hit(0),ReferenceDrumEvent(1,1,36,-1,10,-1))
        val player=ReferenceRhythmPlayer(listOf(track(events)),{10000},{_,_,_->1.0},1000)
        repeat(11){player.next(0,120)}
        assertEquals(1.0,player.left,0.0)
        repeat(10){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
        player.reset();assertEquals(0.0,player.right,0.0)
        player.next(0,120);assertEquals(1.0,player.left,0.0)
    }
}
