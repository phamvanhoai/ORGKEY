package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StereoEffectTest {
    @Test fun drySignalKeepsChannelsSeparate() {
        val fx=StereoEffect(48000)
        fx.process(0.4,-0.6,0,0.5f)
        assertEquals(0.4,fx.left,0.0);assertEquals(-0.6,fx.right,0.0)
    }
    @Test fun delayHasCorrectTimingAndDoesNotLeakIntoOtherChannel() {
        val fx=StereoEffect(48000)
        repeat(13441){frame->
            fx.process(if(frame==0)1.0 else 0.0,0.0,1,1f)
            assertEquals(0.0,fx.right,0.0)
            assertEquals(if(frame==13440)1.0 else 0.0,fx.left,1e-9)
        }
    }
}
