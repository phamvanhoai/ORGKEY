package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceStyleTransitionTest {
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
