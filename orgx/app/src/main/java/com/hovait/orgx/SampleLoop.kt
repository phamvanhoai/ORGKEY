package com.hovait.orgx

object SampleLoop {
    /** Channels.Process uses LoopEnd as an exclusive endpoint. */
    fun position(position:Double,start:Int,endExclusive:Int):Double {
        require(start>=0 && endExclusive>start)
        if(position<endExclusive)return position
        return start+(position-start)%(endExclusive-start)
    }
}
