import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun calculateJoltages() {
        var joltage = calculateJoltages(listOf("987654321111111"), 2)
        assertEquals(98, joltage)

        joltage = calculateJoltages(listOf("811111111111119"), 2)
        assertEquals(89, joltage)

        joltage = calculateJoltages(listOf("234234234234278"), 2)
        assertEquals(78, joltage)

        joltage = calculateJoltages(listOf("818181911112111"), 2)
        assertEquals(92, joltage)
    }

    @Test
    fun maxJoltage() {
        val (index, joltage) = maxJoltage("123456789", 2, 6)
        assertEquals(7, joltage)
        assertEquals(6, index)
    }

}