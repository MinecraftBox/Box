package dev.minecraftbox.event.events.player

import dev.minecraftbox.event.CancellableEvent
import net.minecraft.util.DamageSource

class PlayerSwingItemEvent : CancellableEvent()

class PlayerRespawnEvent

class PlayerDamageEntityEvent(val damageSource: DamageSource, val damageAmount: Float)