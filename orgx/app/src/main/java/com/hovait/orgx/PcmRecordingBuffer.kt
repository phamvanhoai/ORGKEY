package com.hovait.orgx

import java.io.ByteArrayOutputStream

/** Stereo PCM16, limited to complete frames. The caller owns synchronization. */
class PcmRecordingBuffer(private val maxFrames:Int) {
    init { require(maxFrames>0 && maxFrames<=Int.MAX_VALUE/4) }
    private val bytes=ByteArrayOutputStream()
    val isFull:Boolean get()=bytes.size()==maxFrames*4
    fun size()=bytes.size()
    fun reset(){bytes.reset()}
    fun toByteArray()=bytes.toByteArray()
    fun append(samples:ShortArray,startSample:Int){
        require(startSample in 0..samples.size && startSample%2==0 && samples.size%2==0)
        val count=minOf(samples.size-startSample,(maxFrames*4-bytes.size())/2)
        for(index in startSample until startSample+count){
            val value=samples[index].toInt()
            bytes.write(value and 255);bytes.write(value ushr 8 and 255)
        }
    }
}
