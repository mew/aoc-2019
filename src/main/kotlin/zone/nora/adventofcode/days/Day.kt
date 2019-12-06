package zone.nora.adventofcode.days

import zone.nora.adventofcode.util.Input

abstract class Day {
    abstract val day: Int
    val input = Input.asList(day)
    val inputAsString = Input.asString(day)

    fun run(part: Part): Any {
        return when (part) {
            Part.ONE -> {
                partOne()
            }
            Part.TWO -> {
                partTwo()
            }
        }
    }

    abstract fun partOne(): Any
    abstract fun partTwo(): Any

    companion object {
        val dayList = listOf(Day01, Day02, Day03, Day04, Day05, Day06)
    }

    enum class Part {
        ONE,
        TWO
    }
}