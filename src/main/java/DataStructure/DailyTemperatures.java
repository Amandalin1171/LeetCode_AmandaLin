package DataStructure;

/**
 * 739. Daily Temperatures
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000].
 * Each temperature will be an integer in the range [30, 100].
 */

import java.util.Stack;

/**
 * 一开始做这个题的思考就是：
 * 连续递增的区间，在返回的arr中都是1，
 * 一旦出现递减区间，就要算到第几个的数字大于此刻这个数，不是一旦开始递增就对哦！这是理解题意
 * 所以运用堆栈的特性，我们可以一遍O(n)就完成这个过程
 */
public class DailyTemperatures {
  public int[] dailyTemperatures(int[] T) {
    int[] res = new int[T.length];
    Stack<Integer> stack = new Stack<>(); //elements: index of each element in the given array
    int r = 0; //right pointer

    //corner case
    //according to the note, no corner case

    while(r < T.length) {
      //空栈或者栈顶数字大于遍历的右指针所指数字：前面的大于后面的，也就是出现了递减区间
      //这种情况我们就压栈
      if(stack.isEmpty() || T[r] <= T[stack.peek()]) {
        stack.push(r);
        r++;
        //如果递增区间，就结算往答案array里面放数字
      } else if(T[r] > T[stack.peek()]) {
        res[stack.peek()] = r - stack.pop();
      }
    }
    return res;
  }
}
