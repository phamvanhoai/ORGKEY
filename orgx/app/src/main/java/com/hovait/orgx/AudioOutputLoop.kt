package com.hovait.orgx

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

/** The worker owns output startup, writes and cleanup. close() only requests a stop. */
class AudioOutputLoop(
    private val start:()->Unit,
    private val render:(ShortArray)->Unit,
    private val write:(ShortArray,Int,Int)->Int,
    private val release:()->Unit,
    private val onError:(Throwable)->Unit={},
    samplesPerBlock:Int=512
):AutoCloseable {
    constructor(backend:AudioBackend,render:(ShortArray)->Unit,onError:(Throwable)->Unit={},samplesPerBlock:Int=512):this(
        start={backend.start()},render=render,write={samples,offset,count->backend.write(samples,offset,count)},release={backend.close()},onError=onError,samplesPerBlock=samplesPerBlock
    )
    private val running=AtomicBoolean(true)
    init {
        require(samplesPerBlock>0 && samplesPerBlock%2==0)
        thread(name="ORGX-Audio",priority=Thread.MAX_PRIORITY){
            try {
                if(running.get())start()
                val output=ShortArray(samplesPerBlock)
                while(running.get()){
                    render(output)
                    var position=0
                    while(running.get() && position<output.size){
                        val count=write(output,position,output.size-position)
                        check(count>0 && count<=output.size-position){"Audio write failed: $count"}
                        position+=count
                    }
                }
            }catch(error:Exception){onError(error)}
            finally{
                running.set(false)
                try{release()}catch(error:Exception){onError(error)}
            }
        }
    }
    override fun close(){running.set(false)}
}
