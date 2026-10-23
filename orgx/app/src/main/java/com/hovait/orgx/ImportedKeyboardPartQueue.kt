package com.hovait.orgx

import java.util.concurrent.ConcurrentLinkedQueue

/** UI submits ordered commands; only the audio thread touches Playback/part state. */
class ImportedKeyboardPartQueue {
    private val commands=ConcurrentLinkedQueue<()->Unit>()
    private val parts=ImportedKeyboardParts()
    fun replace(values:List<ImportedKeyboardParts.Part>){
        commit(values){true}
    }
    fun commit(values:List<ImportedKeyboardParts.Part>,completed:(Boolean)->Unit = {},beforeReplace:()->Boolean){
        require(values.size<=4 && values.all{it.id in 1..4} && values.map{it.id}.distinct().size==values.size)
        require(values.all{it.inputGain.isFinite() && it.inputGain in 0.0..1.0})
        val snapshot=values.toList()
        commands.add{
            val accepted=beforeReplace()
            if(accepted)parts.replace(snapshot)
            completed(accepted)
        }
    }
    fun noteOn(note:Int,velocity:Double){
        require(note in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        commands.add{parts.noteOn(note,velocity)}
    }
    fun noteOff(note:Int){commands.add{parts.noteOff(note)}}
    fun pedal(down:Boolean){commands.add{parts.pedalAll(down)}}
    fun pitchBend(semitones:Double){
        require(semitones.isFinite() && kotlin.math.abs(semitones)<=48)
        commands.add{parts.pitchBend(semitones)}
    }
    fun pressure(note:Int,value:Float){
        require(value.isFinite() && value in 0f..1f)
        commands.add{parts.pressure(note,value)}
    }
    fun channelPressure(value:Float){
        require(value.isFinite() && value in 0f..1f)
        commands.add{parts.channelPressure(value)}
    }
    fun clear(afterClear:()->Unit = {}){commands.add{parts.reset();parts.replace(emptyList());afterClear()}}
    fun drain(){
        while(true){val command=commands.poll() ?: break;command()}
    }
    fun renderInto(mixer:ImportedKeyboardMixer,drainCommands:Boolean=true){
        if(drainCommands)drain()
        parts.renderInto(mixer)
    }
}
