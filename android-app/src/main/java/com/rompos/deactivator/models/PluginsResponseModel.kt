package com.rompos.deactivator.models

import kotlinx.serialization.Serializable

@Serializable
data class PluginsResponseModel(
    val success: Boolean,
    val data: List<PluginViewModel>
)
