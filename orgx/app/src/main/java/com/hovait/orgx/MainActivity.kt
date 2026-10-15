package com.hovait.orgx

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF0F0F23)
private val Panel = Color(0xFF1B1B30)
private val Muted = Color(0xFF27273B)
private val Border = Color(0xFF4338CA)
private val Accent = Color(0xFF22C55E)
private val Text = Color(0xFFF8FAFC)
private val Subtle = Color(0xFF94A3B8)

class MainActivity : ComponentActivity() {
    private lateinit var engine: AudioEngine
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        engine = AudioEngine(getExternalFilesDir(Environment.DIRECTORY_MUSIC) ?: filesDir)
        setContent { ORGXApp(engine) }
    }
    override fun onDestroy() { engine.close(); super.onDestroy() }
}

@Composable
private fun ORGXApp(engine: AudioEngine) {
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("orgx-settings", 0) }
    val chordNames = listOf("C","Dm","Em","F","G","Am","Bdim","C7","D7","E7","Fmaj7","G7")
    var octave by remember { mutableIntStateOf(preferences.getInt("octave", 4)) }
    var bpm by remember { mutableIntStateOf(preferences.getInt("bpm", 120)) }
    var playing by remember { mutableStateOf(false) }
    var sustain by remember { mutableStateOf(false) }
    var instrument by remember { mutableIntStateOf(preferences.getInt("instrument", 0).coerceIn(0, 2)) }
    var chord by remember { mutableIntStateOf(preferences.getInt("chord", 0).coerceIn(0, chordNames.lastIndex)) }
    var style by remember { mutableIntStateOf(preferences.getInt("style", 0).coerceIn(0, 2)) }
    var effect by remember { mutableIntStateOf(preferences.getInt("effect", 0).coerceIn(0, 2)) }
    var recording by remember { mutableStateOf(false) }
    var showMixer by remember { mutableStateOf(false) }
    var showSets by remember { mutableStateOf(false) }
    var showChords by remember { mutableStateOf(false) }
    var showPads by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        engine.bpm = bpm; engine.instrument = instrument; engine.chordIndex = chord; engine.rhythmStyle = style;engine.effectType=effect
        engine.masterVolume=preferences.getFloat("masterVolume",0.65f);engine.keysVolume=preferences.getFloat("keysVolume",0.78f)
        engine.chordVolume=preferences.getFloat("chordVolume",0.22f);engine.drumsVolume=preferences.getFloat("drumsVolume",0.55f)
        engine.effectMix=preferences.getFloat("effectMix",0.28f)
    }
    LaunchedEffect(octave, bpm, instrument, chord, style, effect) {
        preferences.edit().putInt("octave", octave).putInt("bpm", bpm)
            .putInt("instrument", instrument).putInt("chord", chord).putInt("style", style).putInt("effect",effect).apply()
    }
    MaterialTheme(colorScheme = darkColorScheme(primary = Accent, background = Bg, surface = Panel)) {
        Box(Modifier.fillMaxSize().background(Bg).systemBarsPadding()) {
            Column(Modifier.fillMaxSize().padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Header(recording, onRecord = {
                    if (recording) {
                        val file = engine.stopRecording()
                        recording = false
                        Toast.makeText(context, file?.let { "Saved: ${it.name}" } ?: "Nothing recorded", Toast.LENGTH_LONG).show()
                    } else {
                        engine.startRecording(); recording = true
                    }
                }, onPads={showPads=true},onSets = { showSets = true }, onAbout = { showAbout = true })
                Row(Modifier.weight(0.52f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PerformancePanel(octave, bpm, playing, sustain, chordNames[chord],
                        onOctave = { octave = (octave + it).coerceIn(1, 7) },
                        onBpm = { delta -> bpm = (bpm + delta).coerceIn(40, 240); engine.bpm = bpm },
                        onPlay = { playing = !playing; engine.setRhythmEnabled(playing) },
                        onSustain = { sustain = !sustain; engine.sustain = sustain; if (!sustain) engine.releaseSustain() },
                        onChord = { showChords = true },
                        onMixer = { showMixer = true }, modifier = Modifier.weight(1f))
                    StatusPanel(bpm, octave, playing, chordNames[chord], listOf("Pop", "Rock", "Dance")[style], listOf("ORGX Grand", "ORGX Organ", "ORGX Lead")[instrument], modifier = Modifier.weight(1.3f))
                }
                PianoKeyboard(octave, engine, Modifier.weight(0.48f).fillMaxWidth())
            }
            if (showMixer) MixerDialog(engine, preferences, instrument, style, effect,
                onInstrument = { instrument = it; engine.instrument = it },
                onStyle = { style = it; engine.rhythmStyle = it },
                onEffect = { effect = it; engine.effectType = it },
                onDismiss = { showMixer = false })
            if (showSets) SetMemoryDialog(preferences, octave, bpm, instrument, chord, style, effect, engine,
                onLoad = { loadedOctave, loadedBpm, loadedInstrument, loadedChord, loadedStyle, loadedEffect, loadedVolume ->
                    octave = loadedOctave; bpm = loadedBpm; instrument = loadedInstrument
                    chord = loadedChord; style = loadedStyle; effect=loadedEffect;engine.bpm = bpm
                    engine.instrument = instrument; engine.chordIndex = chord
                    engine.rhythmStyle = style;engine.effectType=effect; engine.masterVolume = loadedVolume
                }, onDismiss = { showSets = false })
            if(showChords) ChordDialog(chordNames,chord,onSelect={chord=it;engine.chordIndex=it;showChords=false},onDismiss={showChords=false})
            if(showPads) PadDialog(engine,onDismiss={showPads=false})
            if (showAbout) AboutDialog(onDismiss = { showAbout = false })
        }
    }
}

