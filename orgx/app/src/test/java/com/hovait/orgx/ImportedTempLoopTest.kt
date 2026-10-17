package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedTempLoopTest {
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
