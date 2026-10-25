package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStyleSessionTest {
    @Test fun sectionChangeKeepsMusicFadeAndCancelInAnotherSectionRestoresIt(){
        val sections=listOf(part(1,32),part(2,33))
        val players=sections.associate{section->section.id to ReferenceRhythmPlayer(listOf(
            ReferenceDrumTrack("Part",section.id,100,section.section,120,true,0,1000,false,listOf(
                ReferenceDrumEvent(if(section.id==1)211 else 212,0,100,0,0,0),
                ReferenceDrumEvent(2,1,60,100,0,1000)))),{10000},{_,_,_->1.0},1000)}
        val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",sections),players,1000)
        val fade=ReferenceFadeController()
        try{
            session.withMusicFade(fade)
            session.next(120);session.player.mixOutput(1f,1f)
            repeat(50){fade.advance()}
            assertEquals(0.49,fade.advance(),0.000001)
            session.request(2,true);session.next(120);session.player.mixOutput(1f,1f)
            assertEquals(2,session.currentId)
            assertEquals(1.0,fade.advance(),0.0)
        }finally{session.close()}
    }
    @Test fun changingStyleDuringIntroPreservesTheLastMainVariation(){
        val old=session();val replacement=session()
        try{
            old.request(2,true);repeat(100){old.next(120)}
            old.press(3,true,false);repeat(1000){old.next(120)}
            assertEquals(3,old.currentId)
            replacement.continueFrom(old)
            assertEquals(3,replacement.currentId)
            repeat(1001){replacement.next(120)}
            assertEquals(2,replacement.currentId)
        }finally{old.close();replacement.close()}
    }
    @Test fun sessionResetClearsVoicesFromInactiveSectionsToo(){
        val parts=listOf(part(1,32),part(2,33))
        val players=parts.associate{p->p.id to ReferenceRhythmPlayer(listOf(
            ReferenceDrumTrack("Part",p.id,100,p.section,120,true,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),
            {10000},{_,_,_->1.0},1000)}
        // Inactive sections may retain state after prior visits or partial transitions.
        players.getValue(2).next(0,120);assertTrue(players.getValue(2).left>0)
        val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",parts),players,1000)
        session.reset()
        players.getValue(2).renderTail()
        assertEquals(0.0,players.getValue(2).left,0.0)
        session.next(120);assertEquals(1,session.currentId);assertTrue(session.player.left>0)
    }
    @Test fun playAfterEndingRestartsSelectedSection(){
        val session=session();session.request(6,immediate=true)
        repeat(1001){session.next(120)};assertTrue(session.finished)
        session.preparePlay();assertFalse(session.finished)
        session.next(120);assertEquals(6,session.currentId);assertTrue(session.player.left>0)
    }
    @Test fun playAfterEndingPreservesImmediateMainChoice(){
        val session=session();session.request(6,immediate=true)
        repeat(1001){session.next(120)};assertTrue(session.finished)
        session.request(2,immediate=true);session.preparePlay();session.next(120)
        assertEquals(2,session.currentId);assertFalse(session.finished);assertTrue(session.player.left>0)
    }
    private fun part(id:Int,section:Int)=ImportedStyleGroup(id,100,"Part",section,section in 32..35,0,1000,120,jumpGroup=if(section in 80..82)0 else -1)
    private val parts=listOf(part(1,32),part(2,33),part(3,16),part(4,48),part(5,64),part(6,80),part(7,81))
    private fun session():ImportedStyleSession {
        val players=parts.dropLast(1).associate{part->
            val track=ReferenceDrumTrack("Part",part.id,100,part.section,120,part.loop,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))
            part.id to ReferenceRhythmPlayer(listOf(track),{10000},{_,_,_->1.0},1000)
        }
        return ImportedStyleSession(ImportedStyleDefinition(100,"Style",parts),players,1000)
    }
    @Test fun catalogGroupsAllPartsByParentInsteadOfListingVariationsAsStyles() {
        val parent=ImportedStyleGroup(100,0,"Dance",0,false,0,0,120)
        val pad=part(20,96)
        val orphan=part(21,80).copy(parent=999)
        val catalog=ImportedStyleCatalog.styles(ImportedSetIndex(emptyList(),listOf(parent)+parts+pad+orphan))
        assertEquals(1,catalog.size);assertEquals("Dance",catalog.single().name)
        assertEquals(parts.sortedBy{it.section},catalog.single().sections)
        assertEquals("MAIN 1",ImportedStyleCatalog.label(32))
        assertEquals("ENDING 3",ImportedStyleCatalog.label(82))
    }
    @Test fun changingStyleKeepsMainAndMusicalPosition(){
        val old=session();old.request(2,immediate=true);repeat(250){old.next(120)}
        val replacement=session();replacement.continueFrom(old)
        assertEquals(2,replacement.currentId)
        replacement.request(4)
        repeat(750){replacement.next(120);assertEquals(2,replacement.currentId)}
        replacement.next(120);assertEquals(4,replacement.currentId)
        assertFalse(replacement.finished)
    }
    @Test fun unboundedEndingUsesEventsInsteadOfStoredZeroBounds(){
        val ending=part(9,80).copy(limit=false,end=0)
        val main=parts.first()
        fun player(group:ImportedStyleGroup,events:List<ReferenceDrumEvent>)=ReferenceRhythmPlayer(listOf(
            ReferenceDrumTrack("Part",group.id,100,group.section,120,group.loop,0,group.end.toLong(),false,events,bounded=group.limit)),
            {10000},{_,_,_->1.0},1000)
        val session=ImportedStyleSession(ImportedStyleDefinition(100,"Style",listOf(main,ending)),mapOf(
            main.id to player(main,listOf(ReferenceDrumEvent(2,1,60,100,0,1000))),
            ending.id to player(ending,listOf(ReferenceDrumEvent(2,1,60,100,0,100),ReferenceDrumEvent(1,1,60,0,100,0)))),1000)
        session.request(ending.id,true)
        repeat(101){session.next(120);assertFalse(session.finished)}
        repeat(1000){session.next(120)}
        assertTrue(session.finished)
    }
    @Test fun changingStyleDuringFillOrBreakReturnsToCorrespondingMain(){
        for(id in listOf(4,5)){
            val old=session();old.request(id,immediate=true);old.next(120)
            val replacement=session();replacement.continueFrom(old)
            assertEquals(1,replacement.currentId);assertFalse(replacement.finished)
        }
    }
    @Test fun introReturnsToMainAndEndingStops() {
        val session=session();session.request(3,immediate=true)
        repeat(1000){session.next(120);assertEquals(3,session.currentId)}
        session.next(120);assertEquals(1,session.currentId)
        session.request(6)
        session.next(120)
        assertEquals(6,session.currentId)
        repeat(1000){session.next(120)}
        assertTrue(session.finished)
        repeat(30){session.renderTail()}
        assertEquals(0.0,session.player.left,0.0)
    }
    @Test fun fillAndBreakReturnToLastSelectedMainAtSectionBoundary() {
        for(part in listOf(4,5)){
            val session=session();session.request(2,immediate=true)
            repeat(100){session.next(120)}
            session.request(part)
            repeat(900){session.next(120);assertEquals(2,session.currentId)}
            session.next(120);assertEquals(part,session.currentId)
            repeat(1000){session.next(120)}
            assertEquals(2,session.currentId)
        }
    }
    @Test fun unsupportedPartCannotBeQueuedAndStoppedSelectionStartsImmediately() {
        val session=session();assertFalse(session.available(7))
        session.request(7);assertNull(session.pendingId)
        session.request(2,immediate=true);session.reset();assertEquals(2,session.currentId)
        session.next(120);assertTrue(session.player.left>0)
        session.reset();assertEquals(0.0,session.player.left,0.0)
    }
    @Test fun immediateSectionButtonStartsRequestedPartWithoutAnExtraResetOrPlay() {
        for(id in 1..6){
            val session=session()
            session.request(id,immediate=true)
            session.next(120)
            assertEquals(id,session.currentId)
            assertNull(session.pendingId)
            assertTrue(session.player.left>0)
        }
    }
    @Test fun sectionButtonRestartsAfterEndingHasFinished() {
        val session=session();session.request(6,immediate=true)
        repeat(1001){session.next(120)}
        assertTrue(session.finished)
        session.request(2,immediate=true);session.next(120)
        assertFalse(session.finished);assertEquals(2,session.currentId)
        assertTrue(session.player.left>0)
    }
}
