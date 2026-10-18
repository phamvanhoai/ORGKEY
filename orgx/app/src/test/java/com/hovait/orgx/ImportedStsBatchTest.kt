package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStsBatchTest {
    private fun snapshot(name:String)=ImportedSts(name,false,0,0,0,0,0,0,0,0,emptyList(),ImportedChannelSettings(),slot=1)
    @Test fun mainPositionsExcludeRhythmTracksAndKeepOriginalTrackIdentity(){
        val master=ImportedTrack("Master",0,0,false,emptyList(),sts=listOf(snapshot("Master")))
        val rhythm=master.copy(name="Rhythm",group=12)
        val right=master.copy(name="Right",sts=listOf(snapshot("Right")))
        val left=right.copy(name="Left",sts=listOf(snapshot("Left")))
        val selection=ImportedStsBatch.resolve(ImportedSetIndex(emptyList(),emptyList(),listOf(master,rhythm,right,left)),1)
        assertEquals(listOf(1,2),selection.members.map{it.position})
        assertEquals(listOf(2,3),selection.members.map{it.trackIndex})
        assertEquals(listOf("Right","Left"),selection.members.map{it.snapshot.name})
    }
    @Test fun preparesBothVoicesFromDiskAndFailsWholePreparationForMissingSlot(){
        val file=File.createTempFile("sts-batch",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32))
            val sound=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,1,100,0,0,0,0,0,0,ImportedWave(0,2))))))
            val master=ImportedTrack("Master",0,0,false,emptyList(),sts=listOf(snapshot("Master")))
            val index=ImportedSetIndex(listOf(sound),emptyList(),listOf(master,master.copy(name="R"),master.copy(name="L")))
            val prepared=ImportedStsBatch.prepareFull(file,index,1)
            assertEquals(setOf(1,2),prepared.voices.keys)
            assertEquals(listOf(1,2),prepared.keyboardParts().map{it.id})
            assertTrue(prepared.keyboardParts().none{it.enabled}) // snapshot Record=false
            val ranged=index.copy(tracks=index.tracks.map{it.copy(keyAreaFrom=48,keyAreaTo=60,sts=it.sts.map{sts->sts.copy(record=true)})})
            val routed=ImportedStsBatch.prepareFull(file,ranged,1).keyboardParts()
            assertTrue(routed.all{it.enabled && it.fromKey==48 && it.toKey==60})
            prepared.voices.values.forEach{assertEquals(.25,it.sound.sample(60,0),1e-9)}
            try{ImportedStsBatch.prepareFull(file,index.copy(tracks=index.tracks+master.copy(sts=emptyList())),1);fail("Partial batch accepted")}
            catch(expected:IllegalStateException){}
        }finally{file.delete()}
    }
}
