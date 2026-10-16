package com.hovait.orgx

import android.content.res.Resources
import java.util.concurrent.atomic.AtomicInteger


class SampleBank(resources:Resources, outputRate:Int=48000) {
    private fun load(resources:Resources,id:Int,rate:Int):ShortArray = resources.openRawResource(id).use { SampleResampler.toRate(SamplePcmDecoder.decode(it),rate) }
    private val samples:Map<String,ShortArray> = mapOf(
        "crash" to load(resources,R.raw.orgx_crash,outputRate),
        "metronome" to load(resources,R.raw.orgx_metronome_short,outputRate),
        "metronomeLong" to load(resources,R.raw.orgx_metronome_long,outputRate),
        "pianoLow" to load(resources,R.raw.orgx_piano_low_eb2,outputRate),
        "pianoMid" to load(resources,R.raw.orgx_piano_dnc_csharp4,outputRate)
    )
    private val effects=SoundEffectCatalog.entries.associate { effect ->
        effect.key to resources.openRawResource(effect.resource).use { StereoSample(SamplePcmDecoder.decode(it),outputRate) }
    } + ("crash" to StereoSample(SamplePcm(outputRate,1,samples.getValue("crash")),outputRate))
    private var active:StereoSample?=null
    private fun pianoRegion(resources:Resources,id:Int):SamplePcm = resources.openRawResource(id).use(SamplePcmDecoder::decode)
    private val pianoRegions=mapOf(
        42 to pianoRegion(resources,R.raw.orgx_piano_zone_42),
        49 to pianoRegion(resources,R.raw.orgx_piano_zone_49),
        54 to pianoRegion(resources,R.raw.orgx_piano_zone_54),
        61 to pianoRegion(resources,R.raw.orgx_piano_zone_61),
        66 to pianoRegion(resources,R.raw.orgx_piano_zone_66),
        73 to pianoRegion(resources,R.raw.orgx_piano_zone_73),
        78 to pianoRegion(resources,R.raw.orgx_piano_zone_78),
        85 to pianoRegion(resources,R.raw.orgx_piano_zone_85),
        90 to pianoRegion(resources,R.raw.orgx_piano_zone_90))
    private val pianoRoots=IntArray(128){SampleRegionMap.nearestRoot(it,pianoRegions.keys.toList())}
    private val pianoSources = Array(128) { pianoRegions.getValue(pianoRoots[it]).frames }
    private val pianoSteps = DoubleArray(128) { note -> SamplePitch.step(note,pianoRoots[note],pianoRegions.getValue(pianoRoots[note]).sampleRate,outputRate) }
    private val cursor=AtomicInteger(0)
    private var metronomeActive:ShortArray?=null
    private val metronomeCursor=AtomicInteger(0)
    @Synchronized fun trigger(name:String){active=effects[name];cursor.set(0)}
    @Synchronized fun triggerMetronome(accent:Boolean=false){metronomeActive=samples[if(accent)"metronomeLong" else "metronome"];metronomeCursor.set(0)}
    @Synchronized fun nextMetronome():Double {val data=metronomeActive?:return 0.0;val index=metronomeCursor.getAndIncrement();if(index>=data.size){metronomeActive=null;return 0.0};return data[index]/32768.0}
    @Synchronized fun nextStereo():Long {val data=active?:return 0;val index=cursor.getAndIncrement();if(index>=data.size){active=null;return 0};return data.frame(index)}
    fun piano(note:Int,age:Long):Double {
        if(note !in 0..127 || age<0) return 0.0
        return SampleResampler.at(pianoSources[note],age*pianoSteps[note])
    }
    @Synchronized fun clear(){active=null;cursor.set(0);metronomeActive=null;metronomeCursor.set(0)}
}
