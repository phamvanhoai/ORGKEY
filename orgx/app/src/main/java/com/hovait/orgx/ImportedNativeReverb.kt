package com.hovait.orgx

import sofeh.audio.NGVerb

/** Explicitly owned reference-native reverb. Call close when the audio owner releases it. */
class ImportedNativeReverb(payload:List<Int>,sampleRate:Int,private val backend:Backend=NativeBackend):AutoCloseable {
    interface Backend {
        fun open(rate:Int,values:List<Int>):Long
        fun process(handle:Long,left:Long,right:Long,mix:Int):Pair<Long,Long>
        fun flush(handle:Long)
        fun close(handle:Long)
    }
    private object NativeBackend:Backend {
        // processStereoRight returns a library-global result. Keep both JNI calls atomic.
        private val lock=Any()
        override fun open(rate:Int,values:List<Int>):Long=synchronized(lock){
            NGVerb.open(0,rate,100f,values[0].toFloat(),values[1].toFloat(),values[2]/100f,.5f,values[3]/100f,values[4]/100f,values[5]/100f)
        }
        override fun process(handle:Long,left:Long,right:Long,mix:Int)=synchronized(lock){
            val l=NGVerb.processStereoLeft(handle,left,right,mix)
            l to NGVerb.processStereoRight()
        }
        override fun flush(handle:Long)=synchronized(lock){NGVerb.flush(handle)}
        override fun close(handle:Long)=synchronized(lock){NGVerb.close(handle)}
    }
    private val mix:Int
    private var handle:Long
    var left=0.0;private set
    var right=0.0;private set
    init {
        require(payload.size==8 && payload.take(7).all{it in 1..100} && sampleRate>0){"Invalid SET Reverb parameters"}
        mix=payload[6]
        handle=backend.open(sampleRate,payload)
        check(handle!=0L){"Cannot create native SET Reverb"}
    }
    @Synchronized fun process(l:Double,r:Double){
        check(handle!=0L){"Native SET Reverb is closed"}
        require(l.isFinite() && r.isFinite())
        // JNI converts input long to float by /32768, and output float by *32768.
        val result=backend.process(handle,(l*32768).toLong(),(r*32768).toLong(),mix)
        left=result.first/32768.0;right=result.second/32768.0
    }
    @Synchronized fun reset(){check(handle!=0L);backend.flush(handle);left=0.0;right=0.0}
    @Synchronized override fun close(){
        val owned=handle;handle=0
        if(owned!=0L)backend.close(owned)
        left=0.0;right=0.0
    }
}
