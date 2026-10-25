package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Test

class ImportedSectionFallbackTest {
    @Test fun stylePreloadsRhythmDestinationsFromItsPadsIncludingSharedPadFallback(){
        val file=File.createTempFile("orgx-pad-rhythm-target",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32)
            val pad=part(2,0,96).copy(loop=false,jumpGroup=3)
            val continuation=part(3,0,97).copy(loop=false,jumpGroup=0)
            val target=part(4,200,33)
            val region=ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))
            val instrument=ImportedInstrument("Kit",100,0,1,listOf(ImportedSound("Sound",listOf(0,0,100,10,0,0),listOf(region))))
            val controls=ImportedTrack("PAD continuation",3,0,false,listOf(ReferenceDrumEvent(201,0,4,0,0,0)))
            val index=ImportedSetIndex(listOf(instrument),listOf(main,pad,continuation,target),listOf(controls))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)))
            val bank=ImportedPadBank()
            val padPlayer=ReferenceRhythmPlayer(listOf(ReferenceDrumTrack("PAD",2,100,96,120,false,0,4000,false,
                listOf(ReferenceDrumEvent(201,0,4,0,100,0)))),{1000},{_,_,_->0.0},48000)
            try{
                assertTrue(session.available(4));assertFalse(session.style.sections.any{it.id==4})
                bank.play(0,padPlayer)
                repeat(48001){session.next(120);session.renderPads(bank,120,1f,1f,0)}
                assertEquals(4,session.currentId);assertNull(session.transitionError)
            }finally{bank.close();session.close()}
        }finally{file.delete()}
    }
    @Test fun storedJumpLoadsAnExternalGroupAndWaitsForTheOneShotToFinish(){
        val file=File.createTempFile("orgx-event-jump",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32)
            val intro=part(2,100,16).copy(jumpGroup=1)
            val target=part(3,200,80).copy(jumpGroup=0)
            val region=ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))
            val instrument=ImportedInstrument("Kit",100,0,1,listOf(ImportedSound("Sample",listOf(0,0,100,10,0,0),listOf(region))))
            val track=ImportedTrack("Intro",2,0,false,listOf(ReferenceDrumEvent(203,0,3,0,100,0)))
            val index=ImportedSetIndex(listOf(instrument),listOf(main,intro,target),listOf(track))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main,intro)))
            try{
                assertTrue(session.available(3));assertFalse(session.style.sections.any{it.id==3})
                session.request(2,true)
                repeat(24000){session.next(120)};assertEquals(2,session.currentId)
                repeat(24001){session.next(120)};assertEquals(3,session.currentId)
            }finally{session.close()}
        }finally{file.delete()}
    }
    @Test fun legacyChordMetadataComesFromOwningGroupUsingMusicContainerVersion(){
        val group=part(1,100,32).copy(chordBase=5,chordType=1)
        val track=ImportedTrack("Notes",1,0,false,emptyList(),chordRoot=0,chordType=0,sourceVersion="1.4.8")
        val orphan=track.copy(group=999)
        val legacy=ImportedStyleSupport.restoreLegacyChords("1.4.6",listOf(track,orphan),listOf(group))
        assertEquals(5,legacy.first().chordRoot);assertEquals(1,legacy.first().chordType)
        assertEquals(orphan,legacy.last())
        for(version in listOf("1.4.8","1.4.10","2.0.0"))
            assertEquals(listOf(track),ImportedStyleSupport.restoreLegacyChords(version,listOf(track),listOf(group)))
        val mapper=ReferenceChordTransposer.fromTable("M 0 4 7\nm 0 3 7")
        // Match the original CalcChordKey pitch-class comparison (including nonzero source root).
        assertEquals(mapper.note(63,5,1,0),mapper.note(63,legacy.first().chordRoot,legacy.first().chordType,0))
        assertNotEquals(mapper.note(63,0,0,0),mapper.note(63,legacy.first().chordRoot,legacy.first().chordType,0))
    }
    @Test fun storedChordTransposesStyleWithoutAnExternalFollowChordCallback(){
        val file=File.createTempFile("orgx-stored-chord",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32,0,64,0,64))
            val main=part(1,100,32)
            fun region(root:Int,offset:Long)=ImportedSection(root,48000,1,2,100,0,0,0,2,0,2,ImportedWave(offset,4))
            val instrument=ImportedInstrument("Piano",100,0,0,listOf(ImportedSound("Sound",listOf(0,0,100,20,0,0),listOf(region(60,0),region(62,4)))))
            val d=(1 shl 2) or (1 shl 6) or (1 shl 9)
            val control=ImportedTrack("Chord",1,0,false,listOf(ReferenceDrumEvent(202,0,d,0,0,0)))
            val notes=ImportedTrack("Notes",1,0,false,listOf(ReferenceDrumEvent(2,1,60,100,1,1000)))
            val index=ImportedSetIndex(listOf(instrument),listOf(main),listOf(control,notes))
            val mapper=ReferenceChordTransposer.fromTable("M 0 4 7")
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)),true,mapper)
            try{repeat(50){session.next(120)};assertEquals(.5,session.player.left,1e-12)}finally{session.close()}
        }finally{file.delete()}
    }
    @Test fun musicKeyMoveOnAControlOnlyTrackChangesAnotherTracksSampleSelection(){
        val file=File.createTempFile("orgx-music-pitch",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32)
            val region=ImportedSection(72,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))
            val instrument=ImportedInstrument("Exact",100,0,0,listOf(ImportedSound("Sample",listOf(0,0,100,20,2,0),listOf(region))))
            val controls=ImportedTrack("Music controls",1,0,false,listOf(ReferenceDrumEvent(231,0,12,0,0,0)))
            val notes=ImportedTrack("Notes",1,0,false,listOf(ReferenceDrumEvent(2,1,60,100,1,1000)))
            val index=ImportedSetIndex(listOf(instrument),listOf(main),listOf(controls,notes))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)),true)
            try{repeat(50){session.next(120)};assertTrue(session.player.left>0)}finally{session.close()}
        }finally{file.delete()}
    }
    @Test fun loadedStyleRecomputesReleaseFromRawSoundAndMusicAfterZeroReleaseSts(){
        val file=File.createTempFile("orgx-release-sts",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32)
            val region=ImportedSection(60,48000,1,2,100,0,0,0,2,0,2,ImportedWave(0,4))
            val instrument=ImportedInstrument("Sound",100,0,1,listOf(ImportedSound("Loop",listOf(0,0,100,20,0,0),listOf(region))))
            val sts=ImportedSts("Restore release",false,0,0,0,0,0,0,0,0,emptyList(),ImportedChannelSettings(releasePercent=100),slot=0)
            val track=ImportedTrack("Main",1,0,false,listOf(ReferenceDrumEvent(64,0,0,0,0,0),
                ReferenceDrumEvent(2,1,60,100,0,1000),ReferenceDrumEvent(1,1,60,0,10,0)),
                settings=ImportedChannelSettings(releasePercent=0),sts=listOf(sts))
            val index=ImportedSetIndex(listOf(instrument),listOf(main),listOf(track),musicSettings=ImportedChannelSettings(releasePercent=50))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)))
            try{
                repeat(2400){session.next(120)}
                // Raw 20 * channel 100% * Music 50% = 10: a 100 ms manual tail.
                assertTrue(session.player.left in .14..0.16)
            }finally{session.close()}
        }finally{file.delete()}
    }
    @Test fun loadedStyleUsesStsPitchVolumeAndPanBeforeOpeningTheNote(){
        val file=File.createTempFile("orgx-sts-track",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32)
            val region=ImportedSection(72,48000,1,2,100,0,0,0,0,0,0,ImportedWave(0,4))
            val instrument=ImportedInstrument("Exact",100,0,0,listOf(ImportedSound("Sound",listOf(0,0,100,10,2,0),listOf(region))))
            val sts=ImportedSts("Snapshot",false,0,0,0,0,0,0,0,0,emptyList(),
                ImportedChannelSettings(volume=40,pan=100,transpose=12),slot=2)
            val track=ImportedTrack("Main",1,0,false,listOf(ReferenceDrumEvent(64,0,2,0,0,0),
                ReferenceDrumEvent(2,1,60,100,0,100)),sts=listOf(sts))
            val index=ImportedSetIndex(listOf(instrument),listOf(main),listOf(track))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)),true)
            try{
                session.next(120)
                assertEquals(0.0,session.player.left,0.0)
                assertEquals(.1,session.player.right,1e-12)
            }finally{session.close()}
        }finally{file.delete()}
    }
    @Test fun styleLoaderAcceptsKeyAftertouchAndTrimsSamplesUsingItsTransposedKey(){
        val file=File.createTempFile("orgx-key-aftertouch",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32)
            val region=ImportedSection(72,48000,1,2,100,0,0,0,0,0,0,ImportedWave(0,4))
            val instrument=ImportedInstrument("Exact",100,0,0,listOf(ImportedSound("Sound",listOf(0,0,100,10,2,0),listOf(region))))
            val track=ImportedTrack("Main",1,0,false,listOf(ReferenceDrumEvent(4,1,60,100,0,100)),
                settings=ImportedChannelSettings(transpose=12))
            val index=ImportedSetIndex(listOf(instrument),listOf(main),listOf(track))
            val settings=ImportedStyleSupport.channelSettings(index,track)
            assertEquals(72,ImportedStyleSupport.sampleEvents(track.copy(settings=settings),true).single().note)
            assertNull(ImportedStyleSupport.reason(index,main,includeAccompaniment=true))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)),true)
            try{session.next(120);assertTrue(session.player.left>0)}finally{session.close()}
        }finally{file.delete()}
    }
    @Test fun catalogEnumeratesDeclaredParentsInSetOrderIncludingFullySharedStyle(){
        fun parent(id:Int,name:String)=ImportedStyleGroup(id,0,name,0,false,0,0,120,type=0)
        val shared=part(1,0,32)
        val intro=part(2,200,16)
        val index=ImportedSetIndex(emptyList(),listOf(parent(100,"Shared"),parent(200,"Local Intro"),
            parent(Int.MIN_VALUE,"Recording"),intro,shared))
        val styles=ImportedStyleCatalog.styles(index)
        assertEquals(listOf(100,200),styles.map{it.id})
        assertEquals(listOf(shared),styles.first().sections)
        assertEquals("Shared",styles.first().name)
        assertEquals(listOf(intro,shared),styles[1].sections)
    }
    @Test fun legacyIndexWithOnlyGroupZeroSectionsStillHasAPlayableStyle(){
        val main=part(1,0,32)
        val styles=ImportedStyleCatalog.styles(ImportedSetIndex(emptyList(),listOf(main)))
        assertEquals(listOf(0),styles.map{it.id})
        assertEquals(listOf(main),styles.single().sections)
    }
    @Test fun catalogKeepsStyleWhoseMainComesFromGroupZero(){
        val parent=ImportedStyleGroup(100,0,"Shared Main Style",0,false,0,0,120,type=0)
        val main=part(1,0,32);val intro=part(2,100,16)
        val index=ImportedSetIndex(emptyList(),listOf(parent,main,intro))
        val style=ImportedStyleCatalog.styles(index).single{it.id==100}
        assertEquals("Shared Main Style",style.name)
        assertEquals(listOf(intro,main),style.sections)
    }
    private fun part(id:Int,parent:Int,section:Int)=ImportedStyleGroup(id,parent,"Part",section,section in 32..35,0,1000,120)
    @Test fun missingSectionUsesGroupZeroWithoutReplacingLocalSection(){
        val main=part(1,100,32);val fill=part(2,0,48);val intro=part(3,100,16);val otherIntro=part(4,0,16)
        val index=ImportedSetIndex(emptyList(),listOf(main,fill,intro,otherIntro,part(5,999,64)))
        val style=ImportedStyleDefinition(100,"Style",listOf(main,intro))
        val result=ImportedSectionFallback.complete(index,style)
        assertEquals(listOf(3,1,2),result.sections.map{it.id})
        assertEquals(result,ImportedSectionFallback.complete(index,result))
    }
    @Test fun fallbackPlayerIsLoadedAndCanBeRequested(){
        val file=File.createTempFile("orgx-fallback",".pcm")
        try{
            file.writeBytes(byteArrayOf(0,32,0,32))
            val main=part(1,100,32);val fill=part(2,0,48)
            val region=ImportedSection(60,48000,1,2,100,0,0,0,0,0,0,ImportedWave(0,4))
            val instrument=ImportedInstrument("Kit",100,0,1,listOf(ImportedSound("Sound",listOf(0,0,100,10,0,0),listOf(region))))
            val events=listOf(ReferenceDrumEvent(2,1,60,100,0,100))
            val index=ImportedSetIndex(listOf(instrument),listOf(main,fill),listOf(ImportedTrack("Main",1,0,false,events),ImportedTrack("Fill",2,0,false,events)))
            val session=ImportedStylePlayer.loadStyle(file,index,ImportedStyleDefinition(100,"Style",listOf(main)))
            assertTrue(session.available(2));session.press(2,false,false);session.next(120)
            assertEquals(2,session.currentId);assertTrue(session.player.left>0)
        }finally{file.delete()}
    }
    @Test fun catalogStyleWithOnlyLocalIntroLoadsSharedMainAndReturnsToIt(){
        val file=File.createTempFile("orgx-shared-main",".pcm")
        try{
            val main=part(1,0,32);val intro=part(2,100,16)
            val index=ImportedSetIndex(emptyList(),listOf(main,intro))
            val definition=ImportedStyleCatalog.styles(index).single{it.id==100}
            val session=ImportedStylePlayer.loadStyle(file,index,definition)
            try{
                assertTrue(session.available(main.id));assertTrue(session.available(intro.id))
                session.press(intro.id,false,false)
                repeat(48001){session.next(120)}
                assertEquals(main.id,session.currentId);assertFalse(session.finished)
            }finally{session.close()}
        }finally{file.delete()}
    }
}
