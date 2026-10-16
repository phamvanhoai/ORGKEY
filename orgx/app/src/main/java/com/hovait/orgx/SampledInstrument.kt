package com.hovait.orgx

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int,val gain:Double=1.0,val pan:Int=0,val looping:Boolean=true)

class SampledInstrument(regions:List<InstrumentRegion>,outputRate:Int) {
    init {
        require(regions.isNotEmpty())
        regions.forEach {
            require(it.sample.frames.isNotEmpty() && it.gain.isFinite() && it.gain>=0 && it.pan in -100..100)
            if(it.looping)require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size)
        }
    }
    private val roots=regions.map{it.root}
    private val selected=Array(128){note->regions.first{it.root==SampleRegionMap.nearestRoot(note,roots)}}
    private val steps=DoubleArray(128){note->SamplePitch.step(note,selected[note].root,selected[note].sample.sampleRate,outputRate)}
    fun sample(note:Int,age:Long,channel:Int=0):Double {
        if(note !in 0..127 || age<0)return 0.0
        val region=selected[note]
        val panGain=if(channel==0)minOf(100,100-region.pan) else minOf(100,100+region.pan)
        val position=age*steps[note]
        val value=if(region.looping)SampleResampler.looped(region.sample,position,channel,region.loopStart,region.loopEnd)
            else SampleResampler.channel(region.sample,position,channel)
        return value*region.gain*panGain/100
    }
}
