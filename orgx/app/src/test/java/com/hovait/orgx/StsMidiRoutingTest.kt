package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class StsMidiRoutingTest {
    @Test fun onlyLiveKeyboardChannelUsesStsParts(){
        for(channel in 0..15)for(playback in listOf(false,true))for(enabled in listOf(false,true)){
            assertEquals(enabled && channel==0 && !playback,StsMidiRouting.usesParts(enabled,channel,playback))
        }
    }
}
