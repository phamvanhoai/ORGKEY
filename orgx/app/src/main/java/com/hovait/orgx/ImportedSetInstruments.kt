package com.hovait.orgx

import java.io.File
import java.io.RandomAccessFile

data class ImportedWave(val offset:Long,val bytes:Int)
data class ImportedSection(val note:Int,val rate:Int,val channels:Int,val frames:Int,val volume:Int,
    val pan:Int,val tune:Int,val start:Int,val loop:Int,val loopStart:Int,val loopEnd:Int,val wave:ImportedWave)
data class ImportedSound(val name:String,val envelope:List<Int>,val sections:List<ImportedSection>)
data class ImportedFxModule(val type:Int,val enabled:Boolean,val realtime:Boolean,val payload:List<Int>)
data class ImportedChannelSettings(val volume:Int=100,val pan:Int=0,val tune:Int=0,val transpose:Int=0,val octave:Int=0,val releasePercent:Int=100,
    val fxEnabled:Boolean=false,val modules:List<ImportedFxModule> = emptyList())
data class ImportedMode(val name:String,val sound:Int,val aftertouch:Int,val mix:List<Int>,val mixVolume:List<Int>,val mixPan:List<Int>)
data class ImportedInstrument(val name:String,val volume:Int,val pan:Int,val type:Int,val sounds:List<ImportedSound>,val modes:List<ImportedMode> = emptyList())
data class ImportedStyleGroup(val id:Int,val parent:Int,val name:String,val section:Int,val loop:Boolean,val start:Int,val end:Int,val tempo:Int,
    val type:Int=1,val limit:Boolean=true,val jumpGroup:Int=-1,val meterX:Int=4,val meterY:Int=4,val settings:ImportedChannelSettings=ImportedChannelSettings(),
    val chordType:Int=0,val chordBase:Int=0)
data class ImportedTrack(val name:String,val group:Int,val instrument:Int,val muted:Boolean,val events:List<ReferenceDrumEvent>,val chordRoot:Int=0,val chordType:Int=0,
    val kind:Int=0,val solo:Boolean=false,val volume:Int=100,val pan:Int=0,val system:Int=0,val settings:ImportedChannelSettings=ImportedChannelSettings())
data class ImportedSystem(val name:String,val startKey:Int,val selectedKeys:Int,val table:List<Double>)
data class ImportedSetIndex(val instruments:List<ImportedInstrument>,val styles:List<ImportedStyleGroup>,val tracks:List<ImportedTrack> = emptyList(),val systems:List<ImportedSystem> = emptyList(),
    val musicSettings:ImportedChannelSettings=ImportedChannelSettings(),val waveSettings:ImportedChannelSettings=ImportedChannelSettings(),val rhythmSettings:ImportedChannelSettings=ImportedChannelSettings())

