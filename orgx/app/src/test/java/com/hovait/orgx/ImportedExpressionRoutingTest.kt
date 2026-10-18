package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedExpressionRoutingTest {
    @Test fun routesByOriginalTrackAndGroupMetadataRatherThanInstrumentType(){
        val master=ImportedTrack("Master",0,0,false,emptyList())
        val track=ImportedTrack("Voice",1,0,false,emptyList())
        val group=ImportedStyleGroup(1,0,"Group",32,true,0,1000,120,type=0)
        fun allowed(kind:Int,groupType:Int):Boolean {
            val candidate=track.copy(kind=kind)
            return ImportedStyleSupport.expressionAllowed(
                ImportedSetIndex(emptyList(),listOf(group.copy(type=groupType)),listOf(master,candidate)),candidate)
        }
        for(kind in 0..3){
            assertFalse(allowed(kind,1))
            assertEquals(kind!=3,allowed(kind,0))
        }
        val index=ImportedSetIndex(emptyList(),listOf(group),listOf(master,track))
        assertFalse(ImportedStyleSupport.expressionAllowed(index,master))
        assertFalse(ImportedStyleSupport.expressionAllowed(index,track.copy(group=99)))
    }
}
