package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class MidiIntegrationTest {
    @Test fun realMidiFixtureCombinesProgramsDrumsSustainAndTempoChanges() {
        val song=File(requireNotNull(System.getProperty("orgx.midiDemo"))).inputStream().use(MidiFile::read)
        assertEquals(setOf(0,1,2,9),song.events.map{it.channel}.toSet())
        assertTrue(song.events.filter{it.on && it.channel==1}.all{it.program==73})
        assertTrue(song.events.filter{it.on && it.channel==2}.all{it.program==32})
        assertTrue(song.events.filter{!it.on && it.channel==0}.all{it.tick==960L})
        assertEquals(listOf(500000,750000),song.tempos.map{it.microsPerQuarter})
        val clock=MidiPlaybackClock(song)
        assertEquals(3750000L,clock.advance(2880))
    }
}
