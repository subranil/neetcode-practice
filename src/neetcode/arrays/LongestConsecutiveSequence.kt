package neetcode.arrays

object LongestConsecutiveSequence {
    fun withBruteForce(array: IntArray): Int {
        if (array.isEmpty())
            return 0
        if (array.size == 1)
            return 1
        array.sort() // O(n log n)
        var maxLength = 1
        var currentLength = 1
        for (i in 1..array.size) {
            if (array[i] != array[i - 1]) { // avoid duplicate
                if (array[i] == array[i - 1] + 1) {
                    currentLength++
                } else {
                    maxLength = maxOf(maxLength, currentLength)
                    currentLength = 1
                }
            }
        }
        return maxOf(maxLength, currentLength)
    }

    fun optimalSolution(array: IntArray): Int { //O(1)
        if (array.isEmpty())
            return 0

        val hashSet = HashSet<Int>()
        array.forEach {
            hashSet.add(it)
        }
        var maxLen = 0

        array.forEach {
            if (!hashSet.contains(it - 1)) {
                var currNum = it
                var currentLength = 1
                while (hashSet.contains(currNum + 1)) {
                    currNum++
                    currentLength++
                }
                maxLen = maxOf(maxLen, currentLength)
            }
        }
        return maxLen
    }
}