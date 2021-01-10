package clone.net.minecraft.client

import java.io.File

interface Minecraft {
    val version: String

    val dataDir: File
}