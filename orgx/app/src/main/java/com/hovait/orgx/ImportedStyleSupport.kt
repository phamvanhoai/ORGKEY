package com.hovait.orgx

object ImportedStyleSupport {
    /** Track.ReadFromStream selects mode first, then restores Channels FX over it. */
    fun channelSettings(index:ImportedSetIndex,track:ImportedTrack)=
        ImportedMusicParameters.pitch(track.settings.copy(volume=track.volume,pan=track.pan),index.musicSettings,index.rhythmSettings.octave)
    fun shiftedNote(note:Int,track:ImportedTrack,melodic:Boolean):Int =
        if(melodic)(note.toLong()+track.settings.transpose+track.settings.octave.toLong()*12).coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt() else note
    fun sampleEvents(track:ImportedTrack,melodic:Boolean)=track.events.map{event->
        if(event.type==2)event.copy(note=shiftedNote(event.note,track,melodic)) else event
    }
    /** Tracks.GroupPlayNext applies kind before rhythm playback. */
    fun instrument(index:ImportedSetIndex,track:ImportedTrack):ImportedInstrument? =
        index.instruments.getOrNull(track.instrument)?.let{source->
            when {
                track.kind==1 && source.type==0->setType(source,2)
                track.kind>1 && source.type==2->setType(source,0)
                else->source
            }
        }
    private fun setType(source:ImportedInstrument,type:Int)=source.copy(type=type,sounds=source.sounds.map{sound->
        sound.copy(envelope=sound.envelope.mapIndexed{index,value->if(index==4)if(type==0)0 else 2 else value})
    })
    fun activeTracks(index:ImportedSetIndex,group:ImportedStyleGroup):List<ImportedTrack>{
        val tracks=index.tracks.filter{it.group==group.id}
        val solo=tracks.any{it.solo}
        return tracks.filter{(!it.muted || it.events.any{event->event.type==14}) && (!solo || it.solo) && it.events.any{event->event.type==2}}
    }
    fun drumTracks(index:ImportedSetIndex,group:ImportedStyleGroup)=activeTracks(index,group).filter{
            instrument(index,it)?.type in 1..2
    }
    fun playbackTracks(index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean)=
        if(!includeAccompaniment)drumTracks(index,group) else activeTracks(index,group)
    fun reason(index:ImportedSetIndex?,group:ImportedStyleGroup,includeAccompaniment:Boolean=false,followChord:Boolean=false):String? {
        if(index==null)return "SET index unavailable"
        if(group.type!=1)return "Unsupported style group type"
        val tracks=playbackTracks(index,group,includeAccompaniment)
        if(index.systems.isNotEmpty() && tracks.any{it.system !in index.systems.indices})return "Track references missing tuning system"
        if(tracks.isEmpty())return "No supported tracks found"
        if(group.limit && (group.start<0 || group.end<=group.start))return "Unsupported loop boundaries"
        if(followChord && tracks.any{track->instrument(index,track)?.let{
            it.type==0 && ImportedInstrumentLoader.layers(it).any{layer->layer.sounds.firstOrNull()?.envelope?.getOrNull(4)!=0}
        }==true})return "Chord following requires pitched sample mapping"
        if(tracks.any{track->instrument(index,track)?.let{
            ImportedInstrumentLoader.supported(if(followChord && it.type==0)it else ImportedInstrumentLoader.forTrack(it,sampleEvents(track.copy(settings=channelSettings(index,track)),it.type==0)))
        }!=true})return "Used track samples contain unsupported parameters"
        if(tracks.any{track->track.events.any{it.type !in listOf(1,2,13,14) || (it.type==2 && it.note !in 0..127)}})return "Track contains unsupported events"
        return null
    }
}
