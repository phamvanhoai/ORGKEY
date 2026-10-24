package com.hovait.orgx

/** Channels.Process Effect[0], evaluated on its original 10 ms control clock. */
class ImportedKeyRepeat(private var effect:ImportedExpressionEffect,private val sampleRate:Int) {
    companion object { private val disabled=ImportedExpressionEffect.default(0) }
    constructor(sampleRate:Int):this(disabled,sampleRate)
    private var frames=0L
    private var start=0
    private var count=0
    init{require(effect.slot==0 && sampleRate>0)}
    fun configure(value:ImportedExpressionEffect?){require(value==null || value.slot==0);effect=value ?: disabled}
    fun next(released:Boolean,pedal:Boolean):Boolean {
        var fire=false
        if(frames*100/sampleRate != (frames+1)*100/sampleRate){
            if(effect.enabled && !released && (!effect.byPedal || pedal))start+=10
            if(effect.enabled && !released && (!effect.stopByRelease || !released) && start>effect.startDelay && effect.playbackSpeed>0){
                count++
                val period=5000/(effect.playbackSpeed*10)+1
                fire=count%period==0
            }
        }
        frames++
        return fire
    }
}
