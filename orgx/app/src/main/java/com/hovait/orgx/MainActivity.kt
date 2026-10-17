package com.hovait.orgx

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

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
        NativeReferenceLoader.tryLoad()
        enableEdgeToEdge()
        engine = AudioEngine(getExternalFilesDir(Environment.DIRECTORY_MUSIC) ?: filesDir,SampleBank(resources),
            preferNativeOutput=getSharedPreferences("orgx-settings",0).getBoolean("nativeOutput",false)
                || ((applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE != 0)
                && intent.getBooleanExtra("orgx.testNativeOutput",false)),
            referenceDrumTracks=runCatching{ReferenceDrumStore.playableVariations(assets)}.getOrDefault(emptyList()))
        setContent { ORGXApp(engine) }
    }
    override fun onDestroy() { engine.close(); super.onDestroy() }
}

@Composable
private fun ORGXApp(engine: AudioEngine) {
    val context = LocalContext.current
    val preferences = remember { context.getSharedPreferences("orgx-settings", 0) }
    // Older builds defaulted to drum-only preview, omitting the SET accompaniment.
    remember(preferences){
        if(!preferences.getBoolean("fullStyleDefaultV1",false))preferences.edit()
            .putInt("bankMode",2).putBoolean("fullStyleDefaultV1",true).apply()
        true
    }
    var restoredSetName by remember{mutableStateOf<String?>(null)}
    var restoringSet by remember{mutableStateOf(preferences.getString("activeSetFile",preferences.getString("importedSetFile",null))!=null)}
    var bankIndex by remember{mutableStateOf(engine.activeSetIndex)}
    LaunchedEffect(Unit){
        val fileName=preferences.getString("importedSetFile",null)
        val soundName=preferences.getString("importedSetSound",null)
        if(fileName!=null && soundName!=null){
            try{
                val loaded=withContext(Dispatchers.IO){
                    val (file,sound)=ImportedSoundSelection.resolve(File(context.filesDir,"ReferenceSets"),fileName,soundName)
                    Triple(ImportedInstrumentLoader.load(file,sound),file,ImportedSetInstruments.index(file))
                }
                // A user may have changed voices while the file was loading.
                if(preferences.getString("importedSetFile",null)==fileName && preferences.getString("importedSetSound",null)==soundName){
                    engine.importedInstrument=loaded.first;engine.activeSetFile=loaded.second;engine.activeSetIndex=loaded.third;engine.activeSetSounds=ImportedInstrumentLoader.playableSounds(loaded.third.instruments);engine.importedInstrumentName=soundName;restoredSetName=soundName
                }
            }catch(error:Exception){Toast.makeText(context,"SET restore: ${error.message}",Toast.LENGTH_LONG).show()}
        }
        val bankName=preferences.getString("activeSetFile",fileName)
        if(bankName!=null && (engine.activeSetFile?.name!=bankName || engine.activeSetIndex==null)){
            try{
                val bank=withContext(Dispatchers.IO){ImportedSetBank.restore(File(context.filesDir,"ReferenceSets"),bankName)}
                if(preferences.getString("activeSetFile",fileName)==bankName){
                    engine.activeSetFile=bank.file;engine.activeSetIndex=bank.index;engine.activeSetSounds=bank.sounds
                }
            }catch(error:Exception){Toast.makeText(context,"SET styles: ${error.message}",Toast.LENGTH_LONG).show()}
        }
        bankIndex=engine.activeSetIndex
        val savedStyle=preferences.getString("selectedStyleSet",null)
        if(savedStyle!=null && savedStyle==engine.activeSetFile?.name){
            try{
                val file=checkNotNull(engine.activeSetFile)
                val index=checkNotNull(engine.activeSetIndex)
                val definition=ImportedStyleCatalog.styles(index).firstOrNull{it.id==preferences.getInt("selectedSetStyleId",Int.MIN_VALUE)}
                if(definition!=null){
                    val mode=preferences.getInt("bankMode",0).coerceIn(0,2)
                    val session=withContext(Dispatchers.IO){ImportedStylePlayer.loadStyle(file,index,definition,mode>0,
                        engine.referenceChordTransposer,if(mode==2)engine::selectedSetChord else null)}
                    engine.importedStyleSession=session;engine.importedRhythm=session.player;engine.importedRhythmName=session.displayName
                    engine.bpm=definition.sections.first{it.id==session.currentId}.tempo.coerceIn(40,240)
                }
            }catch(error:Exception){Toast.makeText(context,"SET style restore: ${error.message}",Toast.LENGTH_LONG).show()}
        }
        restoringSet=false
    }
    val builtInChordNames = listOf("C","Dm","Em","F","G","Am","Bdim","C7","D7","E7","Fmaj7","G7")
    val playableChords = remember { runCatching {
        context.assets.open("Chord").bufferedReader().use { PlayableChordLibrary.fromTable(it.readText()) }
    }.getOrDefault(emptyList()) }
    val chordNames = playableChords.map { it.name }.ifEmpty { builtInChordNames }
    var octave by remember { mutableIntStateOf(preferences.getInt("octave", 4)) }
    var bpm by remember { mutableIntStateOf(preferences.getInt("bpm", 120)) }
    var playing by remember { mutableStateOf(false) }
    var setSession by remember{mutableStateOf(engine.importedStyleSession)}
    var styleAutoFill by remember{mutableStateOf(preferences.getBoolean("styleAutoFill",false))}
    LaunchedEffect(styleAutoFill){engine.styleAutoFill=styleAutoFill}
    var sectionState by remember{mutableStateOf<Pair<Int,Int?>>(0 to null)}
    LaunchedEffect(engine){while(true){
        playing=engine.rhythmPlaying
        bankIndex=engine.activeSetIndex
        setSession=engine.importedStyleSession
        setSession?.let{sectionState=it.currentId to it.pendingId;bpm=engine.bpm}
        kotlinx.coroutines.delay(100)
    }}
    var sustain by remember { mutableStateOf(false) }
    var instrument by remember { mutableIntStateOf(InstrumentCatalog.validIndex(preferences.getInt("instrument", 0))) }
    var chord by remember { mutableIntStateOf(ChordSelection.resolve(chordNames, preferences.getString("chordName", null), preferences.getInt("chord", 0))) }
    var leftChord by remember{mutableStateOf(preferences.getBoolean("leftChord",false))}
    LaunchedEffect(engine){while(true){
        if(!engine.autoChord)engine.detectedKeyboardChord?.let{if(it in chordNames.indices){chord=it;engine.chordIndex=it}}
        kotlinx.coroutines.delay(40)
    }}
    var style by remember { mutableIntStateOf(StyleSelection.resolve(engine.styleIds,if(preferences.contains("styleId"))preferences.getInt("styleId",0) else null,preferences.getInt("style",0))) }
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
    var showTools by remember{mutableStateOf(false)}
    var browseStyles by remember{mutableStateOf(false)}
    var bankPage by remember{mutableIntStateOf(0)}
    var bankBusy by remember{mutableStateOf(false)}
    var bankStatus by remember{mutableStateOf("")}
    var bankMode by remember{mutableIntStateOf(preferences.getInt("bankMode",0).coerceIn(0,2))}
    val bankScope=rememberCoroutineScope()
    val bankStyles=remember(bankIndex){bankIndex?.let{ImportedStyleCatalog.styles(it)}.orEmpty()}
    val bankEnabled=remember(bankIndex,bankMode){bankStyles.map{definition->definition.sections.any{it.section in 32..35 && ImportedStyleSupport.reason(bankIndex,it,bankMode>0,bankMode==2)==null}}}
    var midiPlaying by remember { mutableStateOf(false) }
    val midiPicker=rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()){uri->
        if(uri!=null) runCatching{context.contentResolver.openInputStream(uri)?.use{MidiFile.read(it)}?:error("Cannot read MIDI")}.onSuccess{song->
            midiPlaying=true;engine.playMidi(song){Handler(Looper.getMainLooper()).post{midiPlaying=false}}
            Toast.makeText(context,"MIDI: ${song.events.count{it.on}} notes",Toast.LENGTH_SHORT).show()
        }.onFailure{Toast.makeText(context,"Invalid or unsupported MIDI",Toast.LENGTH_LONG).show()}
    }
    LaunchedEffect(Unit) {
        engine.referenceChords = playableChords
        engine.setLeftChordEnabled(leftChord)
        engine.referenceChordTransposer=runCatching{context.assets.open("Chord").bufferedReader().use{ReferenceChordTransposer.fromTable(it.readText())}}.getOrNull()
        engine.bpm = bpm; engine.instrument = instrument; engine.chordIndex = chord; engine.rhythmStyle = style;engine.effectType=effect
        engine.autoChord=autoChord;engine.progression=progression
        engine.masterVolume=preferences.getFloat("masterVolume",0.65f);engine.keysVolume=preferences.getFloat("keysVolume",0.78f)
        engine.chordVolume=preferences.getFloat("chordVolume",0.22f);engine.drumsVolume=preferences.getFloat("drumsVolume",0.55f)
        engine.effectMix=preferences.getFloat("effectMix",0.28f)
    }
    LaunchedEffect(octave, bpm, instrument, chord, style, effect, autoChord, progression) {
        preferences.edit().putInt("octave", octave).putInt("bpm", bpm)
            .putString("chordName", chordNames[chord]).putInt("instrument", instrument).putInt("chord", chord).putInt("styleId",engine.styleIds[style]).putInt("style", style).putInt("effect",effect)
            .putBoolean("autoChord",autoChord).putInt("progression",progression).apply()
    }
    MaterialTheme(colorScheme = darkColorScheme(primary = Accent, background = Bg, surface = Panel)) {
        Box(Modifier.fillMaxSize().background(Bg).systemBarsPadding()) {
            Column(Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                val chordDisplay=if(autoChord) "AUTO ${listOf("POP","BALLAD","DANCE")[progression]}" else chordNames[chord]
                Box(Modifier.weight(.55f).fillMaxWidth()){
                    InstrumentConsole(Modifier.fillMaxSize(),if(restoringSet) "LOADING SAVED SET..." else setSession?.displayName ?: if(bankIndex!=null) "SELECT SET STYLE" else engine.styleNames[style],
                        engine.importedInstrumentName ?: restoredSetName ?: InstrumentCatalog.names[instrument],chordDisplay,bpm,octave,
                        playing,sustain,styleAutoFill,setSession,sectionState.first,sectionState.second,
                        onMenu={showTools=true},onStyle={browseStyles=true;bankPage=0;bankStatus=""},onVoice={showMixer=true},onChord={showChords=true},
                        onPlay={if(!restoringSet && (bankIndex==null || engine.importedStyleSession!=null))engine.setRhythmEnabled(!engine.rhythmPlaying)},
                        onTempo={bpm=(bpm+it).coerceIn(40,240);engine.bpm=bpm},onOctave={octave=(octave+it).coerceIn(1,7)},
                        onSustain={sustain=!sustain;engine.sustain=sustain;if(!sustain)engine.releaseSustain()},
                        onAutoFill={styleAutoFill=!styleAutoFill;preferences.edit().putBoolean("styleAutoFill",styleAutoFill).apply()},
                        onPart={engine.requestStyleSection(it)},
                        stylePage=browseStyles,styleChoices=if(bankIndex!=null)bankStyles.map{it.name} else engine.styleNames,
                        styleEnabled=if(bankBusy || restoringSet)List(if(bankIndex!=null)bankStyles.size else engine.styleNames.size){false} else if(bankIndex!=null)bankEnabled else emptyList(),
                        page=bankPage,status=bankStatus,onPage={bankPage=(bankPage+it).coerceAtLeast(0)},onExit={if(!bankBusy)browseStyles=false},
                        modeLabel=if(bankIndex==null)"" else listOf("MODE: DRUM","MODE: FULL / ORIGINAL NOTES","MODE: FULL / FOLLOW CHORD")[bankMode],
                        onMode={if(!bankBusy){bankMode=(bankMode+1)%3;preferences.edit().putInt("bankMode",bankMode).apply();bankStatus=""}},
                        onChoose={chosen->
                            if(!bankBusy && !restoringSet){
                                if(bankIndex==null){
                                    engine.setRhythmEnabled(false);engine.importedStyleSession=null;engine.importedRhythm=null;engine.importedRhythmName=null
                                    preferences.edit().remove("selectedStyleSet").remove("selectedSetStyleId").apply();style=chosen;engine.rhythmStyle=chosen;bpm=engine.styleTempos[chosen];engine.bpm=bpm;browseStyles=false
                                }else bankScope.launch{
                                    bankBusy=true;bankStatus="LOADING..."
                                    try{
                                        val loaded=withContext(Dispatchers.IO){ImportedStylePlayer.loadStyle(checkNotNull(engine.activeSetFile),checkNotNull(bankIndex),bankStyles[chosen],bankMode>0,
                                            engine.referenceChordTransposer,if(bankMode==2)engine::selectedSetChord else null)}
                                        engine.selectImportedStyle(loaded)
                                        setSession=loaded;sectionState=loaded.currentId to null
                                        preferences.edit().putString("selectedStyleSet",engine.activeSetFile?.name).putInt("selectedSetStyleId",loaded.style.id).apply()
                                        val tempo=loaded.style.sections.first{it.id==loaded.currentId}.tempo
                                        if(tempo>0){bpm=tempo.coerceIn(40,240);engine.bpm=bpm}
                                        browseStyles=false;bankStatus=""
                                    }catch(error:Exception){bankStatus=error.message ?: "Cannot load style"}
                                    finally{bankBusy=false}
                                }
                            }
                        })
                        DropdownMenu(expanded=showTools,onDismissRequest={showTools=false}){
                            DropdownMenuItem(text={Text("Import / Quản lý SET")},onClick={showTools=false;showSets=true})
                            DropdownMenuItem(text={Text("Files / Bản thu")},onClick={showTools=false;showFiles=true})
                            DropdownMenuItem(text={Text("Project")},onClick={showTools=false;showProjects=true})
                            DropdownMenuItem(text={Text(if(midiPlaying)"Dừng MIDI" else "Mở MIDI")},onClick={showTools=false;if(midiPlaying){engine.stopMidi();midiPlaying=false}else midiPicker.launch(arrayOf("audio/midi","audio/x-midi","application/octet-stream"))})
                            DropdownMenuItem(text={Text("Pad / Hiệu ứng")},onClick={showTools=false;showPads=true})
                            DropdownMenuItem(text={Text(if(recording)"Dừng ghi âm" else "Ghi âm")},onClick={
                                showTools=false
                                if(recording){val file=engine.stopRecording();recording=false;Toast.makeText(context,file?.name ?: "Nothing recorded",Toast.LENGTH_LONG).show()}
                                else {engine.startRecording();recording=true}
                            })
                            DropdownMenuItem(text={Text("Designed by hovait / Cài đặt")},onClick={showTools=false;showAbout=true})
                        }
                }
                PianoKeyboard(octave, engine, Modifier.weight(.45f).fillMaxWidth(),leftChord)
            }
            if (showMixer) MixerDialog(engine, preferences, instrument, style, effect,
                onInstrument = { instrument = it; engine.instrument = it;engine.importedInstrument=null;engine.importedInstrumentName=null;restoredSetName=null
                    preferences.edit().remove("importedSetFile").remove("importedSetSound").apply() },
                onStyle = { engine.importedStyleSession=null;engine.importedRhythm=null;engine.importedRhythmName=null;style = it; engine.rhythmStyle = it;bpm=engine.styleTempos[it];engine.bpm=bpm },
                onEffect = { effect = it; engine.effectType = it },
                onSetStyles={showMixer=false;browseStyles=true;bankPage=0;bankStatus=""},
                onDismiss = { showMixer = false })
            if (showSets) SetMemoryDialog(chordNames, preferences, octave, bpm, instrument, chord, style, effect, engine,
                onLoad = { loadedOctave, loadedBpm, loadedInstrument, loadedChord, loadedStyle, loadedEffect, loadedVolume ->
                    octave = loadedOctave; bpm = loadedBpm; instrument = InstrumentCatalog.validIndex(loadedInstrument)
                    chord = loadedChord.coerceIn(0, chordNames.lastIndex); style = loadedStyle.coerceIn(engine.styleNames.indices); effect=loadedEffect;engine.bpm = bpm
                    engine.instrument = instrument; engine.chordIndex = chord
                    engine.rhythmStyle = style;engine.effectType=effect; engine.masterVolume = loadedVolume
                }, onRhythmSelected={tempo->bpm=tempo;playing=false}, onDismiss = { showSets = false })
            if(showChords) ChordDialog(chordNames,chord,autoChord,progression,leftChord,
                onLeftChord={enabled->leftChord=enabled;engine.setLeftChordEnabled(enabled);preferences.edit().putBoolean("leftChord",enabled).apply();if(enabled){autoChord=false;engine.autoChord=false}},
                onSelect={leftChord=false;engine.setLeftChordEnabled(false);preferences.edit().putBoolean("leftChord",false).apply();chord=it;autoChord=false;engine.autoChord=false;engine.chordIndex=it;showChords=false},
                onAuto={enabled,preset->leftChord=false;engine.setLeftChordEnabled(false);preferences.edit().putBoolean("leftChord",false).apply();autoChord=enabled;progression=preset;engine.autoChord=enabled;engine.progression=preset;showChords=false},
                onDismiss={showChords=false})
            if(showPads) PadDialog(engine,onDismiss={showPads=false})
            if(showFiles) RecordingDialog(engine,onDismiss={showFiles=false})
            if(showProjects) ProjectDialog(ProjectStore(context),ProjectState(octave,bpm,instrument,chord,style,effect,autoChord,progression,engine.masterVolume,engine.keysVolume,engine.chordVolume,engine.drumsVolume,engine.effectMix),onLoad={project->
                octave=project.octave;bpm=project.bpm;instrument=project.instrument;chord=project.chord.coerceIn(0, chordNames.lastIndex);style=project.style.coerceIn(engine.styleNames.indices);effect=project.effect;autoChord=project.autoChord;progression=project.progression
                engine.bpm=bpm;engine.instrument=instrument;engine.chordIndex=chord;engine.rhythmStyle=style;engine.effectType=effect;engine.autoChord=autoChord;engine.progression=progression
                engine.masterVolume=project.master;engine.keysVolume=project.keys;engine.chordVolume=project.chords;engine.drumsVolume=project.drums;engine.effectMix=project.effectMix
            },onDismiss={showProjects=false})
            if (showAbout) AboutDialog(engine, onDismiss = { showAbout = false })
        }
    }
}

