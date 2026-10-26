package sofeh.audio

/** Declaration-only JNI bridge to the already packaged reference library. */
object NGVerb {
    init { System.loadLibrary("ngverb") }
    @JvmStatic external fun open(previous:Long,rate:Int,maxRoom:Float,room:Float,time:Float,damping:Float,spread:Float,bandwidth:Float,early:Float,tail:Float):Long
    @JvmStatic external fun close(handle:Long)
    @JvmStatic external fun flush(handle:Long)
    @JvmStatic external fun setroomsize(handle:Long,value:Int)
    @JvmStatic external fun setrevtime(handle:Long,value:Int)
    @JvmStatic external fun setdamping(handle:Long,value:Float)
    @JvmStatic external fun setinputbandwidth(handle:Long,value:Float)
    @JvmStatic external fun setearlylevel(handle:Long,value:Float)
    @JvmStatic external fun settaillevel(handle:Long,value:Float)
    @JvmStatic external fun processStereoLeft(handle:Long,left:Long,right:Long,mix:Int):Long
    @JvmStatic external fun processStereoRight():Long
}
