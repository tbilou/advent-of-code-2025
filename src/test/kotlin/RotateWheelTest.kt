import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class RotateWheelTest {
    @Test
    fun rotateRight() {
        val instructions = listOf("R8", "L19")
        val clicks = rotateWheel(instructions, 11)
        assertEquals(2, clicks.size)
        assertContentEquals(listOf(19, 0), clicks)
    }
    @Test
    fun overflowFromZero(){
        val instructions = listOf("L1", "R1", "R1")
        val clicks = rotateWheel(instructions, 0)
        assertEquals(3, clicks.size)
        assertContentEquals(listOf(99, 0, 1), clicks)
    }

    @Test
    fun overflowLeft(){
        val instructions = listOf("L10", "R5")
        val clicks = rotateWheel(instructions, 5)
        assertEquals(2, clicks.size)
        assertContentEquals(listOf(95, 0), clicks)
    }

    @Test
    fun largeNumbers(){
        val instructions = listOf("R284")
        val clicks = rotateWheel(instructions, 0)
        assertEquals(1, clicks.size)
        assertContentEquals(listOf(84), clicks)
    }

    @Test
    fun negativeNumbers(){
        val instructions = listOf("L284")
        val clicks = rotateWheel(instructions, 0)
        assertEquals(1, clicks.size)
        assertContentEquals(listOf(16), clicks)
    }
}