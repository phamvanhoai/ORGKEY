package com.hovait.orgx

/** ORGX four-bar presets, resolved by pitch-class mask rather than library row order. */
class AutoChordProgression(library:List<PlayableChord>) {
    private val resolved=IntArray(6){builtin->
        val mask=when(builtin){
            0->(1 shl 0) or (1 shl 4) or (1 shl 7)
            3->(1 shl 5) or (1 shl 9) or (1 shl 0)
            4->(1 shl 7) or (1 shl 11) or (1 shl 2)
            5->(1 shl 9) or (1 shl 0) or (1 shl 4)
            else->0
        }
        if(mask==0)-1 else library.indexOfFirst{chord->chord.notes.fold(0){bits,note->bits or (1 shl (note%12))}==mask}
    }
    fun chord(preset:Int,referenceTime:Long):Int?=resolved[builtinChord(preset,referenceTime)].takeIf{it>=0}
    companion object {
        private val presets=arrayOf(intArrayOf(0,5,3,4),intArrayOf(0,4,5,3),intArrayOf(5,3,0,4))
        // Four quarter-note beats at reference tempo 120: 2000 reference milliseconds/bar.
        fun builtinChord(preset:Int,referenceTime:Long):Int = presets[preset.coerceIn(0,2)][((referenceTime.coerceAtLeast(0)/2000)%4).toInt()]
    }
}
