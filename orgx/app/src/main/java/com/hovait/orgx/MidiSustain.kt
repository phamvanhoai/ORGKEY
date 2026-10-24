package com.hovait.orgx

/** Converts per-channel pedal holds into delayed note-offs before playback. */
object MidiSustain {
    fun resolve(events:List<MidiNoteEvent>):List<MidiNoteEvent> {
        val pedal=BooleanArray(16)
        val modulation=IntArray(16)
        val bends=IntArray(16){8192}
        val pending=LinkedHashMap<Int,MidiNoteEvent>()
        val active=LinkedHashMap<Int,MidiNoteEvent>()
        val result=mutableListOf<MidiNoteEvent>()
        events.forEach{event->
            val channel=event.channel
            val key=MidiVoiceKey.key(event.note,channel)
            if(event.controller==123){
                val iterator=active.entries.iterator()
                while(iterator.hasNext()){
                    val entry=iterator.next()
                    if(entry.value.channel==channel){
                        val off=entry.value.copy(tick=event.tick,on=false,velocity=0)
                        if(pedal[channel] && !MidiVoiceKey.percussion(channel))pending[entry.key]=off else result+=off
                        iterator.remove()
                    }
                }
            } else if(event.controller==64 || event.controller==121){
                if(event.controller==121 && modulation[channel]!=0){result+=event.copy(controller=1,velocity=0);modulation[channel]=0}
                if(event.controller==121 && bends[channel]!=8192){
                    result+=event.copy(controller=-2,note=0,velocity=64)
                    bends[channel]=8192
                }
                pedal[channel]=event.controller==64 && event.velocity>=64
                if(!pedal[channel]){
                    val iterator=pending.entries.iterator()
                    while(iterator.hasNext()){
                        val entry=iterator.next()
                        if(entry.value.channel==channel){result+=entry.value.copy(tick=event.tick);iterator.remove()}
                    }
                }
            } else if(event.controller!=null){
                if(event.controller==1)modulation[channel]=event.velocity
                if(event.controller==-2)bends[channel]=event.note or (event.velocity shl 7)
                // Expression is neither a note release nor a sustained key.
                result+=event
            } else if(event.on){
                pending.remove(key)?.let{result+=it.copy(tick=event.tick)}
                active[key]=event
                result+=event
            } else {
                active.remove(key)
                if(pedal[channel] && !MidiVoiceKey.percussion(channel))pending[key]=event
                else result+=event
            }
        }
        val end=events.lastOrNull()?.tick ?: 0L
        result+=pending.values.map{it.copy(tick=end)}
        return result
    }
}
