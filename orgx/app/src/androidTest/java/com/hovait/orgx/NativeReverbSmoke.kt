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
            val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(29,true,false,listOf(40,7,50,50,50,50,100,0))))
            val track=ReferenceDrumTrack("Impulse",1,0,32,120,false,0,100000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)))
            val previous=ReferenceRhythmPlayer(listOf(track),{1},{_,_,_->.5},48000,fx=ImportedFxChain(settings))
            val next=ReferenceRhythmPlayer(emptyList(),{0},{_,_,_->0.0},48000)
            previous.next(0,120)
            next.releaseVoicesFrom(previous)
            previous.close()
            var transferredPeak=0.0
            repeat(48000){
                next.next(0,120);next.mixOutput(1f,1f)
                check(next.outputLeft.isFinite() && next.outputRight.isFinite())
                transferredPeak=maxOf(transferredPeak,kotlin.math.abs(next.outputLeft),kotlin.math.abs(next.outputRight))
            }
            check(transferredPeak>0){"Transferred native track FX tail was lost"}
            next.close();next.close()
            result.putString("result","PASS: real JNI impulse/tail/flush/close + player tail transfer; peak=$peak transferred=$transferredPeak")
            finish(RESULT_OK,result)
        }catch(error:Throwable){result.putString("result","FAIL: ${error.stackTraceToString()}");finish(RESULT_CANCELED,result)}
    }
    companion object {private const val RESULT_OK=-1;private const val RESULT_CANCELED=0}
}
