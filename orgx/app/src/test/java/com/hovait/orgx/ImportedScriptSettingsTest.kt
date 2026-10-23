package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedScriptSettingsTest {
    @Test fun namedArgumentsKeepDeclarationOrderAndDriveEvents(){
        val session=ImportedScriptSession("""
            main() {
                setting(text, title:"DNC, controls");
                setting(slider, to:100, reset:25, title:"Speed, percent", from:1, name:"speed");
                setting(toggle, reset:true, title:"Enabled", name:"enabled");
            }
            on(touch) { if(enabled) { play(volume:speed); return(true); } }
        """.trimIndent())
        assertEquals(listOf("DNC, controls"),session.settingTitles)
        assertEquals(listOf("speed","enabled"),session.sliders.map{it.name})
        assertEquals("Speed, percent",session.sliders.first().title)
        assertEquals(25,session.event("touch").plays.single().parameters["volume"])
        session.setSlider("speed",70)
        assertEquals(70,session.event("touch").plays.single().parameters["volume"])
        session.setSlider("enabled",0)
        assertFalse(session.event("touch").handled)
    }
    @Test(expected=IllegalArgumentException::class) fun duplicateNamedArgumentIsRejected(){
        ImportedScriptSession("""main(){setting(toggle,name:"enabled",title:"Enabled",reset:true,reset:false);}""")
    }
}
