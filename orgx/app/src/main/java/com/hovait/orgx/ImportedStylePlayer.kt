package com.hovait.orgx

import java.io.File

/** Prepare style samples and mode scripts; Track channel rules control expression effects. */
object ImportedStylePlayer {
    fun loadPad(file:File,index:ImportedSetIndex,group:ImportedStyleGroup,
        transposer:ReferenceChordTransposer?,keyConnectionMethod:Int=0,
        scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()},
        chordControl:()->Int={0},chordOctaveMove:()->Int={0},chordQuality:()->Int={2},targetChord:()->Int?):ImportedPadSequence {
        val groups=listOf(group)+ImportedStyleCatalog.jumpTargets(index,listOf(group))
        require(groups.size<=64){"PAD jump chain exceeds 64 groups"}
        val byId=groups.associateBy{it.id}
        groups.forEach{current->
            require(current.loop || current.jumpGroup<=0 || current.jumpGroup in byId){"PAD jump target ${current.jumpGroup} is missing"}
        }
        val players=loadSections(file,index,groups,true,transposer,targetChord,keyConnectionMethod,
            scriptOverrides,chordControl,chordOctaveMove,chordQuality)
        return ImportedPadSequence(group.id,byId,players)
    }
    fun loadStyle(file:File,index:ImportedSetIndex,style:ImportedStyleDefinition,includeAccompaniment:Boolean=false,
        transposer:ReferenceChordTransposer?=null,keyConnectionMethod:Int=0,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()},
        chordControl:()->Int={0},chordOctaveMove:()->Int={0},chordQuality:()->Int={2},
        stsChanges:List<Boolean> = ImportedStsPlan.defaultChanges,targetChord:(()->Int?)?=null):ImportedStyleSession {
        val complete=ImportedSectionFallback.complete(index,style)
        val sections=complete.sections.filter{ImportedStyleSupport.reason(index,it,includeAccompaniment,targetChord!=null)==null}
        require(sections.any{it.section in 32..35}){"This style has no supported Main section"}
        val linked=ImportedStyleCatalog.jumpTargets(index,sections)
        val playableLinked=linked.filter{ImportedStyleSupport.reason(index,it,includeAccompaniment,targetChord!=null)==null}
        val players=loadSections(file,index,sections+playableLinked,includeAccompaniment,transposer,targetChord,keyConnectionMethod,scriptOverrides,chordControl,chordOctaveMove,chordQuality,stsChanges)
        return ImportedStyleSession(complete,players,linkedGroups=linked)
    }
    fun load(file:File,index:ImportedSetIndex,group:ImportedStyleGroup,includeAccompaniment:Boolean=false,
        transposer:ReferenceChordTransposer?=null,keyConnectionMethod:Int=0,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()},
        chordControl:()->Int={0},chordOctaveMove:()->Int={0},chordQuality:()->Int={2},targetChord:(()->Int?)?=null):ReferenceRhythmPlayer {
        return loadSections(file,index,listOf(group),includeAccompaniment,transposer,targetChord,keyConnectionMethod,scriptOverrides,chordControl,chordOctaveMove,chordQuality).getValue(group.id)
    }
    private fun loadSections(file:File,index:ImportedSetIndex,groups:List<ImportedStyleGroup>,includeAccompaniment:Boolean,
        transposer:ReferenceChordTransposer?,targetChord:(()->Int?)?,keyConnectionMethod:Int,scriptOverrides:(File,ImportedInstrument)->Map<String,String>,
        chordControl:()->Int,chordOctaveMove:()->Int,chordQuality:()->Int,stsChanges:List<Boolean> = ImportedStsPlan.defaultChanges):Map<Int,ReferenceRhythmPlayer> {
        require(keyConnectionMethod in 0..1)
        val follow=targetChord!=null
        require(!follow || (includeAccompaniment && transposer!=null)){"Chord mapping requires accompaniment and chord table"}
        groups.forEach{group->require(ImportedStyleSupport.reason(index,group,includeAccompaniment,follow)==null){
            ImportedStyleSupport.reason(index,group,includeAccompaniment,follow).orEmpty()
        }}
        val byGroup=groups.associate{it.id to ImportedStyleSupport.playbackTracks(index,it,includeAccompaniment)}
        val tracks=byGroup.values.flatten()
        // Expression belongs to Track/Playback, not immutable decoded PCM.
        // Sharing samples must not share per-note phase or channel automation.
        data class SoundKey(val instrument:Int,val type:Int,val system:Int,val settings:ImportedChannelSettings)
        fun key(track:ImportedTrack)=SoundKey(track.instrument,checkNotNull(ImportedStyleSupport.instrument(index,track)).type,track.system,ImportedStyleSupport.channelSettings(index,track))
        val selected=tracks.groupBy(::key).mapValues{(_,parts)->
            val source=checkNotNull(ImportedStyleSupport.instrument(index,parts.first()))
            if(follow && source.type==0){
                require(parts.all{it.chordRoot in 0..127 && checkNotNull(transposer).supports(it.chordType)}){"Unsupported source chord metadata"}
                source
            } else if(source.type==0 && index.tracks.any{it.events.any{event->event.type==231}})source
            else ImportedInstrumentLoader.forTrack(source,parts.flatMap{ImportedStyleSupport.sampleEvents(it.copy(settings=ImportedStyleSupport.channelSettings(index,it)),source.type==0)})
        }
        // PCM decoding retains mono plus stereo buffers; cap total source PCM before allocating.
        require(selected.values.sumOf{source->ImportedInstrumentLoader.layers(source).sumOf{it.sounds.first().sections.sumOf{section->section.wave.bytes.toLong()}}}<=64L*1024*1024){
            "Style exceeds 64 MB PCM budget"
        }
        val sounds=selected.mapValues{(key,instrument)->ImportedInstrumentLoader.load(file,instrument,index.systems.getOrNull(key.system),key.settings,index.musicSettings,keyConnectionMethod=keyConnectionMethod,sourceVersion=index.sourceVersion,scriptOverrides=scriptOverrides(file,instrument),scriptOverridesForMode=scriptOverrides)}
        return groups.associate{group->
          val quality=ImportedChordQuality(chordQuality)
          val parts=byGroup.getValue(group.id).map{track->
            val sound=sounds.getValue(key(track))
            val melodic=checkNotNull(ImportedStyleSupport.instrument(index,track)).type==0
            val settings=ImportedStyleSupport.channelSettings(index,track)
            val timeline=ReferenceDrumTrack(track.name,group.id,group.parent,group.section,group.tempo,
                group.loop,if(group.limit)group.start.toLong() else 0L,group.end.toLong(),false,track.events,bounded=group.limit)
            ReferenceRhythmPlayer(listOf(timeline),sound::durationFrames,
                {note,age,channel->sound.sample(note,age.toLong(),channel)},48000,
                accompaniment=melodic,
                mapNote={note->
                    val target=if(follow && melodic)targetChord?.invoke() else null
                    if(target!=null && target<0)-1 else{
                    val mapped=if(target==null)note else checkNotNull(transposer).note(note,track.chordRoot,track.chordType,target,
                        control=chordControl(),octaveMove=chordOctaveMove())
                    ImportedStyleSupport.shiftedNote(mapped,track.copy(settings=settings),melodic)
                    }
                },sampled=sound,fx=ImportedFxChain(settings),initiallyMuted=track.muted,initialChannel=settings,
                expressionEffects=ImportedExpressionAutomation.applyLegacyKeyConnectionMethod(track.effects,track.sourceVersion ?: index.sourceVersion,keyConnectionMethod),initialPedal=track.pedal!=0,
                // Tracks.ReadFromStream assigns channel type 1 to rhythm groups
                // and Type 3 tracks. Channels.Process excludes that channel type.
                expressionAllowed=ImportedStyleSupport.expressionAllowed(index,track),keyConnectionMethod=keyConnectionMethod,pedalMode=track.pedalMode,
                storedTranspose=track.settings.transpose,storedOctave=track.settings.octave,
                stsSnapshotResolver={slot->track.sts.firstOrNull{it.slot==slot}?.let{sts->
                    sts.copy(settings=ImportedMusicParameters.pitch(sts.settings,index.musicSettings,index.rhythmSettings.octave))
                }},stsChanges=stsChanges)
                .withChordFollowing(control=chordControl,octaveMove=chordOctaveMove,target=if(follow && melodic)targetChord else null)
                .withTrackGate{muted->quality.allows(track.instrument,melodic,muted)}
          }
          // An empty/muted section still owns its timing and JumpGroup (notably
          // silent Endings). Keep transport semantics without inventing notes.
          val player=if(parts.isEmpty())ReferenceRhythmPlayer(listOf(ReferenceDrumTrack(group.name,group.id,group.parent,
              group.section,group.tempo,group.loop,if(group.limit)group.start.toLong() else 0L,group.end.toLong(),false,
              emptyList(),bounded=group.limit)),{0},{_,_,_->0.0},48000) else ReferenceRhythmPlayer.combine(parts)
          group.id to player.withFrameStart(quality::beginFrame).withGroupFx(ImportedGroupFx.resolve(index,group)).withRhythmSettings(index.rhythmSettings,index.musicSettings)
        }
    }
}
