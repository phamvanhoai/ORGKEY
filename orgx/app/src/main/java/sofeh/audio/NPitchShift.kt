package sofeh.audio

object NPitchShift {
    init{System.loadLibrary("npitchshift")}
    @JvmStatic external fun open(previous:Long):Long
    @JvmStatic external fun close(handle:Long)
    @JvmStatic external fun processLeft(handle:Long,shift:Int,rate:Int,input:Long):Long
    @JvmStatic external fun processRight(handle:Long,shift:Int,rate:Int,input:Long):Long
}
