package neetcode.two_pointers

object ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val ans = hashSetOf<List<Int>>()
        nums.sort()
        for (i in 0 until nums.size - 2) {
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                if (sum == 0) {
                    ans.add(listOf(nums[i], nums[left], nums[right]))
                    left++
                    right--
                }
                if (sum < 0)
                    left++
                else
                    right--
            }
        }
        return ans.toList()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
    }
}