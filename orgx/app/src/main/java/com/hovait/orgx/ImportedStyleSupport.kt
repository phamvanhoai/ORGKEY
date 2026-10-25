package com.hovait.orgx

/** Tracks._PlayGroup: quality limits melodic instrument slots, not their volume. */
internal class ImportedChordQuality(private val quality:()->Int) {
    private var level=2
    private var used=0
    fun beginFrame(){
        level=quality().coerceIn(0,2)
        used=when(level){0->(1 shl 7) or (1 shl 8) or (1 shl 9);1->1 shl 8;else->0}
    }
    fun allows(instrument:Int,melodic:Boolean,muted:Boolean):Boolean {
        if(!melodic || level==2 || instrument !in 0..10 || muted)return true
        val bit=1 shl instrument
        if(used and bit!=0)return false
        used=used or bit
        return true
    }
}

object ImportedStyleSupport {
    /** Tracks.ReadFromStream upgrades every track using the container's version. */
    internal fun restoreLegacyChords(version:String,tracks:List<ImportedTrack>,groups:List<ImportedStyleGroup>):List<ImportedTrack>{
        val parts=version.split('.').map{it.toIntOrNull() ?: 0}
        val boundary=listOf(1,4,8)
        val comparison=(0 until maxOf(parts.size,boundary.size)).asSequence()
            .map{parts.getOrElse(it){0}.compareTo(boundary.getOrElse(it){0})}.firstOrNull{it!=0} ?: 0
        if(comparison>=0)return tracks
        val byId=groups.associateBy{it.id}
        return tracks.map{track->byId[track.group]?.let{group->
            track.copy(chordRoot=group.chordBase,chordType=group.chordType)
        } ?: track}
    }
    /** Tracks.ReadFromStream channel types; distinct from instrument percussion type. */
    fun expressionAllowed(index:ImportedSetIndex,track:ImportedTrack):Boolean {
        val position=index.tracks.indexOf(track)
        val group=index.styles.firstOrNull{it.id==track.group}
        return position>0 && group!=null && group.type!=1 && track.type!=3
    }
    /** Track.ReadFromStream selects mode first, then restores Channels FX over it. */
    fun channelSettings(index:ImportedSetIndex,track:ImportedTrack)=
        ImportedMusicParameters.pitch(track.settings.copy(volume=track.volume,pan=track.pan),index.musicSettings,index.rhythmSettings.octave)
    fun shiftedNote(note:Int,track:ImportedTrack,melodic:Boolean):Int =
        if(melodic)(note.toLong()+track.settings.transpose+track.settings.octave.toLong()*12).coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt() else note
    fun sampleEvents(track:ImportedTrack,melodic:Boolean)=track.events.map{event->
        if(event.type in listOf(2,4))event.copy(note=shiftedNote(event.note,track,melodic)) else event
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
        // Tracks.PlayNext sets _solo=false for ordinary rhythm playback.
        // Stored editor Solo flags must not remove the other style/PAD tracks.
        val solo=group.type!=1 && tracks.any{it.solo}
        return tracks.filter{(!it.muted || it.events.any{event->event.type==14}) && (!solo || it.solo) && it.events.any{event->event.type==2 || event.type==4 || event.type==202 || event.type==231 || event.type==232 || event.type in 241..246}}
    }
    fun drumTracks(index:ImportedSetIndex,group:ImportedStyleGroup)=activeTracks(index,group).filter{
            instrument(index,it)?.type in 1..2 || it.events.any{event->event.type==202 || event.type==231 || event.type==232 || event.type in 241..246}
    }
    fun playbackTracks(index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean)=
        if(!includeAccompaniment)drumTracks(index,group) else activeTracks(index,group)
    fun reason(index:ImportedSetIndex?,group:ImportedStyleGroup,includeAccompaniment:Boolean=false,followChord:Boolean=false):String? {
        if(index==null)return "SET index unavailable"
        if(group.type!=1)return "Unsupported style group type"
        val tracks=playbackTracks(index,group,includeAccompaniment)
        if(index.systems.isNotEmpty() && tracks.any{it.system !in index.systems.indices})return "Track references missing tuning system"
        tracks.firstOrNull{it.instrument !in index.instruments.indices}?.let{
            return "Track '${it.name}' references missing instrument ${it.instrument}"
        }
        tracks.forEach{track->track.events.forEach{event->
            if(event.type !in listOf(1,2,3,4,11,12,13,14,44,45,64,202,211,212,231,232,241,242,243,244,245,246) && !(event.type==5 && event.note in -1..127 && event.touch in 0..127) && !(event.type==41 && event.note in -127..127) && !(event.type==42 && event.note in -10..10) && !(event.type==43 && event.note in -48000..48000) && !(event.type in listOf(46,56) && event.note in -127..127) && !(event.type==51 && event.note>=0) && !(event.type==53 && event.note>0) && !(event.type==52 && event.note in -100..100) && !ImportedExpressionAutomation.supported(event)){
                val detail=if(event.type==65)" (Effect slot ${event.id}, parameter ${event.note})" else ""
                return "Track '${track.name}': unsupported event ${event.type}$detail at time ${event.time}"
            }
            if(event.type in listOf(2,4) && event.note !in 0..127)
                return "Track '${track.name}': invalid note ${event.note} at time ${event.time}"
        }}
        if(group.limit && (group.start<0 || group.end<=group.start))return "Unsupported loop boundaries"
        if(followChord && tracks.any{track->instrument(index,track)?.let{
            it.type==0 && ImportedInstrumentLoader.layers(it).any{layer->layer.sounds.firstOrNull()?.envelope?.getOrNull(4)!=0}
        }==true})return "Chord following requires pitched sample mapping"
        tracks.forEach{track->
            val source=instrument(index,track) ?: return "Track '${track.name}' has no instrument"
            val selected=if(followChord && source.type==0)source else ImportedInstrumentLoader.forTrack(source,sampleEvents(track.copy(settings=channelSettings(index,track)),source.type==0))
            ImportedInstrumentLoader.unsupportedReason(selected)?.let{return "Track '${track.name}' (${source.name}): $it"}
        }
        return null
    }
}
