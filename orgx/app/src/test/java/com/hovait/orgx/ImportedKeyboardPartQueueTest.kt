package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedKeyboardPartQueueTest {
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
