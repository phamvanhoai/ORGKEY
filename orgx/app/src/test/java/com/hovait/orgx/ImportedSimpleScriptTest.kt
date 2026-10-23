package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedSimpleScriptTest {
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
