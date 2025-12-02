package tms

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int, test: Boolean): List<String> {
    val name = if (test) "Test" else "Input"
    return Path("src/main/kotlin/tms/day$day/$name.txt").readText().trim().lines()
}

fun readInput(test: Boolean): List<String> {
    // Look through the stack trace for a class under package tms.day{N}
    val stack = Throwable().stackTrace
    val day = stack.asSequence()
        .map { it.className }
        .mapNotNull { className ->
            // Match tms.day12.* or exactly tms.day12
            val match = Regex("^tms\\.day(\\d+)(?:\\..*)?$").find(className)
            match?.groupValues?.getOrNull(1)?.toIntOrNull()
        }
        .firstOrNull()
        ?: error("readInput(test=..): Cannot determine day from call stack. Ensure the caller is in package tms.dayN")

    return readInput(day = day, test = test)
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun List<String>.joinLines(transform: (String) -> String) = joinToString(System.lineSeparator(), transform = transform)

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
