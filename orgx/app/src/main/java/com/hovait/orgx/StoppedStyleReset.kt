package com.hovait.orgx

/** One reset per stopped interval; a newly selected stopped session needs its own reset. */
class StoppedStyleReset {
    private var reset=false
    fun shouldReset(playing:Boolean,selectionChanged:Boolean=false):Boolean {
        if(playing){reset=false;return false}
        if(selectionChanged)reset=false
        if(reset)return false
        reset=true
        return true
    }
}
