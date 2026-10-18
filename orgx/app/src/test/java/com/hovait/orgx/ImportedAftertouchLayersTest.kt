package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedAftertouchLayersTest {
    @Test fun missingModeReferencesAreRejectedBeforeAnyPcmRead(){
        val source=instrument(99)
        assertFalse(ImportedInstrumentLoader.supported(source))
        assertTrue(ImportedInstrumentLoader.modeReferenceError(source).orEmpty().contains("Aftertouch sound 99"))
        val badMix=instrument(-1).let{it.copy(modes=listOf(it.modes.single().copy(mix=listOf(88))))}
        assertTrue(ImportedInstrumentLoader.modeReferenceError(badMix).orEmpty().contains("Mix 1 sound 88"))
        try{ImportedInstrumentLoader.load(java.io.File("missing-mode-pcm.bin"),source);fail("Missing sound accepted")}
        catch(expected:IllegalArgumentException){assertTrue(expected.message.orEmpty().contains("Aftertouch sound 99"))}
        assertNull(ImportedInstrumentLoader.modeReferenceError(instrument(-1)))
    }
    @Test fun combinedPrimaryAndAftertouchBudgetIsCheckedBeforeReadingPcm(){
        val section=ImportedSection(60,48000,1,1,100,0,0,0,0,0,0,ImportedWave(0,40*1024*1024))
        val sound=ImportedSound("Large",listOf(0,0,100,10,0,0),listOf(section))
        val source=ImportedInstrument("Large mode",100,0,0,listOf(sound,sound),listOf(
            ImportedMode("Mode",0,1,List(8){-1},List(8){0},List(8){0})))
        val missing=java.io.File("nonexistent-aftertouch-budget-test.pcm")
        try{ImportedInstrumentLoader.load(missing,source);fail("Accepted combined 80 MB mode")}
        catch(expected:IllegalArgumentException){assertEquals("Instrument mode exceeds 64 MB PCM budget",expected.message)}
    }
    private fun instrument(alternate:Int)=ImportedInstrument("Voice",100,0,0,
        listOf(ImportedSound("Primary",emptyList(),emptyList()),ImportedSound("Alternate",emptyList(),emptyList()),ImportedSound("Mix",emptyList(),emptyList())),
        listOf(ImportedMode("Mode",0,alternate,listOf(2)+List(7){-1},List(8){0},List(8){0})))
    @Test fun alternateReplacesPrimaryWhileRetainingMixLayer(){
        val source=instrument(1)
        assertEquals(listOf("Primary","Mix"),ImportedInstrumentLoader.layers(source).map{it.sounds.single().name})
        assertEquals(listOf("Alternate","Mix"),ImportedInstrumentLoader.layers(source,true).map{it.sounds.single().name})
        assertEquals(0,source.modes.single().sound)
    }
    @Test fun noAlternateFallsBackAndNegativePrimaryStillAllowsMix(){
        val source=instrument(-1)
        assertEquals(ImportedInstrumentLoader.layers(source),ImportedInstrumentLoader.layers(source,true))
        val mixOnly=source.copy(modes=listOf(source.modes.single().copy(sound=-1)))
        assertEquals(listOf("Mix"),ImportedInstrumentLoader.layers(mixOnly,true).map{it.sounds.single().name})
    }
}
