package tms.day2

import tms.readInput

fun main() {

    fun sumWrongSkus(input: List<String>, check: (String) -> Boolean): Long =
        input.first()
            .split(",").map { it.split("-") }
            .filterNot { (start, end) -> start.startsWith('0') || end.startsWith('0') }
            .flatMap { (start, end) -> start.toLong()..end.toLong() }
            .filter { check(it.toString()) }
            .sum()

    fun first(input: List<String>) = sumWrongSkus(input) { sku ->
        if (sku.length % 2 == 0) {
            val (left, right) = sku.chunked(sku.length / 2)
            left == right
        } else false
    }

    fun second(input: List<String>) = sumWrongSkus(input) { sku ->
        sku
            .map(Char::toString)
            .runningReduce(String::plus)
            .take(sku.length / 2)
            .any { it.repeat(sku.length / it.length) == sku }
    }

    val input = readInput(test = false)
    println(first(input))
    println(second(input))
}
