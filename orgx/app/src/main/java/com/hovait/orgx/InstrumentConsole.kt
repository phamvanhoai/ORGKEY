package com.hovait.orgx

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput

/** Hardware panel proportions taken from the installed reference main screen. */
@Composable fun InstrumentConsole(modifier:Modifier,style:String,voice:String,chord:String,bpm:Int,octave:Int,
    playing:Boolean,sustain:Boolean,autoFill:Boolean,session:ImportedStyleSession?,current:Int,pending:Int?,
    onMenu:()->Unit,onStyle:()->Unit,onVoice:()->Unit,onChord:()->Unit,onPlay:()->Unit,
    onTempo:(Int)->Unit,onOctave:(Int)->Unit,onSustain:()->Unit,onAutoFill:()->Unit,onPart:(Int)->Unit,
    stylePage:Boolean=false,styleChoices:List<String> = emptyList(),styleEnabled:List<Boolean> = emptyList(),
    page:Int=0,status:String="",onChoose:(Int)->Unit={},onPage:(Int)->Unit={},onExit:()->Unit={},
    modeLabel:String="",onMode:()->Unit={},selectionTitle:String="STYLE SELECT",selectedChoice:Int=-1,onMixer:()->Unit=onVoice,temporaryLoop:Boolean=false,
    syncStart:Boolean=false,syncStop:Boolean=false,onSyncStart:()->Unit={},onSyncStop:()->Unit={},
    chordMuted:Boolean=false,drumMuted:Boolean=false,onChordMute:()->Unit={},onDrumMute:()->Unit={},padsActive:Boolean=false,
    padEnabled:List<Boolean> = emptyList(),padRunning:Int=0,padWaiting:Int=0,onPad:(Int)->Unit={},
    stsSlots:List<Int?> = emptyList(),selectedSts:Int=-1,loadingSts:Int=-1,onSts:(Int)->Unit={},
    stsPage:Int=0,stsPages:Int=1,onStsPage:(Int)->Unit={}){
    data class Key(val x:Float,val y:Float,val w:Float,val h:Float,val label:String,val active:Boolean=false,val enabled:Boolean=true,val action:()->Unit)
    val keys=mutableListOf<Key>()
    keys+=Key(8f,12f,110f,26f,"ORGX / hovait",action=onMenu)
    keys+=Key(145f,12f,70f,26f,"MENU",action=onMenu)
    keys+=Key(145f,48f,70f,26f,"SET",action=onStyle)
    keys+=Key(145f,84f,70f,26f,"MIXER",action=onMixer)
    keys+=Key(8f,155f,75f,26f,"AUTO FILL",autoFill,action=onAutoFill)
    keys+=Key(90f,155f,55f,26f,if(playing || padsActive)"STOP" else "START",playing || padsActive,action=onPlay)
    keys+=Key(790f,12f,88f,26f,"SUSTAIN",sustain,action=onSustain)
    keys+=Key(888f,12f,102f,26f,"CHORD",action=onChord)
    keys+=Key(790f,155f,88f,26f,"SYNC START",syncStart,action=onSyncStart)
    keys+=Key(888f,155f,102f,26f,"SYNC STOP",syncStop,action=onSyncStop)
    keys+=Key(790f,48f,88f,26f,"CHORD MUTE",chordMuted,enabled=session!=null,action=onChordMute)
    keys+=Key(888f,48f,102f,26f,"DRUM MUTE",drumMuted,enabled=session!=null,action=onDrumMute)
    keys+=Key(790f,110f,43f,26f,"−",action={onTempo(-1)})
    keys+=Key(837f,110f,43f,26f,"+",action={onTempo(1)})
    keys+=Key(888f,110f,49f,26f,"OCT −",action={onOctave(-1)})
    keys+=Key(941f,110f,49f,26f,"OCT +",action={onOctave(1)})
    if(!stylePage)repeat(4){slot->
        val bit=1 shl slot
        keys+=Key(490f+slot*68f,114f,63f,27f,"PAD ${slot+1}${if(padWaiting and bit!=0)"..." else ""}",
            active=(padRunning or padWaiting) and bit!=0,enabled=padEnabled.getOrElse(slot){false} || (padRunning or padWaiting) and bit!=0,
            action={onPad(slot)})
    }
    if(!stylePage && stsSlots.isNotEmpty())repeat(4){position->
        val slot=stsSlots.getOrNull(position)
        val number=if(stsPage==0)position+1 else slot ?: (stsPage*4+position+1)
        keys+=Key(490f+position*68f,147f,63f,25f,"STS $number${if(slot!=null && loadingSts==slot)"..." else ""}",
            active=slot!=null && selectedSts==slot,enabled=slot!=null,action={slot?.let(onSts)})
    }
    if(!stylePage && stsPages>1){
        keys+=Key(235f,143f,42f,20f,"STS <",enabled=stsPage>0,action={onStsPage(-1)})
        keys+=Key(281f,143f,42f,20f,"STS >",enabled=stsPage+1<stsPages,action={onStsPage(1)})
    }
    if(stylePage){
        keys+=Key(680f,16f,80f,18f,"EXIT",action=onExit)
        repeat(12){slot->
            val index=page*12+slot
            if(index<styleChoices.size)keys+=Key(235f+(slot%3)*176,39f+(slot/3)*27,168f,23f,
                styleChoices[index].take(32),active=index==selectedChoice,enabled=styleEnabled.getOrElse(index){true},action={onChoose(index)})
        }
        keys+=Key(235f,153f,65f,23f,"PREV",enabled=page>0,action={onPage(-1)})
        keys+=Key(695f,153f,65f,23f,"NEXT",enabled=(page+1)*12<styleChoices.size,action={onPage(1)})
        if(modeLabel.isNotEmpty())keys+=Key(465f,153f,220f,23f,modeLabel,action=onMode)
    }
    // APK gốc exposes four keys for each Intro/Variation/Fill/Ending bank.
    val slots=listOf(16,17,18,19,32,33,34,35,48,49,50,51,64,80,81,82,83)
    slots.forEachIndexed{i,section->
        val part=session?.style?.sections?.firstOrNull{it.section==section}
        val label=when(section){in 16..19->"INTRO ${section-15}";in 32..35->"MAIN ${section-31}";in 48..51->"FILL ${section-47}";64->"BREAK";else->"END ${section-79}"}
        keys+=Key(8f+(i%9)*110f,202f+(i/9)*25f,104f,22f,label,part!=null && part.id==current,
            part!=null && session.available(part.id),action={part?.let{onPart(it.id)}})
    }
    val latest=rememberUpdatedState(keys)
    val browsing=rememberUpdatedState(stylePage)
    Canvas(modifier.pointerInput(Unit){detectTapGestures{point->
        val x=point.x/size.width*1000;val y=point.y/size.height*250
        latest.value.lastOrNull{x>=it.x && x<it.x+it.w && y>=it.y && y<it.y+it.h && it.enabled}?.action?.invoke()
            ?: when {browsing.value && x in 230f..765f && y in 14f..180f->Unit;x in 230f..480f && y in 40f..150f->onStyle();x in 485f..760f && y in 40f..150f->onVoice();x in 20f..110f && y in 40f..140f->onVoice();else->Unit}
    }}){
        val sx=size.width/1000;val sy=size.height/250
        fun rect(x:Float,y:Float,w:Float,h:Float,color:Color)=drawRect(color,Offset(x*sx,y*sy),Size(w*sx,h*sy))
        val paint=Paint(Paint.ANTI_ALIAS_FLAG).apply{typeface=Typeface.create("sans-serif-condensed",Typeface.BOLD)}
        fun text(value:String,x:Float,y:Float,font:Float,color:Int,center:Boolean=false){
            paint.textSize=font*minOf(sx,sy);paint.color=color;paint.textAlign=if(center)Paint.Align.CENTER else Paint.Align.LEFT
            drawContext.canvas.nativeCanvas.drawText(value,x*sx,y*sy,paint)
        }
        drawRect(Brush.verticalGradient(listOf(Color(0xFF34373B),Color(0xFF16191C),Color(0xFF303338))))
        for(y in 0..250 step 3)rect(0f,y.toFloat(),1000f,.35f,Color(0xFF3C3F41))
        rect(222f,8f,550f,178f,Color(0xFF070A0D));rect(228f,14f,538f,166f,Color(0xFF182B38))
        rect(230f,16f,534f,18f,Color(0xFF98521B));text("ORGX  •  MAIN PAGE",238f,29f,12f,0xFFFFE1B0.toInt())
        rect(233f,39f,245f,69f,Color(0xFF452C21));rect(484f,39f,275f,69f,Color(0xFF183F59))
        text("STYLE",239f,52f,10f,0xFFE2C35B.toInt());text(style.take(32),239f,72f,15f,0xFFFFFFFF.toInt())
        text("TEMPO $bpm    METER ${session?.section(current)?.let{ReferenceSectionTiming.meter(it)}?.let{"${it.first}/${it.second}"} ?: "4/4"}",239f,96f,12f,0xFFDDDDDD.toInt())
        text("UPPER / VOICE",492f,52f,10f,0xFF8CD5FA.toInt());text(voice.take(30),492f,74f,14f,0xFFFFFFFF.toInt())
        text("OCTAVE $octave",492f,98f,12f,0xFFDDDDDD.toInt())
        text("CHORD   $chord",239f,131f,16f,0xFF8CDEA4.toInt())
        val nextLabel=pending?.let{session?.section(it)}?.let{"NEXT ${ImportedStyleCatalog.label(it.section)}"}
        text(nextLabel ?: if(playing && temporaryLoop)"RUNNING / TEMP LOOP" else if(playing)"RUNNING" else "READY",239f,if(stsPages>1)176f else 164f,12f,0xFFDDDDDD.toInt())
        if(stsSlots.isEmpty())text(pending?.let{p->session?.style?.sections?.firstOrNull{it.id==p}?.let{"NEXT  ${ImportedStyleCatalog.label(it.section)}"}} ?: "SELECT STYLE / VOICE ON DISPLAY",490f,157f,11f,0xFFFFD36A.toInt())
        if(stylePage){
            rect(230f,16f,534f,162f,Color(0xFF152D3D))
            rect(230f,16f,534f,18f,Color(0xFF98521B))
            text(selectionTitle,237f,29f,12f,0xFFFFFFFF.toInt())
            text(if(status.isNotEmpty())status.take(48) else "PAGE ${page+1} / ${((styleChoices.size+11)/12).coerceAtLeast(1)}",310f,169f,10f,0xFFFFD36A.toInt())
            if(styleChoices.isEmpty())text("NO STYLES  /  MENU > IMPORT SET",245f,85f,13f,0xFFDDDDDD.toInt())
        }
        for(x in listOf(62f,835f)){
            drawCircle(Color.Black,40f*minOf(sx,sy),Offset(x*sx,77f*sy))
            drawCircle(Brush.radialGradient(listOf(Color(0xFFB9BCC0),Color(0xFF5E6267),Color(0xFF292C30)),Offset(x*sx,77f*sy),38f*minOf(sx,sy)),38f*minOf(sx,sy),Offset(x*sx,77f*sy))
            drawLine(Color(0xFF25282C),Offset(x*sx,47f*sy),Offset(x*sx,63f*sy),3f*sx)
        }
        text("VOLUME / MIXER",62f,132f,10f,0xFFE2C35B.toInt(),true)
        text("TEMPO",835f,100f,10f,0xFFE2C35B.toInt(),true)
        text("STYLE CONTROL",500f,196f,10f,0xFFE2C35B.toInt(),true)
        latest.value.forEach{k->
            val p=Offset(k.x*sx,k.y*sy);val d=Size(k.w*sx,k.h*sy)
            drawRoundRect(Color(0xFF070809),Offset(p.x-1,p.y-1),Size(d.width+2,d.height+2),androidx.compose.ui.geometry.CornerRadius(5f*sx))
            drawRoundRect(Brush.verticalGradient(listOf(Color(0xFF777B80),Color(0xFF383C40),Color(0xFF54585B)),p.y,p.y+d.height),p,d,androidx.compose.ui.geometry.CornerRadius(4f*sx))
            if(k.y>190){rect(k.x+k.w*.4f,k.y+4,k.w*.2f,3f,if(k.active)Color(0xFFFF4D34)else Color(0xFF16191B))}
            text(k.label,k.x+k.w/2,k.y+k.h*.67f,if(k.y>190)10f else 11f,if(!k.enabled)0xFF7D8082.toInt()else if(k.active)0xFFFFD65B.toInt()else 0xFF101214.toInt(),true)
        }
        rect(0f,247f,1000f,3f,Color(0xFF9F2432))
    }
}
