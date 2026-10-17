package com.hovait.orgx

/** Port of Track.CalcChordKey, including its source pitch-class comparison. */
class ReferenceChordTransposer(private val scales:List<IntArray>) {
    fun note(key:Int,sourceRoot:Int,sourceType:Int,target:Int,control:Int=0,octaveMove:Int=0):Int {
        require(key in 0..127 && sourceRoot in 0..127 && sourceType in scales.indices && target in 0 until scales.size*12)
        var result=target%12+key-sourceRoot%12
        for(degree in 0..11){
            val from=scales[sourceType][degree];val to=scales[target/12][degree]
            if(key%12==from && to!=-1){result-=from-to;break}
        }
        when(control){
            1->if(result-key>8)result-=12
            2->if(octaveMove%2==1 && result+12-key<=16)result+=12
            3->if(octaveMove%2==0 && result+12-key<=16)result+=12
            4->if(result-key>6)result-=12
        }
        while(result<21)result+=12
        while(result>108)result-=12
        return result
    }
    fun supports(sourceType:Int)=sourceType in scales.indices
    companion object {
        fun fromTable(text:String):ReferenceChordTransposer {
            ChordTableParser.parse(text) // Validate using the same table contract as the chord library.
            val rows=text.lineSequence().map{it.trim()}.filter{it.isNotEmpty()}.take(256).map{line->
                val tokens=line.split(Regex("\\s+"))
                IntArray(12){tokens.getOrNull(it+1)?.toInt() ?: -1}
            }.toList()
            return ReferenceChordTransposer(rows)
        }
    }
}
