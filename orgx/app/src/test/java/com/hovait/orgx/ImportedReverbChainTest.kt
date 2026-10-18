package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedReverbChainTest {
    @Test fun nativeStageAllocatesLazilyAndClosesExactlyOnce(){
        val backend=JvmReverbBackend();ImportedNativeReverb.testBackend.set(backend)
        try{
            val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(29,true,false,listOf(40,7,50,50,50,50,50,0))))
            val chain=ImportedFxChain(settings)
            chain.process(0.0,0.0);assertEquals(0,backend.opens)
            chain.process(.5,-.25);assertEquals(1,backend.opens);assertTrue(chain.active)
            assertEquals(.5,chain.left,0.0);assertEquals(-.25,chain.right,0.0)
            chain.reset();assertEquals(1,backend.flushes);assertFalse(chain.active)
            chain.close();chain.close();assertEquals(1,backend.closes)
        }finally{ImportedNativeReverb.testBackend.remove()}
    }
    @Test fun keyboardResetReleasesBothChannelAndMusicNativeHandles(){
        val backend=JvmReverbBackend();ImportedNativeReverb.testBackend.set(backend)
        try{
            val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(29,true,false,listOf(40,7,50,50,50,50,50,0))))
            val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(1000),null),0,0,looping=false)),48000).withKeyboardSettings(settings,true)
            val mixer=ImportedKeyboardMixer();mixer.configureMusic(settings)
            mixer.beginFrame();mixer.add(sound,.5,.5);mixer.finishFrame()
            assertEquals(2,backend.opens)
            mixer.reset();assertEquals(2,backend.closes)
        }finally{ImportedNativeReverb.testBackend.remove()}
    }
}
