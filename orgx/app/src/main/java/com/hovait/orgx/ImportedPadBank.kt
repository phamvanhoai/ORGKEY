package com.hovait.orgx

import java.util.concurrent.atomic.AtomicReferenceArray

/** Prepared players cross into the audio thread; PCM decoding stays with the caller. */
class ImportedPadBank(sampleRate:Int=48000) {
    private data class Change(val player:ReferenceRhythmPlayer?,val group:ImportedStyleGroup?=null)
    private val pending=AtomicReferenceArray<Change?>(4)
    private val players=arrayOfNulls<ReferenceRhythmPlayer>(4)
    private val tails=BooleanArray(4)
    private val waiting=arrayOfNulls<Change>(4)
    private val clock=ReferenceStyleClock(sampleRate)
    @Volatile var pendingMask=0;private set
    @Volatile var runningMask=0;private set
    var left=0.0;private set
    var right=0.0;private set
    fun play(slot:Int,player:ReferenceRhythmPlayer,group:ImportedStyleGroup?=null){
        require(slot in 0..3)
        pending.getAndSet(slot,Change(player,group))?.player?.close()
    }
    fun stop(slot:Int){require(slot in 0..3);pending.getAndSet(slot,Change(null))?.player?.close()}
    fun stopAll(){repeat(4,::stop)}
    fun next(tempo:Int,drumGain:Float,chordGain:Float,styleTiming:Pair<Long,Int>?=null,method:Int=0){
        val time=styleTiming?.first ?: clock.time
        val scale=styleTiming?.second ?: 1000
        left=0.0;right=0.0
        repeat(4){slot->
            pending.getAndSet(slot,null)?.let{change->
                waiting[slot]?.player?.close();waiting[slot]=null
                if(change.player==null){players[slot]?.releaseAll();tails[slot]=true}
                else waiting[slot]=change
            }
            waiting[slot]?.let{change->
                if(change.group==null || ReferenceSectionTiming.inWindow(time,scale,tempo)){
                    players[slot]?.close();players[slot]=change.player;tails[slot]=false
                    val offset=change.group?.let{ReferenceSectionTiming.startOffset(time,it,scale,method)} ?: 0L
                    change.player?.startAt(offset);waiting[slot]=null
                }
            }
            val player=players[slot] ?: return@repeat
            if(tails[slot])player.renderTail() else{
                player.next(0,tempo)
                if(player.finished){player.releaseAll();tails[slot]=true}
            }
            player.mixOutput(drumGain,chordGain);left+=player.outputLeft;right+=player.outputRight
            if(tails[slot] && !player.hasTail){player.close();players[slot]=null;tails[slot]=false}
        }
        runningMask=(0..3).fold(0){mask,slot->if(players[slot]!=null && !tails[slot])mask or (1 shl slot) else mask}
        pendingMask=(0..3).fold(0){mask,slot->if(waiting[slot]!=null || pending.get(slot)?.player!=null)mask or (1 shl slot) else mask}
        if(styleTiming!=null)clock.seek(time) else clock.advance(1,tempo)
        if(runningMask==0 && pendingMask==0)clock.reset()
    }
    fun close(){repeat(4){pending.getAndSet(it,null)?.player?.close();waiting[it]?.player?.close();waiting[it]=null;players[it]?.close();players[it]=null};runningMask=0;pendingMask=0}
}
