package com.hovait.orgx

/** Two signed 32-bit values preserve gain headroom until final PCM clipping. */
class StereoSample(sample: SamplePcm, outputRate: Int,loopStart:Int?=null,loopEnd:Int?=null,val gain:Double=1.0) {
    private val left: ShortArray
    private val right: ShortArray
    private val loopStartFrame=loopStart?.let{(it.toLong()*outputRate/sample.sampleRate).toInt()}
    private val loopEndFrame=loopEnd?.let{(it.toLong()*outputRate/sample.sampleRate).toInt()}
    init {
        require(gain.isFinite() && gain in 0.0..100.0)
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
        val l=(left[position]*gain).toInt()
        val r=(right[position]*gain).toInt()
        return (l.toLong() and 0xffffffffL) or (r.toLong() shl 32)
    }
    companion object {
        fun left(frame:Long)=frame.toInt()/32768.0
        fun right(frame:Long)=(frame shr 32).toInt()/32768.0
    }
}
