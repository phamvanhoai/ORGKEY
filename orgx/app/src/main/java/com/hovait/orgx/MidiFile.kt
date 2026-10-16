package com.hovait.orgx

import java.io.InputStream

data class MidiNoteEvent(val tick:Long,val note:Int,val velocity:Int,val on:Boolean,val channel:Int=0,val program:Int=0)
data class MidiSong(val events:List<MidiNoteEvent>,val division:Int,val tempoMicros:Int)

object MidiFile {
    fun read(input:InputStream):MidiSong{
        val data=input.readBytes();var p=0
        fun requireBytes(count:Int){require(p+count<=data.size){"Truncated MIDI"}}
        fun text(count:Int):String{requireBytes(count);return String(data,p,count,Charsets.US_ASCII).also{p+=count}}
        fun u8():Int{requireBytes(1);return data[p++].toInt() and 255}
        fun u16():Int=(u8() shl 8) or u8()
        fun u32():Int=(u8() shl 24) or (u8() shl 16) or (u8() shl 8) or u8()
        fun variable(end:Int):Long{var value=0L;repeat(4){require(p<end);val b=u8();value=(value shl 7) or (b and 127).toLong();if(b and 128==0)return value};return value}
        require(text(4)=="MThd");val header=u32();require(header>=6);val format=u16();val tracks=u16();val division=u16();require(format in 0..1&&division and 0x8000==0&&tracks>0);p+=header-6
        val notes=mutableListOf<MidiNoteEvent>();var tempo=500_000
        data class Program(val tick:Long,val channel:Int,val value:Int)
        val programs=mutableListOf<Program>()
        repeat(tracks){
            require(text(4)=="MTrk");val length=u32();val end=p+length;require(end<=data.size);var tick=0L;var running=0
            while(p<end){tick+=variable(end);var status=u8();if(status<0x80){require(running!=0);p--;status=running}else if(status<0xf0)running=status
                when{
                    status==0xff->{val type=u8();val size=variable(end).toInt();require(p+size<=end);if(type==0x51&&size==3&&tempo==500_000)tempo=(u8() shl 16) or (u8() shl 8) or u8() else p+=size}
                    status==0xf0||status==0xf7->{p+=variable(end).toInt()}
                    status and 0xf0==0x80->{val note=u8();u8();notes+=MidiNoteEvent(tick,note,0,false,status and 15)}
                    status and 0xf0==0x90->{val note=u8();val velocity=u8();notes+=MidiNoteEvent(tick,note,velocity,velocity>0,status and 15)}
                    status and 0xf0 in setOf(0xa0,0xb0,0xe0)->{u8();u8()}
                    status and 0xf0==0xc0->{val program=u8();require(program<128);programs+=Program(tick,status and 15,program)}
                    status and 0xf0==0xd0->u8()
                    else->error("Unsupported MIDI event")
                }
            };p=end
        }
        require(notes.isNotEmpty()){"MIDI has no notes"};val orderedPrograms=programs.sortedBy{it.tick}
        val channelPrograms=IntArray(16);var programIndex=0
        val mapped=notes.sortedBy{it.tick}.map{note->
            while(programIndex<orderedPrograms.size && orderedPrograms[programIndex].tick<=note.tick){val program=orderedPrograms[programIndex++];channelPrograms[program.channel]=program.value}
            note.copy(program=channelPrograms[note.channel])
        };return MidiSong(mapped,division,tempo)
    }
}
