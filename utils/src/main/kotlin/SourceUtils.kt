import java.io.File

/**
 * THESE METHODS SHOULD NOT BE CALLED ANYWHERE IN THE SOURCECODE.
 */

fun main() {
    // Enter versions here
    // Example:
//    generateSourceSet("12202", "1.12.2")
    appendGitIgnore("12202")
}

const val INCLUDE = "include \"%s\""

fun generateSourceSet(sourceDirectoryFormattedVersion: String, minecraftVersion: String) {
    val sourceDirectory = createSourceDirectory(sourceDirectoryFormattedVersion)

    if (sourceDirectory.exists()) {
        println("No need to rerun these steps!"); return
    }
    generateBuildGradle(sourceDirectory, minecraftVersion)

    appendModuleToSettingsGradle(sourceDirectoryFormattedVersion)

    runSetupDevWorkspace(
        ":$sourceDirectoryFormattedVersion:setupDevWorkspace",
        System.getProperty("os.name").startsWith("Windows", true)
    )

    appendGitIgnore(sourceDirectoryFormattedVersion)
}

fun createSourceDirectory(version: String): File {
    val sourceDirectory = File(version)
    println("Creating source directory")
    sourceDirectory.mkdir()
    return sourceDirectory
}

fun generateBuildGradle(sourceDirectory: File, minecraftVersion: String) {
    println("Generating build.gradle file...")
    val bg = File(sourceDirectory, "build.gradle")
    val text = Resource::class.java
        .getResourceAsStream("/default-build.gradle")
        .bufferedReader()
        .use { it.readLines() }
        .toMutableList()

    text[21] = text[21].replace("%VERSION%", minecraftVersion)
    bg.writeText(text.joinToString("\n"))
}

fun appendModuleToSettingsGradle(version: String) {
    println("Appending module to settings.gradle")
    File("settings.gradle").also {
        val lines = it.readLines().toMutableList()
        it.forEachLine { line ->
            if (line == "// Versions") {
                lines.add(lines.indexOf(line) + 1, INCLUDE.format(version))
            }
        }
        it.writeText(lines.joinToString("\n"))
    }
}

fun runSetupDevWorkspace(moduleCommand: String, isWindows: Boolean) {
    println("Executing setupDevWorkspace")

    val builder = ProcessBuilder()
        .let {
            if (isWindows) it.command("cmd.exe", "/c", "gradlew", moduleCommand) else it.command(
                "./gradlew",
                moduleCommand
            )
        }
        .redirectErrorStream(true)
        .start()
    val reader = builder.inputStream.bufferedReader()

    while (builder.isAlive) {
        val line = reader.readLine()
        if (line != null.toString()) println(line)
    }
}

fun appendGitIgnore(version: String) {
    println("Adding files to gitignore")
    File(".gitignore").appendText(
        listOf(
            "\n/$version/build/",
            "/$version/src/minecraft/",
            "/$version/run/",
            "/$version/out/"
        ).joinToString("\n")
    )
}

class Resource