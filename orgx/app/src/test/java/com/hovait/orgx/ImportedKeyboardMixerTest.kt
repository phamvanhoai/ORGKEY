package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedKeyboardMixerTest {
    private val echo=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0))))
    @Test fun musicEchoProcessesSumOfChannelsAndRetainsItsOwnTail(){
        val mixer=ImportedKeyboardMixer();mixer.configureMusic(echo)
        val a=sound(ImportedChannelSettings(volume=50))
        val b=sound(ImportedChannelSettings(volume=25))
        mixer.beginFrame();mixer.add(a,1.0,0.0);mixer.add(b,2.0,0.0);mixer.finishFrame()
        assertEquals(1.0,mixer.left,0.0)
        repeat(479){mixer.beginFrame();mixer.finishFrame();assertEquals(0.0,mixer.left,0.0)}
        mixer.configureMusic(echo.copy(volume=25,pan=100))
        mixer.beginFrame();mixer.finishFrame()
        assertEquals(.5,mixer.left,0.0)
        assertEquals(.25,mixer.musicVolume,0.0)
        assertEquals(0.0,mixer.right,0.0) // Music pan is not a stereo pan.
    }
    @Test fun musicFxCanBeDisabledAndResetWithoutLeavingOldEcho(){
        val mixer=ImportedKeyboardMixer();val sound=sound(ImportedChannelSettings())
        mixer.configureMusic(echo)
        mixer.beginFrame();mixer.add(sound,1.0,0.0);mixer.finishFrame()
        mixer.reset()
        repeat(500){mixer.beginFrame();mixer.finishFrame();assertEquals(0.0,mixer.left,0.0)}
        mixer.configureMusic(echo.copy(fxEnabled=false))
        mixer.beginFrame();mixer.add(sound,1.0,0.0);mixer.finishFrame()
        assertEquals(1.0,mixer.left,0.0)
        repeat(500){mixer.beginFrame();mixer.finishFrame();assertEquals(0.0,mixer.left,0.0)}
    }
    private fun sound(settings:ImportedChannelSettings)=SampledInstrument(listOf(
        InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(1000,2000),null),0,0,looping=false)),48000)
        .withKeyboardSettings(settings,true)
    @Test fun notesAreSummedThenChannelVolumePanAppliedOnce(){
        val sound=sound(ImportedChannelSettings(volume=50,pan=100))
        val mixer=ImportedKeyboardMixer();mixer.beginFrame();mixer.add(sound,1.0,1.0);mixer.add(sound,1.0,1.0);mixer.finishFrame()
        assertEquals(0.0,mixer.left,0.0);assertEquals(1.0,mixer.right,0.0)
    }
    @Test fun echoBusKeepsTailAfterAllKeysAreReleased(){
        val sound=sound(ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0)))))
        val mixer=ImportedKeyboardMixer();mixer.beginFrame();mixer.add(sound,1.0,0.0);mixer.finishFrame()
        repeat(479){mixer.beginFrame();mixer.finishFrame();assertEquals(0.0,mixer.left,0.0)}
        mixer.beginFrame();mixer.finishFrame();assertEquals(.5,mixer.left,0.0)
        mixer.reset();repeat(500){mixer.beginFrame();mixer.finishFrame();assertEquals(0.0,mixer.left,0.0)}
    }
    @Test fun transposeIsAppliedToMelodicKeyboardOnlyAndInvalidKeysStayOutsideRange(){
        val settings=ImportedChannelSettings(transpose=2,octave=1)
        val melodic=sound(settings);assertEquals(74,melodic.keyboardNote(60))
        val drum=sound(settings).withKeyboardSettings(settings,false);assertEquals(60,drum.keyboardNote(60))
        assertTrue(melodic.keyboardNote(120)>127)
    }
}
