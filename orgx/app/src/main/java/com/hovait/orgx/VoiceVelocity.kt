package com.hovait.orgx

object VoiceVelocity {
    fun gain(value:Float,playback:Boolean):Float {
        require(value.isFinite()) { "Velocity must be finite" }
        return value.coerceIn(if(playback)1f/127f else 0.15f,1f)
    }
}
