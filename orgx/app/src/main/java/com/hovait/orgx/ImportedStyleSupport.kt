package com.hovait.orgx

object ImportedStyleSupport {
    fun drumTracks(index:ImportedSetIndex,group:ImportedStyleGroup)=index.tracks.filter{
        it.group==group.id && !it.muted && it.events.any{event->event.type==2} &&
            index.instruments.getOrNull(it.instrument)?.type==1
    }
    fun playbackTracks(index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean)=
        if(!includeAccompaniment)drumTracks(index,group) else index.tracks.filter{
            it.group==group.id && !it.muted && it.events.any{event->event.type==2}
        }
    fun reason(index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean=false):String? {
        val tracks=playbackTracks(index,group,includeAccompaniment)
        if(tracks.isEmpty())return "No supported tracks found"
        if(group.start<0 || group.end<=group.start)return "Unsupported loop boundaries"
        if(tracks.any{track->index.instruments.getOrNull(track.instrument)?.let{
            ImportedInstrumentLoader.supported(ImportedInstrumentLoader.forTrack(it,track.events))
        }!=true})return "Used track samples contain unsupported parameters"
        if(tracks.any{track->track.events.any{it.type !in 1..2 || it.note !in 0..127}})return "Track contains unsupported events"
        if(tracks.any{track->track.events.any{it.type==2 && it.tune!=0}})return "Track note tuning is not supported yet"
        return null
    }
}
