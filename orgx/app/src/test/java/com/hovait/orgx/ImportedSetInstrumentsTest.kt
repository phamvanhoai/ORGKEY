package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSetInstrumentsTest {
    @Test fun legacyStyleRendersItsOwnDrumkitThroughActualTimeline() {
        val path=System.getProperty("orgx.legacySet") ?: return
        val file=File(path);val index=ImportedSetInstruments.index(file)
        val groups=index.styles.filter{it.section in 16..35 && ImportedStyleSupport.reason(index,it)==null}
        println("SET blockers: "+index.styles.filter{it.section in 16..35}.groupingBy{ImportedStyleSupport.reason(index,it) ?: "Playable"}.eachCount())
        println("Drum section parameters: "+index.tracks.filter{index.instruments.getOrNull(it.instrument)?.type==1}.map{it.instrument}.distinct().flatMap{index.instruments[it].sounds.first().sections}.groupingBy{"start=${it.start}, loop=${it.loop}"}.eachCount())
        assertTrue("No playable imported style",groups.isNotEmpty())
        val group=groups.first()
        val track=ImportedStyleSupport.drumTracks(index,group).first()
        val kit=ImportedInstrumentLoader.load(file,index.instruments[track.instrument])
        val timeline=ReferenceDrumTrack(track.name,group.id,group.parent,group.section,group.tempo,true,
            group.start.toLong(),group.end.toLong(),false,track.events)
        val player=ReferenceRhythmPlayer(listOf(timeline),kit::durationFrames,{note,age,channel->kit.sample(note,age.toLong(),channel)},48000)
        var energy=0.0
        repeat(48000*4){player.next(0,group.tempo.coerceIn(40,240));
            assertTrue(player.left.isFinite() && player.right.isFinite());energy+=player.left*player.left+player.right*player.right}
        assertTrue("Imported style rendered silence",energy>0)
        println("Playable SET groups: ${groups.size}; rendered group ${group.id}, energy=$energy")
    }
    @Test fun localLegacySetCanBeIndexedWhenProvided() {
        val path=System.getProperty("orgx.legacySet") ?: return
        val instruments=ImportedSetInstruments.read(File(path))
        val index=ImportedSetInstruments.index(File(path))
        assertTrue(index.styles.isNotEmpty())
        println("Legacy style groups: ${index.styles.size}")
        val drums=index.tracks.filter{index.instruments.getOrNull(it.instrument)?.type==1 && it.events.any{e->e.type==2}}
        println("Legacy tracks: ${index.tracks.size}, drum tracks: ${drums.size}, compatible: ${drums.count{ImportedInstrumentLoader.supported(index.instruments[it.instrument])}}")
        println(drums.take(3).map{index.instruments[it.instrument].name})
        assertTrue(instruments.isNotEmpty())
        val sounds=ImportedInstrumentLoader.playableSounds(instruments)
        val selected=sounds.first()
        val loaded=ImportedInstrumentLoader.load(File(path),selected)
        val note=selected.sounds.single().sections.first().note
        var energy=0.0
        repeat(48000){age->val value=loaded.sample(note,age.toLong());assertTrue(value.isFinite());energy+=value*value}
        assertTrue("Imported legacy sound has no signal",energy>0)
        println("Loaded legacy PCM sound: ${selected.name}")
        println("Legacy SET: ${instruments.size} instruments, ${ImportedInstrumentLoader.playableSounds(instruments).size} playable sounds")
    }
    @Test fun percussionSupportsNearestAndExactButNotCyclicMapping() {
        val wave=ImportedWave(0,8)
        val section=ImportedSection(36,48000,1,4,100,0,0,0,0,0,0,wave)
        val sound=ImportedSound("Kit",listOf(0,0,100,10,2,0),listOf(section))
        val instrument=ImportedInstrument("Percussion",100,0,1,listOf(sound))
        assertTrue(ImportedInstrumentLoader.supported(instrument))
        assertTrue(ImportedInstrumentLoader.supported(instrument.copy(sounds=listOf(sound.copy(envelope=listOf(0,0,100,10,0,0))))))
        assertFalse(ImportedInstrumentLoader.supported(instrument.copy(sounds=listOf(sound.copy(envelope=listOf(0,0,100,10,1,0))))))
        assertFalse(ImportedInstrumentLoader.supported(instrument.copy(type=2)))
    }
    @Test fun loadsCompatibleDrumkitRegionsAndRejectsUnimplementedStartOffsets() {
        val file=File(requireNotNull(System.getProperty("orgx.referenceAssets")),"Default.Template")
        val original=ImportedSetInstruments.read(file).first{it.name=="General Drumkit"}
        assertFalse(ImportedInstrumentLoader.supported(original))
        val sound=original.sounds.first()
        val kit=original.copy(sounds=listOf(sound.copy(sections=sound.sections.filter{it.start==0})))
        assertTrue(ImportedInstrumentLoader.supported(kit))
        val loaded=ImportedInstrumentLoader.load(file,kit)
        var energy=0.0
        repeat(10000){age->val value=loaded.sample(36,age.toLong());assertTrue(value.isFinite());energy+=value*value}
        assertTrue(energy>0)
        assertTrue(loaded.finished(36,480000))
    }
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
