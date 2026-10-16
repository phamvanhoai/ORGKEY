package com.hovait.orgx

import android.content.res.AssetManager
import org.json.JSONObject

data class ReferenceDrumTrack(val name:String,val group:Int,val parent:Int,val section:Int,
    val tempo:Int,val loop:Boolean,val start:Long,val end:Long,val muted:Boolean,
    val events:List<ReferenceDrumEvent>) {
    fun timeline()=ReferenceDrumTimeline(events)
}

object ReferenceDrumStore {
    fun load(assets:AssetManager):List<ReferenceDrumTrack> {
        val json=assets.open("reference-drum-tracks.json").bufferedReader().use { JSONObject(it.readText()) }
        require(json.getString("format")=="ORGX reference drum tracks" && json.getInt("version")==1)
        val tracks=json.getJSONArray("tracks")
        return List(tracks.length()){index->
            val track=tracks.getJSONObject(index)
            val array=track.getJSONArray("events")
            val events=List(array.length()){eventIndex->
                val event=array.getJSONObject(eventIndex)
                val type=event.getInt("event");val note=event.getInt("key")
                require(type in 1..2 && note in 0..127)
                require(event.getInt("tune")==0){"Tuned drum events require pitch support"}
                ReferenceDrumEvent(type,event.getInt("id"),note,event.getInt("touch"),event.getLong("time"),event.getLong("length"))
            }
            ReferenceDrumTrack(track.getString("name"),track.getInt("group"),track.getInt("parent"),
                track.getInt("section"),track.getInt("tempo"),track.getBoolean("loop"),
                track.getLong("limit_start"),track.getLong("limit_end"),track.getBoolean("mute"),events)
        }
    }
}
