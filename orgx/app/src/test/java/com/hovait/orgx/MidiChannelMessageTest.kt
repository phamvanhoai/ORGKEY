package com.hovait.orgx

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import org.junit.Assert.*
import org.junit.Test

class MidiChannelMessageTest {
    @Test fun representativeProgramsRoundTripToTheirBuiltInVoiceFamilies(){
        InstrumentCatalog.names.indices.forEach{instrument->
            val program=GmProgramMap.program(instrument)
            assertEquals(instrument,GmProgramMap.instrument(program))
            assertArrayEquals(byteArrayOf(0xc3.toByte(),program.toByte()),MidiChannelMessage.bytes(0xc3,program))
        }
    }
    @Test fun recordedPedalMessagesDelayNoteOffUntilRelease(){
        val track=ByteArrayOutputStream()
        listOf(Triple(0xb0,64,127),Triple(0x90,60,100),Triple(0x80,60,0),Triple(0xb0,64,0)).forEach{(status,a,b)->
            track.write(1);track.write(MidiChannelMessage.bytes(status,a,b))
        }
        track.write(byteArrayOf(0,0xff.toByte(),0x2f,0))
        val file=ByteArrayOutputStream()
        DataOutputStream(file).use{out->
            out.writeBytes("MThd");out.writeInt(6);out.writeShort(0);out.writeShort(1);out.writeShort(480)
            out.writeBytes("MTrk");out.writeInt(track.size());out.write(track.toByteArray())
        }
        val notes=MidiFile.read(file.toByteArray().inputStream()).events
        assertEquals(listOf(2L,4L),notes.map{it.tick})
        assertTrue(notes[0].on);assertFalse(notes[1].on)
    }
    @Test fun recordedExpressionMessagesRoundTripThroughMidiParser(){
        val track=ByteArrayOutputStream()
        listOf(Triple(0x92,60,100),Triple(0xd2,90,0),Triple(0xa2,60,80),Triple(0xe2,0,96),Triple(0x82,60,0)).forEach{(status,a,b)->
            track.write(0);track.write(MidiChannelMessage.bytes(status,a,b))
        }
        track.write(byteArrayOf(0,0xff.toByte(),0x2f,0))
        val file=ByteArrayOutputStream()
        DataOutputStream(file).use{out->
            out.writeBytes("MThd");out.writeInt(6);out.writeShort(0);out.writeShort(1);out.writeShort(480)
            out.writeBytes("MTrk");out.writeInt(track.size());out.write(track.toByteArray())
        }
        val events=MidiFile.read(file.toByteArray().inputStream()).events
        assertEquals(listOf(null,-4,-3,-2,null),events.map{it.controller})
        assertEquals(90,events[1].velocity)
        assertEquals(80,events[2].velocity)
        assertEquals(96,events[3].velocity)
        assertTrue(events.all{it.channel==2})
        assertEquals(2,MidiChannelMessage.bytes(0xd2,90).size)
    }
}