@Composable private fun Header(recording:Boolean,onRecord:()->Unit,onPads:()->Unit,onSets:()->Unit,onAbout: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("ORGX", color = Text, fontWeight = FontWeight.Black, fontSize = 25.sp)
        Spacer(Modifier.width(10.dp)); Text("MUSIC WORKSTATION", color = Accent, fontSize = 12.sp, letterSpacing = 2.sp)
        Spacer(Modifier.weight(1f));TextButton(onClick=onPads){Text("PAD",color=Subtle,fontWeight=FontWeight.Bold)}; TextButton(onClick=onSets){Text("SET",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onRecord,colors=ButtonDefaults.textButtonColors(contentColor=if(recording) Color(0xFFEF4444) else Subtle)){Text(if(recording) "STOP REC" else "REC",fontWeight=FontWeight.Bold)};TextButton(onClick = onAbout) { Text("DESIGNED BY HOVAIT", color = Subtle) }
    }
}

@Composable private fun PerformancePanel(octave:Int,bpm:Int,playing:Boolean,sustain:Boolean,chordName:String,onOctave:(Int)->Unit,onBpm:(Int)->Unit,onPlay:()->Unit,onSustain:()->Unit,onChord:()->Unit,onMixer:()->Unit,modifier:Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(14.dp)).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("PERFORMANCE", color=Subtle, fontSize=11.sp, fontWeight=FontWeight.Bold)
        Row(horizontalArrangement=Arrangement.spacedBy(8.dp)) {
            ControlButton(if(playing) "STOP" else "PLAY", playing, onPlay, Modifier.weight(1f))
            ControlButton("SUSTAIN", sustain, onSustain, Modifier.weight(1f))
            ControlButton("CHORD $chordName", playing, onChord, Modifier.weight(1f))
            ControlButton("MIXER", false, onMixer, Modifier.weight(1f))
        }
        Row(verticalAlignment=Alignment.CenterVertically, horizontalArrangement=Arrangement.spacedBy(8.dp)) {
            Stepper("OCTAVE", octave, {onOctave(-1)}, {onOctave(1)}, Modifier.weight(1f))
            Stepper("TEMPO", bpm, {onBpm(-5)}, {onBpm(5)}, Modifier.weight(1f))
        }
    }
}

@Composable private fun StatusPanel(bpm:Int,octave:Int,playing:Boolean,chordName:String,styleName:String,instrumentName:String,modifier:Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(14.dp)).padding(14.dp)) {
        Row { Column(Modifier.weight(1f)) { Text("LIVE SET • ${styleName.uppercase()}",color=Accent,fontSize=11.sp); Text(instrumentName,color=Text,fontSize=22.sp,fontWeight=FontWeight.Bold) }; StatusDot(playing) }
        Spacer(Modifier.weight(1f)); Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.SpaceBetween) {
            Stat("TEMPO", "$bpm BPM"); Stat("CHORD", chordName); Stat("OCTAVE", octave.toString()); Stat("ENGINE", "48 kHz")
        }
    }
}
@Composable private fun Stat(label:String,value:String){Column{Text(label,color=Subtle,fontSize=9.sp);Text(value,color=Text,fontWeight=FontWeight.SemiBold)}}
@Composable private fun StatusDot(active:Boolean){Box(Modifier.size(12.dp).background(if(active) Accent else Color(0xFF475569), RoundedCornerShape(99.dp)))}

