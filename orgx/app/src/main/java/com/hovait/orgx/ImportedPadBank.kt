package com.hovait.orgx

import java.util.concurrent.atomic.AtomicReferenceArray
import java.util.concurrent.atomic.AtomicLongArray

/** Owns a preloaded JumpGroup chain. No file access occurs when advancing it. */
class ImportedPadSequence(private val first:Int,private val groups:Map<Int,ImportedStyleGroup>,
    private val players:Map<Int,ReferenceRhythmPlayer>) {
    var currentId=first;private set
    private var startedAt=0L
    private var scaleChange:Int?=null
    fun takeScaleChange():Int?=scaleChange.also{scaleChange=null}
    val player get()=players.getValue(currentId)
    fun withMusicFade(controller:ReferenceFadeController){players.values.forEach{it.withMusicFade(controller)}}
    fun withMusicRelease(provider:()->Int){players.values.forEach{it.withMusicRelease(provider)}}
    fun withMusicState(state:ImportedMusicState){players.values.forEach{it.withMusicState(state)}}
    val group get()=groups.getValue(currentId)
    fun start(time:Long,scale:Int,method:Int){
        currentId=first
        val newScale=ReferenceSectionTiming.scale(group,method)
        scaleChange=newScale.takeIf{it>0}
        val offset=ReferenceSectionTiming.startOffset(time,group,if(newScale in 1..10)newScale else scale,method)
        startedAt=time-offset
        player.startAt(offset)
    }
    fun advance(time:Long,scale:Int,method:Int,tempo:Int):ReferenceRhythmPlayer {
        val complete=player.finished || (!group.loop && group.limit && group.end>group.start && time-startedAt>=group.end.toLong()-group.start)
        if(player.finished && group.loop){
            // _PlayGroup calls GroupPlayStart on exhaustion. GroupPlayNext keeps
            // a Loop group current, irrespective of its stored JumpGroup.
            val offset=ReferenceSectionTiming.startOffset(time,group,scale,method)
            startedAt=time-offset
            player.restartAt(offset)
        }
        else if(complete && !group.loop && group.jumpGroup>0 && ReferenceSectionTiming.inWindow(time,scale,tempo)){
            val next=group.jumpGroup
            if(next in players){
                val previous=player
                currentId=next
                val newScale=ReferenceSectionTiming.scale(group,method)
                scaleChange=newScale.takeIf{it>0}
                val offset=ReferenceSectionTiming.startOffset(time,group,if(newScale in 1..10)newScale else scale,method)
                startedAt=time-offset
                player.restartAt(offset)
                if(player!==previous){player.continueGroupFxFrom(previous);player.releaseVoicesFrom(previous)}
            }
        }
        player.next(0,tempo)
        return player
    }
    val awaitingContinuation get()=player.finished && (group.loop || (group.jumpGroup>0 && group.jumpGroup in players))
    fun close(){players.values.forEach{it.close()}}
}

