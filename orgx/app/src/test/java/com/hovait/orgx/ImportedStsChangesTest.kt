package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsChangesTest {
    @Test fun eachChannelFlagChangesOnlyItsDocumentedFieldAndNeverTune(){
        val old=ImportedChannelSettings(90,-20,123,2,1,75)
        val next=ImportedChannelSettings(50,30,999,-3,-1,40,true,listOf(ImportedFxModule(20,true,false,List(8){0})))
        val expected=listOf(old.copy(volume=50),old.copy(pan=30),old.copy(transpose=-3),old.copy(octave=-1),
            old.copy(releasePercent=40),old.copy(fxEnabled=true,modules=next.modules))
        for(flag in 13..18){
            val mask=List(21){it==flag}
            assertEquals(expected[flag-13],ImportedStsChanges.channel(old,next,mask))
        }
        assertEquals(old,ImportedStsChanges.channel(old,next,List(21){false}))
        assertEquals(123,ImportedStsChanges.channel(old,next,List(21){true}).tune)
    }
    @Test fun trackFlagsKeepUnselectedStateIncludingChordAndMidiRouting(){
        val old=ImportedSts("Old",false,4,1,2,0,3,7,8,0,emptyList(),ImportedChannelSettings())
        val next=old.copy(name="New",record=true,instrument=7,system=9,pedal=1,mode=5,midiChannel=8,chordType=1,chordSingle=List(12){145})
        val merged=ImportedStsChanges.track(old,next,List(21){it in 8..12})
        assertEquals(7,merged.instrument);assertEquals(9,merged.system)
        assertTrue(merged.record);assertEquals(1,merged.pedal);assertEquals(5,merged.mode)
        assertEquals(4,merged.midiChannel);assertEquals(0,merged.chordType)
    }
}
