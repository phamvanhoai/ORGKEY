package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceChordInputTest {
    private val library=PlayableChordLibrary.fromTable("M 0 4 7\nm 0 3 7\n7 0 4 7 10")
    @Test fun allSevenOctaveShortcutsFollowOriginalAscendingKeyScan(){
        val cases=listOf(listOf(48,55,60) to 145,listOf(50,57,62) to 548,
            listOf(52,59,64) to 2192,listOf(48,53,65) to 289,
            listOf(50,55,67) to 2180,listOf(52,57,69) to 529,listOf(53,59,71) to 2084)
        for((notes,expected) in cases){
            assertEquals(expected,ReferenceChordInput.mask(notes))
            assertEquals(expected,ReferenceChordInput.mask(notes.reversed()))
        }
        assertEquals(129,ReferenceChordInput.mask(listOf(48,55)))
        assertEquals(129,ReferenceChordInput.mask(listOf(48,48,55)))
    }
    @Test fun keyboardRecognizesOctaveShortcutAndDoesNotRequireThreePitchClasses(){
        val input=KeyboardChordInput()
        listOf(60,48,55).forEach{input.press(it,library)}
        assertEquals("C M",library[input.chord!!].name)
    }
    @Test fun twoKeyModeUsesOnlyConfiguredAndRecognizableDualMappings(){
        val input=KeyboardChordInput()
        val mappings=mutableMapOf(17 to 137) // C + E selects C minor.
        input.configureChordMode(2,emptyList(),library,mappings)
        mappings[17]=0
        input.press(48,library);input.press(52,library)
        assertEquals("C m",library[input.chord!!].name)
        input.configureChordMode(2,emptyList(),library,mapOf(17 to 3))
        input.reset();input.press(48,library);input.press(52,library)
        assertNull(input.chord)
        input.configureChordMode(2,emptyList(),library)
        input.press(55,library)
        assertEquals("C M",library[input.chord!!].name)
    }
    @Test fun releasingNotesReevaluatesRemainingRecognizableChord(){
        val input=KeyboardChordInput()
        listOf(48,52,55,58).forEach{input.press(it,library)}
        assertEquals("C 7",library[input.chord!!].name)
        input.release(58)
        assertEquals("C M",library[input.chord!!].name)
        input.release(48);input.release(52);input.release(55)
        assertEquals("C M",library[input.chord!!].name)
    }
}
