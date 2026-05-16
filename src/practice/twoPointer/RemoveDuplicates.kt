package practice.twoPointer

object RemoveDuplicates {

    fun removeDupsBruteForce(nums: IntArray): Int {
        val unique = nums.toSortedSet().toList()
        for ( i in unique.indices) {
            nums[i] = unique[i]
        }
        return unique.size
    }

    fun removeDupsOptimal(nums: IntArray): Int {
        /*
        Key insight: slow tracks the boundary of unique elements. fast scans ahead.
         When fast finds something new, slow advances and takes that value.
         */
        var slow = 0
        for (fast in 1..<nums.size) {
            if (nums[fast] != nums[slow]) {
                slow++
                nums[slow] = nums[fast]
            }
        }
        return slow + 1
    }
}