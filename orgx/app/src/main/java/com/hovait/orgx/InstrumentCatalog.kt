package com.hovait.orgx

object InstrumentCatalog {
    val names=listOf("Piano DNC","Organ DNC","Saw Muff DNC","Jazz Flute DNC")
    val shortNames=listOf("Piano","Organ","Lead","Flute")
    fun validIndex(index:Int)=index.coerceIn(names.indices)
}
