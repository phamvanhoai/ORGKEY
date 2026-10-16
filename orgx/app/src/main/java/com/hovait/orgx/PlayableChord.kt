package com.hovait.orgx

data class PlayableChord(val name: String, val notes: List<Int>)

object PlayableChordLibrary {
    fun fromTable(text: String): List<PlayableChord> {
        val roots = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
        return ChordTableParser.parse(text).flatMap { (quality, masks) ->
            roots.mapIndexed { root, name ->
                val notes = (0..11).filter { offset ->
                    masks[root] and (1 shl ((root + offset) % 12)) != 0
                }.map { 48 + root + it }
                PlayableChord("$name $quality", notes)
            }
        }
    }
}
