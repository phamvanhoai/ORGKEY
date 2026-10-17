package com.hovait.orgx

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Fixed slots keep performance controls in the same position for every imported style. */
@Composable fun StyleSectionControls(session:ImportedStyleSession,current:Int,pending:Int?,onSelect:(Int)->Unit){
    val rows=listOf(listOf(16,17,18,32,33,34,35),listOf(48,49,50,51,64,80,81,82))
    val parts=session.style.sections.associateBy{it.section}
    Column(verticalArrangement=Arrangement.spacedBy(6.dp)){
        rows.forEach{sections->
            Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){
                sections.forEach{section->
                    val part=parts[section]
                    val active=part!=null && part.id==current
                    val queued=part!=null && part.id==pending
                    val color=when(section){in 16..18->Color(0xFF2563EB);in 32..35->Color(0xFF15803D);in 48..51->Color(0xFF9333EA);64->Color(0xFFB45309);else->Color(0xFFBE123C)}
                    Button(onClick={part?.let{onSelect(it.id)}},enabled=part!=null && session.available(part.id),
                        modifier=Modifier.weight(1f).height(48.dp),contentPadding=PaddingValues(horizontal=2.dp),
                        colors=ButtonDefaults.buttonColors(containerColor=if(active||queued)color else Color(0xFF24283B),contentColor=Color.White),
                        shape=androidx.compose.foundation.shape.RoundedCornerShape(7.dp)){
                        Column(horizontalAlignment=androidx.compose.ui.Alignment.CenterHorizontally){
                            Text(ImportedStyleCatalog.label(section),fontSize=11.sp,fontWeight=FontWeight.Bold,maxLines=1)
                            if(queued)Text("CHỜ",fontSize=9.sp)else if(active)Text("ĐANG CHỌN",fontSize=9.sp)
                        }
                    }
                }
            }
        }
    }
}
