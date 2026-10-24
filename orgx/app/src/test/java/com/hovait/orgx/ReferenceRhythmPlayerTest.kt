package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ReferenceRhythmPlayerTest {
    @Test fun resetAndCloseRemoveScriptLayersFromPlayingRegistry(){
        for(close in listOf(false,true)){
            val sound=SampledInstrument(listOf(InstrumentRegion(60,
                SamplePcm(1000,1,ShortArray(128){8192}),0,128)),1000,
                envelope=ImportedEnvelope(0,0,100,100,false))
                .withModeScript("on(touch){play();play();return(true);}")
            val player=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),
                {10000},{_,_,_->0.0},1000,sampled=sound)
            try{
                player.next(0,120);assertTrue(player.left>0)
                if(close)player.close() else player.reset()
                sound.withModeScript("on(touch){play(volume:Playing(released));return(true);}")
                val observer=sound.voice(60);observer.next(false)
                assertEquals(0.0,observer.left,0.0);observer.cut()
            }finally{player.close()}
        }
    }
    @Test fun primitivePadTimingPreservesPcmAndTransportAcrossTempoChangesAndStop(){
        val first=ImportedPadBank(1000);val second=ImportedPadBank(1000)
        val group=ImportedStyleGroup(1,100,"Pad",96,true,0,1500,120,meterX=3,meterY=4)
        fun source()=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)),end=1500)),
            {10000},{_,age,_->(age%13)/13.0},1000)
        try{
            first.play(0,source(),group);second.play(0,source(),group)
            repeat(4000){frame->
                if(frame==1800){first.stopAll();second.stopAll()}
                val tempo=if(frame<1000)120 else 173
                first.next(tempo,.7f,.5f,frame.toLong() to 1000,2)
                second.nextAt(tempo,.7f,.5f,frame.toLong(),1000,2)
                assertEquals(first.left,second.left,0.0);assertEquals(first.right,second.right,0.0)
                assertEquals(first.runningMask,second.runningMask);assertEquals(first.pendingMask,second.pendingMask)
                assertEquals(first.rhythmScale,second.rhythmScale)
            }
        }finally{first.close();second.close()}
    }
    @Test fun unboundedPadLoopsUntilStopAndDoesNotFollowStoredJump(){
        val group=ImportedStyleGroup(1,100,"Loop pad",96,true,0,0,120,limit=false,jumpGroup=2)
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(1,1,60,0,5,0))
        val player=ReferenceRhythmPlayer(listOf(track(events).copy(loop=true,bounded=false)),{10000},{_,_,_->1.0},1000)
        val wrong=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,61,100,0,1000)))),{10000},{_,_,_->100.0},1000)
        val sequence=ImportedPadSequence(1,mapOf(1 to group,2 to group.copy(id=2)),mapOf(1 to player,2 to wrong))
        val bank=ImportedPadBank(1000)
        try{
            assertTrue(bank.playPrepared(0,bank.beginLoad(0),sequence))
            var laterEnergy=0.0
            repeat(80){frame->
                bank.next(120,1f,1f)
                assertEquals(1,sequence.currentId);assertEquals(1,bank.runningMask)
                if(frame>30)laterEnergy+=bank.left*bank.left
            }
            assertTrue(laterEnergy>0)
            bank.stop(0);repeat(30){bank.next(120,1f,1f)}
            assertEquals(0,bank.runningMask);assertEquals(0.0,bank.left,0.0)
        }finally{bank.close()}
    }
    @Test fun sectionRestartKeepsChannelControlsAndReleasesOldNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),
            ReferenceDrumEvent(44,0,50,0,5,0),ReferenceDrumEvent(45,0,100,0,6,0))
        val player=ReferenceRhythmPlayer(listOf(track(events)),{10000},{_,_,_->1.0},1000,
            initialChannel=ImportedChannelSettings())
        try{
            player.next(0,120);assertEquals(1.0,player.left,0.0)
            repeat(6){player.next(0,120)}
            assertEquals(0.0,player.left,0.0);assertEquals(.5,player.right,0.0)
            player.restartAt(0);player.next(0,120)
            assertEquals(0.0,player.left,0.0);assertEquals(1.0,player.right,0.0)
            player.next(0,120);assertEquals(.95,player.right,1e-9)
            repeat(20){player.next(0,120)}
            assertEquals(.5,player.right,1e-9)
            player.reset();player.next(0,120)
            assertEquals(1.0,player.left,0.0);assertEquals(1.0,player.right,0.0)
        }finally{player.close()}
    }
    @Test fun chordQualityUsesOriginalSlotsAndKeepsPercussionAndOtherInstruments(){
        fun output(level:Int):Double{
            val quality=ImportedChordQuality{level}
            val parts=listOf(Triple(7,true,1.0),Triple(8,true,2.0),Triple(9,true,4.0),
                Triple(1,true,8.0),Triple(1,true,16.0),Triple(8,false,32.0),Triple(11,true,64.0))
            val player=ReferenceRhythmPlayer.combine(parts.map{(instrument,melodic,value)->
                ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),
                    {10000},{_,_,_->value},1000,accompaniment=melodic)
                    .withTrackGate{muted->quality.allows(instrument,melodic,muted)}
            }).withFrameStart(quality::beginFrame)
            return try{player.next(0,120);player.left}finally{player.close()}
        }
        assertEquals(104.0,output(0),0.0)
        assertEquals(109.0,output(1),0.0)
        assertEquals(127.0,output(2),0.0)
    }
    @Test fun chordQualitySkipsNewEventsButRetainsAlreadyPlayingNotes(){
        var level=2
        val quality=ImportedChordQuality{level}
        val player=ReferenceRhythmPlayer.combine(listOf(
            ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1),
                ReferenceDrumEvent(2,2,61,100,20,1000)))),{10000},{_,_,_->1.0},1000,accompaniment=true)
                .withTrackGate{muted->quality.allows(8,true,muted)}
        )).withFrameStart(quality::beginFrame)
        try{
            player.next(0,120);assertEquals(1.0,player.left,0.0)
            level=0
            repeat(25){player.next(0,120)}
            assertEquals(1.0,player.left,0.0)
            level=2;player.next(0,120)
            assertEquals(2.0,player.left,0.0)
        }finally{player.close()}
    }
    @Test fun padMeterChangesTheSharedWindowBeforeStartingAnotherSlot(){
        val group=ImportedStyleGroup(1,100,"Three beats",96,true,0,1500,120,meterX=3,meterY=4)
        fun source()=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,5000)),end=1500)),
            {10000},{_,_,_->1.0},1000)
        val bank=ImportedPadBank(1000)
        try{
            val first=ImportedPadSequence(1,mapOf(1 to group),mapOf(1 to source()))
            assertTrue(bank.playPrepared(0,bank.beginLoad(0),first))
            bank.next(120,1f,1f,method=2)
            assertEquals(1500,bank.rhythmScale);assertTrue(bank.scaleChanged)
            repeat(999){bank.next(120,1f,1f,method=2)}
            bank.play(1,source(),group.copy(id=2,section=97))
            bank.next(120,1f,1f,method=2)
            assertEquals(1,bank.runningMask);assertEquals(2,bank.pendingMask)
            repeat(499){bank.next(120,1f,1f,method=2)}
            bank.next(120,1f,1f,method=2)
            assertEquals(3,bank.runningMask);assertEquals(0,bank.pendingMask)
        }finally{bank.close()}
    }
    @Test fun automaticPadTimingRetainsTheStyleScale(){
        val bank=ImportedPadBank(1000)
        val group=ImportedStyleGroup(1,100,"Pad",96,true,0,1500,120,meterX=3,meterY=4)
        val player=ReferenceRhythmPlayer(listOf(track(listOf(hit(0)),end=1500)),{10000},{_,_,_->1.0},1000)
        try{
            bank.play(0,player,group)
            bank.next(120,1f,1f,styleTiming=0L to 500,method=0)
            assertEquals(500,bank.rhythmScale);assertFalse(bank.scaleChanged)
        }finally{bank.close()}
    }
    @Test fun padJumpChainAdvancesOnBoundaryAndKeepsRetiringVoice(){
        val a=ImportedStyleGroup(1,100,"First",96,false,0,5,120,jumpGroup=2)
        val b=ImportedStyleGroup(2,100,"Next",97,true,0,1000,120)
        fun source(value:Double,group:ImportedStyleGroup)=ReferenceRhythmPlayer(listOf(
            track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)),end=group.end.toLong()).copy(loop=group.loop)),
            {10000},{_,_,_->value},1000)
        val sequence=ImportedPadSequence(1,mapOf(1 to a,2 to b),mapOf(1 to source(1.0,a),2 to source(2.0,b)))
        val bank=ImportedPadBank(1000)
        try{
            val token=bank.beginLoad(0);assertTrue(bank.playPrepared(0,token,sequence))
            repeat(5){bank.next(120,1f,1f)}
            assertEquals(1,sequence.currentId)
            bank.next(120,1f,1f)
            assertEquals(2,sequence.currentId);assertEquals(3.0,bank.left,1e-9)
            repeat(20){bank.next(120,1f,1f)}
            assertEquals(2.0,bank.left,1e-9);assertEquals(1,bank.runningMask)
            bank.stopAll();repeat(20){bank.next(120,1f,1f)}
            assertEquals(0,bank.runningMask);assertEquals(0.0,bank.left,0.0)
        }finally{bank.close()}
    }
    @Test fun stoppedPadJumpChainCannotStartWhenItsLoadCompletes(){
        val group=ImportedStyleGroup(1,100,"Pad",96,false,0,5,120,jumpGroup=1)
        val player=ReferenceRhythmPlayer(listOf(track(listOf(hit(0)),end=5).copy(loop=false)),{1000},{_,_,_->1.0},1000)
        val sequence=ImportedPadSequence(1,mapOf(1 to group),mapOf(1 to player))
        val bank=ImportedPadBank(1000)
        try{
            val token=bank.beginLoad(0);bank.stopAll()
            assertFalse(bank.playPrepared(0,token,sequence))
            bank.next(120,1f,1f);assertEquals(0.0,bank.left,0.0)
        }finally{bank.close()}
    }
    @Test fun oneShotBoundaryKeepsReleaseAudioWithoutSchedulingAnotherCycle(){
        val player=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,100)),end=5).copy(loop=false)),
            {1000},{_,_,_->1.0},1000)
        repeat(5){player.next(0,120)}
        player.next(0,120)
        assertTrue(player.finished);assertTrue(player.hasTail);assertEquals(1.0,player.left,0.0)
        player.next(0,120);assertEquals(.9,player.left,1e-9)
        repeat(20){player.next(0,120)}
        assertFalse(player.hasTail);assertEquals(0.0,player.left,0.0)
        player.close()
    }
    @Test fun replacingPadKeepsOldReleaseAlongsideNewNotes(){
        fun pad(value:Double)=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),
            {10000},{_,_,_->value},1000)
        val bank=ImportedPadBank(1000)
        try{
            bank.play(0,pad(1.0));bank.next(120,1f,1f)
            bank.play(0,pad(2.0));bank.next(120,1f,1f)
            assertEquals(3.0,bank.left,1e-9)
            bank.next(120,1f,1f);assertEquals(2.9,bank.left,1e-9)
            repeat(20){bank.next(120,1f,1f)}
            assertEquals(2.0,bank.left,1e-9);assertEquals(1,bank.runningMask)
        }finally{bank.close()}
    }
    @Test fun padSectionsKeepTheirSlotsAndNeverUseAnotherStyle(){
        fun group(id:Int,parent:Int,section:Int)=ImportedStyleGroup(id,parent,"Pad $id",section,true,0,1000,120)
        val unrelated=group(1,200,96)
        val shared=group(2,0,97)
        val local=group(3,100,97)
        val fourth=group(4,100,99)
        val index=ImportedSetIndex(emptyList(),listOf(unrelated,shared,fourth,local))
        assertEquals(listOf(null,local,null,fourth),ImportedStyleCatalog.pads(index,100))
        assertEquals(listOf(unrelated,shared,null,null),ImportedStyleCatalog.pads(index,200))
        assertEquals(listOf(null,shared,null,null),ImportedStyleCatalog.pads(index,null))
    }
    @Test fun stoppingPadsRejectsAPlayerThatFinishedLoadingAfterStop(){
        val group=ImportedStyleGroup(9,100,"Pad",96,true,0,1000,120)
        fun pad()=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),
            {10000},{_,_,_->1.0},1000)
        val bank=ImportedPadBank(1000)
        try{
            val old=bank.beginLoad(0)
            bank.stopAll()
            assertFalse(bank.playPrepared(0,old,pad(),group))
            bank.next(120,1f,1f);assertEquals(0,bank.runningMask);assertEquals(0.0,bank.left,0.0)
            val current=bank.beginLoad(0)
            assertTrue(bank.playPrepared(0,current,pad(),group))
            bank.next(120,1f,1f);assertEquals(1,bank.runningMask);assertEquals(1.0,bank.left,0.0)
        }finally{bank.close()}
    }
    @Test fun setPadWaitsForStyleWindowAndStartsAtGroupPhase(){
        val group=ImportedStyleGroup(9,100,"Pad",96,true,0,2000,120)
        val data=ReferenceDrumTrack("Pad",9,100,96,120,true,0,2000,false,listOf(
            ReferenceDrumEvent(2,1,60,100,0,5),ReferenceDrumEvent(2,2,61,100,1000,1000)))
        val pad=ReferenceRhythmPlayer(listOf(data),{10000},{note,_,_->note.toDouble()},1000)
        val bank=ImportedPadBank(1000)
        try{
            bank.play(0,pad,group)
            bank.next(120,1f,1f,250L to 1000,2)
            assertEquals(0,bank.runningMask);assertEquals(1,bank.pendingMask);assertEquals(0.0,bank.left,0.0)
            bank.next(120,1f,1f,1000L to 1000,2)
            assertEquals(1,bank.runningMask);assertEquals(0,bank.pendingMask)
            assertEquals(61.0,bank.left,0.0) // Phase1000; expired opening hit is not replayed.
            bank.play(1,ReferenceRhythmPlayer(listOf(data),{10000},{_,_,_->1.0},1000),group)
            bank.next(120,1f,1f,1250L to 1000,2);assertEquals(2,bank.pendingMask)
            bank.stop(1);bank.next(120,1f,1f,2000L to 1000,2)
            assertEquals(0,bank.pendingMask);assertEquals(1,bank.runningMask)
        }finally{bank.close()}
    }
    @Test fun importedPadsMixIndependentSlotsAndReleaseWithoutStoppingOtherPad(){
        fun pad(value:Double)=ReferenceRhythmPlayer(listOf(track(listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))),
            {10000},{_,_,_->value},1000)
        val bank=ImportedPadBank()
        try{
            bank.play(0,pad(1.0));bank.play(1,pad(2.0));bank.next(120,1f,1f)
            assertEquals(3,bank.runningMask);assertEquals(3.0,bank.left,1e-12)
            bank.stop(0);repeat(12){bank.next(120,1f,1f)}
            assertEquals(2,bank.runningMask);assertEquals(2.0,bank.left,1e-12)
            bank.stopAll();repeat(12){bank.next(120,1f,1f)}
            assertEquals(0,bank.runningMask);assertEquals(0.0,bank.left,0.0)
        }finally{bank.close()}
    }
    @Test fun unmuteCatchesUpControlsAndLongNotesButSkipsExpiredHits(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,5),
            ReferenceDrumEvent(44,0,25,0,10,0),
            ReferenceDrumEvent(2,2,60,100,20,1000),ReferenceDrumEvent(1,2,60,0,60,0))
        val player=ReferenceRhythmPlayer(listOf(track(events)),{10000},{_,_,_->1.0},1000,
            accompaniment=true,initialChannel=ImportedChannelSettings())
        player.setPerformanceMute(true,false)
        repeat(41){player.next(0,120);assertEquals(0.0,player.left,0.0)}
        player.setPerformanceMute(false,false);player.next(0,120)
        assertEquals(.25,player.left,1e-12) // One long note, with its overdue volume event.
        player.setPerformanceMute(true,false)
        repeat(30){player.next(0,120)} // Its note-off remains undispatched while muted.
        assertEquals(.25,player.left,1e-12)
        player.setPerformanceMute(false,false)
        repeat(12){player.next(0,120)}
        assertEquals(0.0,player.left,0.0)
    }
    @Test fun performanceMuteBlocksOnlySelectedGroupAndRetainsExistingNotes(){
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(2,2,60,100,20,1000))
        fun player(melodic:Boolean)=ReferenceRhythmPlayer(listOf(track(events)),{10000},{_,_,_->1.0},1000,accompaniment=melodic)
        val chord=player(true);val drum=player(false)
        chord.setPerformanceMute(true,false);drum.setPerformanceMute(true,false)
        repeat(25){chord.next(0,120);drum.next(0,120)}
        assertEquals(0.0,chord.left,0.0);assertEquals(2.0,drum.left,0.0)
        drum.setPerformanceMute(false,true);drum.next(0,120)
        assertEquals(2.0,drum.left,0.0) // Existing voices are not a mixer hard mute.
        chord.setPerformanceMute(false,false);chord.reset();chord.next(0,120)
        assertEquals(1.0,chord.left,0.0)
    }
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
