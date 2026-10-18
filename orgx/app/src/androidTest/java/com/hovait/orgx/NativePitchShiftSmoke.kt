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
            val measured=mutableListOf<String>()
            for(shift in listOf(50,100,200)){
                ImportedNativePitchShift(shift,48000).use{processor->
                    var crossings=0
                    var previous=0.0
                    repeat(48000){i->
                        processor.process(.2*sin(2*Math.PI*440*i/48000),0.0)
                        check(processor.left.isFinite() && processor.right.isFinite())
                        peak=maxOf(peak,abs(processor.left))
                        if(i>=24000 && previous<=0 && processor.left>0)crossings++
                        previous=processor.left
                        check(processor.right==0.0){"Signal leaked to silent right channel"}
                    }
                    val frequency=crossings*2.0
                    val expected=440.0*shift/100
                    check(abs(frequency-expected)<=expected*.08){"Shift $shift: expected $expected Hz, measured $frequency Hz"}
                    measured.add("$shift=$frequency Hz")
                    processor.reset();processor.process(0.0,0.0)
                    check(processor.left==0.0 && processor.right==0.0)
                }
            }
            check(peak>0){"No Pitch Shift output"}
            val settings=ImportedChannelSettings(fxEnabled=true,modules=listOf(
                ImportedFxModule(15,true,false,listOf(200,0,0,0))))
            ImportedFxChain(settings).use{chain->
                fun render():Double {
                    var energy=0.0
                    repeat(12000){i->
                        chain.process(.2*sin(2*Math.PI*440*i/48000),0.0)
                        check(chain.left.isFinite() && chain.right.isFinite())
                        check(chain.right==0.0)
                        energy+=chain.left*chain.left
                    }
                    return energy
                }
                val first=render();check(first>0 && chain.active)
                chain.reset();check(!chain.active)
                chain.process(0.0,0.0);check(chain.left==0.0 && chain.right==0.0)
                val second=render()
                check(abs(first-second)<1e-9){"FX reset did not reproduce initial Pitch Shift state"}
                repeat(96000){chain.process(0.0,0.0)}
                check(!chain.active){"Pitch Shift tail remained active past its budget"}
            }
            result.putString("result","PASS native Pitch Shift: ${measured.joinToString()}, signal/stereo/reset/close; peak=$peak")
            finish(-1,result)
        }catch(error:Throwable){result.putString("result","FAIL: ${error.stackTraceToString()}");finish(0,result)}
    }
}
