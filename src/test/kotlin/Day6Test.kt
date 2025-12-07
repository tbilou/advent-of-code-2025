import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun calculateOffsets_for_test_input() {
        val input = "*   +   *   +"
        val offsets = calculateColumnRanges(input, input.length + 1L)
        assertEquals(
            listOf(
                IdRange(0, 3),
                IdRange(4, 7),
                IdRange(8, 11),
                IdRange(12, 14)
            ), offsets
        )
    }

    @Test
    fun banana(){
        val nums = CephalopodNumbers(listOf("123", "_45", "__6"))
        assertEquals(listOf(356L, 24L, 1L), nums)
    }

    @Test
    fun getColumns_for_test_input() {
        val input = "*   +   *   +"
        val numbers = listOf(
            "123 328  51 64",
            " 45 64  387 23",
            "  6 98  215 314"
        )
        val maxLength = numbers.maxOf { it.length }.toLong()
        val ranges = calculateColumnRanges(input, maxLength)

        assertEquals(maxLength, 15)

        var columnNumbers = getColumnNumbers(ranges[0], numbers)
        assertEquals(listOf("123", "_45", "__6"), columnNumbers)

        columnNumbers = getColumnNumbers(ranges[1], numbers)
        assertEquals(listOf("328", "64_", "98_"), columnNumbers)

        columnNumbers = getColumnNumbers(ranges[2], numbers)
        assertEquals(listOf("_51", "387", "215"), columnNumbers)

        columnNumbers = getColumnNumbers(ranges[3], numbers)
        assertEquals(listOf("64_", "23_", "314"), columnNumbers)
    }

    @Test
    fun calculateOffsets_for_input() {
        val input = "*  +    +    *   *  +    *   *"
        val offsets = calculateColumnRanges(input, input.length + 0L)
        assertEquals(
            listOf(
                IdRange(0, 2),
                IdRange(3, 7),
                IdRange(8, 12),
                IdRange(13, 16),
                IdRange(17, 19),
                IdRange(20, 24),
                IdRange(25, 28),
                IdRange(29, 30),
            ), offsets
        )
    }

}