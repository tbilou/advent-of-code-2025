import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Long {
        val coordinates = input.map { it.toCoordinate() }
        val areas = calculateAreas(coordinates, Double.MAX_VALUE)
        return areas.map { (_, _, area) -> area }.max()
    }

    fun part2(input: List<String>): Long {
        val coordinates = input.map { it.toCoordinate() }.toMutableList()
        coordinates.add(coordinates[0])
        val polygonArea = polygonArea(coordinates)
        val areas = calculateAreas(coordinates, polygonArea)
        val sortedAreas = areas.sortedByDescending { (_, _, area) -> area }
        // Sort areas by size and the pass them on to the calculateAreasInsidePolygon
        val area = calculateAreasInsidePolygon(sortedAreas, coordinates)
        return area
    }

    // Read the test input from `src/Day01_test.txt`
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 50L)
    check(part2(testInput) == 24L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}

fun calculateAreasInsidePolygon(areas: List<Triple<Coord, Coord, Long>>, coordinates: List<Coord>): Long {
    areas.forEach { (a, b, area) ->
        if (checkCoordinates(a, b, coordinates)) {
            return area
        }
    }
    throw IllegalStateException("No areas found")
}

fun checkCoordinates(a: Coord, b: Coord, coordinates: List<Coord>): Boolean {
    // Generate all possible points between the two coordinates
    for (p in pointsInRectangle(a, b)) {
        // For each point call is coordInPolygon
        // If one is false immediately return
        if (!isCoordInPolygon(p, coordinates)) return false
    }
    return true
}

fun pointsInRectangle(a: Coord, b: Coord): Sequence<Coord> {
    val minX = minOf(a.x, b.x)
    val maxX = maxOf(a.x, b.x)
    val minY = minOf(a.y, b.y)
    val maxY = maxOf(a.y, b.y)

    // Use a Sequence so it's lazy and doesn't allocate a big list unless needed
    return sequence {
        for (y in minY..maxY) {
            yield(Coord(minX, y))
            yield(Coord(maxX, y))
        }
        for (x in minX..maxX) {
            yield(Coord(x, minY))
            yield(Coord(x, maxY))
        }
    }
}

fun calculateAreas(coordinates: List<Coord>, polygonArea: Double): MutableList<Triple<Coord, Coord, Long>> {
    val areas = mutableListOf<Triple<Coord, Coord, Long>>()
    for (a in 0 until coordinates.size) {
        for (b in a + 1 until coordinates.size) {
            val row = abs(coordinates[a].x - coordinates[b].x) + 1
            val col = abs(coordinates[a].y - coordinates[b].y) + 1
            val area = row * col
            // only store areas that are smaller than the polygon area
            if (area <= polygonArea) {
                areas.add(Triple(coordinates[a], coordinates[b], area))
            }
        }
    }
    return areas
}

fun polygonArea(points: List<Coord>): Double {
    require(points.size >= 3) { "A polygon must have at least 3 points" }

    var area = 0.0
    val n = points.size

    for (i in 0 until n) {
        val (x1, y1) = points[i]
        val (x2, y2) = points[(i + 1) % n]  // wraps back to 0 at the end
        area += x1 * y2 - x2 * y1
    }

    return abs(area) * 0.5
}

private fun String.toCoordinate(): Coord {
    val parts = this.split(",")
    return Coord(parts[0].toLong(), parts[1].toLong())
}

data class Coord(val x: Long, val y: Long)

fun isCoordInPolygon(coord: Coord, polygon: List<Coord>): Boolean {
    var inside = false
    val n = polygon.size

    var j = n - 1
    for (i in 0 until n) {
        val a = Coord(polygon[i].x, polygon[i].y)
        val b = Coord(polygon[j].x, polygon[j].y)

        // Check if point in to top of the edge formed by a and b
        val onSegment = pointOnSegment(coord, Pair(a, b))
        if (onSegment) {
            return true
        }

        // Check if coord.y is between a and b (exclusive)
        val intersects = ((a.y > coord.y) != (b.y > coord.y)) && (coord.x < (b.x - a.x) * (coord.y - a.y) / (b.y - a.y) + a.x)
        if (intersects) inside = !inside

        j = i
    }
    return inside;
}

/**
 * Returns true if point P(px, py) lies on the line segment A(x1, y1) -> B(x2, y2).
 */
fun pointOnSegment(coord: Coord, segment: Pair<Coord, Coord>): Boolean {
    val eps = 1e-9
    // 1. Check collinearity via cross product (AP x AB)
    val a = segment.first
    val b = segment.second
    val cross = (coord.x - a.x) * (b.y - a.y) - (coord.y - a.y) * (b.x - a.x)
    if (abs(cross) > eps) {
        return false // not on the infinite line
    }

    // 2. Check that P is within the bounding box of the segment
    val dot = (coord.x - a.x) * (coord.x - b.x) + (coord.y - a.y) * (coord.y - b.y)
    return dot <= eps // <= 0 (within tolerance) means between A and B
}