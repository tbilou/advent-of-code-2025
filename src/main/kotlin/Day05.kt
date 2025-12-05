fun main() {
    fun part1(input: List<String>): Int {
        val (ingredientIdRanges, ingredientIds) = parseIngredientsDatabase(input)
        val freshIngredients = ingredientIds.filter { id ->
            isIngredientFresh(ingredientIdRanges, id.toLong())
        }
        return freshIngredients.size
    }

    fun part2(input: List<String>): Long {
        val (ingredientIdRanges, _) = parseIngredientsDatabase(input)
        val sortedIngredientRanges = ingredientIdRanges.sortedBy { it.start }
        var highestId = 0L
        var total = mutableListOf(0L)
        sortedIngredientRanges.forEach {
            val (value, id) = freshIngredients(it, highestId)
            total.add(value)
            highestId = id
        }
        return total.sum()
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

fun parseIngredientsDatabase(input: List<String>): Pair<List<IdRange>, List<String>> {
    val emptyLine = input.indexOf("")
    val ingredientIdRanges = input.subList(0, emptyLine).map { it.toIdRange() }
    val ingredients = input.subList(emptyLine + 1, input.size)
    return Pair(ingredientIdRanges, ingredients)
}

fun freshIngredients(range: IdRange, highest: Long): Pair<Long, Long> {
    val start = range.start
    val end = range.end

    // Fully inside the existing range
    if (start <= highest && end <= highest) {
        return Pair(0, highest)
    }
    // start is lower than hightest id
    if (start <= highest && end > highest) {
        return Pair(end - highest, end)
    }
    // start is greater than highest id
    if (start > highest) {
        return Pair(end - start + 1, end)
    }
    return Pair(-1L, -1L)
}

fun isIngredientFresh(ingredientIdRanges: List<IdRange>, ingredientId: Long): Boolean {
    ingredientIdRanges.forEach { range ->
        if (ingredientId in range.start..range.end) {
//            println("$ingredientId is Fresh!")
            return true
        }
    }
    return false
}