@Composable private fun Header(recording:Boolean,onRecord:()->Unit,onFiles:()->Unit,onProjects:()->Unit,midiPlaying:Boolean,onMidi:()->Unit,onPads:()->Unit,onSets:()->Unit,onAbout: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("ORGX", color = Text, fontWeight = FontWeight.Black, fontSize = 25.sp)
        Spacer(Modifier.width(8.dp))
        Spacer(Modifier.weight(1f));TextButton(onClick=onFiles){Text("FILES",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onProjects){Text("PROJECT",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onMidi){Text(if(midiPlaying) "STOP MIDI" else "MIDI",color=if(midiPlaying) Accent else Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onPads){Text("PAD",color=Subtle,fontWeight=FontWeight.Bold)}; TextButton(onClick=onSets){Text("SET",color=Subtle,fontWeight=FontWeight.Bold)};TextButton(onClick=onRecord,colors=ButtonDefaults.textButtonColors(contentColor=if(recording) Color(0xFFEF4444) else Subtle)){Text(if(recording) "STOP REC" else "REC",fontWeight=FontWeight.Bold)};TextButton(onClick = onAbout) { Text("hovait / INFO", color = Subtle) }
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
@Composable private fun Stepper(label:String,value:Int,onMinus:()->Unit,onPlus:()->Unit,modifier:Modifier){Row(modifier.height(48.dp).background(Muted,RoundedCornerShape(9.dp)),verticalAlignment=Alignment.CenterVertically){TextButton(onClick=onMinus,contentPadding=PaddingValues(0.dp),modifier=Modifier.width(44.dp)){Text("-",color=Text,fontSize=18.sp)};Text("$label  $value",modifier=Modifier.weight(1f),color=Text,fontSize=11.sp,fontWeight=FontWeight.Bold);TextButton(onClick=onPlus,contentPadding=PaddingValues(0.dp),modifier=Modifier.width(44.dp)){Text("+",color=Text,fontSize=18.sp)}}}

@Composable private fun PianoKeyboard(octave:Int, engine:AudioEngine, modifier:Modifier,leftChord:Boolean=false) {
    val active = remember { mutableStateMapOf<Int,Int>() }
    Canvas(modifier.background(Color(0xFF080811), RoundedCornerShape(12.dp)).pointerInput(octave,leftChord) {
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
                            engine.keyboardNoteOn(note,velocity,(octave+2)*12)
                        } else if (change.changedToUpIgnoreConsumed()) {
                            pointerNotes.remove(id)?.let { note ->
                                val count = (active[note] ?: 1) - 1
                                if (count <= 0) { active.remove(note); engine.keyboardNoteOff(note) } else active[note] = count
                            }
                        } else if (change.pressed) {
                            val previous = pointerNotes[id]
                            val current = noteAt(change.position)
                            if (previous != null && previous != current) {
                                val count = (active[previous] ?: 1) - 1
                                if (count <= 0) { active.remove(previous); engine.keyboardNoteOff(previous) } else active[previous] = count
                                pointerNotes[id] = current
                                active[current] = (active[current] ?: 0) + 1
                                val velocity = (0.25f + 0.75f * (change.position.y / size.height)).coerceIn(0.25f, 1f)
                                engine.keyboardNoteOn(current,velocity,(octave+2)*12)
                            }
                        }
                        change.consume()
                    }
            } while (event.changes.any { it.pressed })
            pointerNotes.values.forEach { engine.keyboardNoteOff(it); active.remove(it) }
        }
    }) {
        val whites=14; val w=size.width/whites
        val scale=intArrayOf(0,2,4,5,7,9,11)
        for(i in 0 until whites){
            val x=i*w;val note=(octave+1)*12+(i/7)*12+scale[i%7]
            val shades=if(active.containsKey(note))listOf(Color(0xFF899C8B),Color(0xFFD2DFD0))else listOf(Color(0xFF858786),Color(0xFFF6F5F0),Color(0xFFD1D2CF))
            drawRoundRect(androidx.compose.ui.graphics.Brush.horizontalGradient(shades,x,x+w),Offset(x+2,2f),androidx.compose.ui.geometry.Size(w-4,size.height-4),cornerRadius=androidx.compose.ui.geometry.CornerRadius(4f))
            drawLine(Color(0xFF666865),Offset(x+4,size.height-5),Offset(x+w-4,size.height-5),4f)
        }
        val blackAfter=setOf(0,1,3,4,5)
        for(i in 0 until whites-1) if(i%7 in blackAfter){
            val x=(i+1)*w-w*.29f;val note=(octave+1)*12+(i/7)*12+scale[i%7]+1
            val colors=if(active.containsKey(note))listOf(Color(0xFF333E34),Color(0xFF141C15))else listOf(Color(0xFF080909),Color(0xFF404343),Color(0xFF080909))
            drawRoundRect(androidx.compose.ui.graphics.Brush.horizontalGradient(colors,x,x+w*.58f),Offset(x,0f),androidx.compose.ui.geometry.Size(w*.58f,size.height*.62f),cornerRadius=androidx.compose.ui.geometry.CornerRadius(3f))
            drawLine(Color(0xFF555858),Offset(x+4,size.height*.60f),Offset(x+w*.58f-4,size.height*.60f),4f)
        }
        if(active.isNotEmpty()) drawCircle(Accent,12f,Offset(size.width-24f,24f))
        if(leftChord)drawLine(Accent,Offset(size.width/2,0f),Offset(size.width/2,size.height),4f)
    }
}

