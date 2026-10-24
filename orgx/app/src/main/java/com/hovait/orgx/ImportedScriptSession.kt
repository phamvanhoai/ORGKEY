package com.hovait.orgx

/** One initialized variable store per mode execution context. Owned by caller. */
class ImportedScriptSession(source:String,storedEffects:List<ImportedExpressionEffect> = emptyList(),storedSettings:Map<String,String> = emptyMap(),private val modeAvailable:(Int)->Boolean={it==0},private val onModeExit:(Int)->Unit={},private val playingCount:(Boolean)->Int={0},private val clockMillis:()->Long={System.nanoTime()/1_000_000}) {
    private val timeOrigin=clockMillis()
    data class Slider(val name:String,val title:String,val reset:Int,val from:Int,val to:Int,val toggle:Boolean=false)
    data class Setting(val title:String,val control:Slider?=null)
    val settings:List<Setting>
    val sliders:List<Slider>
    val settingTitles:List<String>
    val settingValues:Map<String,Int> get()=sliders.associate{it.name to (state[it.name] ?: it.reset)}
    var initialEffects:List<ImportedSimpleScript.EffectChange> = emptyList();private set
    private val script:ImportedModeScript
    private val state=mutableMapOf<String,Int>()
    private fun playingContext()=mapOf("__playing" to playingCount(false),"__playingreleased" to playingCount(true))
    private data class Scheduled(val token:Long,val timer:ImportedSimpleScript.Timer,val parameters:Map<String,Int>,var due:Long)
    private var scheduled:Scheduled?=null
    var timerToken=0L;private set
    fun hasTimer(token:Long)=scheduled?.token==token
    fun cancelTimer(token:Long){if(hasTimer(token))scheduled=null}
    /** Audio-frame time supplied by the owning Playback; at most one callback per poll. */
    fun advanceTimer(token:Long,elapsedMillis:Long,validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result? {
        val current=scheduled?.takeIf{it.token==token && elapsedMillis>=it.due} ?: return null
        val pending=state.toMutableMap()
        try{
            val callback=ImportedModeScript.parse("on(__timer){${current.timer.body}}")
            val elapsed=(clockMillis()-timeOrigin).coerceIn(0,Int.MAX_VALUE.toLong()).toInt()
            val result=ImportedSimpleScript.evaluate(callback,"__timer",current.parameters+playingContext()+("time" to elapsed),pending)
            result.exitMode?.let{require(modeAvailable(it)){"Script exit mode unavailable: $it"}}
            validate(result)
            state.clear();state.putAll(pending)
            if(result.timerStopped)scheduled=null
            else if(result.timers.isNotEmpty())schedule(result.timers.last(),current.parameters,elapsedMillis)
            else current.due=elapsedMillis+current.timer.interval.coerceAtLeast(1)
            result.exitMode?.let(onModeExit)
            return result
        }catch(error:Exception){scheduled=null;throw error}
    }
    private fun schedule(timer:ImportedSimpleScript.Timer,parameters:Map<String,Int>,now:Long=0){
        timerToken++
        scheduled=Scheduled(timerToken,timer,parameters.toMap(),now+timer.interval.coerceAtLeast(1))
    }
    private var pedalDown=false
    private var pitchLevel:Int?=null
    private var pitchResult=ImportedSimpleScript.Result(emptyList(),false)
    fun pitchBend(level:Int,forceEvent:Boolean=false,validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        require(level in -8192..8191)
        if(!forceEvent && pitchLevel==level)return currentEffects(pitchResult).also(validate)
        val result=event("pitchbend",mapOf("level" to level),validate)
        pitchLevel=level;pitchResult=result
        return result
    }
    private var modulationLevel:Int?=null
    private var modulationResult=ImportedSimpleScript.Result(emptyList(),false)
    fun modulation(level:Int,validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        if(modulationLevel==level)return currentEffects(modulationResult).also(validate)
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
        val settings=ImportedScriptSettings.read(code)
        this.settings=settings.entries
        sliders=settings.controls
        settingTitles=settings.titles
        require(sliders.map{it.name}.distinct().size==sliders.size){"Duplicate script slider"}
        sliders.forEach{state[it.name]=it.reset}
        // Instrument.ReadFromStream restores ScriptSetting before main registers its controls.
        // Keep metadata (#name.title/from/to/reset/type) separate from executable variables.
        storedSettings.forEach{(name,raw)->
            val key=name.lowercase()
            if(!key.startsWith("#") && sliders.any{it.name==key}){
                val value=when(raw.lowercase()){
                    "true"->1
                    "false"->0
                    else->raw.toDoubleOrNull()?.takeIf{it.isFinite() && it>=Int.MIN_VALUE && it<=Int.MAX_VALUE}?.toInt()
                }
                if(value!=null)state[key]=value
            }
        }
        script=ImportedModeScript.parse(settings.code)
        synchronizeEffects(storedEffects)
        val initialized=ImportedSimpleScript.evaluate(script,"main",state=state)
        require(initialized.plays.isEmpty()){"Audio commands in main are not supported"}
        require(initialized.timers.isEmpty()){"Timers in main are not supported"}
        require(initialized.exitMode==null){"Mode changes in main are not supported"}
        require(initialized.effects.all{it.slot in 0..3}){"Unsupported main effect slot"}
        initialEffects=initialized.effects
    }
    fun event(name:String,parameters:Map<String,Int> = emptyMap(),validate:(ImportedSimpleScript.Result)->Unit={}):ImportedSimpleScript.Result {
        val pending=state.toMutableMap()
        val elapsed=(clockMillis()-timeOrigin).coerceIn(0,Int.MAX_VALUE.toLong()).toInt()
        val result=ImportedSimpleScript.evaluate(script,name,parameters+playingContext()+("time" to elapsed),pending)
        result.exitMode?.let{require(modeAvailable(it)){"Script exit mode unavailable: $it"}}
        require(result.timers.isEmpty() || name.equals("touch",true)){"Timers in $name are not supported"}
        validate(result)
        state.clear();state.putAll(pending)
        result.timers.lastOrNull()?.let{schedule(it,parameters)}
        result.exitMode?.let(onModeExit)
        return result
    }
}
