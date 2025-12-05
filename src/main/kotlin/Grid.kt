data class Cell<T>(
    val x: Int,
    val y: Int,
    val value: T
)

class Grid<T>(val width: Int, val height: Int, initialValue: T) {

    private val data: MutableList<T> = MutableList(width * height) { initialValue }

    private fun checkBounds(x: Int, y: Int) {
        require(x in 0 until width) { "x out of bounds: $x" }
        require(y in 0 until height) { "y out of bounds: $y" }
    }

    private fun index(x: Int, y: Int): Int = y * width + x

    operator fun get(x: Int, y: Int): T {
        checkBounds(x, y)
        return data[index(x, y)]
    }

    operator fun set(x: Int, y: Int, value: T) {
        checkBounds(x, y)
        data[index(x, y)] = value
    }

    fun forEachCell(action: (x: Int, y: Int, value: T) -> Unit) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                action(x, y, get(x, y))
            }
        }
    }

    /**
     * Returns all 8 neighbouring cells (up to 8; fewer on edges/corners).
     */
    fun neighbours8(x: Int, y: Int): List<Cell<T>> {
        checkBounds(x, y)

        val result = mutableListOf<Cell<T>>()

        for (dy in -1..1) {
            for (dx in -1..1) {
                if (dx == 0 && dy == 0) continue // skip center cell

                val nx = x + dx
                val ny = y + dy

                if (nx in 0 until width && ny in 0 until height) {
                    result += Cell(nx, ny, get(nx, ny))
                }
            }
        }
        return result
    }
}