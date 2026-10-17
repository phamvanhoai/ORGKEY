package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSetBankTest {
    @Test fun openingBankIncludesStyleIndexAndTrackDataNotJustVoices() {
        val path=System.getProperty("orgx.legacySet") ?: return
        val bank=ImportedSetBank.open(File(path))
        val sections=bank.index.styles.filter{it.section in 16..35}
        assertTrue("SET has no style sections",sections.isNotEmpty())
        assertTrue("SET has no style tracks",bank.index.tracks.any{track->sections.any{it.id==track.group}})
        assertTrue(bank.sounds.isNotEmpty())
        assertTrue(sections.any{ImportedStyleSupport.reason(bank.index,it)==null})
    }
    @Test fun restoreRejectsPathsOutsideSetStorage() {
        try{ImportedSetBank.restore(File("."),"../sample.Set");fail("Accepted path traversal")}
        catch(expected:IllegalArgumentException){assertEquals("Invalid saved SET reference",expected.message)}
    }
    @Test fun missingSavedBankHasExplicitError() {
        val directory=File.createTempFile("orgx-bank",".tmp")
        try{
            try{ImportedSetBank.restore(directory,"a".repeat(64)+".orgset");fail("Accepted missing bank")}
            catch(expected:IllegalArgumentException){assertEquals("Saved SET file is missing",expected.message)}
        }finally{directory.delete()}
    }
}
