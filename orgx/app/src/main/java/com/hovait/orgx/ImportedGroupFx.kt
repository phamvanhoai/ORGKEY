package com.hovait.orgx

/** Tracks.GroupFXs walks parents until it finds an enabled, nonempty block. */
object ImportedGroupFx {
    fun resolve(index:ImportedSetIndex,group:ImportedStyleGroup):ImportedChannelSettings {
        val groups=index.styles.associateBy{it.id}
        val visited=HashSet<Int>()
        var current:ImportedStyleGroup?=group
        while(current!=null && visited.add(current.id)){
            if(current.id==0)return current.settings
            if(current.settings.fxEnabled && current.settings.modules.isNotEmpty())return current.settings
            current=groups[current.parent]
        }
        return groups[0]?.settings ?: ImportedChannelSettings()
    }
}
