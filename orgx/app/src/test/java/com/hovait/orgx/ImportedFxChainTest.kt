package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFxChainTest {
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
