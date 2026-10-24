package com.hovait.orgx

import java.io.InputStream

data class MidiNoteEvent(val tick:Long,val note:Int,val velocity:Int,val on:Boolean,val channel:Int=0,val program:Int=0,val controller:Int?=null)
data class MidiTempoEvent(val tick:Long,val microsPerQuarter:Int)
data class MidiSong(val events:List<MidiNoteEvent>,val division:Int,val tempoMicros:Int,
    val tempos:List<MidiTempoEvent> = emptyList(),val endTick:Long=events.maxOfOrNull{it.tick} ?: 0L)

object MidiFile {
    fun read(input:InputStream):MidiSong{
        val data=input.readBytes();var p=0;var readEnd=data.size
        fun requireBytes(count:Int){require(count>=0 && p>=0 && p.toLong()+count<=readEnd){"Truncated MIDI"}}
        fun text(count:Int):String{requireBytes(count);return String(data,p,count,Charsets.US_ASCII).also{p+=count}}
        fun u8():Int{requireBytes(1);return data[p++].toInt() and 255}
        fun dataByte():Int=u8().also{require(it<128){"Invalid MIDI data byte"}}
        fun u16():Int=(u8() shl 8) or u8()
        fun u32():Int=(u8() shl 24) or (u8() shl 16) or (u8() shl 8) or u8()
        fun variable(end:Int):Long{var value=0L;repeat(4){require(p<end);val b=u8();value=(value shl 7) or (b and 127).toLong();if(b and 128==0)return value};error("MIDI variable integer exceeds four bytes")}
        require(text(4)=="MThd");val header=u32();require(header>=6);val format=u16();val tracks=u16();val division=u16();require(format in 0..1&&division>0&&division and 0x8000==0&&tracks>0);requireBytes(header-6);p+=header-6
        val notes=mutableListOf<MidiNoteEvent>();var tempo=500_000
        val tempos=mutableListOf<MidiTempoEvent>();var endTick=0L
        data class Program(val tick:Long,val channel:Int,val value:Int,val order:Int)
        val noteOrders=mutableListOf<Int>();var order=0
        val programs=mutableListOf<Program>()
        repeat(tracks){
            require(text(4)=="MTrk");val length=u32();require(length>=0 && p.toLong()+length<=data.size);val end=p+length;readEnd=end;var tick=0L;var running=0
            while(p<end){val eventOrder=order++;tick+=variable(end);var status=u8();if(status<0x80){require(running!=0);p--;status=running}else if(status<0xf0)running=status
                when{
                    status==0xff->{val type=u8();val size=variable(end).toInt();require(p+size<=end);if(type==0x51&&size==3){
                        val value=(u8() shl 16) or (u8() shl 8) or u8();require(value>0)
                        tempos+=MidiTempoEvent(tick,value)
                    } else p+=size}
                    status==0xf0||status==0xf7->{val size=variable(end).toInt();requireBytes(size);p+=size}
                    status and 0xf0==0x80->{val note=dataByte();dataByte();noteOrders+=eventOrder;notes+=MidiNoteEvent(tick,note,0,false,status and 15)}
                    status and 0xf0==0x90->{val note=dataByte();val velocity=dataByte();noteOrders+=eventOrder;notes+=MidiNoteEvent(tick,note,velocity,velocity>0,status and 15)}
                    status and 0xf0==0xb0->{val controller=dataByte();val value=dataByte()
                        if(controller in setOf(1,6,7,10,11,38,64,98,99,100,101,120,121,123)){noteOrders+=eventOrder;notes+=MidiNoteEvent(tick,0,value,false,status and 15,controller=controller)}
                    }
                    status and 0xf0==0xa0->{val note=dataByte();val pressure=dataByte();noteOrders+=eventOrder;notes+=MidiNoteEvent(tick,note,pressure,false,status and 15,controller=-3)}
                    status and 0xf0==0xe0->{val low=dataByte();val high=dataByte();noteOrders+=eventOrder;notes+=MidiNoteEvent(tick,low,high,false,status and 15,controller=-2)}
                    status and 0xf0==0xc0->{val program=dataByte();require(program<128);programs+=Program(tick,status and 15,program,eventOrder)}
                    status and 0xf0==0xd0->{val pressure=dataByte();noteOrders+=eventOrder;notes+=MidiNoteEvent(tick,0,pressure,false,status and 15,controller=-4)}
                    else->error("Unsupported MIDI event")
                }
            };endTick=maxOf(endTick,tick);p=end;readEnd=data.size
        }
        require(notes.any{it.controller==null}){"MIDI has no notes"};val orderedPrograms=programs.sortedBy{it.tick}
        val channelPrograms=IntArray(16);var programIndex=0
        val mapped=notes.withIndex().sortedWith(compareBy<IndexedValue<MidiNoteEvent>>{it.value.tick}.thenBy{noteOrders[it.index]}).map{indexed->
            val note=indexed.value;val noteOrder=noteOrders[indexed.index]
            while(programIndex<orderedPrograms.size && (orderedPrograms[programIndex].tick<note.tick || (orderedPrograms[programIndex].tick==note.tick && orderedPrograms[programIndex].order<noteOrder))){val program=orderedPrograms[programIndex++];channelPrograms[program.channel]=program.value}
            note.copy(program=channelPrograms[note.channel])
        }
        val orderedTempos=tempos.sortedBy{it.tick}
        tempo=orderedTempos.lastOrNull{it.tick==0L}?.microsPerQuarter ?: 500_000
        return MidiSong(MidiSustain.resolve(mapped,endTick),division,tempo,orderedTempos,endTick)
    }
}
