fun main() {

    fun getSumOfTopN(n: Int, input : String) =
        input.split("\n\n")
            .map {
                it.lines()
                  .sumOf(String::toInt)
            }
            .sortedDescending()
            .take(n)
            .sum()

    fun part1(input: String): Int {
        return getSumOfTopN(1, input)
    }

    fun part2(input: String): Int {
        return getSumOfTopN(3, input)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsString("Day01_test")
    check(part1(testInput) == 24_000)

    val input = readInputAsString("Day01")
    println(part1(input))
    println(part2(input))
}

