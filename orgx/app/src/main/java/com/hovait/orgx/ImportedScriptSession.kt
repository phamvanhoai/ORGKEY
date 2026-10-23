package com.hovait.orgx

/** One initialized variable store per mode execution context. Owned by caller. */
class ImportedScriptSession(source:String) {
    private val script=ImportedModeScript.parse(source)
    private val state=mutableMapOf<String,Int>()
    init {
        val initialized=ImportedSimpleScript.evaluate(script,"main",state=state)
        require(initialized.plays.isEmpty()){"Audio commands in main are not supported"}
    }
    fun event(name:String,parameters:Map<String,Int> = emptyMap()):ImportedSimpleScript.Result =
        ImportedSimpleScript.evaluate(script,name,parameters,state)
}
