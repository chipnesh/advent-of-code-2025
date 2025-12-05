package tms.day5

import tms.readInput

fun main() {

    fun first(input: List<String>): Int {
        var ranges = true
        val (freshIds, ingredients) = input.partition { if (it.isBlank()) ranges = false; ranges }
        val freshRanges = freshIds.map { it.split('-').let { (start, end) -> start.toLong()..end.toLong() } }
        return ingredients.count { id ->
            if (id.isBlank()) return@count false
            freshRanges.any { fresh -> id.toLong() in fresh }
        }
    }

    fun second(input: List<String>) =
        input.asSequence()
            .takeWhile { it.isNotBlank() }
            .map { it.split('-').let { (start, end) -> start.toLong()..end.toLong() } }
            .sortedBy { it.first }
            .fold(emptyList<LongRange>()) { ranges, next ->
                when {
                    ranges.isEmpty() -> listOf(next)
                    next.first > ranges.last().last + 1 -> ranges.plusElement(next)
                    else -> ranges.dropLast(1).plusElement((ranges.last().first..maxOf(ranges.last().last, next.last)))
                }
            }
            .sumOf { it.endExclusive - it.first }

    val input = readInput(test = false)
    println(first(input))
    println(second(input))
}
