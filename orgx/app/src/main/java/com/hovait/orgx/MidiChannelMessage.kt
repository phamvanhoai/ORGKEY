package com.hovait.orgx

/** Encodes channel messages with their actual one- or two-data-byte payload. */
object MidiChannelMessage {
    fun bytes(status:Int,first:Int,second:Int=0):ByteArray {
        require(status in 0x80..0xef)
        val oneByte=status and 0xf0 in listOf(0xc0,0xd0)
        return if(oneByte)byteArrayOf(status.toByte(),first.coerceIn(0,127).toByte())
        else byteArrayOf(status.toByte(),first.coerceIn(0,127).toByte(),second.coerceIn(0,127).toByte())
    }
}
