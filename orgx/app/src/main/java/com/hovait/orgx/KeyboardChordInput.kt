package com.hovait.orgx

/** Exact pitch-class matching with bass-root preference and chord hold on release. */
class KeyboardChordInput {
    private val held=sortedSetOf<Int>()
    @Volatile var chord:Int?=null;private set
    @Synchronized fun press(note:Int,library:List<PlayableChord>) {
        if(note !in 0..127)return
        held.add(note)
        val mask=held.fold(0){value,key->value or (1 shl (key%12))}
        if(Integer.bitCount(mask)<3)return
        val candidates=library.indices.filter{index->
            library[index].notes.fold(0){value,key->value or (1 shl (key%12))}==mask
        }
        val bass=held.first()%12
        val match=candidates.firstOrNull{it%12==bass} ?: candidates.firstOrNull()
        if(match!=null)chord=match
    }
    @Synchronized fun release(note:Int):Boolean=held.remove(note)
    @Synchronized fun reset(){held.clear();chord=null}
}
