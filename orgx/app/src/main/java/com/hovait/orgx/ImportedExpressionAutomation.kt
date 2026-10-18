package com.hovait.orgx

/** Track.Play(65, slot, parameter, value): only implemented slot 3 fields. */
object ImportedExpressionAutomation {
    fun supported(event:ReferenceDrumEvent)=event.type==65 && event.id in 2..3 && event.note in 0..6
    fun update(effect:ImportedExpressionEffect,event:ReferenceDrumEvent):ImportedExpressionEffect {
        require(effect.slot==event.id && supported(event))
        return when(event.note){
            0->effect.copy(enabled=event.touch!=0)
            1->effect.copy(startDelay=event.touch)
            2->effect.copy(byPedal=event.touch!=0)
            3->effect.copy(stopByRelease=event.touch!=0)
            4->effect.copy(speed=event.touch)
            5->effect.copy(depth=event.touch)
            else->effect.copy(gain=event.touch)
        }
    }
}
