package com.hovait.orgx

/** Safe first stage of the original mode-script path: retain event names and
 * variable references without executing arbitrary SET text. */
data class ImportedModeScript(val source:String,val events:List<String>,val variables:Set<String>) {
    fun handles(event:String)=event.lowercase() in events
    /** Bodies in declaration order; comments and string literals are masked. */
    fun eventBodies(event:String):List<String> {
        val code=mask(source)
        return eventPattern.findAll(code).filter{it.groupValues[1].equals(event,true)}.map{declaration->
            val start=declaration.range.last+1
            var depth=1;var end=start
            while(end<code.length && depth>0){
                when(code[end]){'{'->depth++;'}'->depth--}
                end++
            }
            require(depth==0){"Unclosed script event: $event"}
            code.substring(start,end-1)
        }.toList()
    }
    companion object {
        private val eventPattern=Regex("(?i)\\bon\\s*\\(\\s*(aftertouch|release|touch|pedalon|pedaloff|pitchbend|modulate)\\s*\\)\\s*\\{")
        private val variablePattern=Regex("(?i)\\b(sound|key|velocity|balance|instrument|system|volume|pan|tune|pitchmove|pedal|keymove|octavemove|connectkey|connectspeed)\\b")
        private fun mask(source:String)=Regex("(?s)/\\*.*?\\*/|//[^\\r\\n]*|\"(?:\\\\.|[^\"\\\\])*\"").replace(source){match->match.value.map{if(it=='\n' || it=='\r')it else ' '}.joinToString("")}
        fun parse(source:String):ImportedModeScript {
            val code=mask(source)
            return ImportedModeScript(source,eventPattern.findAll(code).map{it.groupValues[1].lowercase()}.distinct().toList(),variablePattern.findAll(code).map{it.value.lowercase()}.toSet())
        }
    }
}
