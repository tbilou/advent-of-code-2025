import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    fun part1(input: List<String>, connections: Int): Long {
        val points = input.map { it.toPoint3D() }
        val distances = calculateDistances(points)
        val sortedDistances = distances.sortedBy { it.third }
        val (circuits, _) = makeConnections(connections, sortedDistances, points)
        val big3 = circuits.sortedByDescending { it.size }.take(3).map { it.size }
        return big3.fold(1L) { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Long {
        val points = input.map { it.toPoint3D() }
        val distances = calculateDistances(points)
        val sortedDistances = distances.sortedBy { it.third }
        val (_, last) = makeConnections(null, sortedDistances, points)
        return last.first.x.toLong() * last.second.x.toLong()
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day08_test")
    check(part1(testInput, 10) == 40L)
    check(part2(testInput) == 25272L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day08")
    part1(input, 1000).println()
    part2(input).println()


}

fun makeConnections(
    numConnections: Int?,
    distances: List<Triple<Point3D, Point3D, Double>>,
    points: List<Point3D>
): Pair<List<MutableList<Point3D>>, Triple<Point3D, Point3D, Double>> {
    val circuits = points.map { mutableListOf(it) }
    var last: Triple<Point3D, Point3D, Double>? = null
    for (i in 0 until (numConnections ?: distances.size)) {
        val (a, b, _) = distances[i]
        val circuitA = circuitContainsPoint(circuits, a)
        val circuitB = circuitContainsPoint(circuits, b)
        if (circuitA == circuitB) continue
        circuits[circuitA].addAll(circuits[circuitB])
        circuits[circuitB].clear()
        last = Triple(a, b, 0.toDouble())

    }
    return Pair(circuits, last!!)
}

private fun circuitContainsPoint(circuits: List<List<Point3D>>, a: Point3D): Int {
    circuits.forEachIndexed { index, circuit ->
        if (circuit.contains(a)) return index
    }
    throw IllegalStateException("No circuit found to point: $a")
}

fun calculateDistances(points: List<Point3D>): List<Triple<Point3D, Point3D, Double>> {
    val distances = mutableListOf<Triple<Point3D, Point3D, Double>>()
    for (a in 0 until points.size) {
        for (b in a + 1 until points.size) {
            val a = points[a]
            val b = points[b]
            distances.add(Triple(a, b, calculateDistance(a, b)))
        }
    }
    return distances
}

fun calculateDistance(a: Point3D, b: Point3D): Double {
    return sqrt(
        (b.x - a.x).toDouble().pow(2)
                + (b.y - a.y).toDouble().pow(2)
                + (b.z - a.z).toDouble().pow(2)
    )
}


private fun String.toPoint3D(): Point3D {
    val coordinates = this.split(",").map { it.toInt() }
    return Point3D(coordinates[0], coordinates[1], coordinates[2])
}

data class Point3D(val x: Int, val y: Int, val z: Int)
