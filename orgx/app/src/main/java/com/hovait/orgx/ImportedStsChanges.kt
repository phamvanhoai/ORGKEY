package com.hovait.orgx

/** Track event 64 -> z01.e: merge raw settings before global pitch is added. */
object ImportedStsChanges {
    fun transition(index:ImportedSetIndex,position:Int,current:ImportedSts,incoming:ImportedSts,
        actions:List<ImportedStsPlan.Action>,mask:List<Boolean>):ImportedSts {
        var base=current
        fun modeSet(value:Int){
            val instrument=index.instruments.getOrNull(base.instrument) ?: error("STS instrument unavailable")
            val mode=instrument.modes.getOrNull(value)
            base=base.copy(mode=if(mode==null)-1 else value,
                settings=mode?.settings ?: ImportedChannelSettings(),effects=mode?.effects ?: emptyList(),sourceVersion=instrument.sourceVersion ?: base.sourceVersion)
        }
        actions.filterIsInstance<ImportedStsPlan.Action.Event>().filter{it.track==position}.forEach{event->
            when(event.type){
                62->{
                    require(event.key>0 && event.key in index.instruments.indices){"STS instrument transition unavailable"}
                    base=base.copy(instrument=event.key)
                    if(event.id!=0)modeSet(0)
                }
                61->if(event.id==0)base=base.copy(mode=event.key) else modeSet(event.key)
            }
        }
        // Selection was already resolved by handlers, including ModeSet fallback.
        val keepSelection=mask.mapIndexed{i,value->value && i!=9 && i!=11}
        return track(base,incoming,keepSelection)
    }
    fun channel(current:ImportedChannelSettings,incoming:ImportedChannelSettings,mask:List<Boolean>):ImportedChannelSettings {
        require(mask.size==21)
        return current.copy(
            volume=if(mask[13])incoming.volume else current.volume,
            pan=if(mask[14])incoming.pan else current.pan,
            // z01.d (Tune) is not copied by e(); do not infer a Tune flag.
            transpose=if(mask[15])incoming.transpose else current.transpose,
            octave=if(mask[16])incoming.octave else current.octave,
            releasePercent=if(mask[17])incoming.releasePercent else current.releasePercent,
            fxEnabled=if(mask[18])incoming.fxEnabled else current.fxEnabled,
            modules=if(mask[18])incoming.modules.toList() else current.modules.toList())
    }
    fun track(current:ImportedSts,incoming:ImportedSts,mask:List<Boolean>):ImportedSts {
        require(mask.size==21)
        return current.copy(name=incoming.name,slot=incoming.slot,
            record=if(mask[8])incoming.record else current.record,
            instrument=if(mask[9])incoming.instrument else current.instrument,
            pedal=if(mask[10])incoming.pedal else current.pedal,
            mode=if(mask[11])incoming.mode else current.mode,
            system=if(mask[12])incoming.system else current.system,
            settings=channel(current.settings,incoming.settings,mask),
            effects=if(mask[19])incoming.effects.toList() else current.effects.toList(),
            sourceVersion=if(mask[19])incoming.sourceVersion else current.sourceVersion)
    }
}
