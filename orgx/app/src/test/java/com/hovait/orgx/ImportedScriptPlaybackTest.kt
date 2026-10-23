package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class ImportedScriptPlaybackTest {
    @Test fun handledReleaseKeepsScriptVoiceWhileFalseUsesDefaultRelease(){
        val held=sound().withModeScript("on(touch) { play(); return(true); } on(release) { return(true); }").voice(60)
        val normal=sound().withModeScript("on(touch) { play(); return(true); } on(release) { return(false); }").voice(60)
        held.next(false);normal.next(false)
        repeat(20){held.next(true);normal.next(true)}
        assertFalse(held.finished);assertEquals(.25,held.left,1e-12)
        assertTrue(normal.finished);assertEquals(0.0,normal.left,0.0)
    }
    @Test fun mainReadsStoredEffectBeforeUpdatingIt(){
        val stored=ImportedExpressionEffect(1,true,0,false,false,0,65,0,0,emptyList())
        val source=sound().withExpressionEffects(listOf(stored))
            .withModeScript("main() { effect[1].Speed=effect[1].Speed+10; }")
        assertEquals(75,source.keyConnectionEffect?.speed)
    }
    @Test fun mainEffectAssignmentsOverrideStoredSetValuesInEitherLoaderOrder(){
        val script="main() { effect[1].enable=true; effect[1].Speed=75; }"
        val stored=listOf(ImportedExpressionEffect(1,false,0,false,false,0,10,0,0,emptyList()))
        val before=sound().withExpressionEffects(stored).withModeScript(script)
        val after=sound().withModeScript(script).withExpressionEffects(stored)
        assertEquals(75,before.keyConnectionEffect?.speed)
        assertEquals(before.keyConnectionEffect,after.keyConnectionEffect)
    }
    @Test fun aftertouchSoundReceivesHeldPedalAndPedalOffExactlyOnce(){
        val alternate=sound().withModeScript("main() { gain=25; } on(pedalon) { gain=gain+25; } on(pedaloff) { gain=gain-25; } on(touch) { play(volume:gain); return(true); }")
        val primary=sound().withAftertouch(alternate).voice(60)
        primary.scriptPedal(true);primary.aftertouch(1f)
        val held=alternate.voice(60);held.next(false);assertEquals(.125,held.left,1e-12)
        primary.scriptPedal(false);primary.scriptPedal(false)
        val released=alternate.voice(60);released.next(false);assertEquals(.0625,released.left,1e-12)
    }
    @Test fun aftertouchSoundKeepsModulationAndReceivesLaterUpdates(){
        val alternate=sound().withModeScript("main() { gain=100; } on(modulate) { gain=level; } on(touch) { play(volume:gain); return(true); }")
        val primary=sound().withAftertouch(alternate).voice(60)
        primary.modulate(50);primary.aftertouch(1f)
        val first=alternate.voice(60);first.next(false);assertEquals(.125,first.left,1e-12)
        primary.modulate(75)
        val second=alternate.voice(60);second.next(false);assertEquals(.1875,second.left,1e-12)
        primary.modulate(0)
        val reset=alternate.voice(60);reset.next(false);assertEquals(0.0,reset.left,0.0)
    }
    @Test fun aftertouchAlternateReceivesHeldRawPitchContext(){
        val alternate=sound().withModeScript("main() { gain=100; } on(pitchbend) { gain=level; return(true); } on(touch) { play(volume:gain); return(true); }")
        val primary=sound().withAftertouch(alternate).voice(60)
        primary.pitchBend(12.0,50)
        primary.aftertouch(1f)
        val observed=alternate.voice(60);observed.next(false)
        assertEquals(.125,observed.left,1e-12)
    }
    @Test fun stsNewNoteReceivesLatchedRawBendOnItsChannel(){
        // Use raw level in an event whose value can be observed on the next touch.
        val actual=sound().withModeScript("main() { gain=100; } on(pitchbend) { gain=level; return(true); } on(touch) { play(volume:gain); return(true); }")
        val parts=ImportedKeyboardParts()
        parts.replace(listOf(ImportedKeyboardParts.Part(1,actual)))
        parts.pitchBend(12.0,3,50);parts.noteOn(60,1.0,3)
        val next=actual.voice(60);next.next(false)
        assertEquals(.125,next.left,1e-12)
        parts.reset()
    }
    @Test fun rawMidiBendLevelIsIndependentOfSemitoneRange(){
        val source=sound().withModeScript("main() { gain=0; } on(pitchbend) { gain=level; return(true); } on(touch) { play(volume:gain); return(true); }")
        val first=source.voice(60);first.pitchBend(12.0,scriptLevel=50)
        val next=source.voice(60);next.next(false)
        assertEquals(.125,next.left,1e-12)
    }
    @Test fun handledPitchBendSuppressesDefaultPitchChange(){
        fun source()=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(64){(it*100).toShort()}),0,64)),1000)
        val handled=source().withModeScript("on(pitchbend) { return(true); }").voice(60)
        val fallback=source().withModeScript("on(pitchbend) { return(false); }").voice(60)
        handled.pitchBend(12.0);fallback.pitchBend(12.0)
        handled.next(false);handled.next(false);fallback.next(false);fallback.next(false)
        assertEquals(100.0/32768,handled.left,1e-12)
        assertEquals(200.0/32768,fallback.left,1e-12)
    }
    @Test fun excessiveScriptNestingFallsBackWithoutStackOverflow(){
        val source="on(touch) { return("+"(".repeat(200)+"1"+")".repeat(200)+"); }"
        val voice=sound().withModeScript(source).voice(60)
        voice.next(false);assertEquals(.25,voice.left,1e-12)
    }
    @Test fun silentModulationIsNotReexecutedForEveryVoice(){
        val source=sound().withModeScript("main() { count=0; } on(modulate) { count=count+25; } on(touch) { play(volume:count); return(true); }")
        source.updateScriptModulation(80)
        val first=source.voice(60);first.modulate(80)
        val second=source.voice(64);second.modulate(80)
        val check=source.voice(60);check.next(false)
        assertEquals(.0625,check.left,1e-12)
        source.updateScriptModulation(0)
        val reset=source.voice(60);reset.next(false);assertEquals(.125,reset.left,1e-12)
    }
    @Test fun silentPedalTransitionsUpdateSessionBeforeNextTouch(){
        val source=sound().withModeScript("main() { level=0; } on(pedalon) { level=level+25; } on(pedaloff) { level=level+10; } on(touch) { play(volume:level); return(true); }")
        val parts=ImportedKeyboardParts()
        parts.replace(listOf(ImportedKeyboardParts.Part(1,source)))
        parts.pedal(1,true);parts.pedal(1,false);parts.pedal(1,true)
        val voice=source.voice(60);voice.next(false)
        assertEquals(.15,voice.left,1e-12)
    }
    @Test fun noteOpenedUnderHeldPedalReceivesPedalScriptEffects(){
        val source=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(4000){(it%100*200).toShort()}),0,4000)),1000)
            .withModeScript("on(pedalon) { effect[2].enable=true; effect[2].Depth=100; effect[2].Speed=25; }")
        val parts=ImportedKeyboardParts();val mixer=ImportedKeyboardMixer()
        try{
            parts.replace(listOf(ImportedKeyboardParts.Part(1,source,pedal=true)))
            parts.noteOn(60,1.0)
            var difference=0.0
            repeat(500){i->mixer.beginFrame();parts.renderInto(mixer);mixer.finishFrame()
                difference+=kotlin.math.abs(mixer.left-(i%100*200)/32768.0)}
            assertTrue(difference>0.0)
        }finally{mixer.close()}
    }
    @Test fun sharedSessionRunsPedalAssignmentOnceAcrossSimultaneousVoices(){
        val source=sound().withModeScript("main() { level=25; } on(touch) { play(volume:level); return(true); } on(pedalon) { level=level+25; } on(pedaloff) { level=level-25; }")
        val first=source.voice(60);val second=source.voice(64)
        first.scriptPedal(true);second.scriptPedal(true)
        val held=source.voice(60);held.next(false);assertEquals(.125,held.left,1e-12)
        first.scriptPedal(false);second.scriptPedal(false)
        val released=source.voice(60);released.next(false);assertEquals(.0625,released.left,1e-12)
    }
    @Test fun pedalScriptDispatchesOnlyOnTransitions(){
        val source=sound().withModeScript("main() { level=25; } on(touch) { play(volume:level); return(true); } on(pedalon) { level=level+25; } on(pedaloff) { level=25; }")
        val voice=source.voice(60)
        voice.scriptPedal(true);voice.scriptPedal(true)
        val next=source.voice(60);next.next(false);assertEquals(.125,next.left,1e-12)
        voice.scriptPedal(false)
        val reset=source.voice(60);reset.next(false);assertEquals(.0625,reset.left,1e-12)
    }
    @Test fun originalModulationShakeRunsBothSignedBranches(){
        fun render(level:Int):Double {
            val source=SampledInstrument(listOf(InstrumentRegion(60,
                SamplePcm(1000,1,ShortArray(4000){(it%100*200).toShort()}),0,4000)),1000)
                .withModeScript(original("Shake/Modulation Shake.Script"))
            val voice=source.voice(60);voice.modulate(level)
            var sum=0.0;repeat(500){voice.next(false);sum+=voice.left}
            return sum
        }
        assertNotEquals(render(0),render(100),1e-9)
        assertNotEquals(render(100),render(-100),1e-9)
    }
    @Test fun effectReadsUseSetValuesRegardlessOfLoaderOrder(){
        val effect=ImportedExpressionEffect(2,false,0,false,false,0,25,20,0,emptyList())
        val script="on(touch) { play(volume:effect[2].Depth); return(true); }"
        val before=sound().withExpressionEffects(listOf(effect)).withModeScript(script).voice(60)
        val after=sound().withModeScript(script).withExpressionEffects(listOf(effect)).voice(60)
        before.next(false);after.next(false)
        assertEquals(.05,before.left,1e-12);assertEquals(before.left,after.left,1e-12)
    }
    @Test fun scriptedPitchShakeWaitsForPedalAndStartDelay(){
        fun source()=SampledInstrument(listOf(InstrumentRegion(60,
            SamplePcm(1000,1,ShortArray(4000){(it%100*200).toShort()}),0,4000)),1000)
        val voice=source().withModeScript("on(touch) { effect[2].enable=true; effect[2].Depth=100; effect[2].Speed=25; effect[2].StartDelay=100; effect[2].ByPedal=true; effect[2].StopByRelease=true; }").voice(60)
        val plain=source().voice(60)
        repeat(150){voice.next(false,false);plain.next(false);assertEquals(plain.left,voice.left,1e-12)}
        var difference=0.0
        repeat(300){voice.next(false,true);plain.next(false);difference+=kotlin.math.abs(plain.left-voice.left)}
        assertTrue(difference>0.0)
    }
    @Test fun releaseEffectChangePersistsForNextVoiceWithoutDroppingRelease(){
        val source=sound().withModeScript("on(touch) { play(); return(true); } on(release) { effect[2].enable=true; effect[2].Depth=100; effect[2].Speed=25; }")
        val voice=source.voice(60);voice.next(false);voice.next(true)
        assertTrue(voice.finished)
        // The event remains valid and its state can be used on future touches.
        val next=source.voice(60);repeat(30){next.next(false)}
        assertTrue(next.left.isFinite());assertFalse(next.finished)
    }
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
