package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TemporaryFolder

class ReferenceSetStoreTest {
    @Test fun legacySetMetadataDoesNotBlockWaveBankImport() {
        val file=temp.newFile()
        val bytes=java.io.ByteArrayOutputStream()
        fun int(value:Int){repeat(4){bytes.write(value ushr (it*8) and 255)}}
        fun text(value:String){val raw=value.toByteArray();int(raw.size);bytes.write(raw)}
        bytes.write("Music Studio music".toByteArray().copyOf(32))
        text("Example\r1.2.8\r");int(0);repeat(3){text("")}
        int(8);bytes.write(ByteArray(8){0xc2.toByte()})
        bytes.write(ByteArray(2));int(0);int(100);int(0);bytes.write(ByteArray(116))
        int(1);int(1);int(0);int(4);bytes.write(byteArrayOf(1,0,2,0));bytes.write(0)
        file.writeBytes(bytes.toByteArray())
        val info=ReferenceSetStore.inspect(file)
        assertEquals("1.2.8",info.version);assertEquals(120,info.tempo)
        assertEquals(1,info.waves);assertEquals(4L,info.pcmBytes)
    }
    @Test fun lowercaseMusicSignatureIsAccepted() {
        val copy=temp.newFile()
        val bytes=asset("empty.Template").readBytes()
        "Music Studio music".toByteArray(Charsets.US_ASCII).copyInto(bytes)
        copy.writeBytes(bytes)
        assertEquals("1.2.4",ReferenceSetStore.inspect(copy).version)
    }
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
