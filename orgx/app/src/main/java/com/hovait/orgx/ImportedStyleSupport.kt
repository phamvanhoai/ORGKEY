package com.hovait.orgx

object ImportedStyleSupport {
    fun drumTracks(index:ImportedSetIndex,group:ImportedStyleGroup)=index.tracks.filter{
        it.group==group.id && !it.muted && it.events.any{event->event.type==2} &&
            index.instruments.getOrNull(it.instrument)?.type==1
    }
    fun reason(index:ImportedSetIndex,group:ImportedStyleGroup):String? {
        val tracks=drumTracks(index,group)
        if(tracks.isEmpty())return "No supported Drum track found"
        if(group.start<0 || group.end<=group.start)return "Unsupported loop boundaries"
        if(tracks.any{!ImportedInstrumentLoader.supported(ImportedInstrumentLoader.forTrack(index.instruments[it.instrument],it.events))})return "Used drum samples contain unsupported parameters"
        if(tracks.any{track->track.events.any{it.type !in 1..2 || it.note !in 0..127}})return "Track contains unsupported events"
        return null
    }
}
