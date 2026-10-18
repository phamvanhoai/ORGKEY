package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedNoteTouchTest {
    private fun sound(pan:Int)=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(16384,16384),null),0,0,pan=pan,looping=false)),48000)
    @Test fun opposingNoteAndRegionPanCancelBeforeStereoGain(){
        val voice=sound(100).voice(60,pan=-100);voice.next(false)
        assertEquals(.5,voice.left,0.0);assertEquals(.5,voice.right,0.0)
        val reverse=sound(-100).voice(60,pan=100);reverse.next(false)
        assertEquals(.5,reverse.left,0.0);assertEquals(.5,reverse.right,0.0)
    }
    @Test fun timelineAppliesNotePanInsideSampleMappingOnlyOnce(){
        val source=sound(100)
        val event=ReferenceDrumEvent(2,1,60,(-100 shl 16) or 100,0,100)
        val track=ReferenceDrumTrack("Pan",1,0,32,120,false,0,1000,false,listOf(event))
        val player=ReferenceRhythmPlayer(listOf(track),source::durationFrames,{n,a,c->source.sample(n,a.toLong(),c)},48000,sampled=source)
        player.next(0,120)
        assertEquals(.5,player.left,0.0);assertEquals(.5,player.right,0.0)
        assertEquals(0.0,source.sample(60,0,0),0.0)
    }
    @Test fun modeLayersCombinePanIndependentlyBeforeSumming(){
        val voice=SampledInstrument.combine(listOf(sound(100),sound(-100))).voice(60,pan=-100)
        voice.next(false)
        assertEquals(1.0,voice.left,0.0);assertEquals(0.0,voice.right,0.0)
    }
    @Test fun negativePackedTouchKeepsUnsignedVelocity(){
        val touch=(-100 shl 16) or 75
        assertEquals(.75,ImportedNoteTouch.velocity(touch),0.0)
        assertEquals(-100,ImportedNoteTouch.pan(touch))
        assertEquals(1.0,ImportedNoteTouch.gain(touch,0),0.0)
        assertEquals(0.0,ImportedNoteTouch.gain(touch,1),0.0)
    }
    @Test fun timelineUsesPerNotePanAndVelocityWithoutChangingOtherNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,(100 shl 16) or 50,0,100),
            ReferenceDrumEvent(2,2,61,(-100 shl 16) or 75,0,100))
        val track=ReferenceDrumTrack("Packed touch",1,0,32,120,false,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{100},{_,_,_->1.0},48000)
        player.next(0,120)
        assertEquals(.75,player.left,0.0);assertEquals(.5,player.right,0.0)
    }
}
