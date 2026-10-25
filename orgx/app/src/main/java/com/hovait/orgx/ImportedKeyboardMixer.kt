package com.hovait.orgx

import java.util.IdentityHashMap

/** Channel DSP after summing all notes/layers of the selected imported sound. */
class ImportedKeyboardMixer {
    private class Bus(sound:SampledInstrument){
        val settings=sound.keyboardSettings
        val fx=ImportedFxChain(settings)
        var left=0.0;var right=0.0;var notes=false
    }
    private val buses=IdentityHashMap<SampledInstrument,Bus>()
    private var musicSettings=ImportedChannelSettings()
    private var musicFx=ImportedFxChain(musicSettings)
    private var musicFxRevision=0L
    /** Music volume is a master gain, applied after mixing rhythm/wave as well. */
    val musicVolume get()=musicSettings.volume/100.0
    fun configureMusic(settings:ImportedChannelSettings,revision:Long=musicFxRevision){
        if(settings==musicSettings && revision==musicFxRevision)return
        // Gain changes must not discard an echo already in flight.
        if(settings.fxEnabled!=musicSettings.fxEnabled || settings.modules!=musicSettings.modules || revision!=musicFxRevision){
            musicFx.close()
            musicFx=ImportedFxChain(settings)
        }
        musicSettings=settings
        musicFxRevision=revision
    }
    var left=0.0;private set
    var right=0.0;private set
    fun beginFrame(){buses.values.forEach{it.left=0.0;it.right=0.0;it.notes=false}}
    fun add(sound:SampledInstrument,l:Double,r:Double){
        val active=sound.activeModeSound
        val bus=buses.getOrPut(active){Bus(active)}
        bus.left+=l;bus.right+=r;bus.notes=true
    }
    fun finishFrame(){
        left=0.0;right=0.0
        val iterator=buses.values.iterator()
        while(iterator.hasNext()){
            val bus=iterator.next();val settings=bus.settings
            bus.fx.process(bus.left*settings.volume/100.0*minOf(100,100-settings.pan)/100.0,
                bus.right*settings.volume/100.0*minOf(100,100+settings.pan)/100.0)
            left+=bus.fx.left;right+=bus.fx.right
            if(!bus.notes && !bus.fx.active){bus.fx.close();iterator.remove()}
        }
        musicFx.process(left,right)
        left=musicFx.left;right=musicFx.right
    }
    fun close(){buses.values.forEach{it.fx.close()};buses.clear();musicFx.close();left=0.0;right=0.0}
    fun reset(){close();musicFx=musicFx.fresh()}
}
