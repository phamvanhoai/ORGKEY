package com.hovait.orgx

/** Straight-line subset used by the bundled additive octave scripts.
 * Produces a complete plan before the caller changes any audio state. */
object ImportedSimpleScript {
    data class Play(val parameters:Map<String,Int>)
    data class Result(val plays:List<Play>,val handled:Boolean)
    fun evaluate(script:ImportedModeScript,event:String,variables:Map<String,Int> = emptyMap()):Result {
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
                if(arguments.isNotEmpty())for(argument in splitArguments(arguments)) {
                    val pair=argument.split(':',limit=2)
                    require(pair.size==2){"Invalid play parameter"}
                    val name=pair[0].trim().lowercase()
                    require(name in setOf("octavemove","keymove","volume","sound","key","velocity","pan","tune","pitchmove","connectkey","connectspeed","autorelease")){"Unsupported play parameter: $name"}
                    require(name !in parameters){"Duplicate play parameter: $name"}
                    parameters[name]=expression(pair[1],variables)
                }
                plays.add(Play(parameters.toMap()))
            }
        }
        return Result(plays.toList(),handled)
    }
    private fun expression(source:String,variables:Map<String,Int>):Int {
        val value=source.trim().lowercase()
        if(value=="true")return 1
        if(value=="false")return 0
        value.toIntOrNull()?.let{return it}
        variables[value]?.let{return it}
        val function=Regex("(max|min|abs)\\s*\\((.*)\\)").matchEntire(value)
        if(function!=null){
            val args=splitArguments(function.groupValues[2]).map{expression(it,variables)}
            return when(function.groupValues[1]){
                "abs"->{require(args.size==1 && args.single()!=Int.MIN_VALUE);kotlin.math.abs(args.single())}
                "max"->{require(args.size==2);maxOf(args[0],args[1])}
                else->{require(args.size==2);minOf(args[0],args[1])}
            }
        }
        val match=Regex("([a-z_][a-z_0-9]*)\\s*([+-])\\s*(\\d+)").matchEntire(value)
            ?: throw IllegalArgumentException("Unsupported play expression: $source")
        val base=variables[match.groupValues[1]] ?: throw IllegalArgumentException("Unknown script variable")
        val offset=match.groupValues[3].toInt()
        return if(match.groupValues[2]=="+")Math.addExact(base,offset) else Math.subtractExact(base,offset)
    }
    private fun splitArguments(source:String):List<String>{
        val result=ArrayList<String>();var depth=0;var start=0
        source.forEachIndexed{index,c->when(c){
            '('->depth++
            ')'->{depth--;require(depth>=0){"Unbalanced expression"}}
            ','->if(depth==0){result.add(source.substring(start,index));start=index+1}
        }}
        require(depth==0){"Unbalanced expression"}
        result.add(source.substring(start));return result
    }
}
