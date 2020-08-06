package com.rompos.deactivator.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class PluginsResponseModel(
    val success: Boolean,
    val data: List<PluginViewModel>
)
