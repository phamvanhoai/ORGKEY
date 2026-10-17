package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSystemTest {
    private val system=ImportedSystem("Quarter tone",0,0,List(24){if(it==12)-.5 else 0.0})
    @Test fun referenceTableChangesOnlyItsTwentyFourKeySlot(){
        val flat=List(24){0.0}
        assertTrue(SamplePitch.referenceStep(60,60,48000,48000,0,system.table)<1)
        assertEquals(SamplePitch.referenceStep(61,60,48000,48000,0,flat),SamplePitch.referenceStep(61,60,48000,48000,0,system.table),0.0)
        assertEquals(SamplePitch.referenceStep(60,60,48000,48000,0,system.table),SamplePitch.referenceStep(84,84,48000,48000,0,system.table),0.0)
    }
    @Test fun tuningChangesMelodicPcmButNotPercussionPcm(){
        val file=File.createTempFile("orgx-system",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,0,0,32,0,64,0,0))
            val section=ImportedSection(60,48000,1,4,100,0,0,0,0,0,0,ImportedWave(0,8))
            val instrument=ImportedInstrument("Sound",100,0,0,listOf(ImportedSound("Primary",listOf(0,0,100,10,0,0),listOf(section))))
            val normal=ImportedInstrumentLoader.load(file,instrument)
            val tuned=ImportedInstrumentLoader.load(file,instrument,system)
            assertTrue(tuned.sample(60,1)<normal.sample(60,1))
            val drums=instrument.copy(type=1)
            assertEquals(ImportedInstrumentLoader.load(file,drums).sample(60,1),ImportedInstrumentLoader.load(file,drums,system).sample(60,1),0.0)
        }finally{file.delete()}
    }
    @Test fun trackCannotSilentlyUseWrongSystem(){
        val group=ImportedStyleGroup(1,0,"Style",32,true,0,1000,120)
        val track=ImportedTrack("Bass",1,0,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)),system=3)
        val index=ImportedSetIndex(emptyList(),listOf(group),listOf(track),listOf(system))
        assertEquals("Track references missing tuning system",ImportedStyleSupport.reason(index,group,true))
    }
    @Test fun localSetSystemReferencesAndTablesAreDecoded(){
        val file=System.getProperty("orgx.legacySet")?.let(::File) ?: return
        val index=ImportedSetInstruments.index(file)
        assertEquals(1,index.systems.size)
        assertEquals(24,index.systems.single().table.size)
        assertTrue(index.tracks.all{it.system in index.systems.indices})
    }
}
