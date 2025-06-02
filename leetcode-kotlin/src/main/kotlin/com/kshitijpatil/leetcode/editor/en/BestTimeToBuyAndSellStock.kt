package com.kshitijpatil.leetcode.editor.en;
//You are given an array prices where prices[i] is the price of a given stock 
//on the iᵗʰ day. 
//
// You want to maximize your profit by choosing a single day to buy one stock 
//and choosing a different day in the future to sell that stock. 
//
// Return the maximum profit you can achieve from this transaction. If you 
//cannot achieve any profit, return 0. 
//
// 
// Example 1: 
//
// 
//Input: prices = [7,1,5,3,6,4]
//Output: 5
//Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 
//6-1 = 5.
//Note that buying on day 2 and selling on day 1 is not allowed because you 
//must buy before you sell.
// 
//
// Example 2: 
//
// 
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transactions are done and the max profit = 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
//
// Related Topics Array Dynamic Programming 👍 33210 👎 1281

class BestTimeToBuyAndSellStock {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    fun maxProfit(prices: IntArray): Int {
        check(prices.isNotEmpty())
        var minSoFar = prices[0]
        var maxProfit = 0
        for (price in prices) {
            minSoFar = minOf(price, minSoFar)
            maxProfit = maxOf(price - minSoFar, maxProfit)
        }
        return maxProfit
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

fun main() {
    val solution = BestTimeToBuyAndSellStock.Solution()
}