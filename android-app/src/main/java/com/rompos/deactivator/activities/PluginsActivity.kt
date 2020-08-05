package com.rompos.deactivator.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.rompos.deactivator.R
import com.rompos.deactivator.adapters.PluginsAdapter
import com.rompos.deactivator.models.PluginsResponseModel
import com.rompos.deactivator.mpp.api.DeactivatorApi
import com.rompos.deactivator.utils.Utils
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json

class PluginsActivity : AppCompatActivity(), LifecycleOwner {

    companion object {
        lateinit var host: String
        lateinit var token: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugins)
//        plugins.visibility = View.INVISIBLE

        val host: String? = intent.getStringExtra("url").toString()
        val token: String? = intent.getStringExtra("token").toString()

        supportActionBar?.title = intent.getStringExtra("title")

//            swipeContainer.setOnRefreshListener {
//            getPlugins()
//            if (swipeContainer.isRefreshing) {
//                swipeContainer.isRefreshing = false
//            }
//        }

        getPlugins()
    }

    @OptIn(UnstableDefault::class)
    private fun generatePage(response: String) {
        val resp = Json.parse(PluginsResponseModel.serializer(), response)
        if (resp.success) {
            plugins.adapter = PluginsAdapter(applicationContext, resp.data)
            plugins.visibility = View.VISIBLE
        } else {
            Utils.snackMsg(mainView, getString(R.string.server_error))
        }
    }

    private fun getPlugins() {
        lifecycleScope.launch {
//            progressBar.visibility = View.VISIBLE
            try {
                DeactivatorApi.getPlugins(host, "/wp-json/deactivator/v1/list", token).also { response ->
//                    progressBar.visibility = View.GONE
                    generatePage(response)
                }
            } catch (e: ClientRequestException) {
                val intent = intent
                intent.putExtra("message", e.response.status.value.toString() + " " + e.response.status.description)
                setResult(MainActivity.CONNECTION_ERROR, intent)
                finish()
            } catch (e: Exception) {
                val intent = intent
                intent.putExtra("message", e.message.toString())
                setResult(MainActivity.CONNECTION_ERROR, intent)
                finish()
            }
        }
    }

    fun sendStateRequest(plugin: String, state: Boolean) {
        lifecycleScope.launch {
//            progressBar.visibility = View.VISIBLE
            try {
                DeactivatorApi.updateStatus(host, "/wp-json/deactivator/v1/update", token, plugin, state).also { _ ->
                    getPlugins()
                }
            } catch (e: Exception) {
                Utils.snackMsg(pluginsView, e.message.toString() )
            }
        }
    }
}
