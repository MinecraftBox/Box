package dev.minecraftbox

import dev.minecraftbox.event.InvokeEvent
import dev.minecraftbox.event.events.core.PreInitializationEvent
import dev.minecraftbox.loader.ModLoader
import dev.minecraftbox.utils.modLoadingCoroutine
import kotlinx.coroutines.launch
import org.apache.logging.log4j.LogManager

object MinecraftBox {
    val logger = LogManager.getLogger(this::class.java)!!

    @InvokeEvent
    fun onPreInitialization(event: PreInitializationEvent) {
        modLoadingCoroutine.launch {
            ModLoader.loadMods()
        }
    }
}