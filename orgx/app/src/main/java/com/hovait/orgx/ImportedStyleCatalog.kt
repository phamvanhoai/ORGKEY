package com.hovait.orgx

data class ImportedStyleDefinition(val id:Int,val name:String,val sections:List<ImportedStyleGroup>)

object ImportedStyleCatalog {
    /** Tracks.Group(id) resolves positive jumps across the entire SET, not just this style. */
    fun jumpTargets(index:ImportedSetIndex,roots:List<ImportedStyleGroup>):List<ImportedStyleGroup>{
        val byId=index.styles.associateBy{it.id}
        val seen=roots.mapTo(HashSet()){it.id}
        val queue=ArrayDeque(roots)
        val linked=ArrayList<ImportedStyleGroup>()
        while(queue.isNotEmpty()){
            val group=queue.removeFirst()
            if(group.loop || group.jumpGroup<=0 || !seen.add(group.jumpGroup))continue
            val next=byId[group.jumpGroup] ?: continue
            linked.add(next);queue.addLast(next)
        }
        return linked
    }
    /** MainActivity.y1 resolves each PAD in the current style before parent zero. */
    fun pads(index:ImportedSetIndex?,parent:Int?):List<ImportedStyleGroup?> = List(4){slot->
        index?.styles?.firstOrNull{parent!=null && it.parent==parent && it.section==96+slot}
            ?: index?.styles?.firstOrNull{it.parent==0 && it.section==96+slot}
    }
    fun isSection(section:Int)=section in 16..18 || section in 32..35 || section in 48..51 || section==64 || section in 80..82
    fun styles(index:ImportedSetIndex):List<ImportedStyleDefinition> {
        val names=index.styles.associateBy{it.id}
        return index.styles.filter{isSection(it.section)}.groupBy{it.parent}
            .map{(parent,sections)->
            ImportedSectionFallback.complete(index,ImportedStyleDefinition(parent,names[parent]?.name?.takeIf{it.isNotBlank()} ?: "Style $parent",sections.sortedBy{it.section}))
        }.filter{style->style.sections.any{it.section in 32..35}}
    }
    fun label(section:Int)=when(section){
        in 16..18->"INTRO ${section-15}"
        in 32..35->"MAIN ${section-31}"
        in 48..51->"FILL ${section-47}"
        64->"BREAK"
        in 80..82->"ENDING ${section-79}"
        else->"PART $section"
    }
}
