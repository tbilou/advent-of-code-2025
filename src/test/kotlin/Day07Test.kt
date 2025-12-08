import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {
    @Test
    fun follow_particle_without_cache() {
        val tachyonManifold = listOf(
            "..S..",
            ".....",
            "..^..",
            ".....",
            ".^.^.",
            "....."
        )

        val splits = followParticle(row = 1, col = 2, tachyonManifold, cache)
        assertEquals(4, splits)
    }
}