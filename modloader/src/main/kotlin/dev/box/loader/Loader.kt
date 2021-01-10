package dev.box.loader

import dev.box.loader.strategy.dev.DevStrategy
import dev.box.loader.strategy.prod.ProdStrategy
import java.io.File

object Loader {
    private val strategies = mutableListOf(DevStrategy, ProdStrategy)

    @Suppress("UNCHECKED_CAST")
    fun loadMods(mods: List<File>) {
        strategies.forEach { it.load(mods) }
    }
}