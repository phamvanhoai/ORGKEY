package com.hovait.orgx

/** Safe first stage of the original mode-script path: retain event names and
 * variable references without executing arbitrary SET text. */
data class ImportedModeScript(val source:String,val events:List<String>,val variables:Set<String>) {
    fun handles(event:String)=event.lowercase() in events
    /** Bodies in declaration order; comments and string literals are masked. */
    fun eventBodies(event:String):List<String> {
        val code=mask(source)
        val declarations=if(event.equals("main",true))Regex("(?i)\\bmain\\s*\\(\\s*\\)\\s*\\{").findAll(code)
            else eventPattern.findAll(code).filter{it.groupValues[1].equals(event,true)}
        return declarations.map{declaration->
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
        private val eventPattern=Regex("(?i)\\bon\\s*\\(\\s*(aftertouch|release|touch|pedalon|pedaloff|pitchbend|modulate|__timer)\\s*\\)\\s*\\{")
        private val variablePattern=Regex("(?i)\\b(sound|key|velocity|balance|instrument|system|volume|pan|tune|pitchmove|pedal|keymove|octavemove|connectkey|connectspeed)\\b")
        private fun mask(source:String)=Regex("(?s)/\\*.*?\\*/|//[^\\r\\n]*|\"(?:\\\\.|[^\"\\\\])*\"").replace(source){match->match.value.map{if(it=='\n' || it=='\r')it else ' '}.joinToString("")}
        fun parse(source:String):ImportedModeScript {
            require(source.length<=262144){"Mode script exceeds supported size"}
            val code=mask(source)
            var parentheses=0;var blocks=0
            code.forEach{character->
                when(character){
                    '('->{parentheses++;require(parentheses<=64){"Script expression nesting exceeds limit"}}
                    ')'->{parentheses--;require(parentheses>=0){"Unbalanced script parentheses"}}
                    '{'->{blocks++;require(blocks<=64){"Script block nesting exceeds limit"}}
                    '}'->{blocks--;require(blocks>=0){"Unbalanced script blocks"}}
                }
            }
            require(parentheses==0 && blocks==0){"Unclosed script structure"}
            return ImportedModeScript(source,eventPattern.findAll(code).map{it.groupValues[1].lowercase()}.distinct().toList(),variablePattern.findAll(code).map{it.value.lowercase()}.toSet())
        }
    }
}
