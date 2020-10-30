package dev.minecraftbox.loader

import dev.minecraftbox.asm.transformer.interfaces.ITransformer
import dev.minecraftbox.event.EventBus

interface EnvironmentStrategy<T> {
    suspend fun load(): List<T>

    suspend fun loadMod(any: Any) = EventBus.register(any)

    fun collectTransformers(): List<ITransformer>
}