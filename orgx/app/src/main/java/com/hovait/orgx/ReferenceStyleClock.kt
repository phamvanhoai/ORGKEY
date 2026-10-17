package com.hovait.orgx

/** Tracks.PlayNext: reference time = elapsed milliseconds * tempo / 120. */
class ReferenceStyleClock(private val sampleRate:Int) {
    init { require(sampleRate>0) }
    private val denominator=sampleRate.toLong()*120
    private var remainder=0L
    var time=0L;private set
    fun advance(frames:Int,tempo:Int):Long {
        require(frames>=0 && tempo in 40..240)
        val numerator=remainder+frames.toLong()*1000*tempo
        time+=numerator/denominator
        remainder=numerator%denominator
        return time
    }
    fun reset(){time=0;remainder=0}
    fun seek(referenceTime:Long){require(referenceTime>=0);time=referenceTime;remainder=0}
}
