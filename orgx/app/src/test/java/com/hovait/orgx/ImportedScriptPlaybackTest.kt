package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class ImportedScriptPlaybackTest {
    @Test fun layeredVoiceKeepsIncomingVelocityInChildScript(){
        val layer=sound().withModeScript("on(touch) { play(velocity:100); return(true); }")
        val voice=SampledInstrument.combine(listOf(layer,sound())).voice(60,scriptVelocity=50)
        voice.next(false)
        assertEquals(.75,voice.left,1e-12)
    }
    @Test fun scriptVelocityOverridesWithoutMultiplyingInputTwice(){
        val source=sound().withModeScript("on(touch) { play(velocity:velocity); return(true); }")
        val preserved=source.voice(60,scriptVelocity=50);preserved.next(false)
        assertEquals(.25,preserved.left,1e-12)
        val overridden=sound().withModeScript("on(touch) { play(velocity:100); return(true); }").voice(60,scriptVelocity=50)
        overridden.next(false);assertEquals(.5,overridden.left,1e-12)
    }
    @Test fun scriptPitchMoveSurvivesExternalPitchBend(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
        val voice=source.withModeScript("on(touch) { play(pitchmove:1000); return(true); }").voice(60)
        voice.pitchBend(12.0)
        voice.next(false);voice.next(false)
        assertEquals(400.0/32768,voice.left,1e-12)
    }
    @Test fun scriptKeyMoveChangesPlaybackSpeedAndPanChangesStereo(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
        val voice=source.withModeScript("on(touch) { play(key:key,keymove:12,pan:100); return(true); }").voice(60)
        voice.next(false);voice.next(false)
        assertEquals(0.0,voice.left,0.0)
        assertEquals(200.0/32768,voice.right,1e-12)
    }
    private fun original(path:String)=File(requireNotNull(System.getProperty("orgx.referenceScripts")),path).readText()
    @Test fun bundledOctaveScriptsRenderTheirDeclaredMix(){
        val upper=sound().withModeScript(original("Octave/Add Upper Octave.Script")).voice(60)
        upper.next(false)
        // No return(true): original key and script octave both contribute.
        assertEquals(.5,upper.left,1e-12)
        val both=sound().withModeScript(original("Octave/Add Upper & Lower Octave.Script")).voice(60)
        both.next(false)
        assertEquals(.375,both.left,1e-12)
        both.next(true);assertTrue(both.finished)
    }
    @Test fun bundledRelativeLegatoScriptsProduceOppositePitchMovement(){
        fun output(path:String):Double {
            val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
            val voice=source.withModeScript(original(path)).voice(60)
            voice.next(false);voice.next(false)
            return voice.left
        }
        assertTrue(output("Legato/Legato from Lower.Script")<100.0/32768)
        assertTrue(output("Legato/Legato from Upper.Script")>100.0/32768)
    }
    @Test fun lowerLegatoScriptStartsSampleAtPitchOfLowerKey(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
        val script=source.withModeScript("on(touch) { play(connectkey:key-12,connectspeed:50); return(true); }").voice(60)
        script.next(false);script.next(false)
        assertTrue(script.left>0.0)
        assertTrue(script.left<100.0/32768)
    }
    private fun sound()=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(16){8192}),0,16)),1000)
    @Test fun directVoiceUsesScriptVolumeAndSuppressesDefault(){
        val voice=sound().withModeScript("on(touch) { play(volume:50); return(true); }").voice(60)
        voice.next(false)
        assertEquals(.125,voice.left,1e-12)
        voice.next(true)
        assertTrue(voice.finished)
    }
    @Test fun handledEventWithoutPlayIsSilent(){
        val voice=sound().withModeScript("on(touch) { return(true); }").voice(60)
        voice.next(false)
        assertEquals(0.0,voice.left,0.0);assertTrue(voice.finished)
    }
    @Test fun unsupportedScriptKeepsDefaultVoice(){
        val voice=sound().withModeScript("on(touch) { timer(10) { play(); } }").voice(60)
        voice.next(false)
        assertEquals(.25,voice.left,1e-12)
    }
}
