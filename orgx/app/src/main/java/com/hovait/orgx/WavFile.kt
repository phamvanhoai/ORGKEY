package com.hovait.orgx

import java.io.File
import java.io.RandomAccessFile

data class WavMetadata(val channels:Int,val sampleRate:Int,val bitsPerSample:Int,val dataOffset:Long,val dataSize:Long)

object WavFile{
    fun inspect(file:File):WavMetadata=RandomAccessFile(file,"r").use{input->
        require(input.length()>=12){"Incomplete WAV"}
        fun fourCc():String=String(ByteArray(4).also{input.readFully(it)},Charsets.US_ASCII)
        fun u16():Int=(input.readUnsignedByte() or (input.readUnsignedByte() shl 8))
        fun u32():Long=(input.readUnsignedByte().toLong() or (input.readUnsignedByte().toLong() shl 8) or (input.readUnsignedByte().toLong() shl 16) or (input.readUnsignedByte().toLong() shl 24))
        require(fourCc()=="RIFF"){"Not RIFF"};u32();require(fourCc()=="WAVE"){"Not WAVE"}
        var format=-1;var channels=0;var rate=0;var bits=0;var dataOffset=-1L;var dataSize=-1L
        while(input.filePointer+8<=input.length()){
            val id=fourCc();val size=u32();val start=input.filePointer;val end=start+size
            require(end>=start&&end<=input.length()){"Incomplete WAV chunk"}
            when(id){
                "fmt "->{require(size>=16){"Invalid fmt chunk"};format=u16();channels=u16();rate=u32().toInt();u32();u16();bits=u16()}
                "data"->{if(dataOffset<0){dataOffset=start;dataSize=size}}
            }
            val next=end+(size and 1L);require(next<=input.length()){"Invalid WAV padding"};input.seek(next)
        }
        require(format==1&&channels in 1..2&&rate in 8000..192000&&bits==16){"Only mono/stereo PCM 16-bit WAV is supported"}
        require(dataOffset>=0&&dataSize>0&&dataOffset+dataSize<=input.length()){"Missing WAV data"}
        WavMetadata(channels,rate,bits,dataOffset,dataSize)
    }
}
