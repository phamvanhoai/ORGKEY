package com.hovait.orgx

import sofeh.audio.Lame
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object Mp3Exporter {
    @Synchronized fun export(wav:File,mp3:File):File{
        BufferedInputStream(FileInputStream(wav),131072).use{input->
            val metadata=WavFile.inspect(wav);val channels=metadata.channels;val rate=metadata.sampleRate
            var skipped=0L;while(skipped<metadata.dataOffset){val amount=input.skip(metadata.dataOffset-skipped);if(amount>0)skipped+=amount else{require(input.read()>=0){"Incomplete WAV"};skipped++}}
            val frameBytes=channels*2;val frames=1152;val pcm=ByteArray(frames*frameBytes);val left=ShortArray(frames);val right=ShortArray(frames);val encoded=ByteArray(16384)
            mp3.parentFile?.mkdirs();Lame.init(rate,2,rate,192,2)
            try{BufferedOutputStream(FileOutputStream(mp3),131072).use{output->
                var remaining=metadata.dataSize
                while(remaining>0){var total=0;val wanted=minOf(pcm.size.toLong(),remaining).toInt();while(total<wanted){val read=input.read(pcm,total,wanted-total);if(read<0)break;total+=read};if(total<=0)break;remaining-=total
                    val count=total/frameBytes;for(i in 0 until count){val at=i*frameBytes;left[i]=((pcm[at].toInt() and 255) or (pcm[at+1].toInt() shl 8)).toShort();right[i]=if(channels==1)left[i] else ((pcm[at+2].toInt() and 255) or (pcm[at+3].toInt() shl 8)).toShort()}
                    val size=Lame.encode(left,right,count,encoded);check(size>=0){"MP3 encode failed: $size"};if(size>0)output.write(encoded,0,size);if(total<pcm.size)break
                };val size=Lame.flush(encoded);check(size>=0){"MP3 flush failed: $size"};if(size>0)output.write(encoded,0,size)
            }}catch(error:Throwable){mp3.delete();throw error}finally{Lame.close()}
        };require(mp3.length()>0){"Empty MP3"};return mp3
    }
}
