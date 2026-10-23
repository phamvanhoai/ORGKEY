package com.hovait.orgx

/** Track.Play(65, slot, parameter, value) for live expression processors. */
object ImportedExpressionAutomation {
    fun applyLegacyKeyConnectionMethod(effects:List<ImportedExpressionEffect>,sourceVersion:String,keyConnectionMethod:Int):List<ImportedExpressionEffect> {
        require(keyConnectionMethod in 0..1)
        if(compareVersions(sourceVersion,"1.4.3")>=0)return effects
        return effects.map{effect->if(effect.slot==1)effect.copy(mode=(effect.mode or keyConnectionMethod*16) and 255) else effect}
    }

    private fun compareVersions(left:String,right:String):Int {
        val a=left.split('.').map{it.toIntOrNull() ?: 0};val b=right.split('.').map{it.toIntOrNull() ?: 0}
        repeat(maxOf(a.size,b.size)){index->
            val compared=a.getOrElse(index){0}.compareTo(b.getOrElse(index){0})
            if(compared!=0)return compared
        }
        return 0
    }

    fun supported(event:ReferenceDrumEvent)=event.type==65 && event.id in 0..3 && event.note in 0..7
    fun update(effect:ImportedExpressionEffect,event:ReferenceDrumEvent,keyConnectionMethod:Int=0):ImportedExpressionEffect {
        require(effect.slot==event.id && supported(event))
        return when(event.note){
            0->effect.copy(enabled=event.touch!=0)
            1->effect.copy(startDelay=event.touch)
            2->effect.copy(byPedal=event.touch!=0)
            3->effect.copy(stopByRelease=event.touch!=0)
            4->effect.copy(speed=event.touch)
            5->effect.copy(depth=event.touch)
            6->effect.copy(gain=event.touch)
            else->effect.copy(mode=(event.touch or if(effect.slot==1)keyConnectionMethod*16 else 0) and 255)
        }
    }
}
