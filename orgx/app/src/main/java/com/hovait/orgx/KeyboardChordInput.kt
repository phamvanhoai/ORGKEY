package com.hovait.orgx

/** k92: arm stop only after a recognised held chord; memory alone cannot trigger start. */
class ChordSynchro {
    private var stopArmed=false
    @Synchronized fun update(start:Boolean,stop:Boolean,recognised:Boolean,count:Int,minimum:Int,running:Boolean):Boolean?{
        if(!stop)stopArmed=false
        if(recognised){
            if(stop)stopArmed=true
            if(start && !running && count>=minimum)return true
        }else if(stop && stopArmed){
            stopArmed=false
            if(running)return false
        }
        return null
    }
}

/** Original ChordGet/ChordFind recognition and chord hold on release. */
class KeyboardChordInput {
    private val held=sortedSetOf<Int>()
    private var mode=0
    private var duals:Map<Int,Int> = emptyMap()
    private var singles:List<Int> = emptyList()
    private var lastLibrary:List<PlayableChord> = emptyList()
    @Volatile var chord:Int?=null;private set
    @Volatile var octaveMove=0;private set
    @Volatile var pressedChord:Int?=null;private set
    @Volatile var pitchMask=0;private set
    val heldKeyCount:Int get()=synchronized(this){held.size}
    @Synchronized fun configureSingleFinger(enabled:Boolean,masks:List<Int>,library:List<PlayableChord>){
        configureChordMode(if(enabled)1 else 0,masks,library)
    }
    @Synchronized fun configureChordMode(value:Int,masks:List<Int>,library:List<PlayableChord>,dualMasks:Map<Int,Int> = emptyMap()){
        require(value!=1 || (masks.size==12 && masks.all{it in 0..4095})){"Invalid STS single-finger chord table"}
        require(dualMasks.all{(key,mask)->key in 0..4095 && Integer.bitCount(key)==2 && mask in 0..4095}){"Invalid two-key chord table"}
        mode=value
        if(value==1)singles=masks.toList()
        duals=dualMasks.toMap()
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
        if(held.isEmpty()){pressedChord=null;pitchMask=0;return}
        var mask=if(mode==1)singles[held.first()%12] else ReferenceChordInput.mask(held)
        if(mode==2 && Integer.bitCount(mask)==2){
            val mapped=duals[mask]
            if(mapped!=null && ReferenceChordMatcher.find(mapped,library)!=null)mask=mapped
        }
        pitchMask=mask
        pressedChord=ReferenceChordMatcher.find(mask,library)
        pressedChord?.let{chord=it;octaveMove=held.last()/12}
    }
    @Synchronized fun release(note:Int):Boolean {
        val removed=held.remove(note)
        if(removed)detect(lastLibrary)
        return removed
    }
    @Synchronized fun reset(){held.clear();chord=null;pressedChord=null;octaveMove=0;pitchMask=0}
}
