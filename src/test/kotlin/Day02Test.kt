import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun part1_getInvalidIds() {
        var invalidIds = getInvalidIds(idRange = IdRange(11, 22), ::isInvalid_part1)
        assertEquals(listOf(11L, 22L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(95, 115), ::isInvalid_part1)
        assertEquals(listOf(99L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(998, 1012), ::isInvalid_part1)
        assertEquals(listOf(1010L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(1188511880, 1188511890), ::isInvalid_part1)
        assertEquals(listOf(1188511885L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(222220, 222224), ::isInvalid_part1)
        assertEquals(listOf(222222L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(1698522, 1698528), ::isInvalid_part1)
        assertEquals(emptyList<Long>(), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(446443, 446449), ::isInvalid_part1)
        assertEquals(listOf(446446L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(38593856, 38593862), ::isInvalid_part1)
        assertEquals(listOf(38593859L), invalidIds)
    }

    @Test
    fun part2_getInvalidIds() {
        var invalidIds = getInvalidIds(idRange = IdRange(11, 22), ::isInvalid_part2)
        assertEquals(listOf(11L, 22L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(95, 115), ::isInvalid_part2)
        assertEquals(listOf(99L, 111L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(998, 1012), ::isInvalid_part2)
        assertEquals(listOf(999L, 1010L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(1188511880, 1188511890), ::isInvalid_part2)
        assertEquals(listOf(1188511885L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(222220, 222224), ::isInvalid_part2)
        assertEquals(listOf(222222L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(1698522, 1698528), ::isInvalid_part2)
        assertEquals(emptyList<Long>(), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(446443, 446449), ::isInvalid_part2)
        assertEquals(listOf(446446L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(38593856, 38593862), ::isInvalid_part2)
        assertEquals(listOf(38593859L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(565653, 565659), ::isInvalid_part2)
        assertEquals(listOf(565656L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(824824821, 824824827), ::isInvalid_part2)
        assertEquals(listOf(824824824L), invalidIds)

        invalidIds = getInvalidIds(idRange = IdRange(2121212118, 2121212124), ::isInvalid_part2)
        assertEquals(listOf(2121212121L), invalidIds)
    }

}