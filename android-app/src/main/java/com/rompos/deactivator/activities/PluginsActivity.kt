package com.rompos.deactivator.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.rompos.deactivator.R
import com.rompos.deactivator.adapters.PluginsAdapter
//import com.rompos.deactivator.models.PluginsResponseModel
import com.rompos.deactivator.mpp.models.PluginsResponseModel
import com.rompos.deactivator.mpp.api.DeactivatorApi
import com.rompos.deactivator.utils.Utils
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.json.*

//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_plugins.*

class PluginsActivity : AppCompatActivity(), LifecycleOwner {

    companion object {
        lateinit var host: String
        lateinit var token: String
    }

//    private var plugins: RecyclerView = RecyclerView(this)
//    private var pluginView: ConstraintLayout = ConstraintLayout(this)
//    private var swipeContainer: SwipeRefreshLayout = SwipeRefreshLayout(this)
//    private var progressBar: ProgressBar = ProgressBar(this)

    lateinit var plugins: RecyclerView
    lateinit var pluginView: ConstraintLayout
    lateinit var swipeContainer: SwipeRefreshLayout
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugins)
        plugins.visibility = View.INVISIBLE

        plugins = findViewById(R.id.plugins)
        pluginView = findViewById(R.id.plugin_view)
        progressBar = findViewById(R.id.progressBar)

        val host: String? = intent.getStringExtra("url").toString()
        val token: String? = intent.getStringExtra("token").toString()

        supportActionBar?.title = intent.getStringExtra("title")

        swipeContainer.setOnRefreshListener {
            getPlugins()
            if (swipeContainer.isRefreshing) {
                swipeContainer.isRefreshing = false
            }
        }

        getPlugins()
    }

    @OptIn(UnstableDefault::class)
    private fun generatePage(response: String) {
        val resp = Json.parse(PluginsResponseModel.serializer(), response)
        if (resp.success) {
            plugins.adapter = PluginsAdapter(applicationContext, resp.data)
            plugins.visibility = View.VISIBLE
        } else {
            Utils.snackMsg(pluginView, getString(R.string.server_error))
        }
    }

//        val res = JSONObject(response)
//        if (res.get("success") == true) {
//            val data: JSONArray = res.getJSONArray("data")
//            val pluginsList = ArrayList<PluginViewModel>()
//            (0 until data.length()).forEach { i ->
//                val item = data.getJSONObject(i)
//                with(item) {
//                    pluginsList.add(
//                        PluginViewModel(
//                            getString("title"),
//                            getString("plugin"),
//                            getBoolean("status")
//                        )
//                    )
//                }
//            }
//        } else {
//            Utils.snackMsg(pluginView, getString(R.string.server_error))
//        }
//    }

    private fun getPlugins() {
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            try {
                DeactivatorApi.getPlugins(host, "/wp-json/deactivator/v1/list", token).also { response ->
                    progressBar.visibility = View.GONE
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
            progressBar.visibility = View.VISIBLE
            try {
                DeactivatorApi.updateStatus(host, "/wp-json/deactivator/v1/update", token, plugin, state)
                    .also { _ ->
                        getPlugins()
                    }
            } catch (e: Exception) {
                Utils.snackMsg(pluginView, e.message.toString())
            }
        }
    }
}
