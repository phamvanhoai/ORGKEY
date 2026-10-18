package com.hovait.orgx

import java.io.File

/** Prepare the keyboard sound completely before replacing the active snapshot. */
object ImportedStsLoader {
    fun load(file:File,index:ImportedSetIndex,snapshot:ImportedSts):SampledInstrument {
        val raw=index.instruments.getOrNull(snapshot.instrument) ?: error("STS instrument unavailable")
        require(snapshot.mode<0 || snapshot.mode in raw.modes.indices || (raw.modes.isEmpty() && snapshot.mode==0)){
            "STS mode unavailable: ${snapshot.mode}"
        }
        val source=when {
            snapshot.mode<0->raw.copy(modes=emptyList())
            snapshot.mode in raw.modes.indices->raw.copy(modes=listOf(raw.modes[snapshot.mode]))
            else->raw
        }
        require(index.systems.isEmpty() || snapshot.system in index.systems.indices){"STS system unavailable"}
        val settings=ImportedMusicParameters.pitch(snapshot.settings,index.musicSettings,index.musicSettings.octave)
        return ImportedInstrumentLoader.load(file,source,index.systems.getOrNull(snapshot.system),settings,index.musicSettings)
            .withKeyboardSettings(settings,source.type==0)
            .withExpressionEffects(snapshot.effects)
    }
}
