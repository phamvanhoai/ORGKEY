package com.hovait.orgx

import kotlin.math.sin

/** Channels.Process slot 3 formula, advanced on a 10 ms control clock. */
class ImportedVolumeShake(private val effect:ImportedExpressionEffect,private val sampleRate:Int) {
    private var frames=0L
    private var start=0L
    private var phase=0.0
    var left=1.0;private set
    var right=1.0;private set
    init{require(sampleRate>0 && effect.slot==3)}
    fun next(released:Boolean,pedal:Boolean){
        if(frames*100/sampleRate != (frames+1)*100/sampleRate){
            if(!released && (!effect.byPedal || pedal))start+=10
            if(effect.enabled && (!effect.stopByRelease || !released) && start>effect.startDelay){
                phase+=effect.speed/100.0
                val wave=sin(phase)
                left=1+wave*(effect.depth.toDouble()-effect.gain)/100
                right=1+wave*(effect.depth.toDouble()+effect.gain)/100
            }else{left=1.0;right=1.0}
        }
        frames++
        if(effect.stopByRelease && released){left=1.0;right=1.0}
    }
}
