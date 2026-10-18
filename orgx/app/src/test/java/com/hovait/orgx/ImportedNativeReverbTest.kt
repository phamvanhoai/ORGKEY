package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedNativeReverbTest {
    private val payload=listOf(40,7,50,50,50,50,50,0)
    private class Fake:ImportedNativeReverb.Backend {
        var closes=0;var flushes=0;var mix=0;var opened:List<Int> = emptyList()
        override fun open(rate:Int,values:List<Int>):Long {assertEquals(48000,rate);opened=values;return 42}
        var outputBuffer:LongArray?=null
        override fun process(handle:Long,left:Long,right:Long,mix:Int,output:LongArray){
            assertEquals(42L,handle);this.mix=mix
            outputBuffer?.let{assertSame(it,output)};outputBuffer=output
            output[0]=left;output[1]=right
        }
        override fun flush(handle:Long){flushes++}
        override fun close(handle:Long){closes++}
    }
    @Test fun bridgePreservesStereoScaleAndSetMix(){
        val backend=Fake();val fx=ImportedNativeReverb(payload,48000,backend)
        fx.process(.5,-.25)
        assertEquals(.5,fx.left,0.0);assertEquals(-.25,fx.right,0.0)
        assertEquals(payload,backend.opened);assertEquals(50,backend.mix)
        repeat(100){fx.process(.25,-.125)}
        fx.reset();assertEquals(1,backend.flushes);assertEquals(0.0,fx.left,0.0)
        fx.close();fx.close();assertEquals(1,backend.closes)
        try{fx.process(0.0,0.0);fail("Closed handle must not be used")}
        catch(expected:IllegalStateException){ }
    }
    @Test fun malformedPayloadIsRejectedBeforeNativeAllocation(){
        val backend=Fake()
        try{ImportedNativeReverb(payload.take(7),48000,backend);fail("Expected rejection")}
        catch(expected:IllegalArgumentException){ }
        assertTrue(backend.opened.isEmpty())
    }
}
