package com.hovait.orgx

import kotlin.math.sin

/** Slot 2 adds to the original fixed-point sample pitch (8192 = unity). */
class ImportedPitchShake(private var effect:ImportedExpressionEffect,private val sampleRate:Int) {
    fun configure(value:ImportedExpressionEffect){require(value.slot==2);effect=value;if(!value.enabled)offset=0.0}
    private var frames=0L
    private var start=0L
    private var count=0
    var offset=0.0;private set
    init{require(effect.slot==2 && sampleRate>0)}
    fun next(released:Boolean,pedal:Boolean){
        if(frames*100/sampleRate != (frames+1)*100/sampleRate){
            if(effect.enabled && !released && (!effect.byPedal || pedal))start+=10
            if(effect.enabled && (!effect.stopByRelease || !released) && start>effect.startDelay){
                // Original Java uses int counters/product/division, then float sin.
                // Keep the fractional offset: truncation of the combined base
                // pitch belongs to the sample mixer, not this modulation state.
                count+=effect.playbackSpeed*10
                val wave=sin((count/1000f).toDouble()).toFloat()*effect.depth
                offset=(wave+(count*effect.gain/10000)).toDouble()
            }else offset=0.0
        }
        frames++
        if(effect.stopByRelease && released)offset=0.0
    }
}
