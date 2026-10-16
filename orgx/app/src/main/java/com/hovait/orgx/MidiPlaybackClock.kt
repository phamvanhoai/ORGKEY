package com.hovait.orgx

/** Sequential conversion retains sub-microsecond remainder across note events. */
class MidiPlaybackClock(private val song:MidiSong) {
    init { require(song.division>0 && song.tempoMicros>0) }
    private val tempos=song.tempos.sortedBy{it.tick}
    private var index=0
    private var tick=0L
    private var tempo=if(tempos.isEmpty())song.tempoMicros else 500000
    private var remainder=0L
    fun advance(target:Long):Long {
        require(target>=tick)
        var micros=0L
        fun segment(end:Long){
            val numerator=(end-tick)*tempo+remainder
            micros+=numerator/song.division
            remainder=numerator%song.division
            tick=end
        }
        while(index<tempos.size && tempos[index].tick<=target){
            val change=tempos[index++]
            require(change.tick>=tick && change.microsPerQuarter>0)
            segment(change.tick);tempo=change.microsPerQuarter
        }
        segment(target)
        return micros
    }
}
