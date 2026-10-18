package com.hovait.orgx

import java.nio.ByteBuffer
import java.nio.ByteOrder
import org.junit.Assert.*
import org.junit.Test

class ImportedExpressionEffectTest {
    @Test fun decodesFixedRecordOffsetsAndKeepsReservedBytes(){
        val raw=ByteArray(40)
        raw[0]=1;raw[4]=123;raw[24]=1;raw[26]=0x81.toByte()
        ByteBuffer.wrap(raw).order(ByteOrder.LITTLE_ENDIAN).apply{
            putInt(20,123456);putInt(28,-1234);putInt(32,98765);putInt(36,42)
        }
        val effect=ImportedExpressionEffect.decode(7,raw)
        assertEquals(7,effect.slot);assertTrue(effect.enabled)
        assertEquals(123456,effect.startDelay);assertTrue(effect.byPedal)
        assertFalse(effect.stopByRelease);assertEquals(129,effect.mode)
        assertEquals(-1234,effect.speed);assertEquals(98765,effect.depth);assertEquals(42,effect.gain)
        assertEquals(raw.map{it.toInt() and 255},effect.raw)
        raw[4]=0;assertEquals(123,effect.raw[4])
    }
    @Test fun rejectsIncompleteRecordsAndOutOfRangeSlots(){
        for((slot,size) in listOf(0 to 39,0 to 41,-1 to 40,8 to 40)){
            try{ImportedExpressionEffect.decode(slot,ByteArray(size));fail("Invalid record accepted")}
            catch(expected:IllegalArgumentException){}
        }
    }
}
