package dev.minecraftbox.loader.normal

import dev.minecraftbox.asm.transformer.interfaces.ITransformer
import dev.minecraftbox.loader.EnvironmentStrategy
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

class NormalEnvironmentStrategy(private val modFolder: File, val metadataFile: File) :
    EnvironmentStrategy<ModFileData> {

    var mods: List<Pair<File, ModFileData>>? = null

    override suspend fun load(): List<ModFileData> {
        val md = mutableListOf<ModFileData>()

        mods?.forEach {
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

    override fun collectTransformers(): List<ITransformer> {
        val transformers = mutableListOf<ITransformer>()

        mods = modFolder
            .listFiles()
            ?.filter { it.isJar() }
            ?.map { it to convert<ModFileData>(ZipFile(it).readFile(metadataFile)) }

        mods?.forEach {
            val fileTransformers = it.second.transformers

            fileTransformers?.forEach { transformer ->
                transformers.add(Class.forName(transformer) as ITransformer)
            }
        }

        return transformers
    }
}