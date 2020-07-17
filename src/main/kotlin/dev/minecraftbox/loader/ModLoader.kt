package dev.minecraftbox.loader

import dev.minecraftbox.loader.dev.DevelopmentEnvironmentStrategy
import dev.minecraftbox.loader.normal.NormalEnvironmentStrategy
import dev.minecraftbox.utils.modLoadingCoroutine
import kotlinx.coroutines.launch
import net.minecraft.client.Minecraft
import java.io.File

object ModLoader {
    private val strategies = listOf(DevelopmentEnvironmentStrategy(), NormalEnvironmentStrategy(File(Minecraft.getMinecraft().mcDataDir, "mods")))

    fun loadMods() {
        modLoadingCoroutine.launch {
            strategies.forEach { it.load() }
        }
    }
}