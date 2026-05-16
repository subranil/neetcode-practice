package practice.twoPointer

object TrappingRainWater {
    fun trapBruteForce(height: IntArray): Int {
        var total = 0
        for ( i in height.indices) {
            var maxL =0; var maxR =0
            for ( l in 0..i ) maxL = maxOf(maxL, height[l])
            for (r in i..<height.size) maxR = maxOf(maxR, height[r])
            total+= minOf(maxL, maxR) - height[i]
        }
        return total
    }

    fun trapOptimal(height: IntArray): Int {
        /*
        Key insight: Water at any index is bounded by the shorter of the two max walls.
        If height[L] < height[R], the left side is the bottleneck — process it and move L inward.
        You already know the right wall is at least height[R] tall so it's safe.
         */
        var L = 0; var R = height.size - 1
        var maxL = 0; var maxR = 0
        var total = 0

        while(L < R) {
            if (height[L] < height[R]) {
                if (height[L] >= maxL) maxL = height[L]
                else total += maxL - height[L]
                L++
            } else {
                if (height[R] >= maxR) maxR = height[R]
                else total += maxR - height[R]
                R--
            }
        }
        return total
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(trapBruteForce(height = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    }
}