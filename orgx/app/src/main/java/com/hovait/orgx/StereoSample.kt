package com.hovait.orgx

/** Packed PCM16 stereo avoids creating a frame object for every audio sample. */
class StereoSample(sample: SamplePcm, outputRate: Int) {
    private val left: ShortArray
    private val right: ShortArray
    init {
        val stereo=sample.stereo
        left=SampleResampler.toRate(SamplePcm(sample.sampleRate,1,
            if(stereo==null)sample.frames else ShortArray(sample.frames.size){stereo[it*2]}),outputRate)
        right=if(stereo==null)left else SampleResampler.toRate(SamplePcm(sample.sampleRate,1,
            ShortArray(sample.frames.size){stereo[it*2+1]}),outputRate)
    }
    val size get()=left.size
    fun frame(index:Int): Long {
        if(index !in left.indices)return 0
        return (left[index].toLong() and 65535) or ((right[index].toLong() and 65535) shl 16)
    }
    companion object {
        fun left(frame:Long)=frame.toShort()/32768.0
        fun right(frame:Long)=(frame shr 16).toShort()/32768.0
    }
}
