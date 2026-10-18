package com.hovait.orgx

import java.io.File

/** Master chooses Group 0 voices; individual track snapshots keep single-voice behavior. */
object ImportedStsRequest {
    sealed class Prepared {
        data class Single(val value:ImportedStsLoader.Prepared):Prepared()
        data class Group(val value:ImportedStsBatch.Prepared):Prepared()
    }
    fun prepare(file:File,index:ImportedSetIndex,track:Int,slot:Int):Prepared =
        if(track==0)Prepared.Group(ImportedStsBatch.prepareFull(file,index,slot))
        else Prepared.Single(ImportedStsLoader.prepare(file,index,ImportedStsSelection.resolve(index,track,slot)))
}
