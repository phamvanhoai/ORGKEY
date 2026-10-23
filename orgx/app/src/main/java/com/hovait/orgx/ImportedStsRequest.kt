package com.hovait.orgx

import java.io.File

/** Master chooses Group 0 voices; individual track snapshots keep single-voice behavior. */
object ImportedStsRequest {
    sealed class Prepared {
        data class Single(val value:ImportedStsLoader.Prepared):Prepared()
        data class Group(val value:ImportedStsBatch.Prepared,val previous:ImportedStsBatch.Prepared?=null):Prepared()
    }
    fun prepare(file:File,index:ImportedSetIndex,track:Int,slot:Int,current:ImportedStsBatch.Prepared?=null,
        changes:List<Boolean> = ImportedStsPlan.defaultChanges,scriptOverrides:(File,ImportedInstrument)->Map<String,String> = {_,_->emptyMap()}):Prepared {
        require(changes.size==21){"Invalid STS change mask"}
        if(track!=0)return Prepared.Single(ImportedStsLoader.prepare(file,index,ImportedStsSelection.resolve(index,track,slot),scriptOverrides))
        if(current==null || current.file!=file || current.index!==index)return Prepared.Group(ImportedStsBatch.prepareFull(file,index,slot,scriptOverrides))
        val basis=index.copy(musicSettings=current.master?.music ?: index.musicSettings,
            systems=current.master?.systems ?: index.systems)
        val snapshots=current.currentSnapshots()
        val loaded=ImportedStsBatch.prepareMasked(file,basis,slot,snapshots,changes,scriptOverrides)
        return Prepared.Group(loaded.copy(index=index,basisSnapshots=snapshots),current)
    }
}
