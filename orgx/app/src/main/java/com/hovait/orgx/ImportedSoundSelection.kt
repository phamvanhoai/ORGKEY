package com.hovait.orgx

import java.io.File

object ImportedSoundSelection {
    fun selectedIndex(sounds:List<ImportedInstrument>,name:String?,key:String?):Int {
        val matches=sounds.indices.filter{if(key!=null)key(sounds[it])==key else sounds[it].name==name}
        return matches.singleOrNull() ?: -1
    }
    fun key(sound:ImportedInstrument):String? {
        val source=sound.sourceIdentity ?: return null
        return if(sound.modes.isNotEmpty())"$source:mode:${sound.selectedMode}"
            else sound.selectedSound?.let{"$source:sound:$it"}
    }
    fun choose(sounds:List<ImportedInstrument>,name:String,key:String?=null):ImportedInstrument {
        val matches=if(key!=null)sounds.filter{key(it)==key} else sounds.filter{it.name==name}
        require(matches.size==1){"Saved SET sound is missing or ambiguous"}
        return matches.single()
    }
    data class Mode(val file:File,val instrument:ImportedInstrument){
        val name:String get()="${instrument.sourceName} / MODE ${instrument.modes.first().name}"
    }
    /** Only a directly selected voice is persisted here; STS keeps its own restore path. */
    fun changedMode(sound:SampledInstrument?,file:File?,savedFile:String?,savedName:String?,savedKey:String?=null):String? {
        if(file==null || savedFile!=file.name || savedName==null)return null
        val mode=sound?.modeSelection ?: return null
        if(mode.file!=file)return null
        val currentKey=key(mode.instrument)
        return mode.name.takeIf{it!=savedName || (currentKey!=null && currentKey!=savedKey)}
    }
    fun resolve(directory:File,fileName:String,soundName:String,soundKey:String?=null):Pair<File,ImportedInstrument> {
        require(Regex("[0-9a-f]{64}\\.orgset").matches(fileName)){"Invalid saved SET reference"}
        val file=File(directory,fileName)
        require(file.isFile){"Saved SET file is missing"}
        val sounds=ImportedInstrumentLoader.playableSounds(ImportedSetInstruments.read(file))
        return file to choose(sounds,soundName,soundKey)
    }
}
