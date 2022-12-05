import java.awt.font.NumericShaper

fun main() {
    infix fun<T : Comparable<T>> ClosedRange<T>.fullyContains(otherRange : ClosedRange<T>) : Boolean {
        return (this.start <= otherRange.start) && (this.endInclusive >= otherRange.endInclusive)
    }
    infix fun<T : Comparable<T>> ClosedRange<T>.partiallyOverlaps(otherRange : ClosedRange<T>) : Boolean {
        return when {
            this.start <= otherRange.start && otherRange.start <= this.endInclusive -> true
            otherRange.start <= this.start && this.start <= otherRange.endInclusive -> true
            else -> false
        }
    }

    fun createRangeFromString(rangeString : String): IntRange {
        val (start, end) = rangeString.split("-")

        return start.toInt()..end.toInt()
    }

    fun part1(input: List<String>): Int {
        return input.filter {
            val (leftString, rightString) = it.split(",")

            val leftRange = createRangeFromString(leftString)

            val rightRange = createRangeFromString(rightString)

            //println("$leftRange,$rightRange ${leftRange fullyContains rightRange} ${rightRange fullyContains leftRange}")

            return@filter (leftRange fullyContains rightRange) or (rightRange fullyContains leftRange)
        }.count()
    }

    fun part2(input: List<String>): Int {
         return input.filter {
            val (leftString, rightString) = it.split(",")

            val leftRange = createRangeFromString(leftString)

            val rightRange = createRangeFromString(rightString)

            return@filter (leftRange partiallyOverlaps rightRange) or (rightRange partiallyOverlaps leftRange)
        }.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

