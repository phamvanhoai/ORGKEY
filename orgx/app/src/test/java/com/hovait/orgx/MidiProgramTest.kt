package com.hovait.orgx

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import org.junit.Assert.*
import org.junit.Test

class MidiProgramTest {
    @Test fun channelPressureKeepsRunningStatusAndTime(){
        val track=byteArrayOf(0,0x91.toByte(),60,100,2,0xd1.toByte(),70,3,90,
            1,0x81.toByte(),60,0,0,0xff.toByte(),0x2f,0)
        val bytes=ByteArrayOutputStream()
        DataOutputStream(bytes).use{out->
            out.writeBytes("MThd");out.writeInt(6);out.writeShort(0);out.writeShort(1);out.writeShort(480)
            out.writeBytes("MTrk");out.writeInt(track.size);out.write(track)
        }
        val pressure=MidiFile.read(bytes.toByteArray().inputStream()).events.filter{it.controller==-4}
        assertEquals(listOf(2L,5L),pressure.map{it.tick})
        assertEquals(listOf(70,90),pressure.map{it.velocity})
        assertTrue(pressure.all{it.channel==1})
    }
    @Test fun noteBeforeSameTickProgramChangeKeepsPreviousVoice() {
        val track=byteArrayOf(0,0x90.toByte(),60,100,0,0xc0.toByte(),73,
            0,0x90.toByte(),62,100,0,0xff.toByte(),0x2f,0)
        val bytes=ByteArrayOutputStream()
        DataOutputStream(bytes).use{out->
            out.writeBytes("MThd");out.writeInt(6);out.writeShort(0);out.writeShort(1);out.writeShort(480)
            out.writeBytes("MTrk");out.writeInt(track.size);out.write(track)
        }
        assertEquals(listOf(0,73),MidiFile.read(bytes.toByteArray().inputStream()).events.map{it.program})
    }
    @Test fun programChangesAreAttachedToNotesOnTheirOwnChannel() {
        val track=byteArrayOf(0,0xc1.toByte(),73,0,0x91.toByte(),60,100,
            0,0x90.toByte(),64,100,10,0xc1.toByte(),32,0,0x91.toByte(),62,100,
            0,0xff.toByte(),0x2f,0)
        val bytes=ByteArrayOutputStream()
        DataOutputStream(bytes).use{out->
            out.writeBytes("MThd");out.writeInt(6);out.writeShort(0);out.writeShort(1);out.writeShort(480)
            out.writeBytes("MTrk");out.writeInt(track.size);out.write(track)
        }
        val song=MidiFile.read(bytes.toByteArray().inputStream())
        assertEquals(listOf(1,0,1),song.events.map{it.channel})
        assertEquals(listOf(73,0,32),song.events.map{it.program})
        assertEquals(listOf(3,0,4),song.events.map{GmProgramMap.instrument(it.program)})
    }
    @Test fun everyGmProgramMapsToAnAvailableVoice() {
        repeat(128){assertTrue(GmProgramMap.instrument(it) in InstrumentCatalog.names.indices)}
        assertEquals(6,GmProgramMap.instrument(40))
        assertEquals(7,GmProgramMap.instrument(48))
        assertEquals(5,GmProgramMap.instrument(52))
    }
}
