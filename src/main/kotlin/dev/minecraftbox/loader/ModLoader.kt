package dev.minecraftbox.loader

import dev.minecraftbox.loader.dev.DevelopmentEnvironmentStrategy
import dev.minecraftbox.loader.normal.NormalEnvironmentStrategy
import net.minecraft.client.Minecraft
import java.io.File

object ModLoader {
    private val strategies = listOf(
        DevelopmentEnvironmentStrategy(File("mod.info")),
        NormalEnvironmentStrategy(File(Minecraft.getMinecraft().mcDataDir, "mods"), File("mod.info"))
    )

    suspend fun loadMods() {
        strategies.forEach { it.load() }
    }
}