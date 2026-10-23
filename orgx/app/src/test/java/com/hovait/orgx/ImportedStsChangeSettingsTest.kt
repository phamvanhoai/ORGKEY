package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsChangeSettingsTest {
    @Test fun defaultMatchesOriginalAndAllBitsRoundTrip(){
        assertEquals(21,ImportedStsChangeSettings.labels.size)
        assertEquals(ImportedStsPlan.defaultChanges,ImportedStsChangeSettings.decode(ImportedStsChangeSettings.defaultMask))
        val alternating=List(21){it%2==0}
        assertEquals(alternating,ImportedStsChangeSettings.decode(ImportedStsChangeSettings.encode(alternating)))
    }
    @Test fun rejectsWrongSizedMask(){
        try{ImportedStsChangeSettings.encode(listOf(true));fail("Invalid mask accepted")}
        catch(expected:IllegalArgumentException){}
    }
}
