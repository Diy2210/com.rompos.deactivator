package com.rompos.deactivator.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header

class Utils {
    companion object {
        fun snackMsg(view: View, message: String) {
            Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}