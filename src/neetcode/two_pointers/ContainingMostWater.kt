package neetcode.two_pointers

object ContainingMostWater {
    fun maxArea(heights: IntArray): Int {
        var left = 0
        var right = heights.size -1
        var biggestSize = 0

        while (left < right) {
            val distance = right - left
            val leftSide = heights[left]
            val rightSide = heights[right]


            val size = if (leftSide > rightSide) {
                right --
                calculateFromSize(distance, rightSide)
            } else {
                left++
                calculateFromSize(distance, leftSide)
            }

            if (size > biggestSize) {
                biggestSize = size
            }
        }
        return biggestSize
    }

    private fun calculateFromSize(distance: Int, side: Int): Int {
       return distance * side
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
    }
}