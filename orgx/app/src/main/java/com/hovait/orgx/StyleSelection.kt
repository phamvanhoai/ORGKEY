package com.hovait.orgx

object StyleSelection {
    fun resolve(ids:List<Int>,savedId:Int?,legacyIndex:Int):Int {
        if(ids.isEmpty())return 0
        if(savedId!=null)return ids.indexOf(savedId).takeIf{it>=0} ?: 0
        return legacyIndex.coerceIn(ids.indices)
    }
}
