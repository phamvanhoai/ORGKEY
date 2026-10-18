package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedChorusTest {
    private fun settings(mix:Int=100,feedback:Int=0)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(21,true,false,listOf(5,10,5,feedback,mix,0).flatMap{v->List(4){(v ushr (8*it)) and 255}})))
    @Test fun impulseHasFractionalDelayAndChannelsDoNotLeak(){
        val fx=ImportedFxChain(settings(),1000)
        fx.process(1.0,0.0);assertEquals(0.0,fx.left,0.0);assertTrue(fx.active)
        repeat(4){fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)}
        fx.process(0.0,0.0)
        assertTrue(fx.left in .94.. .96);assertEquals(0.0,fx.right,0.0)
        fx.process(0.0,0.0);assertTrue(fx.left in .04.. .06)
    }
    @Test fun dryMixBypassesOutputAndResetClearsFeedback(){
        val dry=ImportedFxChain(settings(0),1000)
        repeat(20){dry.process(.5,-.25);assertEquals(.5,dry.left,0.0);assertEquals(-.25,dry.right,0.0)}
        val wet=ImportedFxChain(settings(feedback=50),1000)
        wet.process(1.0,0.0);repeat(8){wet.process(0.0,0.0)}
        wet.reset();assertFalse(wet.active)
        repeat(30){wet.process(0.0,0.0);assertEquals(0.0,wet.left,0.0)}
    }
}
