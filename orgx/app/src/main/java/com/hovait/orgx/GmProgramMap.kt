package com.hovait.orgx

object GmProgramMap {
    // Compact ORGX palette mapped from GM families.
    fun instrument(program:Int):Int=when(program.coerceIn(0,127)){
        in 0..7->0      // acoustic pianos
        in 8..15->3     // chromatic/electric pianos
        in 16..23->1    // organs
        in 24..31->6    // guitars
        in 32..39->4    // basses
        in 40..55->5    // strings/ensembles
        in 56..63->7    // brass
        in 64..79->8    // reeds/pipes
        in 80..95->2    // leads
        else->9         // pads, effects and percussion families
    }
}
