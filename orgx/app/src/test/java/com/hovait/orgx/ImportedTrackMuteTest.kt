package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedTrackMuteTest {
    @Test fun headerMuteIsRestoredOnResetAndUnmuteOnlyAffectsFollowingNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100),ReferenceDrumEvent(14,0,0,0,5,0),
            ReferenceDrumEvent(2,2,60,50,5,100))
        val track=ReferenceDrumTrack("Header mute",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000,initiallyMuted=true)
        player.next(0,120);assertEquals(0.0,player.left,0.0)
        repeat(5){player.next(0,120)};assertEquals(.5,player.left,0.0)
        player.reset();player.next(0,120);assertEquals(0.0,player.left,0.0)
        val group=ImportedStyleGroup(1,0,"Main",32,true,0,1000,120)
        val imported=ImportedTrack("Header mute",1,0,true,events)
        val index=ImportedSetIndex(emptyList(),listOf(group),listOf(imported))
        assertEquals(listOf(imported),ImportedStyleSupport.activeTracks(index,group))
        assertTrue(ImportedStyleSupport.activeTracks(index.copy(tracks=listOf(imported.copy(events=events.filter{it.type!=14}))),group).isEmpty())
        assertTrue(imported.muted)
    }
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
