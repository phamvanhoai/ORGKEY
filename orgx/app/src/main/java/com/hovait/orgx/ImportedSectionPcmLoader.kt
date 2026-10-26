package com.hovait.orgx

import java.io.RandomAccessFile

/** Reads an inline or shared SET wave section without changing the source SET. */
object ImportedSectionPcmLoader {
    fun load(file:java.io.File,section:ImportedSection):SamplePcm=RandomAccessFile(file,"r").use{reader->
        require(section.wave.offset>=0 && section.wave.bytes>=0)
        reader.seek(section.wave.offset)
        val bytes=ByteArray(section.wave.bytes)
        reader.readFully(bytes)
        require(bytes.size==section.frames*section.channels*2)
        val samples=ShortArray(bytes.size/2){i->
            ((bytes[i*2].toInt() and 255) or (bytes[i*2+1].toInt() shl 8)).toShort()
        }
        if(section.channels==1)SamplePcm(section.rate,1,samples)
        else{
            val mono=ShortArray(section.frames);val stereo=ShortArray(section.frames*2)
            for(i in 0 until section.frames){
                val left=samples[i*2];val right=samples[i*2+1]
                stereo[i*2]=left;stereo[i*2+1]=right
                mono[i]=((left.toInt()+right.toInt())/2).toShort()
            }
            SamplePcm(section.rate,2,mono,stereo)
        }
    }
}
