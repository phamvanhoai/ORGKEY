package com.hovait.orgx

/** Music.STSPut command plan. Separate from committing prepared PCM on the UI thread. */
object ImportedStsPlan {
    val defaultChanges:List<Boolean> get()=listOf(false,false,false,false,false,false,false,true,
        true,true,true,true,false,true,true,true,true,true,true,true,false)
    data class Track(val id:Int,val kind:Int,val instrument:Int,val system:Int,val snapshot:ImportedSts)
    sealed class Action {
        data class Event(val track:Int,val type:Int,val id:Int=0,val key:Int=0):Action()
        data class Record(val track:Int,val enabled:Boolean):Action()
    }
    fun create(slot:Int,master:ImportedSts?,tracks:List<Track>,changes:List<Boolean> = defaultChanges):List<Action>{
        require(slot>=0 && changes.size==21){"Invalid STS slot or change mask"}
        if(master==null)return emptyList()
        return buildList {
            fun event(track:Int,type:Int,id:Int=0,key:Int=0){add(Action.Event(track,type,id,key))}
            if(changes[0])event(0,222,key=master.data1)
            if(changes[1])event(0,223,key=master.system)
            event(0,224,key=slot)
            if(changes[20])event(0,225,key=slot)
            tracks.forEach{track->
                val sts=track.snapshot
                if(changes[8])add(Action.Record(track.id,sts.record))
                if(changes[9] && track.instrument!=sts.instrument)
                    event(track.id,62,if(changes[11] || track.id>4)0 else -1,sts.instrument)
                if(changes[12] && track.system!=sts.system)event(track.id,63,key=sts.system)
                if(changes[10])event(track.id,if(sts.pedal==0)11 else 12)
                if(track.kind==0 || track.kind==1){
                    if(changes[11])event(track.id,61,if(changes[18] || track.id>4)0 else -1,sts.mode)
                    if(track.id<=4)event(track.id,64,key=slot)
                }
            }
        }
    }
}
