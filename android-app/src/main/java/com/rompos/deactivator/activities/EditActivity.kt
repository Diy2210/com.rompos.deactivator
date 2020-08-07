package com.rompos.deactivator.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.rompos.deactivator.R
import com.rompos.deactivator.ServerRepository
import com.rompos.deactivator.Servers
import com.rompos.deactivator.utils.Utils
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private val pluginRepository: ServerRepository = ServerRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val serverTitle = findViewById<EditText>(R.id.serverTitle)
        val serverUrl = findViewById<EditText>(R.id.serverUrl)
        val serverToken = findViewById<EditText>(R.id.serverToken)

        val saveBtn: Button = findViewById(R.id.saveBtn)
        val cancelBtn: Button = findViewById(R.id.cancelBtn)

        saveBtn.setOnClickListener {
            val title = serverTitle.text.toString()
            val url = serverUrl.text.toString()
            val token = serverToken.text.toString()

            val plugin = Servers(0, title, url, token)

            if (title.isEmpty() || url.isEmpty() || token.isEmpty()) {
                Utils.snackMsg(it, getString(R.string.error_empty_field))
            } else {
                lifecycleScope.launch {
//                    showProgress(true)
                    try {
                        pluginRepository.insert(plugin)
                        println("//////////////" + pluginRepository.list())
                    } catch (e: Exception) {
                        Utils.snackMsg(it, e.message.toString())
                    }
                }.also {
                    setResult(Activity.RESULT_OK, Intent())
                    this.finish()
                }
            }
        }

        cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            this.finish()
        }
    }
}
