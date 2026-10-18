package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStsLoaderTest {
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
