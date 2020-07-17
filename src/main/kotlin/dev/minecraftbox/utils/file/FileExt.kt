package dev.minecraftbox.utils.file

import java.io.File
import java.util.zip.ZipFile

fun File.isJar() = extension == "jar"

fun ZipFile.readFile(file: File) = try {
    getInputStream(getEntry(file.absolutePath))
        .bufferedReader()
        .readText()
} catch (e: Exception) {
    error("File does not exist!")
}