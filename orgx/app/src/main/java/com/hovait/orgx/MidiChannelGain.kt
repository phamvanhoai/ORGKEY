package com.hovait.orgx

object MidiChannelGain {
    const val DEFAULT_VOLUME=100
    const val DEFAULT_EXPRESSION=127
    fun factor(volume:Int,expression:Int):Double = volume.coerceIn(0,127)/127.0 * (expression.coerceIn(0,127)/127.0)
}
