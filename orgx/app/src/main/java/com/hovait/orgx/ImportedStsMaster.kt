package com.hovait.orgx

/** Master events 222..225. Null tempo/chord means preserve the live value. */
object ImportedStsMaster {
    data class Prepared(val music:ImportedChannelSettings,val systems:List<ImportedSystem>,
        val tempo:Int?,val chord:ImportedSts?)
    fun prepare(index:ImportedSetIndex,snapshot:ImportedSts,changes:List<Boolean>):Prepared {
        require(changes.size==21)
        // Event 224 uses flags 2..7 with the same z01.e order as track flags 13..18.
        val channelMask=MutableList(21){false}
        for(i in 2..7)channelMask[i+11]=changes[i]
        val music=ImportedStsChanges.channel(index.musicSettings,snapshot.settings,channelMask)
        val systems=if(changes[1] && snapshot.system>0 && snapshot.system in index.systems.indices){
            index.systems.mapIndexed{i,system->if(i==0)system.copy(
                name=index.systems[snapshot.system].name,table=index.systems[snapshot.system].table.toList()) else system}
        }else index.systems.toList()
        if(changes[20])ImportedStsChordSettings.validate(snapshot.chordType,snapshot.chordSingle)
        if(changes[0])require(snapshot.data1>0){"Invalid STS Master tempo"}
        return Prepared(music,systems,if(changes[0])snapshot.data1 else null,if(changes[20])snapshot else null)
    }
}
