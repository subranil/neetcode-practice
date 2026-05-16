package neetcode.arrays

object MaxSubArray {
    private fun maxSubArrayBruteForce(nums: IntArray, k: Int): Int {
        var count = 0

        for (i in nums.indices) {
            var sum = 0
            for (j in i..<nums.size) {
                sum += nums[j]
                if (sum == k) count++
            }
        }
        return count
    }

    private fun subArrayOptimal(nums: IntArray, k: Int): Int {
        val prefixCount = HashMap<Int, Int>()
        prefixCount[0] = 1 // empty prefix - handles sub array starting index 0

        var currentSum = 0
        var count = 0
        for (num in nums) {
            currentSum += num
            count += prefixCount.getOrDefault(currentSum - k, 0)
            prefixCount[currentSum] = prefixCount.getOrDefault(currentSum, 0) +1
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(subArrayOptimal(intArrayOf(1, 2, 1, 3), 3))
    }
}