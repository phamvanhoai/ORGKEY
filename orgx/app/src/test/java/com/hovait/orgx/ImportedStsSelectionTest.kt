package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsSelectionTest {
    @Test fun consoleIncludesEveryEnabledMasterSlotAfterTheFourStyleAssignments(){
        val group=ImportedStyleGroup(100,0,"Style",0,false,0,0,120,stsIndices=listOf(8,0,0,0))
        val base=index()
        val master=base.tracks[0].copy(sts=listOf(snapshot(12,1),snapshot(8,1),snapshot(0,1),snapshot(1,1),snapshot(5,1)))
        val bank=base.copy(styles=listOf(group),tracks=listOf(master,base.tracks[1]))
        assertEquals(listOf(8,null,null,null,0,1,5,12),ImportedStsSelection.consoleSlots(bank,100))
        assertEquals(master.sts.map{it.slot}.toSet(),ImportedStsSelection.consoleSlots(bank,100).filterNotNull().toSet())
    }
    @Test fun mainLinkResolvesMasterWithoutAnyPreviouslySelectedTrack(){
        val group=ImportedStyleGroup(100,0,"Style",0,false,0,0,120,stsIndices=listOf(8,9,0,0))
        val bank=index().copy(styles=listOf(group))
        assertEquals(8,ImportedStsSelection.linkedMainSlot(bank,100,32))
        assertNull(ImportedStsSelection.linkedMainSlot(bank,100,33))
        assertEquals(1,ImportedStsSelection.linkedMainSlot(bank,999,32))
        val missingMaster=bank.copy(tracks=bank.tracks.mapIndexed{position,track->if(position==0)track.copy(group=100) else track})
        assertNull(ImportedStsSelection.linkedMainSlot(missingMaster,100,32))
        val onlyMember=bank.copy(tracks=bank.tracks.mapIndexed{position,track->if(position==0)track.copy(sts=emptyList()) else track})
        assertNull(ImportedStsSelection.linkedMainSlot(onlyMember,100,32))
    }
    @Test fun mainVariationUsesPositiveGroupStsOverrideOrItsDefaultSlot(){
        val group=ImportedStyleGroup(100,0,"Style",0,false,0,0,120,stsIndices=listOf(8,0,-1,12))
        assertEquals(listOf(8,2,3,12),(32..35).map{ImportedStsSelection.slotForMain(group,it)})
        assertEquals(listOf(1,2,3,4),(32..35).map{ImportedStsSelection.slotForMain(group.copy(id=0),it)})
        assertEquals(4,ImportedStsSelection.slotForMain(group.copy(stsIndices=emptyList()),35))
    }
    private fun snapshot(slot:Int,instrument:Int)=ImportedSts("Same name",false,0,instrument,0,0,0,0,0,0,emptyList(),ImportedChannelSettings(),slot)
    private fun index()=ImportedSetIndex(emptyList(),emptyList(),tracks=listOf(
        ImportedTrack("Duplicate",0,0,false,emptyList(),sts=listOf(snapshot(1,10),snapshot(8,20))),
        ImportedTrack("Duplicate",0,0,false,emptyList(),sts=listOf(snapshot(8,30)))))
    @Test fun resolvesByTrackAndOriginalSlotDespiteDuplicateNamesAndDisabledSlots(){
        assertEquals(20,ImportedStsSelection.resolve(index(),0,8).instrument)
        assertEquals(30,ImportedStsSelection.resolve(index(),1,8).instrument)
    }
    @Test fun missingTrackOrSlotCannotSilentlySelectAnotherSnapshot(){
        for((track,slot) in listOf(-1 to 8,2 to 8,0 to 0,0 to -1)){
            try{ImportedStsSelection.resolve(index(),track,slot);fail("Invalid identity accepted")}
            catch(expected:IllegalStateException){assertTrue(expected.message.orEmpty().startsWith("Saved STS"))}
        }
    }
}
