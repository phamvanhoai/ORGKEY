package com.hovait.orgx

import kotlin.math.abs

object SampleRegionMap {
    fun nearestRoot(note:Int,roots:List<Int>):Int {
        require(note in 0..127 && roots.isNotEmpty())
        return roots.minWith(compareBy<Int> { abs(it-note) }.thenBy { it })
    }
}
