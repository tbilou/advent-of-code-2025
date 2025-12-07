private fun MutableList<Long>.mul(): Long {
    return this.fold(1L) { acc, value -> acc * value }
}

fun main() {
    // Took the easy way out.
    // Just trim and split, get the numbers from the column and perform the operation
    fun part1(input: List<String>): Long {
        // ["123 328  51 64"] -> [[123][328][51][64]]
        val problems = input
            .map { it.trim() }
            .map { it.replace("""[\s]+""".toRegex(), " ") }
            .map { it.split(" ") }
        val numbersList = problems.dropLast(1)
        val operations = problems.takeLast(1).flatten()

        var answers = mutableListOf<Long>()

        for (column in 0 until operations.size) {
            // Get the numbers from the column
            val numbers = mutableListOf<Long>()
            numbersList.forEach { row ->
                val num = row[column].toLong()
                numbers.add(num)
            }

            // Calculate the answer
            val answer = when (operations[column]) {
                "*" -> numbers.mul()
                "+" -> numbers.sum()
                else -> throw IllegalArgumentException("Unrecognized operation: $operations[column]")
            }
            answers.add(answer)
        }
        return answers.sum()
    }

    // Use the operations as the guide: The distance between the operations is the length of the numbers
    // The only problem is the last operation sign. It has no length, so we need to get it from the numbers.
    fun part2(input: List<String>): Long {
        val numberRows = input.dropLast(1)
        val operationsRow = input.last().split("""\s+""".toRegex())
        val maxLenght = input.maxOf { it.length }.toLong()
        val columnRanges = calculateColumnRanges(input.last(), maxLenght)

        var answers = mutableListOf<Long>()
        columnRanges.forEachIndexed { column, range ->
            val columnNumbers = getColumnNumbers(range, numberRows)
            val nums = CephalopodNumbers(columnNumbers)

            // Apply the operations in the numbers
            val answer = when (operationsRow[column]) {
                "*" -> nums.mul()
                "+" -> nums.sum()
                else -> throw IllegalArgumentException("Unrecognized operation: $operationsRow[column]")
            }
            answers.add(answer)
        }
        return answers.sum()
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 3263827L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

fun CephalopodNumbers(numbers: List<String>): MutableList<Long> {
    val nums = mutableListOf<Long>()
    for (col in 0 until numbers[0].length) {
        val num = StringBuilder()
        numbers.forEach { row ->
            val r = row.reversed()
            if (r[col] != '_') num.append(r[col])
        }
        nums.add(num.toString().toLong())
    }
    return nums
}

fun getColumnNumbers(range: IdRange, numbers: List<String>): List<String> {
    val column = numbers
        .map { r ->
            val charsUntilEnd = r.length - range.end.toInt()
            var row = r
            if (charsUntilEnd <= 0) {
                row = row.padEnd(row.length + 1)
            }
            row.substring(range.start.toInt(), range.end.toInt())
        }
        .map { it.replace(" ", "_") }
    return column
}

fun calculateColumnRanges(operations: String, maxLength: Long): List<IdRange> {
    var start = 0L
    val offsets = mutableListOf<IdRange>()
    for (i in 1 until operations.length) {
        val character = operations[i]
        when (character) {
            '*', '+' -> {
                offsets.add(IdRange(start, i - 1L))
                start = i.toLong()
            }
        }
    }
    // Last one
    offsets.add(IdRange(start, maxLength))
    return offsets
}