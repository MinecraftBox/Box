package dev.minecraftbox.loader.dev

import dev.minecraftbox.asm.transformer.interfaces.ITransformer
import dev.minecraftbox.loader.EnvironmentStrategy
import dev.minecraftbox.utils.data.ModFileData
import dev.minecraftbox.utils.file.convert
import java.io.File

/**
 * Loads the mod that is currently being ran from the development enviorment.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class DevelopmentEnvironmentStrategy(val metadataFile: File) : EnvironmentStrategy<ModFileData> {
    var parsedModFile: ModFileData? = null

    override suspend fun load(): List<ModFileData> {
        loadMod(Class.forName(parsedModFile?.mainClass).newInstance())
        return listOf(parsedModFile ?: return emptyList())
    }

    override fun collectTransformers(): List<ITransformer> {
        val transformers = mutableListOf<ITransformer>()
        try {
            parsedModFile = convert(
                this::class.java
                    .getResourceAsStream("/${metadataFile.path}")
                    .bufferedReader()
                    .readText()
            )

            if (parsedModFile?.transformers != null) {
                val fileTransformers = parsedModFile?.transformers ?: return emptyList()
                fileTransformers.forEach {
                    transformers.add(Class.forName(it) as ITransformer)
                }
            }
        } catch (ignored: Exception) {
            // Ignore as this doesn't really matter and not continue the load
            return emptyList()
        }

        return transformers
    }
}