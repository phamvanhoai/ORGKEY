package com.hovait.orgx

/** Straight-line subset used by the bundled additive octave scripts.
 * Produces a complete plan before the caller changes any audio state. */
object ImportedSimpleScript {
    data class Play(val parameters:Map<String,Int>)
    data class Result(val plays:List<Play>,val handled:Boolean)
    fun evaluate(script:ImportedModeScript,event:String,variables:Map<String,Int> = emptyMap(),state:MutableMap<String,Int>?=null):Result {
        val plays=ArrayList<Play>()
        val pending=state?.toMutableMap() ?: mutableMapOf()
        fun context()=pending+variables
        fun result(handled:Boolean):Result {
            state?.let{it.clear();it.putAll(pending)}
            return Result(plays.toList(),handled)
        }
        var handled=false
        for(body in script.eventBodies(event)) {
            for(rawStatement in statements(body,::context)) {
                var statement=rawStatement
                if(Regex("(?i)^if\\s*\\(").containsMatchIn(statement)){
                    val open=statement.indexOf('(');var depth=1;var end=open+1
                    while(end<statement.length && depth>0){when(statement[end]){'('->depth++;')'->depth--};end++}
                    require(depth==0){"Unclosed if condition"}
                    val condition=statement.substring(open+1,end-1)
                    statement=statement.substring(end).trim()
                    require(statement.isNotEmpty() && !statement.startsWith("{")){"Block conditions are not supported yet"}
                    if(expression(condition,context())==0)continue
                }
                val assignment=Regex("([a-zA-Z_][a-zA-Z_0-9]*)\\s*=\\s*([^=].*)").matchEntire(statement)
                if(assignment!=null){
                    val name=assignment.groupValues[1].lowercase()
                    require(name !in variables){"Cannot overwrite event parameter: $name"}
                    pending[name]=expression(assignment.groupValues[2],context())
                    continue
                }
                val call=Regex("(?is)(play|return)\\s*\\((.*?)\\)").matchEntire(statement)
                    ?: throw IllegalArgumentException("Unsupported script statement: $statement")
                val arguments=call.groupValues[2].trim()
                if(call.groupValues[1].equals("return",true)) {
                    val value=expression(arguments,context())!=0
                    handled=value
                    return result(handled)
                }
                val parameters=linkedMapOf<String,Int>()
                if(arguments.isNotEmpty())for(argument in splitArguments(arguments)) {
                    val pair=argument.split(':',limit=2)
                    require(pair.size==2){"Invalid play parameter"}
                    val name=pair[0].trim().lowercase()
                    require(name in setOf("octavemove","keymove","volume","sound","key","velocity","pan","tune","pitchmove","connectkey","connectspeed","autorelease","attack","decay","sustain","release")){"Unsupported play parameter: $name"}
                    require(name !in parameters){"Duplicate play parameter: $name"}
                    parameters[name]=expression(pair[1],context())
                }
                plays.add(Play(parameters.toMap()))
            }
        }
        return result(handled)
    }
    private fun expression(source:String,variables:Map<String,Int>):Int {
        val value=source.trim().lowercase()
        fun operator(token:String):Int {
            var depth=0
            for(index in value.indices){
                when(value[index]){'('->depth++;')'->depth--}
                if(depth==0 && value.startsWith(token,index))return index
            }
            return -1
        }
        val or=operator("||")
        if(or>=0)return if(expression(value.substring(0,or),variables)!=0 || expression(value.substring(or+2),variables)!=0)1 else 0
        val and=operator("&&")
        if(and>=0)return if(expression(value.substring(0,and),variables)!=0 && expression(value.substring(and+2),variables)!=0)1 else 0
        if(value.startsWith("(")){
            var depth=0;var closing=-1
            for(index in value.indices){
                when(value[index]){'('->depth++;')'->depth--}
                if(depth==0){closing=index;break}
            }
            if(closing==value.lastIndex)return expression(value.substring(1,value.length-1),variables)
        }
        val comparison=listOf("==","!=","<=",">=","<",">").map{it to operator(it)}.firstOrNull{it.second>=0}
        if(comparison!=null){
            val (token,index)=comparison
            val left=expression(value.substring(0,index),variables)
            val right=expression(value.substring(index+token.length),variables)
            val result=when(token){
                "=="->left==right;"!="->left!=right;"<="->left<=right
                ">="->left>=right;"<"->left<right;else->left>right
            }
            return if(result)1 else 0
        }
        if(value.startsWith("!"))return if(expression(value.substring(1),variables)==0)1 else 0
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
        val match=Regex("([a-z_][a-z_0-9]*)\\s*([+-])\\s*([a-z_][a-z_0-9]*|\\d+)").matchEntire(value)
            ?: throw IllegalArgumentException("Unsupported play expression: $source")
        val base=variables[match.groupValues[1]] ?: throw IllegalArgumentException("Unknown script variable")
        val offset=match.groupValues[3].toIntOrNull() ?: variables[match.groupValues[3]]
            ?: throw IllegalArgumentException("Unknown script variable: ${match.groupValues[3]}")
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
    private fun statements(body:String,context:()->Map<String,Int>):Sequence<String> = sequence {
        var position=0
        fun whitespace(){while(position<body.length && (body[position].isWhitespace() || body[position]==';'))position++}
        while(true){
            whitespace();if(position>=body.length)break
            val start=position
            val conditional=Regex("(?i)if\\s*\\(").find(body,position)?.takeIf{it.range.first==position}
            if(conditional!=null){
                val open=conditional.range.last;var end=open+1;var depth=1
                while(end<body.length && depth>0){when(body[end]){'('->depth++;')'->depth--};end++}
                require(depth==0){"Unclosed if condition"}
                position=end;whitespace()
                if(position<body.length && body[position]=='{'){
                    val blockStart=++position;depth=1
                    while(position<body.length && depth>0){when(body[position]){'{'->depth++;'}'->depth--};position++}
                    require(depth==0){"Unclosed if block"}
                    val block=body.substring(blockStart,position-1)
                    val condition=expression(body.substring(open+1,end-1),context())!=0
                    whitespace()
                    var alternative:String?=null
                    val elseToken=Regex("(?i)else\\b").find(body,position)?.takeIf{it.range.first==position}
                    if(elseToken!=null){
                        position=elseToken.range.last+1;whitespace()
                        require(position<body.length && body[position]=='{'){"Else requires a block"}
                        val alternativeStart=++position;depth=1
                        while(position<body.length && depth>0){when(body[position]){'{'->depth++;'}'->depth--};position++}
                        require(depth==0){"Unclosed else block"}
                        alternative=body.substring(alternativeStart,position-1)
                    }
                    if(condition)yieldAll(statements(block,context))
                    else if(alternative!=null)yieldAll(statements(alternative,context))
                    continue
                }
                position=start
            }
            val end=body.indexOf(';',position).let{if(it<0)body.length else it}
            val statement=body.substring(position,end).trim()
            require('{' !in statement && '}' !in statement){"Unsupported script block"}
            if(statement.isNotEmpty())yield(statement)
            position=end+1
        }
    }
}
