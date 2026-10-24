package com.hovait.orgx

/** Snapshot identity is its track index and original slot, never its display name. */
object ImportedStsSelection {
    /** MainActivity.z1 calls Music.STSPut for Master, independently of the editor's selected Track. */
    fun linkedMainSlot(index:ImportedSetIndex,styleId:Int,section:Int):Int? {
        require(section in 32..35){"STS linking requires a Main section"}
        val group=index.styles.firstOrNull{it.id==styleId}
        val slot=if(group!=null)slotForMain(group,section) else section-31
        val master=index.tracks.firstOrNull()?.takeIf{it.group==0} ?: return null
        return slot.takeIf{wanted->master.sts.count{it.slot==wanted}==1}
    }
    /** MainActivity.z1: Main 1..4 uses a positive group override, otherwise slots 1..4. */
    fun slotForMain(group:ImportedStyleGroup,section:Int):Int {
        require(section in 32..35){"STS linking requires a Main section"}
        val variation=section-32
        return group.stsIndices.getOrNull(variation)?.takeIf{group.id!=0 && it>0} ?: (variation+1)
    }
    fun resolve(index:ImportedSetIndex,trackIndex:Int,slot:Int):ImportedSts {
        val track=index.tracks.getOrNull(trackIndex) ?: error("Saved STS track is missing")
        return track.sts.singleOrNull{it.slot==slot} ?: error("Saved STS slot is missing or ambiguous")
    }
}
