package dev.minecraftbox.loader

import dev.minecraftbox.loader.dev.DevelopmentEnvironmentStrategy
import dev.minecraftbox.loader.normal.NormalEnvironmentStrategy
import dev.minecraftbox.utils.data.ModFileData
import net.minecraft.client.Minecraft
import java.io.File

object ModLoader : Loader<ModFileData> {
    override val mods: MutableList<ModFileData> = mutableListOf()

    override val strategies = listOf(
        DevelopmentEnvironmentStrategy(File("mod.info")),
        NormalEnvironmentStrategy(File(Minecraft.getMinecraft().mcDataDir, "mods"), File("mod.info"))
    )

    override suspend fun loadMods() {
        strategies.forEach {
            mods.addAll(it.load())
        }
    }
}