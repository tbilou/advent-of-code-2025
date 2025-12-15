fun main() {
    fun part1(input: List<String>): Long {
        val devices = HashMap<String, List<String>>()
        input.map { string ->
            val (key, values) = string.split(": ")
            devices.put(key, values.split(" "))
        }
        val start = "you"
        val end = "out"
        val cache = hashMapOf<Triple<String, Boolean, Boolean>, Long?>()
        val paths = findPaths(Triple(start, true, true), devices, end, cache)
        return paths
    }

    fun part2(input: List<String>): Long {
        val devices = HashMap<String, List<String>>()
        input.map { string ->
            val (key, values) = string.split(": ")
            devices.put(key, values.split(" "))
        }
        val start = "svr"
        val end = "out"
        val cache = hashMapOf<Triple<String, Boolean, Boolean>, Long?>()
        val paths = findPaths(Triple(start, false, false), devices, end, cache)
        return paths
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day11_test2")
//    check(part1(testInput) == 5)
    check(part2(testInput) == 2L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
//    part1(input).println()
    part2(input).println()
}

fun findPaths(
    deviceNode: Triple<String, Boolean, Boolean>,
    devices: HashMap<String, List<String>>,
    end: String,
    cache: HashMap<Triple<String, Boolean, Boolean>, Long?>
): Long {
    var (deviceName, visitedFFT, visitedDAC) = deviceNode
    // End Node. Return 1 if path is valid 0 otherwise
    if (deviceName == end){
      // We've reached the end
        return if (visitedFFT && visitedDAC) 1L else 0L
    }

    // Did we already visit this node?
    if (cache[deviceNode] != null)
        return cache[deviceNode]!!

    // Check if node matches the valid ones
    if (deviceName == "fft") visitedFFT = true
    if (deviceName == "dac") visitedDAC = true

    // Get connections
    val connections = devices[deviceName]
    if (connections != null) {
        val paths = connections.map { connection ->
           findPaths(Triple(connection, visitedFFT, visitedDAC), devices, end, cache)
        }
        cache[Triple(deviceName, visitedFFT, visitedDAC)] = paths.sum()
        return paths.sum()
    }

    throw IllegalArgumentException("No connections for device nama: $deviceName")
}

