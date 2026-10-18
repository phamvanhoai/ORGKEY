package com.hovait.orgx

import kotlin.math.pow

data class InstrumentRegion(val root:Int,val sample:SamplePcm,val loopStart:Int,val loopEnd:Int,val gain:Double=1.0,val pan:Int=0,val looping:Boolean=true,val tune:Int=0)

class SampledInstrument(private val regions:List<InstrumentRegion>,private val outputRate:Int,private val exactNotes:Boolean=false,val envelope:ImportedEnvelope?=null,
    private val pitchMode:Int=if(exactNotes)2 else 0,private val tuning:List<Double> = emptyList(),private val tuningTranspose:Int=0) {
    init {
        require(regions.isNotEmpty())
        regions.forEach {
            require(it.sample.frames.isNotEmpty() && it.gain.isFinite() && it.gain>=0 && it.pan in -100..100)
            if(it.looping)require(it.loopStart>=0 && it.loopEnd>it.loopStart && it.loopEnd<=it.sample.frames.size)
        }
    }
    private val roots=regions.map{it.root}
    private var layers:List<SampledInstrument> = emptyList()
    private var aftertouchInstrument:SampledInstrument?=null
    var keyboardSettings=ImportedChannelSettings();private set
    private var keyboardMelodic=true
    fun withKeyboardSettings(settings:ImportedChannelSettings,melodic:Boolean)=also{keyboardSettings=settings;keyboardMelodic=melodic}
    fun withAftertouch(alternate:SampledInstrument)=also{aftertouchInstrument=alternate}
    fun keyboardNote(note:Int):Int=if(!keyboardMelodic)note else
        (note.toLong()+keyboardSettings.transpose+keyboardSettings.octave.toLong()*12).coerceIn(Int.MIN_VALUE.toLong(),Int.MAX_VALUE.toLong()).toInt()
    companion object {
        fun combine(sounds:List<SampledInstrument>):SampledInstrument {
            require(sounds.isNotEmpty())
            if(sounds.size==1)return sounds.single()
            return SampledInstrument(sounds.first().regions,sounds.first().outputRate).also{it.layers=sounds}
        }
    }
    fun voice(note:Int,tune:Int=0,pan:Int=0)=Playback(note,tune,pan)
    inner class Playback(private val note:Int,private val tune:Int,private val pan:Int){
        private val children=layers.map{it.voice(note,tune,pan)}
        private val step=if(note in 0..127 && !missing(note))pitchStep(note,tune) else 1.0
        private var bendSemitones=0.0
        private var aftertouch=false
        private var alternate:Playback?=null
        fun pitchBend(semitones:Double){bendSemitones=semitones;children.forEach{it.pitchBend(semitones)};alternate?.pitchBend(semitones)}
        fun aftertouch(pressure:Float){
            if(!aftertouch && pressure>=.5f && aftertouchInstrument!=null){
                aftertouch=true;alternate=aftertouchInstrument!!.voice(note,tune,pan);alternate?.pitchBend(bendSemitones)
            }
        }
        private val state=envelope?.voice(outputRate)
        private var age=0L
        var left=0.0;private set
        var right=0.0;private set
        var finished=false;private set
        fun next(noteOff:Boolean){
            if(aftertouch){alternate?.next(noteOff);left=alternate?.left ?: 0.0;right=alternate?.right ?: 0.0;finished=alternate?.finished ?: true
            }else if(children.isNotEmpty()){
                children.forEach{it.next(noteOff)}
                left=children.sumOf{it.left};right=children.sumOf{it.right};finished=children.all{it.finished}
            }else{
                val gain=state?.next(noteOff) ?: if(noteOff)0.0 else 1.0
                val currentStep=step*2.0.pow(bendSemitones/12.0)
                left=sampleAt(note,age,0,currentStep,pan)*gain;right=sampleAt(note,age,1,currentStep,pan)*gain
                age++;finished=state?.finished==true || note !in 0..127 || missing(note) ||
                    (!selected[note].looping && age*currentStep>=selected[note].sample.frames.size) || (state==null && noteOff)
            }
        }
    }
    private val mapped=Array(128){note->when(pitchMode){
        1->SampleRegionMap.repeatedRoot(note,roots)
        2->note.takeIf{it in roots}
        else->SampleRegionMap.nearestRoot(note,roots)
    }}
    private val selected=Array(128){note->regions.firstOrNull{it.root==mapped[note]} ?: regions.first()}
    private fun pitchStep(note:Int,tune:Int=0):Double {
        val key=if(pitchMode==1)mapped[note] ?: note else note
        val region=selected[note]
        return if(tuning.isEmpty())SamplePitch.step(key,region.root,region.sample.sampleRate,outputRate,region.tune+tune)
        else SamplePitch.referenceStep(key,region.root,region.sample.sampleRate,outputRate,region.tune+tune,tuning,tuningTranspose)
    }
    private val steps=DoubleArray(128){pitchStep(it)}
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
        return sampleAt(note,age,channel,if(note in 0..127)steps[note] else 1.0)
    }
    private fun sampleAt(note:Int,age:Long,channel:Int,step:Double,notePan:Int=0):Double {
        if(note !in 0..127 || age<0)return 0.0
        if(missing(note))return 0.0
        val region=selected[note]
        val pan=region.pan+notePan
        val panGain=if(channel==0)minOf(100,100-pan) else minOf(100,100+pan)
        val position=age*step
        val value=if(region.looping)SampleResampler.looped(region.sample,position,channel,region.loopStart,region.loopEnd)
            else SampleResampler.channel(region.sample,position,channel)
        return value*region.gain*panGain/100
    }
}
