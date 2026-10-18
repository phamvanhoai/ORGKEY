package com.hovait.orgx

import android.app.Instrumentation
import android.os.Bundle

/** Device-only check: executes the real Android ELF, never the JVM test backend. */
class NativeReverbSmoke:Instrumentation() {
    override fun onCreate(arguments:Bundle?){super.onCreate(arguments);start()}
    override fun onStart(){
        val result=Bundle()
        try{
            var peak=0.0
            ImportedNativeReverb(listOf(40,7,50,50,50,50,100,0),48000).use{reverb->
                repeat(48000){i->
                    reverb.process(if(i==0).5 else 0.0,0.0)
                    check(reverb.left.isFinite() && reverb.right.isFinite())
                    if(i>0)peak=maxOf(peak,kotlin.math.abs(reverb.left),kotlin.math.abs(reverb.right))
                }
                check(peak>0){"Native Reverb produced no wet tail"}
                reverb.reset();reverb.process(0.0,0.0)
                check(reverb.left==0.0 && reverb.right==0.0){"Native flush did not clear tail"}
            }
            repeat(8){ImportedNativeReverb(listOf(40,7,50,50,50,50,50,0),48000).use{it.process(.25,-.125)}}
            result.putString("result","PASS: real JNI impulse/tail/flush/close; peak=$peak")
            finish(RESULT_OK,result)
        }catch(error:Throwable){result.putString("result","FAIL: ${error.stackTraceToString()}");finish(RESULT_CANCELED,result)}
    }
    companion object {private const val RESULT_OK=-1;private const val RESULT_CANCELED=0}
}
