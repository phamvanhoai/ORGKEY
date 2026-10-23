package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceRhythmPlayerTest {
    @Test fun styleDncReceivesEventIdAndSignedPackedBalance(){
        val source=SampledInstrument(listOf(InstrumentRegion(36,SamplePcm(1000,1,ShortArray(16){8192}),0,16)),1000)
            .withModeScript("on(touch){if(id==37 && balance==-20){play(volume:50,balance:balance);}else{play(volume:0);}return(true);}")
        val packed=100 or (-20 shl 16)
        val player=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,37,36,packed,0,100)))),
            source::durationFrames,{n,a,c->source.sample(n,a.toLong(),c)},1000,sampled=source)
        try{
            player.next(0,120)
            assertEquals(.125,player.left,1e-12)
            assertEquals(.1,player.right,1e-12)
            assertFalse(source.scriptStatus,source.scriptStatus.contains("fallback"))
        }finally{player.close()}
    }
    @Test fun styleDncReceivesNoteVelocityInsteadOfDefaultHundred(){
        fun render(velocity:Int):Double {
            val source=SampledInstrument(listOf(InstrumentRegion(36,SamplePcm(1000,1,ShortArray(16){8192}),0,16)),1000)
                .withModeScript("on(touch){if(velocity<100){play(volume:50,velocity:velocity);}else{play(volume:100,velocity:velocity);}return(true);}")
            val player=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,36,velocity,0,100)))),
                source::durationFrames,{n,a,c->source.sample(n,a.toLong(),c)},1000,sampled=source)
            try{player.next(0,120);return player.left}finally{player.close()}
        }
        assertEquals(.0625,render(50),1e-12)
        assertEquals(.3,render(120),1e-12)
    }
    @Test fun styleDncReceivesHeldPedalAndTimelinePedalCallbacks(){
        val source=SampledInstrument(listOf(InstrumentRegion(36,SamplePcm(1000,1,ShortArray(16){8192}),0,16)),1000)
            .withModeScript("""
                main(){held=0;}
                on(pedalon){held=held+1;}
                on(pedaloff){held=0;}
                on(touch){if(pedal && held==1){play(volume:25);}else{play(volume:100);}return(true);}
            """.trimIndent())
        val events=listOf(ReferenceDrumEvent(12,0,0,0,0,0),ReferenceDrumEvent(2,1,36,100,0,100),
            ReferenceDrumEvent(11,0,0,0,1,0))
        val player=ReferenceRhythmPlayer(listOf(track(events)),source::durationFrames,{n,a,c->source.sample(n,a.toLong(),c)},1000,sampled=source)
        try{
            player.next(0,120);assertEquals(.0625,player.left,1e-12)
            player.next(0,120)
            val voice=source.voice(36,initialScriptPedal=true)
            voice.next(false);assertEquals(.25,voice.left,1e-12)
        }finally{player.close()}
    }
    @Test fun tailStateBecomesIdleAfterVoicesFinishAndIncludesChildPlayers(){
        val child=ReferenceRhythmPlayer(listOf(track(listOf(hit(0)))),{1000},{_,_,_->1.0},1000)
        val combined=ReferenceRhythmPlayer.combine(listOf(child))
        assertFalse(combined.hasTail)
        combined.next(0,120);assertTrue(combined.hasTail)
        combined.releaseAll()
        repeat(20){combined.renderTail()}
        assertFalse(combined.hasTail);assertEquals(0.0,combined.left,0.0)
    }
    @Test fun tailRenderingReleasesVoicesWithoutSchedulingNewNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100),ReferenceDrumEvent(2,2,60,100,2,100))
        val player=ReferenceRhythmPlayer(listOf(track(events)),{1000},{_,_,_->1.0},1000)
        player.next(0,120);assertEquals(1.0,player.left,0.0)
        player.releaseAll();player.renderTail();assertEquals(1.0,player.left,0.0)
        player.renderTail();assertEquals(.9,player.left,1e-9)
        repeat(20){player.renderTail()};assertEquals(0.0,player.left,0.0)
    }
    @Test fun loopBoundaryReleasesOldNotesInsteadOfErasingThem(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100))
        val track=track(events,end=5)
        val player=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000)
        repeat(5){player.next(0,120);assertEquals(1.0,player.left,0.0)}
        player.next(0,120)
        assertEquals(2.0,player.left,0.0)
        player.next(0,120)
        assertEquals(1.9,player.left,1e-9)
        player.reset();player.next(0,120);assertEquals(1.0,player.left,0.0)
    }
    @Test fun oneShotStopsAtEndAndCanRestartAfterReset() {
        val player=player(track(listOf(hit(0)),end=10).copy(loop=false))
        player.next(0,120);assertEquals(1.0,player.left,0.0)
        repeat(20){player.next(0,120)}
        assertTrue(player.finished);assertEquals(0.0,player.left,0.0)
        player.reset();player.next(0,120)
        assertFalse(player.finished);assertEquals(1.0,player.left,0.0)
    }
    @Test fun combinedTracksSumStereoAndResetTogether() {
        val a=player(track(listOf(hit(0))))
        val b=player(track(listOf(hit(0))))
        val combined=ReferenceRhythmPlayer.combine(listOf(a,b))
        combined.next(0,120)
        assertEquals(2.0,combined.left,0.0);assertEquals(-2.0,combined.right,0.0)
        combined.next(0,120);assertEquals(0.0,combined.left,0.0)
        combined.reset();combined.next(0,120);assertEquals(2.0,combined.left,0.0)
    }
    @Test fun perStyleHeadroomIsAppliedToBothChannels() {
        val player=player(track(listOf(hit(0))).copy(outputGain=0.7))
        player.next(0,120)
        assertEquals(0.7,player.left,1e-9);assertEquals(-0.7,player.right,1e-9)
    }
    private fun track(events:List<ReferenceDrumEvent>,end:Long=1000)=ReferenceDrumTrack("Test",1,0,32,120,true,0,end,false,events)
    private fun hit(time:Long)=ReferenceDrumEvent(2,1,36,100,time,100)
    private fun player(track:ReferenceDrumTrack)=ReferenceRhythmPlayer(listOf(track),{10000},
        {_,age,channel->if(age==0)if(channel==0)1.0 else -1.0 else 0.0},1000)
    @Test fun schedulesHitsAtTempoAndWrapsLoop() {
        val player=player(track(listOf(hit(0),hit(500))))
        val hits=mutableListOf<Int>()
        repeat(1001){frame->player.next(0,120);if(player.left!=0.0){hits.add(frame);assertEquals(-player.left,player.right,0.0)}}
        assertEquals(listOf(0,500,1000),hits)
    }
    @Test fun doubleTempoHalvesFrameDistance() {
        val player=player(track(listOf(hit(0),hit(500))))
        val hits=mutableListOf<Int>()
        repeat(501){frame->player.next(0,240);if(player.left!=0.0)hits.add(frame)}
        assertEquals(listOf(0,250,500),hits)
    }
    @Test fun releaseFadesAndResetRemovesActiveVoices() {
        val events=listOf(hit(0),ReferenceDrumEvent(1,1,36,-1,10,-1))
        val player=ReferenceRhythmPlayer(listOf(track(events)),{10000},{_,_,_->1.0},1000)
        repeat(11){player.next(0,120)}
        assertEquals(1.0,player.left,0.0)
        repeat(10){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
        player.reset();assertEquals(0.0,player.right,0.0)
        player.next(0,120);assertEquals(1.0,player.left,0.0)
    }
}
