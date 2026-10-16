package com.hovait.orgx

object VoiceEnvelope {
    fun next(gain:Float,releasing:Boolean,percussion:Boolean,sampleRate:Int):Float {
        require(sampleRate>0)
        return when {
            releasing -> (gain-1f/(sampleRate*0.12f)).coerceAtLeast(0f)
            percussion -> 1f
            else -> (gain+1f/(sampleRate*0.008f)).coerceIn(0f,1f)
        }
    }
}
