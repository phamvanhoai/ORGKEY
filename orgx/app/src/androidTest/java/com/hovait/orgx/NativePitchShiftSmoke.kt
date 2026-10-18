package com.hovait.orgx

import android.app.Instrumentation
import android.os.Bundle
import kotlin.math.sin
import kotlin.math.abs

class NativePitchShiftSmoke:Instrumentation(){
    override fun onCreate(arguments:Bundle?){super.onCreate(arguments);start()}
    override fun onStart(){
        val result=Bundle()
        try{
            var peak=0.0
            for(shift in listOf(50,100,200)){
                ImportedNativePitchShift(shift,48000).use{processor->
                    repeat(24000){i->
                        processor.process(.2*sin(2*Math.PI*440*i/48000),0.0)
                        check(processor.left.isFinite() && processor.right.isFinite())
                        peak=maxOf(peak,abs(processor.left))
                        check(processor.right==0.0){"Signal leaked to silent right channel"}
                    }
                    processor.reset();processor.process(0.0,0.0)
                    check(processor.left==0.0 && processor.right==0.0)
                }
            }
            check(peak>0){"No Pitch Shift output"}
            result.putString("result","PASS native Pitch Shift: shifts 50/100/200, signal/stereo/reset/close; peak=$peak")
            finish(-1,result)
        }catch(error:Throwable){result.putString("result","FAIL: ${error.stackTraceToString()}");finish(0,result)}
    }
}
