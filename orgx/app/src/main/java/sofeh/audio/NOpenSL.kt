package sofeh.audio

/**
 * Thin declaration-only bridge for the reference NOpenSL ABI.
 *
 * The shipped library exports these JNI names for `sofeh.audio.NOpenSL`.
 * ORGX keeps calls opt-in: AudioEngine still uses its AudioTrack fallback
 * until the device-specific OpenSL path has been validated.
 */
object NOpenSL {
    @JvmStatic external fun Start(callbackOwner: Any?, sampleRate: Int, bufferFrames: Int, bufferCount: Int, record: Boolean): Boolean
    @JvmStatic external fun Write(output: ShortArray, size: Int)
    @JvmStatic external fun Put(output: ShortArray, size: Int)
    @JvmStatic external fun MixWrite(output: ShortArray, input: ShortArray, size: Int, left: Int, right: Int)
    @JvmStatic external fun MixPut(output: ShortArray, input: ShortArray, size: Int, left: Int, right: Int)
    @JvmStatic external fun RecordStart(): Boolean
    @JvmStatic external fun RecordStop()
    @JvmStatic external fun Stop()
    @JvmStatic external fun Stoped(): Boolean
}
