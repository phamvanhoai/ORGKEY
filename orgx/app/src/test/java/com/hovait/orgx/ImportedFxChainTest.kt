package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFxChainTest {
    @Test fun echoAutomationMapsNormalizedValuesBeforeWritingLittleEndianParameters(){
        for(type in listOf(4,6)){
            val original=module(type,50,30)
            val volume=ImportedTrackFxAutomation.apply(original,ReferenceDrumEvent(66,0,1,0,0,0))
            assertEquals(50,volume.payload[0]);assertEquals(30,volume.payload[4])
            val delay=ImportedTrackFxAutomation.apply(volume,ReferenceDrumEvent(66,0,2,100,0,0))
            assertEquals(100,delay.payload[4]);assertEquals(50,delay.payload[0])
            val silent=ImportedTrackFxAutomation.apply(delay,ReferenceDrumEvent(66,0,1,-100,0,0))
            assertEquals(0,silent.payload[0]);assertTrue(silent.enabled)
            val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(silent)),1000)
            try{fx.process(1.0,0.0);repeat(1000){fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)}}finally{fx.close()}
        }
    }
    @Test fun trackRestartResetsOnlyRealtimeModules(){
        for(realtime in listOf(false,true)){
            val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(
                module(6,50,1).copy(enabled=false,realtime=!realtime),
                module(6,50,1).copy(realtime=realtime))),1000)
            try{
                fx.process(1.0,0.0);fx.resetRealtime()
                repeat(9){fx.process(0.0,0.0)}
                fx.process(0.0,0.0)
                assertEquals(if(realtime)0.0 else .5,fx.left,0.0)
                assertEquals(0.0,fx.right,0.0)
            }finally{fx.close()}
        }
    }
    @Test fun stereoEchoModulatesOutputButKeepsFullFeedback(){
        val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(4,50,10))),100)
        // Ten-sample delay. Seed all cells so modulation can be measured throughout the sweep.
        repeat(10){fx.process(1.0,1.0)}
        fx.process(0.0,0.0);assertEquals(.5,fx.left,1e-9);assertEquals(0.0,fx.right,1e-9)
        fx.process(0.0,0.0);assertEquals(.45,fx.left,1e-9);assertEquals(.05,fx.right,1e-9)
        repeat(8){fx.process(0.0,0.0)}
        fx.process(0.0,0.0);assertEquals(0.0,fx.left,1e-9);assertEquals(.25,fx.right,1e-9)
        fx.reset();repeat(20){fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0);assertEquals(0.0,fx.right,0.0)}
    }
    @Test fun stereoEchoDoesNotSendLeftInputIntoRightChannel(){
        val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(4,50,10))),100)
        repeat(50){fx.process(if(it==0)1.0 else 0.0,0.0);assertEquals(0.0,fx.right,0.0)}
    }
    @Test(expected=IllegalArgumentException::class) fun stereoZeroDelayIsRejectedInsteadOfDividingByZero(){
        ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(4,50,0))))
    }
    private fun module(type:Int,a:Int,b:Int)=ImportedFxModule(type,true,false,
        listOf(a and 255,(a ushr 8) and 255,0,0,b and 255,(b ushr 8) and 255,0,0))
    @Test fun echoRepeatsAtStoredDelayWithFeedbackAndIndependentStereo(){
        val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(6,50,1))),1000)
        fx.process(1.0,0.0);assertEquals(1.0,fx.left,0.0)
        repeat(9){fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)}
        fx.process(0.0,0.0);assertEquals(.5,fx.left,0.0);assertEquals(0.0,fx.right,0.0)
        repeat(10){fx.process(0.0,0.0)};assertEquals(.25,fx.left,0.0)
        fx.reset();repeat(11){fx.process(0.0,0.0);assertEquals(0.0,fx.left,0.0)}
    }
    @Test fun zeroDelayUsesOneMemoryCellLikeOriginalCursorReset(){
        val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module(6,50,0))))
        fx.process(1.0,0.0);fx.process(0.0,0.0);assertEquals(.5,fx.left,0.0)
    }
    private fun settings(enabled:Boolean=true,moduleEnabled:Boolean=true)=ImportedChannelSettings(fxEnabled=enabled,
        modules=listOf(ImportedFxModule(20,moduleEnabled,false,listOf(16,0,0,0,1,0,0,0))))
    @Test fun bassBoosterFollowsStereoRecurrenceWithoutCrossfeed(){
        val fx=ImportedFxChain(settings())
        fx.process(1.0,0.0);assertEquals(1.5,fx.left,0.0);assertEquals(0.0,fx.right,0.0)
        fx.process(0.0,0.0);assertEquals(.25,fx.left,0.0);assertEquals(0.0,fx.right,0.0)
        fx.reset();fx.process(0.0,1.0);assertEquals(0.0,fx.left,0.0);assertEquals(1.5,fx.right,0.0)
    }
    @Test fun disabledBlockOrModulePreservesInput(){
        for(settings in listOf(settings(false),settings(moduleEnabled=false))){
            val fx=ImportedFxChain(settings);fx.process(.2,-.3)
            assertEquals(.2,fx.left,0.0);assertEquals(-.3,fx.right,0.0)
        }
    }
    @Test fun effectRunsOnTrackOutput(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100))
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,channel->if(channel==0)1.0 else 0.0},1000,fx=ImportedFxChain(settings()))
        player.next(0,120);assertEquals(1.5,player.left,0.0)
        player.reset();player.next(0,120);assertEquals(1.5,player.left,0.0)
    }
}
