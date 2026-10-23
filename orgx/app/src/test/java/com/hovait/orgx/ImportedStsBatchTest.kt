package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStsBatchTest {
    @Test fun maskedStsKeepsExitedModeAndRejectsPreparationFromOlderMode(){
        val file=File.createTempFile("sts-exit-state",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val sample=ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))))
            fun mode(name:String,script:String="",volume:Int=100)=ImportedMode(name,0,-1,List(8){-1},List(8){0},List(8){0},
                settings=ImportedChannelSettings(volume=volume),script=script)
            val instrument=ImportedInstrument("Voice",100,0,0,listOf(sample),listOf(mode("Default",volume=45),
                mode("Move","on(touch){play();return(true);} on(release){exit;}")))
            val sts=snapshot("STS").copy(mode=1,record=true)
            val master=ImportedTrack("Master",0,0,false,emptyList(),sts=listOf(sts))
            val index=ImportedSetIndex(listOf(instrument),emptyList(),listOf(master,master.copy(name="Right"),master.copy(name="Left")))
            val current=ImportedStsBatch.prepareFull(file,index,1)
            val before=ImportedStsRequest.prepare(file,index,0,1,current,List(21){false}) as ImportedStsRequest.Prepared.Group
            assertTrue(before.value.basisIsCurrent(current))
            val right=current.voices.getValue(1).sound.voice(60)
            right.next(false);right.next(true)
            assertFalse(before.value.basisIsCurrent(current))
            assertEquals(0,current.currentSnapshots().getValue(1).mode)
            assertEquals(45,current.currentSnapshots().getValue(1).settings.volume)
            assertEquals(1,current.currentSnapshots().getValue(2).mode)
            val kept=ImportedStsRequest.prepare(file,index,0,1,current,List(21){false}) as ImportedStsRequest.Prepared.Group
            assertTrue(kept.value.basisIsCurrent(current))
            assertEquals(0,kept.value.voices.getValue(1).snapshot.mode)
            assertEquals(45,kept.value.voices.getValue(1).sound.keyboardSettings.volume)
            assertEquals(1,kept.value.voices.getValue(2).snapshot.mode)
            val saved=ImportedStsModeState.encode(file,1,current.currentSnapshots().mapValues{it.value.mode})
            val restoredModes=ImportedStsModeState.decode(saved,file,1)
            val restored=ImportedStsBatch.prepareFull(file,index,1,restoredModes=restoredModes)
            assertEquals(0,restored.voices.getValue(1).snapshot.mode)
            assertEquals(45,restored.voices.getValue(1).sound.keyboardSettings.volume)
            assertEquals(1,restored.voices.getValue(2).snapshot.mode)
            assertEquals(1,restored.selection.members.first().snapshot.mode) // imported STS remains intact
            assertTrue(ImportedStsModeState.decode(saved,file,2).isEmpty())
            assertTrue(ImportedStsModeState.decode(saved+",1=1",file,1).isEmpty())
            val invalid=ImportedStsBatch.prepareFull(file,index,1,restoredModes=mapOf(1 to 99))
            assertEquals(1,invalid.voices.getValue(1).snapshot.mode)
            file.appendBytes(byteArrayOf(0,0))
            assertTrue(ImportedStsModeState.decode(saved,file,1).isEmpty())
        }finally{file.delete()}
    }
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
            val groupRequest=ImportedStsRequest.prepare(file,index,0,1)
            assertTrue(groupRequest is ImportedStsRequest.Prepared.Group)
            assertEquals(2,(groupRequest as ImportedStsRequest.Prepared.Group).value.voices.size)
            assertTrue(ImportedStsRequest.prepare(file,index,1,1) is ImportedStsRequest.Prepared.Single)
            val nextRequest=ImportedStsRequest.prepare(file,index,0,1,prepared) as ImportedStsRequest.Prepared.Group
            assertSame(prepared,nextRequest.previous)
            assertSame(index,nextRequest.value.index)
            assertNotNull(nextRequest.value.master)
            assertEquals(setOf(1,2),prepared.voices.keys)
            assertEquals(listOf(1,2),prepared.keyboardParts().map{it.id})
            assertTrue(prepared.keyboardParts().none{it.enabled}) // snapshot Record=false
            val ranged=index.copy(tracks=index.tracks.map{it.copy(pedalMode=-1,keyAreaFrom=48,keyAreaTo=60,sts=it.sts.map{sts->sts.copy(record=true)})})
            val routed=ImportedStsBatch.prepareFull(file,ranged,1).keyboardParts()
            assertTrue(routed.all{it.enabled && it.pedalMode==-1 && it.fromKey==48 && it.toKey==60})
            val current=mapOf(1 to snapshot("Current").copy(record=true,settings=ImportedChannelSettings(volume=40)),
                2 to snapshot("Current").copy(record=true,settings=ImportedChannelSettings(volume=60)))
            val masked=ImportedStsBatch.prepareMasked(file,index,1,current,List(21){false})
            assertEquals(listOf(40,60),masked.keyboardParts().map{it.sound.keyboardSettings.volume})
            assertTrue(masked.keyboardParts().all{it.enabled})
            val customRequest=ImportedStsRequest.prepare(file,index,0,1,prepared,List(21){false}) as ImportedStsRequest.Prepared.Group
            assertTrue(customRequest.value.keyboardParts().none{it.enabled}) // current prepared snapshot is Record=false
            val currentEnabled=prepared.copy(voices=prepared.voices.mapValues{(_,voice)->voice.copy(snapshot=voice.snapshot.copy(record=true))})
            val keepRecord=ImportedStsRequest.prepare(file,index,0,1,currentEnabled,List(21){false}) as ImportedStsRequest.Prepared.Group
            assertTrue(keepRecord.value.keyboardParts().all{it.enabled})
            val replaceRecord=ImportedStsRequest.prepare(file,index,0,1,currentEnabled,List(21){it==8}) as ImportedStsRequest.Prepared.Group
            assertTrue(replaceRecord.value.keyboardParts().none{it.enabled})
            val balanced=ImportedStsBatch.prepareFull(file,index.copy(musicSettings=ImportedChannelSettings(pan=-50)),1)
            assertTrue(balanced.keyboardParts().all{it.inputGain==.5})
            val instrumentIndex=index.copy(instruments=listOf(sound,sound),tracks=index.tracks.map{t->t.copy(sts=t.sts.map{it.copy(instrument=1)})})
            val latest=current.mapValues{it.value.copy(instrument=1)}
            val sameInstrument=ImportedStsBatch.prepareMasked(file,instrumentIndex,1,latest,ImportedStsPlan.defaultChanges)
            assertTrue(sameInstrument.selection.actions.filterIsInstance<ImportedStsPlan.Action.Event>().none{it.type==62})
            prepared.voices.values.forEach{assertEquals(.25,it.sound.sample(60,0),1e-9)}
            try{ImportedStsBatch.prepareFull(file,index.copy(tracks=index.tracks+master.copy(sts=emptyList())),1);fail("Partial batch accepted")}
            catch(expected:IllegalStateException){}
        }finally{file.delete()}
    }
}
