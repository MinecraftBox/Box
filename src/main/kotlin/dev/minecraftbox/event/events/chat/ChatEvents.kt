package dev.minecraftbox.event.events.chat

import dev.minecraftbox.event.CancellableEvent
import net.minecraft.util.IChatComponent

class ServerChatEvent(val component: IChatComponent) : CancellableEvent()