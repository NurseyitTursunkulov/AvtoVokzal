plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")
    kotlin("android.extensions")

}
android {
    compileSdkVersion(29)
    buildToolsVersion ="29.0.2"

    defaultConfig {
        applicationId = "com.example.avtovokzal"
        minSdkVersion (16)
        targetSdkVersion (29)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled =  true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dataBinding {
        isEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

// To inline the bytecode built with JVM target 1.8 into
// bytecode that is being built with JVM target 1.6. (e.g. navArgs)


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation(fileTree(Pair("dir", "libs"), Pair("include", listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.60-eap-25")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.navigation:navigation-fragment:2.1.0")
    implementation("androidx.navigation:navigation-ui:2.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.1.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.1.0")
    implementation("com.google.firebase:firebase-auth:19.2.0")
    implementation("com.google.android.gms:play-services-auth:17.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("com.google.android.gms:play-services-maps:17.0.0")
    implementation("com.google.firebase:firebase-database:19.2.0")
    implementation ("com.google.firebase:firebase-functions:19.0.1")
    implementation("com.google.firebase:firebase-firestore:21.3.1")
    implementation("com.google.firebase:firebase-messaging:20.1.0")
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")
    implementation ("com.google.android.gms:play-services-location:17.0.0")
    implementation ("com.google.android.material:material:1.2.0-alpha02")
    implementation (project(":permissionlib"))
    implementation (project(":core"))

    // Koin for Kotlin apps
    implementation ("org.koin:koin-android:2.0.1")
    implementation ("org.koin:koin-android-viewmodel:2.0.1")
    implementation ("com.google.firebase:firebase-database:19.2.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-rc03")
}
