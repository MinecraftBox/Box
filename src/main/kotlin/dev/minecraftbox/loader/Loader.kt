package dev.minecraftbox.loader

interface Loader<T> {
    val mods : MutableList<T>

    val strategies : List<LoadingStrategy<T>>

    suspend fun loadMods()
}