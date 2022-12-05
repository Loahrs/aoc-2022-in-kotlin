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
        return input.asSequence().map {
            val (opponentMove, desiredOutcomeHint) = it.split(" ")
            val opponentShape = getShape(opponentMove)

            val desiredOutcome = desiredOutcome(desiredOutcomeHint)

            return@map desiredOutcome.points + requiredShapeForOutcome(opponentShape, desiredOutcome).points
        }.sum()
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

fun requiredShapeForOutcome(opponentShape: Shape, desiredOutcome: Outcome): Shape {
    return when(desiredOutcome) {
        Outcome.WIN -> Shape.values().find { it beats opponentShape }!!
        Outcome.LOSS -> Shape.values().find { opponentShape beats it }!!
        Outcome.DRAW -> opponentShape
    }
}

fun desiredOutcome(input : String) : Outcome {
    return when(input) {
        "X" -> Outcome.LOSS
        "Y" -> Outcome.DRAW
        "Z" -> Outcome.WIN
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
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    infix fun beats(otherShape: Shape) : Boolean {
        val beatenByThis = when(this) {
            ROCK -> SCISSORS
            PAPER -> ROCK
            SCISSORS -> PAPER
        }

        return beatenByThis == otherShape
    }
}