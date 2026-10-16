package com.hovait.orgx

/** Percussion keeps its own gain and is not normalized by melodic polyphony. */
object VoiceMixer {
    fun mix(melodic:Double,melodicCount:Int,percussion:Double,keys:Float,drums:Float):Double =
        melodic/melodicCount.coerceAtLeast(1)*keys+percussion*drums
}
