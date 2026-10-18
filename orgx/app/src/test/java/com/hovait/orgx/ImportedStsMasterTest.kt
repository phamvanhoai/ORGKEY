package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsMasterTest {
    private val snapshot=ImportedSts("Master",false,0,0,1,0,0,142,0,1,List(12){145},
        ImportedChannelSettings(volume=45,pan=20,tune=999,transpose=3,octave=-1,releasePercent=60))
    private val systems=listOf(ImportedSystem("Original",12,1,List(24){0.0}),ImportedSystem("Scale",4,2,List(24){1.0}))
    private val index=ImportedSetIndex(emptyList(),emptyList(),systems=systems,musicSettings=ImportedChannelSettings(tune=123))
    @Test fun globalFlagsPreserveUnselectedLiveValuesAndDoNotModifyTheIndex(){
        val prepared=ImportedStsMaster.prepare(index,snapshot,List(21){it in listOf(0,1,2,4,20)})
        assertEquals(142,prepared.tempo)
        assertEquals(snapshot,prepared.chord)
        assertEquals(45,prepared.music.volume);assertEquals(0,prepared.music.pan)
        assertEquals(3,prepared.music.transpose);assertEquals(0,prepared.music.octave)
        assertEquals(123,prepared.music.tune)
        assertEquals("Scale",prepared.systems[0].name)
        assertEquals(systems[1].table,prepared.systems[0].table)
        assertEquals(12,prepared.systems[0].startKey)
        assertEquals(1,prepared.systems[0].selectedKeys)
        assertEquals("Original",index.systems[0].name)
    }
    @Test fun defaultMaskOnlyChangesGlobalFxAndLeavesTempoChordAndSystemAlone(){
        val prepared=ImportedStsMaster.prepare(index,snapshot,ImportedStsPlan.defaultChanges)
        assertNull(prepared.tempo);assertNull(prepared.chord)
        assertEquals(index.musicSettings,prepared.music)
        assertEquals(index.systems,prepared.systems)
        val invalid=ImportedStsMaster.prepare(index,snapshot.copy(system=99),List(21){it==1})
        assertEquals(index.systems,invalid.systems)
    }
}
