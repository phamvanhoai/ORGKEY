package com.hovait.orgx

import java.util.concurrent.ConcurrentLinkedQueue

/** UI submits ordered commands; only the audio thread touches Playback/part state. */
class ImportedKeyboardPartQueue {
    private val commands=ConcurrentLinkedQueue<()->Unit>()
    private val parts=ImportedKeyboardParts()
    fun replace(values:List<ImportedKeyboardParts.Part>){
        require(values.size<=4 && values.all{it.id in 1..4} && values.map{it.id}.distinct().size==values.size)
        val snapshot=values.toList()
        commands.add{parts.replace(snapshot)}
    }
    fun noteOn(note:Int,velocity:Double){
        require(note in 0..127 && velocity.isFinite() && velocity in 0.0..1.0)
        commands.add{parts.noteOn(note,velocity)}
    }
    fun noteOff(note:Int){commands.add{parts.noteOff(note)}}
    fun clear(){commands.add{parts.reset();parts.replace(emptyList())}}
    fun renderInto(mixer:ImportedKeyboardMixer){
        while(true){val command=commands.poll() ?: break;command()}
        parts.renderInto(mixer)
    }
}
