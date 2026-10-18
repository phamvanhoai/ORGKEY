package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStereoWidthTest {
    private fun module(volume:Int)=ImportedFxModule(17,true,false,List(4){(volume ushr (it*8)) and 255})
    private fun chain(volume:Int)=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(volume))))
    @Test fun monoCentreIsUnchangedAndStageHasNoTail(){
        val fx=chain(50000);fx.process(.25,.25)
        assertEquals(.25,fx.left,0.0);assertEquals(.25,fx.right,0.0);assertFalse(fx.active)
        fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)
    }
    @Test fun stereoDifferenceUsesOriginalWidthFormula(){
        val fx=chain(10000);fx.process(1.0,0.0)
        val width=6.5536f.toDouble()
        assertEquals((.5*width+1.5)/2,fx.left,1e-9)
        assertEquals((-.5*width+.5)/2,fx.right,1e-9)
        assertEquals(1.0,fx.left+fx.right,1e-9)
        val zero=chain(0);zero.process(1.0,0.0)
        assertEquals(.75,zero.left,0.0);assertEquals(.25,zero.right,0.0)
    }
    @Test fun disabledBlockAndDisabledModuleBypass(){
        listOf(ImportedChannelSettings(modules=listOf(module(10000))),
            ImportedChannelSettings(fxEnabled=true,modules=listOf(module(10000).copy(enabled=false)))).forEach{
            val fx=ImportedFxChain(it);fx.process(1.0,0.0)
            assertEquals(1.0,fx.left,0.0);assertEquals(0.0,fx.right,0.0)
        }
    }
    @Test fun malformedPayloadAndOutOfRangeVolumeAreRejected(){
        for(module in listOf(module(50001),module(0).copy(payload=emptyList()))){
            try{ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module)));fail("Expected rejection")}
            catch(expected:IllegalArgumentException){ }
        }
    }
}
