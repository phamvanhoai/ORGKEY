package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedEqualizerTest {
    private fun settings(values:List<Int>)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(7,true,false,values.flatMap{v->List(4){(v ushr (8*it)) and 255}})))
    @Test fun zeroBandsMuteAndIndependentStereoIsPreserved(){
        val muted=ImportedFxChain(settings(List(8){0}))
        val normal=ImportedFxChain(settings(List(8){100}))
        repeat(256){i->
            val x=if(i==0).5 else 0.0
            muted.process(x,x);normal.process(x,x)
            assertEquals(0.0,muted.left,0.0);assertEquals(0.0,muted.right,0.0)
            assertEquals(normal.left,normal.right,0.0);assertTrue(normal.left.isFinite())
        }
    }
    @Test fun nativeBandGainScalesResponseAndResetKeepsHistory(){
        val half=ImportedFxChain(settings(List(8){50}));val full=ImportedFxChain(settings(List(8){100}))
        repeat(100){i->
            val x=if(i==0)1.0 else 0.0
            half.process(x,0.0);full.process(x,0.0)
            assertEquals(full.left*.5,half.left,1e-12)
        }
        val control=ImportedFxChain(settings(List(8){100}))
        repeat(100){i->control.process(if(i==0)1.0 else 0.0,0.0)}
        full.reset()
        repeat(50){full.process(0.0,0.0);control.process(0.0,0.0);assertEquals(control.left,full.left,0.0)}
    }
    @Test fun excessBandsRejectedAndShortPayloadKeepsDefaultBands(){
        try{ImportedFxChain(settings(List(9){100}));fail("Expected band count rejection")}
        catch(expected:IllegalArgumentException){ }
        val short=ImportedFxChain(settings(emptyList()));val full=ImportedFxChain(settings(List(8){100}))
        short.process(.5,.25);full.process(.5,.25)
        assertEquals(full.left,short.left,0.0);assertEquals(full.right,short.right,0.0)
    }
}
