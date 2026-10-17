package com.hovait.orgx

import java.util.concurrent.atomic.AtomicReference

/** Section changes are quantized to the end of the current section, not a guessed bar length. */
class ImportedStyleSession(val style:ImportedStyleDefinition,private val players:Map<Int,ReferenceRhythmPlayer>,sampleRate:Int=48000) {
    private val main=style.sections.first{it.section in 32..35 && it.id in players}
    private val clock=ReferenceStyleClock(sampleRate)
    private data class Request(val id:Int,val immediate:Boolean)
    private val pending=AtomicReference<Request?>(null)
    @Volatile var currentId=main.id;private set
    private var returnMain=main.id
    @Volatile var displayName="${style.name} / ${ImportedStyleCatalog.label(main.section)}";private set
    var finished=false;private set
    val player get()=players.getValue(currentId)
    val pendingId get()=pending.get()?.id
    fun available(id:Int)=id in players
    fun request(id:Int,immediate:Boolean=false){if(id in players)pending.set(Request(id,immediate))}
    private fun select(id:Int){
        currentId=id
        val section=style.sections.first{it.id==id}
        if(section.section in 32..35)returnMain=id
        displayName="${style.name} / ${ImportedStyleCatalog.label(section.section)}"
        player.reset();clock.reset();finished=false
    }
    fun reset(){pending.getAndSet(null)?.let{select(it.id)};player.reset();clock.reset();finished=false}
    fun next(tempo:Int){
        val command=pending.get()
        if(command?.immediate==true && pending.compareAndSet(command,null))select(command.id)
        if(finished)return
        val section=style.sections.first{it.id==currentId}
        if(clock.time>=section.end.toLong()-section.start){
            val requested=pending.getAndSet(null)
            when {
                requested!=null->select(requested.id)
                section.section in 80..82->{finished=true;player.reset();return}
                section.section !in 32..35->select(returnMain)
                else->clock.reset()
            }
        }
        player.next(0,tempo);clock.advance(1,tempo)
    }
}
