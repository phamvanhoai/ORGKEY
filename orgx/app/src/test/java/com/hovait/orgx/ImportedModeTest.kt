package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedModeTest {
    @Test fun keyboardModeConfigurationIsNotAppliedAgainToStyleSamples(){
        val file=File.createTempFile("orgx-keyboard-mode",".pcm")
        try{
            file.writeBytes(ByteArray(2048){if(it%2==0)0 else 32})
            val source=instrument()
            val settings=ImportedChannelSettings(volume=50,pan=100,transpose=12)
            val mode=source.copy(modes=listOf(source.modes.first().copy(sound=0,mix=List(8){-1},settings=settings)))
            val keyboard=ImportedInstrumentLoader.load(file,mode)
            assertEquals(72,keyboard.keyboardNote(60));assertEquals(settings,keyboard.keyboardSettings)
            val style=ImportedInstrumentLoader.load(file,mode,settings=ImportedChannelSettings())
            assertEquals(60,style.keyboardNote(60));assertEquals(100,style.keyboardSettings.volume)
        }finally{file.delete()}
    }
    @Test fun persistedTrackSettingsOverrideEarlierModeSelection(){
        val modeSettings=ImportedChannelSettings(volume=50,pan=100,tune=250,transpose=2,releasePercent=50)
        val sound=instrument().let{it.copy(modes=listOf(it.modes.first().copy(settings=modeSettings)))}
        val track=ImportedTrack("Track",1,0,false,emptyList(),volume=20,pan=-100,settings=ImportedChannelSettings(volume=20,pan=-100))
        val index=ImportedSetIndex(listOf(sound),emptyList())
        assertEquals(track.settings,ImportedStyleSupport.channelSettings(index,track))
        assertEquals(ImportedChannelSettings(volume=20,pan=-100),ImportedStyleSupport.channelSettings(index.copy(instruments=listOf(sound.copy(modes=emptyList()))),track))
    }
    @Test fun modeReleaseScaleReachesEveryLoadedLayer(){
        val file=File.createTempFile("orgx-mode-fx",".pcm")
        try{
            file.writeBytes(ByteArray(2048){if(it%2==0)0 else 32})
            val source=instrument()
            val single=source.copy(modes=listOf(source.modes.first().copy(sound=0,mix=List(8){-1},settings=ImportedChannelSettings(releasePercent=50))))
            val loaded=ImportedInstrumentLoader.load(file,single)
            assertEquals(5,loaded.envelope?.release)
        }finally{file.delete()}
    }
    @Test fun stylePlaybackUsesPersistedTrackVolumeAndPanAfterModeSelection(){
        val file=File.createTempFile("orgx-mode-route",".pcm")
        try{
            file.writeBytes(ByteArray(2048){if(it%2==0)0 else 32})
            val source=instrument()
            val sound=source.copy(modes=listOf(source.modes.first().copy(sound=0,mix=List(8){-1},settings=ImportedChannelSettings(volume=50,pan=100))))
            val group=ImportedStyleGroup(1,0,"Style",32,true,0,1000,120)
            val track=ImportedTrack("Chord",1,0,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100)),volume=20,pan=-100)
            val index=ImportedSetIndex(listOf(sound),listOf(group),listOf(track))
            val player=ImportedStylePlayer.load(file,index,group,true)
            player.next(0,120)
            assertEquals(.05,player.left,1e-9);assertEquals(0.0,player.right,0.0)
        }finally{file.delete()}
    }
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
