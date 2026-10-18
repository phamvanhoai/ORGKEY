package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class AftertouchPolicyTest {
    @Test fun pressurePolicyClampsAndDoesNotChangeFactoryVoice(){
        assertEquals(.15f,VoiceVelocity.gain(0f,false),0f)
        assertEquals(1f,VoiceVelocity.gain(1f,false),0f)
        assertEquals(1f,VoiceVelocity.gain(2f,false),0f)
        assertEquals(.15f,VoiceVelocity.gain(-1f,false),0f)
    }
    @Test fun modeAftertouchSelectionIsExplicitAndMixIsRetained(){
        val mode=ImportedMode("M",0,1,listOf(2,-1,-1,-1,-1,-1,-1,-1),List(8){0},List(8){0})
        val source=ImportedInstrument("I",100,0,0,List(3){ImportedSound("S$it",emptyList(),emptyList())},listOf(mode))
        assertEquals(listOf("S0","S2"),ImportedInstrumentLoader.layers(source).map{it.sounds.single().name})
        assertEquals(listOf("S1","S2"),ImportedInstrumentLoader.layers(source,true).map{it.sounds.single().name})
    }
}
