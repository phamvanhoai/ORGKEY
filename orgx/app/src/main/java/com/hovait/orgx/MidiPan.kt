package com.hovait.orgx

object MidiPan {
    const val CENTER=64
    fun left(value:Int):Double=if(value<=CENTER)1.0 else (127-value.coerceIn(0,127))/63.0
    fun right(value:Int):Double=if(value>=CENTER)1.0 else value.coerceIn(0,127)/64.0
}
