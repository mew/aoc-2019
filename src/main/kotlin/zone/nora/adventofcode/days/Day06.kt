package zone.nora.adventofcode.days

object Day06 : Day() {
    override val day: Int
        get() = 6

    override fun partOne(): Int {
        var foundAll = false
        var orbitCount = 0
        val found = ArrayList<String>()
        val orbitMap = HashMap<String, Int>()
        while (!foundAll) {
            f@ for (i in input) {
                val orbiter = i.split(")")[1]
                val orbitee = i.split(")")[0]
                if (orbitee == "COM") {
                    if (!found.contains(orbiter)) {
                        orbitMap[orbiter] = 1
                        found.add(orbiter)
                        orbitCount++
                    }
                    continue@f
                }
                val o = orbitMap.getOrDefault(orbitee, -1)
                if (o == -1 || found.contains(orbiter)) continue@f
                orbitMap[orbiter] = o+1
                orbitCount += o+1
                found.add(orbiter)
                if (found.size == input.size) foundAll = true
            }
        }
        return orbitCount
    }

    override fun partTwo(): Int {
        var foundAll = false
        var orbitCount = 0
        val found = ArrayList<String>()
        val orbitMap = HashMap<String, Int>()
        val orbitMap2 = HashMap<String, String>()
        while (!foundAll) {
            f@ for (i in input) {
                val orbiter = i.split(")")[1]
                val orbitee = i.split(")")[0]
                if (orbitee == "COM") {
                    if (!found.contains(orbiter)) {
                        orbitMap[orbiter] = 1
                        found.add(orbiter)
                        orbitCount++
                        orbitMap2[orbiter] = orbitee
                    }
                    continue@f
                }
                val o = orbitMap.getOrDefault(orbitee, -1)
                if (o == -1 || found.contains(orbiter)) continue@f
                orbitMap[orbiter] = o+1
                orbitCount += o+1
                found.add(orbiter)
                orbitMap2[orbiter] = orbitee
                if (found.size == input.size) foundAll = true
            }
        }
        val san = orbitMap["SAN"]
        val you = orbitMap["YOU"]
        println("$san : $you")
        var found2 = false
        val youList = ArrayList<String>()
        val sanList = ArrayList<String>()
        var t = 0
        for (i in 1..1600) {
            if (found2) continue
            if (youList.isEmpty()) {
                val s = orbitMap2["SAN"]
                val y = orbitMap2["YOU"]
                youList.add(y!!)
                sanList.add(s!!)

                println("y: $y")
                println("s: $s")
                if (youList.contains(s)) {
                    found2 = true
                    val i2 = youList.indexOf(s) + 1
                    t = i2 + sanList.indexOf(s) + 1
                }
                continue
            }
            val y = orbitMap2.getOrDefault(youList[youList.size - 1], "NULLS")
            val s = orbitMap2.getOrDefault(sanList[sanList.size - 1], "NULLY")
            youList.add(y)
            sanList.add(s)
            if (!y.contains("NULL")) println("y: $y")
            if (!s.contains("NULL")) println("s: $s")
            if (youList.contains(s)) {
                found2 = true
                val i2 = youList.indexOf(s) + 1
                t = i2 + sanList.indexOf(s) + 1
            } else if (sanList.contains(y)) {
                found2 = true
                val i2 = youList.indexOf(y)
                t = i2 + sanList.indexOf(y)
            }
            continue
        }
        return t
    }
}