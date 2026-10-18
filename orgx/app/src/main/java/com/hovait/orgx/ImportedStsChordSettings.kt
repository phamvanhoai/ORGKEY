package com.hovait.orgx

/** Validate a whole chord snapshot before changing any live engine state. */
object ImportedStsChordSettings {
    fun validate(mode:Int,singles:List<Int>){
        require(mode in 0..2){"Unsupported STS chord input mode: $mode"}
        require(mode==0 || (singles.size==12 && singles.all{it in 0..4095})){
            "STS chord table must contain twelve pitch-class masks"
        }
    }
    fun singles(mode:Int,stored:List<Int>,current:List<Int>):List<Int>{
        validate(mode,stored)
        return (if(mode==0)current else stored).toList()
    }
}
