package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedChannelSettingsTest {
    @Test fun styleUsesMusicTransposeButRhythmOctave(){
        val track=ImportedTrack("Bass",1,0,false,emptyList(),settings=ImportedChannelSettings(transpose=1,octave=1))
        val index=ImportedSetIndex(emptyList(),emptyList(),musicSettings=ImportedChannelSettings(transpose=2,octave=4),
            rhythmSettings=ImportedChannelSettings(octave=-1))
        val settings=ImportedStyleSupport.channelSettings(index,track)
        assertEquals(63,ImportedStyleSupport.shiftedNote(60,track.copy(settings=settings),true))
        assertEquals(60,ImportedStyleSupport.shiftedNote(60,track.copy(settings=settings),false))
    }
    @Test fun releaseProductIsRoundedOnlyAfterAllThreeFactors(){
        val channel=ImportedChannelSettings(releasePercent=33)
        val music=ImportedChannelSettings(releasePercent=150)
        assertEquals(4,ImportedMusicParameters.release(9,channel,music))
        assertEquals(100,ImportedMusicParameters.release(100,music,music))
    }
    @Test fun keyboardGlobalPitchAndReleaseReachRawSoundAndModeOnlyOnce(){
        val file=File.createTempFile("orgx-music",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,0,0,32,0,64,0,0))
            val region=ImportedSection(60,48000,1,4,100,0,0,0,0,0,0,ImportedWave(0,8))
            val raw=ImportedInstrument("Sound",100,0,0,listOf(ImportedSound("Primary",listOf(0,0,100,9,0,0),listOf(region))))
            val channel=ImportedChannelSettings(tune=6000,transpose=1,octave=1,releasePercent=33)
            val music=ImportedChannelSettings(tune=6000,transpose=2,octave=-1,releasePercent=150)
            val mode=raw.copy(modes=listOf(ImportedMode("Mode",0,0,List(8){-1},List(8){0},List(8){0},channel)))
            val loaded=ImportedInstrumentLoader.load(file,mode,musicSettings=music)
            assertEquals(63,loaded.keyboardNote(60));assertEquals(4,loaded.envelope?.release)
            assertEquals(.5,loaded.sample(60,1),1e-9)
            val plain=ImportedInstrumentLoader.load(file,raw,musicSettings=music.copy(tune=12000))
            assertEquals(50,plain.keyboardNote(60));assertEquals(13,plain.envelope?.release)
            assertEquals(.5,plain.sample(60,1),1e-9)
            val drums=ImportedInstrumentLoader.load(file,mode.copy(type=1),musicSettings=music)
            assertEquals(60,drums.keyboardNote(60));assertEquals(.25,drums.sample(60,1),1e-9)
            assertEquals(4,drums.envelope?.release)
        }finally{file.delete()}
    }
    @Test fun transposeAndOctaveApplyToMelodicOnly(){
        val track=ImportedTrack("Bass",1,0,false,emptyList(),settings=ImportedChannelSettings(transpose=-2,octave=1))
        assertEquals(70,ImportedStyleSupport.shiftedNote(60,track,true))
        assertEquals(60,ImportedStyleSupport.shiftedNote(60,track,false))
        val outside=track.copy(settings=ImportedChannelSettings(octave=10))
        assertTrue(ImportedStyleSupport.shiftedNote(60,outside,true)>127)
    }
    @Test fun tuneChangesSampleStepAndReleaseScaleIsApplied(){
        val file=File.createTempFile("orgx-channel",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,0,0,32,0,64,0,0))
            val region=ImportedSection(60,48000,1,4,100,0,0,0,0,0,0,ImportedWave(0,8))
            val instrument=ImportedInstrument("Sound",100,0,0,listOf(ImportedSound("Primary",listOf(0,0,100,10,0,0),listOf(region))))
            val settings=ImportedChannelSettings(tune=12000,releasePercent=50)
            val loaded=ImportedInstrumentLoader.load(file,instrument,settings=settings)
            assertEquals(5,loaded.envelope?.release)
            assertEquals(.5,loaded.sample(60,1),1e-9)
            val percussion=ImportedInstrumentLoader.load(file,instrument.copy(type=1),settings=settings)
            assertEquals(.25,percussion.sample(60,1),1e-9)
        }finally{file.delete()}
    }
}
