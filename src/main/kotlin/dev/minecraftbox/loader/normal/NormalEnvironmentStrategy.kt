package dev.minecraftbox.loader.normal

import dev.minecraftbox.loader.LoadingStrategy
import dev.minecraftbox.utils.data.ModFileData
import dev.minecraftbox.utils.file.convert
import dev.minecraftbox.utils.file.isJar
import dev.minecraftbox.utils.file.readFile
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.zip.ZipFile

/**
 * Loads the mods from the given mods folder
 *
 * @param modFolder The mods folder that contains all the mod jars
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class NormalEnvironmentStrategy(private val modFolder: File, val metadataFile: File) : LoadingStrategy<ModFileData> {
    override suspend fun load(): List<ModFileData> {
        val md = mutableListOf<ModFileData>()

        modFolder
            .listFiles()
            ?.filter { it.isJar() }
            ?.map { it to convert<ModFileData>(ZipFile(it).readFile(metadataFile)) }
            ?.forEach {
                val childUrlClassLoader =
                    URLClassLoader(arrayOf<URL>(it.first.toURI().toURL()), this::class.java.classLoader)
                try {
                    val clazz = Class.forName(it.second.mainClass, true, childUrlClassLoader)
                    loadMod(clazz.newInstance())
                    md.add(it.second)
                } catch (e: Exception) {
                    // Stop crashing when mod has an error and ignore instead
                    e.printStackTrace()
                }
            }
        return md
    }
}