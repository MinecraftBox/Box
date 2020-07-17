package dev.minecraftbox.event.events.gui

import dev.minecraftbox.event.CancellableEvent
import net.minecraft.client.gui.GuiScreen

class GuiOpenEvent(val guiScreen: GuiScreen) : CancellableEvent()

class GuiCloseEvent : CancellableEvent()