package PreSum;

/**
 * 325. Maximum Size Subarray Sum Equals k
 *
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn't one, return 0 instead.
 *
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 *
 * Example 1:
 *
 * Input: nums = [1, -1, 5, -2, 3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * Example 2:
 *
 * Input: nums = [-2, -1, 2, 1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 * Follow Up:
 * Can you do it in O(n) time?
 */


import java.util.HashMap;
import java.util.Map;

/**
 * 笔记：
 * 类似题：525. Contiguous Array
 * sum[i] means the sum from 0 to i inclusively
 * the sum from i to j is sum[j] - sum[i - 1] except that from 0 to j is sum[j].
 *
 * j-i is equal to the length of subarray of original array. we want to find the max(j - i)
 * for any sum[j] we need to find if there is a previous sum[i] such that sum[j] - sum[i] = k
 * Instead of scanning from 0 to j -1 to find such i, we use hashmap to do the job in constant time.
 */
public class MaximumSizeSubarraySumEqualsK {
  public int maxSubArrayLen(int[] nums, int k) {
    int n = nums.length;
    if(nums == null || n == 0) return 0;

    //preSum
    for(int i = 1; i < n; i++) {
      nums[i] += nums[i - 1];
    }

    //use map to do searching
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1); //这一步很重要，因为计算数量是1开始，但是index是0开始，
    // add this fake entry to make sum from 0 to j consistent
    int max = 0;
    for(int i = 0; i < n; i++) {

      if(map.containsKey(nums[i] - k)) {
        max = Math.max(max, i - map.get(nums[i] - k));
      }

      if(!map.containsKey(nums[i])) {
        map.put(nums[i], i); // keep only 1st duplicate number since we want the left index as left as possible
      }
    }
    return max;
  }

  //Test Case
  public static void main(String[] args) {
    MaximumSizeSubarraySumEqualsK test = new MaximumSizeSubarraySumEqualsK();
    System.out.println(test.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
  }
}
