package com.hovait.orgx

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import org.junit.Assert.*
import org.junit.Test

class SamplePcmDecoderTest {
    private fun wav():ByteArray {
        fun le16(v:Int)=byteArrayOf((v and 255).toByte(),(v ushr 8 and 255).toByte())
        fun le32(v:Int)=byteArrayOf((v and 255).toByte(),(v ushr 8 and 255).toByte(),(v ushr 16 and 255).toByte(),(v ushr 24 and 255).toByte())
        val pcm=byteArrayOf(0xE8.toByte(),0x03,0xE8.toByte(),0x03)
        return ByteArrayOutputStream().apply{write("RIFF".toByteArray());write(le32(40));write("WAVEfmt ".toByteArray());write(le32(16));write(le16(1));write(le16(1));write(le32(48000));write(le32(96000));write(le16(2));write(le16(16));write("data".toByteArray());write(le32(4));write(pcm)}.toByteArray()
    }
    @Test fun decodesPcm16(){val sample=SamplePcmDecoder.decode(ByteArrayInputStream(wav()));assertEquals(48000,sample.sampleRate);assertEquals(1,sample.channels);assertArrayEquals(shortArrayOf(1000,1000),sample.frames)}
    @Test(expected=IllegalArgumentException::class) fun rejectsTruncated(){SamplePcmDecoder.decode(ByteArrayInputStream(ByteArray(20)))}
}
