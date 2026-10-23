package com.hovait.orgx

import kotlinx.coroutines.CompletableDeferred

/** Completion ownership independent of whether another audio frame is rendered. */
class StsCommitWaiters {
    private var closed=false
    private val pending=HashSet<CompletableDeferred<Boolean>>()
    @Synchronized fun register():CompletableDeferred<Boolean> {
        val result=CompletableDeferred<Boolean>()
        if(closed)result.complete(false) else {
            pending.add(result)
            result.invokeOnCompletion{synchronized(this){pending.remove(result)}}
        }
        return result
    }
    fun close(){
        val waiting=synchronized(this){closed=true;pending.toList().also{pending.clear()}}
        waiting.forEach{it.complete(false)}
    }
}
