package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFxChainTest {
    @Test fun distortionAutomationPreservesPackedBooleanAndUsesNonzeroParameterMinimum(){
        val original=ImportedFxModule(23,true,false,listOf(20,0,0,0,1,10,0,0,0))
        val gain=ImportedTrackFxAutomation.apply(original,ReferenceDrumEvent(66,0,1,-100,0,0))
        assertEquals(listOf(1,0,0,0,1,10,0,0,0),gain.payload)
        val cutoff=ImportedTrackFxAutomation.apply(gain,ReferenceDrumEvent(66,0,3,0,0,0))
        assertEquals(listOf(1,0,0,0,1,51,0,0,0),cutoff.payload)
        val hard=ImportedTrackFxAutomation.apply(cutoff,ReferenceDrumEvent(66,0,2,100,0,0))
        assertEquals(listOf(1,0,0,0,0,51,0,0,0),hard.payload)
        val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(hard)))
        try{repeat(100){fx.process(.25,0.0);assertTrue(fx.left.isFinite());assertEquals(0.0,fx.right,0.0)}}finally{fx.close()}
        assertFalse(ImportedTrackFxAutomation.supported(original,ReferenceDrumEvent(66,0,4,0,0,0)))
    }
    @Test fun singleParameterAutomationUsesFourBytePayloadAndOriginalRanges(){
        for((type,maximum) in listOf(9 to 150000,10 to 150000,16 to 1000,17 to 50000)){
            val module=ImportedFxModule(type,true,false,listOf(0,0,0,0))
            for(level in listOf(-100,0,100)){
                val event=ReferenceDrumEvent(66,0,1,level,0,0)
                val updated=ImportedTrackFxAutomation.apply(module,event)
                val bytes=updated.payload
                val value=bytes[0] or (bytes[1] shl 8) or (bytes[2] shl 16) or (bytes[3] shl 24)
                assertEquals((level+100)*maximum/200,value)
                assertEquals(4,bytes.size)
                val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(updated)))
                try{repeat(100){fx.process(if(it==0).25 else 0.0,0.0);assertTrue(fx.left.isFinite());assertTrue(fx.right.isFinite())}}finally{fx.close()}
            }
            assertFalse(ImportedTrackFxAutomation.supported(module,ReferenceDrumEvent(66,0,2,0,0,0)))
        }
    }
    @Test fun bassAndBandPassAutomationUseTheirOriginalParameterRanges(){
        fun value(bytes:List<Int>,offset:Int)=bytes[offset] or (bytes[offset+1] shl 8) or
            (bytes[offset+2] shl 16) or (bytes[offset+3] shl 24)
        val bass=ImportedTrackFxAutomation.apply(module(20,0,80),ReferenceDrumEvent(66,0,1,0,0,0))
        assertEquals(50,value(bass.payload,0));assertEquals(80,value(bass.payload,4))
        val filter=ImportedTrackFxAutomation.apply(module(1,1000,2000),ReferenceDrumEvent(66,0,1,0,0,0))
        assertEquals(75000,value(filter.payload,0));assertEquals(2000,value(filter.payload,4))
        val wide=ImportedTrackFxAutomation.apply(filter,ReferenceDrumEvent(66,0,2,100,0,0))
        assertEquals(75000,value(wide.payload,0));assertEquals(25000,value(wide.payload,4))
        val fx=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(wide)))
        try{repeat(100){fx.process(if(it==0)1.0 else 0.0,0.0);assertTrue(fx.left.isFinite());assertEquals(0.0,fx.right,0.0)}}finally{fx.close()}
    }
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
