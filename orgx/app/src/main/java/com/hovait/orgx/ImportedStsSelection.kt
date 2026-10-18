package com.hovait.orgx

/** Snapshot identity is its track index and original slot, never its display name. */
object ImportedStsSelection {
    fun resolve(index:ImportedSetIndex,trackIndex:Int,slot:Int):ImportedSts {
        val track=index.tracks.getOrNull(trackIndex) ?: error("Saved STS track is missing")
        return track.sts.singleOrNull{it.slot==slot} ?: error("Saved STS slot is missing or ambiguous")
    }
}
