package com.hovait.orgx

import java.io.File

data class ImportedSetBank(val file:File,val index:ImportedSetIndex,val sounds:List<ImportedInstrument>) {
    companion object {
        fun open(file:File):ImportedSetBank {
            val index=ImportedSetInstruments.index(file)
            return ImportedSetBank(file,index,ImportedInstrumentLoader.playableSounds(index.instruments))
        }
        fun restore(directory:File,name:String):ImportedSetBank {
            require(Regex("[0-9a-f]{64}\\.orgset").matches(name)){"Invalid saved SET reference"}
            val file=File(directory,name)
            require(file.isFile){"Saved SET file is missing"}
            return open(file)
        }
    }
}
