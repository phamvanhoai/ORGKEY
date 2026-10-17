package com.hovait.orgx

import java.io.File

/** Original-note audition only: chord recognition/transposition and DNC are not applied. */
object ImportedStylePlayer {
    fun load(file:File,index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean=false):ReferenceRhythmPlayer {
        require(ImportedStyleSupport.reason(index,group,includeAccompaniment)==null){
            ImportedStyleSupport.reason(index,group,includeAccompaniment).orEmpty()
        }
        val tracks=ImportedStyleSupport.playbackTracks(index,group,includeAccompaniment)
        val selected=tracks.groupBy{it.instrument}.mapValues{(instrument,parts)->
            ImportedInstrumentLoader.forTrack(index.instruments[instrument],parts.flatMap{it.events})
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
                accompaniment=index.instruments[track.instrument].type==0)
        })
    }
}
