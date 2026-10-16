package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class InstrumentCatalogTest {
    @Test fun fluteIndexSurvivesValidationAndLegacyIndicesStayStable() {
        assertEquals("Jazz Flute DNC",InstrumentCatalog.names[InstrumentCatalog.validIndex(3)])
        assertEquals(listOf("Piano","Organ","Lead"),InstrumentCatalog.shortNames.take(3))
        assertEquals(0,InstrumentCatalog.validIndex(-1))
        assertEquals(3,InstrumentCatalog.validIndex(99))
    }
}
