package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedChannelSettingsTest {
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
