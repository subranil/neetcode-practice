package practice.twoPointer

object ContainerWithMostWater {
    fun findMaxArea(height: IntArray): Int {
        var max = 0
        for (i in height.indices) {
            for (j in i + 1..<height.size) {
                max = maxOf(max, minOf(height[i], height[j]) * (j-i))
            }
        }
        return max
    }

    fun maxAreaOptimal(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var max = 0

        while(l < r) {
            val area = minOf(height[l], height[r]) *(r -l)
            max = maxOf(max, area)

            if (height[l] < height[r]) {
                l++
            } else {
                r--
            }
        }
        return max
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(maxAreaOptimal(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
    }
}