package com.hovait.orgx

object MidiDrumMap {
    fun pad(note:Int):Int?=when(note){
        35,36->0 // bass drum
        38,40->1 // snare
        42,44,46->2 // closed/open hi-hat
        39->3 // hand clap
        45,47->4 // low tom
        48,50->5 // high tom
        49,57->6 // crash
        else->7 // other percussion
    }
}
