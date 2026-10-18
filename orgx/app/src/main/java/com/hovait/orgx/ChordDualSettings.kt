package com.hovait.orgx

/** Global user settings, separate from the SET's twelve ChordSingles entries. */
object ChordDualSettings {
    fun valid(key:Int,value:Int)=key in 0..4095 && Integer.bitCount(key)==2 && value in 1..4095
    fun encode(values:Map<Int,Int>):String {
        require(values.all{(key,value)->valid(key,value)})
        return values.toSortedMap().entries.joinToString(";"){"${it.key}=${it.value}"}
    }
    fun decode(text:String):Map<Int,Int> = buildMap {
        text.split(';').forEach{entry->
            val parts=entry.split('=')
            if(parts.size==2){
                val key=parts[0].toIntOrNull();val value=parts[1].toIntOrNull()
                if(key!=null && value!=null && valid(key,value))put(key,value)
            }
        }
    }
}
