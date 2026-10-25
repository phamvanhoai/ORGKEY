package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedKeyboardPartsTest {
    @Test fun directChordUsesEnabledStsPartsAndTheirGainsAndCutsRemovedParts(){
        fun source(value:Short)=SampledInstrument(listOf(InstrumentRegion(36,
            SamplePcm(1000,1,shortArrayOf(value,value)),0,2)),1000)
            .withModeScript("on(touch){return(true);}")
        val first=source(8192);val second=source(16384)
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        fun frame(mask:Int):Double{
            mixer.beginFrame();parts.renderDirectChord(mixer,mask);mixer.finishFrame();return mixer.left
        }
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,first,inputGain=.5),
                ImportedKeyboardParts.Part(2,second,enabled=false)))
            assertEquals(.375,frame(145),1e-12)
            parts.replace(listOf(ImportedKeyboardParts.Part(1,first,inputGain=.5),ImportedKeyboardParts.Part(2,second,inputGain=.25)))
            assertEquals(.75,frame(145),1e-12)
            parts.replace(listOf(ImportedKeyboardParts.Part(2,second,inputGain=.25)))
            assertEquals(.375,frame(145),1e-12)
            assertEquals(0.0,frame(0),0.0)
            assertEquals(.125,frame(1),1e-12)
            parts.retire();assertEquals(0.0,frame(1),0.0)
        }finally{parts.reset();mixer.close()}
    }
    @Test fun directChordOpensMaskNotesWithoutTouchAndReplacesRatherThanStacksChords(){
        val source=SampledInstrument(listOf(InstrumentRegion(36,SamplePcm(1000,1,shortArrayOf(8192,8192)),0,2)),1000)
            .withModeScript("on(touch){return(true);}")
        val chord=ImportedDirectChord();val mixer=ImportedKeyboardMixer()
        fun frame():Double{mixer.beginFrame();chord.renderInto(mixer);mixer.finishFrame();return mixer.left}
        try{
            chord.select(source,(1 shl 0) or (1 shl 4) or (1 shl 7))
            assertEquals(.75,frame(),1e-12)
            chord.select(source,(1 shl 2) or (1 shl 5))
            assertEquals(.5,frame(),1e-12)
            chord.select(source,0);assertEquals(0.0,frame(),0.0)
            chord.select(source,1);assertEquals(.25,frame(),1e-12)
            chord.cut();assertEquals(0.0,frame(),0.0)
        }finally{chord.cut();mixer.close()}
    }
    @Test fun musicReleaseUpdateAffectsNextStsNoteWithoutRebuildingParts(){
        var release=100
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,shortArrayOf(8192,8192)),0,2)),1000,
            envelope=ImportedEnvelope(0,0,100,0,false),rawRelease=20,musicReleasePercent=0)
        val parts=ImportedKeyboardParts(musicRelease={release});val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
            parts.noteOn(60,1.0);frame(parts,mixer)
            release=0
            parts.noteOn(64,1.0);frame(parts,mixer)
            parts.noteOff(60);parts.noteOff(64)
            assertEquals(.25*(1-1.0/200),frame(parts,mixer),1e-12)
            assertEquals(1,parts.activeNotes)
        }finally{parts.reset();mixer.close()}
    }
    @Test fun polyphonyIsPerPartAndStealsTheOldestNote(){
        val before=ImportedPolyphony.music
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            ImportedPolyphony.music=8
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100)),ImportedKeyboardParts.Part(2,sound(100))))
            for(key in 60..68)parts.noteOn(key,1.0)
            assertEquals(16,parts.activeNotes)
            parts.noteOff(60);frame(parts,mixer)
            assertEquals(16,parts.activeNotes)
            parts.noteOff(61);frame(parts,mixer)
            assertEquals(14,parts.activeNotes)
        }finally{ImportedPolyphony.music=before;mixer.close()}
    }
    @Test fun repeatByPedalUsesActualPedalInsteadOfEnvelopePedalMode(){
        for((pedal,mode,expected) in listOf(Triple(false,1,1),Triple(true,0,2))){
            val effect=ImportedExpressionEffect(0,true,0,true,false,0,500,0,0,emptyList())
            val source=sound(100).withExpressionEffects(listOf(effect))
            val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
            try{
                parts.replace(listOf(ImportedKeyboardParts.Part(1,source,pedal=pedal,pedalMode=mode)))
                parts.noteOn(60,1.0)
                repeat(20){frame(parts,mixer)}
                assertEquals("pedal=$pedal mode=$mode",expected,parts.activeNotes)
            }finally{mixer.close()}
        }
    }
    @Test fun connectionAfterPartReplacementUsesNewSampleInsteadOfReusingOldSound(){
        fun source(value:Short)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(32){value}),0,32)),1000)
            .withExpressionEffects(listOf(ImportedExpressionEffect.default(1).copy(enabled=true,mode=16,stopByRelease=false)))
        val first=source(8192);val second=source(16384)
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,first)))
            parts.noteOn(60,1.0);assertEquals(.25,frame(parts,mixer),0.0)
            parts.replace(listOf(ImportedKeyboardParts.Part(1,second)))
            parts.noteOn(72,1.0)
            assertEquals(2,parts.activeNotes);assertEquals(.75,frame(parts,mixer),0.0)
            parts.noteOff(60);assertEquals(.5,frame(parts,mixer),0.0)
        }finally{mixer.close()}
    }
    @Test fun retiredVoiceCannotBeReconnectedEvenWhenTheSameSoundIsSelectedAgain(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(32){8192}),0,32)),1000,envelope=ImportedEnvelope(0,0,100,10,false))
            .withExpressionEffects(listOf(ImportedExpressionEffect.default(1).copy(enabled=true,mode=16,stopByRelease=false)))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
            parts.noteOn(60,1.0);frame(parts,mixer);parts.retire()
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
            parts.noteOn(72,1.0)
            assertEquals(2,parts.activeNotes)
            repeat(120){frame(parts,mixer)}
            assertEquals(1,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun modulationStopAllCutsReleasedTailsWithoutAffectingAnotherMidiChannel(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(128){8192}),0,128)),1000,
            envelope=ImportedEnvelope(0,0,100,10,false))
            .withModeScript("on(touch){play();return(true);} on(modulate){stopall(cut);}")
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
            parts.noteOn(60,1.0,1);parts.noteOn(64,1.0,2)
            frame(parts,mixer)
            parts.noteOff(60,1);parts.noteOff(64,2)
            assertTrue(frame(parts,mixer)>0)
            assertEquals(2,parts.activeNotes)
            parts.modulate(20,1)
            assertTrue(frame(parts,mixer)>0)
            assertEquals(1,parts.activeNotes)
            parts.modulate(20,2)
            assertEquals(0.0,frame(parts,mixer),0.0)
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun modulationStopAllAlsoReachesRetiredPartReleaseTail(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(128){8192}),0,128)),1000,
            envelope=ImportedEnvelope(0,0,100,10,false))
            .withModeScript("on(touch){play();return(true);} on(modulate){stopall(cut);}")
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
            parts.noteOn(60,1.0);frame(parts,mixer)
            parts.noteOff(60);frame(parts,mixer)
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100))))
            parts.modulate(20)
            assertEquals(0.0,frame(parts,mixer),0.0)
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun octaveScriptCreatesVoicesAndReleasesThemByOriginalKey(){
        val source=sound(100).withModeScript("on(touch) { play(volume:50); play(octavemove:1,volume:50); play(octavemove:-1,volume:50); return(true); }")
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
            parts.noteOn(60,1.0)
            assertEquals(1,parts.activeNotes)
            assertEquals(.375*100/127,frame(parts,mixer),1e-12)
            parts.noteOff(60);frame(parts,mixer);assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun keyRepeatAddsAutoReleaseVoicesWithoutRecursiveRepeat(){
        val repeat=ImportedExpressionEffect(0,true,0,false,false,0,500,0,0,emptyList())
        val repeated=sound(100).withExpressionEffects(listOf(repeat))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,repeated)))
            parts.noteOn(60,1.0)
            repeat(19){frame(parts,mixer)}
            assertEquals(1,parts.activeNotes)
            frame(parts,mixer)
            assertEquals(2,parts.activeNotes)
            // Spawned voices have no repeat controller, preventing exponential growth.
            repeat(20){frame(parts,mixer)}
            assertTrue(parts.activeNotes<=2)
            parts.noteOff(60);repeat(4){frame(parts,mixer)}
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun lowKeyConnectionModeOpensANewVoiceFromPreviousKey(){
        val connection=ImportedExpressionEffect(1,true,0,false,false,0,819,0,0,emptyList())
        val connected=sound(100).withExpressionEffects(listOf(connection))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,connected)))
            parts.noteOn(60,1.0);parts.noteOn(72,1.0)
            assertEquals(2,parts.activeNotes)
            parts.noteOff(60);frame(parts,mixer)
            assertEquals(1,parts.activeNotes)
            parts.noteOff(72);frame(parts,mixer)
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun highKeyConnectionModeReusesLastVoiceAndTransfersItsNoteIdentity(){
        val connection=ImportedExpressionEffect(1,true,0,false,false,16,0,0,0,emptyList())
        val connected=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(32){(it+1).toShort()}),0,32,looping=false)),1000)
            .withExpressionEffects(listOf(connection))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,connected)))
            parts.noteOn(60,1.0);frame(parts,mixer)
            parts.noteOn(72,0.5)
            assertEquals(1,parts.activeNotes)
            // Reused channel keeps its original sample position instead of restarting.
            assertEquals(2.0/32768,frame(parts,mixer),1e-12)
            parts.noteOff(60);assertEquals(1,parts.activeNotes)
            parts.noteOff(72);frame(parts,mixer);assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun highConnectionZeroSpeedRestartsOnlyOriginalLowModesOneAndThree(){
        fun rendered(mode:Int,looping:Boolean):Double {
            val effect=ImportedExpressionEffect(1,true,0,false,false,16+mode,0,0,0,emptyList())
            val pcm=ShortArray(12){(it+1).toShort()}
            val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,pcm),2,10,looping=looping)),1000)
                .withExpressionEffects(listOf(effect))
            val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
            return try{
                parts.replace(listOf(ImportedKeyboardParts.Part(1,sound)))
                parts.noteOn(60,1.0);repeat(3){frame(parts,mixer)};parts.noteOn(61,1.0)
                frame(parts,mixer)
            }finally{mixer.close()}
        }
        assertEquals(1.0/32768,rendered(1,true),1e-12)
        assertEquals(4.0/32768,rendered(3,true),1e-12)
        assertEquals(1.0/32768,rendered(3,false),1e-12)
        assertEquals(4.0/32768,rendered(0,true),1e-12)
    }

    @Test fun lowConnectionZeroSpeedOverridesLoopPointWithSampleStart(){
        val effect=ImportedExpressionEffect(1,true,0,false,false,2,99,0,0,emptyList())
        val pcm=ShortArray(12){(it+1).toShort()}
        val sound=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,pcm),5,10,looping=true)),1000)
            .withExpressionEffects(listOf(effect))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound)))
            parts.noteOn(60,1.0);parts.noteOn(61,1.0)
            assertEquals(2.0/32768,frame(parts,mixer),1e-12)
        }finally{mixer.close()}
    }
    @Test fun keyConnectionByPedalOnlyReusesVoiceWhilePedalIsDown(){
        val effect=ImportedExpressionEffect(1,true,0,true,false,16,0,0,0,emptyList())
        val connected=sound(100).withExpressionEffects(listOf(effect))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,connected,pedal=false,pedalMode=-1)))
            parts.noteOn(60,1.0);parts.noteOn(61,1.0)
            assertEquals(2,parts.activeNotes)
            parts.pedal(1,true);parts.noteOn(62,1.0)
            assertEquals(2,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun polyPressureSwitchesOnlyMatchingKeyAndChannelPressureSwitchesRemainingParts(){
        fun constant(value:Short)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,shortArrayOf(value,value)),0,2)),1000)
        val sound=constant(8192).withAftertouch(constant(16384))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound)))
            parts.noteOn(60,1.0);parts.noteOn(72,1.0)
            assertEquals(.5,frame(parts,mixer),0.0)
            parts.pressure(60,1f)
            assertEquals(.75,frame(parts,mixer),0.0)
            parts.channelPressure(1f)
            assertEquals(1.0,frame(parts,mixer),0.0)
            parts.noteOff(60);parts.noteOff(72)
            assertEquals(0.0,frame(parts,mixer),0.0)
        }finally{mixer.close()}
    }
    @Test fun musicKeyboardBalanceIsLatchedOnceAndDoesNotApplyMasterVolumeTwice(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            mixer.configureMusic(ImportedChannelSettings(volume=40,pan=-50))
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100),inputGain=.5)))
            parts.noteOn(60,1.0)
            assertEquals(.125,frame(parts,mixer),0.0)
            assertEquals(.05,mixer.left*mixer.musicVolume,1e-12)
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100),inputGain=1.0)))
            assertEquals(.125,frame(parts,mixer),0.0) // live note keeps old OpenKey gain
        }finally{mixer.close()}
    }
    @Test fun keyAreasAreInclusiveAndRecordDisabledPartsDoNotOpenNotes(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(
                ImportedKeyboardParts.Part(1,sound(100),fromKey=48,toKey=60),
                ImportedKeyboardParts.Part(2,sound(50),fromKey=60,toKey=72),
                ImportedKeyboardParts.Part(3,sound(100),enabled=false)))
            parts.noteOn(60,1.0)
            assertEquals(.375,frame(parts,mixer),1e-9)
            assertEquals(2,parts.activeNotes)
            parts.reset();parts.noteOn(73,1.0)
            assertEquals(0.0,frame(parts,mixer),0.0)
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun noteOffStillReleasesVoiceAfterItsPartIsDisabledOrRangeChanges(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            val old=ImportedKeyboardParts.Part(1,sound(100),fromKey=60,toKey=60)
            parts.replace(listOf(old));parts.noteOn(60,1.0)
            assertEquals(.25,frame(parts,mixer),0.0)
            parts.replace(listOf(old.copy(enabled=false,fromKey=72,toKey=84)))
            parts.noteOn(60,1.0)
            assertEquals(1,parts.activeNotes)
            parts.noteOff(60)
            assertEquals(0.0,frame(parts,mixer),0.0)
        }finally{mixer.close()}
    }
    private fun sound(volume:Int)=SampledInstrument(listOf(InstrumentRegion(60,
        SamplePcm(1000,1,shortArrayOf(8192,8192)),0,2)),1000)
        .withKeyboardSettings(ImportedChannelSettings(volume=volume),true)
    private fun frame(parts:ImportedKeyboardParts,mixer:ImportedKeyboardMixer):Double {
        mixer.beginFrame();parts.renderInto(mixer);mixer.finishFrame();return mixer.left
    }
    @Test fun pedalModeIsAppliedPerPartWithoutMultiplyingMasterBus(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100),true,pedalMode=-1),ImportedKeyboardParts.Part(2,sound(50))))
            parts.noteOn(60,1.0)
            assertEquals(.375,frame(parts,mixer),1e-9)
            parts.noteOff(60)
            // These fixtures have no envelope, so both released voices close immediately.
            assertEquals(0.0,frame(parts,mixer),0.0)
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun dynamicPedalChoosesLongReleaseAtNoteOffAndDoesNotFreezeTheNote(){
        fun enveloped()=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(2000){8192}),0,2000)),1000,
            envelope=ImportedEnvelope(0,0,100,10,false))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,enveloped(),true,pedalMode=-1)))
            parts.noteOn(60,1.0);frame(parts,mixer);parts.noteOff(60)
            val first=frame(parts,mixer)
            assertTrue(first in 0.24..0.25)
            repeat(120){frame(parts,mixer)}
            assertEquals(1,parts.activeNotes)
            parts.pedal(1,false)
            // Native envelope 4 changes to the fast envelope 5 when pedal goes up.
            repeat(100){frame(parts,mixer)}
            assertEquals(0,parts.activeNotes)
        }finally{mixer.close()}
    }
    @Test fun replacementPreservesLiveVoiceAndInvalidBatchLeavesOldParts(){
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(100))))
            parts.noteOn(60,1.0)
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound(50))))
            assertEquals(.25,frame(parts,mixer),0.0)
            try{parts.replace(listOf(ImportedKeyboardParts.Part(5,sound(100))));fail("Invalid part accepted")}
            catch(expected:IllegalArgumentException){}
            parts.noteOn(60,1.0)
            assertEquals(.375,frame(parts,mixer),0.0)
            parts.reset();assertEquals(0.0,frame(parts,mixer),0.0)
        }finally{mixer.close()}
    }
    @Test fun sameKeyOnTwoMidiChannelsHasIndependentReleaseAndPressure(){
        fun constant(value:Short)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,shortArrayOf(value,value)),0,2)),1000)
        val sound=constant(8192).withAftertouch(constant(16384))
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,sound)))
            parts.noteOn(60,1.0,1);parts.noteOn(60,1.0,2)
            assertEquals(.5,frame(parts,mixer),0.0)
            parts.pressure(60,1f,1)
            assertEquals(.75,frame(parts,mixer),0.0)
            parts.noteOff(60,1)
            assertEquals(.25,frame(parts,mixer),0.0)
            assertEquals(1,parts.activeNotes)
            parts.channelPressure(1f,2)
            assertEquals(.5,frame(parts,mixer),0.0)
            parts.noteOff(60,2);assertEquals(0.0,frame(parts,mixer),0.0)
        }finally{mixer.close()}
    }
}
