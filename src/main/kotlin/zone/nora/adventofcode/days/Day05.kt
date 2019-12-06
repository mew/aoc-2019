package zone.nora.adventofcode.days

object Day05 : Day() {
    override val day: Int
        get() = 5

    override fun partOne(): Int {
        val opcodes: Array<Int?>
        val inp = inputAsString.split(",")
        opcodes = arrayOfNulls(inp.size)

        for (i in inp.indices) {
            opcodes[i] = inp[i].toInt()
        }

        var s = 4
        var output = -1
        var f = false
        var i = 0
        while (i < opcodes.size) {
            val op = OpC(opcodes[i])
            when (op.opCode) {
                1 -> {
                    opcodes[opcodes[i + 3]!!] = op[opcodes, opcodes[i + 1], 1]?.plus(op[opcodes, opcodes[i + 2], 2]!!)
                    s = 4
                }
                2 -> {
                    opcodes[opcodes[i + 3]!!] = op[opcodes, opcodes[i + 1], 1]?.times(op[opcodes, opcodes[i + 2], 2]!!)
                    s = 4
                }
                3 -> {
                    opcodes[opcodes[i + 1]!!] = 1
                    s = 2
                }
                4 -> {
                    output = op[opcodes, opcodes[i + 1], 1]!!
                    s = 2
                }
                else -> {
                    f = true
                }
            }
            if (f) {
                break
            }
            i += s
        }

        return output
    }

    override fun partTwo(): Int {
        val opcodes: Array<Int?>
        val inp = inputAsString.split(",")
        opcodes = arrayOfNulls(inp.size)

        for (i in inp.indices) {
            opcodes[i] = inp[i].toInt()
        }
        var s = 4
        var output = -1
        var f = false
        var i = 0
        w@ while (i < opcodes.size) {
            val op = OpC(opcodes.get(i))
            when (op.opCode) {
                1 -> {
                    opcodes[opcodes[i + 3]!!] = op[opcodes, opcodes.get(i + 1), 1]?.plus(op[opcodes, opcodes.get(i + 2), 2]!!)
                    s = 4
                }
                2 -> {
                    opcodes[opcodes[i + 3]!!] = op[opcodes, opcodes.get(i + 1), 1]?.times(op[opcodes, opcodes.get(i + 2), 2]!!)
                    s = 4
                }
                3 -> {
                    opcodes[opcodes[i + 1]!!] = 5
                    s = 2
                }
                4 -> {
                    output = op[opcodes, opcodes[i + 1], 1]!!
                    s = 2
                }
                5 -> {
                    if (op[opcodes, opcodes[i + 1], 1] !== 0) {
                        i = op[opcodes, opcodes[i + 2], 2]!!
                        s = 0
                        break@w
                    }
                    s = 3
                }
                6 -> {
                    if (op[opcodes, opcodes[i + 1], 1] === 0) {
                        i = op[opcodes, opcodes[i + 2], 2]!!
                        s = 0
                        break@w
                    }
                    s = 3
                }
                7 -> {
                    opcodes[opcodes[i + 3]!!] = if (op[opcodes, opcodes[i + 1], 1]!! < op[opcodes, opcodes[i + 2], 2]!!
                    ) 1 else 0
                    s = 4
                }
                8 -> {
                    opcodes[opcodes[i + 3]!!] = if (op[opcodes, opcodes[i + 1], 1] === op[opcodes, opcodes.get(i + 2), 2]
                    ) 1 else 0
                    s = 4
                }
                else -> {
                    f = true
                }
            }
            if (f) {
                break
            }
            i += s
        }
        return output
    }

    private class OpC(code: Int?) {
        var opCode: Int = code!! % 100
        var params = IntArray(PARAM_SIZE)

        operator fun get(opcodes: Array<Int?>, value: Int?, index: Int?): Int? {
            if (index != null) {
                when (params[index - 1]) {
                    POSITION_MODE -> return opcodes[value!!]
                    IMMEDIATE_MODE -> return value
                }
            }
            return 0
        }

        companion object {
            const val POSITION_MODE = 0
            const val IMMEDIATE_MODE = 1
            const val PARAM_SIZE = 3
        }

        init {
            params[0] = code!! / 100 % 10
            params[1] = code / 1000
            params[2] = POSITION_MODE
        }
    }
}
