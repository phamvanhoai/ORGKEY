package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedReleaseTailTest {
    @Test fun trackEchoSurvivesAfterVoiceHasAlreadyFinishedAndPlayerIsReused(){
        val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0))))
        val track=ReferenceDrumTrack("Echo",1,0,32,120,true,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,1)))
        val leaf=ReferenceRhythmPlayer(listOf(track),{1},{_,_,_->1.0},1000,fx=ImportedFxChain(settings,1000))
        val old=ReferenceRhythmPlayer.combine(listOf(leaf));old.next(0,120)
        val next=player(false);next.startAt(0);next.releaseVoicesFrom(old)
        old.reset() // Resetting the source cannot erase the transferred buffer.
        repeat(9){next.next(0,120);assertEquals(0.0,next.mixedLeft(1f,1f),0.0)}
        next.next(0,120);assertEquals(.5,next.mixedLeft(1f,1f),0.0)
        next.reset();repeat(20){next.next(0,120);assertEquals(0.0,next.mixedLeft(1f,1f),0.0)}
    }
    private fun player(events:Boolean,melodic:Boolean=false):ReferenceRhythmPlayer {
        val track=ReferenceDrumTrack("Part",1,0,32,120,true,0,1000,false,
            if(events)listOf(ReferenceDrumEvent(2,1,60,100,0,1000)) else emptyList())
        return ReferenceRhythmPlayer.combine(listOf(ReferenceRhythmPlayer(listOf(track),{10000},{_,_,_->1.0},1000,accompaniment=melodic)))
    }
    @Test fun oldVoicesReleaseAfterTransitionWithoutSchedulingOldNotes(){
        val old=player(true);old.next(0,120)
        val next=player(false);next.startAt(0);next.releaseVoicesFrom(old)
        next.next(0,120);assertTrue(next.mixedLeft(1f,1f)>0)
        repeat(20){next.next(0,120)}
        assertEquals(0.0,next.mixedLeft(1f,1f),0.0)
        assertEquals(0.0,next.mixedRight(1f,1f),0.0)
    }
    @Test fun releasedMelodicTailStillUsesChordMixerAndStopClearsIt(){
        val old=player(true,true);old.next(0,120)
        val next=player(false);next.startAt(0);next.releaseVoicesFrom(old);next.next(0,120)
        assertEquals(0.0,next.mixedLeft(1f,0f),0.0)
        assertTrue(next.mixedLeft(0f,1f)>0)
        next.reset();next.next(0,120);assertEquals(0.0,next.mixedLeft(1f,1f),0.0)
    }
    @Test fun rapidTransitionsCarryExistingReleaseInsteadOfLosingIt(){
        val first=player(true);first.next(0,120)
        val second=player(false);second.startAt(0);second.releaseVoicesFrom(first);second.next(0,120)
        val third=player(false);third.startAt(0);third.releaseVoicesFrom(second);third.next(0,120)
        assertTrue(third.mixedLeft(1f,1f)>0)
        repeat(20){third.next(0,120)};assertEquals(0.0,third.mixedLeft(1f,1f),0.0)
    }
}
