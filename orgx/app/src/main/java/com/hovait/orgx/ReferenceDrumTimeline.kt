package com.hovait.orgx

data class ReferenceDrumEvent(val type:Int,val id:Int,val note:Int,val touch:Int,val time:Long,val length:Long)

/** Schedules in reference time units; conversion from audio frames is separate. */
class ReferenceDrumTimeline(events:List<ReferenceDrumEvent>) {
    private val events=events.sortedBy{it.time}
    private var index=0
    private var previous=Long.MIN_VALUE
    fun reset(){index=0;previous=Long.MIN_VALUE}
    fun advance(time:Long,dispatch:(ReferenceDrumEvent)->Unit) {
        require(time>=previous){"Reset the timeline before seeking backwards"}
        previous=time
        while(index<events.size && events[index].time<=time){
            val event=events[index++]
            // Reference PlayNext ignores note-ons whose complete interval was missed.
            if(event.type==2 && event.length>0 && time-event.time>event.length+10)continue
            dispatch(event)
        }
    }
}
