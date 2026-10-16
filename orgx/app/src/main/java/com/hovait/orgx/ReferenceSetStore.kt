package com.hovait.orgx

import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile
import java.security.MessageDigest

data class ReferenceSetInfo(val name:String,val version:String,val tempo:Int,val waves:Int,val pcmBytes:Long)

/** Import staging only: never replaces the running sound bank. */
class ReferenceSetStore(private val directory:File) {
    fun list():List<File> = directory.listFiles{f->f.isFile && f.extension=="orgset"}?.sortedBy{it.name} ?: emptyList()
    fun import(input:InputStream):Pair<File,ReferenceSetInfo> {
        check(directory.isDirectory || directory.mkdirs()) { "Cannot create SET directory" }
        val staged=File.createTempFile("import-",".pending",directory)
        try {
            val hash=MessageDigest.getInstance("SHA-256")
            staged.outputStream().use{output->
                val buffer=ByteArray(65536);var total=0L
                while(true){val count=input.read(buffer);if(count<0)break
                    total+=count;require(total<=512L*1024*1024){"SET exceeds 512 MB"}
                    output.write(buffer,0,count);hash.update(buffer,0,count)
                }
            }
            val info=inspect(staged)
            val digest=hash.digest().joinToString(""){"%02x".format(it)}
            val target=File(directory,"$digest.orgset")
            if(!target.exists())check(staged.renameTo(target)){"Cannot save SET"}
            return target to info
        } finally { staged.delete() }
    }
    companion object {
        fun inspect(file:File):ReferenceSetInfo=RandomAccessFile(file,"r").use{reader->
            fun bytes(count:Int):ByteArray{
                require(count>=0 && count.toLong()<=reader.length()-reader.filePointer){"Truncated SET"}
                return ByteArray(count).also{reader.readFully(it)}
            }
            fun int():Int=java.lang.Integer.reverseBytes(reader.readInt())
            fun text():String{val size=int();require(size in 0..1000000);return bytes(size).toString(Charsets.UTF_8)}
            require(bytes(32).toString(Charsets.US_ASCII).substringBefore('\u0000').equals("Music Studio Music",ignoreCase=true)){"Not a supported Music Studio SET/Template"}
            val metadata=text().split('\r');int()
            val version=metadata.getOrNull(1).orEmpty()
            
            repeat(3){text()}
            val passwordBytes=int()
            require(version in listOf("1.2.4","1.2.8","1.4.6")){"Recognized Music Studio SET $version; this format version is not supported yet."}
            require(passwordBytes in 0..1000000){"Invalid SET metadata length"}
            require(passwordBytes==0 || version<"1.3.2"){"Protected metadata layout for SET $version is not supported yet"}
            // Legacy metadata does not encrypt the wave bank; preserve it without displaying its contents.
            bytes(passwordBytes)
            bytes(2);int();val storedTempo=int();val tempo=if(version<"1.3.0")storedTempo*120/100 else storedTempo;int();bytes(116)
            require(tempo>0){"Invalid SET tempo"}
            val slots=int();val count=int()
            require(slots in 0..100000 && count in 0..slots){"Invalid wave count"}
            val indices=HashSet<Int>();var pcm=0L
            repeat(count){
                val index=int();val size=int()
                require(index in 0 until slots && indices.add(index) && size>=0 && size%2==0){"Invalid wave entry"}
                require(size.toLong()<=reader.length()-reader.filePointer){"Truncated wave data"}
                reader.seek(reader.filePointer+size);pcm+=size
            }
            require(reader.filePointer<reader.length()){"Missing SET collections"}
            ReferenceSetInfo(metadata.firstOrNull().orEmpty(),version,tempo,count,pcm)
        }
    }
}
