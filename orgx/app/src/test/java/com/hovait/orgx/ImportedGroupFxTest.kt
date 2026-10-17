package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedGroupFxTest {
    @Test fun globalRhythmBalanceSelectsDrumsOrChordsWithoutPanningStereo(){
        val track=ReferenceDrumTrack("Test",1,0,32,120,true,0,1000,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)))
        fun player(melodic:Boolean,value:Double)=ReferenceRhythmPlayer(listOf(track),{1000},{_,_,_->value},1000,accompaniment=melodic)
        val group=ReferenceRhythmPlayer.combine(listOf(player(false,1.0),player(true,2.0)))
        group.next(0,120)
        group.withRhythmSettings(ImportedChannelSettings(volume=50,pan=100));group.mixOutput(1f,1f)
        assertEquals(.5,group.outputLeft,0.0);assertEquals(.5,group.outputRight,0.0)
        group.withRhythmSettings(ImportedChannelSettings(volume=50,pan=-100));group.mixOutput(1f,1f)
        assertEquals(1.0,group.outputLeft,0.0);assertEquals(1.0,group.outputRight,0.0)
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
