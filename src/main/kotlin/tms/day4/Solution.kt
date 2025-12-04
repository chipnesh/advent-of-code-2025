package tms.day4

import tms.readInput

fun main() {

    fun List<MutableList<Boolean>>.isRoll(cell: Pair<Int, Int>): Boolean = this[cell.first][cell.second]

    fun List<MutableList<Boolean>>.traverseGrid(block: (cell: Pair<Int, Int>) -> Unit) =
        repeat(size) { currRow -> repeat(this[0].size) { currColumn -> block(currRow to currColumn) } }

    fun List<MutableList<Boolean>>.countNeighbors(cell: Pair<Int, Int>) =
        (-1..1).flatMap { row -> (-1..1).map { col -> row to col } }
            .filterNot { (row, col) -> row == 0 && col == 0 }
            .map { (row, col) -> cell.first + row to cell.second + col }
            .filter { (row, col) -> row in indices && col in this[0].indices }
            .count(::isRoll)

    fun List<MutableList<Boolean>>.remove(cell: Pair<Int, Int>) {
        this[cell.first][cell.second] = false
    }

    fun traverse(input: List<String>, remove: Boolean): Int {
        val grid = input.map { it.mapTo(mutableListOf()) { it == '@' } }
        var total = 0
        do {
            val picked = mutableListOf<Pair<Int, Int>>()
            grid.traverseGrid { cell ->
                if (!grid.isRoll(cell)) return@traverseGrid
                val count = grid.countNeighbors(cell)
                if (count < 4) {
                    picked.add(cell)
                }
            }
            picked.forEach(grid::remove)
            total += picked.size
        } while (remove && !picked.isEmpty())
        return total
    }

    fun first(input: List<String>) = traverse(input, remove = false)
    fun second(input: List<String>) = traverse(input, remove = true)

    val input = readInput(test = false)
    println(first(input))
    println(second(input))
}
