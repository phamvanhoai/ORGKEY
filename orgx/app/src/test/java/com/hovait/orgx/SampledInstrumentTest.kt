package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class SampledInstrumentTest {
    @Test fun oneShotEndsInsteadOfWrappingAndTracksPitch() {
        val pcm=SamplePcm(48000,1,shortArrayOf(1000,2000,3000,4000))
        val instrument=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,0,looping=false)),48000)
        assertEquals(4000.0/32768,instrument.sample(60,3),1e-9)
        assertEquals(0.0,instrument.sample(60,4),0.0)
        assertEquals(3000.0/32768,instrument.sample(72,1),1e-9)
        assertEquals(0.0,instrument.sample(72,2),0.0)
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
