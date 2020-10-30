package dev.minecraftbox.utils.data

data class ModFileData(
    val name: String,
    val modId: String,
    val version: String,
    val mainClass: String,
    val transformers: List<String>?
)