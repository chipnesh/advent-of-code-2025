package tms.day1

import tms.readInput
import kotlin.math.absoluteValue
import kotlin.math.sign

private fun decipher(input: List<String>, zeros: (position: Int, rotation: Int) -> Int): Int =
    input
        .map { if (it[0] == 'L') -it.drop(1).toInt() else it.drop(1).toInt() }
        .fold(50 to 0) { (pos, pass), clicks -> (pos + clicks) % 100 to pass + zeros(pos, clicks) }
        .second

fun main() {
    fun first(input: List<String>) = decipher(input) { position, rotation ->
        ((position + rotation) % 100 == 0).compareTo(false)
    }

    fun second(input: List<String>) = decipher(input) { position, rotation ->
        (1..rotation.absoluteValue).count { (position + it * rotation.sign) % 100 == 0 }
    }

    val input = readInput(test = false)
    println(first(input))
    println(second(input))
}
