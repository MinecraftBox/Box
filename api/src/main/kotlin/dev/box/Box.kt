package dev.box

import clone.net.minecraft.client.Minecraft
import dev.box.loader.Loader
import dev.box.loader.discovery.ModDiscovery
import dev.box.state.State
import dev.box.title.TitleManager
import dev.box.utils.version.VERSION
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File

object Box {
    lateinit var boxDir: File

    var state = State.UNKNOWN

    val logger: Logger = LogManager.getLogger(this::class.java)

    fun start(minecraft: Minecraft) {
        boxDir = File(minecraft.dataDir, "mods")
        logger.info("Marking Box API as started. Loading for Minecraft ${minecraft.version}")
        TitleManager.addTitle("Box $VERSION")
        logger.info("Starting mod discovery")
        ModDiscovery.discover(boxDir, minecraft, logger)
        logger.info("Invoke mod loading")
        Loader.loadMods(ModDiscovery.discoveredMods)
        state = State.LAUNCHING
    }
}