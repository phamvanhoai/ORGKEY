package com.hovait.orgx

/** Converts per-channel pedal holds into delayed note-offs before playback. */
object MidiSustain {
    fun resolve(events:List<MidiNoteEvent>):List<MidiNoteEvent> {
        val pedal=BooleanArray(16)
        val pending=LinkedHashMap<Int,MidiNoteEvent>()
        val result=mutableListOf<MidiNoteEvent>()
        events.forEach{event->
            val channel=event.channel
            val key=MidiVoiceKey.key(event.note,channel)
            if(event.controller==64){
                pedal[channel]=event.velocity>=64
                if(!pedal[channel]){
                    val iterator=pending.entries.iterator()
                    while(iterator.hasNext()){
                        val entry=iterator.next()
                        if(entry.value.channel==channel){result+=entry.value.copy(tick=event.tick);iterator.remove()}
                    }
                }
            } else if(event.on){
                pending.remove(key)?.let{result+=it.copy(tick=event.tick)}
                result+=event
            } else if(pedal[channel] && !MidiVoiceKey.percussion(channel))pending[key]=event
            else result+=event
        }
        val end=events.lastOrNull()?.tick ?: 0L
        result+=pending.values.map{it.copy(tick=end)}
        return result
    }
}
