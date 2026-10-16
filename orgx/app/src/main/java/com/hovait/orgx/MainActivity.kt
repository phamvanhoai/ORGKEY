package com.hovait.orgx

import android.content.SharedPreferences
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
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
import java.io.File

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
        engine = AudioEngine(getExternalFilesDir(Environment.DIRECTORY_MUSIC) ?: filesDir,SampleBank(resources))
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
    var autoChord by remember { mutableStateOf(preferences.getBoolean("autoChord",false)) }
    var progression by remember { mutableIntStateOf(preferences.getInt("progression",0).coerceIn(0,2)) }
    var recording by remember { mutableStateOf(false) }
    var showMixer by remember { mutableStateOf(false) }
    var showSets by remember { mutableStateOf(false) }
    var showChords by remember { mutableStateOf(false) }
    var showPads by remember { mutableStateOf(false) }
    var showFiles by remember { mutableStateOf(false) }
    var showProjects by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }
    var midiPlaying by remember { mutableStateOf(false) }
    val midiPicker=rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()){uri->
        if(uri!=null) runCatching{context.contentResolver.openInputStream(uri)?.use{MidiFile.read(it)}?:error("Cannot read MIDI")}.onSuccess{song->
            midiPlaying=true;engine.playMidi(song){Handler(Looper.getMainLooper()).post{midiPlaying=false}}
            Toast.makeText(context,"MIDI: ${song.events.count{it.on}} notes",Toast.LENGTH_SHORT).show()
        }.onFailure{Toast.makeText(context,"Invalid or unsupported MIDI",Toast.LENGTH_LONG).show()}
    }
    LaunchedEffect(Unit) {
        engine.bpm = bpm; engine.instrument = instrument; engine.chordIndex = chord; engine.rhythmStyle = style;engine.effectType=effect
        engine.autoChord=autoChord;engine.progression=progression
        engine.masterVolume=preferences.getFloat("masterVolume",0.65f);engine.keysVolume=preferences.getFloat("keysVolume",0.78f)
        engine.chordVolume=preferences.getFloat("chordVolume",0.22f);engine.drumsVolume=preferences.getFloat("drumsVolume",0.55f)
        engine.effectMix=preferences.getFloat("effectMix",0.28f)
    }
    LaunchedEffect(octave, bpm, instrument, chord, style, effect, autoChord, progression) {
        preferences.edit().putInt("octave", octave).putInt("bpm", bpm)
            .putInt("instrument", instrument).putInt("chord", chord).putInt("style", style).putInt("effect",effect)
            .putBoolean("autoChord",autoChord).putInt("progression",progression).apply()
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
                }, onFiles={showFiles=true},onProjects={showProjects=true},midiPlaying=midiPlaying,onMidi={if(midiPlaying){engine.stopMidi();midiPlaying=false}else midiPicker.launch(arrayOf("audio/midi","audio/x-midi","application/octet-stream"))},onPads={showPads=true},onSets = { showSets = true }, onAbout = { showAbout = true })
                Row(Modifier.weight(0.52f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PerformancePanel(octave, bpm, playing, sustain, chordNames[chord],
                        onOctave = { octave = (octave + it).coerceIn(1, 7) },
                        onBpm = { delta -> bpm = (bpm + delta).coerceIn(40, 240); engine.bpm = bpm },
                        onPlay = { playing = !playing; engine.setRhythmEnabled(playing) },
                        onSustain = { sustain = !sustain; engine.sustain = sustain; if (!sustain) engine.releaseSustain() },
                        onChord = { showChords = true },
                        onMixer = { showMixer = true }, modifier = Modifier.weight(1f))
                    val chordDisplay=if(autoChord) "AUTO ${listOf("POP","BALLAD","DANCE")[progression]}" else chordNames[chord]
                    StatusPanel(bpm, octave, playing, chordDisplay, listOf("Pop", "Rock", "Dance")[style], listOf("ORGX Grand", "ORGX Organ", "ORGX Lead")[instrument], modifier = Modifier.weight(1.3f))
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
            if(showChords) ChordDialog(chordNames,chord,autoChord,progression,
                onSelect={chord=it;autoChord=false;engine.autoChord=false;engine.chordIndex=it;showChords=false},
                onAuto={enabled,preset->autoChord=enabled;progression=preset;engine.autoChord=enabled;engine.progression=preset;showChords=false},
                onDismiss={showChords=false})
            if(showPads) PadDialog(engine,onDismiss={showPads=false})
            if(showFiles) RecordingDialog(engine,onDismiss={showFiles=false})
            if(showProjects) ProjectDialog(ProjectStore(context),ProjectState(octave,bpm,instrument,chord,style,effect,autoChord,progression,engine.masterVolume,engine.keysVolume,engine.chordVolume,engine.drumsVolume,engine.effectMix),onLoad={project->
                octave=project.octave;bpm=project.bpm;instrument=project.instrument;chord=project.chord;style=project.style;effect=project.effect;autoChord=project.autoChord;progression=project.progression
                engine.bpm=bpm;engine.instrument=instrument;engine.chordIndex=chord;engine.rhythmStyle=style;engine.effectType=effect;engine.autoChord=autoChord;engine.progression=progression
                engine.masterVolume=project.master;engine.keysVolume=project.keys;engine.chordVolume=project.chords;engine.drumsVolume=project.drums;engine.effectMix=project.effectMix
            },onDismiss={showProjects=false})
            if (showAbout) AboutDialog(onDismiss = { showAbout = false })
        }
    }
}

