package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFlangerTest {
    private fun settings(stages:Int=2,ratio:Int=128)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(8,true,false,listOf(5,0,45,10,128,stages,ratio).flatMap{v->List(4){(v ushr (it*8)) and 255}})))
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
        val fresh=fx.fresh();assertFalse(fresh.active)
        fresh.process(1.0,0.0);assertEquals(expected.toDouble(),fresh.left,0.0)
    }
    @Test fun resetPreservesNativeHistoryFeedbackAndModulationPhase(){
        val uninterrupted=ImportedFxChain(settings());val reset=ImportedFxChain(settings())
        repeat(63){i->
            val input=if(i==0)1.0 else 0.0
            uninterrupted.process(input,input*.5);reset.process(input,input*.5)
        }
        reset.reset();assertTrue(reset.active)
        repeat(60){
            uninterrupted.process(0.0,0.0);reset.process(0.0,0.0)
            assertEquals(uninterrupted.left,reset.left,0.0);assertEquals(uninterrupted.right,reset.right,0.0)
        }
    }
    @Test fun maximumRatioKeepsNative255DivisorInsteadOfClampingToFullyWet(){
        val fx=ImportedFxChain(settings(ratio=256))
        val cosine=kotlin.math.cos(10.0).toFloat()
        val e=kotlin.math.exp((cosine+cosine+2f).toDouble()).toFloat()
        val coefficient=1f+(e-1f)*(128f*-0.003921568859368563f)/53.598148345947266f
        val first=Math.fma(-coefficient,1f,0f)
        val wet=Math.fma(-coefficient,first,0f)
        fx.process(1.0,0.0)
        assertEquals(((wet*256f-1f)*0.003921568859368563f).toDouble(),fx.left,0.0)
    }
    @Test fun stereoChannelsUseSameModulationAndDisabledBlockBypasses(){
        val fx=ImportedFxChain(settings())
        repeat(100){fx.process(.25,.25);assertEquals(fx.left,fx.right,0.0)}
        val bypass=ImportedFxChain(settings().copy(fxEnabled=false))
        bypass.process(.5,-.25);assertEquals(.5,bypass.left,0.0);assertEquals(-.25,bypass.right,0.0)
    }
}
