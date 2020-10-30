package dev.minecraftbox.launch

import net.minecraft.launchwrapper.ITweaker
import net.minecraft.launchwrapper.LaunchClassLoader
import java.io.File

@Suppress("unused")
class BoxTweaker : ITweaker {
    private val boxArguments = mutableListOf<String>()

    override fun acceptOptions(args: MutableList<String>, gameDir: File, assetsDir: File, profile: String) {
        boxArguments += args + listOf(
            "--gameDir", gameDir.absolutePath,
            "--assetsDir", assetsDir.absolutePath,
            "--version", profile
        )
    }

    override fun getLaunchTarget() = "net.minecraft.client.main.Main"

    override fun injectIntoClassLoader(classLoader: LaunchClassLoader) {
        classLoader.registerTransformer("dev.minecraftbox.asm.transformer.ClassTransformer")
    }

    override fun getLaunchArguments(): Array<String> = boxArguments.toTypedArray()
}