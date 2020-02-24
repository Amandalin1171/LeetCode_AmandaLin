package ArrayAndNumbers;

/**
 * 题目：
 * 53. Maximum Subarray
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

/**
 * 笔记：
 * 需要注意的点都在行间附注，就是考虑处在一个时点的时候面临的选择^^
 */

public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int curSum = nums[0];
    int maxSum = nums[0];
    //注意一开始取了第一个值，所以for loop的时候i从1开始取值，千万别大脑没动手先行--#
    for(int i = 1; i < nums.length; i++) {
      //站在第二个数开始，往后每一个数字，我们都面临两个选择：
      //选择1： 把这个数算进之前的合计数中： curSum + nums[i]
      //选择2： 前面的都不要，这个数开始从新算： nums[i]
      //所以每一步的curSum就是取这两个选择的最大值
      curSum = Math.max(curSum + nums[i], nums[i]);
      maxSum = Math.max(maxSum, curSum);
    }
    return maxSum;
  }

  public static void main(String[] args) {
    MaximumSubarray testClass = new MaximumSubarray();
    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
    int res = testClass.maxSubArray(nums);
    System.out.println(res);
  }

}
