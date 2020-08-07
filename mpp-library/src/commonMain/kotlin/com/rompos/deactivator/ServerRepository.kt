package com.rompos.deactivator

expect fun createDB(): Server

open class ServerRepository {
    private val database = createDB()
    private val serverQueries = database.serverQueries

    // Insert New Plugins
    fun insert(server: Servers) {
        serverQueries.insertServer(server.title, server.url, server.token)
    }

    // Get All Plugins
    fun list(): List<Servers> {
        return serverQueries.selectAll().executeAsList()
    }

    // Get Plugin by ID
//    fun get(id: Long): List<Plugins>? {
//        return pluginQueries.selectPlugin(id)
//    }

    // Update Plugin
    fun update(id: Long, title: String, url: String, token: String) {
        serverQueries.updateServer(title, url, token, id)
    }

    // Delete Plugin
    fun delete(id: Long) {
        serverQueries.deleteServer(id)
    }
}