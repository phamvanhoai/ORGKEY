package com.hovait.orgx

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** An LCD page in the instrument surface: no window, scrim or keyboard interception. */
@Composable fun InstrumentPage(onDismissRequest:()->Unit,title:@Composable ()->Unit,
    text:@Composable ()->Unit,confirmButton:@Composable ()->Unit){
    BackHandler(onBack=onDismissRequest)
    BoxWithConstraints(Modifier.fillMaxSize()){
        Surface(Modifier.offset(x=maxWidth*.232f,y=maxHeight*.025f)
            .width(maxWidth*.532f).height(maxHeight*.355f),
            color=Color(0xFF182B38),contentColor=Color(0xFFE7EDF2)){
            Column{
                Row(Modifier.fillMaxWidth().background(Color(0xFF98521B)).height(28.dp).padding(start=8.dp),
                    verticalAlignment=Alignment.CenterVertically){
                    Box(Modifier.weight(1f)){ProvideTextStyle(MaterialTheme.typography.labelMedium){title()}}
                    TextButton(onClick=onDismissRequest){Text("EXIT",color=Color.White)}
                }
                Box(Modifier.weight(1f).fillMaxWidth().padding(horizontal=8.dp,vertical=4.dp)){
                    ProvideTextStyle(MaterialTheme.typography.bodySmall.copy(fontSize=12.sp)){text()}
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable fun InstrumentMenu(content:@Composable ()->Unit){
    FlowRow(Modifier.fillMaxWidth(),maxItemsInEachRow=2,
        horizontalArrangement=Arrangement.spacedBy(4.dp),verticalArrangement=Arrangement.spacedBy(2.dp)){content()}
}

@Composable fun InstrumentMenuItem(text:@Composable ()->Unit,onClick:()->Unit){
    OutlinedButton(onClick=onClick,modifier=Modifier.fillMaxWidth(.48f).height(34.dp),
        shape=androidx.compose.foundation.shape.RoundedCornerShape(3.dp),contentPadding=PaddingValues(4.dp)){
        ProvideTextStyle(MaterialTheme.typography.labelSmall){text()}
    }
}
