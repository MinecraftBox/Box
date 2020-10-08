package dev.minecraftbox.loader.dev

import dev.minecraftbox.loader.LoadingStrategy
import dev.minecraftbox.utils.data.ModFileData
import dev.minecraftbox.utils.file.convert
import java.io.File

/**
 * Loads the mod that is currently being ran from the development enviorment.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class DevelopmentEnvironmentStrategy(val metadataFile: File) : LoadingStrategy {
    override suspend fun load() {
        val parsedModFile: ModFileData
        try {
            parsedModFile = convert(
                this::class.java
                    .getResourceAsStream("/${metadataFile.path}")
                    .bufferedReader()
                    .readText()
            )
        } catch (ignored: Exception) {
            // Ignore as this doesn't really matter and not continue the load
            return
        }

        loadMod(Class.forName(parsedModFile.mainClass).newInstance())
    }
}