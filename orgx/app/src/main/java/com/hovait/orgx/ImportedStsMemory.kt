package com.hovait.orgx

/** PCM arrays plus largest temporary raw read, across independently decoded parts. */
object ImportedStsMemory {
    const val LIMIT=64L*1024*1024
    fun required(index:ImportedSetIndex,snapshots:Collection<ImportedSts>):Long {
        var resident=0L
        var scratch=0L
        snapshots.forEach{snapshot->
            val source=ImportedStsLoader.source(index,snapshot)
            val layers=if(source.modes.isEmpty())listOf(source) else
                (ImportedInstrumentLoader.layers(source)+
                    if(source.modes.first().aftertouch>=0)ImportedInstrumentLoader.layers(source,true) else emptyList()).distinct()
            layers.forEach{layer->layer.sounds.first().sections.forEach{section->
                require(section.frames>0 && section.channels in 1..2 && section.wave.bytes>0){"Invalid STS PCM shape"}
                val arrays=section.frames.toLong()*(if(section.channels==2)6 else 2)
                resident=Math.addExact(resident,arrays)
                scratch=maxOf(scratch,section.wave.bytes.toLong())
            }}
        }
        return Math.addExact(resident,scratch)
    }
    fun check(index:ImportedSetIndex,snapshots:Collection<ImportedSts>){
        require(required(index,snapshots)<=LIMIT){"STS group exceeds 64 MB PCM memory budget"}
    }
}
