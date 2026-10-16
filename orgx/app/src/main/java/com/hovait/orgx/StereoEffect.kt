package com.hovait.orgx

/** ORGX's simple delay/reverb approximation, with independent stereo history. */
class StereoEffect(private val sampleRate: Int) {
    init { require(sampleRate > 0) }
    private val leftBuffer=DoubleArray(sampleRate)
    private val rightBuffer=DoubleArray(sampleRate)
    private var position=0
    var left=0.0;private set
    var right=0.0;private set

    fun process(inputLeft:Double,inputRight:Double,type:Int,mix:Float) {
        if(type==0){left=inputLeft;right=inputRight;return}
        val delayFrames=(sampleRate*if(type==1)0.28 else 0.095).toInt().coerceAtLeast(1)
        val read=(position-delayFrames+sampleRate)%sampleRate
        val delayedLeft=leftBuffer[read];val delayedRight=rightBuffer[read]
        val feedback=if(type==1)0.38 else 0.67
        leftBuffer[position]=(inputLeft+delayedLeft*feedback).coerceIn(-2.0,2.0)
        rightBuffer[position]=(inputRight+delayedRight*feedback).coerceIn(-2.0,2.0)
        position=(position+1)%sampleRate
        val wet=mix.coerceIn(0f,1f)
        left=inputLeft*(1-wet)+delayedLeft*wet
        right=inputRight*(1-wet)+delayedRight*wet
    }
}
