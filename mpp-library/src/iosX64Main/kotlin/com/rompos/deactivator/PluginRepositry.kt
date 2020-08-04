package com.rompos.deactivator

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDB() : Plugin {
    val driver = NativeSqliteDriver(Plugin.Schema, "Plugin.db")
    return Plugin(driver)
}