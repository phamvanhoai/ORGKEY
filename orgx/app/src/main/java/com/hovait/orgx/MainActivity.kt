package com.hovait.orgx

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
    var octave by remember { mutableIntStateOf(4) }
    var bpm by remember { mutableIntStateOf(120) }
    var playing by remember { mutableStateOf(false) }
    var sustain by remember { mutableStateOf(false) }
    var instrument by remember { mutableIntStateOf(0) }
    var chord by remember { mutableIntStateOf(0) }
    var recording by remember { mutableStateOf(false) }
    var showMixer by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }
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
                }, onAbout = { showAbout = true })
                Row(Modifier.weight(0.52f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val chordNames = listOf("C", "Am", "F", "G")
                    PerformancePanel(octave, bpm, playing, sustain, chordNames[chord],
                        onOctave = { octave = (octave + it).coerceIn(1, 7) },
                        onBpm = { delta -> bpm = (bpm + delta).coerceIn(40, 240); engine.bpm = bpm },
                        onPlay = { playing = !playing; engine.setRhythmEnabled(playing) },
                        onSustain = { sustain = !sustain; engine.sustain = sustain; if (!sustain) engine.releaseSustain() },
                        onChord = { chord = (chord + 1) % chordNames.size; engine.chordIndex = chord },
                        onMixer = { showMixer = true }, modifier = Modifier.weight(1f))
                    StatusPanel(bpm, octave, playing, chordNames[chord], listOf("ORGX Grand", "ORGX Organ", "ORGX Lead")[instrument], modifier = Modifier.weight(1.3f))
                }
                PianoKeyboard(octave, engine, Modifier.weight(0.48f).fillMaxWidth())
            }
            if (showMixer) MixerDialog(engine, instrument, onInstrument = { instrument = it; engine.instrument = it }, onDismiss = { showMixer = false })
            if (showAbout) AboutDialog(onDismiss = { showAbout = false })
        }
    }
}

@Composable private fun Header(recording:Boolean,onRecord:()->Unit,onAbout: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("ORGX", color = Text, fontWeight = FontWeight.Black, fontSize = 25.sp)
        Spacer(Modifier.width(10.dp)); Text("MUSIC WORKSTATION", color = Accent, fontSize = 12.sp, letterSpacing = 2.sp)
        Spacer(Modifier.weight(1f)); TextButton(onClick=onRecord,colors=ButtonDefaults.textButtonColors(contentColor=if(recording) Color(0xFFEF4444) else Subtle)){Text(if(recording) "STOP REC" else "REC",fontWeight=FontWeight.Bold)};TextButton(onClick = onAbout) { Text("DESIGNED BY HOVAIT", color = Subtle) }
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

@Composable private fun StatusPanel(bpm:Int,octave:Int,playing:Boolean,chordName:String,instrumentName:String,modifier:Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(14.dp)).padding(14.dp)) {
        Row { Column(Modifier.weight(1f)) { Text("LIVE SET",color=Accent,fontSize=11.sp); Text(instrumentName,color=Text,fontSize=22.sp,fontWeight=FontWeight.Bold) }; StatusDot(playing) }
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

@Composable private fun MixerDialog(engine:AudioEngine,instrument:Int,onInstrument:(Int)->Unit,onDismiss:()->Unit){var volume by remember{mutableFloatStateOf(engine.masterVolume)};AlertDialog(onDismissRequest=onDismiss,title={Text("Sound & mixer")},text={Column{Text("INSTRUMENT",fontWeight=FontWeight.Bold);Row(horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Grand","Organ","Lead").forEachIndexed{index,name->FilterChip(selected=instrument==index,onClick={onInstrument(index)},label={Text(name)})}};Spacer(Modifier.height(12.dp));Text("OUTPUT LEVEL ${(volume*100).toInt()}%") ; Slider(value=volume,onValueChange={volume=it;engine.masterVolume=it})}},confirmButton={TextButton(onClick=onDismiss){Text("DONE")}})}
@Composable private fun AboutDialog(onDismiss:()->Unit){AlertDialog(onDismissRequest=onDismiss,title={Text("ORGX")},text={Column{Text("Music Workstation",fontWeight=FontWeight.Bold);Spacer(Modifier.height(8.dp));Text("Designed by hovait");Text("Version 0.1.0",color=Subtle)}},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})}


