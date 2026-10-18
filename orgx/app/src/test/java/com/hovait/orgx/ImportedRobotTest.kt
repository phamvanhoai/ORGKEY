package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedRobotTest {
    private fun settings(frequency:Int)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(16,true,false,List(4){(frequency ushr (8*it)) and 255})))
    @Test fun integerPhaseAndSharedStereoCounterMatchOriginal(){
        val fx=ImportedFxChain(settings(100),1000)
        repeat(25){frame->
            fx.process(.5,.25)
            val gain=kotlin.math.cos((100*frame/1000).toDouble())
            assertEquals(.5*gain,fx.left,0.0);assertEquals(.25*gain,fx.right,0.0)
        }
        assertFalse(fx.active)
    }
    @Test fun counterWrapsAtOneSecondAndResetRestartsPhase(){
        val fx=ImportedFxChain(settings(1000),1000)
        repeat(1000){fx.process(1.0,1.0)}
        fx.process(1.0,1.0);assertEquals(1.0,fx.left,0.0)
        repeat(3){fx.process(1.0,1.0)}
        fx.reset();fx.process(1.0,1.0);assertEquals(1.0,fx.left,0.0)
        val fresh=fx.fresh();fresh.process(1.0,1.0);assertEquals(1.0,fresh.left,0.0)
    }
    @Test fun zeroFrequencyAndDisabledBlockBypass(){
        for(config in listOf(settings(0),settings(1000).copy(fxEnabled=false))){
            val fx=ImportedFxChain(config,1000)
            repeat(1100){fx.process(.5,-.25);assertEquals(.5,fx.left,0.0);assertEquals(-.25,fx.right,0.0)}
        }
    }
}
