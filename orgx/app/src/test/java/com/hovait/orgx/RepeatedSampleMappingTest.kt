package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class RepeatedSampleMappingTest {
    @Test fun repeatsStoredRangeAndKeepsHolesSilent(){
        val roots=listOf(36,38)
        assertEquals(36,SampleRegionMap.repeatedRoot(0,roots))
        assertEquals(38,SampleRegionMap.repeatedRoot(41,roots))
        assertNull(SampleRegionMap.repeatedRoot(40,roots))
        assertEquals(36,SampleRegionMap.repeatedRoot(126,roots))
    }
    @Test fun repeatedSamplePlaysAtOriginalPitchInsteadOfTransposingOctaves(){
        val pcm=SamplePcm(48000,1,shortArrayOf(1000,2000,3000,4000),null)
        val region=InstrumentRegion(60,pcm,0,0,looping=false)
        val sound=SampledInstrument(listOf(region),48000,pitchMode=1)
        assertEquals(sound.sample(60,1),sound.sample(12,1),0.0)
        assertEquals(4,sound.durationFrames(12))
        assertEquals(2000.0/32768,sound.sample(12,1),1e-9)
    }
}