@Composable private fun MixerDialog(engine:AudioEngine,preferences:SharedPreferences,instrument:Int,style:Int,effect:Int,onInstrument:(Int)->Unit,onStyle:(Int)->Unit,onEffect:(Int)->Unit,onSetStyles:()->Unit,onDismiss:()->Unit){
    var master by remember{mutableFloatStateOf(engine.masterVolume)};var keys by remember{mutableFloatStateOf(engine.keysVolume)}
    var chords by remember{mutableFloatStateOf(engine.chordVolume)};var drums by remember{mutableFloatStateOf(engine.drumsVolume)}
    var fx by remember{mutableFloatStateOf(engine.effectMix)}
    var mixChannel by remember{mutableIntStateOf(0)}
    var showStylePicker by remember{mutableStateOf(false)}
    val scope=rememberCoroutineScope()
    var loadingSound by remember{mutableStateOf(false)}
    var soundName by remember{mutableStateOf(engine.importedInstrumentName)}
    var soundError by remember{mutableStateOf("")}
    val setSounds=engine.activeSetSounds
    if(showStylePicker) {
        DrumStyleDialog(engine,style,onSelect={onStyle(it);showStylePicker=false},onDismiss={showStylePicker=false})
        return
    }
    fun saveMix(){preferences.edit().putFloat("masterVolume",master).putFloat("keysVolume",keys).putFloat("chordVolume",chords).putFloat("drumsVolume",drums).putFloat("effectMix",fx).apply()}
    AlertDialog(onDismissRequest={saveMix();onDismiss()},title={Text("Sound & mixer")},text={Column(Modifier.heightIn(max=360.dp).verticalScroll(rememberScrollState())){
        if(engine.activeSetFile!=null)OutlinedButton(onClick={saveMix();onSetStyles()},modifier=Modifier.fillMaxWidth().heightIn(min=48.dp)){
            Text("SET STYLES / ${engine.importedRhythmName ?: "SELECT STYLE"}")
        }
        Text("VOICE",fontWeight=FontWeight.Bold)
        if(setSounds.isNotEmpty()){
            Text("Imported SET / ${setSounds.size} supported sounds",fontSize=12.sp)
            if(soundError.isNotEmpty())Text(soundError,fontSize=12.sp)
            setSounds.forEach{sound->FilterChip(selected=soundName==sound.name,enabled=!loadingSound,
                onClick={scope.launch{
                    loadingSound=true
                    try{
                        val file=checkNotNull(engine.activeSetFile)
                        val loaded=withContext(Dispatchers.IO){ImportedInstrumentLoader.load(file,sound)}
                        engine.importedInstrument=loaded;engine.importedInstrumentName=sound.name;soundName=sound.name
                        preferences.edit().putString("importedSetFile",file.name).putString("importedSetSound",sound.name).apply()
                    }catch(error:Exception){soundError=error.message ?: "Cannot load sound"}
                    finally{loadingSound=false}
                }},label={Text(sound.name)},modifier=Modifier.fillMaxWidth().heightIn(min=48.dp))}
        } else InstrumentCatalog.shortNames.withIndex().chunked(2).forEach{items->
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){
                items.forEach{item->FilterChip(selected=instrument==item.index,onClick={onInstrument(item.index)},label={Text(item.value)},modifier=Modifier.weight(1f).heightIn(min=48.dp))}
            }
        }
        Text("DRUM STYLE",fontWeight=FontWeight.Bold)
        OutlinedButton(onClick={if(engine.activeSetFile!=null){saveMix();onSetStyles()}else showStylePicker=true},modifier=Modifier.fillMaxWidth().heightIn(min=48.dp)){
            Text(if(engine.activeSetFile!=null)"SET STYLES / ${engine.importedRhythmName ?: "SELECT STYLE"}" else "${engine.styleNames[style]} / ${engine.styleTempos[style]} BPM")
        }
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){Text("FX",Modifier.width(64.dp),fontWeight=FontWeight.Bold);Row(Modifier.weight(1f),horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Dry","Delay","Reverb").forEachIndexed{index,name->FilterChip(selected=effect==index,onClick={onEffect(index)},label={Text(name,maxLines=1)},modifier=Modifier.weight(1f))}}}
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){Text("MIX",Modifier.width(64.dp),fontWeight=FontWeight.Bold);Column(Modifier.weight(1f)){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(3.dp)){listOf("Keys","Chord","Drums").forEachIndexed{index,name->FilterChip(selected=mixChannel==index,onClick={mixChannel=index},label={Text(name,fontSize=8.sp,maxLines=1)},modifier=Modifier.weight(1f))}};Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(3.dp)){listOf("Master","FX").forEachIndexed{offset,name->val index=offset+3;FilterChip(selected=mixChannel==index,onClick={mixChannel=index},label={Text(name,fontSize=8.sp,maxLines=1)},modifier=Modifier.weight(1f))}}}}
        val level=when(mixChannel){0->keys;1->chords;2->drums;3->master;else->fx}
        Text("${listOf("KEYS","CHORD","DRUMS","MASTER","FX")[mixChannel]} ${(level*100).toInt()}%",fontSize=10.sp,fontWeight=FontWeight.Bold)
        Slider(value=level,onValueChange={when(mixChannel){0->{keys=it;engine.keysVolume=it};1->{chords=it;engine.chordVolume=it};2->{drums=it;engine.drumsVolume=it};3->{master=it;engine.masterVolume=it};else->{fx=it;engine.effectMix=it}}})
    }},confirmButton={TextButton(onClick={saveMix();onDismiss()}){Text("DONE")}})
}
@Composable private fun DrumStyleDialog(engine:AudioEngine,selected:Int,onSelect:(Int)->Unit,onDismiss:()->Unit){
    var query by remember{mutableStateOf("")}
    val matches=engine.styleNames.withIndex().filter{it.value.contains(query.trim(),ignoreCase=true)}
    AlertDialog(onDismissRequest=onDismiss,title={Text("Drum styles (${matches.size})")},text={Column{
        OutlinedTextField(value=query,onValueChange={query=it},label={Text("Search style or variation")},singleLine=true,modifier=Modifier.fillMaxWidth())
        Column(Modifier.heightIn(max=260.dp).verticalScroll(rememberScrollState())){
            if(matches.isEmpty())Text("No matching drum styles",Modifier.padding(vertical=12.dp))
            matches.forEach{item->FilterChip(selected=selected==item.index,onClick={onSelect(item.index)},
                label={Text("${item.value} / ${engine.styleTempos[item.index]} BPM")},modifier=Modifier.fillMaxWidth().heightIn(min=48.dp))}
        }
    }},confirmButton={TextButton(onClick=onDismiss){Text("BACK TO MIXER")}})
}
@Composable private fun SetMemoryDialog(chordNames:List<String>,preferences:SharedPreferences,octave:Int,bpm:Int,instrument:Int,chord:Int,style:Int,effect:Int,engine:AudioEngine,onLoad:(Int,Int,Int,Int,Int,Int,Float)->Unit,onRhythmSelected:(Int)->Unit,onDismiss:()->Unit){
    val context=LocalContext.current
    val store=remember{ReferenceSetStore(File(context.filesDir,"ReferenceSets"))}
    val scope=rememberCoroutineScope()
    var imported by remember{mutableStateOf(store.list())}
    var importing by remember{mutableStateOf(false)}
    var importStatus by remember{mutableStateOf("")}
    var setFile by remember{mutableStateOf(engine.activeSetFile)}
    var setInstruments by remember{mutableStateOf(engine.activeSetSounds)}
    var setGroups by remember{mutableStateOf(engine.activeSetIndex?.styles.orEmpty())}
    var setIndex by remember{mutableStateOf(engine.activeSetIndex)}
    var showSetVoices by remember{mutableStateOf(false)}
    var styleQuery by remember{mutableStateOf("")}
    var showBankFiles by remember{mutableStateOf(engine.activeSetFile==null)}
    var previewAccompaniment by remember{mutableStateOf(preferences.getInt("bankMode",2)>0)}
    var followSetChord by remember{mutableStateOf(preferences.getInt("bankMode",2)==2)}
    var showStyleOptions by remember{mutableStateOf(false)}
    fun activate(bank:ImportedSetBank){
        engine.setRhythmEnabled(false);engine.importedStyleSession=null;engine.importedRhythm=null;engine.importedRhythmName=null;onRhythmSelected(engine.bpm)
        setFile=bank.file;setIndex=bank.index;setGroups=bank.index.styles;setInstruments=bank.sounds
        engine.activeSetFile=bank.file;engine.activeSetIndex=bank.index;engine.activeSetSounds=bank.sounds
        preferences.edit().putString("activeSetFile",bank.file.name).apply()
        showSetVoices=false;styleQuery=""
        showBankFiles=false
        importStatus="SET read: ${ImportedStyleCatalog.styles(bank.index).size} styles, ${bank.sounds.size} supported sounds."
    }
    LaunchedEffect(setFile){
        val file=setFile
        if(file!=null && setIndex==null){
            importing=true
            try{activate(withContext(Dispatchers.IO){ImportedSetBank.open(file)})}
            catch(error:Exception){importStatus="Cannot read SET styles: ${error.message}"}
            finally{importing=false}
        }
    }
    val picker=rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()){uri->
        if(uri!=null)scope.launch{
            importing=true
            try {
                val result=withContext(Dispatchers.IO){
                    context.contentResolver.openInputStream(uri)?.use{store.import(it)} ?: error("Cannot open SET")
                }
                imported=store.list()
                activate(withContext(Dispatchers.IO){ImportedSetBank.open(result.first)})
            } catch(error:Exception){importStatus=error.message ?: "SET import failed"}
            finally{importing=false}
        }
    }
    var revision by remember{mutableIntStateOf(0)}
    AlertDialog(onDismissRequest=onDismiss,title={Text("SET memory")},text={Column(Modifier.heightIn(max=360.dp).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(8.dp)){
        TextButton(onClick={showBankFiles=!showBankFiles}){Text(if(showBankFiles)"ẨN FILE SET" else "ĐỔI / NHẬP FILE SET")}
        if(showBankFiles)OutlinedButton(enabled=!importing,onClick={picker.launch(arrayOf("*/*"))},modifier=Modifier.fillMaxWidth()){
            Text(if(importing)"IMPORTING…" else "IMPORT SET / TEMPLATE")
        }
        if(importStatus.isNotEmpty())Text(importStatus,fontSize=12.sp)
        if(showBankFiles)Text("Imported SET files: ${imported.size}. Open a SET to list its styles and voices.",fontSize=12.sp)
        if(showBankFiles)imported.forEach{file->
            TextButton(enabled=!importing,onClick={scope.launch{
                importing=true
                try{
                    activate(withContext(Dispatchers.IO){ImportedSetBank.open(file)})
                }catch(error:Exception){importStatus=error.message ?: "Cannot read SET"}
                finally{importing=false}
            }}){Text("OPEN SET ${file.name.take(12)}")}
        }
        if(setFile!=null)Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){
            FilterChip(selected=!showSetVoices,onClick={showSetVoices=false},label={Text("STYLES (${setIndex?.let{ImportedStyleCatalog.styles(it).size} ?: 0})")})
            FilterChip(selected=showSetVoices,onClick={showSetVoices=true},label={Text("VOICES (${setInstruments.size})")})
        }
        if(showSetVoices)setInstruments.forEach{item->
            OutlinedButton(enabled=!importing,onClick={scope.launch{
                importing=true
                try{
                    val loaded=withContext(Dispatchers.IO){ImportedInstrumentLoader.load(checkNotNull(setFile),item)}
                    engine.importedInstrument=loaded;engine.importedInstrumentName=item.name
                    preferences.edit().putString("importedSetFile",checkNotNull(setFile).name).putString("importedSetSound",item.name).apply()
                    importStatus="Keyboard: ${item.name}. Select a built-in voice in Mixer to switch back."
                }catch(error:Exception){importStatus=error.message ?: "Cannot load instrument"}
                finally{importing=false}
            }},modifier=Modifier.fillMaxWidth()){Text(item.name)}
        }
        if(!showSetVoices && setGroups.isNotEmpty()){
            TextButton(onClick={showStyleOptions=!showStyleOptions}){Text("TÙY CHỌN NHẠC ĐỆM")}
            if(showStyleOptions){
            Row(verticalAlignment=Alignment.CenterVertically){
                Checkbox(checked=previewAccompaniment,enabled=!importing,onCheckedChange={previewAccompaniment=it})
                Text("Preview Drum + accompaniment")
            }
            if(previewAccompaniment){
                Row(verticalAlignment=Alignment.CenterVertically){
                    Checkbox(checked=followSetChord,enabled=!importing && engine.referenceChordTransposer!=null,onCheckedChange={followSetChord=it})
                    Text("Follow selected chord")
                }
            }
            Text(if(previewAccompaniment && followSetChord) "Manual, left-keyboard or AUTO chords affect new notes. AUTO presets use 4 beats per bar. DNC is not applied."
                else if(previewAccompaniment) "Original notes only; chord changes and DNC are not applied." else "Drum tracks only.",fontSize=12.sp)
            }
            OutlinedTextField(value=styleQuery,onValueChange={styleQuery=it},label={Text("Search SET style")},singleLine=true)
            val matches=setIndex?.let{ImportedStyleCatalog.styles(it)}.orEmpty().filter{it.name.contains(styleQuery.trim(),true)}
            if(matches.isEmpty())Text("No matching SET styles")
            matches.forEach{definition->
                val index=setIndex
                val available=definition.sections.filter{ImportedStyleSupport.reason(index,it,previewAccompaniment,previewAccompaniment && followSetChord)==null}
                val reason=if(available.any{it.section in 32..35})null else "No supported Main section"
                val supported=reason==null
                OutlinedButton(enabled=supported && !importing,onClick={scope.launch{
                    importing=true
                    try{
                        val session=withContext(Dispatchers.IO){
                            ImportedStylePlayer.loadStyle(checkNotNull(setFile),checkNotNull(index),definition,previewAccompaniment,
                                engine.referenceChordTransposer,if(previewAccompaniment && followSetChord)engine::selectedSetChord else null)
                        }
                        engine.selectImportedStyle(session)
                        preferences.edit().putString("selectedStyleSet",setFile?.name).putInt("selectedSetStyleId",definition.id)
                            .putInt("bankMode",if(!previewAccompaniment)0 else if(followSetChord)2 else 1).apply()
                        engine.bpm=definition.sections.first{it.id==session.currentId}.tempo.coerceIn(40,240);onRhythmSelected(engine.bpm)
                        onDismiss()
                    }catch(error:Exception){importStatus=error.message ?: "Cannot load SET Drum"}
                    finally{importing=false}
                }},modifier=Modifier.fillMaxWidth()){
                    Column{Text(definition.name);Text("${available.size}/${definition.sections.size} parts supported",fontSize=12.sp);if(reason!=null)Text(reason,fontSize=12.sp)}
                }
            }
        }
        repeat(3){index->
            val slot=index+1;val exists=preferences.getBoolean("set${slot}_saved",false);revision
            Row(Modifier.fillMaxWidth().background(Muted,RoundedCornerShape(8.dp)).padding(horizontal=10.dp),verticalAlignment=Alignment.CenterVertically){
                Text("SET $slot",Modifier.weight(1f),fontWeight=FontWeight.Bold)
                TextButton(enabled=exists,onClick={engine.keysVolume=preferences.getFloat("set${slot}_keys",0.78f);engine.chordVolume=preferences.getFloat("set${slot}_chords",0.22f);engine.drumsVolume=preferences.getFloat("set${slot}_drums",0.55f);engine.effectMix=preferences.getFloat("set${slot}_fx",0.28f);onLoad(preferences.getInt("set${slot}_octave",4),preferences.getInt("set${slot}_bpm",120),preferences.getInt("set${slot}_instrument",0),ChordSelection.resolve(chordNames,preferences.getString("set${slot}_chordName",null),preferences.getInt("set${slot}_chord",0)),StyleSelection.resolve(engine.styleIds,if(preferences.contains("set${slot}_styleId"))preferences.getInt("set${slot}_styleId",0) else null,preferences.getInt("set${slot}_style",0)),preferences.getInt("set${slot}_effect",0),preferences.getFloat("set${slot}_volume",0.65f));onDismiss()}){Text("LOAD")}
                TextButton(onClick={preferences.edit().putBoolean("set${slot}_saved",true).putInt("set${slot}_octave",octave).putInt("set${slot}_bpm",bpm).putInt("set${slot}_instrument",instrument).putString("set${slot}_chordName",chordNames[chord]).putInt("set${slot}_chord",chord).putInt("set${slot}_styleId",engine.styleIds[style]).putInt("set${slot}_style",style).putInt("set${slot}_effect",effect).putFloat("set${slot}_volume",engine.masterVolume).putFloat("set${slot}_keys",engine.keysVolume).putFloat("set${slot}_chords",engine.chordVolume).putFloat("set${slot}_drums",engine.drumsVolume).putFloat("set${slot}_fx",engine.effectMix).apply();revision++}){Text(if(exists) "UPDATE" else "SAVE")}
            }
        }
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun ChordDialog(names:List<String>,selected:Int,autoChord:Boolean,progression:Int,leftChord:Boolean,onLeftChord:(Boolean)->Unit,onSelect:(Int)->Unit,onAuto:(Boolean,Int)->Unit,onDismiss:()->Unit){
    val roots=remember(names){names.map{it.substringBefore(' ')}.distinct()}
    var root by remember { mutableStateOf(names.getOrNull(selected)?.substringBefore(' ') ?: roots.firstOrNull().orEmpty()) }
    val choices=names.withIndex().filter{it.value.substringBefore(' ')==root}
    AlertDialog(onDismissRequest=onDismiss,title={Text("Chord library")},text={Column(modifier=Modifier.heightIn(max=360.dp).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(6.dp)){
        Row(verticalAlignment=Alignment.CenterVertically){Checkbox(checked=leftChord,onCheckedChange=onLeftChord);Text("Left keyboard chords")}
        Text("Left octave: hold 3 or more chord tones. Last match stays after release. Right octave: melody. SET requires Follow selected chord.",fontSize=12.sp)
        Text("AUTO CHORD",fontWeight=FontWeight.Bold)
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){listOf("Pop","Ballad","Dance").forEachIndexed{index,name->FilterChip(selected=autoChord&&progression==index,onClick={onAuto(true,index)},label={Text(name)},modifier=Modifier.weight(1f))}}
        Text("ROOT",fontWeight=FontWeight.Bold)
        roots.chunked(4).forEach{items->
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){
                items.forEach{name->FilterChip(selected=root==name,onClick={root=name},label={Text(name,fontSize=12.sp)},modifier=Modifier.weight(1f).heightIn(min=48.dp))}
                repeat(4-items.size){Spacer(Modifier.weight(1f))}
            }
        }
        Text("CHORD TYPE — $root",fontWeight=FontWeight.Bold)
        choices.chunked(2).forEach{items->
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(6.dp)){
                items.forEach{item->FilterChip(selected=!autoChord&&selected==item.index,onClick={onSelect(item.index)},label={Text(item.value,fontSize=12.sp)},modifier=Modifier.weight(1f).heightIn(min=48.dp))}
                if(items.size==1)Spacer(Modifier.weight(1f))
            }
        }
    }},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})
}
@Composable private fun PadDialog(engine:AudioEngine,onDismiss:()->Unit){
    val names=listOf("KICK","SNARE","HI-HAT","CLAP","LOW TOM","HIGH TOM","CRASH","PERC")
    AlertDialog(onDismissRequest=onDismiss,title={Text("Performance pads")},text={Column(Modifier.heightIn(max=360.dp).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(8.dp)){
        names.chunked(4).forEachIndexed{row,items->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){items.forEachIndexed{column,name->val index=row*4+column;Button(onClick={engine.triggerPad(index)},modifier=Modifier.weight(1f).height(62.dp),contentPadding=PaddingValues(3.dp),shape=RoundedCornerShape(9.dp)){Text(name,fontSize=9.sp,fontWeight=FontWeight.Bold,maxLines=2)}}}}
        Text("SOUND EFFECTS",fontWeight=FontWeight.Bold)
        SoundEffectCatalog.entries.chunked(2).forEach{items->
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){
                items.forEach{effect->Button(onClick={engine.triggerSoundEffect(effect)},modifier=Modifier.weight(1f).heightIn(min=48.dp),contentPadding=PaddingValues(6.dp)){Text(effect.name,fontSize=12.sp)}}
            }
        }
        TextButton(onClick={engine.stopSoundEffects()},modifier=Modifier.fillMaxWidth().heightIn(min=48.dp)){Text("STOP SOUND")}
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
@Composable private fun AboutDialog(engine:AudioEngine,onDismiss:()->Unit){
    val context=LocalContext.current
    val preferences=remember { context.getSharedPreferences("orgx-settings",0) }
    var nativeOutput by remember { mutableStateOf(preferences.getBoolean("nativeOutput",false)) }
    var outputStatus by remember { mutableStateOf(engine.outputStatus) }
    LaunchedEffect(engine) { while(true) { outputStatus=engine.outputStatus;kotlinx.coroutines.delay(300) } }
    AlertDialog(onDismissRequest=onDismiss,title={Text("ORGX")},text={Column{Text("Music Workstation",fontWeight=FontWeight.Bold);Spacer(Modifier.height(8.dp));Text("Designed by hovait");Text("Version 0.1.6",color=Subtle);Text("Audio output: $outputStatus",color=Subtle);
        Row(Modifier.fillMaxWidth().heightIn(min=48.dp),verticalAlignment=Alignment.CenterVertically){
            Text("Experimental NOpenSL",Modifier.weight(1f));Switch(checked=nativeOutput,onCheckedChange={nativeOutput=it;preferences.edit().putBoolean("nativeOutput",it).apply()})
        };Text("Applies after closing and reopening the app. AudioTrack is used if OpenSL initialization fails.",color=Subtle)}},confirmButton={TextButton(onClick=onDismiss){Text("CLOSE")}})}


