package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedModeTest {
    private fun instrument():ImportedInstrument {
        val section=ImportedSection(60,48000,1,1024,100,0,0,0,0,0,0,ImportedWave(0,2048))
        val a=ImportedSound("A",listOf(0,0,100,10,0,0),listOf(section))
        val b=a.copy(name="B",envelope=listOf(0,0,100,0,0,1))
        return ImportedInstrument("Layered",100,0,0,listOf(a,b),listOf(
            ImportedMode("Default",1,-1,listOf(0,-1,-1,-1,-1,-1,-1,-1),listOf(-50,0,0,0,0,0,0,0),listOf(-100,0,0,0,0,0,0,0))))
    }
    @Test fun defaultModeSelectsPrimarySoundAndMixGainPan(){
        val layers=ImportedInstrumentLoader.layers(instrument())
        assertEquals(listOf("B","A"),layers.map{it.sounds.single().name})
        assertEquals(50,layers[1].volume);assertEquals(-100,layers[1].pan)
        assertTrue(layers.all{it.modes.isEmpty()})
    }
    @Test fun eachMixedLayerHasIndependentEnvelopeAndStereoPan(){
        val file=File.createTempFile("orgx-mode",".pcm")
        try{
            file.writeBytes(ByteArray(2048){if(it%2==0)0 else 32})
            val loaded=ImportedInstrumentLoader.load(file,instrument())
            val voice=loaded.voice(60)
            voice.next(false)
            assertTrue(voice.left>voice.right)
            // Auto-release primary finishes fast; the mixed sound keeps its own release.
            repeat(500){voice.next(false)}
            assertTrue(voice.left>0);assertEquals(0.0,voice.right,0.0)
        }finally{file.delete()}
    }
    @Test fun modeWithDisabledPrimaryCanStillPlayMixSlots(){
        val source=instrument()
        val mixed=source.copy(modes=listOf(source.modes.single().copy(sound=-1)))
        assertEquals(listOf("A"),ImportedInstrumentLoader.layers(mixed).map{it.sounds.single().name})
    }
    @Test fun referenceOrganDefaultModeKeepsStereoLayers(){
        val file=File(requireNotNull(System.getProperty("orgx.referenceAssets")),"Default.Template")
        val organ=ImportedSetInstruments.read(file).first{it.name=="Organ DNC"}
        val layers=ImportedInstrumentLoader.layers(organ)
        assertTrue(layers.size>1)
        val loaded=ImportedInstrumentLoader.load(file,organ)
        val samples=layers.map{ImportedInstrumentLoader.load(file,it)}
        for(age in 48000L..48020L)for(channel in 0..1){
            assertEquals(samples.sumOf{it.sample(60,age,channel)},loaded.sample(60,age,channel),1e-9)
        }
    }
}
