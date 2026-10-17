package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StereoPeakLimiterTest {
    @Test fun protectsPeakWhilePreservingStereoRatio() {
        val limiter=StereoPeakLimiter(48000)
        limiter.process(4.0,-2.0)
        assertEquals(0.98,limiter.left,1e-9)
        assertEquals(-0.49,limiter.right,1e-9)
    }
    @Test fun quietSignalIsUnchangedBeforeOverload() {
        val limiter=StereoPeakLimiter(48000)
        limiter.process(0.2,-0.3)
        assertEquals(0.2,limiter.left,0.0);assertEquals(-0.3,limiter.right,0.0)
    }
    @Test fun recoveryIsGradualAndReturnsToUnity() {
        val limiter=StereoPeakLimiter(48000)
        limiter.process(4.0,4.0);val reduced=limiter.gain
        limiter.process(0.1,0.1)
        assertTrue(limiter.gain>reduced && limiter.gain<1)
        repeat(4800){limiter.process(0.1,0.1)}
        assertEquals(1.0,limiter.gain,0.0)
    }
    @Test fun nonFiniteSignalDoesNotReachPcm() {
        val limiter=StereoPeakLimiter(48000)
        limiter.process(Double.NaN,1.0)
        assertEquals(0.0,limiter.left,0.0);assertEquals(0.0,limiter.right,0.0)
    }
}
