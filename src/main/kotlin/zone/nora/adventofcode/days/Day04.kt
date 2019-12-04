package zone.nora.adventofcode.days

object Day04 : Day() {
    override val day: Int
        get() = 4

    override fun partOne(): Int {
        return password(1)
    }

    override fun partTwo(): Int {
        return password(2)
    }

    private fun password(part: Int): Int {
        val r1 = inputAsString.split("-")[0].toInt()
        val r2 = inputAsString.split("-")[1].toInt()
        var counter = 0
        for (i in r1..r2) {
            val chars = i.toString().toCharArray()
            var dupe = false
            var failed = false
            for (i2 in chars.indices) {
                if (failed || i2 == 0) continue
                if (chars[i2].toInt() < chars[i2-1].toInt()) {
                    failed = true
                    continue
                }
                if (chars[i2] == chars[i2-1]) {
                    if (part == 1) dupe = true
                    if (dupe) continue
                    when (i2) {
                        1 -> {
                            if (chars[1] != chars[2]) dupe = true
                        }
                        2, 3, 4 -> {
                            if (chars[i2] != chars[i2+1] && chars[i2] != chars[i2-2]) dupe = true
                        }
                        5 -> {
                            if (chars[i2] != chars[i2-2]) dupe = true
                        }
                    }
                }
            }
            if (!dupe) failed = true
            if (!failed) counter++
        }

        return counter
    }
}