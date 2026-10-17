package com.hovait.orgx

import java.io.File
import org.junit.Test
import org.junit.Assert.*

class LocalStyleCompatibilityTest {
    @Test fun reportLocalSetCompatibility(){
        val file=System.getProperty("orgx.legacySet")?.let(::File) ?: return
        val index=ImportedSetInstruments.index(file)
        val report=buildString {
            appendLine("Track channel data: "+index.tracks.groupingBy{"kind=${it.kind}, volume=${it.volume}, pan=${it.pan}, solo=${it.solo}"}.eachCount())
            appendLine("Instrument modes: ${index.instruments.sumOf{it.modes.size}}; enabled mix slots: ${index.instruments.sumOf{instrument->instrument.modes.sumOf{mode->mode.mix.count{it>=0}}}}")
            for(style in ImportedStyleCatalog.styles(index)){
                appendLine(style.name)
                for(part in style.sections){
                    val reason=ImportedStyleSupport.reason(index,part,true,true)
                    appendLine("  ${ImportedStyleCatalog.label(part.section)}: ${reason ?: "OK"}")
                    if(reason!=null)for(track in ImportedStyleSupport.playbackTracks(index,part,true)){
                        val inst=index.instruments[track.instrument]
                        val selected=if(inst.type==0)inst.copy(sounds=inst.sounds.take(1)) else ImportedInstrumentLoader.forTrack(inst,track.events)
                        if(!ImportedInstrumentLoader.supported(selected))appendLine("    ${track.name}: ${inst.name} type=${inst.type} pitch=${selected.sounds.firstOrNull()?.envelope?.getOrNull(4)} start=${selected.sounds.flatMap{it.sections}.count{it.start!=0}}")
                    }
                }
            }
        }
        File(file.parentFile,"style-compatibility.txt").writeText(report)
        val transposer=ReferenceChordTransposer.fromTable(File(requireNotNull(System.getProperty("orgx.referenceAssets")),"Chord").readText())
        for(style in ImportedStyleCatalog.styles(index)){
            assertTrue("No Main: ${style.name}",style.sections.any{it.section in 32..35 && ImportedStyleSupport.reason(index,it,true,true)==null})
            val session=ImportedStylePlayer.loadStyle(file,index,style,true,transposer){0}
            for(part in style.sections){
                assertTrue("Missing ${style.name} ${part.section}: ${ImportedStyleSupport.reason(index,part,true,true)}",session.available(part.id))
                session.press(part.id,false,false)
                repeat(4800){session.next(120);assertTrue(session.player.left.isFinite() && session.player.right.isFinite())}
            }
        }
    }
}
