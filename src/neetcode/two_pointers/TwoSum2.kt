package neetcode.two_pointers

object TwoSum2 {
    fun twoSum(num: IntArray, target: Int): IntArray {
        var left = 0
        var right = num.size - 1
        while (left < right) {
            val sum = num[left] + num[right]
            if (sum < target) {
                left++
            } else if (sum > target) {
                right--
            } else {
                return intArrayOf(left + 1, right + 1)
            }
        }
        return intArrayOf(-1, -1)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(twoSum(intArrayOf(2, 7, 11, 15), 9).toList())
    }
}