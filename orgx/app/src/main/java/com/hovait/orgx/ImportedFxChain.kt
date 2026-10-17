package com.hovait.orgx

/** Bass Booster p01: same stereo recurrence, applied to the summed track signal. */
class ImportedFxChain(settings:ImportedChannelSettings) {
    private class Bass(val volume:Float,val frequency:Float){
        var left=0f;var right=0f
        val smoothing=1f/(frequency+1f)
    }
    private fun int(bytes:List<Int>,offset:Int):Int =
        bytes[offset] or (bytes[offset+1] shl 8) or (bytes[offset+2] shl 16) or (bytes[offset+3] shl 24)
    private val bass=if(!settings.fxEnabled)emptyList() else settings.modules.filter{it.enabled && it.type==20}.map{
        require(it.payload.size==8)
        val volume=int(it.payload,0);val frequency=int(it.payload,4)
        require(volume in 0..100 && frequency in 0..100){"Unsupported Bass Booster parameters"}
        Bass(volume/16f,frequency.toFloat())
    }
    var left=0.0;private set
    var right=0.0;private set
    fun reset(){bass.forEach{it.left=0f;it.right=0f};left=0.0;right=0.0}
    fun process(inputLeft:Double,inputRight:Double){
        left=inputLeft;right=inputRight
        for(stage in bass){
            stage.left=(stage.left*stage.frequency+left.toFloat())*stage.smoothing
            stage.right=(stage.right*stage.frequency+right.toFloat())*stage.smoothing
            left+=stage.left*stage.volume;right+=stage.right*stage.volume
        }
    }
}
