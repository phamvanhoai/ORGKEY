package com.hovait.orgx

import sofeh.audio.NPitchShift

/** Explicit lifetime for the original phase-vocoder library; allocated on first signal. */
class ImportedNativePitchShift(private val shift:Int,private val rate:Int):AutoCloseable {
    companion object {
        // JNI uses a 1024-point FFT with 4x overlap. Keep four windows for
        // flushing, independent of sample rate; verified on the native bridge.
        const val FLUSH_FRAMES=4096
    }
    private var handle=0L
    private var closed=false
    var left=0.0;private set
    var right=0.0;private set
    init{require(shift in 50..200 && rate>0)}
    fun process(l:Double,r:Double){
        check(!closed)
        if(handle==0L){handle=NPitchShift.open(0);check(handle!=0L){"Cannot open Pitch Shift"}}
        left=NPitchShift.processLeft(handle,shift,rate,(l*32768).toLong())/32768.0
        right=NPitchShift.processRight(handle,shift,rate,(r*32768).toLong())/32768.0
    }
    fun reset(){check(!closed);if(handle!=0L)NPitchShift.close(handle);handle=0;left=0.0;right=0.0}
    override fun close(){if(!closed){reset();closed=true}}
}
