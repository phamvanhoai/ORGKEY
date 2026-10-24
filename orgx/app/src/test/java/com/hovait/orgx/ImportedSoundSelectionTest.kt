package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class ImportedSoundSelectionTest {
    @Test fun duplicateVoiceNamesRestoreByInstrumentAndSoundIdentity(){
        fun source(id:Int)=ImportedInstrument("Same",100,0,0,listOf(
            ImportedSound("Same",listOf(0,0,100,10,0,0),emptyList()),
            ImportedSound("Same",listOf(0,0,100,10,0,0),emptyList())),sourceIdentity="instrument:$id")
        val sounds=ImportedInstrumentLoader.playableSounds(listOf(source(1),source(2)))
        assertEquals(4,sounds.size)
        val selected=sounds.last()
        assertEquals("instrument:2:sound:1",ImportedSoundSelection.key(selected))
        assertSame(selected,ImportedSoundSelection.choose(sounds,selected.name,ImportedSoundSelection.key(selected)))
        for(key in listOf(null,"instrument:9:sound:1")){
            try{ImportedSoundSelection.choose(sounds,selected.name,key);fail("Ambiguous or missing selection accepted")}
            catch(_:IllegalArgumentException){}
        }
    }
    @get:Rule val temp=TemporaryFolder()
    @Test fun savedSoundCanBeResolvedFromImportedContentIdentity() {
        val directory=temp.newFolder()
        val source=File(requireNotNull(System.getProperty("orgx.referenceAssets")),"Default.Template")
        val imported=source.inputStream().use{ReferenceSetStore(directory).import(it)}.first
        val (file,sound)=ImportedSoundSelection.resolve(directory,imported.name,"Organ DNC / 16' 8' 51/3' Perc LF L")
        assertEquals(imported,file);assertEquals(25,sound.sounds.single().sections.size)
    }
    @Test(expected=IllegalArgumentException::class)
    fun rejectsPathTraversal(){ImportedSoundSelection.resolve(temp.root,"../outside.orgset","Organ")}
    @Test(expected=IllegalArgumentException::class)
    fun rejectsMissingSet(){ImportedSoundSelection.resolve(temp.root,"a".repeat(64)+".orgset","Organ")}
}
