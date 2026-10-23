package com.hovait.orgx

import java.util.concurrent.atomic.AtomicReferenceArray

/** Prepared players cross into the audio thread; PCM decoding stays with the caller. */
class ImportedPadBank {
    private data class Change(val player:ReferenceRhythmPlayer?)
    private val pending=AtomicReferenceArray<Change?>(4)
    private val players=arrayOfNulls<ReferenceRhythmPlayer>(4)
    private val tails=BooleanArray(4)
    @Volatile var runningMask=0;private set
    var left=0.0;private set
    var right=0.0;private set
    fun play(slot:Int,player:ReferenceRhythmPlayer){
        require(slot in 0..3)
        pending.getAndSet(slot,Change(player))?.player?.close()
    }
    fun stop(slot:Int){require(slot in 0..3);pending.getAndSet(slot,Change(null))?.player?.close()}
    fun stopAll(){repeat(4,::stop)}
    fun next(tempo:Int,drumGain:Float,chordGain:Float){
        left=0.0;right=0.0
        repeat(4){slot->
            pending.getAndSet(slot,null)?.let{change->
                if(change.player==null){players[slot]?.releaseAll();tails[slot]=true}
                else{players[slot]?.close();players[slot]=change.player;tails[slot]=false;change.player.reset()}
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
    }
    fun close(){repeat(4){pending.getAndSet(it,null)?.player?.close();players[it]?.close();players[it]=null};runningMask=0}
}
