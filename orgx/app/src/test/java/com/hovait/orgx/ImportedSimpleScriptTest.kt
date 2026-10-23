package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedSimpleScriptTest {
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
    @Test(expected=IllegalArgumentException::class) fun timerScriptCannotBeMistakenForAnExecutedEvent(){
        ImportedSimpleScript.evaluate(ImportedModeScript.parse("on(touch) { play(); timer(30) { play(); } }"),"touch")
    }
}
