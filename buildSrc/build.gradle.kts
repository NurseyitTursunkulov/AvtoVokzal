plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    maven(url = "https://jitpack.io")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
}
object Config {
    object BuildPlugins
    object Android
    object Libs
    object TestLibs
}

object Android {
    val buildToolsVersion = "27.0.3"
    val minSdkVersion = 19
    val targetSdkVersion = 27
    val compileSdkVersion = 27
    val applicationId = "com.antonioleiva.bandhookkotlin"
    val versionCode = 1
    val versionName = "0.1"
}

//private const val supportVersion = "27.0.2"
object Libs {
//    val appcompat = "com.android.support:appcompat-v7:$supportVersion"
//    val recyclerview = "com.android.support:recyclerview-v7:$supportVersion"
//    val cardview = "com.android.support:cardview-v7:$supportVersion"
//    val palette = "com.android.support:palette-v7:$supportVersion"
//    val design = "com.android.support:design:$supportVersion"
}