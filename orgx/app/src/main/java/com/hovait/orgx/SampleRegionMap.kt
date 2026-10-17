package com.hovait.orgx

import kotlin.math.abs

object SampleRegionMap {
    /** OpenKey PitchMode 1 repeats the stored range; gaps stay silent. */
    fun repeatedRoot(note:Int,roots:List<Int>):Int? {
        require(note in 0..127)
        if(roots.isEmpty())return null
        val width=roots.max()-roots.min()+1
        var key=note
        repeat(128/width+1){
            if(key in roots)return key
            key+=width
            if(key>=128)key%=width
        }
        return null
    }
    fun nearestRoot(note:Int,roots:List<Int>):Int {
        require(note in 0..127 && roots.isNotEmpty())
        return roots.minWith(compareBy<Int> { abs(it-note) }.thenBy { it })
    }
}
