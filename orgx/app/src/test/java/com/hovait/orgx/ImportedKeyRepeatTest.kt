package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedKeyRepeatTest {
    @Test fun heldNoteCanEnableDisableAndResumeRepeatWithoutResettingItsCounter(){
        val repeat=ImportedKeyRepeat(1000)
        repeat(100){assertFalse(repeat.next(false,false))}
        repeat.configure(effect(speed=500))
        val first=List(20){repeat.next(false,false)}
        assertEquals(listOf(19),first.indices.filter{first[it]})
        repeat.configure(null)
        repeat(40){assertFalse(repeat.next(false,false))}
        repeat.configure(effect(speed=500))
        val resumed=List(20){repeat.next(false,false)}
        assertEquals(listOf(19),resumed.indices.filter{resumed[it]})
    }
    private fun effect(delay:Int=0,pedal:Boolean=false,stop:Boolean=false,speed:Int=100)=
        ImportedExpressionEffect(0,true,delay,pedal,stop,0,speed,0,0,emptyList())
    @Test fun usesOriginalTenMillisecondDelayAndPeriodFormula(){
        val repeat=ImportedKeyRepeat(effect(delay=20,speed=100),1000)
        val fired=ArrayList<Int>()
        repeat(80){frame->if(repeat.next(false,false))fired.add(frame)}
        assertEquals(listOf(79),fired)
    }
    @Test fun pedalPausesDelayAndReleasePreventsFurtherRepeats(){
        val repeat=ImportedKeyRepeat(effect(pedal=true,speed=500),1000)
        repeat(20){assertFalse(repeat.next(false,false))}
        var fired=false
        repeat(20){fired = repeat.next(false,true) || fired}
        assertTrue(fired)
        repeat(100){assertFalse(repeat.next(true,true))}
    }
    @Test fun zeroSpeedDoesNotDivideByZeroOrRepeat(){
        val repeat=ImportedKeyRepeat(effect(speed=0),1000)
        repeat(1000){assertFalse(repeat.next(false,false))}
    }
}
