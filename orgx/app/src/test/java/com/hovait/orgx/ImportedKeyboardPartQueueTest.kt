package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedKeyboardPartQueueTest {
    @Test fun secondInitialGroupCannotOverwriteAGroupCommittedWhileItWasLoading(){
        val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
        try{
            var current:Any?=null
            val first=Any()
            val second=Any()
            val expected:Any?=null
            queue.commit(listOf(part(100))){
                if(current!==expected)false else {current=first;true}
            }
            queue.commit(listOf(part(50))){
                if(current!==expected)false else {current=second;true}
            }
            queue.noteOn(60,1.0)
            assertEquals(.25,frame(queue,mixer),0.0)
            assertSame(first,current)
        }finally{mixer.close()}
    }
    @Test fun groupAndMasterAreCommittedAtTheSameFrameAndStaleCommitIsIgnored(){
        val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
        try{
            var tempo=120
            queue.commit(listOf(part(100))){tempo=142;mixer.configureMusic(ImportedChannelSettings(volume=40));true}
            queue.noteOn(60,1.0)
            assertEquals(120,tempo)
            assertEquals(.25,frame(queue,mixer),0.0)
            assertEquals(142,tempo);assertEquals(.4,mixer.musicVolume,0.0)
            queue.commit(listOf(part(50))){false}
            queue.noteOff(60);queue.noteOn(60,1.0)
            assertEquals(.25,frame(queue,mixer),0.0)
            queue.clear{tempo=120}
            frame(queue,mixer);assertEquals(120,tempo)
        }finally{mixer.close()}
    }
    @Test fun livePedalReleasesHeldNotesEvenAfterTheirPartWasReplaced(){
        val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
        try{
            queue.replace(listOf(part(100)));queue.pedal(true);queue.noteOn(60,1.0)
            assertEquals(.25,frame(queue,mixer),0.0)
            queue.noteOff(60);queue.replace(emptyList())
            assertEquals(.25,frame(queue,mixer),0.0)
            queue.pedal(false)
            assertEquals(0.0,frame(queue,mixer),0.0)
        }finally{mixer.close()}
    }
    @Test fun pitchBendAffectsExistingAndFutureVoicesAndClearRestoresUnity(){
        val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
        val ramp=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,
            shortArrayOf(0,4096,8192,12288,16384,20480,24576,28672)),0,8)),1000)
        try{
            queue.replace(listOf(ImportedKeyboardParts.Part(1,ramp)));queue.pitchBend(12.0);queue.noteOn(60,1.0)
            assertEquals(0.0,frame(queue,mixer),0.0)
            assertEquals(.25,frame(queue,mixer),0.0)
            queue.pitchBend(0.0)
            assertEquals(.5,frame(queue,mixer),0.0)
            assertEquals(.625,frame(queue,mixer),0.0)
            queue.clear();queue.replace(listOf(ImportedKeyboardParts.Part(1,ramp)));queue.noteOn(60,1.0)
            assertEquals(0.0,frame(queue,mixer),0.0)
            assertEquals(.125,frame(queue,mixer),0.0)
        }finally{mixer.close()}
    }
    private fun part(volume:Int)=ImportedKeyboardParts.Part(1,SampledInstrument(listOf(InstrumentRegion(60,
        SamplePcm(1000,1,shortArrayOf(8192,8192)),0,2)),1000)
        .withKeyboardSettings(ImportedChannelSettings(volume=volume),true))
    private fun frame(queue:ImportedKeyboardPartQueue,mixer:ImportedKeyboardMixer):Double {
        mixer.beginFrame();queue.renderInto(mixer);mixer.finishFrame();return mixer.left
    }
    @Test fun queuedReplacementAndNotesExecuteInSubmissionOrderOnRender(){
        val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
        try{
            queue.replace(listOf(part(100)));queue.noteOn(60,1.0)
            queue.replace(listOf(part(50)));queue.noteOn(60,1.0)
            assertEquals(.375,frame(queue,mixer),0.0)
            queue.noteOff(60)
            assertEquals(0.0,frame(queue,mixer),0.0)
            queue.noteOn(60,1.0)
            assertEquals(.125,frame(queue,mixer),0.0)
            queue.clear();queue.noteOn(60,1.0)
            assertEquals(0.0,frame(queue,mixer),0.0)
        }finally{mixer.close()}
    }
    @Test fun replacementCopiesCallerListAndRejectsInvalidInputBeforeEnqueue(){
        val queue=ImportedKeyboardPartQueue();val mixer=ImportedKeyboardMixer()
        try{
            val list=mutableListOf(part(100));queue.replace(list);list.clear()
            try{queue.replace(listOf(part(50).copy(id=9)));fail("Invalid part accepted")}
            catch(expected:IllegalArgumentException){}
            queue.noteOn(60,1.0)
            assertEquals(.25,frame(queue,mixer),0.0)
        }finally{mixer.close()}
    }
}
