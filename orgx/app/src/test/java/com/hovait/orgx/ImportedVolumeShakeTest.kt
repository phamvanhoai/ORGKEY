package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.sin

class ImportedVolumeShakeTest {
    @Test fun sectionReleaseTailKeepsPedalForDelayedExpression(){
        val sample=SamplePcm(48000,1,shortArrayOf(8192,8192))
        val sound=SampledInstrument(listOf(InstrumentRegion(60,sample,0,2)),48000,
            envelope=ImportedEnvelope(0,0,100,100,false))
        val shake=effect(pedal=true)
        val events=listOf(ReferenceDrumEvent(12,0,0,0,0,0),ReferenceDrumEvent(2,1,60,100,0,1000))
        val track=ReferenceDrumTrack("Track",1,0,32,120,true,0,1000,false,events)
        val source=ReferenceRhythmPlayer(listOf(track),sound::durationFrames,{n,a,c->sound.sample(n,a.toLong(),c)},48000,sampled=sound,expressionEffects=listOf(shake))
        val destination=ReferenceRhythmPlayer(emptyList(),{0},{_,_,_->0.0},48000)
        try{
            repeat(480){source.next(0,120)}
            destination.releaseVoicesFrom(source)
            destination.renderTail()
            assertTrue(destination.mixedLeft(1f,1f)>0)
            assertTrue(destination.mixedRight(1f,1f)>destination.mixedLeft(1f,1f))
            assertTrue(destination.hasTail)
        }finally{source.close();destination.close()}
    }
    @Test fun disabledEffectDoesNotConsumeItsStartDelayBeforeTimelineEnablesIt(){
        val enabled=effect(delay=20)
        val state=ImportedVolumeShake(enabled.copy(enabled=false),1000)
        repeat(100){state.next(false,false)}
        state.configure(enabled)
        repeat(20){state.next(false,false)}
        assertEquals(1.0,state.left,0.0)
        repeat(10){state.next(false,false)}
        assertEquals(1+sin(1.0)*.1,state.left,1e-9)
    }
    @Test fun timelineCanChangeActiveAndFutureVoiceExpression(){
        val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(48000,1,shortArrayOf(8192,8192)),0,2)),48000)
        val events=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),
            ReferenceDrumEvent(65,3,0,1,1,0),ReferenceDrumEvent(65,3,4,100,1,0),
            ReferenceDrumEvent(65,3,5,20,1,0))
        val track=ReferenceDrumTrack("Track",1,0,32,120,true,0,1000,false,events)
        val player=ReferenceRhythmPlayer(listOf(track),sound::durationFrames,{n,a,c->sound.sample(n,a.toLong(),c)},48000,sampled=sound)
        try{
            player.next(0,120);assertEquals(.25,player.left,1e-9)
            repeat(528){player.next(0,120)}
            assertTrue("Automation did not modulate active note",player.left>.25)
            player.reset();player.next(0,120)
            assertEquals(.25,player.left,1e-9)
            assertFalse(ImportedExpressionAutomation.supported(ReferenceDrumEvent(65,2,5,20,0,0)))
        }finally{player.close()}
    }
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
