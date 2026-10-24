package com.hovait.orgx

/** Tempo edits belong to the opened SET, not its immutable decoded definitions. */
class ImportedStyleTempoMemory {
    private var bank:Any?=null
    private val tempos=HashMap<Int,Int>()
    private fun bind(value:Any?){if(bank!==value){bank=value;tempos.clear()}}
    @Synchronized fun get(value:Any?,style:Int):Int?{bind(value);return tempos[style]}
    @Synchronized fun remember(value:Any?,style:Int,tempo:Int){require(tempo in 40..240);bind(value);tempos[style]=tempo}
}

/** GroupPlayAdd/Next/Start rules for supported, bounded style groups. */
class ImportedStyleSession(val style:ImportedStyleDefinition,private val players:Map<Int,ReferenceRhythmPlayer>,sampleRate:Int=48000,
    private var changeMethod:Int=0,private var tempoLock:Boolean=false,linkedGroups:List<ImportedStyleGroup> = emptyList()) {
    private val groups=(style.sections+linkedGroups).associateBy{it.id}
    private val main=style.sections.first{it.section in 32..35 && it.id in players}
    private val clock=ReferenceStyleClock(sampleRate)
    private data class Request(val id:Int,val immediate:Boolean,val mute:Int=0)
    private val queue=ArrayDeque<Request>()
    private var jumpId:Int?=null
    private val temporaryLoops=HashSet<Int>()
    val temporaryLoop get()=synchronized(this){currentId in temporaryLoops}
    private var lastLoop:Int?=main.id.takeIf{main.loop}
    private var startedAt=0L
    private var rhythmScale=ReferenceSectionTiming.scale(main,changeMethod).takeIf{it>0} ?: 1000
    private var tempoChange:Int?=null
    private var manualTempo:Int?=null
    @Synchronized internal fun restoreManualTempo(tempo:Int?){
        require(tempo==null || tempo in 40..240)
        manualTempo=tempo;tempoChange=null
    }
    val selectedTempo:Int get()=synchronized(this){
        val section=groups.getValue(currentId)
        if(section.parent==style.id)manualTempo ?: section.tempo else section.tempo
    }
    @Synchronized fun setManualTempo(tempo:Int){
        require(tempo in 40..240)
        tempoChange=null
        if(!tempoLock)manualTempo=tempo
    }
    @Volatile var currentId=main.id;private set
    @Volatile var displayName="${style.name} / ${ImportedStyleCatalog.label(main.section)}";private set
    @Volatile var transitionError:String?=null;private set
    var finished=false;private set
    val player get()=players.getValue(currentId)
    val padTiming:Pair<Long,Int> get()=synchronized(this){clock.time to rhythmScale}
    /** GroupPlayNext shares Music.rhythmScale across the main group and PAD slots. */
    @Synchronized fun applyPadRhythmScale(scale:Int){require(scale>0);rhythmScale=scale}
    val pendingId get()=synchronized(this){queue.firstOrNull{it.mute==0}?.id}
    @Volatile var chordMuted=false;private set
    @Volatile var drumMuted=false;private set
    @Synchronized fun restorePerformanceMute(chord:Boolean,drum:Boolean){
        chordMuted=chord;drumMuted=drum
        players.values.forEach{it.setPerformanceMute(chord,drum)}
    }
    @Synchronized fun togglePerformanceMute(chord:Boolean,running:Boolean){
        if(running){if(queue.size<=8)queue.addLast(Request(0,false,if(chord)1 else 2))}
        else restorePerformanceMute(if(chord)!chordMuted else chordMuted,if(chord)drumMuted else !drumMuted)
    }
    fun available(id:Int)=id in players
    fun close(){players.values.forEach{it.close()}}
    @Synchronized fun stopPlayback(preserveImmediate:Boolean=false){
        val immediate=queue.firstOrNull()?.takeIf{preserveImmediate && it.immediate}
        queue.clear();if(immediate!=null)queue.addLast(immediate) else jumpId=null
        temporaryLoops.clear();tempoChange=null
        finished=true;player.releaseAll()
    }
    @Synchronized fun renderTail(){if(finished)player.renderTail()}
    val hasTail get()=synchronized(this){player.hasTail}
    /** Play after a natural ending must clear finished while retaining an immediate section choice. */
    @Synchronized fun preparePlay(){if(finished)reset()}
    /** MainActivity.B1 keeps the section, with Fill/Break returning to Main. */
    fun continueFrom(previous:ImportedStyleSession){
        val (section,time)=synchronized(previous){previous.groups.getValue(previous.currentId).section to previous.clock.time}
        synchronized(this){
            val wanted=if(section in 48..64)32+(section and 15) else section
            val target=style.sections.firstOrNull{it.section==wanted && available(it.id)}
                ?: style.sections.firstOrNull{it.section==32 && available(it.id)} ?: main
            queue.clear();jumpId=null;clock.seek(time);select(target.id)
            player.continueGroupFxFrom(previous.player)
            player.releaseVoicesFrom(previous.player)
        }
    }
    @Synchronized fun request(id:Int,immediate:Boolean=false){
        if(id !in players)return
        temporaryLoops.remove(id)
        if(immediate){queue.clear();queue.addLast(Request(id,true));return}
        val previous=queue.lastOrNull{it.mute==0}?.id ?: currentId
        if(previous==id && groups.getValue(id).loop)return
        if(queue.size<=8)queue.addLast(Request(id,false))
    }
    @Synchronized fun jump(id:Int){if(id in players){temporaryLoops.remove(id);jumpId=id}}
    /** MainActivity.y1: Main + Auto Fill emits Rhythm(fill), then RhythmJump(main). */
    @Synchronized fun press(id:Int,running:Boolean,autoFill:Boolean,repeatToLoop:Boolean=false){
        if(id !in players)return
        val group=groups.getValue(id)
        // A physical Intro button is a new one-shot request. Do not let a
        // previously queued Intro/Fill continue after it; the selected intro
        // must finish once and then return to the last Main variation.
        if(running && group.section in 16..18) {
            queue.clear()
            jumpId=lastLoop ?: main.id
        }
        if(repeatToLoop && running && (queue.lastOrNull()?.id ?: currentId)==id){
            if(!group.loop && (group.limit || group.end-group.start>10))temporaryLoops.add(id)
            return
        }
        val fill=if(running && autoFill && group.section in 32..35)
            style.sections.firstOrNull{it.section==group.section-32+48 && it.id in players} else null
        if(fill!=null){request(fill.id);jumpId=id}else {
            request(id,immediate=!running)
            if(group.section in 16..18)jumpId=lastLoop ?: main.id
        }
    }
    @Synchronized fun takeTempoChange():Int?=tempoChange.also{tempoChange=null}
    @Synchronized fun configureTiming(method:Int,locked:Boolean){
        require(method in 0..3)
        if(changeMethod!=method){
            changeMethod=method
            val scale=ReferenceSectionTiming.scale(groups.getValue(currentId),method)
            if(scale>0)rhythmScale=scale
        }
        tempoLock=locked
        if(locked)tempoChange=null
    }
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
        player.restartAt(offset)
        player.continueGroupFxFrom(previous)
        player.releaseVoicesFrom(previous)
        if(newScale>0)rhythmScale=newScale
        val tempo=if(section.parent==style.id)manualTempo ?: section.tempo else section.tempo
        if(!tempoLock && tempo>0)tempoChange=tempo.coerceIn(40,240)
        displayName="${style.name} / ${ImportedStyleCatalog.label(section.section)}"
        finished=false;transitionError=null
    }
    @Synchronized fun reset(){
        val immediate=queue.firstOrNull()?.takeIf{it.immediate}
        queue.clear();if(immediate==null)jumpId=null;clock.reset();startedAt=0;tempoChange=null
        if(immediate!=null)select(immediate.id)
        players.values.forEach{it.reset()};finished=false
    }
    @Synchronized fun next(tempo:Int){
        if(queue.firstOrNull()?.immediate==true){clock.reset();select(queue.removeFirst().id)}
        if(finished)return
        val section=groups.getValue(currentId)
        val completed=if(section.limit)clock.time-startedAt>=section.end.toLong()-section.start else player.finished
        if(section.type==1 && ReferenceSectionTiming.inWindow(clock.time,rhythmScale,tempo)){
            val loop=section.loop || currentId in temporaryLoops
            // Tracks.PlayNext dispatches a queued Rhythm at the next scale window
            // even while a one-shot Intro/Fill/Ending still has events remaining.
            if(queue.isNotEmpty()){
                val request=queue.removeFirst()
                if(request.mute!=0){
                    restorePerformanceMute(if(request.mute==1)!chordMuted else chordMuted,if(request.mute==2)!drumMuted else drumMuted)
                }else select(request.id)
            }
            else if(completed && !loop){
                val target=jumpId ?: when {
                    section.jumpGroup<0->lastLoop
                    section.jumpGroup>0->section.jumpGroup
                    else->null
                }
                jumpId=null
                if(target==null){finished=true;player.releaseAll();player.renderTail();return}else select(target)
            }
            else if(completed && ((!section.limit && section.loop) || currentId in temporaryLoops))select(currentId)
        }
        if(finished)return
        val effectiveTempo=tempoChange ?: tempo
        player.next(0,effectiveTempo);clock.advance(1,effectiveTempo)
    }
}
