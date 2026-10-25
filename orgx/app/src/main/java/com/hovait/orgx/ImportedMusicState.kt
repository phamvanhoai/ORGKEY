package com.hovait.orgx

/** Music FX state shared by keyboard, rhythm groups and PADs. */
class ImportedMusicState(initial:ImportedChannelSettings=ImportedChannelSettings()) {
    private var source=initial
    @Volatile var settings=initial;private set
    fun configure(value:ImportedChannelSettings){
        if(source===value)return
        source=value;settings=value
    }
    fun keyMove(value:Int){settings=settings.copy(transpose=value)}
    fun octaveMove(value:Int){settings=settings.copy(octave=value)}
}
