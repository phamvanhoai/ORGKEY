package com.hovait.orgx

object ReferenceStyleEligibility {
    fun playable(track:ReferenceDrumTrack,availableNotes:Set<Int>):Boolean =
        !track.muted && track.loop && track.end>track.start && track.section in 32..35 &&
            track.events.count{it.type==2}>=16 &&
            track.events.filter{it.type==2}.all{it.note in availableNotes}
}
