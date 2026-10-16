package com.hovait.orgx

/** Approximate GM mapping onto the eight sampled voices currently available. */
object GmProgramMap {
    fun instrument(program:Int):Int=when(program.coerceIn(0,127)){
        in 0..15->0
        in 16..23->1
        in 24..31->0
        in 32..39->4
        40->6
        in 41..51->7
        in 52..54->5
        in 55..71->2
        in 72..79->3
        else->2
    }
}