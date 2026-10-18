package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedBandMoveTest {
    private fun bytes(value:Int)=List(4){(value ushr (8*it)) and 255}
    private fun settings(from:Int,to:Int,delay:Int=1,loop:Boolean=false)=ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(2,true,false,bytes(from+100)+bytes(to+100)+bytes(delay)+bytes(2000)+listOf(if(loop)1 else 0))))
    private fun equalizer(position:Int)=ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(
        ImportedFxModule(7,true,false,List(8){if(it<4)100-position else 100+position}.flatMap(::bytes)))))
    @Test fun fixedEndpointsMatchNativeBandMapping(){
        val moving=ImportedFxChain(settings(-50,-50));val fixed=equalizer(-50)
        repeat(200){i->val x=if(i==0).5 else 0.0
            moving.process(x,-x);fixed.process(x,-x)
            assertEquals(fixed.left,moving.left,0.0);assertEquals(fixed.right,moving.right,0.0)}
    }
    @Test fun delayRetainsInitialBandsBeforeMotionAndThenChangesResponse(){
        val moving=ImportedFxChain(settings(-100,100));val fixed=equalizer(-100)
        var changed=false
        repeat(1500){i->
            val x=if(i%4<2).5 else -.5
            moving.process(x,x);fixed.process(x,x)
            if(i<48)assertEquals(fixed.left,moving.left,0.0)
            if(kotlin.math.abs(fixed.left-moving.left)>1e-5)changed=true
            assertEquals(moving.left,moving.right,0.0);assertTrue(moving.left.isFinite())
        }
        assertTrue(changed)
    }
    @Test fun resetKeepsLoopMovementAndFilterHistory(){
        val a=ImportedFxChain(settings(-1,1,loop=true));val b=ImportedFxChain(settings(-1,1,loop=true))
        repeat(4000){i->
            if(i==1500)a.reset()
            val x=if(i%2==0).5 else -.5
            a.process(x,0.0);b.process(x,0.0)
            assertEquals(b.left,a.left,0.0);assertTrue(a.left.isFinite())
        }
    }
}
