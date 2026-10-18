package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedStsChordSettingsTest {
    @Test fun bothSingleAndDualSnapshotsReplaceTheStoredSingleTable(){
        val old=List(12){145}
        for(mode in 1..2){
            val incoming=MutableList(12){137}
            val applied=ImportedStsChordSettings.singles(mode,incoming,old)
            incoming[0]=0
            assertEquals(List(12){137},applied)
        }
        assertEquals(old,ImportedStsChordSettings.singles(0,emptyList(),old))
    }
    @Test fun invalidDualTableCannotPoisonLaterSingleFingerSelection(){
        val current=List(12){145}
        for(mode in listOf(-1,1,2,3)){
            for(table in listOf(emptyList(),List(12){-1},List(12){4096})){
                try{ImportedStsChordSettings.singles(mode,table,current);fail("Invalid snapshot accepted")}
                catch(expected:IllegalArgumentException){}
                assertEquals(List(12){145},current)
            }
        }
    }
}
