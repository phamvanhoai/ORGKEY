package com.hovait.orgx

import java.io.File
import java.io.RandomAccessFile

object ImportedInstrumentLoader {
    /** Channels.OpenKey multiplies stored Instrument/InstSec volumes only when both are nonzero. */
    internal fun regionGain(instrumentVolume:Int,sectionVolume:Int):Double =
        if(instrumentVolume==0 || sectionVolume==0)1.0 else instrumentVolume.toDouble()*sectionVolume/10000
    fun modeReferenceError(instrument:ImportedInstrument):String? {
        val mode=instrument.modes.firstOrNull() ?: return null
        val references=listOf("primary" to mode.sound,"Aftertouch" to mode.aftertouch)+
            mode.mix.mapIndexed{slot,sound->"Mix ${slot+1}" to sound}
        return references.firstOrNull{(_,sound)->sound>=0 && sound !in instrument.sounds.indices}
            ?.let{(role,sound)->"Mode '${mode.name}': missing $role sound $sound"}
    }
    /** Preserve original nearest-root decisions, while loading only regions used by this track. */
    fun forTrack(instrument:ImportedInstrument,events:List<ReferenceDrumEvent>):ImportedInstrument {
        // Runtime transpose/octave can select roots absent from the initial notes.
        if(events.any{it.type==41 || it.type==42 || it.type==64 || it.type==231 || it.type==232})return instrument
        if(instrument.sounds.size>1 || instrument.modes.isNotEmpty())return instrument.copy(sounds=instrument.sounds.map{sound->
            forTrack(instrument.copy(sounds=listOf(sound),modes=emptyList()),events).sounds.single()
        })
        val sound=instrument.sounds.firstOrNull() ?: return instrument
        val roots=sound.sections.map{it.note}
        if(roots.isEmpty())return instrument
        // Trimming changes the repeat width, so PitchMode 1 must keep its original range.
        if(sound.envelope.getOrNull(4)==1)return instrument.copy(sounds=listOf(sound))
        val exact=sound.envelope.getOrNull(4)==2
        val required=events.filter{it.type in listOf(2,4) && it.note in 0..127}.mapNotNull{event->
            if(exact)event.note.takeIf{it in roots} else SampleRegionMap.nearestRoot(event.note,roots)
        }.toSet()
        return instrument.copy(sounds=listOf(sound.copy(sections=sound.sections.filter{it.note in required})))
    }
    /** Each entry contains exactly the selected sound; no DNC mode mixing is implied. */
    fun playableSounds(instruments:List<ImportedInstrument>):List<ImportedInstrument> =
        instruments.flatMap { instrument ->
            val sounds=instrument.sounds.mapIndexedNotNull { index,sound ->
                val selected=instrument.copy(name="${instrument.name} / ${sound.name.ifBlank{"Sound ${index+1}"}}",
                    sounds=listOf(sound),modes=emptyList(),selectedSound=index)
                selected.takeIf(::supported)
            }
            sounds+instrument.modes.mapIndexedNotNull{index,mode->
                instrument.copy(name="${instrument.name} / MODE ${mode.name}",modes=listOf(mode),selectedMode=index,selectedSound=null).takeIf(::supported)
            }
        }
    fun supported(instrument:ImportedInstrument)=unsupportedReason(instrument)==null
    fun unsupportedReason(instrument:ImportedInstrument):String? {
        if(instrument.modes.isNotEmpty()){
            modeReferenceError(instrument)?.let{return it}
            val primary=layers(instrument)
            val alternate=if(instrument.modes.first().aftertouch>=0)layers(instrument,true) else emptyList()
            if(primary.isEmpty())return "Mode has no playable sounds"
            return (primary+alternate).firstNotNullOfOrNull(::unsupportedReason)
        }
        val sound=instrument.sounds.firstOrNull() ?: return "Instrument has no sound"
        val pitchMode=sound.envelope.getOrNull(4)
        if(instrument.type !in 0..2)return "Unsupported instrument type ${instrument.type}"
        runCatching{ImportedEnvelope.from(sound.envelope)}.exceptionOrNull()?.let{return "Envelope: ${it.message}"}
        if(pitchMode !in 0..2)return "Unsupported pitch mode $pitchMode"
        // Channels.OpenKey leaves i26=-1 when no section exists: that note is
        // silent, rather than making the entire rhythm group invalid.
        sound.sections.forEach{region->
            if(region.frames<=0)return "Sample ${region.note} has no frames"
            if(instrument.pan !in -100..100 || region.pan !in -100..100)return "Sample ${region.note} balance outside range"
            if(region.loop !in 0..3)return "Sample ${region.note} uses loop mode ${region.loop}"
            if(region.loop!=0 && (region.loopStart<0 || region.loopEnd<=region.loopStart || region.loopEnd>region.frames))
                return "Sample ${region.note} loop ${region.loopStart}..${region.loopEnd} exceeds ${region.frames} frames"
        }
        return null
    }
    fun load(file:File,instrument:ImportedInstrument,system:ImportedSystem?=null,settings:ImportedChannelSettings?=null,
        musicSettings:ImportedChannelSettings=ImportedChannelSettings(),aftertouch:Boolean=false,keyConnectionMethod:Int=0,sourceVersion:String="1.4.6",scriptOverrides:Map<String,String> = emptyMap(),prepareExit:Boolean=true,scriptOverridesForMode:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()}):SampledInstrument {
        if(instrument.modes.isNotEmpty()){
            require(modeReferenceError(instrument)==null){modeReferenceError(instrument).orEmpty()}
            val layers=layers(instrument,aftertouch)
            require(layers.isNotEmpty()){"Instrument mode contains no playable sounds"}
            val alternateLayers=if(!aftertouch && instrument.modes.first().aftertouch>=0)layers(instrument,true) else emptyList()
            val mode=instrument.modes.first()
            // Play defaults to InstMode.Sound, and an explicit sound bypasses the mode Mix.
            // Decode before playback: script execution must never read PCM from disk.
            val scriptIndices=if(mode.script.isBlank())emptyList() else
                if(Regex("(?i)\\bsound\\s*:").containsMatchIn(mode.script))instrument.sounds.indices.toList()
                else listOf(mode.sound.coerceAtLeast(0))
            val scriptSources=scriptIndices.associateWith{sound->
                require(sound in instrument.sounds.indices){"Script default sound unavailable: $sound"}
                instrument.copy(sounds=listOf(instrument.sounds[sound]),modes=emptyList())
            }.filterValues(::supported)
            val uniqueLayers=(layers+alternateLayers+scriptSources.values).distinct()
            require(uniqueLayers.sumOf{it.sounds.single().sections.sumOf{section->section.wave.bytes.toLong()}}<=64L*1024*1024){"Instrument mode exceeds 64 MB PCM budget"}
            val modeSettings=settings ?: ImportedMusicParameters.pitch(instrument.modes.first().settings,musicSettings,musicSettings.octave)
            val decoded=uniqueLayers.associateWith{load(file,it,system,modeSettings,musicSettings,keyConnectionMethod=keyConnectionMethod,sourceVersion=sourceVersion)}
            val loaded=SampledInstrument.combine(layers.map{decoded.getValue(it).copySampleSource()})
                .withExpressionEffects(if(settings==null)ImportedExpressionAutomation.applyLegacyKeyConnectionMethod(instrument.modes.first().effects,instrument.sourceVersion ?: sourceVersion,keyConnectionMethod) else emptyList())
                .withScriptSounds(scriptSources.mapValues{decoded.getValue(it.value).copySampleSource()},mode.sound)
                .withModeScript(instrument.modes.first().script,instrument.modes.first().scriptSettings+scriptOverrides)
            loaded.scriptSettingsKey=ImportedDncPreferences.key(file,instrument)
            loaded.modeSelection=ImportedSoundSelection.Mode(file,instrument)
            if(alternateLayers.isNotEmpty()){
                val alternate=SampledInstrument.combine(alternateLayers.map{decoded.getValue(it).copySampleSource()})
                loaded.withAftertouch(alternate)
            }
            if(settings==null)loaded.withKeyboardSettings(modeSettings,instrument.type==0)
            if(prepareExit && instrument.selectedMode!=0 && mode.script.contains(Regex("(?i)\\bexit\\b")) && instrument.defaultMode!=null){
                val target=instrument.copy(name="${instrument.sourceName} / MODE ${instrument.defaultMode.name}",modes=listOf(instrument.defaultMode),selectedMode=0)
                // Conservative aggregate budget for the two prepared mode contexts.
                val currentBytes=uniqueLayers.sumOf{it.sounds.single().sections.sumOf{section->section.wave.bytes.toLong()}}
                val targetBytes=instrument.sounds.sumOf{it.sections.sumOf{section->section.wave.bytes.toLong()}}
                require(currentBytes+targetBytes<=64L*1024*1024){"DNC exit modes exceed 64 MB PCM budget"}
                val fallback=load(file,target,system,musicSettings=musicSettings,keyConnectionMethod=keyConnectionMethod,sourceVersion=sourceVersion,scriptOverrides=scriptOverridesForMode(file,target),prepareExit=false)
                loaded.withExitModes(mapOf(instrument.selectedMode to loaded,0 to fallback),instrument.selectedMode)
            }else loaded.withExitModes(mapOf(instrument.selectedMode to loaded),instrument.selectedMode)
            return loaded
        }
        require(supported(instrument)){"Unsupported instrument parameters"}
        val channel=settings ?: ImportedMusicParameters.pitch(ImportedChannelSettings(),musicSettings,musicSettings.octave)
        val sections=instrument.sounds.first().sections
        require(sections.sumOf{it.wave.bytes.toLong()}<=64L*1024*1024){"Instrument exceeds 64 MB PCM budget"}
        return RandomAccessFile(file,"r").use{reader->
            val regions=sections.map{section->
                reader.seek(section.wave.offset)
                val raw=ByteArray(section.wave.bytes);reader.readFully(raw)
                fun value(index:Int)=((raw[index].toInt() and 255) or (raw[index+1].toInt() shl 8)).toShort()
                val stereo=if(section.channels==2)ShortArray(section.frames*2){value(it*2)} else null
                val mono=ShortArray(section.frames){frame->
                    if(stereo==null)value(frame*2) else ((stereo[frame*2].toInt()+stereo[frame*2+1])/2).toShort()
                }
                InstrumentRegion(section.note,SamplePcm(section.rate,section.channels,mono,stereo),
                    section.loopStart,section.loopEnd,regionGain(instrument.volume,section.volume)*instrument.layerGain,
                    (instrument.pan.toLong()+section.pan+instrument.layerPan).coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt(),section.loop!=0,
                    section.tune+if(instrument.type==0)channel.tune+musicSettings.tune else 0,tempoSync=section.loop==3,loopUntilRelease=section.loop==1)
            }
            SampledInstrument(regions,48000,exactNotes=instrument.sounds.first().envelope[4]==2,
                envelope=ImportedEnvelope.from(instrument.sounds.first().envelope).let{it.copy(release=ImportedMusicParameters.release(it.release,channel,musicSettings))},pitchMode=instrument.sounds.first().envelope[4],
                tuning=if(instrument.type==0)system?.table.orEmpty() else emptyList(),tuningTranspose=channel.transpose,
                rawRelease=instrument.sounds.first().envelope[3],musicReleasePercent=musicSettings.releasePercent,loadedReleasePercent=channel.releasePercent,
                loadedMusicTranspose=musicSettings.transpose,loadedMusicOctave=musicSettings.octave)
        }.let{if(settings==null)it.withKeyboardSettings(channel,instrument.type==0) else it}
            .also{it.sourceSelectionKey=ImportedSoundSelection.key(instrument)}
    }
    /** Channels.OpenKey: default mode primary plus every enabled Mix slot, including duplicates. */
    fun layers(instrument:ImportedInstrument,aftertouch:Boolean=false):List<ImportedInstrument>{
        val mode=instrument.modes.firstOrNull() ?: return listOf(instrument)
        fun layer(sound:Int,volume:Int,pan:Int)=instrument.sounds.getOrNull(sound)?.let{selected->
            instrument.copy(layerGain=instrument.layerGain*volume/100,layerPan=instrument.layerPan+pan,sounds=listOf(selected),modes=emptyList())
        }
        return buildList {
            val primary=if(aftertouch && mode.aftertouch>=0)mode.aftertouch else mode.sound
            layer(primary,100,0)?.let(::add)
            mode.mix.forEachIndexed{slot,sound->if(sound>=0)layer(sound,100+mode.mixVolume.getOrElse(slot){0},mode.mixPan.getOrElse(slot){0})?.let(::add)}
        }
    }
}
