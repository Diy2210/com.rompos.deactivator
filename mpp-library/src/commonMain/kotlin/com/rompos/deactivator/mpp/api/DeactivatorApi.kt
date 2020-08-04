package com.rompos.deactivator.mpp.api

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.http.Parameters

class DeactivatorApi {
    companion object {
        suspend fun getPlugins(host: String, path: String, token: String): String {
            val client = HttpClient()
            return client.get("${host}${path}?token=${token}") {
            }
        }

        suspend fun updateStatus(host: String, path: String, token: String, id: String, state: Boolean) : String  {
            val client = HttpClient()
            var status: String = "deactivate"
            if (state) {
                status = "activate"
            }
            val params = Parameters.build {
                append("token", token)
                append("id", id)
                append("status", status)
            }
            return client.submitForm("${host}${path}", params)
        }
    }
}