package neetcode.slidingWindow

object LongestSubstringWithoutRepeatingCharacter {
    fun bruteForce(s: String): Int {
        var maxLength = 0
        for (i in s.indices) {
            for (j in i..<s.length) {
                val subString = s.substring(i, j + 1)
                if (allUnique(subString)) {
                    maxLength = maxOf(maxLength, subString.length)
                }
            }
        }
        return maxLength
    }

    private fun allUnique(subString: String): Boolean {
        val set = HashSet<Char>()
        for (c in subString) {
            if (!set.add(c)) {
                return false
            }
        }
        return true
    }

    fun optimal(s: String): Int {
        val seenChar = HashSet<Char>()
        var leftPointer = 0
        var maxLength = 0

        for (rightPointer in s.indices) {
            while (seenChar.contains(s[rightPointer])) {
                seenChar.remove(s[leftPointer])
                leftPointer++
            }
            seenChar.add(s[rightPointer])
            maxLength = maxOf(maxLength, rightPointer - leftPointer + 1)
        }
        return maxLength
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(bruteForce("abcabcbb"))
    }
}