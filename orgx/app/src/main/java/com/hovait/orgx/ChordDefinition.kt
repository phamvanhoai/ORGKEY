package com.hovait.orgx

/** Parsed representation of the reference `.Chord` files. Values are kept as
 * authored (the reference format stores one integer per line); conversion to
 * playable MIDI notes remains a separate mapping step. */
data class ChordDefinition(val name: String, val values: List<Int>)

object ChordDefinitionParser {
    fun parse(name: String, bytes: ByteArray): ChordDefinition {
        val text = bytes.toString(Charsets.UTF_8)
        val values = text.lineSequence().map(String::trim).filter(String::isNotEmpty)
            .map { it.toIntOrNull() ?: error("Invalid chord value in $name: $it") }.toList()
        return ChordDefinition(name, values)
    }
}
