package com.hovait.orgx

/** One initialized variable store per mode execution context. Owned by caller. */
class ImportedScriptSession(source:String,storedEffects:List<ImportedExpressionEffect> = emptyList(),private val clockMillis:()->Long={System.nanoTime()/1_000_000}) {
    private val timeOrigin=clockMillis()
    data class Slider(val name:String,val title:String,val reset:Int,val from:Int,val to:Int,val toggle:Boolean=false)
    val sliders:List<Slider>
    val settingTitles:List<String>
    var initialEffects:List<ImportedSimpleScript.EffectChange> = emptyList();private set
    private val script:ImportedModeScript
    private val state=mutableMapOf<String,Int>()
    private var pedalDown=false
    private var modulationLevel:Int?=null
    private var modulationResult=ImportedSimpleScript.Result(emptyList(),false)
    fun modulation(level:Int,validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        if(modulationLevel==level)return currentEffects(modulationResult)
        val result=event("modulate",mapOf("level" to level),validate)
        modulationLevel=level;modulationResult=result
        return result
    }
    private var pedalResult=ImportedSimpleScript.Result(emptyList(),false)
    fun pedal(down:Boolean,validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        if(pedalDown==down)return currentEffects(pedalResult)
        val result=event(if(down)"pedalon" else "pedaloff",validate=validate)
        pedalDown=down;pedalResult=result
        return result
    }
    fun synchronizeEffects(effects:List<ImportedExpressionEffect>){
        (0..7).forEach{slot->
            val effect=effects.firstOrNull{it.slot==slot} ?: ImportedExpressionEffect.default(slot)
            val values=mapOf("enable" to if(effect.enabled)1 else 0,"startdelay" to effect.startDelay,
                "bypedal" to if(effect.byPedal)1 else 0,"stopbyrelease" to if(effect.stopByRelease)1 else 0,
                "mode" to effect.mode,"speed" to effect.speed,"depth" to effect.depth,"gain" to effect.gain)
            values.forEach{(name,value)->state["effect[${effect.slot}].$name"]=value}
        }
        initialEffects.forEach{change->state["effect[${change.slot}].${change.property}"]=change.value}
    }
    private fun currentEffects(result:ImportedSimpleScript.Result)=result.copy(effects=result.effects.map{change->
        change.copy(value=state["effect[${change.slot}].${change.property}"] ?: change.value)
    })
    fun setSlider(name:String,value:Int){
        val slider=sliders.singleOrNull{it.name==name.lowercase()} ?: throw IllegalArgumentException("Unknown script slider: $name")
        require(value in slider.from..slider.to){"Script slider value outside declared range"}
        state[slider.name]=value
    }
    init {
        val code=Regex("(?s)/\\*.*?\\*/|//[^\\r\\n]*|\"(?:\\\\.|[^\"\\\\])*\"").replace(source){match->
            if(match.value.startsWith("\""))match.value else " "
        }
        val declarations=Regex("(?i)setting\\s*\\(\\s*slider\\s*,\\s*name:\"([a-z_][a-z_0-9]*)\"\\s*,\\s*title:\"([^\"]*)\"\\s*,\\s*reset:(-?\\d+)\\s*,\\s*from:(-?\\d+)\\s*,\\s*to:(-?\\d+)\\s*\\)\\s*;")
        val toggles=Regex("(?i)setting\\s*\\(\\s*toggle\\s*,\\s*name:\"([a-z_][a-z_0-9]*)\"\\s*,\\s*title:\"([^\"]*)\"\\s*,\\s*reset:\\s*(true|false|0|1)\\s*\\)\\s*;")
        sliders=toggles.findAll(code).map{Slider(it.groupValues[1].lowercase(),it.groupValues[2],if(it.groupValues[3].lowercase() in listOf("true","1"))1 else 0,0,1,true)}.toList()+declarations.findAll(code).map{match->
            Slider(match.groupValues[1].lowercase(),match.groupValues[2],match.groupValues[3].toInt(),match.groupValues[4].toInt(),match.groupValues[5].toInt()).also{
                require(it.from<=it.to && it.reset in it.from..it.to){"Invalid script slider"}
            }
        }.toList()
        require(sliders.map{it.name}.distinct().size==sliders.size){"Duplicate script slider"}
        sliders.forEach{state[it.name]=it.reset}
        val textSettings=Regex("(?i)setting\\s*\\(\\s*text\\s*,\\s*title:\"([^\"]*)\"\\s*\\)\\s*;")
        settingTitles=textSettings.findAll(code).map{it.groupValues[1]}.toList()
        script=ImportedModeScript.parse(toggles.replace(textSettings.replace(declarations.replace(code,""),""),""))
        synchronizeEffects(storedEffects)
        val initialized=ImportedSimpleScript.evaluate(script,"main",state=state)
        require(initialized.plays.isEmpty()){"Audio commands in main are not supported"}
        require(initialized.effects.all{it.slot in 0..3}){"Unsupported main effect slot"}
        initialEffects=initialized.effects
    }
    fun event(name:String,parameters:Map<String,Int> = emptyMap(),validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        val pending=state.toMutableMap()
        val elapsed=(clockMillis()-timeOrigin).coerceIn(0,Int.MAX_VALUE.toLong()).toInt()
        val result=ImportedSimpleScript.evaluate(script,name,parameters+("time" to elapsed),pending)
        validate(result)
        state.clear();state.putAll(pending)
        return result
    }
}
