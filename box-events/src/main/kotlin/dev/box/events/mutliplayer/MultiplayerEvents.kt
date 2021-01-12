package dev.box.events.mutliplayer

import dev.box.eventbus.Event

data class ServerJoinEvent(val ip: String, val port: Int) { companion object EVENT : Event<ServerJoinEvent>() }