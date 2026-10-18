package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceBandBoosterTest {
    @Test fun unityBandsRecombineIntoThreeSampleDelayedInput(){
        val processor=ReferenceBandBooster(0,0,0,48000)
        val input=LongArray(256){if(it%2==0)12000L else -7000L}
        input.indices.forEach{i->assertEquals(if(i<3)0.0 else input[i-3].toDouble(),processor.process(input[i]).toDouble(),1.0)}
        processor.reset()
        repeat(3){assertEquals(0L,processor.process(0))}
    }
    @Test fun boostedResponseIsStatefulAndResetIsRepeatable(){
        val processor=ReferenceBandBooster(50,-20,80,48000)
        val input=LongArray(128){if(it==0)16000 else 0}
        val first=input.map(processor::process)
        assertTrue(first.drop(4).any{it!=0L})
        processor.reset();assertEquals(first,input.map(processor::process))
        val separate=ReferenceBandBooster(0,0,0,48000)
        assertEquals(0L,separate.process(0))
    }
}
