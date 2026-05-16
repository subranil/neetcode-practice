package practice.twoPointer

object TwoSum {
    fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1..<nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i + 1, j + 1)
                }
            }
        }
        return intArrayOf()
    }

    fun twoSumOptimal(nums: IntArray, target: Int): IntArray {
        var l = 0
        var r = nums.size - 1

        while (l < r) {
            val sum = nums[l] + nums[r]
            when {
                sum == target -> return intArrayOf(l + 1, r + 1)
                sum < target -> l++
                else -> r--
            }
        }
        return intArrayOf()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(twoSumOptimal(intArrayOf(2, 7, 11, 15), 9).contentToString())
    }
}