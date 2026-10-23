package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedSimpleScriptTest {
    @Test fun octaveSwitchSupportsOpenRangesAndDottedVariables(){
        val session=ImportedScriptSession("""
            main(){touch.octave=0;}
            on(touch){
                switch(velocity){
                    case 10..:touch.octave=-1;break;
                    case 0:touch.octave=0;break;
                    case ..-10:touch.octave=1;break;
                }
                play(octavemove:touch.octave);return(true);
            }
        """.trimIndent())
        for((level,expected) in listOf(10 to -1,9 to -1,0 to 0,-9 to 0,-10 to 1,127 to -1))
            assertEquals(expected,session.event("touch",mapOf("velocity" to level)).plays.single().parameters["octavemove"])
    }
    @Test fun switchBreakDoesNotStopSurroundingTimerAndSupportsFallthrough(){
        val script=ImportedModeScript.parse("""
            on(__timer){
                switch(key){case 1,2:play(volume:25);case 3:play(volume:50);break;default:play(volume:100);}
                play(volume:75);
            }
        """.trimIndent())
        val selected=ImportedSimpleScript.evaluate(script,"__timer",mapOf("key" to 2))
        assertEquals(listOf(25,50,75),selected.plays.map{it.parameters["volume"]})
        assertFalse(selected.timerStopped)
        val other=ImportedSimpleScript.evaluate(script,"__timer",mapOf("key" to 9))
        assertEquals(listOf(100,75),other.plays.map{it.parameters["volume"]})
    }
    @Test fun effectSetterNormalizesValuesBeforeSubsequentReads(){
        val session=ImportedScriptSession("on(touch) { effect[1].Mode=255; effect[2].Enable=-4; play(volume:effect[1].Mode+2,velocity:effect[2].Enable); }")
        val result=session.event("touch")
        assertEquals(-1,result.effects[0].value)
        assertEquals(1,result.effects[1].value)
        assertEquals(1,result.plays.single().parameters["volume"])
        assertEquals(1,result.plays.single().parameters["velocity"])
    }
    @Test fun scriptEffectSpeedReadUsesModeOverrideWithoutDestroyingStoredSpeed(){
        val session=ImportedScriptSession("on(touch) { effect[2].Mode=1; play(volume:effect[2].Speed); effect[2].Mode=0; play(volume:effect[2].Speed); }")
        session.synchronizeEffects(listOf(ImportedExpressionEffect(2,true,0,false,false,0,75,50,0,emptyList())))
        assertEquals(listOf(0,75),session.event("touch").plays.map{it.parameters["volume"]})
    }
    @Test fun repeatedModulationDoesNotRestoreEffectValueFromBeforeAftertouch(){
        val session=ImportedScriptSession("on(modulate) { effect[2].Depth=25; } on(aftertouch) { effect[2].Depth=velocity; }")
        assertEquals(25,session.modulation(80).effects.single().value)
        session.event("aftertouch",mapOf("velocity" to 100))
        assertEquals(100,session.modulation(80).effects.single().value)
    }
    @Test fun effectReadsSeeEarlierAssignmentAndPreviousEvent(){
        val session=ImportedScriptSession("on(touch) { effect[2].Depth = effect[2].Depth + 10; play(volume:effect[2].Depth); }")
        session.synchronizeEffects(listOf(ImportedExpressionEffect(2,true,0,false,false,0,25,20,0,emptyList())))
        assertEquals(30,session.event("touch").plays.single().parameters["volume"])
        assertEquals(40,session.event("touch").plays.single().parameters["volume"])
    }
    @Test fun originalDynamicLegatoVelocityProducesEffectAndPlayPlan(){
        val source=java.io.File(requireNotNull(System.getProperty("orgx.referenceScripts")),"Legato/Dynamic Legato (Velocity).Script").readText()
        val session=ImportedScriptSession(source)
        val first=session.event("touch",mapOf("key" to 60,"velocity" to 110))
        assertEquals(ImportedSimpleScript.EffectChange(1,"enable",1),first.effects.single())
        assertTrue(first.plays.isEmpty())
        val second=session.event("touch",mapOf("key" to 64,"velocity" to 80))
        assertTrue(second.handled)
        assertEquals(60,second.plays.single().parameters["connectkey"])
        assertEquals(80,second.plays.single().parameters["connectspeed"])
    }
    @Test fun sessionTimeMeasuresMillisecondsBetweenTouches(){
        var now=1_000_000_000_000L
        val session=ImportedScriptSession("main() { previous = 0; } on(touch) { play(connectspeed:time-previous); previous = time; }"){now}
        now+=120
        assertEquals(120,session.event("touch").plays.single().parameters["connectspeed"])
        now+=75
        assertEquals(75,session.event("touch").plays.single().parameters["connectspeed"])
    }
    @Test fun dynamicLegatoSpeedFormulaUsesArithmeticPrecedence(){
        val script=ImportedModeScript.parse("on(touch) { play(connectspeed:10000 / (time - touch_time) + 10,volume:2+3*4); }")
        val result=ImportedSimpleScript.evaluate(script,"touch",mapOf("time" to 300,"touch_time" to 100))
        assertEquals(60,result.plays.single().parameters["connectspeed"])
        assertEquals(14,result.plays.single().parameters["volume"])
    }
    @Test(expected=IllegalArgumentException::class) fun zeroTimeIntervalRejectsDivision(){
        ImportedSimpleScript.evaluate(ImportedModeScript.parse("on(touch) { play(connectspeed:10000/(time-time)); }"),"touch",mapOf("time" to 10))
    }
    @Test fun groupedComparisonsAndNegationRespectParentheses(){
        fun handled(condition:String)=ImportedSimpleScript.evaluate(ImportedModeScript.parse("on(touch) { return($condition); }"),"touch",mapOf("key" to 64)).handled
        assertTrue(handled("(key > 60) == true"))
        assertTrue(handled("(key > 60) == (key < 70)"))
        assertFalse(handled("!(key > 60)"))
        assertTrue(handled("Max(1, key > 60) == 1"))
    }
    @Test fun commentedSliderDoesNotInitializeVariables(){
        val session=ImportedScriptSession("// setting(slider, name:\"move\", title:\"Move\", reset:-2, from:-12, to:12);\nmain() { count=0; }")
        assertTrue(session.sliders.isEmpty())
    }
    @Test fun originalLegatoSliderInitializesConnectionOffset(){
        val source="main() { setting(slider, name:\"legato_move\", title:\"Legato from\", reset:-2, from:-12, to:12); } on(touch) { play(connectkey:key+legato_move,connectspeed:50); return(true); }"
        val session=ImportedScriptSession(source)
        assertEquals(-2,session.sliders.single().reset)
        assertEquals(58,session.event("touch",mapOf("key" to 60)).plays.single().parameters["connectkey"])
    }
    @Test fun lastKeyDistanceExpressionSelectsLegatoOnlyWithinSixSemitones(){
        val script=ImportedModeScript.parse("on(touch) { if (abs(last_key - key) <= 6) { play(connectkey:last_key,connectspeed:75); return(true); } }")
        assertEquals(60,ImportedSimpleScript.evaluate(script,"touch",mapOf("last_key" to 60,"key" to 65)).plays.single().parameters["connectkey"])
        assertTrue(ImportedSimpleScript.evaluate(script,"touch",mapOf("last_key" to 60,"key" to 67)).plays.isEmpty())
        assertEquals(65,ImportedSimpleScript.evaluate(script,"touch",mapOf("last_key" to 65,"key" to 60)).plays.single().parameters["connectkey"])
    }
    @Test fun ifElseRunsOnlySelectedBranch(){
        val script=ImportedModeScript.parse("on(touch) { if (velocity < 100) { level = 25; } else { level = 75; } play(volume:level); return(true); }")
        assertEquals(25,ImportedSimpleScript.evaluate(script,"touch",mapOf("velocity" to 50)).plays.single().parameters["volume"])
        assertEquals(75,ImportedSimpleScript.evaluate(script,"touch",mapOf("velocity" to 110)).plays.single().parameters["volume"])
    }
    @Test fun nestedIfBlocksRunInOrderAndReturnStopsOuterEvent(){
        val script=ImportedModeScript.parse("on(touch) { if (velocity < 100) { value = 20; if (value == 20) { play(volume:value); return(true); } } play(volume:100); }")
        val low=ImportedSimpleScript.evaluate(script,"touch",mapOf("velocity" to 50))
        assertTrue(low.handled);assertEquals(listOf(20),low.plays.map{it.parameters["volume"]})
        val high=ImportedSimpleScript.evaluate(script,"touch",mapOf("velocity" to 110))
        assertFalse(high.handled);assertEquals(listOf(100),high.plays.map{it.parameters["volume"]})
    }
    @Test fun returnExpressionTreatsEveryNonzeroResultAsHandled(){
        val condition=ImportedModeScript.parse("on(aftertouch) { return(velocity > 0); }")
        assertTrue(ImportedSimpleScript.evaluate(condition,"aftertouch",mapOf("velocity" to 60)).handled)
        assertFalse(ImportedSimpleScript.evaluate(condition,"aftertouch",mapOf("velocity" to 0)).handled)
        assertTrue(ImportedSimpleScript.evaluate(ImportedModeScript.parse("on(touch) { return(-2); }"),"touch").handled)
    }
    @Test fun dynamicLegatoCompoundConditionUsesStoredKeyAndVelocity(){
        val script=ImportedModeScript.parse("on(touch) { if (touch_old != -1 && velocity < 100) play(connectkey:touch_old,connectspeed:Max(5,velocity)); }")
        assertTrue(ImportedSimpleScript.evaluate(script,"touch",mapOf("touch_old" to -1,"velocity" to 50)).plays.isEmpty())
        assertEquals(60,ImportedSimpleScript.evaluate(script,"touch",mapOf("touch_old" to 60,"velocity" to 50)).plays.single().parameters["connectkey"])
    }
    @Test fun booleanOperatorsShortCircuitUnknownVariables(){
        val script=ImportedModeScript.parse("on(touch) { if (true || unknown) play(); if (false && unknown) play(); if (!false) play(); }")
        assertEquals(2,ImportedSimpleScript.evaluate(script,"touch").plays.size)
    }
    @Test fun conditionalPlayUsesEventVelocityAndSkipsFalseBranch(){
        val script=ImportedModeScript.parse("on(touch) { if (velocity < 100) play(connectkey:key-2,connectspeed:50); return(true); }")
        assertEquals(1,ImportedSimpleScript.evaluate(script,"touch",mapOf("key" to 60,"velocity" to 80)).plays.size)
        assertTrue(ImportedSimpleScript.evaluate(script,"touch",mapOf("key" to 60,"velocity" to 110)).plays.isEmpty())
    }
    @Test fun conditionalAssignmentDoesNotChangeStateWhenFalse(){
        val session=ImportedScriptSession("main() { count = 1; } on(touch) { if (velocity > 100) count = count + 1; play(volume:count); }")
        assertEquals(1,session.event("touch",mapOf("velocity" to 80)).plays.single().parameters["volume"])
        assertEquals(2,session.event("touch",mapOf("velocity" to 110)).plays.single().parameters["volume"])
    }
    @Test fun rejectedAudioPlanDoesNotAdvanceSessionState(){
        val session=ImportedScriptSession("main() { count = 0; } on(touch) { count = count + 1; play(volume:count); }")
        try{session.event("touch"){throw IllegalArgumentException("Cannot play")};fail("Accepted rejected plan")}
        catch(expected:IllegalArgumentException){}
        assertEquals(1,session.event("touch").plays.single().parameters["volume"])
    }
    @Test fun mainInitializesOnceAndSessionsHaveIndependentState(){
        val source="main() { count = 0; } on(touch) { count = count + 1; play(volume:count); return(true); }"
        val first=ImportedScriptSession(source);val second=ImportedScriptSession(source)
        assertEquals(1,first.event("touch").plays.single().parameters["volume"])
        assertEquals(2,first.event("touch").plays.single().parameters["volume"])
        assertEquals(1,second.event("touch").plays.single().parameters["volume"])
    }
    @Test fun scriptStatePersistsAcrossEventsButFailedEventDoesNotCommit(){
        val state=mutableMapOf("touch_count" to 0)
        val script=ImportedModeScript.parse("on(touch) { touch_count = touch_count + 1; play(volume:touch_count); return(true); }")
        assertEquals(1,ImportedSimpleScript.evaluate(script,"touch",state=state).plays.single().parameters["volume"])
        assertEquals(2,ImportedSimpleScript.evaluate(script,"touch",state=state).plays.single().parameters["volume"])
        try{
            ImportedSimpleScript.evaluate(ImportedModeScript.parse("on(touch) { touch_count = 99; unsupported(); }"),"touch",state=state)
            fail("Unsupported statement accepted")
        }catch(expected:IllegalArgumentException){}
        assertEquals(2,state["touch_count"])
    }
    @Test fun originalMoveAutoReleaseExpressionResolvesBoolean(){
        val script=ImportedModeScript.parse("on(touch) { play(autorelease:move_key != 0); return(true); }")
        assertEquals(1,ImportedSimpleScript.evaluate(script,"touch",mapOf("move_key" to 6)).plays.single().parameters["autorelease"])
        assertEquals(0,ImportedSimpleScript.evaluate(script,"touch",mapOf("move_key" to 0)).plays.single().parameters["autorelease"])
    }
    @Test fun dynamicLegatoSpeedUsesNestedNamedArgumentExpression(){
        val script=ImportedModeScript.parse("on(touch) { play(connectkey:key-2,connectspeed:Max(5, velocity)); return(true); }")
        val slow=ImportedSimpleScript.evaluate(script,"touch",mapOf("key" to 60,"velocity" to 2))
        val fast=ImportedSimpleScript.evaluate(script,"touch",mapOf("key" to 60,"velocity" to 80))
        assertEquals(5,slow.plays.single().parameters["connectspeed"])
        assertEquals(80,fast.plays.single().parameters["connectspeed"])
    }
    @Test fun originalLowerLegatoResolvesConnectionRelativeToInputKey(){
        val script=ImportedModeScript.parse("on(touch) { play(connectkey:key-2, connectspeed:50); return(true); }")
        val result=ImportedSimpleScript.evaluate(script,"touch",mapOf("key" to 60))
        assertTrue(result.handled)
        assertEquals(58,result.plays.single().parameters["connectkey"])
        assertEquals(50,result.plays.single().parameters["connectspeed"])
    }
    @Test fun additiveOctavesProduceThreePlaysAndSuppressFallback(){
        val script=ImportedModeScript.parse("on(touch) { play(volume:50); play(octavemove:1, volume:50); play(octavemove:-1, volume:50); return(true); }")
        val result=ImportedSimpleScript.evaluate(script,"touch")
        assertTrue(result.handled)
        assertEquals(listOf(null,1,-1),result.plays.map{it.parameters["octavemove"]})
        assertTrue(result.plays.all{it.parameters["volume"]==50})
        assertEquals(ImportedSimpleScript.Result(emptyList(),false),ImportedSimpleScript.evaluate(script,"release"))
    }
    @Test fun timerRegistersBodyWithoutExecutingItDuringTouch(){
        val result=ImportedSimpleScript.evaluate(ImportedModeScript.parse("on(touch) { play(); timer(30) { play(volume:50); } }"),"touch")
        assertEquals(1,result.plays.size)
        assertEquals(30,result.timers.single().interval)
        assertTrue(result.timers.single().body.contains("play(volume:50)"))
    }
}
