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

fun splitStingIntoColumns(input: String, numColumns: Int): List<List<String>>? {
    // Validate input
    if (input.length <= 1) return null
    // Create a flat list (with a spaces splitting the numbers)
    val input = input.replace("""[\s]+""".toRegex(), " ")

    // Create a list to store the columns
    val columns = MutableList(numColumns) { mutableListOf<String>() }

    var listIndex = 0
    val buffer = StringBuilder()
    for (c in input) {
        if (c == ' '){
            // assign the buffer to the column list
            columns[listIndex].add(buffer.toString())
            buffer.clear()
            if (listIndex == numColumns - 1) listIndex = 0 else listIndex++
            continue
        }
        buffer.append(c)
    }
    if (buffer.isNotEmpty())
        columns[listIndex].add(buffer.toString())

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
