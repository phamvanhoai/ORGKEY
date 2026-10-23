package com.hovait.orgx

/** Named setting arguments used by InstMode.ScriptEvent; commas inside titles are literal. */
internal object ImportedScriptSettings {
    data class Parsed(val code:String,val controls:List<ImportedScriptSession.Slider>,val titles:List<String>,val entries:List<ImportedScriptSession.Setting>)
    private const val quoted="\"(?:\\\\.|[^\"\\\\])*\""
    private val calls=Regex("$quoted|\\bsetting\\s*\\(\\s*(text|slider|volume|toggle)\\s*,((?:$quoted|[^\")])*)\\)\\s*;",RegexOption.IGNORE_CASE)
    private val argument=Regex("\\s*([a-z_][a-z_0-9]*)\\s*:\\s*($quoted|-?\\d+|true|false)\\s*(,|$)",RegexOption.IGNORE_CASE)
    fun read(source:String):Parsed {
        val controls=mutableListOf<ImportedScriptSession.Slider>()
        val titles=mutableListOf<String>()
        val entries=mutableListOf<ImportedScriptSession.Setting>()
        val code=calls.replace(source){call->
            if(call.groups[1]==null) return@replace call.value
            val values=mutableMapOf<String,String>()
            val args=call.groupValues[2]
            var offset=0
            while(offset<args.length){
                val match=argument.matchAt(args,offset) ?: throw IllegalArgumentException("Invalid script setting argument")
                val key=match.groupValues[1].lowercase()
                require(values.put(key,match.groupValues[2])==null){"Duplicate script setting argument: $key"}
                offset=match.range.last+1
                require(match.groupValues[3]!="," || offset<args.length){"Missing script setting argument"}
            }
            require(values.keys.all{it in setOf("name","title","reset","from","to")}){"Unsupported script setting argument"}
            fun string(name:String):String {
                val raw=values[name] ?: throw IllegalArgumentException("Missing script setting $name")
                require(raw.startsWith('"')){"Expected script setting text"}
                return Regex("\\\\(.)").replace(raw.substring(1,raw.length-1)){it.groupValues[1]}
            }
            fun integer(name:String)=values[name]?.toIntOrNull() ?: throw IllegalArgumentException("Invalid script setting $name")
            val title=string("title")
            if(call.groupValues[1].equals("text",true)) {
                titles+=title
                entries+=ImportedScriptSession.Setting(title)
            }
            else {
                val name=string("name").lowercase()
                require(Regex("[a-z_][a-z_0-9]*").matches(name)){"Invalid script setting name"}
                val toggle=call.groupValues[1].equals("toggle",true)
                val reset=if(toggle) when(values["reset"]?.lowercase()){
                    "true","1"->1; "false","0"->0
                    else->throw IllegalArgumentException("Invalid script toggle reset")
                } else integer("reset")
                val from=if(toggle)0 else if("from" in values)integer("from") else 0
                val to=if(toggle)1 else if("to" in values)integer("to") else 100
                require(from<=to && reset in from..to){"Invalid script slider"}
                val control=ImportedScriptSession.Slider(name,title,reset,from,to,toggle)
                controls+=control
                entries+=ImportedScriptSession.Setting(title,control)
            }
            " "
        }
        return Parsed(code,controls,titles,entries)
    }
}
