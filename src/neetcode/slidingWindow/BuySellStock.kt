package neetcode.slidingWindow

object BuySellStock {
    fun bruteForce(prices: Array<Int>): Int {
        var maxProfit = 0
        for (i in prices.indices) {
            for (j in i + 1..prices.size) {
                val profit = prices[j] - prices[i]
                maxProfit = maxOf(maxProfit, profit)
            }
        }
        return maxProfit
    }

    fun optimal(prices: Array<Int>): Int {
        if (prices.isEmpty())
            return 0

        var maxProfit = 0
        var minPrice = Int.MAX_VALUE
        for (currentPrice in prices.indices) {
            if (currentPrice < minPrice) {
                minPrice = currentPrice
            } else {
                maxProfit = maxOf(maxProfit, currentPrice - minPrice)
            }
        }
        return maxProfit
    }
}