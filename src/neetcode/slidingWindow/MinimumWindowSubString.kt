package neetcode.slidingWindow

object MinimumWindowSubString {
    fun bruteForce(s: String, t: String): String {
        var minLength = Int.MAX_VALUE
        var result = ""

        for (i in s.indices) {
            for (j in i..s.length) {    
                val subString = s.substring(i, j + 1)
                if (containsAll(subString, t)) {
                    if (subString.length < minLength) {
                        minLength = subString.length
                        result = subString
                    }
                }
            }
        }
        return result
    }

    private fun containsAll(subString: String, t: String): Boolean {
        val tCount = IntArray(128)
        val sSubStringCount = IntArray(128)

        for (c in t) {
            tCount[c.code]++
        }

        for (c in subString) {
            sSubStringCount[c.code]++
        }
        for (c in t) {
            if (sSubStringCount[c.code] < tCount[c.code]) {
                return false
            }
        }
        return true
    }

    fun optimal(s: String, t: String): String {
        if (s.isEmpty() || t.isEmpty())
            return ""

        val tCount = IntArray(128)
        val windowCount = IntArray(128)

        for (c in t) {
            tCount[c.code]++
        }

        var leftPointer = 0
        var rightPointer = 0
        var requiredLength = t.length
        var minLength = Int.MAX_VALUE
        var result = ""

        // expand the window with right pointer
        while (rightPointer < s.length) {
            val rChar = s[rightPointer]
            windowCount[rChar.code]++

            if (tCount[rChar.code] > 0 && windowCount[rChar.code] <= tCount[rChar.code]) {
                requiredLength--
            }

            // contract the window when all character of t are in window

            while (requiredLength == 0) {
                val lChar = s[leftPointer]
                if (rightPointer - leftPointer + 1 < minLength) {
                    minLength = rightPointer - leftPointer + 1
                    result = s.substring(leftPointer, rightPointer + 1)
                }
                windowCount[lChar.code]--
                if (tCount[lChar.code] > 0 && windowCount[lChar.code] < tCount[lChar.code]) {
                    requiredLength++
                }
                leftPointer++
            }
            rightPointer++
        }
        return result
    }
}