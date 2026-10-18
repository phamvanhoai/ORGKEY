package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFlangerTest {
    private fun settings(stages:Int=2)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(8,true,false,listOf(5,0,45,10,128,stages,128).flatMap{v->List(4){(v ushr (it*8)) and 255}})))
    @Test fun firstImpulseMatchesNativeAllPassAndDryWetMix(){
        val fx=ImportedFxChain(settings())
        val cosine=kotlin.math.cos(10.0).toFloat()
        val e=kotlin.math.exp((cosine+cosine+2f).toDouble()).toFloat()
        val coefficient=1f+(e-1f)*(128f*-0.003921568859368563f)/53.598148345947266f
        val stage1=Math.fma(-coefficient,1f,0f)
        val stage2=Math.fma(-coefficient,stage1,0f)
        val expected=(stage2*128f+127f)*0.003921568859368563f
        fx.process(1.0,0.0)
        assertEquals(expected.toDouble(),fx.left,0.0);assertEquals(0.0,fx.right,0.0);assertTrue(fx.active)
        repeat(100){fx.process(0.0,0.0);assertEquals(0.0,fx.right,0.0);assertTrue(fx.left.isFinite())}
        fx.reset();assertFalse(fx.active)
        fx.process(1.0,0.0);assertEquals(expected.toDouble(),fx.left,0.0)
    }
    @Test fun stereoChannelsUseSameModulationAndDisabledBlockBypasses(){
        val fx=ImportedFxChain(settings())
        repeat(100){fx.process(.25,.25);assertEquals(fx.left,fx.right,0.0)}
        val bypass=ImportedFxChain(settings().copy(fxEnabled=false))
        bypass.process(.5,-.25);assertEquals(.5,bypass.left,0.0);assertEquals(-.25,bypass.right,0.0)
    }
}
