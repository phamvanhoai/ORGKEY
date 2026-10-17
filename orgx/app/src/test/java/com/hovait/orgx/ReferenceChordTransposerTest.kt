package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceChordTransposerTest {
    private val mapper=ReferenceChordTransposer.fromTable("M 0 4 7\nm 0 3 7")
    @Test fun convertsChordDegreesAndRoot() {
        assertEquals(63,mapper.note(64,0,0,12)) // C major E -> C minor Eb
        assertEquals(71,mapper.note(64,0,0,7)) // C major E -> G major B
        assertEquals(64,mapper.note(63,0,1,0)) // C minor Eb -> C major E
        assertEquals(69,mapper.note(62,0,0,7)) // Non-chord D retains its offset.
        assertEquals(60,mapper.note(62,2,0,0))
    }
    @Test fun octaveControlsMatchReferenceThresholds() {
        assertEquals(59,mapper.note(60,0,0,11,control=1))
        assertEquals(71,mapper.note(60,0,0,11))
        assertEquals(72,mapper.note(60,0,0,0,control=2,octaveMove=1))
        assertEquals(60,mapper.note(60,0,0,0,control=2,octaveMove=0))
        assertEquals(72,mapper.note(60,0,0,0,control=3,octaveMove=0))
        assertEquals(55,mapper.note(60,0,0,7,control=4))
        for(note in 0..127)for(chord in 0..23)assertTrue(mapper.note(note,0,0,chord) in 21..108)
    }
    @Test fun changesApplyToNewVoicesAndReleaseStillUsesOriginalId() {
        var target=0
        val events=listOf(ReferenceDrumEvent(2,1,64,100,0,100),ReferenceDrumEvent(1,1,64,0,2,0),
            ReferenceDrumEvent(2,2,64,100,20,100))
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{note,_,_->note.toDouble()},1000,true,
            mapNote={mapper.note(it,0,0,target)})
        player.next(0,120);assertEquals(64.0,player.left,0.0)
        target=12
        player.next(0,120);assertEquals(64.0,player.left,0.0)
        repeat(12){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
        repeat(7){player.next(0,120)}
        assertEquals(63.0,player.left,0.0)
    }
}
