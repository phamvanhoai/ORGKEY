package com.hovait.orgx

/** Safe first stage of the original mode-script path: retain event names and
 * variable references without executing arbitrary SET text. */
data class ImportedModeScript(val source:String,val events:List<String>,val variables:Set<String>) {
    companion object {
        private val eventPattern=Regex("(?i)\\b(aftertouch|release|touch|pedalon|pedaloff|pitchbend|modulate)\\b")
        private val variablePattern=Regex("(?i)\\b(sound|key|velocity|balance|instrument|system|volume|pan|tune|pitchmove|pedal|keymove|octavemove|connectkey|connectspeed)\\b")
        fun parse(source:String):ImportedModeScript = ImportedModeScript(source,eventPattern.findAll(source).map{it.value.lowercase()}.toList(),variablePattern.findAll(source).map{it.value.lowercase()}.toSet())
    }
}
