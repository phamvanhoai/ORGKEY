package com.hovait.orgx

data class ImportedStyleDefinition(val id:Int,val name:String,val sections:List<ImportedStyleGroup>)

object ImportedStyleCatalog {
    fun isSection(section:Int)=section in 16..18 || section in 32..35 || section in 48..51 || section==64 || section in 80..82
    fun styles(index:ImportedSetIndex):List<ImportedStyleDefinition> {
        val names=index.styles.associateBy{it.id}
        return index.styles.filter{isSection(it.section)}.groupBy{it.parent}
            .filterValues{sections->sections.any{it.section in 32..35}}.map{(parent,sections)->
            ImportedStyleDefinition(parent,names[parent]?.name?.takeIf{it.isNotBlank()} ?: "Style $parent",sections.sortedBy{it.section})
        }
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
