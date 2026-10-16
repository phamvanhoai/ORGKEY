package com.hovait.orgx

/** Mirrors the reference Music.ChordTable bit-mask construction. */
object ChordTableParser {
    fun parse(text: String, maxChannels: Int = 256): Map<String, IntArray> {
        val result = LinkedHashMap<String, IntArray>()
        text.split(Regex("\\r?\\n")).asSequence().map(String::trim)
            .filter(String::isNotEmpty).take(maxChannels).forEach { line ->
                val tokens = line.split(Regex("\\s+"))
                val name = tokens.first()
                val scale = IntArray(12) { i -> tokens.getOrNull(i + 1)?.toIntOrNull() ?: -1 }
                result[name] = IntArray(12) { root ->
                    scale.fold(0) { mask, value ->
                        if (value == -1) mask else mask or (1 shl ((value + root) % 12))
                    }
                }
            }
        return result
    }
}
