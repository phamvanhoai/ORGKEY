package com.hovait.orgx

/** Packed PCM16 stereo avoids creating a frame object for every audio sample. */
class StereoSample(sample: SamplePcm, outputRate: Int,loopStart:Int?=null,loopEnd:Int?=null) {
    private val left: ShortArray
    private val right: ShortArray
    private val loopStartFrame=loopStart?.let{(it.toLong()*outputRate/sample.sampleRate).toInt()}
    private val loopEndFrame=loopEnd?.let{(it.toLong()*outputRate/sample.sampleRate).toInt()}
    init {
        val stereo=sample.stereo
        left=SampleResampler.toRate(SamplePcm(sample.sampleRate,1,
            if(stereo==null)sample.frames else ShortArray(sample.frames.size){stereo[it*2]}),outputRate)
        right=if(stereo==null)left else SampleResampler.toRate(SamplePcm(sample.sampleRate,1,
            ShortArray(sample.frames.size){stereo[it*2+1]}),outputRate)
        require((loopStart==null && loopEnd==null) ||
            (loopStartFrame!=null && loopEndFrame!=null && loopStartFrame>=0 && loopEndFrame>loopStartFrame && loopEndFrame<=left.size))
    }
    val size get()=left.size
    val looping get()=loopStartFrame!=null
    fun frame(index:Int): Long {
        val position=if(loopStartFrame!=null && loopEndFrame!=null)
            SampleLoop.position(index.toDouble(),loopStartFrame,loopEndFrame).toInt() else index
        if(position !in left.indices)return 0
        return (left[position].toLong() and 65535) or ((right[position].toLong() and 65535) shl 16)
    }
    companion object {
        fun left(frame:Long)=frame.toShort()/32768.0
        fun right(frame:Long)=(frame shr 16).toShort()/32768.0
    }
}
