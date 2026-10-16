package com.hovait.orgx

import java.io.File
import kotlin.math.abs
import org.junit.Assert.*
import org.junit.Test

class PackagedSamplesTest {
    @Test fun allPackagedWavesDecodeAndKeepDurationAtOutputRate() {
        val directory=File(requireNotNull(System.getProperty("orgx.sampleDirectory")))
        val files=requireNotNull(directory.listFiles { file -> file.extension=="wav" })
        assertEquals(18,files.size)
        files.forEach { file ->
            val sample=file.inputStream().use(SamplePcmDecoder::decode)
            assertTrue(file.name,sample.frames.isNotEmpty())
            assertTrue("Silent sample: ${file.name}",sample.frames.any { it.toInt()!=0 })
            val normalized=SampleResampler.toRate(sample,48000)
            assertTrue(file.name,abs(normalized.size/48000.0-sample.frames.size.toDouble()/sample.sampleRate)<=1.0/48000)
            assertTrue("Silent resampling: ${file.name}",normalized.any { it.toInt()!=0 })
        }
    }
}
