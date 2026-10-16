package com.hovait.orgx

import android.content.res.Resources
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

class SampleBank(resources:Resources) {
    private val samples:Map<String,ShortArray> = mapOf(
        "crash" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_crash)).frames,
        "metronome" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_metronome_short)).frames,
        "metronomeLong" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_metronome_long)).frames,
        "pianoLow" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_piano_low_eb2)).frames,
        "pianoMid" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_piano_dnc_csharp4)).frames
    )
    private var active:ShortArray?=null
    private val cursor=AtomicInteger(0)
    private var metronomeActive:ShortArray?=null
    private val metronomeCursor=AtomicInteger(0)
    @Synchronized fun trigger(name:String){active=samples[name];cursor.set(0)}
    @Synchronized fun triggerMetronome(accent:Boolean=false){metronomeActive=samples[if(accent)"metronomeLong" else "metronome"];metronomeCursor.set(0)}
    @Synchronized fun nextMetronome():Double {val data=metronomeActive?:return 0.0;val index=metronomeCursor.getAndIncrement();if(index>=data.size){metronomeActive=null;return 0.0};return data[index]/32768.0}
    @Synchronized fun next():Double {val data=active?:return 0.0;val index=cursor.getAndIncrement();if(index>=data.size){active=null;return 0.0};return data[index]/32768.0}
    fun piano(note:Int,age:Long):Double {val source=(if(note<50)samples["pianoLow"] else samples["pianoMid"]) ?: return 0.0;val reference=if(note<50)39 else 61;val index=(age*2.0.pow((note-reference)/12.0)).toInt();return if(index in source.indices)source[index]/32768.0 else 0.0}
    @Synchronized fun clear(){active=null;cursor.set(0);metronomeActive=null;metronomeCursor.set(0)}
}
