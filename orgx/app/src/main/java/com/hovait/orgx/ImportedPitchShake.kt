package com.hovait.orgx

import kotlin.math.sin

/** Slot 2 adds to the original fixed-point sample pitch (8192 = unity). */
class ImportedPitchShake(private var effect:ImportedExpressionEffect,private val sampleRate:Int) {
    fun configure(value:ImportedExpressionEffect){require(value.slot==2);effect=value;if(!value.enabled)offset=0.0}
    private var frames=0L
    private var start=0L
    private var count=0L
    var offset=0.0;private set
    init{require(effect.slot==2 && sampleRate>0)}
    fun next(released:Boolean,pedal:Boolean){
        if(frames*100/sampleRate != (frames+1)*100/sampleRate){
            if(effect.enabled && !released && (!effect.byPedal || pedal))start+=10
            if(effect.enabled && (!effect.stopByRelease || !released) && start>effect.startDelay){
                count+=effect.speed.toLong()*10
                offset=sin(count/1000.0)*effect.depth+count.toDouble()*effect.gain/10000
            }else offset=0.0
        }
        frames++
        if(effect.stopByRelease && released)offset=0.0
    }
}
