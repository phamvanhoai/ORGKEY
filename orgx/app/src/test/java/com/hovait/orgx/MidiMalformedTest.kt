package com.hovait.orgx

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import org.junit.Test

class MidiMalformedTest {
    private fun read(track:ByteArray,division:Int=480){
        val bytes=ByteArrayOutputStream()
        DataOutputStream(bytes).use{out->
            out.writeBytes("MThd");out.writeInt(6);out.writeShort(0);out.writeShort(1);out.writeShort(division)
            out.writeBytes("MTrk");out.writeInt(track.size);out.write(track)
            // Extra bytes cannot complete a truncated event in the preceding track.
            out.write(byteArrayOf(60,100))
        }
        MidiFile.read(bytes.toByteArray().inputStream())
    }
    @Test(expected=IllegalArgumentException::class)
    fun rejectsZeroDivision(){read(byteArrayOf(0,0x90.toByte(),60,100),0)}
    @Test(expected=IllegalArgumentException::class)
    fun rejectsNoteOutsideTrack(){read(byteArrayOf(0,0x90.toByte()))}
    @Test(expected=IllegalArgumentException::class)
    fun rejectsStatusByteAsNote(){read(byteArrayOf(0,0x90.toByte(),0x80.toByte(),100))}
    @Test(expected=IllegalArgumentException::class)
    fun rejectsSysExBeyondTrack(){read(byteArrayOf(0,0xf0.toByte(),10,1))}
    @Test(expected=IllegalStateException::class)
    fun rejectsOverlongVariableInteger(){read(byteArrayOf(0x80.toByte(),0x80.toByte(),0x80.toByte(),0x80.toByte(),0))}
}
