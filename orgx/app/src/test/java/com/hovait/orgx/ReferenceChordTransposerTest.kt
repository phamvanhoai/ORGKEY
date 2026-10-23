package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceChordTransposerTest {
    @Test fun releasingChordWithoutMemoryReleasesMelodyButKeepsDrumsAndTimeline(){
        var target=0
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,2000),ReferenceDrumEvent(2,2,60,100,20,2000))
        val track=ReferenceDrumTrack("Part",1,0,32,120,true,0,4000,false,events)
        fun player(melodic:Boolean)=ReferenceRhythmPlayer(listOf(track),{10000},{_,_,_->1.0},1000,
            accompaniment=melodic,mapNote={if(melodic && target<0)-1 else it}).withChordFollowing(target={target})
        val melody=player(true);val drum=player(false)
        melody.next(0,120);drum.next(0,120)
        assertEquals(1.0,melody.left,0.0)
        target=-1
        repeat(30){melody.next(0,120);drum.next(0,120)}
        assertEquals(0.0,melody.left,0.0);assertEquals(2.0,drum.left,0.0)
        target=0;melody.next(0,120)
        assertEquals(1.0,melody.left,0.0) // The still-live note at t20 is caught up.
    }
    @Test fun chordControlAndKeyboardOctaveAffectHeldPcmWithoutChangingChord(){
        var control=0;var octave=4
        val source=SampledInstrument(listOf(60 to 8192,72 to 16384).map{(key,value)->
            InstrumentRegion(key,SamplePcm(1000,1,ShortArray(128){value.toShort()}),0,128)
        },1000,exactNotes=true)
        val track=ReferenceDrumTrack("Bass",1,0,32,120,true,0,4000,false,listOf(ReferenceDrumEvent(2,37,60,100,0,2000)))
        val player=ReferenceRhythmPlayer(listOf(track),source::durationFrames,
            {note,age,channel->source.sample(note,age.toLong(),channel)},1000,accompaniment=true,sampled=source,
            mapNote={mapper.note(it,0,0,0,control,octave)})
            .withChordFollowing(control={control},octaveMove={octave},target={0})
        player.next(0,120);assertEquals(.25,player.left,1e-12)
        control=2;player.next(0,120);assertEquals(.25,player.left,1e-12)
        octave=5;player.next(0,120);assertEquals(.5,player.left,1e-12)
        control=3;player.next(0,120);assertEquals(.25,player.left,1e-12)
        octave=4;player.next(0,120);assertEquals(.5,player.left,1e-12)
    }
    @Test fun liveChordRevoicesLongHeldNotesButLeavesShortNotesAndNoteOffIdsIntact(){
        var target=0
        val events=listOf(ReferenceDrumEvent(2,37,64,100,0,2000),ReferenceDrumEvent(2,38,64,100,0,500),
            ReferenceDrumEvent(1,37,64,0,20,0),ReferenceDrumEvent(1,38,64,0,20,0))
        val track=ReferenceDrumTrack("Bass",1,0,32,120,true,0,4000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{10000},{note,_,_->note.toDouble()},1000,true,
            mapNote={mapper.note(it,0,0,target)}).withChordFollowing{target}
        player.next(0,120);assertEquals(128.0,player.left,0.0)
        target=12;player.next(0,120);assertEquals(127.0,player.left,0.0)
        repeat(30){player.next(0,120)};assertEquals(0.0,player.left,0.0)
    }
    private val mapper=ReferenceChordTransposer.fromTable("M 0 4 7\nm 0 3 7")
    @Test fun sustainedChordChangeSelectsNewPcmWithoutChangingDrumTrack(){
        var target=0
        val sample=SampledInstrument(listOf(63 to 16384,64 to 24576).map{(key,value)->
            InstrumentRegion(key,SamplePcm(1000,1,ShortArray(128){value.toShort()}),0,128)
        },1000,exactNotes=true)
        val track=ReferenceDrumTrack("Bass",1,0,32,120,true,0,4000,false,listOf(ReferenceDrumEvent(2,37,64,100,0,2000)))
        fun player(melodic:Boolean)=ReferenceRhythmPlayer(listOf(track),sample::durationFrames,
            {note,age,channel->sample.sample(note,age.toLong(),channel)},1000,accompaniment=melodic,
            mapNote={if(melodic)mapper.note(it,0,0,target) else it},sampled=sample).withChordFollowing{target}
        val bass=player(true);val drum=player(false)
        bass.next(0,120);drum.next(0,120)
        assertEquals(.75,bass.left,1e-12);assertEquals(.75,drum.left,1e-12)
        target=12;bass.next(0,120);drum.next(0,120)
        assertEquals(.5,bass.left,1e-12);assertEquals(.75,drum.left,1e-12)
        target=0;bass.next(0,120);assertEquals(.75,bass.left,1e-12)
        bass.releaseAll();repeat(15){bass.next(0,120)};assertEquals(0.0,bass.left,0.0)
    }
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
