package com.hovait.orgx

import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

data class SamplePcm(val sampleRate:Int,val channels:Int,val frames:ShortArray)

object SamplePcmDecoder {
    fun decode(input:InputStream):SamplePcm {
        val data=input.readBytes();require(data.size>=44){"Incomplete sample"}
        fun u16(offset:Int)=ByteBuffer.wrap(data,offset,2).order(ByteOrder.LITTLE_ENDIAN).short.toInt() and 65535
        fun u32(offset:Int)=ByteBuffer.wrap(data,offset,4).order(ByteOrder.LITTLE_ENDIAN).int
        require(String(data,0,4,Charsets.US_ASCII)=="RIFF"&&String(data,8,4,Charsets.US_ASCII)=="WAVE")
        require(String(data,12,4,Charsets.US_ASCII)=="fmt ")
        val format=u16(20);val channels=u16(22);val rate=u32(24);val bits=u16(34)
        require(format==1&&channels in 1..2&&rate in 8000..192000&&bits==16){"Only PCM16 mono/stereo samples supported"}
        var dataOffset=-1;var dataSize=0;var position=36
        while(position+8<=data.size){val size=u32(position+4);val start=position+8;require(size>=0&&start+size<=data.size);if(String(data,position,4,Charsets.US_ASCII)=="data"){dataOffset=start;dataSize=size;break};position=start+(size and 1)}
        require(dataOffset>=0&&dataSize>0&&dataSize% (channels*2)==0){"Missing sample data"}
        val frames=dataSize/(channels*2);val mono=ShortArray(frames)
        for(frame in 0 until frames){var sum=0;for(channel in 0 until channels){val offset=dataOffset+(frame*channels+channel)*2;sum+=u16(offset).toShort().toInt()};mono[frame]=(sum/channels).coerceIn(-32768,32767).toShort()}
        return SamplePcm(rate,channels,mono)
    }
}
