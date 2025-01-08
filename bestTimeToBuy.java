// 188. Best Time to Buy and Sell Stock IV
// Hard
// Topics
// Companies
// You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

// Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

// Example 1:

// Input: k = 2, prices = [2,4,1]
// Output: 2
// Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

class Solution {
  public int maxProfit(int k, int[] prices) {
    if (k >= prices.length / 2) {
      int sell = 0;
      int hold = Integer.MIN_VALUE;

      for (final int price : prices) {
        sell = Math.max(sell, hold + price);
        hold = Math.max(hold, sell - price);
      }

      return sell;
    }

    int[] sell = new int[k + 1];
    int[] hold = new int[k + 1];
    Arrays.fill(hold, Integer.MIN_VALUE);

    for (final int price : prices)
      for (int i = k; i > 0; --i) {
        sell[i] = Math.max(sell[i], hold[i] + price);
        hold[i] = Math.max(hold[i], sell[i - 1] - price);
      }

    return sell[k];
  }
}