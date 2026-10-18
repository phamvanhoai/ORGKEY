package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class MidiVoiceKeyTest {
    @Test fun expressiveLiveControlsOnlyTargetTheRequestedChannel(){
        for(channel in 0..15)for(note in 0..127){
            assertTrue(MidiVoiceKey.isLiveChannel(MidiVoiceKey.key(note,channel),channel))
            assertFalse(MidiVoiceKey.isLiveChannel(MidiVoiceKey.key(note,channel,true),channel))
            assertTrue(MidiVoiceKey.isChannel(MidiVoiceKey.key(note,channel,true),channel,true))
            assertFalse(MidiVoiceKey.isChannel(MidiVoiceKey.key(note,channel),channel,true))
            assertFalse(MidiVoiceKey.isLiveChannel(MidiVoiceKey.key(note,channel),(channel+1)%16))
        }
        assertFalse(MidiVoiceKey.isLiveChannel(-1,0))
        assertFalse(MidiVoiceKey.isLiveChannel(0,16))
    }
    @Test fun playbackAndLiveKeyboardNeverShareVoiceKeys() {
        val live=(0..15).flatMap{channel->(0..127).map{MidiVoiceKey.key(it,channel)}}.toSet()
        val playback=(0..15).flatMap{channel->(0..127).map{MidiVoiceKey.key(it,channel,true)}}.toSet()
        assertTrue(live.intersect(playback).isEmpty())
        assertEquals(4096,(live+playback).size)
    }
    @Test fun allChannelsAndNotesHaveDistinctVoiceKeys() {
        val keys=(0..15).flatMap{channel->(0..127).map{MidiVoiceKey.key(it,channel)}}
        assertEquals(2048,keys.toSet().size)
        assertEquals(60,MidiVoiceKey.key(60,0))
        assertNotEquals(MidiVoiceKey.key(60,1),MidiVoiceKey.key(60,2))
    }
    @Test fun onlyGmChannelTenUsesDrums() {
        assertEquals(listOf(9),(0..15).filter{MidiVoiceKey.percussion(it)})
    }
}
