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
        val riffSize=u32(4).toLong() and 0xffffffffL
        require(riffSize>=4 && riffSize+8<=data.size.toLong()) { "Truncated RIFF" }
        val end=(riffSize+8).toInt()
        var format=0;var channels=0;var rate=0;var bits=0;var blockAlign=0
        var foundFormat=false
        var dataOffset=-1;var dataSize=0;var position=12
        while(position.toLong()+8<=end){
            val size=u32(position+4).toLong() and 0xffffffffL
            val start=position+8
            require(start.toLong()+size<=end) { "Truncated WAV chunk" }
            when(String(data,position,4,Charsets.US_ASCII)) {
                "fmt " -> {
                    require(!foundFormat && size>=16) { "Invalid WAV format chunk" }
                    format=u16(start);channels=u16(start+2);rate=u32(start+4)
                    blockAlign=u16(start+12);bits=u16(start+14);foundFormat=true
                }
                "data" -> {
                    require(dataOffset<0) { "Duplicate WAV data chunk" }
                    dataOffset=start;dataSize=size.toInt()
                }
            }
            val next=start.toLong()+size+(size and 1)
            require(next<=end) { "Missing WAV chunk padding" }
            position=next.toInt()
        }
        require(foundFormat&&format==1&&channels in 1..2&&rate in 8000..192000&&bits==16&&blockAlign==channels*2){"Only PCM16 mono/stereo samples supported"}
        require(dataOffset>=0&&dataSize>0&&dataSize% (channels*2)==0){"Missing sample data"}
        val frames=dataSize/(channels*2);val mono=ShortArray(frames)
        for(frame in 0 until frames){var sum=0;for(channel in 0 until channels){val offset=dataOffset+(frame*channels+channel)*2;sum+=u16(offset).toShort().toInt()};mono[frame]=(sum/channels).coerceIn(-32768,32767).toShort()}
        return SamplePcm(rate,channels,mono)
    }
}
