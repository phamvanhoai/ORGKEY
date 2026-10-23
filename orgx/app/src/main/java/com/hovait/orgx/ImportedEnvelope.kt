package com.hovait.orgx

/** Channels.Process units: ADS/release-auto use 100 ms per unit; pedal mode 0 release uses 10 ms. */
data class ImportedEnvelope(val attack:Int,val decay:Int,val sustain:Int,val release:Int,val autoRelease:Boolean) {
    fun voice(sampleRate:Int)=State(this,sampleRate)
    class State(private val settings:ImportedEnvelope,sampleRate:Int) {
        private val attackFrames=settings.attack.toLong()*sampleRate/10
        private val decayFrames=settings.decay.toLong()*sampleRate/10
        private val manualReleaseFrames=(if(settings.release==0)0.1 else settings.release.coerceAtMost(20).toDouble())*sampleRate/100
        private val autoReleaseFrames=(if(settings.release==0)0.1 else settings.release.toDouble())*sampleRate/10
        private var age=0L
        private var releaseAge=0L
        private var releaseGain=0.0
        private var releaseFrames=0.0
        private var releasing=false
        private var manualRelease=false
        private var manualFastRelease=false
        var gain=if(attackFrames==0L)1.0 else 0.0;private set
        var finished=false;private set
        /** Connection revives channel gain without replaying the sample attack. */
        fun reconnect(){
            if(settings.autoRelease)return
            releasing=false;manualRelease=false;manualFastRelease=false
            releaseAge=0;releaseGain=0.0;finished=false
            gain=1.0
            age=attackFrames+decayFrames
        }
        fun next(noteOff:Boolean,pedalRelease:Boolean=false):Double {
            if(finished)return 0.0
            if(noteOff && !manualRelease){
                releasing=true;manualRelease=true;releaseAge=0;releaseGain=gain
                releaseFrames=if(pedalRelease)autoReleaseFrames else manualReleaseFrames
                manualFastRelease=!pedalRelease
            }else if(noteOff && manualRelease && !pedalRelease && !manualFastRelease){
                // Native envelope 4 changes to envelope 5 when a dynamic pedal is lifted.
                releaseAge=0;releaseGain=gain;releaseFrames=manualReleaseFrames
                manualFastRelease=true
            }
            if(!releasing && settings.autoRelease && age>=attackFrames+decayFrames){
                releasing=true;releaseGain=gain;releaseFrames=autoReleaseFrames
            }
            gain=if(releasing){
                releaseAge++
                (releaseGain*(1-releaseAge/releaseFrames.coerceAtLeast(1.0))).coerceAtLeast(0.0)
            }else{
                age++
                when {
                    age<=attackFrames->age.toDouble()/attackFrames
                    decayFrames>0 && age<attackFrames+decayFrames->1-(1-settings.sustain/100.0)*(age-attackFrames).toDouble()/decayFrames
                    decayFrames>0->settings.sustain/100.0
                    else->1.0 // Native Decay=0 retains peak, not Sustain.
                }
            }
            if(releasing && gain<=0)finished=true
            return gain
        }
    }
    companion object {
        fun from(values:List<Int>):ImportedEnvelope {
            require(values.size==6 && values.take(4).all{it in 0..127} && values[2]<=100){"Unsupported SET envelope parameters"}
            return ImportedEnvelope(values[0],values[1],values[2],values[3],values[5]!=0)
        }
    }
}
