package com.rompos.deactivator

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDB() : Server {
    val driver = NativeSqliteDriver(Server.Schema, "Plugin.db")
    return Server(driver)
}