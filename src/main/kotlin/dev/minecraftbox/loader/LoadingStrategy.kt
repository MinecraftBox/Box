package dev.minecraftbox.loader

import dev.minecraftbox.event.EventBus

interface LoadingStrategy<T> {
    suspend fun load() : List<T>

    suspend fun loadMod(any: Any) = EventBus.register(any)
}