@Composable private fun Header(recording:Boolean,onRecord:()->Unit,onFiles:()->Unit,onProjects:()->Unit,midiPlaying:Boolean,onMidi:()->Unit,onPads:()->Unit,onSets:()->Unit,onAbout: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("ORGX", color = Text, fontWeight = FontWeight.Black, fontSize = 25.sp)
        Spacer(Modifier.width(10.dp)); Text("MUSIC WORKSTATION", color = Accent, fontSize = 12.sp, letterSpacing = 2.sp)
        Spacer(Modifier.weight(1f));TextButton(onClick=onFiles){Text("FILES",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onProjects){Text("PROJECT",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onMidi){Text(if(midiPlaying) "STOP MIDI" else "MIDI",color=if(midiPlaying) Accent else Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onPads){Text("PAD",color=Subtle,fontWeight=FontWeight.Bold)}; TextButton(onClick=onSets){Text("SET",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onRecord,colors=ButtonDefaults.textButtonColors(contentColor=if(recording) Color(0xFFEF4444) else Subtle)){Text(if(recording) "STOP REC" else "REC",fontWeight=FontWeight.Bold)};TextButton(onClick = onAbout) { Text("DESIGNED BY HOVAIT", color = Subtle) }
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
                            val velocity = (0.25f + 0.75f * (change.position.y / size.height)).coerceIn(0.25f, 1f)
                            pointerNotes[id] = note
                            active[note] = (active[note] ?: 0) + 1
                            engine.noteOn(note,velocity)
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
                                val velocity = (0.25f + 0.75f * (change.position.y / size.height)).coerceIn(0.25f, 1f)
                                engine.noteOn(current,velocity)
                            }
                        }
                        change.consume()
                    }
            } while (event.changes.any { it.pressed })
            pointerNotes.values.forEach { engine.noteOff(it); active.remove(it) }
        }
    }) {
        val whites=14; val w=size.width/whites
        val scale=intArrayOf(0,2,4,5,7,9,11)
        for(i in 0 until whites){val x=i*w;val note=(octave+1)*12+(i/7)*12+scale[i%7];val color=if(active.containsKey(note)) Color(0xFF86EFAC) else Color(0xFFF4F4F5);drawRoundRect(color,Offset(x+1,1f),androidx.compose.ui.geometry.Size(w-2,size.height-2),cornerRadius=androidx.compose.ui.geometry.CornerRadius(5f));drawLine(Color(0xFF94A3B8),Offset(x,0f),Offset(x,size.height),2f)}
        val blackAfter=setOf(0,1,3,4,5)
        for(i in 0 until whites-1) if(i%7 in blackAfter){val x=(i+1)*w-w*.29f;val note=(octave+1)*12+(i/7)*12+scale[i%7]+1;val color=if(active.containsKey(note)) Accent else Color(0xFF11111F);drawRoundRect(color,Offset(x,0f),androidx.compose.ui.geometry.Size(w*.58f,size.height*.62f),cornerRadius=androidx.compose.ui.geometry.CornerRadius(5f));drawRoundRect(Border,Offset(x,0f),androidx.compose.ui.geometry.Size(w*.58f,size.height*.62f),cornerRadius=androidx.compose.ui.geometry.CornerRadius(5f),style=Stroke(1.5f))}
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
@Composable private fun ChordDialog(names:List<String>,selected:Int,autoChord:Boolean,progression:Int,onSelect:(Int)->Unit,onAuto:(Boolean,Int)->Unit,onDismiss:()->Unit){
    AlertDialog(onDismissRequest=onDismiss,title={Text("Chord library")},text={Column(verticalArrangement=Arrangement.spacedBy(6.dp)){
        Text("AUTO CHORD",fontWeight=FontWeight.Bold)
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Pop","Ballad","Dance").forEachIndexed{index,name->FilterChip(selected=autoChord&&progression==index,onClick={onAuto(true,index)},label={Text(name)},modifier=Modifier.weight(1f))}}
        Text("MANUAL",fontWeight=FontWeight.Bold)
        names.chunked(6).forEachIndexed{row,items->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(3.dp)){items.forEachIndexed{column,name->val index=row*6+column;FilterChip(selected=!autoChord&&selected==index,onClick={onSelect(index)},label={Text(name,fontSize=8.sp,maxLines=1)},modifier=Modifier.weight(1f))}}}
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun PadDialog(engine:AudioEngine,onDismiss:()->Unit){
    val names=listOf("KICK","SNARE","HI-HAT","CLAP","LOW TOM","HIGH TOM","CRASH","PERC")
    AlertDialog(onDismissRequest=onDismiss,title={Text("Performance pads")},text={Column(verticalArrangement=Arrangement.spacedBy(8.dp)){
        names.chunked(4).forEachIndexed{row,items->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){items.forEachIndexed{column,name->val index=row*4+column;Button(onClick={engine.triggerPad(index)},modifier=Modifier.weight(1f).height(62.dp),contentPadding=PaddingValues(3.dp),shape=RoundedCornerShape(9.dp)){Text(name,fontSize=9.sp,fontWeight=FontWeight.Bold,maxLines=2)}}}}
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun ProjectDialog(store:ProjectStore,current:ProjectState,onLoad:(ProjectState)->Unit,onDismiss:()->Unit){
    val context=LocalContext.current
    var files by remember{mutableStateOf(store.list())}
    AlertDialog(onDismissRequest=onDismiss,title={Text("ORGX projects (${files.size})")},text={Column(verticalArrangement=Arrangement.spacedBy(7.dp)){
        Button(onClick={val file=store.save(current);files=store.list();Toast.makeText(context,"Saved: ${file.name}",Toast.LENGTH_SHORT).show()},modifier=Modifier.fillMaxWidth()){Text("SAVE CURRENT PROJECT")}
        if(files.isEmpty()) Text("No saved projects yet",color=Subtle)
        files.take(5).forEach{file->
            Row(Modifier.fillMaxWidth().background(Muted,RoundedCornerShape(8.dp)).padding(horizontal=8.dp),verticalAlignment=Alignment.CenterVertically){
                Column(Modifier.weight(1f)){Text(file.name,fontSize=10.sp,fontWeight=FontWeight.Bold,maxLines=1);Text("ORGX project • ${file.length()} bytes",fontSize=8.sp,color=Subtle)}
                TextButton(onClick={runCatching{store.load(file)}.onSuccess{onLoad(it);Toast.makeText(context,"Loaded: ${file.name}",Toast.LENGTH_SHORT).show();onDismiss()}.onFailure{Toast.makeText(context,"Cannot open project",Toast.LENGTH_SHORT).show()}}){Text("LOAD")}
                TextButton(onClick={val uri=FileProvider.getUriForFile(context,"${context.packageName}.files",file);context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply{type="application/json";putExtra(Intent.EXTRA_STREAM,uri);addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)},"Share ORGX project"))}){Text("SHARE")}
            }
        }
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun RecordingDialog(engine:AudioEngine,onDismiss:()->Unit){
    val context=LocalContext.current
    val files=remember{engine.recordings()}
    var playingFile by remember{mutableStateOf<File?>(null)}
    var player by remember{mutableStateOf<MediaPlayer?>(null)}
    DisposableEffect(Unit){onDispose{player?.release()}}
    AlertDialog(onDismissRequest={player?.release();onDismiss()},title={Text("Recordings (${files.size})")},text={Column(verticalArrangement=Arrangement.spacedBy(6.dp)){
        if(files.isEmpty()) Text("No WAV recordings yet",color=Subtle)
        files.take(5).forEach{file->
            val seconds=((file.length()-44).coerceAtLeast(0)/192000.0).toInt()
            val midiFile=File(file.parentFile,file.nameWithoutExtension+".mid")
            Row(Modifier.fillMaxWidth().background(Muted,RoundedCornerShape(8.dp)).padding(horizontal=8.dp),verticalAlignment=Alignment.CenterVertically){
                Column(Modifier.weight(1f)){Text(file.name,fontSize=9.sp,fontWeight=FontWeight.Bold,maxLines=1);Text("${seconds/60}:${(seconds%60).toString().padStart(2,'0')} • WAV 48 kHz${if(midiFile.exists()) " + MIDI" else ""}",fontSize=8.sp,color=Subtle)}
                TextButton(onClick={if(playingFile==file){player?.stop();player?.release();player=null;playingFile=null}else{player?.release();player=MediaPlayer().apply{setDataSource(file.absolutePath);prepare();setOnCompletionListener{it.release();player=null;playingFile=null};start()};playingFile=file}}){Text(if(playingFile==file)"STOP" else "PLAY")}
                TextButton(onClick={val uri=FileProvider.getUriForFile(context,"${context.packageName}.files",file);context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply{type="audio/wav";putExtra(Intent.EXTRA_STREAM,uri);addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)},"Share ORGX WAV"))}){Text("WAV")}
                if(midiFile.exists()) TextButton(onClick={val uri=FileProvider.getUriForFile(context,"${context.packageName}.files",midiFile);context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply{type="audio/midi";putExtra(Intent.EXTRA_STREAM,uri);addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)},"Share ORGX MIDI"))}){Text("MIDI")}
            }
        }
    }},confirmButton={TextButton(onClick={player?.release();onDismiss()}){Text("CLOSE")}})
}
@Composable private fun AboutDialog(onDismiss:()->Unit){AlertDialog(onDismissRequest=onDismiss,title={Text("ORGX")},text={Column{Text("Music Workstation",fontWeight=FontWeight.Bold);Spacer(Modifier.height(8.dp));Text("Designed by hovait");Text("Version 0.1.0",color=Subtle)}},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})}


