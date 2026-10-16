package com.hovait.orgx

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int,val gain:Double=1.0,val pan:Int=0)

class SampledInstrument(regions:List<InstrumentRegion>,outputRate:Int) {
    init {
        require(regions.isNotEmpty())
        regions.forEach { require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size && it.gain.isFinite() && it.gain>=0 && it.pan in -100..100) }
    }
    private val roots=regions.map{it.root}
    private val selected=Array(128){note->regions.first{it.root==SampleRegionMap.nearestRoot(note,roots)}}
    private val steps=DoubleArray(128){note->SamplePitch.step(note,selected[note].root,selected[note].sample.sampleRate,outputRate)}
    fun sample(note:Int,age:Long,channel:Int=0):Double {
        if(note !in 0..127 || age<0)return 0.0
        val region=selected[note]
        val panGain=if(channel==0)minOf(100,100-region.pan) else minOf(100,100+region.pan)
        return SampleResampler.looped(region.sample,age*steps[note],channel,region.loopStart,region.loopEnd)*region.gain*panGain/100
    }
}
