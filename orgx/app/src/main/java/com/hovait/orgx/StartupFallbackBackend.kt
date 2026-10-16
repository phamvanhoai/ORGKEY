package com.hovait.orgx

/** Falls back only during startup; write failures are handled by the output loop. */
class StartupFallbackBackend(
    override val sampleRate: Int,
    override val channels: Int = 2,
    private val preferred: () -> AudioBackend,
    private val fallback: () -> AudioBackend,
    private val onSelected: (Boolean) -> Unit = {}
) : AudioBackend {
    private var active: AudioBackend? = null
    override fun start() {
        check(active == null)
        var candidate: AudioBackend? = null
        try {
            candidate = preferred()
            candidate.start()
            active = candidate
            onSelected(true)
        } catch (error: Throwable) {
            runCatching { candidate?.close() }
            if (error !is Exception && error !is LinkageError) throw error
            val replacement = fallback()
            active = replacement
            replacement.start()
            onSelected(false)
        }
    }
    override fun write(samples: ShortArray, offset: Int, count: Int): Int =
        checkNotNull(active).write(samples, offset, count)
    override fun stop() { active?.stop() }
    override fun close() { val backend=active;active=null;backend?.close() }
}
