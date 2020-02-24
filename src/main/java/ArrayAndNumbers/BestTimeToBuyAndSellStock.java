package ArrayAndNumbers;

/**
 * 题目：
 * 121. Best Time to Buy and Sell Stock
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */

/**
 * 笔记：
 * 类似于DP的思想：站在此刻，我有两个选择，把我脚下的这个数字进行操作（加到subarray中)或者不管
 * 只不过这里面简化就是直接进行判断，如果加进去发现小于0，直接赋值变成0：
 * Kadane's Algorithm： the logic is to calculate the difference (maxCur += prices[i] - prices[i-1])
 * of the original array, and find a contiguous subarray giving maximum profit.
 * If the difference falls below 0, reset it to zero.
 *
 * ******快去看下一个题，打开了DP新世界的大门！！！*******
 * Maximum Subarray，一样的思想套路！！！但是已经是totally DP的思想咯！！！加油~~~
 */
public class BestTimeToBuyAndSellStock {
  public int maxProfit(int[] prices) {
    int max = 0;
    int curMax = 0;
    for(int i = 0; i < prices.length - 1; i++) {
      curMax = Math.max(0, curMax + prices[i + 1] - prices[i]);
      max = Math.max(max, curMax);
    }
    return max;
  }

  public static void main(String[] args) {
    BestTimeToBuyAndSellStock testClass = new BestTimeToBuyAndSellStock();
    int[] prices = {7,1,5,3,6,4};
    int res = testClass.maxProfit(prices);
    System.out.println(res);
  }

}
