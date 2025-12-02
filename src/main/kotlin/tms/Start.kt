package tms

import java.nio.file.Files.writeString
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.readLines

fun main(args: Array<String>) {
    val rootPkg = Path("src/main/kotlin/tms")

    val nextDay = rootPkg
        .listDirectoryEntries()
        .filter { it.isDirectory() && it.name.startsWith("day") }
        .maxOf { it.name.removePrefix("day").toInt() + 1 }

    val dayDir = rootPkg.resolve("day$nextDay").createDirectories()
    val content = rootPkg.resolve("Template.kt")
        .readLines()
        .joinLines { if (it.startsWith("package")) "package tms.day$nextDay\n\nimport tms.readInput" else it }
    writeString(dayDir.resolve("Solution.kt"), content)
    writeString(dayDir.resolve("Test.txt"), "")
    writeString(dayDir.resolve("Input.txt"), "")

    println("Generated day$nextDay at: $dayDir")
}
