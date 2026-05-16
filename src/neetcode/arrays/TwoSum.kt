package neetcode.arrays

object TwoSum {
    private fun twoSumBruteForce(
        nums: IntArray,
        target: Int
    ): IntArray{
        for (i in nums.indices) {
            for (j in i+1..<nums.size){
                if (nums[i] + nums[j] == target){
                    return intArrayOf(nums[i], nums[j])
                }
            }
        }
        return intArrayOf()
    }

    private fun twoSumOptimal(
        nums: IntArray,
        target: Int
    ): IntArray {
        val seen = HashMap<Int, Int>()
        for (i in nums.indices) {
            val compliment = target - nums[i]
            if (seen.containsKey(compliment)) {
                return intArrayOf(seen[compliment]!!, i)
            }
            seen[nums[i]] = i
        }
        return intArrayOf()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(twoSumOptimal(intArrayOf(1,2,3), 3).contentToString())
    }
}