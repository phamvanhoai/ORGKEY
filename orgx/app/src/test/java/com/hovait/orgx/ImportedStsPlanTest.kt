package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsPlanTest {
    private val sts=ImportedSts("STS",true,0,7,3,1,2,142,0,0,emptyList(),ImportedChannelSettings())
    private val track=ImportedStsPlan.Track(1,1,2,0,sts)
    @Test fun defaultsMatchOriginalAndKeepMasterAndTrackCommandOrder(){
        val actions=ImportedStsPlan.create(2,sts,listOf(track))
        assertEquals(listOf(
            ImportedStsPlan.Action.Event(0,224,key=2),
            ImportedStsPlan.Action.Record(1,true),
            ImportedStsPlan.Action.Event(1,62,0,7),
            ImportedStsPlan.Action.Event(1,12),
            ImportedStsPlan.Action.Event(1,61,0,2),
            ImportedStsPlan.Action.Event(1,64,key=2)),actions)
    }
    @Test fun globalChangesAndExplicitKeepFlagsUseOriginalEventIds(){
        val mask=MutableList(21){false}
        listOf(0,1,9,11,12,20).forEach{mask[it]=true}
        val events=ImportedStsPlan.create(3,sts,listOf(track),mask).filterIsInstance<ImportedStsPlan.Action.Event>()
        assertEquals(listOf(222,223,224,225,62,63,61,64),events.map{it.type})
        assertEquals(142,events[0].key)
        assertEquals(-1,events.first{it.type==61}.id)
        mask[11]=false
        assertEquals(-1,ImportedStsPlan.create(3,sts,listOf(track),mask)
            .filterIsInstance<ImportedStsPlan.Action.Event>().first{it.type==62}.id)
    }
    @Test fun unchangedReferencesAndNonKeyboardTracksSkipOnlyTheApplicableCommands(){
        val same=track.copy(kind=3,instrument=7,system=3,snapshot=sts.copy(pedal=0))
        val events=ImportedStsPlan.create(0,sts,listOf(same),List(21){true})
            .filterIsInstance<ImportedStsPlan.Action.Event>()
        assertEquals(listOf(222,223,224,225,11),events.map{it.type})
        val late=ImportedStsPlan.create(0,sts,listOf(track.copy(id=5)),List(21){false})
        assertEquals(listOf(ImportedStsPlan.Action.Event(0,224)),late)
        assertTrue(ImportedStsPlan.create(0,null,listOf(track)).isEmpty())
    }
}
