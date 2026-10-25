package com.hovait.orgx

data class ReferenceDrumEvent(val type:Int,val id:Int,val note:Int,val touch:Int,val time:Long,val length:Long,val tune:Int=0)

/** Schedules in reference time units; conversion from audio frames is separate. */
class ReferenceDrumTimeline(events:List<ReferenceDrumEvent>) {
    private val events=events.sortedBy{it.time}
    private var index=0
    private var previous=Long.MIN_VALUE
    val exhausted get()=index>=events.size
    val endsWithNoteOff get()=events.lastOrNull()?.type in listOf(1,3)
    fun reset(){index=0;previous=Long.MIN_VALUE}
    fun advance(time:Long,dispatch:(ReferenceDrumEvent)->Unit) {
        require(time>=previous){"Reset the timeline before seeking backwards"}
        previous=time
        while(index<events.size && events[index].time<=time){
            val event=events[index++]
            // Reference PlayNext ignores note-ons whose complete interval was missed.
            if(event.type in listOf(2,4) && event.length>0 && time-event.time>event.length+10)continue
            dispatch(event)
        }
    }
}
