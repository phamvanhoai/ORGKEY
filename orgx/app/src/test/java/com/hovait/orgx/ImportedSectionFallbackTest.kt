package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSectionFallbackTest {
    private fun part(id:Int,parent:Int,section:Int)=ImportedStyleGroup(id,parent,"Part",section,section in 32..35,0,1000,120)
    @Test fun missingSectionUsesGroupZeroWithoutReplacingLocalSection(){
        val main=part(1,100,32);val fill=part(2,0,48);val intro=part(3,100,16);val otherIntro=part(4,0,16)
        val index=ImportedSetIndex(emptyList(),listOf(main,fill,intro,otherIntro,part(5,999,64)))
        val style=ImportedStyleDefinition(100,"Style",listOf(main,intro))
        val result=ImportedSectionFallback.complete(index,style)
        assertEquals(listOf(3,1,2),result.sections.map{it.id})
        assertEquals(result,ImportedSectionFallback.complete(index,result))
    }
    @Test fun fallbackPlayerIsLoadedAndCanBeRequested(){
        val file=File.createTempFile("orgx-fallback",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32);val fill=part(2,0,48)
            val region=ImportedSection(60,48000,1,2,100,0,0,0,0,0,0,ImportedWave(0,4))
            val instrument=ImportedInstrument("Kit",100,0,1,listOf(ImportedSound("Sound",listOf(0,0,100,10,0,0),listOf(region))))
            val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100))
            val index=ImportedSetIndex(listOf(instrument),listOf(main,fill),listOf(ImportedTrack("Main",1,0,false,events),ImportedTrack("Fill",2,0,false,events)))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)))
            assertTrue(session.available(2));session.press(2,false,false);session.next(120)
            assertEquals(2,session.currentId);assertTrue(session.player.left>0)
        }finally{file.delete()}
    }
}
