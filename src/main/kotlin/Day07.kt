fun main() {
    fun part1(input: List<String>): Int {
        val col = input.first().indexOf('S')
        var cols = mutableSetOf(col)
        var totalSplits = 0
        for (rowIndex in 1 until input.size) {
            val row = input[rowIndex]
            val (newCols, splits) = expandTachyonBeam(cols, row)
            cols = newCols
            totalSplits += splits
        }
        return totalSplits
    }

    fun part2(input: List<String>): Long {
        val col = input.first().indexOf('S')
        val cache = hashMapOf<Pair<Int, Int>, Long?>()
        val splits = followParticle(1, col, input, cache)
        return splits
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 40L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()

}

fun followParticle(row: Int, col: Int, tachyonManifold: List<String>, cache: HashMap<Pair<Int, Int>, Long?>): Long {
    if (row == tachyonManifold.size)
        return 1L
    if (tachyonManifold[row][col] == '^') {
        // Check if we already went down this path
        val cached = cache[Pair(row, col)]
        if (cached != null) {
            println("cached value ($row, $col) -> $cached")
            return cached
        }

        val left = followParticle(row + 1, col - 1, tachyonManifold, cache)
        val right = followParticle(row + 1, col + 1, tachyonManifold, cache)
        cache[Pair(row, col)] = left + right
        return left + right
    }
    return followParticle(row + 1, col, tachyonManifold, cache)
}

fun expandTachyonBeam(cols: MutableSet<Int>, row: String): Pair<MutableSet<Int>, Int> {
    val tachyonBeams = mutableSetOf<Int>()
    var splits = 0
    cols.forEach { c ->
        when (row[c]) {
            '^' -> {
                tachyonBeams.add(c - 1)
                tachyonBeams.add(c + 1)
                splits++
            }

            '.' -> tachyonBeams.add(c)
        }
    }
    return Pair(tachyonBeams, splits)
}
