package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class SampledInstrumentTest {
    @org.junit.Test fun legatoPitchUsesTheSampleStillPlayingAcrossRegionBoundaries(){
        val samples=ShortArray(128){(it*100).toShort()}
        val instrument=SampledInstrument(listOf(
            InstrumentRegion(60,SamplePcm(1000,1,samples),0,128),
            InstrumentRegion(72,SamplePcm(2000,1,samples),0,128)),1000)
        val connection=ImportedExpressionEffect.default(1).copy(enabled=true,mode=2,stopByRelease=false)
        val voice=instrument.voice(60)
        repeat(3){voice.next(false)} // old sample position3
        voice.connectTo(72,connection)
        voice.next(false);assertEquals(300.0/32768,voice.left,1e-12)
        voice.next(false);assertEquals(500.0/32768,voice.left,1e-12) // old root60 -> key72 = step2
        voice.connectTo(60,connection)
        voice.next(false);voice.next(false)
        assertEquals(800.0/32768,voice.left,1e-12)
        val opened=instrument.voice(72)
        opened.connectFrom(60,connection.copy(mode=3,speed=1))
        opened.next(false);opened.next(false)
        // New root72/sampleRate2000 starts at key60: step ~1, not the old region's unrelated mapping.
        assertEquals(100.0/32768,opened.left,1e-6)
    }
    @org.junit.Test fun connectionReopensForcedReleaseAndSustainLoopForTheNewKey(){
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(40){if(it<4)4000 else 16000}),0,4,loopUntilRelease=true)),
            1000,envelope=ImportedEnvelope(0,0,100,20,false))
        val voice=instrument.voice(60)
        repeat(10){voice.next(false)}
        voice.forceRelease();repeat(3){voice.next(false)}
        val connection=ImportedExpressionEffect.default(1).copy(enabled=true,stopByRelease=false,mode=2)
        voice.connectTo(60,connection)
        repeat(50){
            voice.next(false)
            assertFalse(voice.finished)
            assertEquals(4000.0/32768,voice.left,1e-9)
        }
        voice.forceRelease();repeat(50){voice.next(false)}
        assertTrue(voice.finished)
    }
    @org.junit.Test fun releaseLoopExitsAtItsCurrentPhaseAndPlaysTheStoredTail(){
        fun voice(untilRelease:Boolean)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,shortArrayOf(1000,2000,3000,4000,5000,6000,7000,8000)),2,4,
            loopUntilRelease=untilRelease)),1000,envelope=ImportedEnvelope(0,0,100,20,false)).voice(60)
        val held=voice(true);val continuous=voice(false)
        repeat(10){held.next(false);continuous.next(false)}
        assertEquals(continuous.left,held.left,0.0)
        held.next(true);continuous.next(true)
        held.next(true);continuous.next(true)
        held.next(true);continuous.next(true)
        assertTrue(held.left>continuous.left) // Exit has reached frame4; continuous still loops frame2.
        repeat(8){held.next(true);continuous.next(true)}
        assertTrue(held.finished);assertFalse(continuous.finished)
    }
    @org.junit.Test fun loopTempoScalesOnlyModeThreeAndTracksLiveBpmWithoutRestart(){
        val oldEnabled=SampledInstrument.loopTempo;val oldTempo=SampledInstrument.tempo
        try{
            SampledInstrument.loopTempo=true;SampledInstrument.tempo=120
            fun voice(sync:Boolean)=SampledInstrument(listOf(InstrumentRegion(60,
                SamplePcm(1000,1,ShortArray(128){(it*100).toShort()}),0,128,tempoSync=sync)),1000).voice(60)
            val synced=voice(true);val plain=voice(false)
            synced.next(false);plain.next(false)
            SampledInstrument.tempo=240
            synced.next(false);plain.next(false)
            synced.next(false);plain.next(false)
            org.junit.Assert.assertEquals(300.0/32768,synced.left,1e-12)
            org.junit.Assert.assertEquals(200.0/32768,plain.left,1e-12)
            SampledInstrument.loopTempo=false
            synced.next(false);synced.next(false)
            org.junit.Assert.assertEquals(600.0/32768,synced.left,1e-12)
        }finally{SampledInstrument.loopTempo=oldEnabled;SampledInstrument.tempo=oldTempo}
    }
    @org.junit.Test fun pitchSpeedRampsHeldPcmButNewNotesStartAtCurrentBend(){
        val previous=SampledInstrument.pitchSpeed
        try{
            SampledInstrument.pitchSpeed=2
            val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(256){(it*100).toShort()}),0,256)),1000)
            val held=sound.voice(60)
            held.next(false);held.next(false);held.pitchBend(12.0)
            held.next(false);held.next(false)
            org.junit.Assert.assertTrue(held.left>300.0/32768 && held.left<400.0/32768)
            val fresh=sound.voice(60);fresh.pitchBend(12.0)
            fresh.next(false);fresh.next(false)
            org.junit.Assert.assertEquals(200.0/32768,fresh.left,1e-12)
            held.pitchBend(0.0);repeat(100){held.next(false);org.junit.Assert.assertTrue(held.left.isFinite())}
        }finally{SampledInstrument.pitchSpeed=previous}
    }
    @Test fun keyConnectionByPedalPausesDelayAndPitchMovement(){
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(16){((it+1)*1000).toShort()}),0,0,looping=false)),1000)
        val playback=instrument.voice(72)
        playback.connectFrom(60,ImportedExpressionEffect(1,true,0,true,false,0,819,0,0,emptyList()))
        playback.next(false,false);playback.next(false,false)
        assertEquals(2000.0/32768,playback.left,1e-9)
        playback.next(false,true);playback.next(false,true)
        assertEquals(4100.0/32768,playback.left,2e-5)
    }
    @Test fun lowConnectionModeAboveOneStartsAtTargetLoopPoint(){
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(16){((it+1)*1000).toShort()}),5,16)),1000)
        val playback=instrument.voice(72)
        playback.connectFrom(60,ImportedExpressionEffect(1,true,0,false,false,3,819,0,0,emptyList()))
        playback.next(false)
        assertEquals(6000.0/32768,playback.left,1e-9)
    }
    @Test fun keyConnectionRetargetsPitchWithoutRestartingSamplePosition(){
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(16){((it+1)*1000).toShort()}),0,0,looping=false)),1000)
        val playback=instrument.voice(60)
        playback.next(false)
        assertEquals(1000.0/32768,playback.left,1e-9)
        playback.connectTo(72,ImportedExpressionEffect(1,true,0,false,false,16,0,0,0,emptyList()))
        playback.next(false)
        assertEquals(2000.0/32768,playback.left,1e-9)
        playback.next(false)
        assertEquals(4000.0/32768,playback.left,1e-9)
    }
    @Test fun aftertouchCanOutlivePrimaryAndRepeatedPressureDoesNotRestartIt(){
        fun sound(samples:ShortArray)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(48000,1,samples),0,0,looping=false)),48000)
        val primary=sound(shortArrayOf(1000,1000))
        val playback=primary.withAftertouch(sound(shortArrayOf(2000,4000,6000,8000))).voice(60)
        playback.next(false)
        playback.aftertouch(1f);playback.next(false)
        assertEquals(2000.0/32768,playback.left,1e-9)
        assertTrue(primary.finished(60,2))
        playback.aftertouch(1f);playback.next(false)
        assertEquals(4000.0/32768,playback.left,1e-9)
        assertFalse(playback.finished)
        playback.next(false);playback.next(false)
        assertTrue(playback.finished)
    }
    @Test fun aftertouchSwitchesAnActiveVoiceToAlternateLayer() {
        val primary=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(8192,8192)),0,2,looping=false)),48000)
        val alternate=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(16384,16384)),0,2,looping=false)),48000)
        val playback=primary.withAftertouch(alternate).voice(60)
        playback.next(false)
        assertEquals(8192.0/32768,playback.left,1e-9)
        playback.aftertouch(1f/127f)
        playback.next(false)
        assertEquals(16384.0/32768,playback.left,1e-9)
    }
    @Test fun zeroPressureDoesNotSwitchAndFirstPositivePressureSwitchesOnlyOnce() {
        fun sound(vararg samples:Short)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(48000,1,samples),0,0,looping=false)),48000)
        val playback=sound(1000,2000,3000).withAftertouch(sound(4000,5000,6000)).voice(60)
        playback.aftertouch(0f);playback.next(false)
        assertEquals(1000.0/32768,playback.left,1e-9)
        playback.aftertouch(1f/127f);playback.next(false)
        assertEquals(4000.0/32768,playback.left,1e-9)
        playback.aftertouch(1f);playback.next(false)
        assertEquals(5000.0/32768,playback.left,1e-9)
    }
    @Test fun exactMappingDoesNotBorrowOrTransposeNearbySections() {
        val pcm=SamplePcm(48000,1,shortArrayOf(1000,2000,3000))
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,3)),48000,exactNotes=true)
        assertEquals(1000.0/32768,instrument.sample(60,0),1e-9)
        assertEquals(0.0,instrument.sample(61,0),0.0)
        assertEquals(0.0,instrument.sample(72,0),0.0)
        assertTrue(instrument.finished(61,0))
        assertFalse(instrument.finished(60,48000))
    }
    @Test fun oneShotEndsInsteadOfWrappingAndTracksPitch() {
        val pcm=SamplePcm(48000,1,shortArrayOf(1000,2000,3000,4000))
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,0,looping=false)),48000)
        assertEquals(4000.0/32768,instrument.sample(60,3),1e-9)
        assertEquals(0.0,instrument.sample(60,4),0.0)
        assertEquals(3000.0/32768,instrument.sample(72,1),1e-9)
        assertEquals(0.0,instrument.sample(72,2),0.0)
        assertFalse(instrument.finished(60,3))
        assertTrue(instrument.finished(60,4))
        assertTrue(instrument.finished(72,2))
        assertFalse(instrument.finished(48,4))
    }
    @Test fun loopingRegionDoesNotFinishAfterManyCycles() {
        val pcm=SamplePcm(48000,1,shortArrayOf(1000,2000,3000))
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,pcm,1,3)),48000)
        assertFalse(instrument.finished(60,480000))
    }
    @Test fun regionPanUsesIndependentLinearChannelGains() {
        val pcm=SamplePcm(48000,1,shortArrayOf(10000,10000))
        val right=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,2,1.0,50)),48000)
        assertEquals(5000.0/32768,right.sample(60,0,0),1e-9)
        assertEquals(10000.0/32768,right.sample(60,0,1),1e-9)
        val left=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,2,1.0,-100)),48000)
        assertEquals(10000.0/32768,left.sample(60,0,0),1e-9)
        assertEquals(0.0,left.sample(60,0,1),0.0)
    }
    @Test fun stereoRegionKeepsOppositeChannelsDuringPitchingAndLooping() {
        val pcm=SamplePcm(48000,2,shortArrayOf(0,0,0),shortArrayOf(1000,-1000,2000,-2000,3000,-3000))
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,pcm,1,3)),48000)
        assertEquals(2000.0/32768,instrument.sample(60,3,0),1e-9)
        assertEquals(-2000.0/32768,instrument.sample(60,3,1),1e-9)
        assertEquals(1500.0/32768,instrument.sample(48,1,0),1e-9)
        assertEquals(-1500.0/32768,instrument.sample(48,1,1),1e-9)
    }
    @Test fun regionGainAppliesBeforeOutputMixing() {
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(48000,1,shortArrayOf(10000,10000)),0,2,0.54)),48000)
        assertEquals(5400.0/32768,instrument.sample(60,0),1e-9)
        assertEquals(5400.0/32768,instrument.sample(60,100),1e-9)
    }
    @Test fun sustainedRootRepeatsRecordedLoopAfterAttack() {
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(48000,1,shortArrayOf(1000,2000,3000,4000)),1,4)),48000)
        assertEquals(1000.0/32768,instrument.sample(60,0),1e-9)
        assertEquals(2000.0/32768,instrument.sample(60,4),1e-9)
        assertEquals(instrument.sample(60,4),instrument.sample(60,3004),1e-9)
    }
    @Test fun nearestRegionAndOctaveStepAreApplied() {
        val low=InstrumentRegion(48,SamplePcm(48000,1,shortArrayOf(1000,2000,3000)),0,3)
        val high=InstrumentRegion(72,SamplePcm(48000,1,shortArrayOf(-1000,-2000,-3000)),0,3)
        val instrument=SampledInstrument(listOf(low,high),48000)
        assertEquals(3000.0/32768,instrument.sample(60,1),1e-9)
        assertEquals(-1000.0/32768,instrument.sample(72,0),1e-9)
        assertEquals(0.0,instrument.sample(-1,0),0.0)
    }
}
