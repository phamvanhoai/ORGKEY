package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedGroupFxTest {
    @Test fun revisitedSectionRetainsFreshBusAfterLiveBusWasTransferred(){
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)))
        fun player()=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000).withGroupFx(effect)
        val original=player();val destination=player()
        original.next(0,120);original.mixOutput(1f,1f)
        destination.continueGroupFxFrom(original)
        original.reset();original.next(0,120);original.mixOutput(1f,1f)
        assertEquals(1.5,original.outputLeft,0.0)
        destination.continueGroupFxFrom(original)
        original.reset();original.next(0,120);original.mixOutput(1f,1f)
        assertEquals(1.5,original.outputLeft,0.0)
        original.close();destination.close()
    }
    @Test fun globalRhythmBalanceSelectsDrumsOrChordsWithoutPanningStereo(){
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)))
        fun player(melodic:Boolean,value:Double)=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->value},1000,accompaniment=melodic)
        val group=ReferenceRhythmPlayer.combine(listOf(player(false,1.0),player(true,2.0)))
        group.withRhythmSettings(ImportedChannelSettings(volume=50,pan=100));group.next(0,120);group.mixOutput(1f,1f)
        assertEquals(.5,group.outputLeft,0.0);assertEquals(.5,group.outputRight,0.0)
        group.withRhythmSettings(ImportedChannelSettings(volume=50,pan=-100));group.next(0,120);group.mixOutput(1f,1f)
        assertEquals(.5,group.outputLeft,0.0) // Already sounding notes keep their OpenKey gain.
        group.reset();group.next(0,120);group.mixOutput(1f,1f)
        assertEquals(1.0,group.outputLeft,0.0);assertEquals(1.0,group.outputRight,0.0)
    }
    @Test fun musicBalanceSeparatesKeyboardAndRhythmWithoutStereoPan(){
        val music=ImportedChannelSettings(pan=-75)
        assertEquals(.25,ImportedMusicParameters.keyboardGain(music),0.0)
        assertEquals(1.0,ImportedMusicParameters.rhythmGain(music,ImportedChannelSettings(),false),0.0)
        val opposite=music.copy(pan=75)
        assertEquals(1.0,ImportedMusicParameters.keyboardGain(opposite),0.0)
        assertEquals(.125,ImportedMusicParameters.rhythmGain(opposite,ImportedChannelSettings(volume=50),true),0.0)
    }
    @Test fun rhythmGainIsAppliedBeforeEchoAndChangingBalanceKeepsEchoTail(){
        val track=ReferenceDrumTrack("Impulse",1,0,32,120,false,0,100000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)))
        val echo=ImportedChannelSettings(fxEnabled=true,modules=listOf(ImportedFxModule(6,true,false,listOf(50,0,0,0,1,0,0,0))))
        val child=ReferenceRhythmPlayer(listOf(track),{1},{_,_,_->1.0},48000,fx=ImportedFxChain(echo))
        val group=ReferenceRhythmPlayer.combine(listOf(child)).withRhythmSettings(ImportedChannelSettings(volume=50),ImportedChannelSettings(pan=50))
        group.next(0,120);group.mixOutput(1f,1f)
        assertEquals(.25,group.outputLeft,0.0)
        group.withRhythmSettings(ImportedChannelSettings(volume=0))
        repeat(479){group.next(0,120);group.mixOutput(1f,1f)}
        group.next(0,120);group.mixOutput(1f,1f)
        assertEquals(.125,group.outputLeft,0.0);assertEquals(.125,group.outputRight,0.0)
    }
    private val module=ImportedFxModule(20,true,false,listOf(16,0,0,0,1,0,0,0))
    private val effect=ImportedChannelSettings(fxEnabled=true,modules=listOf(module))
    private fun group(id:Int,parent:Int,settings:ImportedChannelSettings=ImportedChannelSettings())=
        ImportedStyleGroup(id,parent,"Group",32,true,0,1000,120,settings=settings)
    @Test fun sectionInheritsEnabledParentAndOverridesWhenItsOwnBlockExists(){
        val parent=group(100,0,effect);val part=group(1,100)
        val index=ImportedSetIndex(emptyList(),listOf(parent,part))
        assertEquals(effect,ImportedGroupFx.resolve(index,part))
        val own=effect.copy(modules=listOf(module.copy(enabled=false)))
        assertEquals(own,ImportedGroupFx.resolve(index,part.copy(settings=own)))
    }
    @Test fun disabledOrEmptyBlockFallsBackAndParentCycleTerminates(){
        val root=group(0,0,effect);val part=group(1,2,effect.copy(fxEnabled=false));val parent=group(2,1)
        val index=ImportedSetIndex(emptyList(),listOf(root,part,parent),rhythmSettings=effect)
        assertEquals(effect,ImportedGroupFx.resolve(index,part))
        assertEquals(effect,ImportedGroupFx.resolve(index,part.copy(settings=effect.copy(modules=emptyList()))))
    }
    @Test fun reachingGroupZeroUsesGlobalRhythmFxInsteadOfItsLocalBlock(){
        val own=effect.copy(modules=listOf(module.copy(enabled=false)))
        val root=group(0,0,own);val part=group(1,0)
        val index=ImportedSetIndex(emptyList(),listOf(root,part),rhythmSettings=effect)
        assertEquals(effect,ImportedGroupFx.resolve(index,part))
        assertEquals(effect,ImportedGroupFx.resolve(index,root))
    }
    @Test fun groupDspProcessesSumAfterDrumAndChordGain(){
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)))
        fun player(melodic:Boolean)=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->1.0},1000,accompaniment=melodic)
        val group=ReferenceRhythmPlayer.combine(listOf(player(false),player(true))).withGroupFx(effect)
        group.next(0,120);group.mixOutput(.5f,.25f)
        assertEquals(1.125,group.outputLeft,0.0);assertEquals(1.125,group.outputRight,0.0)
        group.reset();assertEquals(0.0,group.outputLeft,0.0)
        group.next(0,120);group.mixOutput(.5f,.25f);assertEquals(1.125,group.outputLeft,0.0)
    }
}
