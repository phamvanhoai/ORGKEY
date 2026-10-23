package com.hovait.orgx

/** Snapshot nonzero CC1 values at capture start, retaining MIDI channel identity. */
object MidiModulation {
    fun recordingStart(levels:IntArray):List<RecordedMidiEvent>{
        require(levels.size==16 && levels.all{it in 0..127})
        return levels.toList().mapIndexedNotNull{channel,value->
            if(value==0)null else RecordedMidiEvent(0,0xb0 or channel,1,value)
        }
    }
}
