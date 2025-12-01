import kotlin.test.Test
import kotlin.test.assertEquals

class RotateWheel2Test {
    @Test
    fun simple_overflow() {
        val instructions = listOf("L1","R1","R1")
        val zeros = rotateWheel2(instructions, 0)
        assertEquals(3, zeros)
    }
    @Test
    fun cycles_overflow() {
        val instructions = listOf("L135","R140")
        val zeros = rotateWheel2(instructions, 0)
        assertEquals(4, zeros)
    }

    @Test
    fun cycles_overflow_large() {
        val instructions = listOf("R1000")
        val zeros = rotateWheel2(instructions, 50)
        assertEquals(10, zeros)
    }
}