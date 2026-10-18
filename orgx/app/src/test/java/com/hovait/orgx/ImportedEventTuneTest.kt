package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedEventTuneTest {
    private fun sound()=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(0,8192,16384,24576),null),0,0,looping=false)),48000)
    @Test fun simultaneousVoicesKeepIndependentTuneAndSampleLifetime(){
        val source=sound();val normal=source.voice(60);val octave=source.voice(60,12000)
        normal.next(false);octave.next(false)
        normal.next(false);octave.next(false)
        assertEquals(.25,normal.left,0.0);assertEquals(.5,octave.left,0.0)
        assertTrue(octave.finished);assertFalse(normal.finished)
        normal.next(false);assertEquals(.5,normal.left,0.0)
    }
    @Test fun rhythmTimelinePassesEventTuneIntoSampledVoice(){
        val source=sound()
        val event=ReferenceDrumEvent(2,1,60,100,0,100,tune=12000)
        val track=ReferenceDrumTrack("Tune",1,0,32,120,false,0,100000,false,listOf(event))
        val player=ReferenceRhythmPlayer(listOf(track),source::durationFrames,{n,a,c->source.sample(n,a.toLong(),c)},48000,sampled=source)
        player.next(0,120);player.next(0,120)
        assertEquals(.5,player.left,0.0);assertEquals(.5,player.right,0.0)
        player.next(0,120);assertEquals(0.0,player.left,0.0)
    }
    @Test fun modeLayersEachReceiveTheSameEventTune(){
        val source=SampledInstrument.combine(listOf(sound(),sound()))
        val voice=source.voice(60,12000);voice.next(false);voice.next(false)
        assertEquals(1.0,voice.left,0.0);assertTrue(voice.finished)
        assertEquals(.25,sound().sample(60,1),0.0)
    }
}
