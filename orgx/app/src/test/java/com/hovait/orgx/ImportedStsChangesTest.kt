package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsChangesTest {
    @Test fun modeSetLoadsModeSettingsBeforeSnapshotMasksWhileDirectSelectionKeepsOldSettings(){
        val modes=listOf(ImportedMode("Mode",0,-1,emptyList(),emptyList(),emptyList(),ImportedChannelSettings(volume=55,tune=12)))
        val instrument=ImportedInstrument("Voice",100,0,0,emptyList(),modes)
        val index=ImportedSetIndex(listOf(instrument,instrument),emptyList())
        val old=ImportedSts("Old",false,0,1,0,0,0,0,0,0,emptyList(),ImportedChannelSettings(volume=90,tune=42))
        val incoming=old.copy(settings=ImportedChannelSettings(volume=25,tune=999))
        val mask=List(21){it==11}
        val reset=ImportedStsChanges.transition(index,1,old,incoming,listOf(ImportedStsPlan.Action.Event(1,61,-1,0)),mask)
        assertEquals(55,reset.settings.volume);assertEquals(12,reset.settings.tune)
        val direct=ImportedStsChanges.transition(index,1,old,incoming,listOf(ImportedStsPlan.Action.Event(1,61,0,0)),mask)
        assertEquals(old.settings,direct.settings)
        val volumeMask=mask.mapIndexed{i,v->v || i==13}
        val merged=ImportedStsChanges.transition(index,1,old,incoming,listOf(ImportedStsPlan.Action.Event(1,61,-1,0)),volumeMask)
        assertEquals(25,merged.settings.volume);assertEquals(12,merged.settings.tune)
    }
    @Test fun instrumentResetUsesNewDefaultModeAndMissingModeResetsChannel(){
        val mode=ImportedMode("New",0,-1,emptyList(),emptyList(),emptyList(),ImportedChannelSettings(volume=33))
        val instrument=ImportedInstrument("Voice",100,0,0,emptyList(),listOf(mode))
        val index=ImportedSetIndex(listOf(instrument,instrument),emptyList())
        val old=ImportedSts("Old",false,0,0,0,0,0,0,0,0,emptyList(),ImportedChannelSettings(volume=90))
        val next=old.copy(instrument=1)
        val mask=List(21){it==9}
        val reset=ImportedStsChanges.transition(index,1,old,next,listOf(ImportedStsPlan.Action.Event(1,62,-1,1)),mask)
        assertEquals(1,reset.instrument);assertEquals(33,reset.settings.volume)
        val absent=ImportedStsChanges.transition(index,1,reset,next,listOf(ImportedStsPlan.Action.Event(1,61,-1,9)),List(21){it==11})
        assertEquals(-1,absent.mode);assertEquals(ImportedChannelSettings(),absent.settings)
    }
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
