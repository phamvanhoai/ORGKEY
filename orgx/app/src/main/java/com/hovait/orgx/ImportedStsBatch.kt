package com.hovait.orgx

import java.io.File

/** Resolve Group 0 identities before allocating PCM; no live engine mutations. */
object ImportedStsBatch {
    data class Member(val position:Int,val trackIndex:Int,val snapshot:ImportedSts)
    data class Selection(val slot:Int,val master:ImportedSts,val members:List<Member>,val actions:List<ImportedStsPlan.Action>)
    data class Prepared(val file:File,val index:ImportedSetIndex,val selection:Selection,
        val voices:Map<Int,ImportedStsLoader.Prepared>,val master:ImportedStsMaster.Prepared?=null,
        val basisSnapshots:Map<Int,ImportedSts>?=null){
        fun currentSnapshots():Map<Int,ImportedSts> = voices.mapValues{it.value.currentSnapshot()}
        fun basisIsCurrent(previous:Prepared?):Boolean = basisSnapshots==null || previous?.currentSnapshots()==basisSnapshots
        fun keyboardParts():List<ImportedKeyboardParts.Part> = voices.map{(position,voice)->
            val member=selection.members.single{it.position==position}
            val track=index.tracks[member.trackIndex]
            ImportedKeyboardParts.Part(position,voice.sound,voice.snapshot.pedal!=0,
                voice.snapshot.record,track.keyAreaFrom,track.keyAreaTo,
                ImportedMusicParameters.keyboardGain(master?.music ?: index.musicSettings),track.pedalMode)
        }
    }
    fun resolve(index:ImportedSetIndex,slot:Int,changes:List<Boolean> = ImportedStsPlan.defaultChanges):Selection {
        require(slot>=0 && changes.size==21)
        val main=index.tracks.withIndex().filter{it.value.group==0}
        require(main.isNotEmpty() && main.first().index==0){"SET Master track unavailable"}
        val master=ImportedStsSelection.resolve(index,0,slot)
        val members=main.drop(1).mapIndexed{offset,entry->
            Member(offset+1,entry.index,ImportedStsSelection.resolve(index,entry.index,slot))
        }
        val actions=ImportedStsPlan.create(slot,master,members.map{member->
            val track=index.tracks[member.trackIndex]
            ImportedStsPlan.Track(member.position,track.kind,track.instrument,track.system,member.snapshot)
        },changes)
        return Selection(slot,master,members,actions)
    }
    /** Before the first STSPut, Track state comes from the SET, not the incoming STS. */
    fun prepareInitial(file:File,index:ImportedSetIndex,slot:Int,changes:List<Boolean>,
        scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()}):Prepared {
        val current=index.tracks.filter{it.group==0}.drop(1).mapIndexedNotNull{offset,track->
            val position=offset+1
            if(position>4 || track.kind !in 0..1)null else position to ImportedSts(
                name=track.name,record=track.record,midiChannel=track.midiChannelOut,
                instrument=track.instrument,system=track.system,pedal=track.pedal,
                mode=index.instruments.getOrNull(track.instrument)?.selectedMode ?: 0,
                data1=0,data2=0,chordType=0,chordSingle=emptyList(),
                settings=track.settings.copy(volume=track.volume,pan=track.pan),effects=track.effects)
        }.toMap()
        return prepareMasked(file,index,slot,current,changes,scriptOverrides)
    }
    /** Masked PCM preparation requires the effective current Track state. */
    fun prepareMasked(file:File,index:ImportedSetIndex,slot:Int,current:Map<Int,ImportedSts>,changes:List<Boolean>,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()}):Prepared {
        val resolved=resolve(index,slot,changes)
        val actions=ImportedStsPlan.create(slot,resolved.master,resolved.members.map{member->
            val track=index.tracks[member.trackIndex]
            val prior=current[member.position]
            ImportedStsPlan.Track(member.position,track.kind,prior?.instrument ?: track.instrument,
                prior?.system ?: track.system,member.snapshot)
        },changes)
        val selection=resolved.copy(actions=actions)
        val master=ImportedStsMaster.prepare(index,selection.master,changes)
        val loadIndex=index.copy(musicSettings=master.music,systems=master.systems)
        val effective=selection.members.filter{it.position<=4 && index.tracks[it.trackIndex].kind in 0..1}
            .associate{member->
                val prior=current[member.position] ?: error("Current STS part ${member.position} unavailable")
                member.position to ImportedStsChanges.transition(index,member.position,prior,member.snapshot,selection.actions,changes)
            }
        ImportedStsMemory.check(loadIndex,effective.values)
        val voices=effective.mapValues{(_,snapshot)->ImportedStsLoader.prepare(file,loadIndex,snapshot,scriptOverrides)}
        return Prepared(file,index,selection,voices,master)
    }
    fun prepareFull(file:File,index:ImportedSetIndex,slot:Int,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()},restoredModes:Map<Int,Int> = emptyMap()):Prepared {
        val selection=resolve(index,slot)
        // Resolve all slots first, so a missing later Track cannot cause partial apply.
        val members=selection.members.filter{it.position<=4 && index.tracks[it.trackIndex].kind in 0..1}
        val snapshots=members.associate{it.position to ImportedStsModeState.restore(index,it.snapshot,restoredModes[it.position])}
        ImportedStsMemory.check(index,snapshots.values)
        val voices=snapshots.mapValues{(_,snapshot)->ImportedStsLoader.prepare(file,index,snapshot,scriptOverrides)}
        return Prepared(file,index,selection,voices)
    }
}
