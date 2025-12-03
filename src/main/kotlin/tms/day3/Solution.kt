package tms.day3

import tms.readInput

fun main() {

    fun totalJoltage(batteries: String, count: Int) =
        batteries
            .map(Char::digitToInt)
            .fold(emptyList<Int>() to batteries.length - count) { (bank, remaining), jolt ->
                val smallerCount = bank.count { it < jolt }.coerceAtMost(remaining)
                bank.dropLast(smallerCount) + jolt to remaining - smallerCount
            }.first.take(count).joinToString("").toLong()

    fun first(input: List<String>) = input.sumOf { line ->
        totalJoltage(line, 2)
    }

    fun second(input: List<String>) = input.sumOf { line ->
        totalJoltage(line, 12)
    }

    val input = readInput(test = false)
    println(first(input))
    println(second(input))
}
