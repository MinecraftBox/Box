package dev.minecraftbox.loader

import dev.minecraftbox.event.EventBus

interface LoadingStrategy {
    suspend fun load()

    suspend fun loadMod(any: Any) = EventBus.register(any)
}