package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsSelectionTest {
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
