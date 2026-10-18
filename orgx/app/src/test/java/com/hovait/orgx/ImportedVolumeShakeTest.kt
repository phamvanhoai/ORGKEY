package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.sin

class ImportedVolumeShakeTest {
    private fun effect(delay:Int=0,pedal:Boolean=false,stop:Boolean=false)=ImportedExpressionEffect(
        3,true,delay,pedal,stop,0,100,20,10,emptyList())
    @Test fun stereoFormulaDelayAndStopFollowControlTicks(){
        val state=ImportedVolumeShake(effect(delay=10,stop=true),1000)
        repeat(19){state.next(false,false)}
        assertEquals(1.0,state.left,0.0)
        state.next(false,false)
        assertEquals(1+sin(1.0)*.1,state.left,1e-9)
        assertEquals(1+sin(1.0)*.3,state.right,1e-9)
        state.next(true,false)
        assertEquals(1.0,state.left,0.0);assertEquals(1.0,state.right,0.0)
    }
    @Test fun pedalGatesStartAndEachVoiceHasIndependentPhase(){
        val state=ImportedVolumeShake(effect(pedal=true),1000)
        repeat(30){state.next(false,false)}
        assertEquals(1.0,state.left,0.0)
        repeat(10){state.next(false,true)}
        assertEquals(1+sin(1.0)*.1,state.left,1e-9)
        val fresh=ImportedVolumeShake(effect(),1000)
        assertEquals(1.0,fresh.left,0.0)
    }
    @Test fun sampledVoiceActuallyAppliesStereoVolumeShake(){
        val sample=SamplePcm(48000,1,shortArrayOf(8192,8192))
        val sound=SampledInstrument(listOf(InstrumentRegion(60,sample,0,2)),48000)
            .withExpressionEffects(listOf(effect()))
        val voice=sound.voice(60)
        repeat(480){voice.next(false)}
        assertEquals(.25*(1+sin(1.0)*.1),voice.left,1e-9)
        assertEquals(.25*(1+sin(1.0)*.3),voice.right,1e-9)
        val nextVoice=sound.voice(60);nextVoice.next(false)
        assertEquals(.25,nextVoice.left,0.0)
    }
}
