package com.hovait.orgx

import java.io.File

/** Session restore data, separate from the imported SET and STS definitions. */
object ImportedStsModeState {
    const val preferenceKey="stsPartModes"
    const val singlePreferenceKey="stsSingleMode"
    const val partsPreferenceKey="stsEffectiveParts"
    fun encodeParts(file:File,slot:Int,parts:Map<Int,ImportedSts>):String =
        encode(file,slot,emptyMap())+parts.toSortedMap().entries.joinToString(";"){(position,part)->
            val s=part.settings
            listOf(position,part.instrument,part.mode,part.system,part.pedal,if(part.record)1 else 0,
                s.volume,s.pan,s.tune,s.transpose,s.octave,s.releasePercent).joinToString(",")
        }
    fun decodeParts(value:String,file:File,slot:Int,index:ImportedSetIndex):Map<Int,ImportedSts> {
        if(value.length>4096)return emptyMap()
        val prefix=encode(file,slot,emptyMap())
        if(!value.startsWith(prefix))return emptyMap()
        return try{
            val result=linkedMapOf<Int,ImportedSts>()
            for(row in value.removePrefix(prefix).split(';').filter{it.isNotEmpty()}){
                val v=row.split(',').map{it.toInt()}
                require(v.size==12 && v[0] in 1..4 && v[5] in 0..1 && v[6] in 0..200 && v[7] in -100..100)
                val track=index.tracks.filter{it.group==0}.getOrNull(v[0]) ?: error("Missing part")
                val incoming=track.sts.single{it.slot==slot}
                val instrument=index.instruments.getOrNull(v[1]) ?: error("Missing instrument")
                require(v[2]==-1 || v[2] in instrument.modes.indices || (v[2]==0 && instrument.modes.isEmpty()))
                require(index.systems.isEmpty() || v[3] in index.systems.indices)
                val selected=if(v[1]==incoming.instrument && v[2]==incoming.mode)incoming
                    else incoming.copy(settings=instrument.modes.getOrNull(v[2])?.settings ?: ImportedChannelSettings(),
                        effects=instrument.modes.getOrNull(v[2])?.effects.orEmpty())
                val restored=selected.copy(instrument=v[1],mode=v[2],system=v[3],pedal=v[4],record=v[5]==1,
                    settings=selected.settings.copy(volume=v[6],pan=v[7],tune=v[8],transpose=v[9],octave=v[10],releasePercent=v[11]))
                require(result.put(v[0],restored)==null)
            }
            result
        }catch(_:Exception){emptyMap()}
    }
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
