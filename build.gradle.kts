// Top-level build file where you can add configuration options common to all sub-projects/modules.

extra["kotlin_version"] = "1.3.60-eap-25"
buildscript {
    val kotlin_version = "1.3.60-eap-25"
    repositories {
        google()
        jcenter()
        maven { url = uri( "https://dl.bintray.com/kotlin/kotlin-eap") }
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:4.0.0-beta02")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0")
        classpath ("com.google.gms:google-services:4.2.0")
        classpath ("com.github.dcendents:android-maven-gradle-plugin:1.4.1")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url  = uri( "https://jitpack.io") }
        maven { url  = uri( "https://dl.bintray.com/kotlin/kotlin-eap") }
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
