package com.hovait.orgx

object NativeReferenceLoader {
    @Volatile private var attempted=false
    @Volatile var available=false
        private set
    fun tryLoad():Boolean {
        if(attempted)return available
        synchronized(this){
            if(attempted)return available
            attempted=true
            available=runCatching{
                arrayOf("nopensl","nequalizer","nflanger","ngverb","npitchshift","nbandbooster","nmusic","nsf2").forEach{System.loadLibrary(it)}
                true
            }.getOrDefault(false)
            return available
        }
    }
}