/** Bounded, lazy PCM indexing for Music Studio 1.4.6; sample bytes stay on disk. */
object ImportedSetInstruments {
    fun read(file:File):List<ImportedInstrument> = parse(file,false).instruments
    fun index(file:File):ImportedSetIndex = parse(file,true)
    private fun parse(file:File,includeStyles:Boolean):ImportedSetIndex = RandomAccessFile(file,"r").use { f->
        fun skip(size:Long){require(size>=0 && size<=f.length()-f.filePointer){"Truncated SET"};f.seek(f.filePointer+size)}
        fun bytes(size:Int):ByteArray{require(size>=0 && size.toLong()<=f.length()-f.filePointer);return ByteArray(size).also(f::readFully)}
        fun int()=Integer.reverseBytes(f.readInt())
        fun count(max:Int):Int=int().also{require(it in 0..max)}
        fun text():String=bytes(count(1000000)).toString(Charsets.UTF_8)
        var lastGroup=0
        fun common():List<String>{val metadata=text().split('\r');lastGroup=int();return metadata}
        fun fx():ImportedChannelSettings{
            val volume=int();val pan=int();val tune=int();int();val transpose=int();val octave=int();val release=int().let{if(it==0)100 else it}
            skip(19);val fxEnabled=f.readBoolean();val modules=count(1000)
            val extra=mapOf(1 to 8,2 to 17,4 to 8,6 to 8,8 to 28,9 to 4,10 to 4,15 to 4,16 to 4,17 to 4,20 to 8,21 to 24,23 to 9,29 to 8,31 to 4)
            val decoded=List(modules){val type=int();val enabled=f.readBoolean();skip(2);val realtime=f.readBoolean()
                val payload=if(type==7)bytes(count(128)*4).map{it.toInt() and 255}
                    else bytes(extra[type] ?: error("Unsupported SET FX $type")).map{it.toInt() and 255}
                ImportedFxModule(type,enabled,realtime,payload)
            }
            return ImportedChannelSettings(volume,pan,tune,transpose,octave,release,fxEnabled,decoded)
        }
        require(bytes(32).toString(Charsets.US_ASCII).substringBefore('\u0000').equals("Music Studio Music",true))
        val musicVersion=common().getOrNull(1)
        require(musicVersion in listOf("1.2.8","1.4.6")){"Unsupported SET instrument layout: $musicVersion"}
        repeat(3){text()};val metadataLength=count(1000000)
        require(metadataLength==0 || musicVersion=="1.2.8"){"Unsupported protected metadata layout"}
        skip(metadataLength.toLong());skip(2+12+116L)
        val slots=count(100000);val waves=HashMap<Int,ImportedWave>()
        repeat(count(slots)){val index=int();val length=count(Int.MAX_VALUE)
            require(index in 0 until slots && index !in waves)
            waves[index]=ImportedWave(f.filePointer,length);skip(length.toLong())
        }
        val musicSettings=fx();val waveSettings=fx();val rhythmSettings=fx();common()
        val instruments=List(count(10000)){
            val metadata=common();val version=metadata.getOrNull(1)
            require(version in listOf("1.2.8","1.4.6","1.4.2")){"Unsupported instrument version $version"}
            val legacy=version=="1.2.8"
            skip(52)
            val legacyAds=if(legacy){skip(12);List(16){List(3){f.readUnsignedByte()}}}else{skip(60);emptyList()}
            val volume=int();val pan=int();val type=f.readUnsignedByte();skip(2)
            val modes=List(f.readUnsignedByte()){mode->
                val modeName=if(!legacy || mode!=0)text() else "Default mode"
                val selectedSound=int();val aftertouch=int();val mix=List(8){f.readByte().toInt()}
                fx();skip(320)
                val mixVolume=List(8){f.readByte().toInt()};val mixPan=List(8){f.readByte().toInt()}
                if(!legacy)text()
                if(version=="1.4.6"){
                    val settings=count(10000);repeat(settings){text()}
                    if(settings>0)repeat(count(10000)){text()}
                }
                ImportedMode(modeName,selectedSound,aftertouch,mix,mixVolume,mixPan)
            }
            // Instrument.ReadFromStream resets legacy ADS before 1.3.1; stored bytes are not the effective ADS.
            val effectiveLegacyAds=legacyAds.map{listOf(0,0,100)}
            val firstEnvelope=if(legacy)effectiveLegacyAds[0]+List(3){f.readUnsignedByte()} else emptyList()
            val sounds=List(f.readUnsignedByte().coerceAtLeast(1)){soundIndex->
                val name=if(legacy && soundIndex==0)"Primary" else text()
                val envelope=if(legacy && soundIndex==0)firstEnvelope else if(legacy)effectiveLegacyAds[soundIndex]+List(3){f.readUnsignedByte()} else List(6){f.readUnsignedByte()}
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
            ImportedInstrument(metadata.firstOrNull().orEmpty(),volume,pan,type,sounds,modes)
        }
        if(!includeStyles)return@use ImportedSetIndex(instruments,emptyList(),musicSettings=musicSettings,waveSettings=waveSettings,rhythmSettings=rhythmSettings)
        common();val systems=List(count(10000)){
            val metadata=common();val startKey=f.readByte().toInt();val selectedKeys=int();skip(28)
            val table=List(24){java.lang.Double.longBitsToDouble(java.lang.Long.reverseBytes(f.readLong()))}
            require(table.all{it.isFinite()}){"Invalid SET tuning table"}
            ImportedSystem(metadata.firstOrNull().orEmpty(),startKey,selectedKeys,table)
        }
        common()
        fun sts(){
            skip(1);skip(f.readUnsignedByte().toLong());skip(16)
            skip(1);val chord=f.readUnsignedByte();if(chord!=0)skip(24)
            fx();skip(320)
        }
        val tracks=List(count(100000)){
            val metadata=common();val trackGroup=lastGroup
            f.readUnsignedByte();val kind=f.readByte().toInt();skip(2);val system=int();val instrument=int();skip(1);val solo=f.readBoolean();val muted=f.readBoolean()
            skip(5);val chordRoot=f.readUnsignedByte();val chordType=f.readUnsignedByte();skip(106)
            val stored=count(10000);val number=if(musicVersion!!<"1.2.9")13 else stored
            if(trackGroup==0 || musicVersion<"1.2.8"){
                val enabled=List(number){f.readBoolean()};enabled.filter{it}.forEach{sts()}
            }
            skip(23);val channelFx=fx();skip(320)
            val events=List(count(2000000)){
                val type=java.lang.Short.reverseBytes(f.readShort()).toInt()
                val tune=java.lang.Short.reverseBytes(f.readShort()).toInt()
                val id=int();val note=int();val touch=int();val time=int();val length=int()
                require(type!=101){"Embedded wave events in SET tracks are not supported yet"}
                ReferenceDrumEvent(type,id,note,touch,time.toLong(),length.toLong(),tune)
            }
            ImportedTrack(metadata.firstOrNull().orEmpty(),trackGroup,instrument,muted,events,chordRoot,chordType,kind,solo,channelFx.volume,channelFx.pan,system,channelFx)
        }
        val styles=List(count(100000)){
            val id=int();val parent=int();val name=bytes(32).drop(1).takeWhile{it!=0.toByte()}.toByteArray().toString(Charsets.UTF_8)
            val section=f.readUnsignedByte();val type=f.readUnsignedByte();val loop=f.readBoolean();val limit=f.readBoolean()
            val start=int();val rawEnd=int();val jump=int();skip(4);val chordType=f.readByte().toInt();val chordBase=f.readByte().toInt()
            val end=if(limit && start>=rawEnd)Math.addExact(start,1000) else rawEnd
            val tempo=java.lang.Short.reverseBytes(f.readShort()).toInt()
            skip(6);val storedX=f.readUnsignedByte();val storedY=f.readUnsignedByte()
            val meterX=if(musicVersion!!<"1.4.8")storedY shr 4 else storedX
            val meterY=if(musicVersion<"1.4.8")storedY and 15 else storedY
            if(musicVersion!!>="1.4.6")skip(16)
            val settings=if(musicVersion>="1.4.2")fx() else ImportedChannelSettings()
            ImportedStyleGroup(id,parent,name,section,loop,start,end,if(musicVersion<"1.3.0")tempo*120/100 else tempo,type,limit,jump,meterX,meterY,settings,chordType,chordBase)
        }
        ImportedSetIndex(instruments,styles,tracks,systems,musicSettings,waveSettings,rhythmSettings)
    }
}
