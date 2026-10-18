package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiRecordingPedalTest {
    @Test fun globalPedalPreservesLiveNotesOnEveryMelodicChannel(){
        fun pedal(enabled:Boolean,tick:Long)=MidiRecordingPedal.messages(enabled).map{bytes->
            MidiNoteEvent(tick,0,bytes[2].toInt() and 127,false,bytes[0].toInt() and 15,controller=64)
        }
        val channels=(0..15).filterNot(MidiVoiceKey::percussion)
        assertEquals(channels,pedal(true,0).map{it.channel})
        val on=channels.map{MidiNoteEvent(1,60,100,true,it)}
        val off=on.map{it.copy(tick=2,on=false,velocity=0)}
        val resolved=MidiSustain.resolve(pedal(true,0)+on+off+pedal(false,10))
        assertEquals(15,resolved.count{it.on})
        assertEquals(15,resolved.count{!it.on && it.tick==10L})
        assertFalse(resolved.any{it.channel==9})
    }
}
