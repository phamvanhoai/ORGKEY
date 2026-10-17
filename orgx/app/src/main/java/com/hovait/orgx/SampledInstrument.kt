package com.hovait.orgx

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int,val gain:Double=1.0,val pan:Int=0,val looping:Boolean=true,val tune:Int=0)

class SampledInstrument(private val regions:List<InstrumentRegion>,private val outputRate:Int,private val exactNotes:Boolean=false,val envelope:ImportedEnvelope?=null,
    private val pitchMode:Int=if(exactNotes)2 else 0) {
    init {
        require(regions.isNotEmpty())
        regions.forEach {
            require(it.sample.frames.isNotEmpty() && it.gain.isFinite() && it.gain>=0 && it.pan in -100..100)
            if(it.looping)require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size)
        }
    }
    private val roots=regions.map{it.root}
    private var layers:List<SampledInstrument> = emptyList()
    companion object {
        fun combine(sounds:List<SampledInstrument>):SampledInstrument {
            require(sounds.isNotEmpty())
            if(sounds.size==1)return sounds.single()
            return SampledInstrument(sounds.first().regions,sounds.first().outputRate).also{it.layers=sounds}
        }
    }
    fun voice(note:Int)=Playback(note)
    inner class Playback(private val note:Int){
        private val children=layers.map{it.voice(note)}
        private val state=envelope?.voice(outputRate)
        private var age=0L
        var left=0.0;private set
        var right=0.0;private set
        var finished=false;private set
        fun next(noteOff:Boolean){
            if(children.isNotEmpty()){
                children.forEach{it.next(noteOff)}
                left=children.sumOf{it.left};right=children.sumOf{it.right};finished=children.all{it.finished}
            }else{
                val gain=state?.next(noteOff) ?: if(noteOff)0.0 else 1.0
                left=sample(note,age,0)*gain;right=sample(note,age,1)*gain
                age++;finished=state?.finished==true || finished(note,age) || (state==null && noteOff)
            }
        }
    }
    private val mapped=Array(128){note->when(pitchMode){
        1->SampleRegionMap.repeatedRoot(note,roots)
        2->note.takeIf{it in roots}
        else->SampleRegionMap.nearestRoot(note,roots)
    }}
    private val selected=Array(128){note->regions.firstOrNull{it.root==mapped[note]} ?: regions.first()}
    private val steps=DoubleArray(128){note->SamplePitch.step(if(pitchMode==1)mapped[note] ?: note else note,selected[note].root,selected[note].sample.sampleRate,outputRate,selected[note].tune)}
    private fun missing(note:Int)=mapped[note]==null
    fun durationFrames(note:Int):Int {
        if(layers.isNotEmpty())return layers.maxOf{it.durationFrames(note)}
        if(note !in 0..127 || missing(note))return 0
        val region=selected[note]
        return if(region.looping)Int.MAX_VALUE else kotlin.math.ceil(region.sample.frames.size/steps[note]).coerceAtMost(Int.MAX_VALUE.toDouble()).toInt()
    }
    fun finished(note:Int,age:Long):Boolean {
        if(layers.isNotEmpty())return layers.all{it.finished(note,age)}
        if(note !in 0..127)return true
        if(missing(note))return true
        val region=selected[note]
        return !region.looping && age*steps[note]>=region.sample.frames.size
    }
    fun sample(note:Int,age:Long,channel:Int=0):Double {
        if(layers.isNotEmpty())return layers.sumOf{it.sample(note,age,channel)}
        if(note !in 0..127 || age<0)return 0.0
        if(missing(note))return 0.0
        val region=selected[note]
        val panGain=if(channel==0)minOf(100,100-region.pan) else minOf(100,100+region.pan)
        val position=age*steps[note]
        val value=if(region.looping)SampleResampler.looped(region.sample,position,channel,region.loopStart,region.loopEnd)
            else SampleResampler.channel(region.sample,position,channel)
        return value*region.gain*panGain/100
    }
}
