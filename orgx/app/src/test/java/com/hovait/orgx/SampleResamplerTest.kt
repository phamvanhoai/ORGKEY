package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class SampleResamplerTest {
    @Test fun interpolatesBetweenFrames() {
        assertEquals(5000.0 / 32768, SampleResampler.at(shortArrayOf(0,10000), 0.5), 1e-9)
        assertEquals(0.0, SampleResampler.at(shortArrayOf(1), -0.5), 0.0)
        assertEquals(0.0, SampleResampler.at(shortArrayOf(1), 1.0), 0.0)
    }
    @Test fun rateConversionPreservesDurationAndOriginalSamplePositions() {
        val source = SamplePcm(24000, 1, shortArrayOf(0,10000,0,-10000))
        val output = SampleResampler.toRate(source,48000)
        assertEquals(8, output.size)
        assertArrayEquals(shortArrayOf(0,5000,10000,5000,0,-5000,-10000,-5000),output)
    }
    @Test fun matchingRateAvoidsCopy() {
        val sample = SamplePcm(48000,1,shortArrayOf(123))
        assertSame(sample.frames, SampleResampler.toRate(sample,48000))
    }
}
