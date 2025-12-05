fun main() {
    fun part1(input: List<String>): Int {
        val grid = mapFloorPlan(input)
        val accessibleRolls = getRemovableRolls(grid)
        return accessibleRolls.count()
    }

    fun part2(input: List<String>): Int {
        val grid = mapFloorPlan(input)
        var total = 0
        var removableRolls = mutableListOf(Cell(0, 0, ""))
        while (removableRolls.isNotEmpty()) {
            removableRolls = getRemovableRolls(grid)
            total += removableRolls.size
            removableRolls.forEach { cell ->
                grid[cell.x, cell.y] = ""
            }
        }
        return total
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

fun getRemovableRolls(grid: Grid<String>): MutableList<Cell<String>> {
    var removedRolls = mutableListOf<Cell<String>>()
    grid.forEachCell { x, y, value ->
        if (value == "@") {
            val neighbours = grid.neighbours8(x, y)
            val rolls = neighbours.filter { cell -> cell.value == value }
            if (rolls.size < 4) {
//                println("($y, $x) is a candidate")
                removedRolls.add(Cell(x, y, "@"))
            }
        }
    }
    return removedRolls
}

fun mapFloorPlan(input: List<String>): Grid<String> {
    val grid = Grid(input.size, input.size, "")
    input.forEachIndexed { row, value ->
        value.forEachIndexed { column, value ->
            grid[column, row] = value.toString()
        }
    }
    return grid
}
