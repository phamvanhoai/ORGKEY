package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStyleSessionTest {
    private fun part(id:Int,section:Int)=ImportedStyleGroup(id,100,"Part",section,section in 32..35,0,10,120)
    private val parts=listOf(part(1,32),part(2,33),part(3,16),part(4,48),part(5,64),part(6,80),part(7,81))
    private fun session():ImportedStyleSession {
        val players=parts.dropLast(1).associate{part->
            val track=ReferenceDrumTrack("Part",part.id,100,part.section,120,part.loop,0,10,false,listOf(ReferenceDrumEvent(2,1,60,100,0,10)))
            part.id to ReferenceRhythmPlayer(listOf(track),{100},{_,_,_->1.0},1000)
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
    @Test fun introReturnsToMainAndEndingStops() {
        val session=session();session.request(3);session.reset()
        repeat(10){session.next(120);assertEquals(3,session.currentId)}
        session.next(120);assertEquals(1,session.currentId)
        session.request(6)
        repeat(10){session.next(120)}
        assertEquals(6,session.currentId)
        repeat(10){session.next(120)}
        assertTrue(session.finished)
        assertEquals(0.0,session.player.left,0.0)
    }
    @Test fun fillAndBreakReturnToLastSelectedMainAtSectionBoundary() {
        for(part in listOf(4,5)){
            val session=session();session.request(2);session.reset()
            session.request(part)
            repeat(10){session.next(120);assertEquals(2,session.currentId)}
            session.next(120);assertEquals(part,session.currentId)
            repeat(10){session.next(120)}
            assertEquals(2,session.currentId)
        }
    }
    @Test fun unsupportedPartCannotBeQueuedAndStoppedSelectionStartsImmediately() {
        val session=session();assertFalse(session.available(7))
        session.request(7);assertNull(session.pendingId)
        session.request(2);session.reset();assertEquals(2,session.currentId)
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
        repeat(11){session.next(120)}
        assertTrue(session.finished)
        session.request(2,immediate=true);session.next(120)
        assertFalse(session.finished);assertEquals(2,session.currentId)
        assertTrue(session.player.left>0)
    }
}
