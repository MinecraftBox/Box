package dev.box.loader.discovery

import clone.net.minecraft.client.Minecraft
import org.apache.logging.log4j.Logger
import java.io.File

object ModDiscovery {
    var discoveredMods = mutableListOf<File>()

    fun discover(directory: File, minecraft: Minecraft, logger: Logger) {
        if (!directory.exists()) directory.mkdir()
        val filesToLoad = directory.listFiles()?.filter { it.extension == "jar" }?.toMutableList() ?: mutableListOf()
        logger.info("Found ${filesToLoad.size} mod(s) to load from main mod directory")
        val versionFiles = File(directory, minecraft.version).listFiles()?.filter { it.extension == "jar" } ?: mutableListOf()
        logger.info("Found ${versionFiles.size} mod(s) to load from version specific directory (${directory.name}/${minecraft.version}).")
        filesToLoad.addAll(versionFiles)
        logger.info("Setting discoveredMods to discovered mods.")
        discoveredMods = filesToLoad
    }
}