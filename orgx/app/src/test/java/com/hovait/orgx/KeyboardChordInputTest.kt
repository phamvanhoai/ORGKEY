package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class KeyboardChordInputTest {
    private val library=PlayableChordLibrary.fromTable("M 0 4 7\nm 0 3 7\n7 0 4 7 10\ndim 0 3 6\n6 0 4 7 9\nm7 0 3 7 10")
    private fun play(vararg notes:Int)=KeyboardChordInput().also{input->notes.forEach{input.press(it,library)}}
    @Test fun recognizesMajorMinorAndSeventhUsingLibraryIndices() {
        assertEquals("C M",library[play(60,64,67).chord!!].name)
        assertEquals("C m",library[play(60,63,67).chord!!].name)
        assertEquals("G 7",library[play(55,59,62,65).chord!!].name)
    }
    @Test fun inversionAndOctaveDoublingMatchSamePitchClasses() {
        assertEquals("C M",library[play(64,67,72).chord!!].name)
        assertEquals("C M",library[play(48,60,64,67,72).chord!!].name)
    }
    @Test fun ambiguousMaskPrefersBassRoot() {
        assertEquals("C 6",library[play(48,52,55,57).chord!!].name)
        assertEquals("A m7",library[play(45,48,52,55).chord!!].name)
    }
    @Test fun releaseAndUnknownClusterKeepLastMatch() {
        val input=play(60,64,67);val chord=input.chord
        assertTrue(input.release(60));input.release(64);input.release(67)
        assertEquals(chord,input.chord)
        listOf(60,61,62).forEach{input.press(it,library)}
        assertEquals(chord,input.chord)
    }
    @Test fun incompleteInputDoesNotGuessAndResetClearsHeldKeys() {
        val input=play(60,64)
        assertNull(input.chord)
        input.reset();input.press(67,library)
        assertNull(input.chord)
        assertFalse(input.release(60))
        input.press(60,library);input.press(64,library)
        assertNotNull(input.chord)
        input.reset();assertNull(input.chord)
    }
}
