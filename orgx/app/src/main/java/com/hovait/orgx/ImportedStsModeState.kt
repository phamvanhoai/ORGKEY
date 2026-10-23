package com.hovait.orgx

import java.io.File

/** Session restore data, separate from the imported SET and STS definitions. */
object ImportedStsModeState {
    const val preferenceKey="stsPartModes"
    const val singlePreferenceKey="stsSingleMode"
    fun encodeSingle(file:File,track:Int,snapshot:ImportedSts):String =
        "$track:${snapshot.instrument}|"+encode(file,snapshot.slot,mapOf(1 to snapshot.mode))
    fun restoreSingle(value:String,file:File,track:Int,index:ImportedSetIndex,snapshot:ImportedSts):ImportedSts {
        val prefix="$track:${snapshot.instrument}|"
        if(!value.startsWith(prefix))return snapshot
        return restore(index,snapshot,decode(value.removePrefix(prefix),file,snapshot.slot)[1])
    }
    fun encode(file:File,slot:Int,modes:Map<Int,Int>):String =
        "${file.name}:${file.length()}:${file.lastModified()}:$slot|"+
            modes.toSortedMap().entries.joinToString(","){"${it.key}=${it.value}"}
    fun decode(value:String,file:File,slot:Int):Map<Int,Int> {
        if(value.length>1024)return emptyMap()
        val expected=encode(file,slot,emptyMap())
        if(!value.startsWith(expected))return emptyMap()
        val result=linkedMapOf<Int,Int>()
        for(item in value.removePrefix(expected).split(',').filter{it.isNotEmpty()}){
            val parts=item.split('=')
            if(parts.size!=2)return emptyMap()
            val position=parts[0].toIntOrNull() ?: return emptyMap()
            val mode=parts[1].toIntOrNull() ?: return emptyMap()
            if(position !in 1..4 || mode !in -1..255 || result.put(position,mode)!=null)return emptyMap()
        }
        return result
    }
    fun restore(index:ImportedSetIndex,snapshot:ImportedSts,mode:Int?):ImportedSts {
        if(mode==null || mode==snapshot.mode)return snapshot
        val target=index.instruments.getOrNull(snapshot.instrument)?.modes?.getOrNull(mode) ?: return snapshot
        return snapshot.copy(mode=mode,settings=target.settings,effects=target.effects)
    }
}
