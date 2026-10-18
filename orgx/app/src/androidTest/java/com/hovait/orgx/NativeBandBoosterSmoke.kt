package com.hovait.orgx

import android.app.Instrumentation
import android.os.Bundle
import sofeh.audio.NBandBooster
import kotlin.math.abs

class NativeBandBoosterSmoke:Instrumentation(){
    override fun onCreate(arguments:Bundle?){super.onCreate(arguments);start()}
    override fun onStart(){
        val result=Bundle()
        try{
            var maximum=0L
            val reports=mutableListOf<String>()
            for(rate in listOf(32000,44100,48000,96000)){
            var rateMaximum=0L
            for(gains in listOf(listOf(0,0,0),listOf(50,-20,80),listOf(-100,100,-50))){
                val handle=NBandBooster.open(0)
                check(handle!=0L)
                try{
                    NBandBooster.build(handle,gains[0],gains[1],gains[2],rate)
                    val scalar=ReferenceBandBooster(gains[0],gains[1],gains[2],rate)
                    var seed=1234567
                    repeat(12000){i->
                        seed=seed*1664525+1013904223
                        val input=if(i<4000)(seed shr 17).toLong() else 0L
                        rateMaximum=maxOf(rateMaximum,abs(NBandBooster.process(handle,input)-scalar.process(input)))
                    }
                }finally{NBandBooster.close(handle)}
            }
            maximum=maxOf(maximum,rateMaximum)
            reports.add("$rate Hz: $rateMaximum")
            }
            check(maximum<=2){"Native/scalar error exceeds two integer PCM units: $maximum"}
            result.putString("result","PASS Band Booster: 144000 samples, max integer PCM difference=$maximum; ${reports.joinToString()}")
            finish(-1,result)
        }catch(error:Throwable){result.putString("result","FAIL: ${error.stackTraceToString()}");finish(0,result)}
    }
}
