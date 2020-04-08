package TwoPointers;

/**
 * 题目：209. Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution,
 * try coding another solution of which the time complexity is O(n log n).
 */

/**
 * 笔记：
 * 这里面注意一下for loop和 while loop的逻辑，for loop是走完肚子里面所有的逻辑指针才挪动，所以用while loop
 * 要自己控制指针，注意在最底下挪动指针才可以跟for loop起到一样的结果。
 * 看一下下面两个写法：
 */
public class MinimumSizeSubarraySum {
  public int minSubArrayLen(int s, int[] nums) {
    int res = Integer.MAX_VALUE;
    int left = 0;
    int right = 0;
    int currSum = 0;
    while(right < nums.length) {
      currSum = currSum + nums[right];
      while(right < nums.length && currSum >= s) {
        res = Math.min(res, right - left + 1);
        currSum = currSum - nums[left];
        left++;
      }
      right++; //最外层的while循环挪动指针需要在肚子里所有逻辑都走完的时候再挪动
    }
    return res == Integer.MAX_VALUE ? 0 : res;
  }

  public int minSubArrayLen2(int s, int[] nums) {
    int res = Integer.MAX_VALUE;
    int left = 0;
    int right = 0;
    int currSum = 0;
    for(right =0; right < nums.length; right++) {
      //for loop的情况下就是里面逻辑全走完才进行指针挪动
      currSum = currSum + nums[right];
      while(right < nums.length && currSum >= s) {
        res = Math.min(res, right - left + 1);
        currSum = currSum - nums[left];
        left++;
      }
    }
    return res == Integer.MAX_VALUE ? 0 : res;
  }

  //Test case
  public static void main(String[] args) {
    MinimumSizeSubarraySum test = new MinimumSizeSubarraySum();
    int[] nums = {2,3,1,2,4,3};
    System.out.println(test.minSubArrayLen(7, nums));
  }
}
