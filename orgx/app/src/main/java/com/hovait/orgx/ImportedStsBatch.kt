package com.hovait.orgx

import java.io.File

/** Resolve Group 0 identities before allocating PCM; no live engine mutations. */
object ImportedStsBatch {
    data class Member(val position:Int,val trackIndex:Int,val snapshot:ImportedSts)
    data class Selection(val slot:Int,val master:ImportedSts,val members:List<Member>,val actions:List<ImportedStsPlan.Action>)
    data class Prepared(val file:File,val index:ImportedSetIndex,val selection:Selection,
        val voices:Map<Int,ImportedStsLoader.Prepared>,val master:ImportedStsMaster.Prepared?=null){
        fun keyboardParts():List<ImportedKeyboardParts.Part> = voices.map{(position,voice)->
            val member=selection.members.single{it.position==position}
            val track=index.tracks[member.trackIndex]
            ImportedKeyboardParts.Part(position,voice.sound,voice.snapshot.pedal!=0,
                voice.snapshot.record,track.keyAreaFrom,track.keyAreaTo,
                ImportedMusicParameters.keyboardGain(master?.music ?: index.musicSettings))
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
    /** Full voice snapshots only. Masked PCM preparation requires current Track state. */
    fun prepareMasked(file:File,index:ImportedSetIndex,slot:Int,current:Map<Int,ImportedSts>,changes:List<Boolean>):Prepared {
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
        val voices=effective.mapValues{(_,snapshot)->ImportedStsLoader.prepare(file,loadIndex,snapshot)}
        return Prepared(file,index,selection,voices,master)
    }
    fun prepareFull(file:File,index:ImportedSetIndex,slot:Int):Prepared {
        val selection=resolve(index,slot)
        // Resolve all slots first, so a missing later Track cannot cause partial apply.
        val members=selection.members.filter{it.position<=4 && index.tracks[it.trackIndex].kind in 0..1}
        ImportedStsMemory.check(index,members.map{it.snapshot})
        val voices=members.associate{it.position to ImportedStsLoader.prepare(file,index,it.snapshot)}
        return Prepared(file,index,selection,voices)
    }
}
