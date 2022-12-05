fun main() {
    fun part1(input: List<String>): Int {
        val letters : List<Char> = listOf('a'..'z','A'..'Z').flatten()

        return input.asSequence().map {
            val (leftPocket, rightPocket) = it.chunked(it.length / 2)

            val leftPocketSet : HashSet<Char> = hashSetOf()
            leftPocket.toCharArray().forEach(leftPocketSet::add)

            return@map rightPocket
                .toCharArray()
                .distinct()
                .filter { char -> leftPocketSet.contains(char) }
                .sumOf { char -> letters.indexOf(char) + 1 }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val letters : List<Char> = listOf('a'..'z','A'..'Z').flatten()

        return input.windowed(size = 3, step = 3)
            .map {
                val (firstList, secondList, thirdList) = it

                firstList.find { char -> secondList.contains(char) and thirdList.contains(char) }
            }
            .sumOf { letters.indexOf(it) + 1 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun getCharValue(char : Char) : Int {
    return char.digitToInt()
}

