package com.hovait.orgx

import kotlin.math.abs

/** Immediate peak protection with linked stereo gain and gradual recovery. */
class StereoPeakLimiter(sampleRate:Int,private val ceiling:Double=0.98) {
    init { require(sampleRate>0 && ceiling>0 && ceiling<=1) }
    private val recovery=1.0/(sampleRate*0.1)
    var gain=1.0;private set
    var left=0.0;private set
    var right=0.0;private set
    fun process(inputLeft:Double,inputRight:Double) {
        if(!inputLeft.isFinite() || !inputRight.isFinite()){left=0.0;right=0.0;return}
        val peak=maxOf(abs(inputLeft),abs(inputRight))
        val target=if(peak>ceiling)ceiling/peak else 1.0
        gain=if(target<gain)target else minOf(target,gain+recovery)
        left=inputLeft*gain;right=inputRight*gain
    }
}
