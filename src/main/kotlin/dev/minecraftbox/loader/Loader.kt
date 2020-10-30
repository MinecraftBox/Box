package dev.minecraftbox.loader

import dev.minecraftbox.asm.transformer.interfaces.ITransformer

interface Loader<T> {
    val mods: MutableList<T>

    val strategies: List<EnvironmentStrategy<T>>

    suspend fun loadMods()

    fun getTransformers(): List<ITransformer>
}