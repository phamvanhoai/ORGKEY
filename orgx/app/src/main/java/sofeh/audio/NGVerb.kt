package sofeh.audio

/** Declaration-only JNI bridge to the already packaged reference library. */
object NGVerb {
    init { System.loadLibrary("ngverb") }
    @JvmStatic external fun open(previous:Long,rate:Int,maxRoom:Float,room:Float,time:Float,damping:Float,spread:Float,bandwidth:Float,early:Float,tail:Float):Long
    @JvmStatic external fun close(handle:Long)
    @JvmStatic external fun flush(handle:Long)
    @JvmStatic external fun processStereoLeft(handle:Long,left:Long,right:Long,mix:Int):Long
    @JvmStatic external fun processStereoRight():Long
}
