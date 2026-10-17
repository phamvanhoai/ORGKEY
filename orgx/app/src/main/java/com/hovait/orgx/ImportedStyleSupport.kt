package com.hovait.orgx

object ImportedStyleSupport {
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
        return tracks.filter{!it.muted && (!solo || it.solo) && it.events.any{event->event.type==2}}
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
            ImportedInstrumentLoader.supported(if(followChord && it.type==0)it else ImportedInstrumentLoader.forTrack(it,track.events))
        }!=true})return "Used track samples contain unsupported parameters"
        if(tracks.any{track->track.events.any{it.type !in 1..2 || it.note !in 0..127}})return "Track contains unsupported events"
        if(tracks.any{track->track.events.any{it.type==2 && it.tune!=0}})return "Track note tuning is not supported yet"
        return null
    }
}
