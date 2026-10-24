package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStsBatchTest {
    @Test fun effectivePartStateRestoresMaskedSelectionAndChannelLevels(){
        val file=File.createTempFile("sts-effective",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val sound=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))))))
            val original=snapshot("Slot").copy(instrument=1)
            val track=ImportedTrack("Master",0,0,false,emptyList(),sts=listOf(original))
            val index=ImportedSetIndex(listOf(sound,sound),emptyList(),listOf(track,track.copy(name="Right")))
            val fx=ImportedFxModule(6,true,true,listOf(50,0,0,0,1,0,0,0))
            val effect=ImportedExpressionEffect.default(2).copy(enabled=true,speed=36,depth=12)
            val effective=original.copy(instrument=0,record=true,pedal=1,
                settings=ImportedChannelSettings(volume=43,pan=-28,transpose=3,octave=-1,fxEnabled=true,modules=listOf(fx)),
                effects=listOf(effect),sourceVersion="1.2.8")
            val encoded=ImportedStsModeState.encodeParts(file,1,mapOf(1 to effective))
            val parts=ImportedStsModeState.decodeParts(encoded,file,1,index)
            val restored=ImportedStsBatch.prepareFull(file,index,1,restoredParts=parts)
            assertEquals(effective,restored.voices.getValue(1).snapshot)
            assertEquals(43,restored.keyboardParts().single().sound.keyboardSettings.volume)
            assertEquals(listOf(fx),restored.voices.getValue(1).snapshot.settings.modules)
            assertEquals(listOf(effect),restored.voices.getValue(1).snapshot.effects)
            assertEquals("1.2.8",restored.voices.getValue(1).snapshot.sourceVersion)
            val previousFormat=encoded.substringBeforeLast('~')
            assertEquals(listOf(effect),ImportedStsModeState.decodeParts(previousFormat,file,1,index).getValue(1).effects)
            assertTrue(ImportedStsModeState.decodeParts(previousFormat+"~invalid-version",file,1,index).isEmpty())
            val legacy=encoded.substringBefore('~')
            assertEquals(43,ImportedStsModeState.decodeParts(legacy,file,1,index).getValue(1).settings.volume)
            assertTrue(ImportedStsModeState.decodeParts(encoded.replace("~1~","~2~"),file,1,index).isEmpty())
            assertTrue(ImportedStsModeState.decodeParts(encoded,file,2,index).isEmpty())
            assertTrue(ImportedStsModeState.decodeParts(encoded+";"+encoded.substringAfter('|'),file,1,index).isEmpty())
            file.appendBytes(byteArrayOf(0))
            assertTrue(ImportedStsModeState.decodeParts(encoded,file,1,index).isEmpty())
        }finally{file.delete()}
    }
    @Test fun firstMasterRequestHonorsSetControlAgainstStoredTrackState(){
        val file=File.createTempFile("sts-initial-mask",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val sound=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))))))
            val incoming=snapshot("Incoming").copy(instrument=1,record=false,pedal=1,
                settings=ImportedChannelSettings(volume=90,pan=70,transpose=5,octave=2))
            val master=ImportedTrack("Master",0,0,false,emptyList(),sts=listOf(incoming))
            val right=master.copy(name="Right",instrument=0,record=true,pedal=0,volume=40,pan=-25,
                settings=ImportedChannelSettings(volume=40,pan=-25,transpose=2,octave=-1))
            val index=ImportedSetIndex(listOf(sound,sound),emptyList(),listOf(master,right),
                musicSettings=ImportedChannelSettings(volume=65,pan=-10))
            fun request(mask:List<Boolean>)=(ImportedStsRequest.prepare(file,index,0,1,changes=mask) as ImportedStsRequest.Prepared.Group).value
            val kept=request(List(21){false})
            val prior=kept.voices.getValue(1).snapshot
            assertEquals(0,prior.instrument);assertTrue(prior.record);assertEquals(0,prior.pedal)
            assertEquals(40,prior.settings.volume);assertEquals(-25,prior.settings.pan)
            assertEquals(2,prior.settings.transpose);assertEquals(-1,prior.settings.octave)
            assertEquals(65,kept.master!!.music.volume)
            val selected=request(List(21){it in listOf(2,8,10,13)})
            val changed=selected.voices.getValue(1).snapshot
            assertEquals(0,changed.instrument);assertFalse(changed.record);assertEquals(1,changed.pedal)
            assertEquals(90,changed.settings.volume);assertEquals(-25,changed.settings.pan)
            assertEquals(90,selected.master!!.music.volume)
            val replaced=request(List(21){it==9}).voices.getValue(1).snapshot
            assertEquals(1,replaced.instrument)
            assertEquals(0,replaced.settings.pan) // Instrument replacement loads its default mode first.
            assertEquals(40,index.tracks[1].volume);assertEquals(90,incoming.settings.volume)
        }finally{file.delete()}
    }
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
