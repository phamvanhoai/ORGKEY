package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedAftertouchLayersTest {
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
