package com.hovait.orgx

import java.io.File
import java.io.RandomAccessFile

object ImportedInstrumentLoader {
    /** Preserve original nearest-root decisions, while loading only regions used by this track. */
    fun forTrack(instrument:ImportedInstrument,events:List<ReferenceDrumEvent>):ImportedInstrument {
        if(instrument.sounds.size>1 || instrument.modes.isNotEmpty())return instrument.copy(sounds=instrument.sounds.map{sound->
            forTrack(instrument.copy(sounds=listOf(sound),modes=emptyList()),events).sounds.single()
        })
        val sound=instrument.sounds.firstOrNull() ?: return instrument
        val roots=sound.sections.map{it.note}
        if(roots.isEmpty())return instrument
        // Trimming changes the repeat width, so PitchMode 1 must keep its original range.
        if(sound.envelope.getOrNull(4)==1)return instrument.copy(sounds=listOf(sound))
        val exact=sound.envelope.getOrNull(4)==2
        val required=events.filter{it.type==2 && it.note in 0..127}.mapNotNull{event->
            if(exact)event.note.takeIf{it in roots} else SampleRegionMap.nearestRoot(event.note,roots)
        }.toSet()
        return instrument.copy(sounds=listOf(sound.copy(sections=sound.sections.filter{it.note in required})))
    }
    /** Each entry contains exactly the selected sound; no DNC mode mixing is implied. */
    fun playableSounds(instruments:List<ImportedInstrument>):List<ImportedInstrument> =
        instruments.flatMap { instrument ->
            val sounds=instrument.sounds.mapIndexedNotNull { index,sound ->
                val selected=instrument.copy(name="${instrument.name} / ${sound.name.ifBlank{"Sound ${index+1}"}}",
                    sounds=listOf(sound),modes=emptyList())
                selected.takeIf(::supported)
            }
            sounds+instrument.modes.mapNotNull{mode->
                instrument.copy(name="${instrument.name} / MODE ${mode.name}",modes=listOf(mode)).takeIf(::supported)
            }
        }
    fun supported(instrument:ImportedInstrument):Boolean {
        if(instrument.modes.isNotEmpty())return layers(instrument).let{it.isNotEmpty() && it.all(::supported)}
        val sound=instrument.sounds.firstOrNull() ?: return false
        val pitchMode=sound.envelope.getOrNull(4)
        val supportedType=instrument.type in 0..2
        return supportedType && runCatching{ImportedEnvelope.from(sound.envelope)}.isSuccess && pitchMode in 0..2 &&
            sound.sections.isNotEmpty() && sound.sections.all{
                it.frames>0 && instrument.pan.toLong()+it.pan in -100L..100L && it.loop in listOf(0,2,3) &&
                    (it.loop==0 || (it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.frames))
            }
    }
    fun load(file:File,instrument:ImportedInstrument,system:ImportedSystem?=null,settings:ImportedChannelSettings=ImportedChannelSettings()):SampledInstrument {
        if(instrument.modes.isNotEmpty()){
            val layers=layers(instrument)
            require(layers.isNotEmpty()){"Instrument mode contains no playable sounds"}
            require(layers.sumOf{it.sounds.single().sections.sumOf{section->section.wave.bytes.toLong()}}<=64L*1024*1024){"Instrument mode exceeds 64 MB PCM budget"}
            return SampledInstrument.combine(layers.map{load(file,it,system,settings)})
        }
        require(supported(instrument)){"Unsupported instrument parameters"}
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
                    section.loopStart,section.loopEnd,instrument.volume.toDouble()*section.volume/10000,instrument.pan+section.pan,section.loop!=0,
                    section.tune+if(instrument.type==0)settings.tune else 0)
            }
            SampledInstrument(regions,48000,exactNotes=instrument.sounds.first().envelope[4]==2,
                envelope=ImportedEnvelope.from(instrument.sounds.first().envelope).let{it.copy(release=(it.release.toLong()*settings.releasePercent/100).coerceIn(0,100).toInt())},pitchMode=instrument.sounds.first().envelope[4],
                tuning=if(instrument.type==0)system?.table.orEmpty() else emptyList())
        }
    }
    /** Channels.OpenKey: default mode primary plus every enabled Mix slot, including duplicates. */
    fun layers(instrument:ImportedInstrument):List<ImportedInstrument>{
        val mode=instrument.modes.firstOrNull() ?: return listOf(instrument)
        fun layer(sound:Int,volume:Int,pan:Int)=instrument.sounds.getOrNull(sound)?.let{selected->
            instrument.copy(volume=instrument.volume*volume/100,pan=instrument.pan+pan,sounds=listOf(selected),modes=emptyList())
        }
        return buildList {
            layer(mode.sound,100,0)?.let(::add)
            mode.mix.forEachIndexed{slot,sound->if(sound>=0)layer(sound,100+mode.mixVolume.getOrElse(slot){0},mode.mixPan.getOrElse(slot){0})?.let(::add)}
        }
    }
}
