package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedStylePlayerTest {
    private val section=ImportedSection(60,48000,1,4,100,0,0,0,0,0,0,ImportedWave(0,8))
    private fun instrument(type:Int)=ImportedInstrument("Sound",100,0,type,
        listOf(ImportedSound("Main",listOf(0,0,100,10,0,0),listOf(section))))
    private val group=ImportedStyleGroup(1,0,"Test",32,true,0,1000,120)
    private val event=ReferenceDrumEvent(2,1,60,100,0,100)
    private fun index()=ImportedSetIndex(listOf(instrument(1),instrument(0)),listOf(group),listOf(
        ImportedTrack("Drum",1,0,false,listOf(event)),ImportedTrack("Bass",1,1,false,listOf(event))))

    @Test fun accompanimentPreviewActuallyMixesItsOwnSample() {
        val file=File.createTempFile("style-preview",".pcm")
        try {
            file.writeBytes(byteArrayOf(0,32,0,32,0,32,0,32))
            val drum=ImportedStylePlayer.load(file,index(),group)
            val full=ImportedStylePlayer.load(file,index(),group,true)
            drum.next(0,120);full.next(0,120)
            assertTrue(drum.left>0)
            assertEquals(drum.left*2,full.left,1e-9)
            assertEquals(drum.right*2,full.right,1e-9)
            assertEquals(drum.left,full.mixedLeft(1f,0f),1e-9)
            assertEquals(drum.left,full.mixedLeft(0f,1f),1e-9)
            assertEquals(drum.right*0.75,full.mixedRight(0.25f,0.5f),1e-9)
            assertEquals(0.0,full.mixedLeft(0f,0f),0.0)
            full.reset()
            assertEquals(0.0,full.mixedLeft(1f,1f),0.0)
        } finally {file.delete()}
    }
    @Test fun unsupportedAccompanimentIsExplainedWithoutBlockingDrumMode() {
        val source=index()
        val bad=source.copy(instruments=listOf(instrument(1),instrument(2)))
        assertNull(ImportedStyleSupport.reason(bad,group))
        assertNotNull(ImportedStyleSupport.reason(bad,group,true))
        val muted=bad.copy(tracks=bad.tracks.map{if(it.instrument==1)it.copy(muted=true) else it})
        assertNull(ImportedStyleSupport.reason(muted,group,true))
    }
    @Test fun tuningAndInvalidInstrumentAreNotSilentlyIgnored() {
        val source=index()
        assertNotNull(ImportedStyleSupport.reason(source.copy(tracks=listOf(source.tracks.first().copy(instrument=99))),group,true))
        assertNotNull(ImportedStyleSupport.reason(source.copy(tracks=listOf(source.tracks.first().copy(events=listOf(event.copy(tune=1))))),group))
    }
    @Test fun localSetPreviewCompatibility() {
        val path=System.getProperty("orgx.legacySet") ?: return
        val file=File(path);val index=ImportedSetInstruments.index(file)
        val groups=index.styles.filter{it.section in 16..35}
        println("Accompaniment preview: "+groups.groupingBy{ImportedStyleSupport.reason(index,it,true) ?: "Playable"}.eachCount())
        groups.filter{ImportedStyleSupport.reason(index,it,true)==null}.forEach{group->
            val player=ImportedStylePlayer.load(file,index,group,true)
            var energy=0.0
            val tempo=group.tempo.coerceIn(40,240)
            val frames=kotlin.math.ceil((group.end.toLong()-group.start)*120.0/tempo/1000*48000).toInt()
            assertTrue(frames in 1..48000*120)
            val limiter=StereoPeakLimiter(48000)
            repeat(frames+512){
                player.next(0,tempo)
                assertTrue(player.left.isFinite() && player.right.isFinite())
                energy+=player.left*player.left+player.right*player.right
                limiter.process(player.left,player.right)
                assertTrue(kotlin.math.abs(limiter.left)<=0.980000001 && kotlin.math.abs(limiter.right)<=0.980000001)
            }
            assertTrue("Silent preview ${group.id}",energy>0)
            if(!group.loop)assertTrue(player.finished)
        }
    }
}
