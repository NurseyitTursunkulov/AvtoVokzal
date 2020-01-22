plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")
    kotlin("android.extensions")
    id("kotlin-android-extensions")

}
android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"

    defaultConfig {
        applicationId = "com.example.avtovokzal"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dataBinding {
        isEnabled = true
        isEnabledForTests = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation("com.google.firebase:firebase-functions:19.0.1")
    implementation("com.google.firebase:firebase-firestore:21.3.1")
    implementation("com.google.firebase:firebase-messaging:20.1.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("com.google.android.gms:play-services-location:17.0.0")
    implementation("com.google.android.material:material:1.2.0-alpha02")
    implementation(project(":permissionlib"))
    implementation(project(":core"))

    val compose_version = "0.1.0-dev03"
    implementation("androidx.compose:compose-runtime:$compose_version")
    implementation("androidx.ui:ui-framework:$compose_version")
    implementation("androidx.ui:ui-layout:$compose_version")
    implementation("androidx.ui:ui-material:$compose_version")
    implementation("androidx.ui:ui-foundation:$compose_version")
    implementation("androidx.ui:ui-animation:$compose_version")
    implementation("androidx.ui:ui-tooling:$compose_version")

    // Koin for Kotlin apps
    implementation("org.koin:koin-android:2.0.1")
    implementation("org.koin:koin-android-viewmodel:2.0.1")
    implementation("com.google.firebase:firebase-database:19.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-rc03")

    // Dependencies for local unit tests
    val junitVersion = "4.12"
    val mockitoVersion = "2.8.9"
    val hamcrestVersion = "1.3"
    val archTestingVersion = "2.0.0"
    val coroutinesVersion = "1.1.0"
    val robolectricVersion = "4.3-beta-1"
    val espressoVersion = "3.2.0"
    val truthVersion = "0.44"
    val dexMakerVersion = "2.12.1"
    val androidXTestCoreVersion = "1.3.0-alpha03"
    val androidXTestExtKotlinRunnerVersion = "1.1.1-beta01"
    val androidXTestRulesVersion = "1.2.0-beta01"
    val androidXAnnotations = "1.1.0"
    val androidXLegacySupport = "1.0.0"
    val recyclerViewVersion = "1.0.0"
    val appCompatVersion = "1.0.2"
    val materialVersion = "1.0.0"
    val fragmentVersion = "1.2.0-rc04"
    implementation("androidx.test.espresso:espresso-idling-resource:$espressoVersion")

    // Dependencies for local unit tests
    testImplementation("junit:junit:$junitVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.hamcrest:hamcrest-all:$hamcrestVersion")
    testImplementation("androidx.arch.core:core-testing:$archTestingVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation("org.robolectric:robolectric:$robolectricVersion")
    testImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    testImplementation("androidx.test.espresso:espresso-contrib:$espressoVersion")
    testImplementation("androidx.test.espresso:espresso-intents:$espressoVersion")
    testImplementation("com.google.truth:truth:$truthVersion")

    // Dependencies for Android unit tests
    androidTestImplementation("junit:junit:$junitVersion")
    androidTestImplementation("org.mockito:mockito-core:$mockitoVersion")
    androidTestImplementation("com.linkedin.dexmaker:dexmaker-mockito:$dexMakerVersion")

    // AndroidX Test - JVM testing
    testImplementation("androidx.test:core-ktx:$androidXTestCoreVersion")
    testImplementation("androidx.test.ext:junit-ktx:$androidXTestExtKotlinRunnerVersion")
    testImplementation("androidx.test:rules:$androidXTestRulesVersion")
    implementation ("androidx.fragment:fragment-testing:$fragmentVersion"){
        exclude("androidx.test", "core")
    }
    implementation ("androidx.test:core:$androidXTestCoreVersion")
    implementation ("androidx.fragment:fragment:$fragmentVersion")

    // AndroidX Test - Instrumented testing
    androidTestImplementation("androidx.test:core-ktx:$androidXTestCoreVersion")
    androidTestImplementation("androidx.test.ext:junit-ktx:$androidXTestExtKotlinRunnerVersion")
    androidTestImplementation("androidx.test:rules:$androidXTestRulesVersion")
    androidTestImplementation("androidx.arch.core:core-testing:$archTestingVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:$espressoVersion")
    androidTestImplementation("androidx.test.espresso:espresso-intents:$espressoVersion")
    androidTestImplementation("androidx.test.espresso.idling:idling-concurrent:$espressoVersion")
    androidTestImplementation("org.robolectric:annotations:$robolectricVersion")
    implementation("androidx.test.espresso:espresso-idling-resource:$espressoVersion")
    androidTestImplementation ("com.agoda.kakao:kakao:2.2.0")

    // Resolve conflicts between main and test APK:
    androidTestImplementation("androidx.annotation:annotation:$androidXAnnotations")
    androidTestImplementation("androidx.legacy:legacy-support-v4:$androidXLegacySupport")
    androidTestImplementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")
    androidTestImplementation("androidx.appcompat:appcompat:$appCompatVersion")
    androidTestImplementation("com.google.android.material:material:$materialVersion")

}
