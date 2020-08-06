plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("dev.icerock.mobile.multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("Plugin") {
        packageName =  Versions.App.namespace
    }
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
    }
}

val mppLibs = listOf(
    Deps.Libs.MultiPlatform.settings,
    Deps.Libs.MultiPlatform.napier,
    Deps.Libs.MultiPlatform.mokoParcelize,
    Deps.Libs.MultiPlatform.mokoResources,
    Deps.Libs.MultiPlatform.mokoMvvm,
    Deps.Libs.MultiPlatform.mokoUnits,
    Deps.Libs.MultiPlatform.kotlinStdLib,
    Deps.Libs.MultiPlatform.coroutines,
    Deps.Libs.MultiPlatform.ktorClient,
    Deps.Libs.MultiPlatform.serialization,
    Deps.Libs.MultiPlatform.sqldelight
)

setupFramework(
    exports = mppLibs
)

dependencies {
    mppLibs.forEach { mppLibrary(it) }
}

multiplatformResources {
    multiplatformResourcesPackage = "${Versions.App.namespace}.library"
}
