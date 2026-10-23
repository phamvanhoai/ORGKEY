package com.hovait.orgx

/** Safe first stage of the original mode-script path: retain event names and
 * variable references without executing arbitrary SET text. */
data class ImportedModeScript(val source:String,val events:List<String>,val variables:Set<String>) {
    fun handles(event:String)=event.lowercase() in events
    companion object {
        private val eventPattern=Regex("(?i)\\bon\\s*\\(\\s*(aftertouch|release|touch|pedalon|pedaloff|pitchbend|modulate)\\s*\\)\\s*\\{")
        private val variablePattern=Regex("(?i)\\b(sound|key|velocity|balance|instrument|system|volume|pan|tune|pitchmove|pedal|keymove|octavemove|connectkey|connectspeed)\\b")
        fun parse(source:String):ImportedModeScript {
            val code=Regex("(?s)/\\*.*?\\*/|//[^\\r\\n]*|\"(?:\\\\.|[^\"\\\\])*\"").replace(source," ")
            return ImportedModeScript(source,eventPattern.findAll(code).map{it.groupValues[1].lowercase()}.distinct().toList(),variablePattern.findAll(code).map{it.value.lowercase()}.toSet())
        }
    }
}
