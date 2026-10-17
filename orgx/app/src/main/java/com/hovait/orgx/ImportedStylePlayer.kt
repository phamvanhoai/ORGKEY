package com.hovait.orgx

import java.io.File

/** Optional manual chord mapping; DNC is not applied. */
object ImportedStylePlayer {
    fun loadStyle(file:File,index:ImportedSetIndex,style:ImportedStyleDefinition,includeAccompaniment:Boolean=false,
        transposer:ReferenceChordTransposer?=null,targetChord:(()->Int?)?=null):ImportedStyleSession {
        val sections=style.sections.filter{ImportedStyleSupport.reason(index,it,includeAccompaniment,targetChord!=null)==null}
        require(sections.any{it.section in 32..35}){"This style has no supported Main section"}
        return ImportedStyleSession(style,loadSections(file,index,sections,includeAccompaniment,transposer,targetChord))
    }
    fun load(file:File,index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean=false,
        transposer:ReferenceChordTransposer?=null,targetChord:(()->Int?)?=null):ReferenceRhythmPlayer {
        return loadSections(file,index,listOf(group),includeAccompaniment,transposer,targetChord).getValue(group.id)
    }
    private fun loadSections(file:File,index:ImportedSetIndex,groups:List<ImportedStyleGroup>,includeAccompaniment:Boolean,
        transposer:ReferenceChordTransposer?,targetChord:(()->Int?)?):Map<Int,ReferenceRhythmPlayer> {
        val follow=targetChord!=null
        require(!follow || (includeAccompaniment && transposer!=null)){"Chord mapping requires accompaniment and chord table"}
        groups.forEach{group->require(ImportedStyleSupport.reason(index,group,includeAccompaniment,follow)==null){
            ImportedStyleSupport.reason(index,group,includeAccompaniment,follow).orEmpty()
        }}
        val byGroup=groups.associate{it.id to ImportedStyleSupport.playbackTracks(index,it,includeAccompaniment)}
        val tracks=byGroup.values.flatten()
        val selected=tracks.groupBy{it.instrument}.mapValues{(instrument,parts)->
            val source=index.instruments[instrument]
            if(follow && source.type==0){
                require(parts.all{it.chordRoot in 0..127 && checkNotNull(transposer).supports(it.chordType)}){"Unsupported source chord metadata"}
                source.copy(sounds=source.sounds.take(1))
            } else ImportedInstrumentLoader.forTrack(source,parts.flatMap{it.events})
        }
        // PCM decoding retains mono plus stereo buffers; cap total source PCM before allocating.
        require(selected.values.sumOf{it.sounds.first().sections.sumOf{section->section.wave.bytes.toLong()}}<=64L*1024*1024){
            "Style exceeds 64 MB PCM budget"
        }
        val sounds=selected.mapValues{ImportedInstrumentLoader.load(file,it.value)}
        return groups.associate{group->group.id to ReferenceRhythmPlayer.combine(byGroup.getValue(group.id).map{track->
            val sound=sounds.getValue(track.instrument)
            val timeline=ReferenceDrumTrack(track.name,group.id,group.parent,group.section,group.tempo,
                group.loop,if(group.limit)group.start.toLong() else 0L,group.end.toLong(),false,track.events,bounded=group.limit)
            ReferenceRhythmPlayer(listOf(timeline),sound::durationFrames,
                {note,age,channel->sound.sample(note,age.toLong(),channel)},48000,
                accompaniment=index.instruments[track.instrument].type==0,
                mapNote={note->
                    val target=if(follow && index.instruments[track.instrument].type==0)targetChord?.invoke() else null
                    if(target==null)note else checkNotNull(transposer).note(note,track.chordRoot,track.chordType,target)
                },envelope=sound.envelope)
        })}
    }
}
