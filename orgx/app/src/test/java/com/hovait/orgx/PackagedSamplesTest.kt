package com.hovait.orgx

import java.io.File
import kotlin.math.abs
import org.junit.Assert.*
import org.junit.Test

class PackagedSamplesTest {
    @Test fun everyReferenceInstrumentCanPlayAllMidiNotesAcrossLoops() {
        val directory=File(requireNotNull(System.getProperty("orgx.sampleDirectory")))
        val instruments=listOf("organ" to ReferenceInstrumentRegions.organ,
            "lead" to ReferenceInstrumentRegions.lead,"flute" to ReferenceInstrumentRegions.flute,
            "bass" to ReferenceInstrumentRegions.bass,"choir" to ReferenceInstrumentRegions.choir,
            "violin" to ReferenceInstrumentRegions.violin)
        instruments.forEach{(name,definitions)->
            val regions=definitions.map { definition ->
                val pcm=File(directory,"orgx_${name}_zone_${definition.root}.wav").inputStream().use(SamplePcmDecoder::decode)
                InstrumentRegion(definition.root,pcm,definition.loopStart,definition.loopEnd,definition.gain)
            }
            val instrument=SampledInstrument(regions,48000)
            repeat(128){note->
                val region=regions.first{it.root==SampleRegionMap.nearestRoot(note,regions.map{it.root})}
                val step=SamplePitch.step(note,region.root,region.sample.sampleRate,48000)
                val boundary=kotlin.math.ceil(region.loopEnd/step).toLong()
                var energy=0.0
                // Inspect the attack, first wrap, and a later wrap at every MIDI pitch.
                listOf(0L,boundary,boundary*5).forEach{start->
                    repeat(128){offset->
                        val value=instrument.sample(note,start+offset)
                        assertTrue("$name note $note",value.isFinite() && kotlin.math.abs(value)<=region.gain+1e-6)
                        energy+=value*value
                    }
                }
                assertTrue("Silent $name note $note",energy>0)
            }
        }
    }
    @Test fun allPackagedWavesDecodeAndKeepDurationAtOutputRate() {
        val directory=File(requireNotNull(System.getProperty("orgx.sampleDirectory")))
        val files=requireNotNull(directory.listFiles { file -> file.extension=="wav" })
        assertEquals(116,files.size)
        files.forEach { file ->
            val sample=file.inputStream().use(SamplePcmDecoder::decode)
            assertTrue(file.name,sample.frames.isNotEmpty())
            assertTrue("Silent sample: ${file.name}",sample.frames.any { it.toInt()!=0 })
            val normalized=SampleResampler.toRate(sample,48000)
            assertTrue(file.name,abs(normalized.size/48000.0-sample.frames.size.toDouble()/sample.sampleRate)<=1.0/48000)
            assertTrue("Silent resampling: ${file.name}",normalized.any { it.toInt()!=0 })
        }
    }
}
