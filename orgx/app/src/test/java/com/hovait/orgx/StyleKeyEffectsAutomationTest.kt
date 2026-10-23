package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StyleKeyEffectsAutomationTest {
    private fun sound()=SampledInstrument(listOf(InstrumentRegion(60,
        SamplePcm(1000,1,ShortArray(2000){8192}),0,2000)),1000)
    private fun player(events:List<ReferenceDrumEvent>,effects:List<ImportedExpressionEffect>):ReferenceRhythmPlayer {
        val sound=sound();val track=ReferenceDrumTrack("Main",1,0,32,120,true,0,1000,false,events)
        return ReferenceRhythmPlayer(listOf(track),sound::durationFrames,{n,a,c->sound.sample(n,a.toLong(),c)},
            1000,sampled=sound,expressionEffects=effects)
    }
    @Test fun timelineEnablesKeyRepeatForActiveAndFutureState(){
        val disabled=ImportedExpressionEffect(0,false,0,false,false,0,500,0,0,emptyList())
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),
            ReferenceDrumEvent(65,0,0,1,1,0),ReferenceDrumEvent(65,0,4,500,1,0))
        val player=player(events,listOf(disabled))
        try{
            var peak=0.0
            repeat(300){player.next(0,120);peak=maxOf(peak,player.left)}
            assertTrue("Key Repeat automation did not add an overlapping voice",peak>.25)
        }finally{player.close()}
    }
    @Test fun timelineHighConnectionModeTransfersExistingVoice(){
        val disabled=ImportedExpressionEffect(1,false,0,false,true,0,250,0,0,emptyList())
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),
            ReferenceDrumEvent(65,1,0,1,1,0),ReferenceDrumEvent(65,1,7,16,1,0),
            ReferenceDrumEvent(2,2,72,100,2,1000))
        val connected=player(events,listOf(disabled))
        val plain=player(events.filter{it.type!=65},emptyList())
        try{
            repeat(300){connected.next(0,120);plain.next(0,120)}
            assertTrue(plain.left>connected.left*1.5)
        }finally{connected.close();plain.close()}
    }
}
