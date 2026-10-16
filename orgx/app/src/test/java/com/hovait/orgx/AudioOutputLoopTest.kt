package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class AudioOutputLoopTest {
    @Test fun partialWritesFinishBeforeReleaseAndCloseIsIdempotent() {
        val writing=CountDownLatch(1)
        val continueWrite=CountDownLatch(1)
        val released=CountDownLatch(1)
        val releases=AtomicInteger()
        val loop=AudioOutputLoop(start={},render={},write={_,_,_->
            writing.countDown()
            check(continueWrite.await(3,TimeUnit.SECONDS))
            2
        },release={releases.incrementAndGet();released.countDown()},samplesPerBlock=4)
        try {
            assertTrue(writing.await(3,TimeUnit.SECONDS))
            loop.close();loop.close()
            assertEquals(0,releases.get())
        } finally { continueWrite.countDown() }
        assertTrue(released.await(3,TimeUnit.SECONDS))
        assertEquals(1,releases.get())
    }

    @Test fun writeFailureStillReleasesBackend() {
        val released=CountDownLatch(1)
        val errors=AtomicInteger()
        AudioOutputLoop(start={},render={},write={_,_,_->-1},release={released.countDown()},
            onError={errors.incrementAndGet()},samplesPerBlock=4)
        assertTrue(released.await(3,TimeUnit.SECONDS))
        assertEquals(1,errors.get())
    }
}
