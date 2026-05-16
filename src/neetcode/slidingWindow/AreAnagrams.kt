package neetcode.slidingWindow

object AreAnagrams {
    fun simpleAndEfficientSolution(s1: String, s2: String): Boolean {
        if (s1.length != s2.length) return false

        val freq = IntArray(26)
        for (c in s1) freq[c - 'a']++
        for (c in s2) freq[c - 'a']--

        return freq.all { it == 0 }
    }

    fun countOccurances(a: String, b: String): Int {
        if (b.length > a.length) return 0
        val freqB = IntArray(26)
        val freqA = IntArray(26)
        val windowSize = b.length
        var count = 0

        for (c in b) freqB[c - 'a']++
        for (i in 0 until windowSize) freqA[a[i] - 'a']++
        if (freqA.contentEquals(freqB)) count++

        for (i in windowSize until a.length) {
            freqA[a[i] - 'a']++
            freqA[a[i - windowSize] - 'a']--

            if(freqA.contentEquals(freqB)) count++
        }
        return count
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(countOccurances("forxxorfxdofr", "for"))
    }
}