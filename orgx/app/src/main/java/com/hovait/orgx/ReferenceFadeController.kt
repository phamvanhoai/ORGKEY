package com.hovait.orgx
class ReferenceFadeController {
    private var gain=1.0;private var target=1.0;private var step=0.0;private var delay=0L
    @Synchronized fun command(increase:Boolean,frames:Long,delayTicks:Long=0){target=if(increase)1.0 else 0.0;step=(target-gain)/frames.coerceAtLeast(1).toDouble();delay=delayTicks.coerceAtLeast(0)}
    @Synchronized fun advance():Double{if(delay>0){delay--;return gain};gain=(gain+step).coerceIn(0.0,1.0);return gain}
}
