package dev.minecraftbox.loader

import dev.minecraftbox.loader.loader.ModLoader

object ModLoader {

    /**
     * Access currently set loader
     *
     * Default value: Default mod loader, can be changed in case of beta changes to mod loading system with easy switching access etc.
     */
    var loader: Loader<*> = ModLoader()
}