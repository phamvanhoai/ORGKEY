package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSetInstrumentsTest {
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
