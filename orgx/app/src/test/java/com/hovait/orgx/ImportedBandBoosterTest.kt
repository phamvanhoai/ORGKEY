package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedBandBoosterTest {
    @Test fun tailBudgetUsesAudioRateAndResetClearsState(){
        val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(31,true,false,listOf(50,236,80,0))))
        for(rate in listOf(32000,44100,48000,96000)){
            val chain=ImportedFxChain(settings,rate)
            try{
                chain.process(.5,0.0);assertTrue(chain.active)
                repeat(rate/10-1){chain.process(0.0,0.0)}
                assertTrue(chain.active)
                chain.process(0.0,0.0);assertFalse(chain.active)
                chain.process(.5,0.0);chain.reset()
                assertFalse(chain.active)
                repeat(8){chain.process(0.0,0.0);assertEquals(0.0,chain.left,0.0);assertEquals(0.0,chain.right,0.0)}
            }finally{chain.close()}
        }
    }
    @Test fun chainDecodesSignedBandsAndUsesOriginalInterleavedChannelState(){
        val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(31,true,false,listOf(50,236,80,0))))
        val chain=ImportedFxChain(settings)
        val reference=ReferenceBandBooster(50,-20,80,48000)
        try{
            repeat(100){i->
                val l=if(i==0)12000L else 0L;val r=if(i==2)-8000L else 0L
                chain.process(l/32768.0,r/32768.0)
                assertEquals(reference.process(l)/32768.0,chain.left,0.0)
                assertEquals(reference.process(r)/32768.0,chain.right,0.0)
            }
            chain.reset();reference.reset()
            chain.process(.25,0.0)
            assertEquals(reference.process(8192)/32768.0,chain.left,0.0)
            assertEquals(reference.process(0)/32768.0,chain.right,0.0)
        }finally{chain.close()}
    }
}
