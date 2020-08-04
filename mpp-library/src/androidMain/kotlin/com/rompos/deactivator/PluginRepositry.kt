package com.rompos.deactivator

import com.squareup.sqldelight.android.AndroidSqliteDriver
import android.content.Context

lateinit var appContext: Context

actual fun createDB() : Plugin {
    val driver = AndroidSqliteDriver(Plugin.Schema, appContext, "Plugin.db")
    return Plugin(driver)
}