package com.hovait.orgx

/** GroupPlayAdd/Next/Start rules for supported, bounded style groups. */
class ImportedStyleSession(val style:ImportedStyleDefinition,private val players:Map<Int,ReferenceRhythmPlayer>,sampleRate:Int=48000,
    private val changeMethod:Int=0,private val tempoLock:Boolean=false) {
    private val groups=style.sections.associateBy{it.id}
    private val main=style.sections.first{it.section in 32..35 && it.id in players}
    private val clock=ReferenceStyleClock(sampleRate)
    private data class Request(val id:Int,val immediate:Boolean)
    private val queue=ArrayDeque<Request>()
    private var jumpId:Int?=null
    private var lastLoop:Int?=main.id.takeIf{main.loop}
    private var startedAt=0L
    private var rhythmScale=ReferenceSectionTiming.scale(main,changeMethod).takeIf{it>0} ?: 1000
    private var tempoChange:Int?=null
    @Volatile var currentId=main.id;private set
    @Volatile var displayName="${style.name} / ${ImportedStyleCatalog.label(main.section)}";private set
    @Volatile var transitionError:String?=null;private set
    var finished=false;private set
    val player get()=players.getValue(currentId)
    val pendingId get()=synchronized(this){queue.firstOrNull()?.id}
    fun available(id:Int)=id in players
    /** MainActivity.B1 keeps the section, with Fill/Break returning to Main. */
    fun continueFrom(previous:ImportedStyleSession){
        val (section,time)=synchronized(previous){previous.groups.getValue(previous.currentId).section to previous.clock.time}
        synchronized(this){
            val wanted=if(section in 48..64)32+(section and 15) else section
            val target=style.sections.firstOrNull{it.section==wanted && available(it.id)}
                ?: style.sections.firstOrNull{it.section==32 && available(it.id)} ?: main
            queue.clear();jumpId=null;clock.seek(time);select(target.id)
            player.continueGroupFxFrom(previous.player)
        }
    }
    @Synchronized fun request(id:Int,immediate:Boolean=false){
        if(id !in players)return
        if(immediate){queue.clear();queue.addLast(Request(id,true));return}
        val previous=queue.lastOrNull()?.id ?: currentId
        if(previous==id && groups.getValue(id).loop)return
        if(queue.size<=8)queue.addLast(Request(id,false))
    }
    @Synchronized fun jump(id:Int){if(id in players)jumpId=id}
    /** MainActivity.y1: Main + Auto Fill emits Rhythm(fill), then RhythmJump(main). */
    @Synchronized fun press(id:Int,running:Boolean,autoFill:Boolean){
        if(id !in players)return
        val group=groups.getValue(id)
        val fill=if(running && autoFill && group.section in 32..35)
            style.sections.firstOrNull{it.section==group.section-32+48 && it.id in players} else null
        if(fill!=null){request(fill.id);jumpId=id}else {
            request(id,immediate=!running)
            if(group.section in 16..18)jumpId=lastLoop ?: main.id
        }
    }
    @Synchronized fun takeTempoChange():Int?=tempoChange.also{tempoChange=null}
    private fun select(id:Int){
        val section=groups[id]
        if(section==null || id !in players){
            transitionError="SET jump target $id is unavailable";finished=true;player.reset();return
        }
        val previous=player
        currentId=id
        if(section.loop)lastLoop=id
        val newScale=ReferenceSectionTiming.scale(section,changeMethod)
        if(newScale in 1..10)rhythmScale=newScale
        val offset=ReferenceSectionTiming.startOffset(clock.time,section,rhythmScale,changeMethod)
        startedAt=clock.time-offset
        player.startAt(offset,preserveGroupFx=previous===player)
        player.continueGroupFxFrom(previous)
        if(newScale>0)rhythmScale=newScale
        if(!tempoLock && section.tempo>0)tempoChange=section.tempo.coerceIn(40,240)
        displayName="${style.name} / ${ImportedStyleCatalog.label(section.section)}"
        finished=false;transitionError=null
    }
    @Synchronized fun reset(){
        val immediate=queue.firstOrNull()?.takeIf{it.immediate}
        queue.clear();if(immediate==null)jumpId=null;clock.reset();startedAt=0;tempoChange=null
        if(immediate!=null)select(immediate.id)
        player.reset();finished=false
    }
    @Synchronized fun next(tempo:Int){
        if(queue.firstOrNull()?.immediate==true){clock.reset();select(queue.removeFirst().id)}
        if(finished)return
        val section=groups.getValue(currentId)
        val completed=if(section.limit)clock.time-startedAt>=section.end.toLong()-section.start else player.finished
        if(section.type==1 && ReferenceSectionTiming.inWindow(clock.time,rhythmScale,tempo)){
            if(queue.isNotEmpty() && (section.loop || completed))select(queue.removeFirst().id)
            else if(completed && !section.loop){
                val target=jumpId ?: when {
                    section.jumpGroup<0->lastLoop
                    section.jumpGroup>0->section.jumpGroup
                    else->null
                }
                jumpId=null
                if(target==null){finished=true;player.reset();return}else select(target)
            }
            else if(completed && !section.limit && section.loop)select(currentId)
        }
        if(finished)return
        val effectiveTempo=tempoChange ?: tempo
        player.next(0,effectiveTempo);clock.advance(1,effectiveTempo)
    }
}
