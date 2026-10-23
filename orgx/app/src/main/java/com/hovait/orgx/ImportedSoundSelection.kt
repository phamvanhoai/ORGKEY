package com.hovait.orgx

import java.io.File

object ImportedSoundSelection {
    data class Mode(val file:File,val instrument:ImportedInstrument){
        val name:String get()="${instrument.sourceName} / MODE ${instrument.modes.first().name}"
    }
    /** Only a directly selected voice is persisted here; STS keeps its own restore path. */
    fun changedMode(sound:SampledInstrument?,file:File?,savedFile:String?,savedName:String?):String? {
        if(file==null || savedFile!=file.name || savedName==null)return null
        val mode=sound?.modeSelection ?: return null
        if(mode.file!=file)return null
        return mode.name.takeIf{it!=savedName}
    }
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
