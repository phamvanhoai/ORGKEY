package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedBandPassTest {
    private fun settings(frequency:Int,width:Int)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(1,true,false,listOf(frequency,width).flatMap{v->List(4){(v ushr (it*8)) and 255}})))
    @Test fun impulseMatchesReferenceRecurrenceAndStereoIsIndependent(){
        val fx=ImportedFxChain(settings(5000,1000))
        val radius=1f-(1000/48000f)*3f
        val cosine=kotlin.math.cos((5000/48000f).toDouble()*2*Math.PI).toFloat()*2f
        val a1=radius*cosine;val a2=-radius*radius
        val scale=((1f-a1)+radius*radius)/(2f-cosine)
        val b=floatArrayOf(1f-scale,(scale-radius)*cosine,radius*radius-scale)
        val history=FloatArray(32)
        repeat(32){frame->
            var expected=0f
            for(i in b.indices)expected=b[i]*(if(frame==i)1f else 0f)+expected
            expected=a1*(history.getOrNull(frame-1) ?: 0f)+expected
            expected=a2*(history.getOrNull(frame-2) ?: 0f)+expected
            history[frame]=expected
            fx.process(if(frame==0)1.0 else 0.0,0.0)
            assertEquals(expected.toDouble(),fx.left,0.0);assertEquals(0.0,fx.right,0.0)
        }
        assertTrue(fx.active);fx.reset();assertFalse(fx.active)
        fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)
    }
    @Test fun singularCoefficientsAreRejectedAndDisabledBlockBypasses(){
        try{ImportedFxChain(settings(0,1000));fail("Expected singular coefficient rejection")}
        catch(expected:IllegalArgumentException){ }
        val fx=ImportedFxChain(settings(0,1000).copy(fxEnabled=false))
        fx.process(.5,-.25);assertEquals(.5,fx.left,0.0);assertEquals(-.25,fx.right,0.0)
    }
}
