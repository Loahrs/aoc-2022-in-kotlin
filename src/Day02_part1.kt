fun main() {
    fun part1(input: List<String>): Int {
        return input.asSequence().map {
            val (opponentMove, playerMove) = it.split(" ")
            val opponentShape = getShape(opponentMove)
            val playerShape = getShape(playerMove)

            val outcome = calculateOutcome(opponentShape, playerShape)

            return@map outcome.points + playerShape.points
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun getShape(input : String) : Shape {
    return when(input) {
        "A","X" -> Shape.ROCK
        "B","Y" -> Shape.PAPER
        "C","Z" -> Shape.SCISSORS
        else -> throw Exception("Illegal Symbol")
    }
}

fun calculateOutcome(opponentShape : Shape, playerShape : Shape) : Outcome {
    return when {
        playerShape beats opponentShape -> Outcome.WIN
        opponentShape beats playerShape -> Outcome.LOSS
        else -> Outcome.DRAW
    }
}

enum class Outcome(val points: Int) {
    WIN(6), DRAW(3), LOSS(0)
}

enum class Shape(val points: Int) {
    ROCK(1) {
        override infix fun beats(otherShape : Shape) = otherShape == SCISSORS
    },
    PAPER(2) {
        override infix fun beats(otherShape : Shape) = otherShape == ROCK
    },
    SCISSORS(3) {
        override infix fun beats(otherShape : Shape) = otherShape == PAPER
    };

    abstract infix fun beats(otherShape: Shape) : Boolean
}