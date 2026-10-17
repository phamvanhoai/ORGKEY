package com.hovait.orgx

/** MainActivity.y1 falls back to GroupSection(0, section) only when absent in the style. */
object ImportedSectionFallback {
    fun complete(index:ImportedSetIndex,style:ImportedStyleDefinition):ImportedStyleDefinition {
        val existing=style.sections.map{it.section}.toSet()
        val fallback=index.styles.filter{it.parent==0 && ImportedStyleCatalog.isSection(it.section) && it.section !in existing}
            .distinctBy{it.section}
        return style.copy(sections=(style.sections+fallback).sortedBy{it.section})
    }
}
