package com.hovait.orgx

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Touch controls use the existing live MIDI expression path, including recording. */
@Composable fun PerformanceWheels(engine:AudioEngine,enabled:Boolean,modifier:Modifier=Modifier){
    var bend by remember{mutableFloatStateOf(0f)}
    var modulation by remember{mutableFloatStateOf(0f)}
    Column(modifier){
        Row(Modifier.weight(1f),horizontalArrangement=Arrangement.spacedBy(4.dp)){
            for(wheel in 0..1)Column(Modifier.weight(1f)){
                Text(if(wheel==0)"BEND" else "MOD",color=Color.White,fontSize=9.sp)
                Canvas(Modifier.fillMaxWidth().weight(1f).pointerInput(enabled){
                    if(!enabled)return@pointerInput
                    try{
                        awaitEachGesture{
                            val down=awaitPointerEvent().changes.firstOrNull{it.pressed} ?: return@awaitEachGesture
                            val origin=down.position.y
                            val initial=if(wheel==0)bend else modulation
                            var pressed=true
                            while(pressed){
                                val event=awaitPointerEvent()
                                val change=event.changes.firstOrNull{it.id==down.id} ?: break
                                pressed=change.pressed
                                if(pressed){
                                    val delta=(origin-change.position.y)/(size.height*.5f).coerceAtLeast(1f)
                                    if(wheel==0){bend=(initial+delta).coerceIn(-1f,1f);engine.midiPitchBend((8192+bend*if(bend<0)8192 else 8191).toInt())}
                                    else{modulation=(initial+delta).coerceIn(0f,1f);engine.midiModulation((modulation*127).toInt())}
                                }
                                change.consume()
                            }
                            if(wheel==0 && !engine.pitchBendLock){bend=0f;engine.midiPitchBend(8192)}
                        }
                    }finally{if(wheel==0){bend=0f;engine.midiPitchBend(8192)}}
                }){
                    drawRect(Color(0xFF080A0C))
                    val value=if(wheel==0)(bend+1)/2 else modulation
                    val y=(1-value)*(size.height-16.dp.toPx())
                    drawLine(Color(0xFF60666B),Offset(size.width/2,0f),Offset(size.width/2,size.height),3.dp.toPx())
                    drawRect(if(enabled)Color(0xFFB9B9B0) else Color.DarkGray,Offset(3.dp.toPx(),y),Size(size.width-6.dp.toPx(),16.dp.toPx()))
                }
            }
        }
        TextButton(enabled=enabled,onClick={bend=0f;modulation=0f;engine.midiPitchBend(8192);engine.midiModulation(0)},contentPadding=PaddingValues(0.dp)){
            Text("RESET",fontSize=9.sp)
        }
    }
    DisposableEffect(engine){onDispose{engine.midiPitchBend(8192);engine.midiModulation(0)}}
}
