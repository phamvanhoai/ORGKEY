package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStsLoaderTest {
    @Test fun singleStsRestoreKeepsChannelFxAndExpressionInsteadOfReloadingSlotDefaults(){
        val file=File.createTempFile("sts-single-state",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val source=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))))))
            val index=ImportedSetIndex(listOf(source),emptyList())
            val original=ImportedSts("Slot",true,0,0,0,0,0,0,0,0,emptyList(),ImportedChannelSettings(),slot=2)
            val fx=ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0))
            val effective=original.copy(pedal=1,record=false,settings=ImportedChannelSettings(volume=37,pan=-12,
                fxEnabled=true,modules=listOf(fx)),effects=listOf(ImportedExpressionEffect.default(2).copy(depth=29)))
            val encoded=ImportedStsModeState.encodeSingle(file,3,effective)
            val restored=ImportedStsModeState.restoreSingle(encoded,file,3,index,original)
            assertEquals(effective,restored)
            val player=ImportedStsLoader.prepare(file,index,restored)
            assertEquals(37,player.sound.keyboardSettings.volume)
            assertEquals(-12,player.sound.keyboardSettings.pan)
            assertEquals(original,ImportedStsModeState.restoreSingle(encoded+"invalid",file,3,index,original))
            val legacy="3:0|"+ImportedStsModeState.encode(file,2,mapOf(1 to 0))
            assertEquals(original,ImportedStsModeState.restoreSingle(legacy,file,3,index,original))
        }finally{file.delete()}
    }
    @Test fun singleStsRestoresExitedModeOnlyForSameTrackInstrumentAndSlot(){
        val file=File.createTempFile("sts-single-restore",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val sample=ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))))
            fun mode(name:String,script:String="",volume:Int=100)=ImportedMode(name,0,-1,List(8){-1},List(8){0},List(8){0},
                settings=ImportedChannelSettings(volume=volume),script=script)
            val source=ImportedInstrument("Voice",100,0,0,listOf(sample),listOf(mode("Default",volume=45),
                mode("Move","on(touch){play();return(true);} on(release){exit;}")))
            val index=ImportedSetIndex(listOf(source),emptyList())
            val original=ImportedSts("STS",true,0,0,0,0,1,0,0,0,emptyList(),ImportedChannelSettings(),slot=2)
            val selected=ImportedStsLoader.prepare(file,index,original)
            val voice=selected.sound.voice(60);voice.next(false);voice.next(true)
            val encoded=ImportedStsModeState.encodeSingle(file,3,selected.currentSnapshot())
            val snapshot=ImportedStsModeState.restoreSingle(encoded,file,3,index,original)
            val restored=ImportedStsLoader.prepare(file,index,snapshot)
            assertEquals(0,snapshot.mode)
            assertEquals(45,restored.sound.keyboardSettings.volume)
            assertEquals(1,ImportedStsModeState.restoreSingle(encoded,file,4,index,original).mode)
            assertEquals(1,ImportedStsModeState.restoreSingle(encoded,file,3,index,original.copy(slot=1)).mode)
            assertEquals(1,ImportedStsModeState.restoreSingle(encoded,file,3,index,original.copy(instrument=1)).mode)
            file.appendBytes(byteArrayOf(0,0))
            assertEquals(1,ImportedStsModeState.restoreSingle(encoded,file,3,index,original).mode)
        }finally{file.delete()}
    }
    @Test fun stsRestoresTheSameDncOverrideAsRenamedVoice(){
        val file=File.createTempFile("sts-dnc",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val sample=ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,2,100,0,0,0,0,0,0,ImportedWave(0,4))))
            val mode=ImportedMode("Mode",0,-1,List(8){-1},List(8){0},List(8){0},
                script="""main(){setting(volume,name:"level",title:"Level",reset:80);} on(touch){play(volume:level);return(true);}""",
                scriptSettings=mapOf("level" to "35"))
            val source=ImportedInstrument("Voice",100,0,0,listOf(sample),listOf(mode),sourceIdentity="instrument:3")
            val alias=ImportedInstrumentLoader.playableSounds(listOf(source)).last()
            val expectedKey=ImportedDncPreferences.key(file,alias)
            val snapshot=ImportedSts("STS",false,0,0,0,0,0,0,0,0,emptyList(),ImportedChannelSettings())
            val index=ImportedSetIndex(listOf(source),emptyList())
            val loaded=ImportedStsLoader.prepare(file,index,snapshot){bank,selected->
                assertEquals(expectedKey,ImportedDncPreferences.key(bank,selected))
                mapOf("level" to "42")
            }.sound
            assertEquals(expectedKey,loaded.scriptSettingsKey)
            assertEquals(42,loaded.scriptSliderValues["level"])
            assertEquals(80,loaded.scriptSliders.single().reset)
        }finally{file.delete()}
    }
    @Test fun selectedModeUsesItsSoundAndInvalidModeDoesNotFallBack(){
        val file=File.createTempFile("sts-mode",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,64))
            fun sound(offset:Long)=ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
                ImportedSection(60,48000,1,1,100,0,0,0,0,0,0,ImportedWave(offset,2))))
            fun mode(sound:Int)=ImportedMode("Mode",sound,-1,List(8){-1},List(8){0},List(8){0})
            val source=ImportedInstrument("Voice",100,0,0,listOf(sound(0),sound(2)),listOf(mode(0),mode(1)))
            val index=ImportedSetIndex(listOf(source),emptyList())
            val snapshot=ImportedSts("STS",false,0,0,0,0,1,0,0,0,emptyList(),ImportedChannelSettings())
            assertEquals(.5,ImportedStsLoader.load(file,index,snapshot).sample(60,0),1e-9)
            assertEquals(.25,ImportedStsLoader.load(file,index,snapshot.copy(mode=-1)).sample(60,0),1e-9)
            try{ImportedStsLoader.load(file,index,snapshot.copy(mode=99));fail("Invalid mode fell back")}
            catch(expected:IllegalArgumentException){assertTrue(expected.message.orEmpty().contains("STS mode unavailable"))}
        }finally{file.delete()}
    }
    @Test fun snapshotCombinesGlobalPitchOnceAndRetainsMixerSettings(){
        val file=File.createTempFile("sts-pitch",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,0,0,32,0,64,0,0))
            val section=ImportedSection(60,48000,1,4,100,0,0,0,0,0,0,ImportedWave(0,8))
            val sound=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(section))))
            val music=ImportedChannelSettings(transpose=2,octave=-1,tune=6000)
            val settings=ImportedChannelSettings(volume=40,pan=-30,transpose=1,octave=1,tune=6000)
            val snapshot=ImportedSts("STS",false,0,0,0,0,0,0,0,0,emptyList(),settings)
            val index=ImportedSetIndex(listOf(sound),emptyList(),musicSettings=music)
            val loaded=ImportedStsLoader.load(file,index,snapshot)
            assertEquals(63,loaded.keyboardNote(60))
            assertEquals(40,loaded.keyboardSettings.volume)
            assertEquals(-30,loaded.keyboardSettings.pan)
            assertEquals(.5,loaded.sample(60,1),1e-9)
            val drums=ImportedStsLoader.load(file,index.copy(instruments=listOf(sound.copy(type=1))),snapshot)
            assertEquals(60,drums.keyboardNote(60))
            assertEquals(.25,drums.sample(60,1),1e-9)
        }finally{file.delete()}
    }
}
