package com.hovait.orgx

import android.content.Context
import java.util.zip.ZipFile

/** Reads the reference template/chord bundle without unpacking it at install time. */
class ReferenceAssetStore(private val context: Context) {
    private val zipFile get() = context.cacheDir.resolve("orgx-reference-data.zip")

    private fun ensureZip(): ZipFile {
        if (!zipFile.exists()) context.assets.open("data.zip").use { input ->
            zipFile.outputStream().use { output -> input.copyTo(output) }
        }
        return ZipFile(zipFile)
    }

    fun chordNames(): List<String> = ensureZip().use { zip ->
        zip.entries().asSequence()
            .filter { !it.isDirectory && it.name.startsWith("Chord/") && it.name.endsWith(".Chord") }
            .map { it.name.removePrefix("Chord/").removeSuffix(".Chord") }
            .sorted()
            .toList()
    }

    fun readChord(name: String): ByteArray? = ensureZip().use { zip ->
        val entry = zip.getEntry("Chord/$name.Chord") ?: return null
        zip.getInputStream(entry).use { it.readBytes() }
    }

    fun readChordDefinition(name: String): ChordDefinition? =
        readChord(name)?.let { ChordDefinitionParser.parse(name, it) }
}
