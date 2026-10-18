package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedPassFilterTest {
    private fun module(type:Int,frequency:Int)=ImportedFxModule(type,true,false,List(4){(frequency ushr (it*8)) and 255})
    private fun chain(type:Int,frequency:Int)=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(type,frequency))))
    @Test fun lowPassImpulseUsesOriginalCoefficientsAndStereoState(){
        val fx=chain(10,75000)
        val feedback=kotlin.math.exp(-2*Math.PI*((75000*1000f/150000f)/48000).toDouble()).toFloat()
        val direct=1f-feedback
        fx.process(1.0,0.0)
        assertEquals(direct.toDouble(),fx.left,0.0);assertEquals(0.0,fx.right,0.0)
        assertTrue(fx.active)
        fx.process(0.0,0.0);assertEquals((feedback*direct).toDouble(),fx.left,0.0)
        fx.reset();assertFalse(fx.active)
        fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)
    }
    @Test fun highPassPreservesIntegerFrequencyDivisionFromOriginal(){
        val low=chain(9,47999);low.process(1.0,0.0)
        assertEquals(1.0,low.left,0.0)
        low.process(0.0,0.0);assertEquals(0.0,low.left,0.0)
        val high=chain(9,48000)
        val feedback=kotlin.math.exp(-2*Math.PI).toFloat()
        val direct=(1f+feedback)/2f
        high.process(1.0,0.0);assertEquals(direct.toDouble(),high.left,0.0)
        high.process(0.0,0.0);assertEquals((-direct+feedback*direct).toDouble(),high.left,0.0)
    }
    @Test fun zeroLowPassIsSilentAndDisabledModulesBypass(){
        val zero=chain(10,0);zero.process(1.0,1.0)
        assertEquals(0.0,zero.left,0.0);assertFalse(zero.active)
        val bypass=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(9,100000).copy(enabled=false))))
        bypass.process(.5,.25);assertEquals(.5,bypass.left,0.0);assertEquals(.25,bypass.right,0.0)
    }
}
