package sofeh.audio

/** Declaration-only bridge used to validate the reconstructed DSP against Android ELF. */
object NBandBooster {
    init{System.loadLibrary("nbandbooster")}
    @JvmStatic external fun open(previous:Long):Long
    @JvmStatic external fun build(handle:Long,low:Int,mid:Int,high:Int,rate:Int)
    @JvmStatic external fun process(handle:Long,input:Long):Long
    @JvmStatic external fun close(handle:Long)
}
