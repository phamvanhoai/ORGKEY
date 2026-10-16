package com.hovait.orgx

import java.io.File
import java.io.RandomAccessFile

data class ImportedWave(val offset:Long,val bytes:Int)
data class ImportedSection(val note:Int,val rate:Int,val channels:Int,val frames:Int,val volume:Int,
    val pan:Int,val tune:Int,val start:Int,val loop:Int,val loopStart:Int,val loopEnd:Int,val wave:ImportedWave)
data class ImportedSound(val name:String,val envelope:List<Int>,val sections:List<ImportedSection>)
data class ImportedInstrument(val name:String,val volume:Int,val pan:Int,val type:Int,val sounds:List<ImportedSound>)

/** Bounded, lazy PCM indexing for Music Studio 1.4.6; sample bytes stay on disk. */
object ImportedSetInstruments {
    fun read(file:File):List<ImportedInstrument> = RandomAccessFile(file,"r").use { f->
        fun skip(size:Long){require(size>=0 && size<=f.length()-f.filePointer){"Truncated SET"};f.seek(f.filePointer+size)}
        fun bytes(size:Int):ByteArray{require(size>=0 && size.toLong()<=f.length()-f.filePointer);return ByteArray(size).also(f::readFully)}
        fun int()=Integer.reverseBytes(f.readInt())
        fun count(max:Int):Int=int().also{require(it in 0..max)}
        fun text():String=bytes(count(1000000)).toString(Charsets.UTF_8)
        fun common():List<String>{val metadata=text().split('\r');int();return metadata}
        fun fx(){
            skip(48);val modules=count(1000)
            val extra=mapOf(1 to 8,2 to 17,4 to 8,6 to 8,8 to 28,9 to 4,10 to 4,15 to 4,16 to 4,17 to 4,20 to 8,21 to 24,23 to 9,29 to 8,31 to 4)
            repeat(modules){val type=int();skip(4)
                if(type==7)skip(count(128)*4L) else skip((extra[type] ?: error("Unsupported SET FX $type")).toLong())
            }
        }
        require(bytes(32).toString(Charsets.US_ASCII).substringBefore('\u0000')=="Music Studio Music")
        require(common().getOrNull(1)=="1.4.6"){"Instrument indexing currently supports SET 1.4.6 only"}
        repeat(3){text()};require(int()==0){"Encrypted SET unsupported"};skip(2+12+116L)
        val slots=count(100000);val waves=HashMap<Int,ImportedWave>()
        repeat(count(slots)){val index=int();val length=count(Int.MAX_VALUE)
            require(index in 0 until slots && index !in waves)
            waves[index]=ImportedWave(f.filePointer,length);skip(length.toLong())
        }
        repeat(3){fx()};common()
        List(count(10000)){
            val metadata=common();val version=metadata.getOrNull(1)
            require(version=="1.4.6" || version=="1.4.2"){"Unsupported instrument version $version"}
            skip(112);val volume=int();val pan=int();val type=f.readUnsignedByte();skip(2)
            repeat(f.readUnsignedByte()){
                text();skip(16);fx();skip(336);text()
                if(version=="1.4.6"){
                    val settings=count(10000);repeat(settings){text()}
                    if(settings>0)repeat(count(10000)){text()}
                }
            }
            val sounds=List(f.readUnsignedByte().coerceAtLeast(1)){
                val name=text();val envelope=List(6){f.readUnsignedByte()}
                val sections=List(f.readUnsignedByte()){
                    val note=f.readUnsignedByte();bytes(32)
                    val legacyRate=java.lang.Short.reverseBytes(f.readShort()).toInt() and 65535
                    f.readUnsignedByte();val channels=f.readUnsignedByte().let{if(it in 1..2)it else 2}
                    val rate=int().let{if(it!=0)it else if(legacyRate!=0)legacyRate else 48000}
                    val waveIndex=int();val frames=count(Int.MAX_VALUE);int()
                    val gain=int();val sectionPan=int();val tune=int();val start=int()
                    val loop=f.readUnsignedByte();skip(3);val loopStart=int();val loopEnd=int();skip(16)
                    val length=frames.toLong()*channels*2
                    require(length<=Int.MAX_VALUE && note in 0..127 && rate>0)
                    val wave=if(waveIndex==0){ImportedWave(f.filePointer,length.toInt()).also{skip(length)}}
                        else waves[waveIndex-1] ?: error("Missing wave $waveIndex")
                    require(wave.bytes==length.toInt()){"Wave length mismatch"}
                    ImportedSection(note,rate,channels,frames,gain,sectionPan,tune,start,loop,loopStart,loopEnd,wave)
                }
                ImportedSound(name,envelope,sections)
            }
            ImportedInstrument(metadata.firstOrNull().orEmpty(),volume,pan,type,sounds)
        }
    }
}
