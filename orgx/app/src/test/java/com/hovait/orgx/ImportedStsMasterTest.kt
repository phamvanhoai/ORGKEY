package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsMasterTest {
    @Test fun persistenceCapturesLiveTempoAndChordWhenLatestApplyPlanPreservedThem(){
        val plan=ImportedStsMaster.Prepared(ImportedChannelSettings(volume=61),systems,null,null)
        val saved=ImportedStsMaster.capture(plan,index,snapshot,null,173,2,List(12){273})
        assertEquals(173,saved.tempo);assertEquals(2,saved.chord!!.chordType)
        assertEquals(List(12){273},saved.chord!!.chordSingle)
        assertEquals(61,saved.music.volume);assertEquals(systems,saved.systems)
        assertNull(plan.tempo);assertNull(plan.chord)
        val initial=ImportedStsMaster.capture(null,index,snapshot,ImportedChannelSettings(volume=77),99,0,List(12){145})
        assertEquals(77,initial.music.volume);assertEquals(99,initial.tempo)
        assertEquals(0,initial.chord!!.chordType)
    }
    @Test fun persistedMasterKeepsDspTuningTempoAndChordAndRejectsWrongSet(){
        val file=java.io.File.createTempFile("sts-master",".set")
        try{
            file.writeBytes(byteArrayOf(1,2,3))
            val bank=index.copy(tracks=listOf(ImportedTrack("Master",0,0,false,emptyList(),sts=listOf(snapshot.copy(slot=1)))))
            val music=ImportedChannelSettings(volume=73,pan=-21,fxEnabled=true,modules=listOf(
                ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0))))
            val state=ImportedStsMaster.Prepared(music,systems.map{it.copy(table=List(24){.25})},142,snapshot.copy(slot=1))
            val encoded=ImportedStsMaster.encode(file,1,state)
            assertEquals(state,ImportedStsMaster.decode(encoded,file,1,bank))
            assertNull(ImportedStsMaster.decode(encoded,file,2,bank))
            assertNull(ImportedStsMaster.decode(encoded+"!",file,1,bank))
            assertNull(ImportedStsMaster.decode(encoded,file,1,bank.copy(systems=emptyList())))
            file.appendBytes(byteArrayOf(4))
            assertNull(ImportedStsMaster.decode(encoded,file,1,bank))
        }finally{file.delete()}
    }
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
