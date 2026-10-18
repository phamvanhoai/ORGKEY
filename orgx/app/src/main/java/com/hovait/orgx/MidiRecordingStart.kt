package com.hovait.orgx

object MidiRecordingStart {
    fun noteMessages(note:Int,velocity:Int,channel:Int,pendingSustain:Boolean):List<ByteArray> {
        require(note in 0..127 && channel in 0..15)
        val on=MidiChannelMessage.bytes(0x90 or channel,note,velocity.coerceIn(1,127))
        return if(pendingSustain)listOf(on,MidiChannelMessage.bytes(0x80 or channel,note,0)) else listOf(on)
    }
}
