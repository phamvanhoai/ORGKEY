package com.hovait.orgx

/** Release pedal before All Notes Off so sustained keys actually stop. */
object MidiRecordingEnd {
    fun messages(channels:Iterable<Int>):List<ByteArray> = channels.distinct().sorted().flatMap{channel->
        require(channel in 0..15)
        listOf(MidiChannelMessage.bytes(0xb0 or channel,64,0),MidiChannelMessage.bytes(0xb0 or channel,123,0))
    }
}
