package practice.hashMap

object SumArraySumEqualsK {
    fun sumArrayBruteForce(nums: IntArray, k: Int): Int {
        var count = 0
        for ( i in nums.indices) {
            var sum = 0
            for ( j in i..<nums.size) {
                sum += nums[j]
                if (sum == k) count++
            }
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(sumArrayBruteForce(intArrayOf(1, 1, 1), 2))
    }
}