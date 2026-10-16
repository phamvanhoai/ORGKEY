package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NativeOutputLeaseTest {
    @Test fun oldSessionCannotReleaseReplacement() {
        val lease=NativeOutputLease();val old=Any();val next=Any()
        assertTrue(lease.acquire(old));assertFalse(lease.acquire(next))
        assertFalse(lease.release(next));assertTrue(lease.release(old))
        assertTrue(lease.acquire(next));assertFalse(lease.release(old))
        assertFalse(lease.acquire(Any()));assertTrue(lease.release(next))
    }
    @Test fun concurrentStartsHaveOnlyOneOwner() {
        val lease=NativeOutputLease();val start=CountDownLatch(1)
        val pool=Executors.newFixedThreadPool(4)
        try {
            val results=(1..8).map { pool.submit<Boolean> { start.await();lease.acquire(Any()) } }
            start.countDown()
            assertEquals(1,results.count { it.get(3,TimeUnit.SECONDS) })
        } finally { pool.shutdownNow() }
    }
}
