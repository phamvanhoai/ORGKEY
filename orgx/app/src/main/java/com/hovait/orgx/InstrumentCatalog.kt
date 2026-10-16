package com.hovait.orgx

object InstrumentCatalog {
    val names=listOf("Piano DNC","Organ DNC","Saw Muff DNC","Jazz Flute DNC","Finger Bass","Soprano Choir DNC","Violin DNC")
    val shortNames=listOf("Piano","Organ","Lead","Flute","Bass","Choir","Violin")
    fun validIndex(index:Int)=index.coerceIn(names.indices)
}
