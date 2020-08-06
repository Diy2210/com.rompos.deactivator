package com.rompos.deactivator.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class PluginViewModel(
    val title: String,
    val plugin: String,
    val status: Boolean
)