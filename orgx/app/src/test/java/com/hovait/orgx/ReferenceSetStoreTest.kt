package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class ReferenceSetStoreTest {
    @get:Rule val temp=TemporaryFolder()
    private fun asset(name:String)=File(requireNotNull(System.getProperty("orgx.referenceAssets")),name)
    @Test fun inspectsRealTemplateWaveBank() {
        val info=ReferenceSetStore.inspect(asset("Default.Template"))
        assertEquals("1.4.6",info.version)
        assertEquals(1251,info.waves)
        assertEquals(62086248L,info.pcmBytes)
        assertEquals("1.2.4",ReferenceSetStore.inspect(asset("empty.Template")).version)
    }
    @Test fun duplicateImportsKeepOneFileAndFailedImportLeavesNoStagingFile() {
        val directory=temp.newFolder();val store=ReferenceSetStore(directory)
        repeat(2){asset("empty.Template").inputStream().use{store.import(it)}}
        assertEquals(1,store.list().size)
        try{byteArrayOf(1,2,3).inputStream().use{store.import(it)};fail("Accepted invalid SET")}
        catch(_:IllegalArgumentException){}
        assertEquals(1,directory.listFiles()!!.size)
    }
}
