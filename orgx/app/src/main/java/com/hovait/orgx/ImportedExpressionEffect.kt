package com.hovait.orgx

import java.nio.ByteBuffer
import java.nio.ByteOrder

/** Fixed Effect[8] records are distinct from the variable DSP FX module chain. */
data class ImportedExpressionEffect(val slot:Int,val enabled:Boolean,val startDelay:Int,
    val byPedal:Boolean,val stopByRelease:Boolean,val mode:Int,val speed:Int,val depth:Int,val gain:Int,
    val raw:List<Int>) {
    /** Effect.getSpeed(): mode overrides playback without destroying saved Speed. */
    val playbackSpeed:Int get()=if(mode==1 || mode==2)0 else speed
    companion object {
        fun default(slot:Int):ImportedExpressionEffect {
            require(slot in 0..7)
            val delay=when(slot){0->60;2->100;3->120;else->0}
            val speed=when(slot){0->70;1->250;2->20;3->30;else->0}
            val depth=when(slot){1->120;2,3->50;else->0}
            return ImportedExpressionEffect(slot,false,delay,false,slot==1,0,speed,depth,if(slot==3)120 else 0,emptyList())
        }
        fun decode(slot:Int,bytes:ByteArray):ImportedExpressionEffect {
            require(slot in 0..7 && bytes.size==40){"Invalid expression Effect record"}
            val reader=ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)
            fun flag(offset:Int)=bytes[offset]!=0.toByte()
            return ImportedExpressionEffect(slot,flag(0),reader.getInt(20),flag(24),flag(25),
                bytes[26].toInt() and 255,reader.getInt(28),reader.getInt(32),reader.getInt(36),
                bytes.map{it.toInt() and 255})
        }
    }
}
