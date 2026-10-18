package com.hovait.orgx

import kotlin.math.sin
import org.junit.Assert.*
import org.junit.Test

class ImportedPitchShakeTest {
    @Test fun changingSectionKeepsTheExactPitchPhaseOfReleasedVoices(){
        val pcm=SamplePcm(1000,1,ShortArray(200){(it*100).toShort()})
        val sound=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,200)),1000,
            envelope=ImportedEnvelope(0,0,100,100,false))
        val shake=effect().copy(startDelay=0,byPedal=false,stopByRelease=false,depth=8192,gain=0)
        val track=ReferenceDrumTrack("Main",1,0,32,120,true,0,1000,false,
            listOf(ReferenceDrumEvent(2,1,60,100,0,1000)))
        fun player()=ReferenceRhythmPlayer(listOf(track),sound::durationFrames,
            {n,a,c->sound.sample(n,a.toLong(),c)},1000,sampled=sound,expressionEffects=listOf(shake))
        val source=player();val reference=player()
        val destination=ReferenceRhythmPlayer(emptyList(),{0},{_,_,_->0.0},1000)
        try{
            repeat(25){source.next(0,120);reference.next(0,120)}
            reference.releaseAll();destination.releaseVoicesFrom(source)
            repeat(30){
                reference.renderTail();destination.renderTail()
                assertEquals(reference.left,destination.mixedLeft(1f,1f),1e-12)
                assertEquals(reference.right,destination.mixedRight(1f,1f),1e-12)
            }
            source.reset();source.next(0,120)
            assertEquals(0.0,source.left,0.0)
            assertTrue(destination.hasTail)
        }finally{source.close();reference.close();destination.close()}
    }
    @Test fun automationPreservesPhaseAndDisableImmediatelyClearsPitchOffset(){
        val first=effect().copy(startDelay=0,byPedal=false,gain=0)
        val state=ImportedPitchShake(first,1000)
        repeat(10){state.next(false,false)}
        state.configure(ImportedExpressionAutomation.update(first,ReferenceDrumEvent(65,2,5,200,0,0)))
        repeat(10){state.next(false,false)}
        assertEquals((sin(2.0).toFloat()*200).toDouble(),state.offset,0.0)
        state.configure(first.copy(enabled=false))
        assertEquals(0.0,state.offset,0.0)
    }
    private fun effect()=ImportedExpressionEffect(2,true,10,true,true,0,100,100,50,emptyList())
    @Test fun delayPedalDriftAndReleaseFollowSlotTwoFormula(){
        val state=ImportedPitchShake(effect(),1000)
        repeat(30){state.next(false,false)};assertEquals(0.0,state.offset,0.0)
        repeat(19){state.next(false,true)};assertEquals(0.0,state.offset,0.0)
        state.next(false,true);assertEquals((sin(1.0).toFloat()*100+5).toDouble(),state.offset,0.0)
        state.next(true,true);assertEquals(0.0,state.offset,0.0)
    }
    @Test fun sampledOutputChangesWithoutSharingPhaseWithAnotherVoice(){
        val pcm=SamplePcm(1000,1,ShortArray(100){(it*200).toShort()})
        val plain=SampledInstrument(listOf(InstrumentRegion(60,pcm,0,100)),1000)
        val changed=plain.copySampleSource().withExpressionEffects(listOf(effect().copy(startDelay=0,byPedal=false,depth=8192,gain=0)))
        val voice=changed.voice(60);val reference=plain.voice(60)
        repeat(11){voice.next(false);reference.next(false)}
        assertTrue(voice.left>reference.left)
        val fresh=changed.voice(60);fresh.next(false)
        assertEquals(0.0,fresh.left,0.0)
    }
}
