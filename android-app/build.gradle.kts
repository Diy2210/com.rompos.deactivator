plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dev.icerock.mobile.multiplatform-units")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(Versions.Android.compileSdk)

    dataBinding {
        isEnabled = true
    }

    dexOptions {
        javaMaxHeapSize = "2g"
    }

    defaultConfig {
        minSdkVersion(Versions.Android.minSdk)
        targetSdkVersion(Versions.Android.targetSdk)
        applicationId = Versions.App.namespace
        versionCode = Versions.App.version
        versionName = Versions.App.versionCode
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

val depLibs = listOf(
        Deps.Libs.Android.kotlinStdLib.name,
        Deps.Libs.Android.appCompat.name,
        Deps.Libs.Android.material.name,
        Deps.Libs.Android.recyclerView.name,
        Deps.Libs.Android.constraintLayout.name,
        Deps.Libs.Android.swiperefreshlayout.name,
        Deps.Libs.Android.coroutines.name,
        Deps.Libs.Android.core.name,
        Deps.Libs.Android.lifecycleExtension.name,
        Deps.Libs.Android.lifecycle.name,
        Deps.Libs.Android.serialization.name,
        Deps.Libs.MultiPlatform.ktorClient.android !!,
        Deps.Libs.MultiPlatform.napier.android !!,
        project(":mpp-library")
)

dependencies {
    depLibs.forEach { implementation(it) }

    // fix of package javax.annotation does not exist import javax.annotation.Generated in DataBinding code
    compileOnly("javax.annotation:jsr250-api:1.0")
}

multiplatformUnits {
    classesPackage = Versions.App.namespace
    dataBindingPackage = Versions.App.namespace
    layoutsSourceSet = "main"
}
