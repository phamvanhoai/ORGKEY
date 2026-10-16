package com.hovait.orgx

/** Mirrors the reference Music.ChordTable bit-mask construction. */
object ChordTableParser {
    fun parse(text: String, maxChannels: Int = 256): Map<String, IntArray> {
        require(maxChannels > 0) { "Chord table capacity must be positive" }
        val result = LinkedHashMap<String, IntArray>()
        text.split(Regex("\\r?\\n")).asSequence().map(String::trim)
            .filter(String::isNotEmpty).take(maxChannels).forEach { line ->
                val tokens = line.split(Regex("\\s+"))
                val name = tokens.first()
                require(tokens.size in 2..13) { "Chord $name must contain 1 to 12 intervals" }
                require(name !in result) { "Duplicate chord: $name" }
                val scale = IntArray(12) { i ->
                    val token = tokens.getOrNull(i + 1)
                    if (token == null) -1 else {
                        val interval = token.toIntOrNull() ?: error("Invalid interval for $name: $token")
                        require(interval in -1..11) { "Interval outside pitch-class range for $name: $interval" }
                        interval
                    }
                }
                require(scale.any { it >= 0 }) { "Chord $name contains no playable intervals" }
                result[name] = IntArray(12) { root ->
                    scale.fold(0) { mask, value ->
                        if (value == -1) mask else mask or (1 shl ((value + root) % 12))
                    }
                }
            }
        return result
    }
}
