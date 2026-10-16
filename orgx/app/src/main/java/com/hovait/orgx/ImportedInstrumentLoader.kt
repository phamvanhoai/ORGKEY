package com.hovait.orgx

import java.io.File
import java.io.RandomAccessFile

object ImportedInstrumentLoader {
    fun supported(instrument:ImportedInstrument):Boolean {
        val sound=instrument.sounds.firstOrNull() ?: return false
        return instrument.type==0 && instrument.pan==0 && sound.envelope.getOrNull(4)==0 &&
            sound.sections.isNotEmpty() && sound.sections.all{
                it.frames>0 && it.pan==0 && it.tune==0 && it.start==0 && it.loop==2 &&
                    it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.frames
            }
    }
    fun load(file:File,instrument:ImportedInstrument):SampledInstrument {
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
                    section.loopStart,section.loopEnd,instrument.volume.toDouble()*section.volume/10000)
            }
            SampledInstrument(regions,48000)
        }
    }
}
