package com.hovait.orgx

/** Serializes output with session replacement so retired workers cannot affect successors. */
class PlaybackSessionGate {
    private var generation=0L
    @Synchronized fun replace():Long { generation++;return generation }
    @Synchronized fun runIfCurrent(session:Long,action:()->Unit):Boolean {
        if(session!=generation)return false
        action();return true
    }
}
