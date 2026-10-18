package com.hovait.orgx

import java.io.File
import kotlin.math.sin
import org.junit.Assert.*
import org.junit.Test

class StyleVolumeShakeTest {
    @Test fun tracksSharingPcmKeepDifferentExpressionAndPedalSettings(){
        val file=File.createTempFile("style-shake",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val section=ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))
            val instrument=ImportedInstrument("Voice",100,0,0,listOf(ImportedSound("PCM",listOf(0,0,100,10,0,0),listOf(section))))
            val group=ImportedStyleGroup(1,0,"Main",32,true,0,1000,120)
            val event=ReferenceDrumEvent(2,1,60,100,0,1000)
            val shake=ImportedExpressionEffect(3,true,0,true,false,0,100,20,10,emptyList())
            val plain=ImportedTrack("Plain",1,0,false,listOf(event))
            val modulated=plain.copy(name="Shake",events=listOf(ReferenceDrumEvent(12,0,0,0,0,0),event),effects=listOf(shake))
            val index=ImportedSetIndex(listOf(instrument),listOf(group),listOf(plain,modulated))
            val player=ImportedStylePlayer.load(file,index,group,true)
            try{
                repeat(480){player.next(0,120)}
                assertEquals(.25*(2+sin(1.0)*.1),player.left,1e-9)
                assertEquals(.25*(2+sin(1.0)*.3),player.right,1e-9)
                player.reset();player.next(0,120)
                assertEquals(.5,player.left,1e-9)
            }finally{player.close()}
        }finally{file.delete()}
    }
}
