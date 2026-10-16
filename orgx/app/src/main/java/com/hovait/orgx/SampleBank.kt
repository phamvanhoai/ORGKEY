package com.hovait.orgx

import android.content.res.Resources
import java.util.concurrent.atomic.AtomicInteger

class SampleBank(resources:Resources) {
    private val samples=mapOf(
        "crash" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_crash)).frames,
        "metronome" to SamplePcmDecoder.decode(resources.openRawResource(R.raw.orgx_metronome_short)).frames
    )
    private var active:ShortArray?=null
    private val cursor=AtomicInteger(0)
    @Synchronized fun trigger(name:String){active=samples[name];cursor.set(0)}
    @Synchronized fun next():Double {
        val data=active?:return 0.0;val index=cursor.getAndIncrement()
        if(index>=data.size){active=null;return 0.0}
        return data[index]/32768.0
    }
    @Synchronized fun clear(){active=null;cursor.set(0)}
}
