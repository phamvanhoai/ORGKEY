package com.hovait.orgx

/** Tracks.ChordFind: richest contained chord, first table entry wins ties. */
object ReferenceChordMatcher {
    fun find(mask:Int,library:List<PlayableChord>):Int? {
        val pitches=mask and 4095
        if(Integer.bitCount(pitches)<3)return null
        var result:Int?=null
        var best=0
        library.forEachIndexed{index,chord->
            val candidate=chord.notes.fold(0){bits,note->bits or (1 shl Math.floorMod(note,12))}
            val size=Integer.bitCount(candidate)
            if(size>=3 && size>best && (candidate and pitches)==candidate){
                result=index;best=size
            }
        }
        return result
    }
}
