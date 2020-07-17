package dev.minecraftbox.event.events.networking

import dev.minecraftbox.event.CancellableEvent
import net.minecraft.network.Packet

class PacketSendEvent(val packet: Packet<*>) : CancellableEvent()