package com.hovait.orgx

import java.io.File

/** Optional manual chord mapping; DNC is not applied. */
object ImportedStylePlayer {
    fun load(file:File,index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean=false,
        transposer:ReferenceChordTransposer?=null,targetChord:(()->Int?)?=null):ReferenceRhythmPlayer {
        val follow=targetChord!=null
        require(!follow || (includeAccompaniment && transposer!=null)){"Chord mapping requires accompaniment and chord table"}
        require(ImportedStyleSupport.reason(index,group,includeAccompaniment,follow)==null){
            ImportedStyleSupport.reason(index,group,includeAccompaniment,follow).orEmpty()
        }
        val tracks=ImportedStyleSupport.playbackTracks(index,group,includeAccompaniment)
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
        return ReferenceRhythmPlayer.combine(tracks.map{track->
            val sound=sounds.getValue(track.instrument)
            val timeline=ReferenceDrumTrack(track.name,group.id,group.parent,group.section,group.tempo,
                group.loop,group.start.toLong(),group.end.toLong(),false,track.events)
            ReferenceRhythmPlayer(listOf(timeline),sound::durationFrames,
                {note,age,channel->sound.sample(note,age.toLong(),channel)},48000,
                accompaniment=index.instruments[track.instrument].type==0,
                mapNote={note->
                    val target=if(follow && index.instruments[track.instrument].type==0)targetChord?.invoke() else null
                    if(target==null)note else checkNotNull(transposer).note(note,track.chordRoot,track.chordType,target)
                })
        })
    }
}
