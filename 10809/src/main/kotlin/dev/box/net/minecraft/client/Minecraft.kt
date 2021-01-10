package dev.box.net.minecraft.client

import clone.net.minecraft.client.Minecraft
import java.io.File

object Minecraft : Minecraft {
    override val version: String
        get() = "1.8.9"
    override val dataDir: File
        get() = net.minecraft.client.Minecraft.getMinecraft().mcDataDir
}