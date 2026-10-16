package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class ImportedSoundSelectionTest {
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
