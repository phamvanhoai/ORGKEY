package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ChordDualSettingsTest {
    @Test fun persistedMappingsRestoreTwoKeyRecognitionAndClearingRemovesIt(){
        val library=PlayableChordLibrary.fromTable("M 0 4 7\nm 0 3 7")
        val saved=ChordDualSettings.encode(mapOf(17 to 137,36 to 548))
        val input=KeyboardChordInput()
        input.configureChordMode(2,emptyList(),library,ChordDualSettings.decode(saved))
        input.press(48,library);input.press(52,library)
        assertEquals("C m",library[input.chord!!].name)
        val cleared=ChordDualSettings.decode(ChordDualSettings.encode(ChordDualSettings.decode(saved)-17))
        input.configureChordMode(2,emptyList(),library,cleared)
        input.reset();input.press(48,library);input.press(52,library)
        assertNull(input.chord)
        assertEquals(mapOf(36 to 548),cleared)
    }
    @Test fun ignoresCorruptPreferenceEntriesAndKeepsValidPairs(){
        assertEquals(mapOf(17 to 145),ChordDualSettings.decode("junk;1=145;17=-1;17=145;4096=145;36=0;3=1=2"))
        for(entry in listOf(1 to 145,17 to 4096,17 to 0)){
            try{ChordDualSettings.encode(mapOf(entry));fail("Invalid setting accepted")}
            catch(expected:IllegalArgumentException){}
        }
    }
}
