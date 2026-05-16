package neetcode.slidingWindow

object LongestRepeatingCharacterReplacement {
    fun bruteForce(s: String, k: Int): Int {
        var maxLength = 0
        for (i in s.indices) {
            for (j in i..<s.length) {
                val subString = s.substring(i, j + 1)
                if (canBeUniform(subString, k)) {
                    maxLength = maxOf(maxLength, subString.length)
                }
            }
        }
        return maxLength
    }
    private fun canBeUniform(subString: String, k: Int): Boolean {
        val frequency = IntArray(26)
        var maxCount = 0
        for (c in subString) {
            frequency[c - 'A']++
            maxCount = maxOf(maxCount, frequency[c - 'A'])
        }
        return (subString.length - maxCount) <= k
    }

    fun optimal(s: String, k: Int): Int {
        val frequency = IntArray(26)
        var leftPointer = 0
        var maxCount = 0
        var maxLength = 0

        for (rightPointer in s.indices) {
            val charIndex = s[rightPointer] - 'A'
            frequency[charIndex]++

            maxCount = maxOf(maxCount, frequency[charIndex])

            while (rightPointer - leftPointer +1 - maxCount > k) {
                val leftCharIndex = s[leftPointer] - 'A'
                frequency[leftCharIndex]--
                leftPointer++
            }
            maxLength = maxOf(maxLength, rightPointer - leftPointer + 1)
        }
        return maxLength
    }
}