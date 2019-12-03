package zone.nora.adventofcode.days

import kotlin.math.abs

object Day03 : Day() {
    override val day: Int
        get() = 3

    override fun partOne(): Int {
        val points = intersections()
        var smallest: Int? = null
        for (point in points) {
            val split = point.split(", ")
            val x = split[0].toInt()
            val y = split[1].toInt()
            if (smallest == null) smallest = abs(x) + abs(y)
            if ((abs(x) + abs(y)) < smallest) smallest = abs(x) + abs(y)
        }
        return smallest!!
    }

    override fun partTwo(): Int {
        // its 03 00 i give up lol
        val wire1 = input[0].split(",")
        val wire2 = input[1].split(",")

        var wire1X = 0
        var wire1Y = 0
        var wire2X = 0
        var wire2Y = 0
        var wire1d = 0
        var wire2d = 0
        val wire1XPoints = ArrayList<Int>()
        val wire1YPoints = ArrayList<Int>()
        val wire1hashmap = HashMap<String, Int>()
        for (s in wire1) {
            val op = op(s)
            val i = s.replace(s[0].toString(), "").toInt()
            for (i2 in 1..i) {
                when (op) {
                    OP.R -> wire1X++
                    OP.L -> wire1X--
                    OP.U -> wire1Y++
                    OP.D -> wire1Y--
                    else -> { /* nothing */ }
                }
                wire1d++
                wire1XPoints.add(wire1X)
                wire1YPoints.add(wire1Y)
                wire1hashmap["$wire1X, $wire1Y"] = wire1d
            }
        }
        val wire2XPoints = ArrayList<Int>()
        val wire2YPoints = ArrayList<Int>()
        val wire2hashmap = HashMap<String, Int>()
        for (s in wire2) {
            val op = op(s)
            val i = s.replace(s[0].toString(), "").toInt()
            for (i2 in 1..i) {
                when (op) {
                    OP.R -> wire2X++
                    OP.L -> wire2X--
                    OP.U -> wire2Y++
                    OP.D -> wire2Y--
                    else -> { /* nothing */ }
                }
                wire2d++
                wire2XPoints.add(wire2X)
                wire2YPoints.add(wire2Y)
                wire2hashmap["$wire2X, $wire2Y"] = wire2d
            }
        }

        val distances = ArrayList<Int>()
        for (i in 0 until wire1XPoints.size) {
            for (i2 in 0 until wire2XPoints.size) {
                if ((wire1XPoints[i] == wire2XPoints[i2]) && (wire1YPoints[i] == wire2YPoints[i2])) {
                    try {
                        distances.add(wire2hashmap["${wire2XPoints[i2]}, ${wire2YPoints[i2]}"]!! + wire1hashmap["${wire1XPoints[i]}, ${wire1YPoints[i]}"]!!)
                    } catch (ignored: Exception) {}
                }
            }
        }
        return distances.min()!!
    }

    private fun intersections(): ArrayList<String> {
        // this is bad
        // <oh you have no idea, Nora of 2 hours ago who wrote this ^>
        val wire1 = input[0].split(",")
        val wire2 = input[1].split(",")

        var wire1X = 0
        var wire1Y = 0
        var wire2X = 0
        var wire2Y = 0
        val wire1XPoints = ArrayList<Int>()
        val wire1YPoints = ArrayList<Int>()

        for (s in wire1) {
            val op = op(s)
            val i = s.replace(s[0].toString(), "").toInt()
            for (i2 in 1..i) {
                when (op) {
                    OP.R -> wire1X++
                    OP.L -> wire1X--
                    OP.U -> wire1Y++
                    OP.D -> wire1Y--
                    else -> { /* nothing */ }
                }
                wire1XPoints.add(wire1X)
                wire1YPoints.add(wire1Y)
            }
        }
        val wire2XPoints = ArrayList<Int>()
        val wire2YPoints = ArrayList<Int>()
        for (s in wire2) {
            val op = op(s)
            val i = s.replace(s[0].toString(), "").toInt()
            for (i2 in 1..i) {
                when (op) {
                    OP.R -> wire2X++
                    OP.L -> wire2X--
                    OP.U -> wire2Y++
                    OP.D -> wire2Y--
                    else -> { /* nothing */ }
                }
                wire2XPoints.add(wire2X)
                wire2YPoints.add(wire2Y)
            }
        }

        val points = ArrayList<String>()
        for (i in 0 until wire1XPoints.size) {
            for (i2 in 0 until wire2XPoints.size) {
                if ((wire1XPoints[i] == wire2XPoints[i2]) && (wire1YPoints[i] == wire2YPoints[i2])) {
                    points.add("${wire1XPoints[i]}, ${wire1YPoints[i]}")
                }
            }
        }
        return points
    }

    private fun op(s: String): OP {
        return when (s[0]) {
            "R"[0] -> OP.R
            "L"[0] -> OP.L
            "U"[0] -> OP.U
            "D"[0] -> OP.D
            else -> OP.E
        }
    }

    enum class OP {
        R, L, U, D, E
    }
}