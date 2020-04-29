package PreSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 *
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArray {
  public int findMaxLength(int[] nums) {
    if(nums == null || nums.length == 0) return 0;
    int n = nums.length;
    //change all 0s in given array to -1
    for(int i = 0; i < n; i++) {
      if(nums[i] == 0) nums[i] = -1;
    }

    //preSum
    for(int i = 1; i < n; i++) {
      nums[i] += nums[i - 1];
    }

    //seach using hashmap
    int max = 0; // store the longest length of subarray
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1); // fake starting point
    /**
     * 对这个initialization的解释：
     * for the arr [-1,1], when entering the loop, when i=1, sum=-1+1=0.
     * the length is i-(-1) -> 1-(-1) = 2. so put(0,-1) is needed.
     *
     * (0,-1), It means that, before we loop this array, the sum is 0 in initial,
     * and because we haven't starting loop, so the index = -1.
     */
    for(int i = 0; i < n; i++) {
      if(map.containsKey(nums[i] - 0)) { // similar to 325. Maximum Size Subarray Sum Equals k, k == 0
        max = Math.max(max, i - map.get(nums[i]));
      }
      if(!map.containsKey(nums[i])) {
        map.put(nums[i], i);
      }
    }
    return max;
  }
}
