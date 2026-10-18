package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStsMemoryTest {
    private fun snapshot()=ImportedSts("STS",true,0,0,0,0,-1,0,0,0,emptyList(),ImportedChannelSettings(),slot=1)
    private fun index(frames:Int,channels:Int):ImportedSetIndex {
        val section=ImportedSection(60,48000,channels,frames,100,0,0,0,0,0,0,ImportedWave(0,frames*channels*2))
        val sound=ImportedInstrument("PCM",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(section))))
        val track=ImportedTrack("Track",0,0,false,emptyList(),sts=listOf(snapshot()))
        return ImportedSetIndex(listOf(sound),emptyList(),listOf(track,track.copy(name="R"),track.copy(name="L")))
    }
    @Test fun countsMonoStereoAndRepeatedPartsAsActuallyAllocated(){
        assertEquals(400L,ImportedStsMemory.required(index(100,1),listOf(snapshot())))
        assertEquals(1000L,ImportedStsMemory.required(index(100,2),listOf(snapshot())))
        assertEquals(1600L,ImportedStsMemory.required(index(100,2),listOf(snapshot(),snapshot())))
    }
    @Test fun oversizedGroupFailsBeforeOpeningPcmFile(){
        val index=index(5_000_000,2)
        assertTrue(ImportedStsMemory.required(index,listOf(snapshot(),snapshot()))>ImportedStsMemory.LIMIT)
        try{ImportedStsBatch.prepareFull(File("missing-sts-memory-test.pcm"),index,1);fail("Oversized group accepted")}
        catch(expected:IllegalArgumentException){assertTrue(expected.message.orEmpty().contains("memory budget"))}
    }
}
