package com.hovait.orgx
class ReferenceFadeController {
    private var gain=1.0;private var target=1.0;private var step=0.0
    @Synchronized fun command(increase:Boolean,frames:Long){target=if(increase)1.0 else 0.0;step=(target-gain)/frames.coerceAtLeast(1).toDouble()}
    @Synchronized fun advance():Double{gain=(gain+step).coerceIn(0.0,1.0);return gain}
}
