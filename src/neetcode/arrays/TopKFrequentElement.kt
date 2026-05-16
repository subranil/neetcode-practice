package neetcode.arrays

import java.util.PriorityQueue

object TopKFrequentElement {

    /*
The solution uses bucket sort. It
Counts the frequency of each number.
Groups numbers by their frequency.
Iterates from the highest frequency to lowest, collecting elements until we have k of them.
     */

    // option 1: sort by frequency BruteForce Way
    /*
    Time Complexity:
O(n + u log u)
O(n) to build frequency map.
O(u log u) to sort.
O(k) is negligible compared to sorting.
     */
    private fun findSortByFrequency(array: IntArray, k: Int): IntArray {
        val frequencyMap = HashMap<Int, Int>()
        for (num in array) {
            frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
        }
        return frequencyMap.entries
            .sortedByDescending { it.value }
            .take(k)
            .map { it.key }
            .toIntArray()
    }

    // option 2: use min heap
    private fun findTopKFrequent(array: IntArray, k: Int): IntArray {
        val frequencyMap = HashMap<Int, Int>()
        for (num in array) {
            frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
        }
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        for ((num, freq) in frequencyMap) {
            minHeap.offer(num to freq)
            if (minHeap.size > k) {
                minHeap.poll()
            }
        }
        return minHeap.map { it.first }.toIntArray()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(findSortByFrequency(intArrayOf(1, 1, 1, 2, 2, 3), 2).toList())
        println(findTopKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2).toList())
    }
}