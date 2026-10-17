package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFxContinuityTest {
    private val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0))))
    private fun player(note:Boolean,fx:ImportedChannelSettings=settings):ReferenceRhythmPlayer {
        val events=if(note)listOf(ReferenceDrumEvent(2,1,60,100,0,1)) else emptyList()
        val track=ReferenceDrumTrack("Part",1,0,32,120,true,0,1000,false,events)
        return ReferenceRhythmPlayer.combine(listOf(ReferenceRhythmPlayer(listOf(track),{1},{_,_,_->1.0},48000)))
            .withGroupFx(fx)
    }
    @Test fun changingSectionKeepsEchoTailWithSameSettings(){
        val first=player(true)
        first.next(0,120);first.mixOutput(1f,1f)
        assertEquals(1.0,first.outputLeft,0.0)
        val second=player(false);second.startAt(0);second.continueGroupFxFrom(first)
        repeat(479){second.next(0,120);second.mixOutput(1f,1f);assertEquals(0.0,second.outputLeft,0.0)}
        second.next(0,120);second.mixOutput(1f,1f);assertEquals(.5,second.outputLeft,0.0)
        second.reset();repeat(500){second.next(0,120);second.mixOutput(1f,1f);assertEquals(0.0,second.outputLeft,0.0)}
    }
    @Test fun changedConfigurationDoesNotReuseOldEchoBuffer(){
        val first=player(true);first.next(0,120);first.mixOutput(1f,1f)
        val second=player(false,settings.copy(fxEnabled=false));second.startAt(0);second.continueGroupFxFrom(first)
        repeat(500){second.next(0,120);second.mixOutput(1f,1f);assertEquals(0.0,second.outputLeft,0.0)}
    }
    @Test fun restartingSameSectionCanPreserveBusWhileStopStillClearsIt(){
        val part=player(true);part.next(0,120);part.mixOutput(1f,1f)
        part.startAt(100,preserveGroupFx=true)
        repeat(479){part.next(0,120);part.mixOutput(1f,1f)}
        part.next(0,120);part.mixOutput(1f,1f);assertEquals(.5,part.outputLeft,0.0)
        part.reset();part.startAt(100)
        repeat(500){part.next(0,120);part.mixOutput(1f,1f);assertEquals(0.0,part.outputLeft,0.0)}
    }
}
