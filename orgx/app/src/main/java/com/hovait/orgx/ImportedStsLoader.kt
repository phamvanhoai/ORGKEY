package com.hovait.orgx

import java.io.File

/** Prepare the keyboard sound completely before replacing the active snapshot. */
object ImportedStsLoader {
    data class Prepared(val file:File,val index:ImportedSetIndex,val snapshot:ImportedSts,val sound:SampledInstrument){
        fun currentSnapshot():ImportedSts {
            val selected=sound.modeSelection?.instrument ?: return snapshot
            if(selected.selectedMode==snapshot.mode)return snapshot
            val mode=selected.modes.firstOrNull() ?: return snapshot
            return snapshot.copy(mode=selected.selectedMode,settings=mode.settings,effects=mode.effects,sourceVersion=selected.sourceVersion ?: snapshot.sourceVersion)
        }
    }
    fun prepare(file:File,index:ImportedSetIndex,snapshot:ImportedSts,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()},keyConnectionMethod:Int=0)=Prepared(file,index,snapshot,load(file,index,snapshot,scriptOverrides,keyConnectionMethod))
    fun source(index:ImportedSetIndex,snapshot:ImportedSts):ImportedInstrument {
        ImportedStsChordSettings.validate(snapshot.chordType,snapshot.chordSingle)
        val raw=index.instruments.getOrNull(snapshot.instrument) ?: error("STS instrument unavailable")
        require(snapshot.mode<0 || snapshot.mode in raw.modes.indices || (raw.modes.isEmpty() && snapshot.mode==0)){
            "STS mode unavailable: ${snapshot.mode}"
        }
        return when {
            snapshot.mode<0->raw.copy(modes=emptyList())
            snapshot.mode in raw.modes.indices->raw.copy(modes=listOf(raw.modes[snapshot.mode]),selectedMode=snapshot.mode)
            else->raw
        }
    }
    fun load(file:File,index:ImportedSetIndex,snapshot:ImportedSts,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()},keyConnectionMethod:Int=0):SampledInstrument {
        val source=source(index,snapshot)
        require(index.systems.isEmpty() || snapshot.system in index.systems.indices){"STS system unavailable"}
        val settings=ImportedMusicParameters.pitch(snapshot.settings,index.musicSettings,index.musicSettings.octave)
        return ImportedInstrumentLoader.load(file,source,index.systems.getOrNull(snapshot.system),settings,index.musicSettings,keyConnectionMethod=keyConnectionMethod,sourceVersion=index.sourceVersion,scriptOverrides=scriptOverrides(file,source),scriptOverridesForMode=scriptOverrides)
            .withKeyboardSettings(settings,source.type==0)
            .withExpressionEffects(ImportedExpressionAutomation.applyLegacyKeyConnectionMethod(snapshot.effects,snapshot.sourceVersion ?: index.sourceVersion,keyConnectionMethod))
    }
}
