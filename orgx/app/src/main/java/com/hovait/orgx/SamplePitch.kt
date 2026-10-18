package com.hovait.orgx

import kotlin.math.pow

object SamplePitch {
    /** Music.KeyToPitchEx: 24-key table applies only to melodic instruments. */
    fun referenceStep(note:Int,root:Int,sourceRate:Int,outputRate:Int,tune:Int,table:List<Double>,transpose:Int=0):Double {
        require(table.size==24 && table.all{it.isFinite()})
        val tableKey=Math.floorMod(note.toLong()-transpose,24L).toInt()
        val semitones=note-root+tune/1000.0+table[tableKey]
        val raw=kotlin.math.exp(kotlin.math.ln(1.059463094)*semitones)*sourceRate/outputRate*8192
        require(raw.isFinite() && raw>=0 && raw<=Int.MAX_VALUE){"Unsupported reference pitch step"}
        return Math.round(raw).coerceAtLeast(1)/8192.0
    }
    /** Equal temperament, no tuning: the fixed-point step observed in OpenKey. */
    fun step(note:Int,root:Int,sourceRate:Int,outputRate:Int,tune:Int=0):Double {
        require(note in 0..127 && root in 0..127 && sourceRate>0 && outputRate>0)
        val raw=2.0.pow((note-root+tune/1000.0)/12.0)*sourceRate/outputRate*8192.0
        require(raw.isFinite() && raw>=0 && raw<=Int.MAX_VALUE){"Unsupported pitch step"}
        return java.lang.Math.round(raw).coerceAtLeast(1)/8192.0
    }
}
