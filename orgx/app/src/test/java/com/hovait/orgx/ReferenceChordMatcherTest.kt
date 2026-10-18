package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceChordMatcherTest {
    private val major=PlayableChord("C",listOf(48,52,55))
    private val seventh=PlayableChord("C7",listOf(48,52,55,58))
    @Test fun extraInputNotesPermitContainedChordsAndRichestMatchWins(){
        val input=145 or (1 shl 10) or (1 shl 1)
        assertEquals(1,ReferenceChordMatcher.find(input,listOf(major,seventh)))
        assertEquals(0,ReferenceChordMatcher.find(input,listOf(major)))
    }
    @Test fun tiesKeepOriginalTableOrderEvenWhenBassSuggestsAnotherRoot(){
        val sixth=PlayableChord("C6",listOf(48,52,55,57))
        val minorSeventh=PlayableChord("Am7",listOf(45,48,52,55))
        assertEquals(0,ReferenceChordMatcher.find(657,listOf(sixth,minorSeventh)))
        assertEquals(0,ReferenceChordMatcher.find(657,listOf(minorSeventh,sixth)))
    }
    @Test fun needsThreeDistinctPitchClassesAndNeverAcceptsMissingChordNotes(){
        assertNull(ReferenceChordMatcher.find(17,listOf(major)))
        assertNull(ReferenceChordMatcher.find(137,listOf(major)))
        assertNull(ReferenceChordMatcher.find(145,listOf(PlayableChord("Empty",emptyList()))))
        assertEquals(0,ReferenceChordMatcher.find(145,listOf(major.copy(notes=major.notes+60))))
    }
}
