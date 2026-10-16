package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSetInstrumentsTest {
    @Test fun selectsEachSoundSeparatelyAndSkipsUnsupportedPitchModes() {
        val wave=ImportedWave(0,8)
        val section=ImportedSection(60,48000,1,4,100,0,0,0,0,0,0,wave)
        val first=ImportedSound("Primary",listOf(0,0,100,10,0,0),listOf(section))
        val second=first.copy(name="Alternate",sections=listOf(section.copy(volume=54)))
        val unsupported=first.copy(name="Cyclic mapping",envelope=listOf(0,0,100,10,1,0))
        val instrument=ImportedInstrument("Test",100,0,0,listOf(first,second,unsupported))
        val choices=ImportedInstrumentLoader.playableSounds(listOf(instrument))
        assertEquals(listOf("Test / Primary","Test / Alternate"),choices.map{it.name})
        assertEquals(54,choices[1].sounds.single().sections.single().volume)
        assertEquals(3,instrument.sounds.size)
    }
    @Test fun loadsExternalOrganPcmAndSustainsBothChannels() {
        val file=File(requireNotNull(System.getProperty("orgx.referenceAssets")),"Default.Template")
        val organ=ImportedSetInstruments.read(file).first{it.name=="Organ DNC"}
        assertTrue(ImportedInstrumentLoader.supported(organ))
        val loaded=ImportedInstrumentLoader.load(file,organ)
        var energy=0.0
        for(age in 48000L..49000L){
            val left=loaded.sample(60,age,0);val right=loaded.sample(60,age,1)
            assertTrue(left.isFinite());assertEquals(left,right,0.0);energy+=left*left
        }
        assertTrue(energy>0)
        assertTrue(ImportedInstrumentLoader.supported(organ.copy(pan=50)))
        val panned=ImportedInstrumentLoader.load(file,organ.copy(pan=100))
        for(age in 48000L..48010L)assertEquals(0.0,panned.sample(60,age,0),0.0)
        assertFalse(ImportedInstrumentLoader.supported(organ.copy(pan=101)))
    }
    @Test fun indexesAllReferenceInstrumentsWithoutLoadingPcm() {
        val file=File(requireNotNull(System.getProperty("orgx.referenceAssets")),"Default.Template")
        val instruments=ImportedSetInstruments.read(file)
        assertEquals(92,instruments.size)
        assertEquals(2001,instruments.sumOf{it.sounds.sumOf{sound->sound.sections.size}})
        assertEquals("Piano DNC",instruments[1].name)
        assertEquals(9,instruments[1].sounds.first().sections.size)
        instruments.flatMap{it.sounds}.flatMap{it.sections}.forEach{
            assertTrue(it.wave.offset+it.wave.bytes<=file.length())
        }
    }
}
