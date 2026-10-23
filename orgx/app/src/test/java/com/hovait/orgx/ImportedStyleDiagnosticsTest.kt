package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStyleDiagnosticsTest {
    private val group=ImportedStyleGroup(1,0,"Main",32,true,0,1000,120)
    private fun reason(control:ReferenceDrumEvent,instrument:Int=0):String?{
        val track=ImportedTrack("Bass",1,instrument,false,listOf(ReferenceDrumEvent(2,1,60,100,0,100),control))
        val source=ImportedInstrument("Voice",100,0,0,emptyList())
        return ImportedStyleSupport.reason(ImportedSetIndex(listOf(source),listOf(group),listOf(track)),group,true)
    }
    @Test fun unsupportedEffectNamesTrackSlotParameterAndTime(){
        val message=reason(ReferenceDrumEvent(65,4,5,20,123,0)).orEmpty()
        assertTrue(message.contains("Bass"));assertTrue(message.contains("event 65"))
        assertTrue(message.contains("slot 4"));assertTrue(message.contains("parameter 5"));assertTrue(message.contains("123"))
    }
    @Test fun invalidNoteAndMissingInstrumentAreReportedBeforeSampleLoading(){
        assertTrue(reason(ReferenceDrumEvent(2,2,200,100,50,10)).orEmpty().contains("invalid note 200"))
        assertTrue(reason(ReferenceDrumEvent(1,1,60,0,50,0),99).orEmpty().contains("missing instrument 99"))
    }
}
