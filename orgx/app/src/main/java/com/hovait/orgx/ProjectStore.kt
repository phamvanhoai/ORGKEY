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
    private val chordLibrary = runCatching {
        context.assets.open("Chord").bufferedReader().use { PlayableChordLibrary.fromTable(it.readText()) }
    }.getOrDefault(emptyList())
    private val directory=File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?:context.filesDir,"Projects")
    fun list():List<File> = directory.listFiles{f->f.isFile&&f.extension.equals("orgx",true)}?.sortedByDescending{it.lastModified()}?:emptyList()
    fun save(state:ProjectState):File{
        directory.mkdirs()
        val stamp=SimpleDateFormat("yyyyMMdd-HHmmss",Locale.US).format(Date())
        val file=File(directory,"ORGX-$stamp.orgx")
        val json=JSONObject().apply{
            put("format","ORGX Project");put("version",2);put("octave",state.octave);put("bpm",state.bpm)
            put("chordName",chordLibrary.getOrNull(state.chord)?.name)
            put("instrument",state.instrument);put("chord",state.chord);put("style",state.style);put("effect",state.effect)
            put("autoChord",state.autoChord);put("progression",state.progression);put("master",state.master.toDouble())
            put("keys",state.keys.toDouble());put("chords",state.chords.toDouble());put("drums",state.drums.toDouble());put("effectMix",state.effectMix.toDouble())
        }
        file.writeText(json.toString(2));return file
    }
    fun load(file:File):ProjectState{
        val json=JSONObject(file.readText())
        require(json.optString("format")=="ORGX Project"&&json.optInt("version") in 1..2){"Invalid ORGX project"}
        val savedName = if (json.optInt("version") == 1) {
            listOf("C M","D m","E m","F M","G M","A m","B dim","C 7th","D 7th","E 7th","F M7","G 7th")
                .getOrNull(json.optInt("chord"))
        } else json.optString("chordName")
        val restoredChord = chordLibrary.indexOfFirst { it.name == savedName }.takeIf { it >= 0 }
            ?: json.optInt("chord").coerceIn(0, (chordLibrary.size - 1).coerceAtLeast(0))
        return ProjectState(json.getInt("octave").coerceIn(1,7),json.getInt("bpm").coerceIn(40,240),json.getInt("instrument").coerceIn(0,2),restoredChord,json.getInt("style").coerceIn(0,2),json.getInt("effect").coerceIn(0,2),json.optBoolean("autoChord"),json.optInt("progression").coerceIn(0,2),json.optDouble("master",.65).toFloat().coerceIn(0f,1f),json.optDouble("keys",.78).toFloat().coerceIn(0f,1f),json.optDouble("chords",.22).toFloat().coerceIn(0f,1f),json.optDouble("drums",.55).toFloat().coerceIn(0f,1f),json.optDouble("effectMix",.28).toFloat().coerceIn(0f,1f))
    }
}
