import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {

    @Test
    fun `Fully inside the existing range`(){
        val (numIds, highest) = freshIngredients("4-10".toIdRange(), 20L)
        assertEquals(20L, highest)
        assertEquals(0L, numIds)
    }
    @Test
    fun `Fully inside the existing range with end value equal to highest`(){
        val (numIds, highest) = freshIngredients("4-10".toIdRange(), 10L)
        assertEquals(10L, highest)
        assertEquals(0L, numIds)
    }
    @Test
    fun `Fully inside the existing range with start value equal to highest`(){
        val (numIds, highest) = freshIngredients("4-4".toIdRange(), 4L)
        assertEquals(4L, highest)
        assertEquals(0L, numIds)
    }
    @Test
    fun `Start is equal highest`(){
        val (numIds, highest) = freshIngredients("14-18".toIdRange(), 14L)
        assertEquals(18L, highest)
        assertEquals(4L, numIds)
    }
    @Test
    fun `Start is after highest`(){
        val (numIds, highest) = freshIngredients("10-18".toIdRange(), 4L)
        assertEquals(18L, highest)
        assertEquals(9L, numIds)
    }
}