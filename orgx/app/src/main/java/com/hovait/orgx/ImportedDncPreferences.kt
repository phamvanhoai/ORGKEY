package com.hovait.orgx

import android.content.Context
import java.io.File
import java.security.MessageDigest

/** Local overrides belong to a bank revision and mode; imported SET bytes stay untouched. */
class ImportedDncPreferences(context:Context) {
    private val preferences=context.getSharedPreferences("orgx-dnc",Context.MODE_PRIVATE)
    fun read(file:File,instrument:ImportedInstrument):Map<String,String> {
        val key=key(file,instrument) ?: return emptyMap()
        val suffix=" / MODE ${instrument.modes.first().name}"
        val alias=if(instrument.name.endsWith(suffix))instrument else instrument.copy(name=instrument.name+suffix)
        val prefixes=listOfNotNull(key(file,alias,legacy=true),key(file,instrument,legacy=true),key).distinct().map{"$it:"}
        val saved=preferences.all
        return prefixes.flatMap{prefix->saved.entries.filter{it.key.startsWith(prefix)}.mapNotNull{entry->
            (entry.value as? String)?.let{entry.key.removePrefix(prefix) to it}
        }}.toMap()
    }
    fun save(key:String?,name:String,value:Int){
        if(key!=null)preferences.edit().putString("$key:${name.lowercase()}",value.toString()).apply()
    }
    companion object {
        fun key(file:File,instrument:ImportedInstrument,legacy:Boolean=false):String? {
            val mode=instrument.modes.firstOrNull() ?: return null
            if(mode.script.isBlank())return null
            val identity=buildString {
                append(file.canonicalPath);append('\u0000');append(file.length());append(':');append(file.lastModified())
                append('\u0000');append(if(legacy)instrument.name else instrument.sourceIdentity ?: instrument.name);append('\u0000');append(mode.name)
                append(':');append(mode.sound);append(':');append(mode.mix)
                instrument.sounds.forEach{sound->sound.sections.forEach{append(':');append(it.wave.offset)}}
                append('\u0000');append(mode.script)
            }
            return MessageDigest.getInstance("SHA-256").digest(identity.toByteArray(Charsets.UTF_8)).joinToString(""){"%02x".format(it.toInt() and 255)}
        }
    }
}
