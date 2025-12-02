fun main() {
    fun part1(input: String): Long {
        val idRanges = input.split(",").map { it.toIdRange() }
        // I like to keep the list of invalid Ids.
        val invalidIds = mutableListOf<Long>()

        idRanges.forEach {
            invalidIds.addAll(getInvalidIds(it, ::isInvalid_part1))
        }
        return invalidIds.sum()
    }

    fun part2(input: String): Long {
        val idRanges = input.split(",").map { it.toIdRange() }
        // I like to keep the list of invalid Ids.
        val invalidIds = mutableListOf<Long>()

        idRanges.forEach {
            invalidIds.addAll(getInvalidIds(it, ::isInvalid_part2))
        }
        return invalidIds.sum()
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInputAsString("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInputAsString("Day02")
    part1(input).println()
    part2(input).println()
}

fun getInvalidIds(idRange: IdRange, function: (Long) -> (Boolean)): List<Long> {
    val invalidIds = mutableListOf<Long>()
    var value: Long = idRange.start
    while (value <= idRange.end) {
        if (function(value)) {
            println("$value is invalid")
            invalidIds.add(value)
        }
        value++
    }
    return invalidIds
}

fun isInvalid_part1(input: Long): Boolean {
    val id = input.toString()
    if (id.lengthIsEven()) {
        val middle = id.length / 2
        // Compare both sides of the string
        return id.substring(0, middle) == id.substring(middle)
    } else return false
}

fun isInvalid_part2(value: Long): Boolean {
    val id = value.toString()
    val groups = id.length / 2
    for (i in 1..groups) {
        val chunks = id.chunked(i)
        // Remove duplicates by converting to set
        if (chunks.toSet().size == 1) return true
    }
    return false
}

private fun String.lengthIsEven(): Boolean {
    return this.length % 2L == 0L
}

internal fun String.toIdRange(): IdRange {
    return IdRange(this.substringBefore("-").toLong(), this.substringAfter("-").toLong())
}

data class IdRange(val start: Long, val end: Long)