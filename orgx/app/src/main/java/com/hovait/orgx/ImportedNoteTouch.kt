package com.hovait.orgx

/** Channels.OpenKey splits packed Touch into unsigned velocity and signed per-note pan. */
object ImportedNoteTouch {
    fun velocity(touch:Int)=(touch and 65535)/100.0
    fun pan(touch:Int)=touch shr 16
    fun gain(touch:Int,channel:Int):Double {
        val pan=pan(touch)
        return minOf(100,if(channel==0)100-pan else 100+pan)/100.0
    }
}
