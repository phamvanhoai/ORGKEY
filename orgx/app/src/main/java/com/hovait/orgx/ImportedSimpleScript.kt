package com.hovait.orgx

/** Straight-line subset used by the bundled additive octave scripts.
 * Produces a complete plan before the caller changes any audio state. */
object ImportedSimpleScript {
    data class Play(val parameters:Map<String,Int>)
    data class EffectChange(val slot:Int,val property:String,val value:Int)
    data class Timer(val interval:Int,val body:String)
    data class Result(val plays:List<Play>,val handled:Boolean,val effects:List<EffectChange> = emptyList(),val timers:List<Timer> = emptyList(),val timerStopped:Boolean=false)
    fun evaluate(script:ImportedModeScript,event:String,variables:Map<String,Int> = emptyMap(),state:MutableMap<String,Int>?=null):Result {
        val plays=ArrayList<Play>()
        val effects=ArrayList<EffectChange>()
        val timers=ArrayList<Timer>()
        val pending=state?.toMutableMap() ?: mutableMapOf()
        fun context():Map<String,Int> {
            val values=(pending+variables).toMutableMap()
            repeat(8){slot->
                val mode=values["effect[$slot].mode"]?.toByte()?.toInt()
                if(mode==1 || mode==2)values["effect[$slot].speed"]=0
            }
            return values
        }
        fun result(handled:Boolean,timerStopped:Boolean=false):Result {
            state?.let{it.clear();it.putAll(pending)}
            return Result(plays.toList(),handled,effects.toList(),timers.toList(),timerStopped)
        }
        var handled=false
        for(body in script.eventBodies(event)) {
            for(rawStatement in statements(body,::context)) {
                var statement=rawStatement
                if(statement.equals("break",true)){
                    require(event=="__timer"){"break outside timer"}
                    return result(false,true)
                }
                val timer=Regex("(?s)^__timer\\((.*?)\\)\\s*\\{(.*)}$").matchEntire(statement)
                if(timer!=null){
                    timers.add(Timer(expression(timer.groupValues[1],context()).coerceIn(0,5000),timer.groupValues[2]))
                    continue
                }
                if(Regex("(?i)^if\\s*\\(").containsMatchIn(statement)){
                    val open=statement.indexOf('(');var depth=1;var end=open+1
                    while(end<statement.length && depth>0){when(statement[end]){'('->depth++;')'->depth--};end++}
                    require(depth==0){"Unclosed if condition"}
                    val condition=statement.substring(open+1,end-1)
                    statement=statement.substring(end).trim()
                    require(statement.isNotEmpty() && !statement.startsWith("{")){"Block conditions are not supported yet"}
                    if(expression(condition,context())==0)continue
                }
                val effect=Regex("(?i)effect\\[(\\d+)]\\.(enable|startdelay|bypedal|stopbyrelease|mode|speed|depth|gain)\\s*=\\s*(.+)").matchEntire(statement)
                if(effect!=null){
                    val slot=effect.groupValues[1].toInt();require(slot in 0..7)
                    val property=effect.groupValues[2].lowercase()
                    val evaluated=expression(effect.groupValues[3],context())
                    val value=when(property){
                        "mode"->evaluated.toByte().toInt()
                        "enable","bypedal","stopbyrelease"->if(evaluated!=0)1 else 0
                        else->evaluated
                    }
                    effects.add(EffectChange(slot,property,value))
                    pending["effect[$slot].$property"]=value
                    continue
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
        if(Regex("effect\\[[0-7]]\\.(enable|startdelay|bypedal|stopbyrelease|mode|speed|depth|gain)").matches(value))
            return variables[value] ?: throw IllegalArgumentException("Unknown effect property: $value")
        val function=Regex("(max|min|abs)\\s*\\((.*)\\)").matchEntire(value)
        if(function!=null){
            val args=splitArguments(function.groupValues[2]).map{expression(it,variables)}
            return when(function.groupValues[1]){
                "abs"->{require(args.size==1 && args.single()!=Int.MIN_VALUE);kotlin.math.abs(args.single())}
                "max"->{require(args.size==2);maxOf(args[0],args[1])}
                else->{require(args.size==2);minOf(args[0],args[1])}
            }
        }
        fun arithmetic(tokens:String):Int {
            var depth=0
            for(index in value.indices.reversed()){
                when(value[index]){')'->depth++;'('->depth--}
                if(depth==0 && value[index] in tokens && index>0){
                    val previous=value.substring(0,index).trimEnd().lastOrNull()
                    if(previous!=null && previous !in "+-*/(")return index
                }
            }
            return -1
        }
        val index=arithmetic("+-").let{if(it>=0)it else arithmetic("*/")}
        if(index>=0){
            val left=expression(value.substring(0,index),variables)
            val right=expression(value.substring(index+1),variables)
            return when(value[index]){
                '+'->Math.addExact(left,right)
                '-'->Math.subtractExact(left,right)
                '*'->Math.multiplyExact(left,right)
                else->{require(right!=0 && !(left==Int.MIN_VALUE && right==-1)){"Invalid script division"};left/right}
            }
        }
        if(value.startsWith("-"))return Math.negateExact(expression(value.substring(1),variables))
        throw IllegalArgumentException("Unsupported play expression: $source")
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
    private sealed interface Statement {
        data class Command(val text:String):Statement
        data class Branch(val condition:String,val yes:Statement,val no:Statement?):Statement
        data class Block(val items:List<Statement>):Statement
        data class Timed(val interval:String,val body:String):Statement
    }
    private class StatementReader(private val source:String) {
        private var position=0
        private fun space(){while(position<source.length && source[position].isWhitespace())position++}
        private fun word(value:String):Boolean {space();return source.regionMatches(position,value,0,value.length,true) &&
            (position+value.length==source.length || !source[position+value.length].isLetterOrDigit())}
        fun all():List<Statement>{
            val result=ArrayList<Statement>();space()
            while(position<source.length){result.add(read());space()}
            return result
        }
        private fun read():Statement {
            space();require(position<source.length){"Missing statement"}
            if(word("timer")){
                position+=5;space();require(position<source.length && source[position]=='(')
                val start=++position;var depth=1
                while(position<source.length && depth>0){when(source[position]){'('->depth++;')'->depth--};position++}
                require(depth==0){"Unclosed timer interval"}
                val interval=source.substring(start,position-1)
                space();require(position<source.length && source[position]=='{'){"Timer requires a block"}
                val bodyStart=++position;depth=1
                while(position<source.length && depth>0){when(source[position]){'{'->depth++;'}'->depth--};position++}
                require(depth==0){"Unclosed timer body"}
                val body=source.substring(bodyStart,position-1)
                StatementReader(body).all()
                return Statement.Timed(interval,body)
            }
            if(source[position]=='{'){
                position++;val items=ArrayList<Statement>();space()
                while(position<source.length && source[position]!='}'){items.add(read());space()}
                require(position<source.length){"Unclosed block"};position++
                return Statement.Block(items)
            }
            if(word("if")){
                position+=2;space();require(position<source.length && source[position]=='(')
                val start=++position;var depth=1
                while(position<source.length && depth>0){when(source[position]){'('->depth++;')'->depth--};position++}
                require(depth==0){"Unclosed condition"}
                val condition=source.substring(start,position-1)
                val yes=read();val no=if(word("else")){position+=4;read()}else null
                return Statement.Branch(condition,yes,no)
            }
            val start=position
            while(position<source.length && source[position]!=';'){
                require(source[position]!='{' && source[position]!='}'){"Unsupported script block"};position++
            }
            val text=source.substring(start,position).trim()
            if(position<source.length)position++
            require(text.isNotEmpty()){ "Empty statement" }
            return Statement.Command(text)
        }
    }
    private fun statements(body:String,context:()->Map<String,Int>):Sequence<String> {
        val parsed=StatementReader(body).all()
        fun execute(statement:Statement):Sequence<String> = sequence {
            when(statement){
                is Statement.Command->yield(statement.text)
                is Statement.Timed->yield("__timer(${statement.interval}) {${statement.body}}")
                is Statement.Block->statement.items.forEach{yieldAll(execute(it))}
                is Statement.Branch->{val selected=if(expression(statement.condition,context())!=0)statement.yes else statement.no
                    if(selected!=null)yieldAll(execute(selected))}
            }
        }
        return sequence{parsed.forEach{yieldAll(execute(it))}}
    }
}
