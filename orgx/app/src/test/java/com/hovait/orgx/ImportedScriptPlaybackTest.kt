package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedScriptPlaybackTest {
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
