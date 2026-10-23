package com.hovait.orgx

object StsMidiRouting {
    fun usesParts(enabled:Boolean,channel:Int,playback:Boolean):Boolean {
        require(channel in 0..15)
        return enabled && !playback
    }
}
