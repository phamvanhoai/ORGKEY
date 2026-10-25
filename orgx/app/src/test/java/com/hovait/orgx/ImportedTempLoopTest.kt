package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedTempLoopTest {
    @Test fun unboundedTempLoopUsesStoredTrackEndAndTheOriginalTenUnitThreshold(){
        for(end in listOf(10L,11L)){
            val main=ImportedStyleGroup(1,100,"Main",32,true,0,1000,120)
            val intro=main.copy(id=2,section=16,loop=false,limit=false,end=9999)
            val groups=listOf(main,intro)
            val players=groups.associate{part->part.id to ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(
                part.name,part.id,100,part.section,120,part.loop,0,1000,false,
                listOf(ReferenceDrumEvent(204,0,2,0,0,0)))),{10000},{_,_,_->0.0},1000)}
            val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",groups),players,1000,storedEndTimes=mapOf(2 to end))
            try{
                session.request(2,true);session.next(120)
                assertEquals(end>10,session.temporaryLoop)
            }finally{session.close()}
        }
    }
    @Test fun endTimeIncludesMutedTracksButUsesTheirFinalEventRatherThanLongestNote(){
        val first=ImportedTrack("Notes",2,0,false,listOf(
            ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(1,1,60,0,10,0)))
        val muted=first.copy(name="Muted",muted=true,events=listOf(ReferenceDrumEvent(1,1,60,0,12,0)))
        val index=ImportedSetIndex(emptyList(),emptyList(),listOf(first,muted))
        assertEquals(11L,ImportedStyleCatalog.endTimes(index).getValue(2))
        assertEquals(9L,ImportedStyleCatalog.endTimes(index.copy(tracks=listOf(first))).getValue(2))
    }
    @Test fun storedTempLoopKeepsOneShotSectionUntilAnotherSectionIsRequested(){
        val main=ImportedStyleGroup(1,100,"Main",32,true,0,1000,120)
        val intro=main.copy(id=2,name="Intro",section=16,loop=false,jumpGroup=1)
        val groups=listOf(main,intro)
        val players=groups.associate{part->part.id to ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(
            part.name,part.id,100,part.section,120,part.loop,0,1000,false,
            listOf(ReferenceDrumEvent(204,0,2,0,0,0),ReferenceDrumEvent(2,1,60,100,0,1000)))),
            {10000},{_,_,_->1.0},1000)}
        val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",groups),players,1000)
        try{
            session.request(2,true)
            repeat(2500){session.next(120);assertEquals(2,session.currentId)}
            assertTrue(session.temporaryLoop);assertFalse(session.finished)
            assertFalse(intro.loop)
            session.request(1,true);session.next(120);assertEquals(1,session.currentId)
        }finally{session.close()}
    }
    @Test fun storedPadTempLoopDefersItsJumpTarget(){
        val first=ImportedStyleGroup(1,100,"PAD",96,false,0,1000,120,jumpGroup=2)
        val next=first.copy(id=2,jumpGroup=0)
        val groups=listOf(first,next)
        val players=groups.associate{part->part.id to ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(
            part.name,part.id,100,part.section,120,false,0,1000,false,
            listOf(ReferenceDrumEvent(204,0,1,0,0,0),ReferenceDrumEvent(2,1,60,100,0,1000)))),
            {10000},{_,_,_->1.0},1000)}
        val sequence=ImportedPadSequence(1,groups.associateBy{it.id},players)
        try{
            sequence.start(0,1000,0)
            for(time in 0L..2500L){sequence.advance(time,1000,0,120);assertEquals(1,sequence.currentId)}
        }finally{sequence.close()}
    }
    @Test fun repeatedQueuedIntroKeepsLoopWhenItStarts(){
        val session=session();repeat(100){session.next(120)}
        session.press(2,true,false,true);session.press(2,true,false,true)
        assertEquals(2,session.pendingId)
        repeat(901){session.next(120)}
        assertEquals(2,session.currentId);assertTrue(session.temporaryLoop)
        repeat(2200){session.next(120);assertEquals(2,session.currentId)}
    }
    @Test fun repeatCurrentIntroDoesNotEraseQueuedMain(){
        val session=session();session.press(2,false,false,true);session.next(120)
        session.press(1,true,false,true)
        session.press(2,true,false,true)
        assertTrue(session.temporaryLoop);assertEquals(1,session.pendingId)
        repeat(1100){session.next(120)}
        assertEquals(1,session.currentId)
    }
    private fun session():ImportedStyleSession {
        val parts=listOf(ImportedStyleGroup(1,100,"Main",32,true,0,1000,120),
            ImportedStyleGroup(2,100,"Intro",16,false,0,1000,120,jumpGroup=-1),
            ImportedStyleGroup(3,100,"Ending",80,false,0,1000,120,jumpGroup=0))
        val players=parts.associate{part->part.id to ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(part.name,part.id,100,part.section,120,part.loop,0,1000,false,
            listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),{10000},{_,_,_->1.0},1000)}
        return ImportedStyleSession(ImportedStyleDefinition(100,"Style",parts),players,1000)
    }
    @Test fun repeatedIntroLoopsOnlyWhenOptionEnabledAndOtherPartCanExit(){
        val session=session();session.press(2,false,false,true);session.next(120)
        session.press(2,true,false,true);assertTrue(session.temporaryLoop)
        repeat(2500){session.next(120);assertEquals(2,session.currentId);assertFalse(session.finished)}
        session.press(1,true,false,true);repeat(1100){session.next(120)}
        assertEquals(1,session.currentId);assertFalse(session.temporaryLoop)
    }
    @Test fun repeatedEndingDoesNotStopUntilAnotherPartSelected(){
        val session=session();session.press(3,false,false,true);session.next(120);session.press(3,true,false,true)
        repeat(2200){session.next(120)};assertFalse(session.finished);assertTrue(session.temporaryLoop)
    }
    @Test fun disabledOptionKeepsNormalOneShotEnding(){
        val session=session();session.press(3,false,false);session.next(120)
        repeat(1100){session.next(120)};assertTrue(session.finished);assertFalse(session.temporaryLoop)
    }
}
