package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedExpressionModeTest {
    private fun effect(slot:Int,mode:Int)=ImportedExpressionEffect(
        slot,true,0,false,false,mode,125,51,7,emptyList())

    @Test fun storedSpeedIsPreservedButModesOneAndTwoFreezeBothModulators(){
        for(mode in listOf(1,2)){
            val pitch=effect(2,mode)
            assertEquals(125,pitch.speed)
            assertEquals(0,pitch.playbackSpeed)
            val p=ImportedPitchShake(pitch,1000)
            val v=ImportedVolumeShake(effect(3,mode),1000)
            repeat(100){p.next(false,false);v.next(false,false)}
            assertEquals(0.0,p.offset,0.0)
            assertEquals(1.0,v.left,0.0);assertEquals(1.0,v.right,0.0)
        }
        // getSpeed compares the entire mode, not just its low nibble.
        assertEquals(125,effect(2,17).playbackSpeed)
    }

    @Test fun modeSwitchKeepsPhaseAndRestoringModeResumesSavedSpeed(){
        val e=effect(2,0)
        val p=ImportedPitchShake(e,1000)
        repeat(10){p.next(false,false)}
        val first=p.offset
        p.configure(e.copy(mode=1))
        repeat(50){p.next(false,false)}
        assertEquals(first,p.offset,0.0)
        p.configure(e)
        repeat(10){p.next(false,false)}
        assertNotEquals(first,p.offset,0.0)
    }

    @Test fun pitchDriftUsesIntegerDivision(){
        val p=ImportedPitchShake(effect(2,0).copy(speed=1,depth=0,gain=999),1000)
        repeat(10){p.next(false,false)}
        assertEquals(0.0,p.offset,0.0) // 10 * 999 / 10000 is integer zero.
        repeat(10){p.next(false,false)}
        assertEquals(1.0,p.offset,0.0)
    }
}
