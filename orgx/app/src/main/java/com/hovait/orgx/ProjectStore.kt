package com.hovait.orgx

import android.content.Context
import android.os.Environment
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ProjectState(
    val octave:Int,val bpm:Int,val instrument:Int,val chord:Int,val style:Int,val effect:Int,
    val autoChord:Boolean,val progression:Int,val master:Float,val keys:Float,val chords:Float,
    val drums:Float,val effectMix:Float
)

class ProjectStore(context:Context){
    private val directory=File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?:context.filesDir,"Projects")
    fun list():List<File> = directory.listFiles{f->f.isFile&&f.extension.equals("orgx",true)}?.sortedByDescending{it.lastModified()}?:emptyList()
    fun save(state:ProjectState):File{
        directory.mkdirs()
        val stamp=SimpleDateFormat("yyyyMMdd-HHmmss",Locale.US).format(Date())
        val file=File(directory,"ORGX-$stamp.orgx")
        val json=JSONObject().apply{
            put("format","ORGX Project");put("version",1);put("octave",state.octave);put("bpm",state.bpm)
            put("instrument",state.instrument);put("chord",state.chord);put("style",state.style);put("effect",state.effect)
            put("autoChord",state.autoChord);put("progression",state.progression);put("master",state.master.toDouble())
            put("keys",state.keys.toDouble());put("chords",state.chords.toDouble());put("drums",state.drums.toDouble());put("effectMix",state.effectMix.toDouble())
        }
        file.writeText(json.toString(2));return file
    }
    fun load(file:File):ProjectState{
        val json=JSONObject(file.readText())
        require(json.optString("format")=="ORGX Project"&&json.optInt("version")==1){"Invalid ORGX project"}
        return ProjectState(json.getInt("octave").coerceIn(1,7),json.getInt("bpm").coerceIn(40,240),json.getInt("instrument").coerceIn(0,2),json.getInt("chord").coerceIn(0,11),json.getInt("style").coerceIn(0,2),json.getInt("effect").coerceIn(0,2),json.optBoolean("autoChord"),json.optInt("progression").coerceIn(0,2),json.optDouble("master",.65).toFloat().coerceIn(0f,1f),json.optDouble("keys",.78).toFloat().coerceIn(0f,1f),json.optDouble("chords",.22).toFloat().coerceIn(0f,1f),json.optDouble("drums",.55).toFloat().coerceIn(0f,1f),json.optDouble("effectMix",.28).toFloat().coerceIn(0f,1f))
    }
}
