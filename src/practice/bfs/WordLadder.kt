package practice.bfs

class WordLadder {
    /*
    Brute Force — BFS with Pair Comparison
Approach:

For each word in queue compare with every word in wordList
Check if they differ by exactly one character
Inefficient — O(N x L) comparison per word
     */

    fun ladderLengthBrute(
        beginWord: String,
        endWord: String,
        wordList: List<String>,
    ): Int {
        val wordSet = wordList.toMutableSet()
        if (endWord !in wordSet) return 0

        val queue = ArrayDeque<Pair<String, Int>>()
        queue.add(Pair(beginWord, 1))

        while (queue.isNotEmpty()) {
            val (word, level) = queue.removeFirst()

            for (candidate in wordSet.toList()) {
                if (differsByOne(word, candidate)) {
                    if (candidate == endWord) return level + 1
                    wordSet.remove(candidate)
                    queue.add(Pair(candidate, level + 1))
                }
            }
        }
        return 0
    }

    private fun differsByOne(word: String, candidate: String): Boolean {
        var diff = 0
        for (i in word.indices) {
            if (word[i] != candidate[i]) diff++
            if (diff > 1) return false
        }
        return diff == 1
    }

    /*
    Time  -> O(N² x L) N = wordList size, L = word length
Space -> O(N) queue + visited set
     */

    /*
    Optimal — BFS with 26 Letter Substitution
        Approach:

        For each word try replacing every position with a-z
        Check if transformed word exists in wordSet
        Remove from wordSet immediately to mark visited
        Return level when endWord found
         */

    fun ladderLengthOptimal(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordSet = wordList.toMutableSet()
        if (endWord !in wordSet) return 0

        val queue = ArrayDeque<String>()
        queue.add(beginWord)
        var level = 1

        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            level++

            repeat(levelSize) {
                val word = queue.removeFirst()
                val chars = word.toCharArray()

                for (i in chars.indices) {
                    val original = chars[i]

                    for (c in 'a'..'z') {
                        if (c == original) continue
                        chars[i] = c
                        val newWord = String(chars)
                        if (newWord == endWord) return level
                        if (newWord in wordSet) {
                            wordSet.remove(newWord)
                            queue.add(newWord)
                        }
                    }
                    chars[i] = original
                }
            }
        }
        return 0
    }
    /*
    Time  -> O(N x L x 26) ~ O(N x L)
Space -> O(N) queue + wordSet
     */
    /*
    Brute Force          Optimal
Neighbor find   Compare all words    26 letter substitution
Time            O(N² x L)           O(N x L x 26)
Space           O(N)                 O(N)
Interview use   Mention first        Code this
     */
    /*
    1. Model as graph — words are nodes, edge if differ by one letter
2. BFS guarantees shortest path in unweighted graph
3. Brute force -> compare every word pair -> O(N² x L)
4. Optimal -> try all 26 substitutions per position -> O(N x L x 26)
5. Remove word from set immediately to mark visited
6. Edge case -> endWord not in wordList -> return 0 immediately
7. level starts at 1 (beginWord counts as step 1)
     */
}