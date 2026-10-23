package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class KeyboardChordInputTest {
    @Test fun synchroStartsAtThresholdAndStopsAfterLastRecognisedChordIsReleased(){
        val input=KeyboardChordInput();val sync=ChordSynchro()
        input.configureSingleFinger(true,List(12){145},library)
        fun request(running:Boolean)=sync.update(true,true,input.pressedChord!=null,input.heldKeyCount,3,running)
        input.press(60,library);assertNull(request(false))
        input.press(64,library);assertNull(request(false))
        input.press(67,library);assertEquals(true,request(false))
        input.release(60);assertNull(request(true))
        input.release(64);assertNull(request(true))
        input.release(67);assertEquals(false,request(true))
        assertNotNull(input.chord);assertNull(input.pressedChord)
        assertNull(request(false)) // Remembered chord cannot restart the style.
    }
    @Test fun synchroStopDoesNotStopManualPlaybackBeforeAChordWasHeld(){
        val sync=ChordSynchro()
        assertNull(sync.update(false,true,false,0,0,true))
        assertNull(sync.update(false,true,true,3,0,true))
        assertEquals(false,sync.update(false,true,false,0,0,true))
        assertNull(sync.update(true,false,true,3,0,true))
        assertNull(sync.update(true,false,false,0,0,true))
    }
    @Test fun singleFingerUsesOriginalContainedChordFallbackAndTableOrder(){
        val input=KeyboardChordInput()
        input.configureSingleFinger(true,List(12){145 or (1 shl 1)},library)
        input.press(60,library)
        assertEquals("C M",library[input.chord!!].name)
        input.configureSingleFinger(true,List(12){657},library)
        input.reset();input.press(57,library)
        assertEquals("C 6",library[input.chord!!].name)
    }
    @Test fun stsSingleFingerUsesCustomTableAndLowestHeldMidiNote(){
        val input=KeyboardChordInput()
        val masks=MutableList(12){145} // C major
        masks[2]=(1 shl 2) or (1 shl 5) or (1 shl 9) // D minor
        input.configureSingleFinger(true,masks,library)
        masks[2]=0 // Configuration must retain its own snapshot.
        input.press(62,library)
        assertEquals("D m",library[input.chord!!].name)
        assertEquals(5,input.octaveMove)
        input.press(48,library)
        assertEquals("C M",library[input.chord!!].name)
        input.release(48)
        assertEquals("D m",library[input.chord!!].name)
        input.release(62)
        assertEquals("D m",library[input.chord!!].name)
        assertEquals(5,input.octaveMove)
        input.press(74,library)
        assertEquals("D m",library[input.chord!!].name)
        assertEquals(6,input.octaveMove)
        input.reset();assertEquals(0,input.octaveMove)
    }
    @Test fun switchingStsReevaluatesHeldKeyAndCanReturnToFingeredInput(){
        val input=KeyboardChordInput()
        input.press(60,library);assertNull(input.chord)
        input.configureSingleFinger(true,List(12){137},library) // C minor
        assertEquals("C m",library[input.chord!!].name)
        input.configureSingleFinger(false,emptyList(),library)
        input.reset();input.press(60,library)
        assertNull(input.chord)
        input.press(64,library);input.press(67,library)
        assertEquals("C M",library[input.chord!!].name)
    }
    @Test fun malformedSingleFingerSnapshotDoesNotReplaceActiveConfiguration(){
        val input=KeyboardChordInput()
        input.configureSingleFinger(true,List(12){145},library)
        for(masks in listOf(emptyList(),List(12){4096},List(12){-1})){
            try{input.configureSingleFinger(true,masks,library);fail("Invalid table accepted")}
            catch(expected:IllegalArgumentException){}
        }
        input.press(60,library)
        assertEquals("C M",library[input.chord!!].name)
    }
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
    @Test fun ambiguousMaskUsesOriginalTableOrder() {
        assertEquals("C 6",library[play(48,52,55,57).chord!!].name)
        assertEquals("C 6",library[play(45,48,52,55).chord!!].name)
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
