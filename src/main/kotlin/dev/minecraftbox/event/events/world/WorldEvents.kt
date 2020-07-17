package dev.minecraftbox.event.events.world

import dev.minecraftbox.event.CancellableEvent
import net.minecraft.client.multiplayer.WorldClient

class WorldLoadEvent(val worldClient: WorldClient, val loadingMessage: String) : CancellableEvent()