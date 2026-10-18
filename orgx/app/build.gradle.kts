plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.hovait.orgx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hovait.orgx"
        minSdk = 26
        targetSdk = 35
        versionCode = 41
        versionName = "0.1.6"
        testInstrumentationRunner = "com.hovait.orgx.ImportedStsGroupSmoke"
    }

    buildFeatures { compose = true }
    testOptions.unitTests.all {
        it.systemProperty("orgx.chordTable", file("src/main/assets/Chord").absolutePath)
        it.systemProperty("orgx.sampleDirectory", file("src/main/res/raw").absolutePath)
        it.systemProperty("orgx.midiDemo", file("../../analysis/previews/ORGX-midi-integration.mid").absolutePath)
        it.systemProperty("orgx.referenceAssets", file("src/main/assets").absolutePath)
        if(file("../../analysis/local-set-check/sample.Set").exists()) it.systemProperty("orgx.legacySet",file("../../analysis/local-set-check/sample.Set").absolutePath)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
