package ArrayAndNumbers;

/**
 * 122. Best Time to Buy and Sell Stock II
 * Say you have an array prices for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times).
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Constraints:
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 */

/**
 * 这题就没有 I 难了，核心思想就是只要是递增区间就累加，不然就只是挪指针。
 */
public class BestTimeTBuyAndSellStock_II {
  public int maxProfit(int[] prices) {
    int curDiff = 0;
    int res = 0;

    //corner case
    if(prices == null || prices.length == 0) return res;

    int i = 1; //pointer
    while(i < prices.length) {
      curDiff = prices[i] - prices[i - 1];
      if(curDiff > 0) {
        res += curDiff;
        i++;
      } else {
        i++;
      }
    }
    return res;
  }
}

/**
 Dry Run:
 i:           1  2  3  4  5
 Input:    7, 1, 5, 3, 6, 4
 curDiff:    -6  4 -2  3 -2
 res:         0  4  4  7  7
 */
