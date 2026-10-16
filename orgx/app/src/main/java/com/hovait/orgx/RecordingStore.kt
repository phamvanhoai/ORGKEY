package com.hovait.orgx

import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class RecordingStore(
    private val directory:File,
    private val clock:()->Long={System.currentTimeMillis()},
    private val midiWriter:(File,List<RecordedMidiEvent>,Int,Long)->Unit={file,events,bpm,duration->
        FileOutputStream(file).use{MidiRecording.write(it,events,bpm,duration);it.fd.sync()}
    }
) {
    @Synchronized fun save(pcm:ByteArray,events:List<RecordedMidiEvent>,bpm:Int,sampleRate:Int=48000):File {
        require(pcm.isNotEmpty() && pcm.size%4==0 && sampleRate in 8000..192000)
        check(directory.isDirectory || directory.mkdirs()){"Cannot create recording directory"}
        val base="ORGX-${clock()}"
        var name=base;var copy=2
        while(listOf("wav","mid","mp3").any{File(directory,"$name.$it").exists()}){name="$base-${copy++}"}
        val wav=File(directory,"$name.wav");val midi=File(directory,"$name.mid")
        val pending=File(directory,".pending-${UUID.randomUUID()}")
        check(pending.mkdir()){"Cannot create recording staging directory"}
        val stagedWav=File(pending,"audio.wav");val stagedMidi=File(pending,"notes.mid")
        var midiPublished=false
        try {
            FileOutputStream(stagedWav).use{output->
                fun int(value:Int){repeat(4){output.write(value ushr (it*8) and 255)}}
                fun short(value:Int){output.write(value and 255);output.write(value ushr 8 and 255)}
                output.write("RIFF".toByteArray(Charsets.US_ASCII));int(36+pcm.size);output.write("WAVEfmt ".toByteArray(Charsets.US_ASCII))
                int(16);short(1);short(2);int(sampleRate);int(sampleRate*4);short(4);short(16)
                output.write("data".toByteArray(Charsets.US_ASCII));int(pcm.size);output.write(pcm);output.fd.sync()
            }
            WavFile.inspect(stagedWav)
            midiWriter(stagedMidi,events,bpm,pcm.size.toLong()*1_000_000_000/(sampleRate*4))
            check(stagedMidi.isFile && stagedMidi.length()>=26){"Incomplete MIDI recording"}
            check(!midi.exists() && stagedMidi.renameTo(midi)){"Cannot publish MIDI recording"}
            midiPublished=true
            check(!wav.exists() && stagedWav.renameTo(wav)){"Cannot publish WAV recording"}
            return wav
        }catch(error:Throwable){
            if(midiPublished)midi.delete()
            throw error
        }finally{
            stagedWav.delete();stagedMidi.delete();pending.delete()
        }
    }
}
