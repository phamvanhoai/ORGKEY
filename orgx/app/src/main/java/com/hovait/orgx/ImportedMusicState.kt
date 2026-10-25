package com.hovait.orgx

/** Music FX state shared by keyboard, rhythm groups and PADs. */
class ImportedMusicState(initial:ImportedChannelSettings=ImportedChannelSettings()) {
    private var source=initial
    @Volatile var settings=initial;private set
    @Volatile var waveFxEnabled:Boolean?=null;private set
    @Volatile var rhythmFxEnabled:Boolean?=null;private set
    var musicFxRevision=0L;private set
    var waveFxRevision=0L;private set
    var rhythmFxRevision=0L;private set
    private var waveSource:ImportedChannelSettings?=null
    private var rhythmSource:ImportedChannelSettings?=null
    @Volatile var chordMask:Int?=null;private set
    @Volatile var chordOctave:Int=0;private set
    fun chord(mask:Int,octave:Int){chordOctave=octave;chordMask=mask and 4095}
    fun clearChord(){chordMask=null;chordOctave=0}
    fun chordIndex(library:List<PlayableChord>):Int?=chordMask?.let{ReferenceChordMatcher.find(it,library) ?: -1}
    fun configureBuses(wave:ImportedChannelSettings,rhythm:ImportedChannelSettings){
        if(waveSource!==wave){waveSource=wave;waveFxEnabled=null}
        if(rhythmSource!==rhythm){rhythmSource=rhythm;rhythmFxEnabled=null}
    }
    fun fxEvent(type:Int){when(type){
        241,242->{settings=settings.copy(fxEnabled=type==242);musicFxRevision++}
        243,244->{waveFxEnabled=type==244;waveFxRevision++}
        245,246->{rhythmFxEnabled=type==246;rhythmFxRevision++}
        else->error("Not a Music FX bus event: $type")
    }}
    fun configure(value:ImportedChannelSettings){
        if(source===value)return
        source=value;settings=value
    }
    fun keyMove(value:Int){settings=settings.copy(transpose=value)}
    fun octaveMove(value:Int){settings=settings.copy(octave=value)}
}
