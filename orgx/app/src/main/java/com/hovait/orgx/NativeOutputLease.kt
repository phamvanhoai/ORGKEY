package com.hovait.orgx

import java.util.concurrent.atomic.AtomicReference

/** The reference OpenSL library has one process-wide output session. */
internal class NativeOutputLease {
    private val owner = AtomicReference<Any?>(null)
    fun acquire(candidate: Any): Boolean = owner.compareAndSet(null, candidate)
    fun release(candidate: Any): Boolean = owner.compareAndSet(candidate, null)
}
