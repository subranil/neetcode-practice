package neetcode.maths

import kotlin.math.pow

object IsArmStrongNumber {
    fun isArmStrongNumber(n: Int): Boolean {
        if (n < 0) return false
        val digits = n.toString()
        val power = digits.length
        val sum = digits.sumOf { ch ->
            (ch - '0').toDouble().pow(power.toDouble()).toLong()
        }
        return sum == n.toLong()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isArmStrongNumber(153))
    }
}