@Composable private fun ControlButton(label:String,active:Boolean,onClick:()->Unit,modifier:Modifier=Modifier){Button(onClick=onClick,modifier=modifier.height(48.dp),contentPadding=PaddingValues(horizontal=4.dp),colors=ButtonDefaults.buttonColors(containerColor=if(active) Accent else Muted,contentColor=if(active) Bg else Text),shape=RoundedCornerShape(9.dp)){Text(label,fontWeight=FontWeight.Bold,fontSize=10.sp,maxLines=1)}}
@Composable private fun Stepper(label:String,value:Int,onMinus:()->Unit,onPlus:()->Unit,modifier:Modifier){Row(modifier.height(44.dp).background(Muted,RoundedCornerShape(9.dp)),verticalAlignment=Alignment.CenterVertically){TextButton(onClick=onMinus,contentPadding=PaddingValues(0.dp),modifier=Modifier.width(44.dp)){Text("-",color=Text,fontSize=18.sp)};Text("$label  $value",modifier=Modifier.weight(1f),color=Text,fontSize=11.sp,fontWeight=FontWeight.Bold);TextButton(onClick=onPlus,contentPadding=PaddingValues(0.dp),modifier=Modifier.width(44.dp)){Text("+",color=Text,fontSize=18.sp)}}}

@Composable private fun PianoKeyboard(octave:Int, engine:AudioEngine, modifier:Modifier) {
    val active = remember { mutableStateMapOf<Int,Int>() }
    Canvas(modifier.background(Color(0xFF080811), RoundedCornerShape(12.dp)).pointerInput(octave) {
        fun noteAt(position: Offset): Int {
            val whites = 14
            val width = size.width / whites
            val scale = intArrayOf(0, 2, 4, 5, 7, 9, 11)
            if (position.y < size.height * 0.62f) {
                for (leftWhite in 0 until whites - 1) {
                    if (leftWhite % 7 in setOf(0, 1, 3, 4, 5)) {
                        val center = (leftWhite + 1) * width
                        if (kotlin.math.abs(position.x - center) <= width * 0.29f) {
                            return (octave + 1) * 12 + (leftWhite / 7) * 12 + scale[leftWhite % 7] + 1
                        }
                    }
                }
            }
            val white = (position.x / width).toInt().coerceIn(0, whites - 1)
            return (octave + 1) * 12 + (white / 7) * 12 + scale[white % 7]
        }
        awaitEachGesture {
            val pointerNotes = mutableMapOf<Long, Int>()
            do {
                val event = awaitPointerEvent()
                    event.changes.forEach { change ->
                        val id = change.id.value
                        if (change.changedToDown()) {
                            val note = noteAt(change.position)
                            pointerNotes[id] = note
                            active[note] = (active[note] ?: 0) + 1
                            engine.noteOn(note)
                        } else if (change.changedToUpIgnoreConsumed()) {
                            pointerNotes.remove(id)?.let { note ->
                                val count = (active[note] ?: 1) - 1
                                if (count <= 0) { active.remove(note); engine.noteOff(note) } else active[note] = count
                            }
                        } else if (change.pressed) {
                            val previous = pointerNotes[id]
                            val current = noteAt(change.position)
                            if (previous != null && previous != current) {
                                val count = (active[previous] ?: 1) - 1
                                if (count <= 0) { active.remove(previous); engine.noteOff(previous) } else active[previous] = count
                                pointerNotes[id] = current
                                active[current] = (active[current] ?: 0) + 1
                                engine.noteOn(current)
                            }
                        }
                        change.consume()
                    }
            } while (event.changes.any { it.pressed })
            pointerNotes.values.forEach { engine.noteOff(it); active.remove(it) }
        }
    }) {
        val whites=14; val w=size.width/whites
        for(i in 0 until whites){val x=i*w;drawRoundRect(Color(0xFFF4F4F5),Offset(x+1,1f),androidx.compose.ui.geometry.Size(w-2,size.height-2),cornerRadius=androidx.compose.ui.geometry.CornerRadius(5f));drawLine(Color(0xFF94A3B8),Offset(x,0f),Offset(x,size.height),2f)}
        val blackAfter=setOf(0,1,3,4,5)
        for(i in 0 until whites-1) if(i%7 in blackAfter){val x=(i+1)*w-w*.29f;drawRoundRect(Color(0xFF11111F),Offset(x,0f),androidx.compose.ui.geometry.Size(w*.58f,size.height*.62f),cornerRadius=androidx.compose.ui.geometry.CornerRadius(5f));drawRoundRect(Border,Offset(x,0f),androidx.compose.ui.geometry.Size(w*.58f,size.height*.62f),cornerRadius=androidx.compose.ui.geometry.CornerRadius(5f),style=Stroke(1.5f))}
        if(active.isNotEmpty()) drawCircle(Accent,12f,Offset(size.width-24f,24f))
    }
}

