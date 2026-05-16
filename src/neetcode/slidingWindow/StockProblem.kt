package neetcode.slidingWindow

object StockProblem {
    /*

Time Complexity: O(n)
Space Complexity: O(1)
     */
    fun maxProfit(prices: IntArray): Int {
        var profit = 0
        for (i in 1 until prices.size) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1]
            }
        }
        return profit
    }

    /*
    Intuition:

Either do nothing on day d → dp[t][d-1]

Or sell on day d → profit = prices[d] + max(dp[t-1][j] - prices[j])
     */
    fun maxProfitWithKTransactions(prices: IntArray, k: Int): Int {
        if (prices.isEmpty() || k == 0) return 0
        val n = prices.size

        if (k >= n / 2) return maxProfit(prices)

        val dp = Array(k + 1) { IntArray(n) }

        for (t in 1..k) {
            var maxDiff = -prices[0]
            for (d in 1 until n) {
                dp[t][d] = maxOf(dp[t][d - 1], prices[d] + maxDiff)
                maxDiff = maxOf(maxDiff, dp[t - 1][d - prices[d]])
            }
        }
        return dp[k][n - 1]
    }
}