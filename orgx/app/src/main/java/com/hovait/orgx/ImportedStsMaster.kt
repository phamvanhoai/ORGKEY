package com.hovait.orgx

/** Master events 222..225. Null tempo/chord means preserve the live value. */
object ImportedStsMaster {
    const val preferenceKey="stsEffectiveMaster"
    fun encode(file:java.io.File,slot:Int,state:Prepared):String {
        val bytes=java.io.ByteArrayOutputStream()
        java.io.DataOutputStream(bytes).use{out->
            val s=state.music
            listOf(s.volume,s.pan,s.tune,s.transpose,s.octave,s.releasePercent).forEach(out::writeInt)
            out.writeBoolean(s.fxEnabled);out.writeInt(s.modules.size)
            s.modules.forEach{m->out.writeInt(m.type);out.writeBoolean(m.enabled);out.writeBoolean(m.realtime)
                out.writeInt(m.payload.size);m.payload.forEach(out::writeByte)}
            out.writeInt(state.systems.size)
            state.systems.forEach{system->out.writeUTF(system.name);out.writeInt(system.startKey);out.writeInt(system.selectedKeys)
                out.writeInt(system.table.size);system.table.forEach(out::writeDouble)}
            out.writeInt(state.tempo ?: -1)
            out.writeBoolean(state.chord!=null)
            state.chord?.let{out.writeInt(it.chordType);out.writeInt(it.chordSingle.size);it.chordSingle.forEach(out::writeInt)}
        }
        return ImportedStsModeState.encode(file,slot,emptyMap())+java.util.Base64.getEncoder().encodeToString(bytes.toByteArray())
    }
    fun decode(value:String,file:java.io.File,slot:Int,index:ImportedSetIndex):Prepared? = try{
        val prefix=ImportedStsModeState.encode(file,slot,emptyMap())
        require(value.length<=2000000 && value.startsWith(prefix))
        val input=java.io.DataInputStream(java.io.ByteArrayInputStream(java.util.Base64.getDecoder().decode(value.removePrefix(prefix))))
        input.use{r->
            val values=List(6){r.readInt()}
            val enabled=r.readBoolean()
            fun count(max:Int)=r.readInt().also{require(it in 0..max)}
            val modules=List(count(1000)){ImportedFxModule(r.readInt(),r.readBoolean(),r.readBoolean(),List(count(512)){r.readUnsignedByte()})}
            val music=ImportedChannelSettings(values[0],values[1],values[2],values[3],values[4],values[5],enabled,modules)
            require(music.volume in 0..200 && music.pan in -100..100)
            val systems=List(count(10000)){ImportedSystem(r.readUTF(),r.readInt(),r.readInt(),List(count(24)){r.readDouble().also{require(it.isFinite())}})}
            require(systems.size==index.systems.size && systems.all{it.table.size==24})
            val tempo=r.readInt().let{if(it==-1)null else it.also{require(it in 40..240)}}
            val chord=if(r.readBoolean()){
                val type=r.readInt();val singles=List(count(12)){r.readInt()}
                ImportedStsChordSettings.validate(type,singles)
                ImportedStsSelection.resolve(index,0,slot).copy(chordType=type,chordSingle=singles)
            }else null
            require(r.available()==0)
            Prepared(music,systems,tempo,chord)
        }
    }catch(_:Exception){null}
    data class Prepared(val music:ImportedChannelSettings,val systems:List<ImportedSystem>,
        val tempo:Int?,val chord:ImportedSts?)
    /** Persistence captures live values; apply plans intentionally use null to mean unchanged. */
    fun capture(current:Prepared?,index:ImportedSetIndex,master:ImportedSts,music:ImportedChannelSettings?,
        tempo:Int,chordMode:Int,singles:List<Int>):Prepared {
        require(tempo in 40..240)
        ImportedStsChordSettings.validate(chordMode,singles)
        return Prepared(music ?: current?.music ?: index.musicSettings,
            (current?.systems ?: index.systems).toList(),tempo,
            master.copy(chordType=chordMode,chordSingle=singles.toList()))
    }
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
