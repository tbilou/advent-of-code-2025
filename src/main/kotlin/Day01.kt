fun main() {
    fun part1(instructions: List<String>): Int {
        val positions = rotateWheel(instructions)
        val answer = positions.filter { it == 0 }.size
        return answer
    }

    fun part2(input: List<String>): Int {
        val zeros = rotateWheel2(input)
        return zeros
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

fun rotateWheel(instructions: List<String>, initialPosition: Int = 50): List<Int> {
    var current = initialPosition
    val steps = mutableListOf<Int>()
    instructions.forEach { instruction ->
        val direction = instruction.first()
        var amount = instruction.drop(1).toInt()
        // Remove the cycles. For an input of 562, drop the 5 and use only 62
        if (amount >= 100) amount = amount.toString().drop(1).toInt()
        when (direction) {
            'L' -> {
                current -= amount
                if (current < 0) current += 100
            }

            'R' -> {
                current += amount % 100
                if (current > 99) current %= 100
            }
        }
        steps.add(current)
    }
    return steps
}

fun rotateWheel2(instructions: List<String>, initialPosition: Int = 50): Int {
    var current = initialPosition
    var zeros = 0
    instructions.forEach { instruction ->
        val direction = instruction.first()
        // Remove the direction char to get the amount
        var amount = instruction.drop(1).toInt()
        // Remove the cycles. For an input of 562, drop the 5 and use only 62
        if (amount >= 100) {
            val cycles = (amount / 100)
            amount -= cycles * 100
            zeros += cycles
        }
        when (direction) {
            'L' -> {
                current -= amount
                if (current < 0) {
                    current += 100
                    val previous = current + amount
                    // If the previous value was zero, don't count it as a cycle.
                    if (current != 0 && previous != 100) zeros++
                }
            }

            'R' -> {
                current += amount % 100
                if (current > 99) {
                    current %= 100
                    if (current != 0) zeros++
                }
            }
        }
        // If we land on the zero count it
        if (current == 0) zeros++
    }
    return zeros
}


