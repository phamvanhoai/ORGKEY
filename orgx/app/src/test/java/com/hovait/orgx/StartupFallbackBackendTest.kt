package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StartupFallbackBackendTest {
    private class Fake(private val failure: Throwable?=null): AudioBackend {
        override val sampleRate=48000
        override val channels=2
        var starts=0;var closes=0;var writes=0
        override fun start(){starts++;failure?.let { throw it }}
        override fun write(samples:ShortArray,offset:Int,count:Int):Int {writes++;return count}
        override fun stop(){}
        override fun close(){closes++}
    }
    @Test fun successfulNativeStartDoesNotConstructFallback() {
        val native=Fake()
        val backend=StartupFallbackBackend(48000,preferred={native},fallback={error("Unexpected fallback")})
        backend.start();assertEquals(4,backend.write(ShortArray(4),0,4));backend.close();backend.close()
        assertEquals(1,native.writes);assertEquals(1,native.closes)
    }
    @Test fun missingJniReleasesCandidateAndStartsFallback() {
        val native=Fake(UnsatisfiedLinkError("missing JNI"));val fallback=Fake()
        var nativeSelected=true
        val backend=StartupFallbackBackend(48000,preferred={native},fallback={fallback},onSelected={nativeSelected=it})
        backend.start();backend.write(ShortArray(4),0,4);backend.close()
        assertFalse(nativeSelected);assertEquals(1,native.closes)
        assertEquals(1,fallback.starts);assertEquals(1,fallback.writes);assertEquals(1,fallback.closes)
    }
}
