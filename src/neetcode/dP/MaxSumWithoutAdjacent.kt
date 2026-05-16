package neetcode.dP

import kotlin.math.max

object MaxSumWithoutAdjacent {
    fun bruteForce(array: IntArray, index: Int = 0): Int {
        if (index >= array.size) return 0

        val include = array[index] + bruteForce(array, index + 2)

        val exclude = bruteForce(array, index + 1)

        return maxOf(include, exclude)
    }

    fun optimalSolution(arr: IntArray): Int {
        /*
        At each position i, you have two choices:
        Include arr[i] → add it to dp[i-2]
        Exclude arr[i] → take dp[i-1]
         */
        if (arr.isEmpty()) return 0
        if (arr.size == 1) return arr[0]

        val dp = IntArray(arr.size)
        dp[0] = arr[0]
        dp[1] = maxOf(arr[0], arr[1])


        for (i in 2 until arr.size) {
            dp[i] = maxOf(arr[i] + dp[i - 2], dp[i - 1])
        }
        return dp.last()
    }

    fun furtherOptimized(array: IntArray) : Int {
        if (array.isEmpty()) return 0
        if (array.size == 1) return array[0]

        var prev2 = array[0]
        var prev1 = maxOf(array[0], array[1])

        for (i in 2 until array.size) {
            val curr = max(array[i] + prev2, prev1)
            prev2 = prev1
            prev1 = curr
        }
        return prev1
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val arr1 = intArrayOf(3, 2, 7, 10)
        val arr2 = intArrayOf(3, 2, 5, 10, 7)

        println(bruteForce(arr1))
        println(bruteForce(arr2))
    }
}