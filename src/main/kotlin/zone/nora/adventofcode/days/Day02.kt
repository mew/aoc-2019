package zone.nora.adventofcode.days

import kotlin.math.floor

object Day02 : Day() {
    override val day: Int
        get() = 2

    override fun partOne(): Any {
        // I copied my solution for part one into a new function to help solve part two.
        // This was my original solution vv
        /*
        * val ints = inputAsString.split(",").toMutableList()
        * var hit99 = false
        * ints[1] = "12"
        * ints[2] = "2"
        * for (i in ints.indices) {
        *     if (!(i == 0 || (i.toDouble() / 4) == floor(i.toDouble() / 4))) continue
        *     if (ints[i] == "99") hit99 = true
        *     if (hit99) continue
        *     val p1 = ints[ints[i+1].toInt()].toInt()
        *     val p2 = ints[ints[i+2].toInt()].toInt()
        *     val p3 = ints[i+3].toInt()
        *     when (ints[i]) {
        *         "1" -> ints[p3] = (p1 + p2).toString()
        *         "2" -> ints[p3] = (p1 * p2).toString()
        *     }
        * }
        * return ints[0]
        */
        return solve(12, 2)
    }

    override fun partTwo(): Any {
        var found = false
        var a = 0
        var b = 0
        for (i in 0..99) {
            if (found) continue
            a = i
            for (i2 in 0..99) {
                if (found) continue
                b = i2
                if (solve(i, i2) == "19690720") found = true
            }
        }
        return 100 * a + b
    }

    private fun solve(a: Int, b: Int): String {
        val ints = inputAsString.split(",").toMutableList()
        var hit99 = false
        ints[1] = "$a"
        ints[2] = "$b"
        for (i in ints.indices) {
            if (!(i == 0 || (i.toDouble() / 4) == floor(i.toDouble() / 4))) continue
            if (ints[i] == "99") hit99 = true
            if (hit99) continue
            val p1 = ints[ints[i+1].toInt()].toInt()
            val p2 = ints[ints[i+2].toInt()].toInt()
            val p3 = ints[i+3].toInt()
            when (ints[i]) {
                "1" -> ints[p3] = (p1 + p2).toString()
                "2" -> ints[p3] = (p1 * p2).toString()
            }
        }
        return ints[0]
    }
}