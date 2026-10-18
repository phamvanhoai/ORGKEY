package com.hovait.orgx

import kotlin.math.sin

/** Scalar reconstruction of NBandBooster build/process; one instance per channel.
 * Input/output use the JNI integer PCM scale. SET payload mapping is deliberately separate.
 */
class ReferenceBandBooster(low:Int,mid:Int,high:Int,sampleRate:Int) {
    private val lowCoefficient:Float
    private val highCoefficient:Float
    private val gains=floatArrayOf(1f+low*.01f,1f+mid*.01f,1f+high*.01f)
    private val lows=FloatArray(4)
    private val highs=FloatArray(4)
    private val delay=LongArray(3)
    init{
        require(sampleRate>=32000){"Band Booster reference coefficients require a supported audio rate"}
        lowCoefficient=2f*sin((2764.602f/sampleRate).toDouble()).toFloat()
        highCoefficient=2f*sin((15707.96f/sampleRate).toDouble()).toFloat()
    }
    fun process(input:Long):Long {
        fun filter(state:FloatArray,coefficient:Float):Float {
            state[0]+=coefficient*(input.toFloat()-state[0])+2.3283064e-10f
            for(i in 1..3)state[i]+=coefficient*(state[i-1]-state[i])
            return state[3]
        }
        val low=filter(lows,lowCoefficient)
        val upper=filter(highs,highCoefficient)
        val output=low*gains[0]+(upper-low)*gains[1]+(delay[2].toFloat()-upper)*gains[2]
        delay[2]=delay[1];delay[1]=delay[0];delay[0]=input
        return output.toLong()
    }
    fun reset(){lows.fill(0f);highs.fill(0f);delay.fill(0)}
}
