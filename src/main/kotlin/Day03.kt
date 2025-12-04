fun main() {
    fun part1(batteryBanks: List<String>): Long {
        return calculateJoltages(batteryBanks, 2)
    }

    fun part2(banks: List<String>): Long {
        return calculateJoltages(banks, 12)
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

fun calculateJoltages(batteryBanks: List<String>, batteries: Int): Long {
    return batteryBanks.sumOf { bank ->
        var start = 0
        var maxJoltage = mutableListOf<Int>()

        for (b in batteries downTo 1) {
            val end = bank.length - b
            val (index, joltage) = maxJoltage(bank, start, end)
            maxJoltage.add(joltage)
            start = index + 1
        }
        maxJoltage.joinToString("").toLong()
    }
}

// Search for the highest value between start and end indices of the string
fun maxJoltage(bank: String, start: Int, end: Int): Pair<Int, Int> {
    var index = -1
    var joltage = 0
    for (i in end downTo start) {
        if (bank[i].toString().toInt() >= joltage) {
            joltage = bank[i].toString().toInt()
            index = i
        }
    }
    return Pair(index, joltage)
}