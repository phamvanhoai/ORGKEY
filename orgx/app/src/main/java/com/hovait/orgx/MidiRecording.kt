package com.hovait.orgx

import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.OutputStream

data class RecordedMidiEvent(val timeNanos:Long,val status:Int,val note:Int,val velocity:Int)

object MidiRecording {
    fun write(output:OutputStream,events:List<RecordedMidiEvent>,tempoBpm:Int,durationNanos:Long){
        val bpm=tempoBpm.coerceIn(40,240)
        val end=durationNanos.coerceAtLeast(0)
        val ordered=events.filter{it.timeNanos<=end}.sortedBy{it.timeNanos}.toMutableList()
        val held=mutableSetOf<Int>()
        val pedals=mutableSetOf<Int>()
        val bentChannels=mutableSetOf<Int>()
        val modulatedChannels=mutableSetOf<Int>()
        val gainControllers=mutableMapOf<Int,Int>()
        ordered.forEach{event->
            val channel=event.status and 15;val key=channel*128+event.note
            when(event.status and 0xf0){
                0x90->if(event.velocity>0)held.add(key)else held.remove(key)
                0x80->held.remove(key)
                0xe0->if((event.note or (event.velocity shl 7))!=MidiPitchBend.CENTER)bentChannels.add(channel)else bentChannels.remove(channel)
                0xb0->when(event.note){
                    1->if(event.velocity!=0)modulatedChannels.add(channel)else modulatedChannels.remove(channel)
                    64->if(event.velocity>=64)pedals.add(channel)else pedals.remove(channel)
                    7,10,11->gainControllers[channel*128+event.note]=event.velocity
                }
            }
        }
        held.sorted().forEach{ordered+=RecordedMidiEvent(end,0x80 or (it/128),it%128,0)}
        pedals.sorted().forEach{ordered+=RecordedMidiEvent(end,0xb0 or it,64,0)}
        bentChannels.sorted().forEach{ordered+=RecordedMidiEvent(end,0xe0 or it,0,64)}
        modulatedChannels.sorted().forEach{ordered+=RecordedMidiEvent(end,0xb0 or it,1,0)}
        gainControllers.toSortedMap().forEach{(key,value)->
            val default=when(key%128){7->MidiChannelGain.DEFAULT_VOLUME;10->MidiPan.CENTER;else->MidiChannelGain.DEFAULT_EXPRESSION}
            if(value!=default)ordered+=RecordedMidiEvent(end,0xb0 or (key/128),key%128,default)
        }
        val bytes=ByteArrayOutputStream()
        DataOutputStream(bytes).use{track->
            fun variable(input:Long){var value=input.coerceAtLeast(0);require(value<=0x0fffffff);var buffer=value and 127;while(value.shr(7).also{value=it}>0){buffer=buffer.shl(8) or ((value and 127) or 128)};while(true){track.writeByte((buffer and 255).toInt());if(buffer and 128L!=0L)buffer=buffer.shr(8)else break}}
            fun tick(nanos:Long)=nanos.coerceAtLeast(0)*bpm*480L/60_000_000_000L
            variable(0);track.writeByte(0xff);track.writeByte(0x51);track.writeByte(3)
            val tempo=60_000_000/bpm;track.writeByte(tempo shr 16);track.writeByte(tempo shr 8);track.writeByte(tempo)
            var previous=0L
            ordered.forEach{event->val now=tick(event.timeNanos);variable(now-previous);previous=now;track.writeByte(event.status);track.writeByte(event.note);track.writeByte(event.velocity)}
            variable(tick(end)-previous);track.writeByte(0xff);track.writeByte(0x2f);track.writeByte(0)
        }
        val writer=DataOutputStream(output)
        writer.writeBytes("MThd");writer.writeInt(6);writer.writeShort(0);writer.writeShort(1);writer.writeShort(480)
        writer.writeBytes("MTrk");writer.writeInt(bytes.size());bytes.writeTo(writer);writer.flush()
    }
}
