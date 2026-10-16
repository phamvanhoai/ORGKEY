package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class SampledInstrumentTest {
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
