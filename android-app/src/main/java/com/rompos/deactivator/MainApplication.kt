package com.rompos.deactivator

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = this
    }
}
