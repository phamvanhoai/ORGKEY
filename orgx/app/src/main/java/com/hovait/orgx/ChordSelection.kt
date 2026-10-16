package com.hovait.orgx

object ChordSelection {
    private val legacy = listOf("C M", "D m", "E m", "F M", "G M", "A m", "B dim", "C 7th", "D 7th", "E 7th", "F M7", "G 7th")

    fun resolve(names: List<String>, savedName: String?, savedIndex: Int): Int {
        if (names.isEmpty()) return 0
        val name = savedName ?: legacy.getOrNull(savedIndex)
        val match = names.indexOf(name)
        return if (match >= 0) match else savedIndex.coerceIn(0, names.lastIndex)
    }
}
