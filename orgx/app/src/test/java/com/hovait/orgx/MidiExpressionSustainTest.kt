package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiExpressionSustainTest {
    @Test fun resetRestoresExpressionWithoutChangingChannelVolumeOrPan(){
        val events=MidiSustain.resolve(listOf(
            MidiNoteEvent(0,0,80,false,controller=7),
            MidiNoteEvent(0,0,0,false,controller=10),
            MidiNoteEvent(1,0,20,false,controller=11),
            MidiNoteEvent(1,0,30,false,channel=1,controller=11),
            MidiNoteEvent(2,0,0,false,controller=121),
            MidiNoteEvent(3,0,0,false,controller=121)))
        val mix=MidiChannelMix()
        events.forEach{event->event.controller?.let{mix.controller(event.channel,it,event.velocity)}}
        assertEquals(80/127.0,mix.gain(0,false),0.0)
        assertEquals(0.0,mix.gain(0,true),0.0)
        assertEquals(100/127.0*30/127.0,mix.gain(1,false),1e-12)
        assertEquals(listOf(2L),events.filter{it.controller==11 && it.velocity==127}.map{it.tick})
    }
    @Test fun pedalDoesNotDelayExpressionOrRemoveItsMatchingActiveNote(){
        val note=MidiNoteEvent(0,60,100,true)
        val bend=MidiNoteEvent(2,60,70,false,controller=-2)
        val pressure=MidiNoteEvent(3,60,80,false,controller=-3)
        val result=MidiSustain.resolve(listOf(note,
            MidiNoteEvent(1,0,127,false,controller=64),bend,pressure,
            MidiNoteEvent(4,0,0,false,controller=123),
            MidiNoteEvent(5,0,0,false,controller=64)))
        assertEquals(listOf(note,bend,pressure,note.copy(tick=5,on=false,velocity=0)),result)
    }
}
