package com.hovait.orgx

object ImportedStsChangeSettings {
    const val preferenceKey="stsChangesMask"
    val labels=listOf("Tempo","Master System","Master Volume","Style / Keyboard Balance",
        "Transpose","Octave","Stability Percent","Music FX","Track Record","Track Instrument",
        "Track Pedal","Track DNC Mode","Track System","Track Volume","Track Balance",
        "Track Transpose","Track Octave","Track Stability Percent","Track FX","Track Effects",
        "One-key Chord")
    val defaultMask:Int get()=encode(ImportedStsPlan.defaultChanges)
    fun encode(values:List<Boolean>):Int {
        require(values.size==labels.size)
        return values.indices.fold(0){mask,index->if(values[index])mask or (1 shl index) else mask}
    }
    fun decode(mask:Int):List<Boolean> = List(labels.size){index->mask and (1 shl index)!=0}
}
