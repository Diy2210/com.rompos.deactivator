package com.rompos.deactivator.models

import android.view.View
import android.widget.Switch
import androidx.lifecycle.ViewModel
import com.rompos.deactivator.activities.PluginsActivity
import kotlinx.serialization.Serializable

@Serializable
data class PluginViewModel(
    val title: String,
    val plugin: String,
    val status: Boolean
): ViewModel() {

    fun changeState(view: View) {
        (view.context as PluginsActivity).sendStateRequest(plugin, (view as Switch).isChecked)
    }
}