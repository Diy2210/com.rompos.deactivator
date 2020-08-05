package com.rompos.deactivator.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rompos.deactivator.PluginRepository
import com.rompos.deactivator.Plugins
import com.rompos.deactivator.R
import com.rompos.deactivator.adapters.ServersAdapter
import com.rompos.deactivator.utils.Utils
import kotlinx.coroutines.launch
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val EDITED = 101
        const val DETAILS = 102
        const val CONNECTION_ERROR = 2
    }

    val pluginRepository: PluginRepository = PluginRepository()
    lateinit var adapter: ServersAdapter
    lateinit var serversList: List<Plugins>
    lateinit var mainView: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainView: ConstraintLayout = findViewById(R.id.mainView)
        val servers: RecyclerView = findViewById(R.id.servers)

        val mFab = findViewById<FloatingActionButton>(R.id.fab)
        mFab.setOnClickListener {
            val intent = Intent(applicationContext, EditActivity::class.java)
            startActivityForResult(intent, EDITED)
        }

        lifecycleScope.launch {
//            showProgress(true)
            serversList = pluginRepository.list()
        }.also {
            adapter = ServersAdapter(
                serversList,

                // Click Server Action
                object : ServersAdapter.ClickCallback {
                    override fun onItemClicked(item: Plugins) {
                        val intent = Intent(applicationContext, PluginsActivity::class.java)
                        with(item) {
                            intent.putExtra("title", title)
                            intent.putExtra("url", url)
                            intent.putExtra("token", token)
                        }
                        startActivityForResult(intent, DETAILS)
                    }
                },

                // Edit Server Action
                object : ServersAdapter.EditClickCallback {
                    override fun onEditItemClicked(item: Plugins) {
                        val intent = Intent(applicationContext, EditActivity::class.java)
                        intent.putExtra("ID", item.id)
                        intent.putExtra("title", item.title)
                        intent.putExtra("url", item.url)
                        intent.putExtra("token", item.token)
                        startActivityForResult(intent, EDITED)
                    }
                },

                // Delete Server Action
                object : ServersAdapter.DeleteClickCallback {
                    override fun onDeleteItemClicked(item: Plugins) {
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle(getString(R.string.confirm_delete, item.title))
                            .setCancelable(true)
                            .setPositiveButton(android.R.string.yes) { _, _ ->
                                try {
                                    pluginRepository.delete(item.id.toLong()).also {
                                        adapter.items = pluginRepository.list()
                                        adapter.notifyDataSetChanged()
                                        Utils.snackMsg(mainView, getString(R.string.deleted))
                                    }
                                } catch (e: Exception) {
                                    Utils.snackMsg(mainView, e.message.toString())
                                }
                            }
                            .setNegativeButton(android.R.string.no) { _, _ ->
                                // nothing to do
                            }
                            .show()
                    }
                }
            )
            servers.adapter = adapter
//            showProgress(false)
        }

//        swipeContainer.setOnRefreshListener {
//            adapter.items = DatabaseHelper.readAllServers()
//            adapter.notifyDataSetChanged()
//            if (swipeContainer.isRefreshing) {
//                swipeContainer.isRefreshing = false
//            }
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDITED && resultCode == Activity.RESULT_OK) {
            adapter.items = pluginRepository.list()
            adapter.notifyDataSetChanged()
        } else if (requestCode == DETAILS && resultCode == CONNECTION_ERROR) {
            val message = data!!.getStringExtra("message")
            Utils.snackMsg(mainView, message!!)
            println(message)
        }
    }
}