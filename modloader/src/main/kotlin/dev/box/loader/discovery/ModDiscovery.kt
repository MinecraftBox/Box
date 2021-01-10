package dev.box.loader.discovery

import clone.net.minecraft.client.Minecraft
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

object ModDiscovery {
    val logger: Logger = LogManager.getLogger(this)

    var discoveredMods = mutableListOf<File>()

    fun discover(directory: File, minecraft: Minecraft) {
        val filesToLoad = directory.listFiles()?.filter { it.extension == "jar" }?.toMutableList() ?: return
        logger.info("Found ${filesToLoad.size} mods(s) to load from main mod directory")
        val versionFiles = File(directory, minecraft.version).listFiles()?.filter { it.extension == "jar" } ?: return
        logger.info("Found ${versionFiles.size} mod(s) to load from version specific directory.")
        filesToLoad.addAll(versionFiles)
        logger.info("Setting discoveredMods to discovered mods.")
        discoveredMods = filesToLoad
    }
}