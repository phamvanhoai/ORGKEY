package com.hovait.orgx

/** Exact pitch-class matching with bass-root preference and chord hold on release. */
class KeyboardChordInput {
    private val held=sortedSetOf<Int>()
    private var singleFinger=false
    private var singles:List<Int> = emptyList()
    private var lastLibrary:List<PlayableChord> = emptyList()
    @Volatile var chord:Int?=null;private set
    @Synchronized fun configureSingleFinger(enabled:Boolean,masks:List<Int>,library:List<PlayableChord>){
        require(!enabled || (masks.size==12 && masks.all{it in 0..4095})){"Invalid STS single-finger chord table"}
        singleFinger=enabled
        if(enabled)singles=masks.toList()
        lastLibrary=library
        detect(library)
    }
    @Synchronized fun press(note:Int,library:List<PlayableChord>) {
        if(note !in 0..127)return
        held.add(note)
        lastLibrary=library
        detect(library)
    }
    private fun detect(library:List<PlayableChord>){
        if(held.isEmpty())return
        val mask=if(singleFinger)singles[held.first()%12] else held.fold(0){value,key->value or (1 shl (key%12))}
        if(singleFinger){
            ReferenceChordMatcher.find(mask,library)?.let{chord=it}
            return
        }
        if(Integer.bitCount(mask)<3)return
        val candidates=library.indices.filter{index->
            library[index].notes.fold(0){value,key->value or (1 shl (key%12))}==mask
        }
        val bass=held.first()%12
        val match=candidates.firstOrNull{it%12==bass} ?: candidates.firstOrNull()
        if(match!=null)chord=match
    }
    @Synchronized fun release(note:Int):Boolean {
        val removed=held.remove(note)
        if(removed && singleFinger)detect(lastLibrary)
        return removed
    }
    @Synchronized fun reset(){held.clear();chord=null}
}
