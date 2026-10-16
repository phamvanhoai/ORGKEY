package com.hovait.orgx

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int)

class SampledInstrument(regions:List<InstrumentRegion>,outputRate:Int) {
    init {
        require(regions.isNotEmpty())
        regions.forEach { require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size) }
    }
    private val roots=regions.map{it.root}
    private val selected=Array(128){note->regions.first{it.root==SampleRegionMap.nearestRoot(note,roots)}}
    private val steps=DoubleArray(128){note->SamplePitch.step(note,selected[note].root,selected[note].sample.sampleRate,outputRate)}
    fun sample(note:Int,age:Long):Double {
        if(note !in 0..127 || age<0)return 0.0
        val region=selected[note]
        val position=SampleLoop.position(age*steps[note],region.loopStart,region.loopEnd)
        return SampleResampler.at(region.sample.frames,position)
    }
}
