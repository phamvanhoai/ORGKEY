package com.hovait.orgx

data class SoundEffect(val note: Int, val name: String, val resource: Int) {
    val key get() = "sound-$note"
}

object SoundEffectCatalog {
    val entries = listOf(
        SoundEffect(24,"Brrr",R.raw.orgx_sound_24),
        SoundEffect(25,"Eehh",R.raw.orgx_sound_25),
        SoundEffect(26,"Heyy",R.raw.orgx_sound_26),
        SoundEffect(27,"Scream",R.raw.orgx_sound_27),
        SoundEffect(28,"Kel",R.raw.orgx_sound_28),
        SoundEffect(29,"Sea",R.raw.orgx_sound_29),
        SoundEffect(30,"Dance",R.raw.orgx_sound_30),
        SoundEffect(31,"Haa",R.raw.orgx_sound_31),
        SoundEffect(32,"Gangnam",R.raw.orgx_sound_32),
        SoundEffect(33,"Joonom",R.raw.orgx_sound_33),
        SoundEffect(34,"Yashasa",R.raw.orgx_sound_34),
        SoundEffect(35,"Yaainn",R.raw.orgx_sound_35)
    )
}
