package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceStyleTransitionTest {
    @Test fun changingStyleAnchorsNewSectionWithThePreviousSharedScale(){
        fun create(group:ImportedStyleGroup,events:List<ReferenceDrumEvent>):ImportedStyleSession {
            val player=ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(group.name,group.id,group.parent,
                group.section,120,true,0,group.end.toLong(),false,events)),{1000},{_,_,_->1.0},1000)
            return ImportedStyleSession(ImportedStyleDefinition(group.parent,group.name,listOf(group)),mapOf(group.id to player),1000)
        }
        val old=create(ImportedStyleGroup(1,100,"3/8",32,true,0,750,120,meterX=3,meterY=8),emptyList())
        val replacement=create(ImportedStyleGroup(2,200,"4/4",32,true,0,1000,120),
            listOf(ReferenceDrumEvent(2,1,60,100,250,100)))
        try{
            repeat(1000){old.next(120)}
            assertEquals(750,old.padTiming.second)
            replacement.continueFrom(old)
            assertEquals(1000L,replacement.padTiming.first)
            assertEquals(1000,replacement.padTiming.second)
            // GroupPlayStart uses the old 750-unit scale before installing
            // the new 1000-unit scale: 1000 % 750 starts at offset 250.
            replacement.next(120)
            assertEquals(1.0,replacement.player.left,0.0)
        }finally{old.close();replacement.close()}
    }
    @Test fun audioPadRenderingFeedsBackScaleWithoutAdvancingStyleClock(){
        val session=session(lock=true)
        val bank=ImportedPadBank(1000)
        val group=ImportedStyleGroup(9,100,"Pad",96,true,0,1500,120,meterX=3,meterY=4)
        val player=ReferenceRhythmPlayer(listOf(ReferenceDrumTrack("Pad",9,100,96,120,true,0,1500,false,
            listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),{10000},{_,_,_->1.0},1000)
        try{
            bank.play(0,player,group)
            session.renderPads(bank,120,1f,1f,2)
            assertEquals(0L,session.padTiming.first);assertEquals(1500,session.padTiming.second)
            assertEquals(1.0,bank.left,0.0)
            repeat(100){session.next(120);session.renderPads(bank,120,1f,1f,2)}
            session.request(2)
            repeat(1400){session.next(120);session.renderPads(bank,120,1f,1f,2);assertEquals(1,session.currentId)}
            session.next(120);assertEquals(2,session.currentId)
        }finally{session.close();bank.close()}
    }
    @Test fun manualStopDrainsVoiceWithoutPlayingQueuedSectionsOrMovingClock(){
        val session=session(lock=true)
        try{
            session.next(120);session.request(2)
            val time=session.padTiming.first
            session.stopPlayback()
            assertTrue(session.finished);assertNull(session.pendingId)
            session.renderTail();assertEquals(.1,session.player.left,1e-9)
            session.renderTail();assertEquals(.09,session.player.left,1e-9)
            repeat(20){session.next(120);session.renderTail()}
            assertEquals(time,session.padTiming.first);assertFalse(session.hasTail)
            assertEquals(1,session.currentId)
            session.preparePlay();session.next(120)
            assertFalse(session.finished);assertEquals(.1,session.player.left,1e-9)
        }finally{session.close()}
    }
    @Test fun stopFollowedImmediatelyByIntroKeepsTheNewButtonRequest(){
        val session=session(lock=true)
        try{
            session.next(120)
            session.press(5,false,false)
            session.stopPlayback(preserveImmediate=true)
            session.preparePlay();session.next(120)
            assertEquals(5,session.currentId);assertFalse(session.finished)
            repeat(1000){session.next(120)}
            assertEquals(1,session.currentId)
        }finally{session.close()}
    }
    @Test fun revisitingMainKeepsItsChannelVolumeUntilItsNextAutomationEvent(){
        val groups=listOf(ImportedStyleGroup(1,100,"Main 1",32,true,0,4000,120),
            ImportedStyleGroup(2,100,"Main 2",33,true,0,4000,120))
        val players=groups.associate{group->
            val events=listOf(ReferenceDrumEvent(2,1,60,100,0,4000))+
                if(group.id==1)listOf(ReferenceDrumEvent(44,0,50,0,200,0)) else emptyList()
            group.id to ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(group.name,group.id,100,group.section,
                120,true,0,4000,false,events)),{10000},{_,_,_->1.0},1000,initialChannel=ImportedChannelSettings())
        }
        val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",groups),players,1000)
        try{
            repeat(201){session.next(120)}
            assertEquals(.5,session.player.left,0.0)
            session.request(2,true);repeat(21){session.next(120)}
            session.request(1,true);repeat(21){session.next(120)}
            assertEquals(1,session.currentId)
            assertEquals(.5,session.player.mixedLeft(1f,1f),1e-9)
        }finally{session.close()}
    }
    @Test fun padScaleMovesTheQueuedStyleWindowWithoutResettingTransport(){
        val session=session(lock=true)
        try{
            repeat(100){session.next(120)}
            session.request(2)
            session.applyPadRhythmScale(1500)
            assertEquals(100L,session.padTiming.first);assertEquals(2,session.pendingId)
            repeat(1400){session.next(120);assertEquals(1,session.currentId)}
            session.next(120);assertEquals(2,session.currentId)
            assertEquals(1501L,session.padTiming.first)
        }finally{session.close()}
    }
    @Test fun performanceMuteWaitsForRhythmWindowWithoutChangingSection(){
        val session=session(lock=true)
        repeat(100){session.next(120)}
        session.togglePerformanceMute(true,true)
        assertFalse(session.chordMuted);assertNull(session.pendingId)
        repeat(900){session.next(120);assertFalse(session.chordMuted)}
        session.next(120);assertTrue(session.chordMuted);assertEquals(1,session.currentId)
        session.togglePerformanceMute(false,false);assertTrue(session.drumMuted)
        session.request(2,true);session.next(120)
        assertTrue(session.chordMuted);assertTrue(session.drumMuted)
        session.togglePerformanceMute(true,false);assertFalse(session.chordMuted)
    }
    @Test fun revisitingStyleRestoresEditedTempoEvenWhenSelectedWhileLocked(){
        val bank=Any();val memory=ImportedStyleTempoMemory()
        val first=session();first.setManualTempo(147)
        memory.remember(bank,first.style.id,147)
        memory.remember(bank,999,173) // A different style must not replace this one's tempo.
        val revisited=session(lock=true)
        revisited.restoreManualTempo(memory.get(bank,revisited.style.id))
        assertEquals(147,revisited.selectedTempo)
        revisited.request(2,true);revisited.next(120);assertNull(revisited.takeTempoChange())
        revisited.configureTiming(0,false)
        revisited.request(1,true);revisited.next(120);assertEquals(147,revisited.takeTempoChange())
        val otherBank=Any()
        val replacement=session();replacement.restoreManualTempo(memory.get(otherBank,replacement.style.id))
        assertEquals(120,replacement.selectedTempo)
        assertEquals(120,replacement.style.sections.first().tempo)
    }
    @Test fun manualTempoAppliesAcrossPartsWithoutMutatingImportedSet(){
        val session=session()
        session.setManualTempo(147)
        session.request(2,true);session.next(120);assertEquals(147,session.takeTempoChange())
        session.request(3,true);session.next(147);assertEquals(147,session.takeTempoChange())
        session.reset();session.request(1,true);session.next(147);assertEquals(147,session.takeTempoChange())
        assertEquals(160,session.style.sections.first{it.id==2}.tempo)
    }
    @Test fun lockedManualTempoDoesNotRewriteSectionTempos(){
        val session=session(lock=true)
        session.setManualTempo(147)
        session.request(2,true);session.next(147);assertNull(session.takeTempoChange())
        session.configureTiming(0,false)
        session.request(2,true);session.next(147);assertEquals(160,session.takeTempoChange())
    }
    @Test fun changingTimingWhileRunningPreservesPendingRequestUntilSectionBoundary(){
        val session=session(lock=true)
        repeat(100){session.next(120)}
        session.request(2)
        session.configureTiming(3,true)
        repeat(3900){session.next(120);assertEquals(1,session.currentId)}
        session.next(120);assertEquals(2,session.currentId);assertNull(session.takeTempoChange())
    }
    @Test fun enablingTempoLockClearsPendingTempoAndUnlockRestoresSectionTempo(){
        val session=session();session.request(2,true);session.next(120)
        session.configureTiming(0,true)
        assertNull(session.takeTempoChange())
        session.request(1,true);session.next(120);assertNull(session.takeTempoChange())
        session.configureTiming(0,false)
        session.request(2,true);session.next(120);assertEquals(160,session.takeTempoChange())
    }
    private fun session(jump:Int=2,lock:Boolean=false):ImportedStyleSession {
        fun group(id:Int,section:Int,loop:Boolean,end:Int,jumpGroup:Int=-1)=
            ImportedStyleGroup(id,100,"Part",section,loop,0,end,if(id==2)160 else 120,jumpGroup=jumpGroup)
        val groups=listOf(group(1,32,true,4000),group(2,33,true,4000),group(3,49,false,1000),
            group(4,80,false,1000,jump),group(5,16,false,1000,0))
        val players=groups.associate{g->
            val track=ReferenceDrumTrack("Part",g.id,100,g.section,120,g.loop,0,g.end.toLong(),false,
                listOf(ReferenceDrumEvent(2,1,60,100,0,g.end.toLong())))
            g.id to ReferenceRhythmPlayer(listOf(track),{10000},{_,_,_->0.1},1000)
        }
        return ImportedStyleSession(ImportedStyleDefinition(100,"Style",groups),players,1000,tempoLock=lock)
    }
    @Test fun queuedMainChangesAtRhythmScaleBeforeFourSecondPartEnds() {
        val session=session(lock=true)
        repeat(100){session.next(120)}
        session.request(2)
        repeat(900){session.next(120);assertEquals(1,session.currentId)}
        session.next(120);assertEquals(2,session.currentId)
    }
    @Test fun mainButtonInterruptsLongIntroOrEndingAtNextRhythmWindow(){
        for(section in listOf(16,80)){
            val groups=listOf(ImportedStyleGroup(1,100,"Main",32,true,0,4000,120),
                ImportedStyleGroup(2,100,"One shot",section,false,0,4000,120,jumpGroup=0))
            val players=groups.associate{g->g.id to ReferenceRhythmPlayer(listOf(
                ReferenceDrumTrack(g.name,g.id,100,g.section,120,g.loop,0,4000,false,
                    listOf(ReferenceDrumEvent(2,1,60,100,0,4000)))),{10000},{_,_,_->0.1},1000)}
            val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",groups),players,1000)
            session.press(2,false,false);repeat(100){session.next(120)}
            session.press(1,true,false)
            repeat(900){session.next(120);assertEquals(2,session.currentId)}
            session.next(120);assertEquals(1,session.currentId);assertFalse(session.finished)
        }
    }
    @Test fun multipleRequestsAreQueuedAndRepeatedLoopRequestIsIgnored() {
        val session=session(lock=true)
        session.request(1);assertNull(session.pendingId)
        repeat(100){session.next(120)}
        session.request(2);session.request(3)
        assertEquals(2,session.pendingId)
        repeat(901){session.next(120)}
        assertEquals(2,session.currentId);assertEquals(3,session.pendingId)
        session.next(120);assertEquals(3,session.currentId)
    }
    @Test fun endingFollowsPositiveJumpAndExplicitJumpTakesPriority() {
        val session=session(lock=true);session.request(4,true)
        repeat(1001){session.next(120)}
        assertEquals(2,session.currentId);assertFalse(session.finished)
        val overridden=session(lock=true);overridden.request(4,true);overridden.jump(1)
        repeat(1001){overridden.next(120)}
        assertEquals(1,overridden.currentId)
    }
    @Test fun zeroJumpStopsEvenForIntroAndNegativeJumpReturnsLastLoop() {
        val stop=session();stop.request(5,true);repeat(1001){stop.next(120)}
        assertTrue(stop.finished)
        val back=session(jump=-1,lock=true);back.request(2,true);back.next(120)
        back.request(4,true);repeat(1001){back.next(120)}
        assertEquals(2,back.currentId);assertFalse(back.finished)
    }
    @Test fun unavailableJumpReportsErrorInsteadOfSubstitutingMain() {
        val session=session(jump=999);session.request(4,true);repeat(1001){session.next(120)}
        assertTrue(session.finished);assertNotNull(session.transitionError)
    }
    @Test fun transitionAppliesStoredTempoUnlessLocked() {
        val unlocked=session();unlocked.request(2,true);unlocked.next(120)
        assertEquals(160,unlocked.takeTempoChange())
        assertNull(unlocked.takeTempoChange())
        val locked=session(lock=true);locked.request(2,true);locked.next(120)
        assertNull(locked.takeTempoChange())
    }
    @Test fun autoFillMainButtonQueuesMatchingFillThenJumpsToRequestedMain() {
        val session=session(lock=true)
        repeat(100){session.next(120)}
        session.press(2,running=true,autoFill=true)
        assertEquals(3,session.pendingId)
        repeat(901){session.next(120)}
        assertEquals(3,session.currentId)
        repeat(1000){session.next(120)}
        assertEquals(2,session.currentId)
    }
    @Test fun missingFillAndDisabledAutoFillSelectMainDirectly() {
        val missing=session(lock=true);missing.press(1,true,true)
        assertNull(missing.pendingId) // Same looping Main; no matching Fill 1.
        val disabled=session(lock=true);disabled.press(2,true,false)
        assertEquals(2,disabled.pendingId)
        val stopped=session(lock=true);stopped.press(2,false,true);stopped.next(120)
        assertEquals(2,stopped.currentId)
    }
    @Test fun introButtonOverridesStoredIntroChainAndReturnsToSelectedMain() {
        val groups=listOf(
            ImportedStyleGroup(1,100,"Main",32,true,0,1000,120),
            ImportedStyleGroup(2,100,"Main 2",33,true,0,1000,120),
            ImportedStyleGroup(3,100,"Intro 1",16,false,0,1000,120,jumpGroup=4),
            ImportedStyleGroup(4,100,"Intro 2",17,false,0,1000,120,jumpGroup=0))
        fun create():ImportedStyleSession {
            val players=groups.associate{g->g.id to ReferenceRhythmPlayer(listOf(
                ReferenceDrumTrack(g.name,g.id,100,g.section,120,g.loop,0,1000,false,
                    listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),{10000},{_,_,_->0.1},1000)}
            return ImportedStyleSession(ImportedStyleDefinition(100,"Test",groups),players,1000)
        }
        val stopped=create();stopped.press(3,false,false)
        stopped.reset() // Audio idle/reset must preserve the Intro button's return command.
        repeat(1001){stopped.next(120)}
        assertEquals(1,stopped.currentId);assertFalse(stopped.finished)
        val running=create();running.press(2,false,false);running.next(120)
        running.press(3,true,false)
        repeat(2000){running.next(120)}
        assertEquals(2,running.currentId);assertFalse(running.finished)
        val replace=create();replace.request(2,immediate=true);replace.next(120)
        replace.request(3)
        replace.press(4,true,false)
        assertEquals(4,replace.pendingId)
        val raw=create();raw.request(3,true)
        repeat(1001){raw.next(120)}
        assertEquals(4,raw.currentId) // Raw playback still honors the unmodified SET jump.
    }
}
