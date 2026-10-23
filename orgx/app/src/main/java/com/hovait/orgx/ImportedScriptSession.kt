package com.hovait.orgx

/** One initialized variable store per mode execution context. Owned by caller. */
class ImportedScriptSession(source:String) {
    data class Slider(val name:String,val title:String,val reset:Int,val from:Int,val to:Int)
    val sliders:List<Slider>
    private val script:ImportedModeScript
    private val state=mutableMapOf<String,Int>()
    init {
        val code=Regex("(?s)/\\*.*?\\*/|//[^\\r\\n]*|\"(?:\\\\.|[^\"\\\\])*\"").replace(source){match->
            if(match.value.startsWith("\""))match.value else " "
        }
        val declarations=Regex("(?i)setting\\s*\\(\\s*slider\\s*,\\s*name:\"([a-z_][a-z_0-9]*)\"\\s*,\\s*title:\"([^\"]*)\"\\s*,\\s*reset:(-?\\d+)\\s*,\\s*from:(-?\\d+)\\s*,\\s*to:(-?\\d+)\\s*\\)\\s*;")
        sliders=declarations.findAll(code).map{match->
            Slider(match.groupValues[1].lowercase(),match.groupValues[2],match.groupValues[3].toInt(),match.groupValues[4].toInt(),match.groupValues[5].toInt()).also{
                require(it.from<=it.to && it.reset in it.from..it.to){"Invalid script slider"}
            }
        }.toList()
        require(sliders.map{it.name}.distinct().size==sliders.size){"Duplicate script slider"}
        sliders.forEach{state[it.name]=it.reset}
        script=ImportedModeScript.parse(declarations.replace(code,""))
        val initialized=ImportedSimpleScript.evaluate(script,"main",state=state)
        require(initialized.plays.isEmpty()){"Audio commands in main are not supported"}
    }
    fun event(name:String,parameters:Map<String,Int> = emptyMap(),validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        val pending=state.toMutableMap()
        val result=ImportedSimpleScript.evaluate(script,name,parameters,pending)
        validate(result)
        state.clear();state.putAll(pending)
        return result
    }
}
