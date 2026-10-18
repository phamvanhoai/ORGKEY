package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedKeyboardPartsTest {
    private fun sound(volume:Int)=SampledInstrument(listOf(InstrumentRegion(60,
        SamplePcm(1000,1,shortArrayOf(8192,8192)),0,2)),1000)
        .withKeyboardSettings(ImportedChannelSettings(volume=volume),true)
    private fun frame(parts:ImportedKeyboardParts,mixer:ImportedKeyboardMixer):Double {
        mixer.beginFrame();parts.renderInto(mixer);mixer.finishFrame();return mixer.left
    }
    @Test fun mixesIndependentPartGainsAndSustainWithoutMultiplyingMasterBus(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100),true),ImportedKeyboardParts.Part(2,sound(50))))
            parts.noteOn(60,1.0)
            assertEquals(.375,frame(parts,mixer),1e-9)
            parts.noteOff(60)
            assertEquals(.25,frame(parts,mixer),1e-9)
            assertEquals(1,parts.activeNotes)
            parts.pedal(1,false)
            assertEquals(0.0,frame(parts,mixer),0.0)
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun replacementPreservesLiveVoiceAndInvalidBatchLeavesOldParts(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100))))
            parts.noteOn(60,1.0)
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(50))))
            assertEquals(.25,frame(parts,mixer),0.0)
            try{parts.replace(listOf(ImportedKeyboardParts.Part(5,sound(100))));fail("Invalid part accepted")}
            catch(expected:IllegalArgumentException){}
            parts.noteOn(60,1.0)
            assertEquals(.375,frame(parts,mixer),0.0)
            parts.reset();assertEquals(0.0,frame(parts,mixer),0.0)
        }finally{mixer.close()}
    }
}
