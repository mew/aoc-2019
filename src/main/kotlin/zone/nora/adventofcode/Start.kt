package zone.nora.adventofcode

import com.github.ajalt.mordant.TermColors
import zone.nora.adventofcode.days.Day

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        val day = try {
            args[0].toInt()
        } catch (ignored: NumberFormatException) {
            error("Input must be an integer.")
            return
        }

        val d = Day.dayList[(day - 1)].run(Day.Part.ONE)
        ans("Part One:\n$d")
        val d2 = Day.dayList[(day - 1)].run(Day.Part.TWO)
        ans("Part Two:\n$d2")
        return
    }
    error("Please provide a day.")
}

fun ans(a: Any) {
    with(TermColors()) { println(brightGreen(a.toString())) }
}

fun error(s: String) {
    with(TermColors()) { println(red(s)) }
}