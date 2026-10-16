package com.hovait.orgx

import kotlin.math.pow

object SamplePitch {
    /** Equal temperament, no tuning: the fixed-point step observed in OpenKey. */
    fun step(note:Int,root:Int,sourceRate:Int,outputRate:Int,tune:Int=0):Double {
        require(note in 0..127 && root in 0..127 && sourceRate>0 && outputRate>0)
        val raw=2.0.pow((note-root+tune/1000.0)/12.0)*sourceRate/outputRate*8192.0
        require(raw.isFinite() && raw>=0 && raw<=Int.MAX_VALUE){"Unsupported pitch step"}
        return java.lang.Math.round(raw).coerceAtLeast(1)/8192.0
    }
}
