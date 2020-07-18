package dev.minecraftbox

import club.ampthedev.mcgradle.Properties
import dev.minecraftbox.event.InvokeEvent
import dev.minecraftbox.event.events.core.InitializationEvent
import dev.minecraftbox.event.events.core.PostInitializationEvent
import dev.minecraftbox.event.events.core.PreInitializationEvent
import dev.minecraftbox.loader.ModLoader
import dev.minecraftbox.utils.modLoadingCoroutine
import kotlinx.coroutines.launch
import net.minecraft.client.Minecraft
import org.apache.logging.log4j.LogManager
import org.lwjgl.opengl.Display

object MinecraftBox {
    val logger = LogManager.getLogger(this::class.java)!!

    @InvokeEvent
    fun onPostInitialization(event: PostInitializationEvent) {
        modLoadingCoroutine.launch {
            ModLoader.loadMods()
        }
    }

    @InvokeEvent
    fun onInitialization(event: InitializationEvent) {
        Display.setTitle("Box ${Properties.boxVersion}")
    }
}