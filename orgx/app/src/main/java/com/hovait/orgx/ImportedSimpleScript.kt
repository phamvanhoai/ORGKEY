package com.hovait.orgx

/** Straight-line subset used by the bundled additive octave scripts.
 * Produces a complete plan before the caller changes any audio state. */
object ImportedSimpleScript {
    data class Play(val parameters:Map<String,Int>)
    data class Result(val plays:List<Play>,val handled:Boolean)
    fun evaluate(script:ImportedModeScript,event:String):Result {
        val plays=ArrayList<Play>()
        var handled=false
        for(body in script.eventBodies(event)) {
            for(statement in body.split(';').map{it.trim()}.filter{it.isNotEmpty()}) {
                val call=Regex("(?is)(play|return)\\s*\\((.*?)\\)").matchEntire(statement)
                    ?: throw IllegalArgumentException("Unsupported script statement: $statement")
                val arguments=call.groupValues[2].trim()
                if(call.groupValues[1].equals("return",true)) {
                    val value=when(arguments.lowercase()){"true"->true;"false","0"->false;"1"->true;else->throw IllegalArgumentException("Unsupported script return")}
                    handled=value
                    return Result(plays.toList(),handled)
                }
                val parameters=linkedMapOf<String,Int>()
                if(arguments.isNotEmpty())for(argument in arguments.split(',')) {
                    val pair=argument.split(':',limit=2)
                    require(pair.size==2){"Invalid play parameter"}
                    val name=pair[0].trim().lowercase()
                    require(name in setOf("octavemove","volume","sound","key","velocity","pan","tune","pitchmove","connectkey","connectspeed","autorelease")){"Unsupported play parameter: $name"}
                    require(name !in parameters){"Duplicate play parameter: $name"}
                    parameters[name]=pair[1].trim().toIntOrNull() ?: throw IllegalArgumentException("Unsupported play expression")
                }
                plays.add(Play(parameters.toMap()))
            }
        }
        return Result(plays.toList(),handled)
    }
}
