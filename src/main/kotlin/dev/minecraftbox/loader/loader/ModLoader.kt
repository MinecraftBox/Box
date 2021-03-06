package dev.minecraftbox.loader.loader

import dev.minecraftbox.asm.transformer.interfaces.ITransformer
import dev.minecraftbox.loader.Loader
import dev.minecraftbox.loader.dev.DevelopmentEnvironmentStrategy
import dev.minecraftbox.loader.normal.NormalEnvironmentStrategy
import dev.minecraftbox.utils.data.ModFileData
import net.minecraft.client.Minecraft
import java.io.File

class ModLoader : Loader<ModFileData> {
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

    override fun getTransformers(): List<ITransformer> {
        val transformers = mutableListOf<ITransformer>()
        strategies.forEach {
            transformers.addAll(it.collectTransformers())
        }

        return transformers
    }
}