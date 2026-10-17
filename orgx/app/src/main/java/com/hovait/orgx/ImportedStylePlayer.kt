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
        data class SoundKey(val instrument:Int,val type:Int,val system:Int,val settings:ImportedChannelSettings)
        fun key(track:ImportedTrack)=SoundKey(track.instrument,checkNotNull(ImportedStyleSupport.instrument(index,track)).type,track.system,track.settings)
        val selected=tracks.groupBy(::key).mapValues{(_,parts)->
            val source=checkNotNull(ImportedStyleSupport.instrument(index,parts.first()))
            if(follow && source.type==0){
                require(parts.all{it.chordRoot in 0..127 && checkNotNull(transposer).supports(it.chordType)}){"Unsupported source chord metadata"}
                source
            } else ImportedInstrumentLoader.forTrack(source,parts.flatMap{ImportedStyleSupport.sampleEvents(it,source.type==0)})
        }
        // PCM decoding retains mono plus stereo buffers; cap total source PCM before allocating.
        require(selected.values.sumOf{source->ImportedInstrumentLoader.layers(source).sumOf{it.sounds.first().sections.sumOf{section->section.wave.bytes.toLong()}}}<=64L*1024*1024){
            "Style exceeds 64 MB PCM budget"
        }
        val sounds=selected.mapValues{(key,instrument)->ImportedInstrumentLoader.load(file,instrument,index.systems.getOrNull(key.system),key.settings)}
        return groups.associate{group->group.id to ReferenceRhythmPlayer.combine(byGroup.getValue(group.id).map{track->
            val sound=sounds.getValue(key(track))
            val melodic=checkNotNull(ImportedStyleSupport.instrument(index,track)).type==0
            val timeline=ReferenceDrumTrack(track.name,group.id,group.parent,group.section,group.tempo,
                group.loop,if(group.limit)group.start.toLong() else 0L,group.end.toLong(),false,track.events,bounded=group.limit)
            ReferenceRhythmPlayer(listOf(timeline),sound::durationFrames,
                {note,age,channel->sound.sample(note,age.toLong(),channel)*track.volume/100.0*
                    (if(channel==0)minOf(100,100-track.pan) else minOf(100,100+track.pan))/100.0},48000,
                accompaniment=melodic,
                mapNote={note->
                    val target=if(follow && melodic)targetChord?.invoke() else null
                    val mapped=if(target==null)note else checkNotNull(transposer).note(note,track.chordRoot,track.chordType,target)
                    ImportedStyleSupport.shiftedNote(mapped,track,melodic)
                },sampled=sound,channelGain={channel->track.volume/100.0*(if(channel==0)minOf(100,100-track.pan) else minOf(100,100+track.pan))/100.0})
        })}
    }
}