/** Prepared players cross into the audio thread; PCM decoding stays with the caller. */
class ImportedPadBank(sampleRate:Int=48000,private val musicFade:ReferenceFadeController?=null,private val musicRelease:(()->Int)?=null,
    private val musicState:ImportedMusicState?=null) {
    private data class Change(val player:ReferenceRhythmPlayer?,val group:ImportedStyleGroup?=null,val sequence:ImportedPadSequence?=null){
        fun close(){if(sequence!=null)sequence.close() else player?.close()}
    }
    private val pending=AtomicReferenceArray<Change?>(4)
    private val generations=AtomicLongArray(4)
    private val commandLock=Any()
    @Volatile var loadingMask=0;private set
    fun beginLoad(slot:Int):Long{require(slot in 0..3);return synchronized(commandLock){loadingMask=loadingMask or (1 shl slot);generations.incrementAndGet(slot)}}
    fun finishLoad(slot:Int,token:Long)=synchronized(commandLock){if(generations.get(slot)==token)loadingMask=loadingMask and (1 shl slot).inv()}
    fun playPrepared(slot:Int,token:Long,player:ReferenceRhythmPlayer,group:ImportedStyleGroup):Boolean=synchronized(commandLock){
        require(slot in 0..3)
        if(generations.get(slot)!=token){player.close();false}
        else{pending.getAndSet(slot,Change(player,group))?.close();loadingMask=loadingMask and (1 shl slot).inv();true}
    }
    fun playPrepared(slot:Int,token:Long,sequence:ImportedPadSequence):Boolean=synchronized(commandLock){
        require(slot in 0..3)
        if(generations.get(slot)!=token){sequence.close();false}
        else{pending.getAndSet(slot,Change(sequence.player,sequence.group,sequence))?.close();loadingMask=loadingMask and (1 shl slot).inv();true}
    }
    private val players=arrayOfNulls<ReferenceRhythmPlayer>(4)
    private val sequences=arrayOfNulls<ImportedPadSequence>(4)
    private fun closeSlot(slot:Int){
        val sequence=sequences[slot]
        if(sequence!=null)sequence.close() else players[slot]?.close()
        sequences[slot]=null;players[slot]=null
    }
    private val tails=BooleanArray(4)
    private val waiting=arrayOfNulls<Change>(4)
    private val clock=ReferenceStyleClock(sampleRate)
    var rhythmScale=1000;private set
    var scaleChanged=false;private set
    private fun updateScale(scale:Int?){
        if(scale!=null && scale>0){rhythmScale=scale;scaleChanged=true}
    }
    @Volatile var pendingMask=0;private set
    @Volatile var runningMask=0;private set
    var left=0.0;private set
    var right=0.0;private set
    fun play(slot:Int,player:ReferenceRhythmPlayer,group:ImportedStyleGroup?=null){
        require(slot in 0..3)
        pending.getAndSet(slot,Change(player,group))?.close()
    }
    fun stop(slot:Int){require(slot in 0..3);synchronized(commandLock){generations.incrementAndGet(slot);loadingMask=loadingMask and (1 shl slot).inv();pending.getAndSet(slot,Change(null))?.close()}}
    fun stopAll(){repeat(4,::stop)}
    fun next(tempo:Int,drumGain:Float,chordGain:Float,styleTiming:Pair<Long,Int>?=null,method:Int=0){
        nextAt(tempo,drumGain,chordGain,styleTiming?.first ?: -1L,styleTiming?.second ?: rhythmScale,method)
    }
    /** Audio callback entry: primitive timing avoids Pair and boxed Long allocations per sample. */
    fun nextAt(tempo:Int,drumGain:Float,chordGain:Float,styleTime:Long=-1L,styleScale:Int=rhythmScale,method:Int=0){
        val time=if(styleTime>=0)styleTime else clock.time
        var scale=if(styleTime>=0)styleScale else rhythmScale
        rhythmScale=scale;scaleChanged=false
        left=0.0;right=0.0
        repeat(4){slot->
            pending.getAndSet(slot,null)?.let{change->
                musicState?.let{state->change.sequence?.withMusicState(state);change.player?.withMusicState(state)}
                musicRelease?.let{provider->change.sequence?.withMusicRelease(provider);change.player?.withMusicRelease(provider)}
                musicFade?.let{controller->change.sequence?.withMusicFade(controller);change.player?.withMusicFade(controller)}
                waiting[slot]?.close();waiting[slot]=null
                if(change.player==null){players[slot]?.releaseAll();tails[slot]=true}
                else waiting[slot]=change
            }
            waiting[slot]?.let{change->
                if(change.group==null || ReferenceSectionTiming.inWindow(time,scale,tempo)){
                    val previous=players[slot]
                    players[slot]=change.player;tails[slot]=false
                    val newScale=change.group?.let{ReferenceSectionTiming.scale(it,method)}
                    val offset=change.group?.let{ReferenceSectionTiming.startOffset(time,it,if(newScale!=null && newScale in 1..10)newScale else scale,method)} ?: 0L
                    if(change.sequence!=null)change.sequence.start(time,scale,method) else change.player?.startAt(offset)
                    updateScale(if(change.sequence!=null)change.sequence.takeScaleChange() else newScale)
                    scale=rhythmScale
                    if(previous!=null){
                        change.player?.continueGroupFxFrom(previous)
                        change.player?.releaseVoicesFrom(previous)
                        val previousSequence=sequences[slot]
                        if(previousSequence!=null)previousSequence.close() else previous.close()
                    }
                    sequences[slot]=change.sequence
                    waiting[slot]=null
                }
            }
            var player=players[slot] ?: return@repeat
            if(tails[slot])player.renderTail() else{
                val sequence=sequences[slot]
                if(sequence!=null){
                    player=sequence.advance(time,scale,method,tempo);players[slot]=player
                    updateScale(sequence.takeScaleChange());scale=rhythmScale
                }
                else player.next(0,tempo)
                if(player.finished && sequence?.awaitingContinuation!=true){player.releaseAll();tails[slot]=true}
            }
            player.mixOutput(drumGain,chordGain);left+=player.outputLeft;right+=player.outputRight
            if(tails[slot] && !player.hasTail){closeSlot(slot);tails[slot]=false}
        }
        runningMask=(0..3).fold(0){mask,slot->if(players[slot]!=null && !tails[slot])mask or (1 shl slot) else mask}
        pendingMask=(0..3).fold(0){mask,slot->if(waiting[slot]!=null || pending.get(slot)?.player!=null)mask or (1 shl slot) else mask}
        if(styleTime>=0)clock.seek(time) else clock.advance(1,tempo)
        if(runningMask==0 && pendingMask==0)clock.reset()
    }
    fun close(){repeat(4){pending.getAndSet(it,null)?.close();waiting[it]?.close();waiting[it]=null;closeSlot(it)};runningMask=0;pendingMask=0}
}
