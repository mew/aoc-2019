package zone.nora.adventofcode.util

import java.io.File

object Input {
    fun asList(day: Int): List<String> {
        var d = day.toString()
        for (i in 0..9) {
            if (day == i) d = "0$day"
        }
        return File(javaClass.classLoader.getResource("day$d.txt").toURI()).readLines()
    }

    fun asString(day: Int): String {
        var d = day.toString()
        for (i in 0..9) {
            if (day == i) d = "0$day"
        }
        return File(javaClass.classLoader.getResource("day$d.txt").toURI()).readText()
    }
}