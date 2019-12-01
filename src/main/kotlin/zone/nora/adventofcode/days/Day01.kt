package zone.nora.adventofcode.days

object Day01 : Day() {
    override val day
        get() = 1

    override fun partOne(): Int {
        var total = 0
        for (i in input) {
            var i2 = i.toInt()
            i2 /= 3
            total += (i2 - 2)
        }
        return total
    }

    override fun partTwo(): Int {
        var total = 0
        for (i in input) {
            var i2 = i.toInt()
            while (i2 > 0) {
                i2 = (i2 / 3) -2
                total += if (i2 < 0) 0 else i2
                // println(i2) im bad
            }
        }
        return total
    }
}