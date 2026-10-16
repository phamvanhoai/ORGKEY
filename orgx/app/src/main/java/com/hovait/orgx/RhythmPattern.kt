package com.hovait.orgx

/** Existing ORGX eight-step patterns; these are not the reference style tracks. */
object RhythmPattern {
    fun kick(style:Int,step:Int)=when(style){
        1->step==0 || step==3 || step==4 || step==7
        2->step%2==0
        else->step==0 || step==4
    }
    fun snare(step:Int)=step==2 || step==6
    fun gain(style:Int,pad:Int,step:Int):Double=when(pad){
        0->if(kick(style,step))0.70 else 0.0
        1->if(snare(step))if(style==1)0.40 else 0.32 else 0.0
        2->if(style==2 && step%2==1)0.18 else 0.10
        else->0.0
    }
}
