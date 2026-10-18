package com.hovait.orgx

/** Test-only passthrough; never included in the Android APK or treated as DSP validation. */
class JvmReverbBackend:ImportedNativeReverb.Backend {
    var opens=0;var closes=0;var flushes=0
    override fun open(rate:Int,values:List<Int>):Long=(++opens).toLong()
    override fun process(handle:Long,left:Long,right:Long,mix:Int,output:LongArray){output[0]=left;output[1]=right}
    override fun flush(handle:Long){flushes++}
    override fun close(handle:Long){closes++}
}
