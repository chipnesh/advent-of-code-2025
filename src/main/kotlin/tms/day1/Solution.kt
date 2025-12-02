package tms.day1

import tms.readInput
import kotlin.math.absoluteValue
import kotlin.math.sign

private fun decipher(combinations: List<String>, countZeros: (position: Int, rotation: Int) -> Int): Int {
    var password = 0
    var position = 50
    for (op in combinations) {
        val rotation = op
            .partition { it.isLetter() }.let { (d, r) -> d[0] to r.toInt() }
            .let { (d, r) -> if (d == 'L') -r else r }
        password += countZeros(position, rotation)
        position = (position + rotation).mod(100)
    }
    return password
}

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
