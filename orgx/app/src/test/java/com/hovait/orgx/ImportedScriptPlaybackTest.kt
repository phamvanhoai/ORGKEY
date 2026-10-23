package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class ImportedScriptPlaybackTest {
    @Test fun originalAftertouchShakeChangesCurrentlySoundingVoice(){
        fun instrument()=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(4000){(it%100*200).toShort()}),0,4000)),1000)
        val scripted=instrument().withModeScript(original("Shake/Aftertouch Shake.Script")).voice(60)
        val plain=instrument().voice(60)
        scripted.aftertouch(1f)
        var difference=0.0
        repeat(500){scripted.next(false);plain.next(false);difference+=kotlin.math.abs(scripted.left-plain.left)}
        assertTrue(difference>0.0)
        scripted.aftertouch(0f)
        repeat(20){scripted.next(false);assertTrue(scripted.left.isFinite())}
    }
    @Test fun originalDynamicVelocityScriptGlidesFromPreviousTouch(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(64){(it*100).toShort()}),0,64)),1000)
            .withModeScript(original("Legato/Dynamic Legato (Velocity).Script"))
        val first=source.voice(60,scriptVelocity=110);first.next(false)
        assertTrue(source.keyConnectionEffect?.enabled==true)
        val next=source.voice(72,scriptVelocity=80);next.next(false);next.next(false)
        // Destination normally advances by 2 samples; connected voice starts near 1.
        assertTrue(next.left>0.0 && next.left<200.0/32768)
    }
    @Test fun originalLastKeyLegatoKeepsStateAndLimitsBothDirections(){
        val session=ImportedScriptSession(original("Legato/Legato from Last.Script"))
        fun connection(key:Int)=session.event("touch",mapOf("key" to key)).plays.single().parameters["connectkey"]
        assertEquals(60,connection(60))
        assertEquals(66,connection(72))
        assertEquals(66,connection(60))
        assertEquals(60,connection(63))
    }
    @Test fun directSliderQueueCommitsAudioValueAndRejectsStaleOrInvalidChanges(){
        val source=sound().withModeScript("main() { setting(slider, name:\"level\", title:\"Level\", reset:25, from:0, to:100); } on(touch) { play(volume:level); return(true); }")
        val queue=ImportedKeyboardPartQueue();var current=true;var accepted=false
        queue.updateScriptSlider(source,"level",75,{current}){accepted=it}
        assertEquals(25,source.scriptSliderValues["level"])
        queue.drain();assertTrue(accepted)
        val updated=source.voice(60);updated.next(false);assertEquals(.1875,updated.left,1e-12)
        queue.updateScriptSlider(source,"level",100,{current}){accepted=it}
        current=false;queue.drain();assertFalse(accepted)
        assertEquals(75,source.scriptSliderValues["level"])
        current=true
        queue.updateScriptSlider(source,"level",101,{current}){accepted=it};queue.drain()
        assertFalse(accepted);assertEquals(75,source.scriptSliderValues["level"])
        queue.updateScriptSlider(source,"level",source.scriptSliders.single().reset,{current}){accepted=it};queue.drain()
        assertTrue(accepted)
        val reset=source.voice(60);reset.next(false);assertEquals(.0625,reset.left,1e-12)
    }
    @Test fun queuedSliderChangeCannotModifyReplacementSound(){
        fun configured()=sound().withModeScript("main() { setting(slider, name:\"level\", title:\"Level\", reset:25, from:0, to:100); } on(touch) { play(volume:level); return(true); }")
        val old=configured();val replacement=configured();val queue=ImportedKeyboardPartQueue()
        queue.replace(listOf(ImportedKeyboardParts.Part(1,old)))
        var accepted=false
        queue.setScriptSlider(1,old,"level",75){accepted=it};queue.drain()
        assertTrue(accepted)
        queue.replace(listOf(ImportedKeyboardParts.Part(1,replacement)))
        queue.setScriptSlider(1,old,"level",100){accepted=it};queue.drain()
        assertFalse(accepted)
        val voice=replacement.voice(60);voice.next(false);assertEquals(.0625,voice.left,1e-12)
    }
    @Test fun sliderChangesOnlyFutureTouchesAndRejectsOutOfRangeValue(){
        val source=sound().withModeScript("main() { setting(slider, name:\"level\", title:\"Level\", reset:25, from:0, to:100); } on(touch) { play(volume:level); return(true); }")
        val first=source.voice(60);first.next(false)
        source.setScriptSlider("level",75)
        val second=source.voice(60);second.next(false);first.next(false)
        assertEquals(.0625,first.left,1e-12);assertEquals(.1875,second.left,1e-12)
        try{source.setScriptSlider("level",101);fail("Invalid slider accepted")}
        catch(expected:IllegalArgumentException){}
        val third=source.voice(60);third.next(false);assertEquals(.1875,third.left,1e-12)
    }
    @Test fun originalLegatoWithSettingUsesDefaultLowerPitch(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
            .withModeScript(original("Legato/Legato from + Setting.Script"))
        val voice=source.voice(60);voice.next(false);voice.next(false)
        assertTrue(voice.left>0.0 && voice.left<100.0/32768)
    }
    @Test fun velocityConditionSelectsDifferentVoiceGainThroughPlayback(){
        val source=sound().withModeScript("on(touch) { if (velocity < 100) { play(volume:25); } else { play(volume:75); } return(true); }")
        val soft=source.voice(60,scriptVelocity=50);soft.next(false)
        val hard=source.voice(60,scriptVelocity=110);hard.next(false)
        assertEquals(.0625,soft.left,1e-12)
        assertEquals(.1875,hard.left,1e-12)
    }
    @Test fun handledAftertouchKeepsPrimaryAndStoresOriginalKeyVelocity(){
        val source=sound().withAftertouch(SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(16){16384}),0,16)),1000))
            .withModeScript("main() { level = 100; } on(touch) { play(volume:level); return(true); } on(aftertouch) { level = keyvelocity; return(true); }")
        val first=source.voice(60,scriptVelocity=50)
        first.aftertouch(1f);first.next(false)
        assertEquals(.25,first.left,1e-12)
        val second=source.voice(60);second.next(false)
        assertEquals(.125,second.left,1e-12)
    }
    @Test fun releaseUpdatesScriptStateOnlyOnceDuringTailRendering(){
        val source=sound().withModeScript("main() { level = 100; } on(touch) { play(volume:level); return(true); } on(release) { level = level - 25; }")
        val first=source.voice(60);first.next(false)
        repeat(5){first.next(true)}
        val second=source.voice(60);second.next(false)
        assertEquals(.1875,second.left,1e-12)
    }
    @Test fun repeatedTouchesUseStateInitializedByMain(){
        val source=sound().withModeScript("main() { level = 0; } on(touch) { level = level + 25; play(volume:level); return(true); }")
        val first=source.voice(60);first.next(false)
        val second=source.voice(60);second.next(false)
        assertEquals(.0625,first.left,1e-12)
        assertEquals(.125,second.left,1e-12)
        first.next(false);assertEquals(.0625,first.left,1e-12)
    }
    @Test fun overflowingScriptTuneFallsBackBeforeCreatingAnyScriptVoice(){
        val source=sound().withModeScript("on(touch) { play(volume:50); play(tune:2147483647); return(true); }")
        val voice=source.voice(60,tune=1)
        voice.next(false)
        assertEquals(.25,voice.left,1e-12)
    }
    @Test fun scriptReleaseOverrideReachesEveryLayerWithoutSharingLiveState(){
        fun layer(attack:Int)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(2000){8192}),0,2000)),1000,
            envelope=ImportedEnvelope(attack,0,100,20,false))
        val instrument=SampledInstrument.combine(listOf(layer(5),layer(10)))
            .withModeScript("on(touch) { play(attack:0,release:1); return(true); }")
        val first=instrument.voice(60);val second=instrument.voice(60)
        first.next(false);second.next(false)
        assertEquals(.5,first.left,1e-12)
        repeat(20){first.next(true);second.next(false)}
        assertTrue(first.finished);assertFalse(second.finished)
        assertEquals(.5,second.left,1e-12)
    }
    @Test fun scriptEnvelopeOverridesAttackAndRelease(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(2000){8192}),0,2000)),1000,
            envelope=ImportedEnvelope(10,0,100,20,false))
        val voice=source.withModeScript("on(touch) { play(attack:0,release:1); return(true); }").voice(60)
        voice.next(false);assertEquals(.25,voice.left,1e-12)
        repeat(20){voice.next(true)};assertTrue(voice.finished)
    }
    @Test fun scriptAutoReleaseOverridesSoundEnvelopeInBothDirections(){
        fun voice(stored:Boolean,requested:Boolean)=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(2000){8192}),0,2000)),1000,
            envelope=ImportedEnvelope(0,0,100,1,stored))
            .withModeScript("on(touch) { play(autorelease:$requested); return(true); }").voice(60)
        val automatic=voice(false,true);val held=voice(true,false)
        repeat(120){automatic.next(false);held.next(false)}
        assertTrue(automatic.finished);assertFalse(held.finished)
        repeat(20){held.next(true)};assertTrue(held.finished)
    }
    @Test fun scriptAutoReleaseClosesLoopWithoutPhysicalNoteOff(){
        val automatic=sound().withModeScript("on(touch) { play(autorelease:true); return(true); }").voice(60)
        val held=sound().withModeScript("on(touch) { play(autorelease:false); return(true); }").voice(60)
        repeat(20){automatic.next(false);held.next(false)}
        assertTrue(automatic.finished)
        assertFalse(held.finished)
        held.next(true);assertTrue(held.finished)
    }
    @Test fun layeredVoiceKeepsIncomingVelocityInChildScript(){
        val layer=sound().withModeScript("on(touch) { play(velocity:100); return(true); }")
        val voice=SampledInstrument.combine(listOf(layer,sound())).voice(60,scriptVelocity=50)
        voice.next(false)
        assertEquals(.75,voice.left,1e-12)
    }
    @Test fun scriptVelocityOverridesWithoutMultiplyingInputTwice(){
        val source=sound().withModeScript("on(touch) { play(velocity:velocity); return(true); }")
        val preserved=source.voice(60,scriptVelocity=50);preserved.next(false)
        assertEquals(.25,preserved.left,1e-12)
        val overridden=sound().withModeScript("on(touch) { play(velocity:100); return(true); }").voice(60,scriptVelocity=50)
        overridden.next(false);assertEquals(.5,overridden.left,1e-12)
    }
    @Test fun scriptPitchMoveSurvivesExternalPitchBend(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
        val voice=source.withModeScript("on(touch) { play(pitchmove:1000); return(true); }").voice(60)
        voice.pitchBend(12.0)
        voice.next(false);voice.next(false)
        assertEquals(400.0/32768,voice.left,1e-12)
    }
    @Test fun scriptKeyMoveChangesPlaybackSpeedAndPanChangesStereo(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
        val voice=source.withModeScript("on(touch) { play(key:key,keymove:12,pan:100); return(true); }").voice(60)
        voice.next(false);voice.next(false)
        assertEquals(0.0,voice.left,0.0)
        assertEquals(200.0/32768,voice.right,1e-12)
    }
    private fun original(path:String)=File(requireNotNull(System.getProperty("orgx.referenceScripts")),path).readText()
    @Test fun bundledOctaveScriptsRenderTheirDeclaredMix(){
        val upper=sound().withModeScript(original("Octave/Add Upper Octave.Script")).voice(60)
        upper.next(false)
        // No return(true): original key and script octave both contribute.
        assertEquals(.5,upper.left,1e-12)
        val both=sound().withModeScript(original("Octave/Add Upper & Lower Octave.Script")).voice(60)
        both.next(false)
        assertEquals(.375,both.left,1e-12)
        both.next(true);assertTrue(both.finished)
    }
    @Test fun bundledRelativeLegatoScriptsProduceOppositePitchMovement(){
        fun output(path:String):Double {
            val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
            val voice=source.withModeScript(original(path)).voice(60)
            voice.next(false);voice.next(false)
            return voice.left
        }
        assertTrue(output("Legato/Legato from Lower.Script")<100.0/32768)
        assertTrue(output("Legato/Legato from Upper.Script")>100.0/32768)
    }
    @Test fun lowerLegatoScriptStartsSampleAtPitchOfLowerKey(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(32){(it*100).toShort()}),0,32)),1000)
        val script=source.withModeScript("on(touch) { play(connectkey:key-12,connectspeed:50); return(true); }").voice(60)
        script.next(false);script.next(false)
        assertTrue(script.left>0.0)
        assertTrue(script.left<100.0/32768)
    }
    private fun sound()=SampledInstrument(listOf(InstrumentRegion(60,SamplePcm(1000,1,ShortArray(16){8192}),0,16)),1000)
    @Test fun directVoiceUsesScriptVolumeAndSuppressesDefault(){
        val voice=sound().withModeScript("on(touch) { play(volume:50); return(true); }").voice(60)
        voice.next(false)
        assertEquals(.125,voice.left,1e-12)
        voice.next(true)
        assertTrue(voice.finished)
    }
    @Test fun handledEventWithoutPlayIsSilent(){
        val voice=sound().withModeScript("on(touch) { return(true); }").voice(60)
        voice.next(false)
        assertEquals(0.0,voice.left,0.0);assertTrue(voice.finished)
    }
    @Test fun unsupportedScriptKeepsDefaultVoice(){
        val voice=sound().withModeScript("on(touch) { timer(10) { play(); } }").voice(60)
        voice.next(false)
        assertEquals(.25,voice.left,1e-12)
    }
}
