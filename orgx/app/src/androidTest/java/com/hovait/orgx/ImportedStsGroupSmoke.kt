package com.hovait.orgx

import android.app.Instrumentation
import android.os.Bundle
import java.io.File
import kotlin.math.abs

/** Offline rendering against the user's imported banks; never edits the SET. */
class ImportedStsGroupSmoke:Instrumentation(){
    override fun onCreate(arguments:Bundle?){super.onCreate(arguments);start()}
    override fun onStart(){
        val result=Bundle()
        try{
            val banks=File(targetContext.filesDir,"ReferenceSets").listFiles().orEmpty().filter{it.extension=="orgset"}
            check(banks.isNotEmpty()){"No imported SET available"}
            val reports=mutableListOf<String>()
            var rendered=0
            for(file in banks){
                val index=ImportedSetInstruments.index(file)
                val slots=index.tracks.firstOrNull()?.sts.orEmpty()
                var current:ImportedStsBatch.Prepared?=null
                for(snapshot in slots.take(3)){
                    val request=ImportedStsRequest.prepare(file,index,0,snapshot.slot,current) as ImportedStsRequest.Prepared.Group
                    val prepared=request.value
                    current=prepared
                    val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
                    try{
                        queue.replace(prepared.keyboardParts())
                        // Cover all playable keys without retaining an entire recording.
                        var peak=0.0
                        for(note in listOf(36,48,60,72,84)){
                            queue.noteOn(note,.7)
                            repeat(2400){
                                mixer.beginFrame();queue.renderInto(mixer);mixer.finishFrame()
                                check(mixer.left.isFinite() && mixer.right.isFinite())
                                peak=maxOf(peak,abs(mixer.left),abs(mixer.right))
                            }
                            queue.noteOff(note);queue.pedal(false)
                        }
                        queue.clear()
                        mixer.beginFrame();queue.renderInto(mixer);mixer.finishFrame()
                        reports.add("${file.name.take(8)}/${snapshot.slot}:parts=${prepared.voices.size},enabled=${prepared.keyboardParts().count{it.enabled}},peak=$peak")
                        rendered++
                    }finally{mixer.close()}
                }
            }
            check(rendered>0){"No Master STS slots found"}
            result.putString("result","PASS $rendered groups: ${reports.joinToString()}")
            finish(-1,result)
        }catch(error:Throwable){result.putString("result","FAIL ${error.stackTraceToString()}");finish(0,result)}
    }
}