@Composable private fun MixerDialog(engine:AudioEngine,preferences:SharedPreferences,instrument:Int,style:Int,effect:Int,onInstrument:(Int)->Unit,onStyle:(Int)->Unit,onEffect:(Int)->Unit,onDismiss:()->Unit){
    var master by remember{mutableFloatStateOf(engine.masterVolume)};var keys by remember{mutableFloatStateOf(engine.keysVolume)}
    var chords by remember{mutableFloatStateOf(engine.chordVolume)};var drums by remember{mutableFloatStateOf(engine.drumsVolume)}
    var fx by remember{mutableFloatStateOf(engine.effectMix)}
    var mixChannel by remember{mutableIntStateOf(0)}
    fun saveMix(){preferences.edit().putFloat("masterVolume",master).putFloat("keysVolume",keys).putFloat("chordVolume",chords).putFloat("drumsVolume",drums).putFloat("effectMix",fx).apply()}
    AlertDialog(onDismissRequest={saveMix();onDismiss()},title={Text("Sound & mixer")},text={Column{
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){Text("VOICE",Modifier.width(64.dp),fontWeight=FontWeight.Bold);Row(Modifier.weight(1f),horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Grand","Organ","Lead").forEachIndexed{index,name->FilterChip(selected=instrument==index,onClick={onInstrument(index)},label={Text(name,maxLines=1)},modifier=Modifier.weight(1f))}}}
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){Text("STYLE",Modifier.width(64.dp),fontWeight=FontWeight.Bold);Row(Modifier.weight(1f),horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Pop","Rock","Dance").forEachIndexed{index,name->FilterChip(selected=style==index,onClick={onStyle(index)},label={Text(name,maxLines=1)},modifier=Modifier.weight(1f))}}}
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){Text("FX",Modifier.width(64.dp),fontWeight=FontWeight.Bold);Row(Modifier.weight(1f),horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Dry","Delay","Reverb").forEachIndexed{index,name->FilterChip(selected=effect==index,onClick={onEffect(index)},label={Text(name,maxLines=1)},modifier=Modifier.weight(1f))}}}
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){Text("MIX",Modifier.width(64.dp),fontWeight=FontWeight.Bold);Column(Modifier.weight(1f)){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(3.dp)){listOf("Keys","Chord","Drums").forEachIndexed{index,name->FilterChip(selected=mixChannel==index,onClick={mixChannel=index},label={Text(name,fontSize=8.sp,maxLines=1)},modifier=Modifier.weight(1f))}};Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(3.dp)){listOf("Master","FX").forEachIndexed{offset,name->val index=offset+3;FilterChip(selected=mixChannel==index,onClick={mixChannel=index},label={Text(name,fontSize=8.sp,maxLines=1)},modifier=Modifier.weight(1f))}}}}
        val level=when(mixChannel){0->keys;1->chords;2->drums;3->master;else->fx}
        Text("${listOf("KEYS","CHORD","DRUMS","MASTER","FX")[mixChannel]} ${(level*100).toInt()}%",fontSize=10.sp,fontWeight=FontWeight.Bold)
        Slider(value=level,onValueChange={when(mixChannel){0->{keys=it;engine.keysVolume=it};1->{chords=it;engine.chordVolume=it};2->{drums=it;engine.drumsVolume=it};3->{master=it;engine.masterVolume=it};else->{fx=it;engine.effectMix=it}}})
    }},confirmButton={TextButton(onClick={saveMix();onDismiss()}){Text("DONE")}})
}
@Composable private fun SetMemoryDialog(preferences:SharedPreferences,octave:Int,bpm:Int,instrument:Int,chord:Int,style:Int,effect:Int,engine:AudioEngine,onLoad:(Int,Int,Int,Int,Int,Int,Float)->Unit,onDismiss:()->Unit){
    var revision by remember{mutableIntStateOf(0)}
    AlertDialog(onDismissRequest=onDismiss,title={Text("SET memory")},text={Column(verticalArrangement=Arrangement.spacedBy(8.dp)){
        repeat(3){index->
            val slot=index+1;val exists=preferences.getBoolean("set${slot}_saved",false);revision
            Row(Modifier.fillMaxWidth().background(Muted,RoundedCornerShape(8.dp)).padding(horizontal=10.dp),verticalAlignment=Alignment.CenterVertically){
                Text("SET $slot",Modifier.weight(1f),fontWeight=FontWeight.Bold)
                TextButton(enabled=exists,onClick={engine.keysVolume=preferences.getFloat("set${slot}_keys",0.78f);engine.chordVolume=preferences.getFloat("set${slot}_chords",0.22f);engine.drumsVolume=preferences.getFloat("set${slot}_drums",0.55f);engine.effectMix=preferences.getFloat("set${slot}_fx",0.28f);onLoad(preferences.getInt("set${slot}_octave",4),preferences.getInt("set${slot}_bpm",120),preferences.getInt("set${slot}_instrument",0),preferences.getInt("set${slot}_chord",0),preferences.getInt("set${slot}_style",0),preferences.getInt("set${slot}_effect",0),preferences.getFloat("set${slot}_volume",0.65f));onDismiss()}){Text("LOAD")}
                TextButton(onClick={preferences.edit().putBoolean("set${slot}_saved",true).putInt("set${slot}_octave",octave).putInt("set${slot}_bpm",bpm).putInt("set${slot}_instrument",instrument).putInt("set${slot}_chord",chord).putInt("set${slot}_style",style).putInt("set${slot}_effect",effect).putFloat("set${slot}_volume",engine.masterVolume).putFloat("set${slot}_keys",engine.keysVolume).putFloat("set${slot}_chords",engine.chordVolume).putFloat("set${slot}_drums",engine.drumsVolume).putFloat("set${slot}_fx",engine.effectMix).apply();revision++}){Text(if(exists) "UPDATE" else "SAVE")}
            }
        }
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun ChordDialog(names:List<String>,selected:Int,onSelect:(Int)->Unit,onDismiss:()->Unit){
    AlertDialog(onDismissRequest=onDismiss,title={Text("Chord library")},text={Column(verticalArrangement=Arrangement.spacedBy(6.dp)){
        names.chunked(4).forEachIndexed{row,items->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){items.forEachIndexed{column,name->val index=row*4+column;FilterChip(selected=selected==index,onClick={onSelect(index)},label={Text(name)},modifier=Modifier.weight(1f))}}}
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun PadDialog(engine:AudioEngine,onDismiss:()->Unit){
    val names=listOf("KICK","SNARE","HI-HAT","CLAP","LOW TOM","HIGH TOM","CRASH","PERC")
    AlertDialog(onDismissRequest=onDismiss,title={Text("Performance pads")},text={Column(verticalArrangement=Arrangement.spacedBy(8.dp)){
        names.chunked(4).forEachIndexed{row,items->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){items.forEachIndexed{column,name->val index=row*4+column;Button(onClick={engine.triggerPad(index)},modifier=Modifier.weight(1f).height(62.dp),contentPadding=PaddingValues(3.dp),shape=RoundedCornerShape(9.dp)){Text(name,fontSize=9.sp,fontWeight=FontWeight.Bold,maxLines=2)}}}}
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun AboutDialog(onDismiss:()->Unit){AlertDialog(onDismissRequest=onDismiss,title={Text("ORGX")},text={Column{Text("Music Workstation",fontWeight=FontWeight.Bold);Spacer(Modifier.height(8.dp));Text("Designed by hovait");Text("Version 0.1.0",color=Subtle)}},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})}


