package com.hovait.orgx

import java.io.File

object ImportedSoundSelection {
    fun resolve(directory:File,fileName:String,soundName:String):Pair<File,ImportedInstrument> {
        require(Regex("[0-9a-f]{64}\\.orgset").matches(fileName)){"Invalid saved SET reference"}
        val file=File(directory,fileName)
        require(file.isFile){"Saved SET file is missing"}
        val sounds=ImportedInstrumentLoader.playableSounds(ImportedSetInstruments.read(file))
        val matches=sounds.filter{it.name==soundName}
        require(matches.size==1){"Saved SET sound is missing or ambiguous"}
        return file to matches.single()
    }
}
