package com.hovait.orgx

/** The app sustain switch is global for live voices; MIDI CC64 is per channel. */
object MidiRecordingPedal {
    fun messages(enabled:Boolean):List<ByteArray> = (0..15).filterNot(MidiVoiceKey::percussion).map{channel->
        MidiChannelMessage.bytes(0xb0 or channel,64,if(enabled)127 else 0)
    }
}
