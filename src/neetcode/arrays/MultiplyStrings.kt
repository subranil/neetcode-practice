package neetcode.arrays

object MultiplyStrings {
    private fun multiply(num1: String, num2: String): String {
        val m = num1.length
        val n = num2.length
        val position = IntArray(m + n)

        for (i in m - 1 downTo 0) {
            for (j in n - 1 downTo 0) {
                val multiply = (num1[i] - '0') * (num2[j] - '0')
                val p1 = i + j
                val p2 = i + j + 1
                // important part
                val sum = multiply + position[p2]
                position[p2] = sum % 10
                position[p1] += sum / 10
            }
        }

        var start = 0
        while (start < position.size - 1 && position[start] == 0)
            start++

        val result = CharArray(position.size - start)
        for (i in result.indices) {
            result[i] = '0' + position[start + i]
        }
        return String(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(multiply("23", "45"))
    }

}