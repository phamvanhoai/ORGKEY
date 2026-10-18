package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StylePitchMoveTest {
    @Test fun balanceCommandChangesActiveStereoAndResetClearsIt(){
        val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,shortArrayOf(8192,8192)),0,2)),1000)
        val track=ReferenceDrumTrack("Track",1,0,32,120,true,0,1000,false,listOf(
            ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(52,0,100,0,1,0)))
        val player=ReferenceRhythmPlayer(listOf(track),sound::durationFrames,{n,a,c->sound.sample(n,a.toLong(),c)},1000,sampled=sound)
        try{
            player.next(0,120);assertEquals(.25,player.left,1e-9)
            player.next(0,120);assertEquals(0.0,player.left,0.0);assertEquals(.25,player.right,1e-9)
            player.reset();player.next(0,120);assertEquals(.25,player.left,1e-9)
        }finally{player.close()}
    }
    @Test fun pitchCommandKeepsCursorAndResetRestoresNormalSpeed(){
        val pcm=SamplePcm(1000,1,shortArrayOf(0,1000,2000,3000,4000,5000,6000,7000))
        val sound=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,8)),1000)
        val track=ReferenceDrumTrack("Track",1,0,32,120,true,0,1000,false,listOf(
            ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(53,0,2000,0,1,0)))
        val player=ReferenceRhythmPlayer(listOf(track),sound::durationFrames,{n,a,c->sound.sample(n,a.toLong(),c)},1000,sampled=sound)
        try{
            player.next(0,120);player.next(0,120)
            assertEquals(1000.0/32768,player.left,1e-9)
            player.next(0,120);assertEquals(3000.0/32768,player.left,1e-9)
            player.reset();player.next(0,120);assertEquals(0.0,player.left,0.0)
        }finally{player.close()}
    }
}
