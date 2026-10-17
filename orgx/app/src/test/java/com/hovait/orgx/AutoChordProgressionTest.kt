package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class AutoChordProgressionTest {
    private val table="M 0 4 7\nm 0 3 7"
    private val library=PlayableChordLibrary.fromTable(table)
    @Test fun presetsResolveToChordNamesRegardlessOfLibraryOrder() {
        val reordered=library.reversed()
        val progression=AutoChordProgression(reordered)
        val expected=listOf(listOf("C M","A m","F M","G M"),listOf("C M","G M","A m","F M"),listOf("A m","F M","C M","G M"))
        expected.forEachIndexed{preset,names->
            repeat(8){bar->assertEquals(names[bar%4],reordered[progression.chord(preset,bar*2000L)!!].name)}
        }
    }
    @Test fun missingLibraryChordIsNotReplacedWithWrongChord() {
        val incomplete=AutoChordProgression(listOf(library.first()))
        assertEquals(0,incomplete.chord(0,0))
        assertNull(incomplete.chord(0,2000))
        assertNull(AutoChordProgression(emptyList()).chord(0,0))
    }
    @Test fun tempoChangePreservesBarPositionAndResetRestarts() {
        val clock=ReferenceStyleClock(1000)
        val progression=AutoChordProgression(library)
        clock.advance(1000,120)
        assertEquals("C M",library[progression.chord(0,clock.time)!!].name)
        clock.advance(499,240)
        assertEquals("C M",library[progression.chord(0,clock.time)!!].name)
        clock.advance(1,240)
        assertEquals("A m",library[progression.chord(0,clock.time)!!].name)
        clock.reset()
        assertEquals("C M",library[progression.chord(0,clock.time)!!].name)
    }
    @Test fun scheduledSetNotesActuallyFollowAutoChordAtBarBoundary() {
        val clock=ReferenceStyleClock(1000)
        val progression=AutoChordProgression(library)
        val transposer=ReferenceChordTransposer.fromTable(table)
        val events=listOf(ReferenceDrumEvent(2,1,64,100,0,1),ReferenceDrumEvent(2,2,64,100,2000,1))
        val track=ReferenceDrumTrack("Melody",1,0,32,120,true,0,4000,false,events)
        val heard=mutableListOf<Int>()
        val player=ReferenceRhythmPlayer(listOf(track),{1},{note,_,_->heard.add(note);0.1},1000,true,
            mapNote={transposer.note(it,0,0,progression.chord(0,clock.time)!!)})
        repeat(2001){player.next(0,120);clock.advance(1,120)}
        assertEquals(listOf(64,64,72,72),heard) // Each note renders once per stereo channel.
    }
}
