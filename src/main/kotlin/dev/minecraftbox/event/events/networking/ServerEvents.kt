package dev.minecraftbox.event.events.networking

import dev.minecraftbox.event.CancellableEvent

class ServerJoinEvent(val server: String, val port: Int) : CancellableEvent()

class ServerLeaveEvent
