package sofeh.audio

object Lame {
    init { System.loadLibrary("nlame") }
    external fun init(inputSampleRate:Int,channels:Int,outputSampleRate:Int,bitRate:Int,quality:Int)
    external fun encode(left:ShortArray,right:ShortArray,samples:Int,output:ByteArray):Int
    external fun flush(output:ByteArray):Int
    external fun close()
}
