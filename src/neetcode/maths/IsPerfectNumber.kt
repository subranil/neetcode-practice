package neetcode.maths

object IsPerfectNumber {
    fun isPerfectNumber(num: Int): Boolean {
        if (num <= 1) return false
        var sum = 1
        var i = 2
        while (i * i <= num) {
            if (num % i == 0) {
                sum += i
                if (i != num / i)
                    sum += num / i
            }
            i++
        }
        return sum == num
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(isPerfectNumber(28))
    }
}