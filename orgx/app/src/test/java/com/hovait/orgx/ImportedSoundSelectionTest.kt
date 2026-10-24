package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class ImportedSoundSelectionTest {
    @Test fun scriptExitToSameNamedModeStillUpdatesSavedIdentity(){
        val file=temp.newFile("same-mode.pcm")
        file.writeBytes(byteArrayOf(0,32,0,32))
        val sample=ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(
            ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))))
        fun mode(script:String="")=ImportedMode("Same",0,-1,List(8){-1},List(8){0},List(8){0},script=script)
        val raw=ImportedInstrument("Voice",100,0,0,listOf(sample),listOf(mode(),
            mode("on(touch){play();return(true);} on(release){exit;}")),sourceIdentity="instrument:5")
        val selected=ImportedInstrumentLoader.playableSounds(listOf(raw)).last()
        val loaded=ImportedInstrumentLoader.load(file,selected)
        val savedKey=ImportedSoundSelection.key(selected)
        assertNull(ImportedSoundSelection.changedMode(loaded,file,file.name,selected.name,savedKey))
        val voice=loaded.voice(60);voice.next(false);voice.next(true)
        assertEquals(selected.name,loaded.modeSelection!!.name)
        assertEquals(selected.name,ImportedSoundSelection.changedMode(loaded,file,file.name,selected.name,savedKey))
        val newKey=ImportedSoundSelection.key(loaded.modeSelection!!.instrument)
        assertEquals("instrument:5:mode:0",newKey)
        assertNull(ImportedSoundSelection.changedMode(loaded,file,file.name,selected.name,newKey))
    }
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
