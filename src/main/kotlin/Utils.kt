import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/main/kotlin/$name.txt").readText().trim().lines()

fun readInputSplitByNewline(name: String) = Path("src/main/kotlin/$name.txt").readText().split("\n\n")

fun readInputAsString(name: String) = Path("src/main/kotlin/$name.txt").readText().trim()

fun splitStingIntoColumns(input: String, cols: Int): List<List<String>>? {
    // Validate input
    if (input.length <= 1) return null
    // Create a flat list (without whitespaces and newlines)
    val input = input.replace("""[\s]+""".toRegex(), "")

    // Create a list to store the columns
    val columns = MutableList(cols) { mutableListOf<String>() }

    var listIndex = 0
    input.forEachIndexed { i, c ->
        if (i % cols == 0) listIndex = 0
        columns[listIndex].add(c.toString())
        listIndex++
    }
    return columns
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
