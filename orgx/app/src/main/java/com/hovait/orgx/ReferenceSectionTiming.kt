package com.hovait.orgx

/** Ports Tracks.CalcRhythmScale and the start anchor from GroupPlayStart. */
object ReferenceSectionTiming {
    fun scale(group:ImportedStyleGroup,method:Int=0,fallbackX:Int=4,fallbackY:Int=4):Int {
        val mode=if(group.parent==Int.MIN_VALUE)0 else method
        val x=if(group.meterX==0 || group.meterY==0)fallbackX else group.meterX
        val y=if(group.meterX==0 || group.meterY==0)fallbackY else group.meterY
        require(x>0 && y>0)
        val bar=x*2000/y
        require(bar>0)
        val length=group.end.toLong()-group.start
        var value=if(group.limit && length!=0L && length%bar==0L)bar else 1000
        return when(mode){
            0->{
                if((group.section shr 4)==6)-1 else {
                    while(value>=2000)value/=2
                    if(!group.limit || value<=10 || length%value==0L)value else 10
                }
            }
            1->{while(value>500)value/=2;value}
            2->value
            3->if(group.limit && length>0)length.coerceAtMost(Int.MAX_VALUE.toLong()).toInt() else value
            else->-1
        }
    }
    fun startOffset(time:Long,group:ImportedStyleGroup,scale:Int,method:Int=0):Long {
        var quantum=scale.coerceAtLeast(1).toLong()
        if(method in 1..2 && group.parent!=Int.MIN_VALUE &&
            ((group.section and 240) in listOf(32,48,64,96) || method==1)){
            if(group.limit && group.end>group.start)quantum=group.end.toLong()-group.start
        }
        return time%quantum
    }
    fun inWindow(time:Long,scale:Int,tempo:Int)=time%scale.coerceAtLeast(1)<=tempo*10/120
}
