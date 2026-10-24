package com.hovait.orgx

object VoiceVelocity {
    fun gain(value:Float,playback:Boolean,sampled:Boolean=false):Float {
        require(value.isFinite()) { "Velocity must be finite" }
        return value.coerceIn(if(sampled)0f else if(playback)1f/127f else 0.15f,1f)
    }
}
