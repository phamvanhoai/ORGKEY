package com.hovait.orgx

import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import org.junit.Assert.*
import org.junit.Test

class StylePitchShakeIntegrationTest {
    @Test fun rhythmGroupBypassesTimelinePitchShakeAndResetReplaysTheSameAudio(){
        val file=File.createTempFile("style-pitch-shake",".pcm")
        try{
            val pcm=ByteBuffer.allocate(8192).order(ByteOrder.LITTLE_ENDIAN)
            repeat(4096){pcm.putShort((it*4).toShort())};file.writeBytes(pcm.array())
            val section=ImportedSection(60,48000,1,4096,100,0,0,0,2,0,4096,ImportedWave(0,8192))
            val instrument=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(section))))
            val group=ImportedStyleGroup(1,0,"Main",32,true,0,1000,120)
            val notes=listOf(ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(2,2,60,100,15,1000))
            val commands=listOf(ReferenceDrumEvent(65,2,0,1,1,0),ReferenceDrumEvent(65,2,4,100,1,0),
                ReferenceDrumEvent(65,2,5,8192,1,0),ReferenceDrumEvent(65,2,0,0,30,0))
            fun index(events:List<ReferenceDrumEvent>)=ImportedSetIndex(listOf(instrument),listOf(group),
                listOf(ImportedTrack("Lead",1,0,false,events)))
            val changed=ImportedStylePlayer.load(file,index(notes+commands),group,true)
            val plain=ImportedStylePlayer.load(file,index(notes),group,true)
            try{
                val audio=DoubleArray(1920)
                var different=false
                audio.indices.forEach{i->changed.next(0,120);plain.next(0,120);audio[i]=changed.left
                    assertTrue(audio[i].isFinite());if(kotlin.math.abs(changed.left-plain.left)>1e-8)different=true}
                assertFalse("Rhythm channel applied expression excluded by original Channels.Process",different)
                changed.reset()
                audio.forEach{expected->changed.next(0,120);assertEquals(expected,changed.left,1e-12)}
            }finally{changed.close();plain.close()}
        }finally{file.delete()}
    }
}
