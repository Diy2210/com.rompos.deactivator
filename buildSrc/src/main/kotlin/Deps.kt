object Deps {
    object Plugins {
        const val kotlinSerialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.Plugins.serialization}"
        const val androidExtensions =
            "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.Plugins.androidExtensions}"
        const val mokoResources =
            "dev.icerock.moko:resources-generator:${Versions.Plugins.mokoResources}"
        const val mokoNetwork =
            "dev.icerock.moko:network-generator:${Versions.Plugins.mokoNetwork}"
        const val mokoUnits =
            "dev.icerock.moko:units-generator:${Versions.Plugins.mokoUnits}"
        const val sqldelight =
            "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
    }

    object Libs {
        object Android {
            val kotlinStdLib = AndroidLibrary(
                name = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Libs.Android.kotlinStdLib}"
            )
            val appCompat = AndroidLibrary(
                name = "androidx.appcompat:appcompat:${Versions.Libs.Android.appCompat}"
            )
            val material = AndroidLibrary(
                name = "com.google.android.material:material:${Versions.Libs.Android.material}"
            )
            val recyclerView = AndroidLibrary(
                name = "androidx.recyclerview:recyclerview:${Versions.Libs.Android.recyclerView}"
            )
            val constraintLayout = AndroidLibrary(
                name = "androidx.constraintlayout:constraintlayout:${Versions.Libs.Android.constraintLayout}"
            )
            val lifecycleExtension = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.Android.lifecycleExtension}"
            )
            val lifecycle = AndroidLibrary(
                name = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Libs.Android.lifecycle}"
            )
            val swiperefreshlayout = AndroidLibrary(
                name = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Libs.Android.swiperefreshlayout}"
            )
            val coroutines = AndroidLibrary(
                name = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.Android.coroutines}"
            )
            val core = AndroidLibrary(
                name = "androidx.core:core-ktx:${Versions.Libs.Android.core}"
            )
            val serialization = AndroidLibrary(
                name = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.Android.serialization}"
            )
        }

        object MultiPlatform {
            val kotlinStdLib = MultiPlatformLibrary(
                android = Android.kotlinStdLib.name,
                common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.Libs.MultiPlatform.kotlinStdLib}"
            )
            val ktorClient = MultiPlatformLibrary(
                android = "io.ktor:ktor-client-android:${Versions.Libs.MultiPlatform.ktorClient}",
                common = "io.ktor:ktor-client-core:${Versions.Libs.MultiPlatform.ktorClient}",
                ios = "io.ktor:ktor-client-ios:${Versions.Libs.MultiPlatform.ktorClient}"
            )
            val ktorClientLogging = MultiPlatformLibrary(
                android = "io.ktor:ktor-client-logging-jvm:${Versions.Libs.MultiPlatform.ktorClientLogging}",
                common = "io.ktor:ktor-client-logging:${Versions.Libs.MultiPlatform.ktorClientLogging}",
                ios = "io.ktor:ktor-client-logging-native:${Versions.Libs.MultiPlatform.ktorClientLogging}"
            )
            val coroutines = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Libs.MultiPlatform.coroutines}",
                common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.Libs.MultiPlatform.coroutines}",
                ios = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Libs.MultiPlatform.coroutines}"
            )
            val serialization = MultiPlatformLibrary(
                android = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.Libs.MultiPlatform.serialization}",
                common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Libs.MultiPlatform.serialization}",
                ios = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.Libs.MultiPlatform.serialization}"
            )
            val mokoParcelize = MultiPlatformLibrary(
                common = "dev.icerock.moko:parcelize:${Versions.Libs.MultiPlatform.mokoParcelize}",
                iosX64 = "dev.icerock.moko:parcelize-iosx64:${Versions.Libs.MultiPlatform.mokoParcelize}",
                iosArm64 = "dev.icerock.moko:parcelize-iosarm64:${Versions.Libs.MultiPlatform.mokoParcelize}"
            )
            val mokoGraphics = MultiPlatformLibrary(
                common = "dev.icerock.moko:graphics:${Versions.Libs.MultiPlatform.mokoGraphics}",
                iosX64 = "dev.icerock.moko:graphics-iosx64:${Versions.Libs.MultiPlatform.mokoGraphics}",
                iosArm64 = "dev.icerock.moko:graphics-iosarm64:${Versions.Libs.MultiPlatform.mokoGraphics}"
            )
            val mokoTime = MultiPlatformLibrary(
                common = "dev.icerock.moko:time:${Versions.Libs.MultiPlatform.mokoTime}",
                iosX64 = "dev.icerock.moko:time-iosx64:${Versions.Libs.MultiPlatform.mokoTime}",
                iosArm64 = "dev.icerock.moko:time-iosarm64:${Versions.Libs.MultiPlatform.mokoTime}"
            )
            val mokoMvvm = MultiPlatformLibrary(
                common = "dev.icerock.moko:mvvm:${Versions.Libs.MultiPlatform.mokoMvvm}",
                iosX64 = "dev.icerock.moko:mvvm-iosx64:${Versions.Libs.MultiPlatform.mokoMvvm}",
                iosArm64 = "dev.icerock.moko:mvvm-iosarm64:${Versions.Libs.MultiPlatform.mokoMvvm}"
            )
            val mokoResources = MultiPlatformLibrary(
                common = "dev.icerock.moko:resources:${Versions.Libs.MultiPlatform.mokoResources}",
                iosX64 = "dev.icerock.moko:resources-iosx64:${Versions.Libs.MultiPlatform.mokoResources}",
                iosArm64 = "dev.icerock.moko:resources-iosarm64:${Versions.Libs.MultiPlatform.mokoResources}"
            )
            val mokoPermissions = MultiPlatformLibrary(
                common = "dev.icerock.moko:permissions:${Versions.Libs.MultiPlatform.mokoPermissions}",
                iosX64 = "dev.icerock.moko:permissions-iosx64:${Versions.Libs.MultiPlatform.mokoPermissions}",
                iosArm64 = "dev.icerock.moko:permissions-iosarm64:${Versions.Libs.MultiPlatform.mokoPermissions}"
            )
            val mokoMedia = MultiPlatformLibrary(
                common = "dev.icerock.moko:media:${Versions.Libs.MultiPlatform.mokoMedia}",
                iosX64 = "dev.icerock.moko:media-iosx64:${Versions.Libs.MultiPlatform.mokoMedia}",
                iosArm64 = "dev.icerock.moko:media-iosarm64:${Versions.Libs.MultiPlatform.mokoMedia}"
            )
            val mokoNetwork = MultiPlatformLibrary(
                common = "dev.icerock.moko:network:${Versions.Libs.MultiPlatform.mokoNetwork}",
                iosX64 = "dev.icerock.moko:network-iosx64:${Versions.Libs.MultiPlatform.mokoNetwork}",
                iosArm64 = "dev.icerock.moko:network-iosarm64:${Versions.Libs.MultiPlatform.mokoNetwork}"
            )
            val mokoUnits = MultiPlatformLibrary(
                common = "dev.icerock.moko:units:${Versions.Libs.MultiPlatform.mokoUnits}",
                iosX64 = "dev.icerock.moko:units-iosx64:${Versions.Libs.MultiPlatform.mokoUnits}",
                iosArm64 = "dev.icerock.moko:units-iosarm64:${Versions.Libs.MultiPlatform.mokoUnits}"
            )
            val mokoFields = MultiPlatformLibrary(
                common = "dev.icerock.moko:fields:${Versions.Libs.MultiPlatform.mokoFields}",
                iosX64 = "dev.icerock.moko:fields-iosx64:${Versions.Libs.MultiPlatform.mokoFields}",
                iosArm64 = "dev.icerock.moko:fields-iosarm64:${Versions.Libs.MultiPlatform.mokoFields}"
            )
            val settings = MultiPlatformLibrary(
                common = "com.russhwolf:multiplatform-settings:${Versions.Libs.MultiPlatform.settings}",
                iosX64 = "com.russhwolf:multiplatform-settings-iosx64:${Versions.Libs.MultiPlatform.settings}",
                iosArm64 = "com.russhwolf:multiplatform-settings-iosarm64:${Versions.Libs.MultiPlatform.settings}"
            )
            val napier = MultiPlatformLibrary(
                android = "com.github.aakira:napier-android:${Versions.Libs.MultiPlatform.napier}",
                common = "com.github.aakira:napier:${Versions.Libs.MultiPlatform.napier}",
                iosX64 = "com.github.aakira:napier-iosX64:${Versions.Libs.MultiPlatform.napier}",
                iosArm64 = "com.github.aakira:napier-iosArm64:${Versions.Libs.MultiPlatform.napier}"
            )
            val sqldelight = MultiPlatformLibrary(
                android = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}",
                ios = "com.squareup.sqldelight:native-driver:${Versions.sqldelight}",
                common = "com.squareup.sqldelight:runtime:${Versions.sqldelight}"
            )
        }
    }

    val plugins: Map<String, String> = mapOf(
        "kotlin-android-extensions" to Plugins.androidExtensions,
        "kotlinx-serialization" to Plugins.kotlinSerialization,
        "dev.icerock.mobile.multiplatform-resources" to Plugins.mokoResources,
        "dev.icerock.mobile.multiplatform-network-generator" to Plugins.mokoNetwork,
        "dev.icerock.mobile.multiplatform-units" to Plugins.mokoUnits,
        "com.squareup.sqldelight" to Plugins.sqldelight
    )
}