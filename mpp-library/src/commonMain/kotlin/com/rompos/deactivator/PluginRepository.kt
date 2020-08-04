package com.rompos.deactivator

expect fun createDB(): Plugin

open class PluginRepository {
    private val database = createDB()
    private val pluginQueries = database.pluginQueries

    // Insert New Plugins
    fun insert(plugin: Plugins) {
        pluginQueries.insertPlugin(plugin.title, plugin.url, plugin.token)
    }

    // Get All Plugins
    fun list(): List<Plugins> {
        return pluginQueries.selectAll().executeAsList()
    }

    // Get Plugin by ID
//    fun get(id: Long): List<Plugins>? {
//        return pluginQueries.selectPlugin(id)
//    }

    // Update Plugin
    fun update(id: Long, title: String, url: String, token: String) {
        pluginQueries.updatePlugin(title, url, token, id)
    }

    // Delete Plugin
    fun delete(id: Long) {
        pluginQueries.deletePlugin(id)
    }
}