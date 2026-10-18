package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedDistortionTest {
    private fun settings(gain:Int,soft:Boolean,cutoff:Int)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(23,true,false,bytes(gain)+listOf(if(soft)1 else 0)+bytes(cutoff))))
    private fun bytes(value:Int)=List(4){(value ushr (8*it)) and 255}
    @Test fun hardClipUsesGainAndOutputCompensation(){
        val fx=ImportedFxChain(settings(4,false,1))
        fx.process(.5,-.5)
        assertEquals(.5,fx.left,0.0);assertEquals(-.5,fx.right,0.0)
        fx.process(.1,0.0);assertEquals(.2,fx.left,1e-9);assertEquals(0.0,fx.right,0.0)
    }
    @Test fun softClipAndHistoryUsePreviousFinalOutput(){
        val fx=ImportedFxChain(settings(8,true,2))
        val first=kotlin.math.tanh(4.0)*.5*.5
        fx.process(.5,0.0);assertEquals(first,fx.left,1e-12);assertEquals(0.0,fx.right,0.0)
        fx.process(0.0,0.0);assertEquals(first*.25,fx.left,1e-12);assertTrue(fx.active)
        fx.reset();assertFalse(fx.active);fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)
    }
    @Test fun disabledBlockBypassesAndZeroCutoffIsRejected(){
        val bypass=ImportedFxChain(settings(100,true,1).copy(fxEnabled=false))
        bypass.process(.5,-.25);assertEquals(.5,bypass.left,0.0);assertEquals(-.25,bypass.right,0.0)
        try{ImportedFxChain(settings(1,false,0));fail("Expected invalid cutoff rejection")}
        catch(expected:IllegalArgumentException){ }
    }
}
