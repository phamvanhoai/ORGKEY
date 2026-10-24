package com.hovait.orgx

/** Channels.Open selects the channel budget separately for keyboard and rhythm tracks. */
object ImportedPolyphony {
    val choices=listOf(8,10,12,14,16,24,32,48,64,96,128,192,256)
    @Volatile var music=10
        set(value){require(value in choices);field=value}
    @Volatile var rhythm=14
        set(value){require(value in choices);field=value.coerceAtMost(64)}
}
