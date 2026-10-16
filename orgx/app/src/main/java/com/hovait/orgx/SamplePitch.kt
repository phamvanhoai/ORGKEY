package com.hovait.orgx

import kotlin.math.pow

object SamplePitch {
    /** Equal temperament, no tuning: the fixed-point step observed in OpenKey. */
    fun step(note:Int,root:Int,sourceRate:Int,outputRate:Int):Double {
        require(note in 0..127 && root in 0..127 && sourceRate>0 && outputRate>0)
        return java.lang.Math.round(2.0.pow((note-root)/12.0)*sourceRate/outputRate*8192.0)/8192.0
    }
}
