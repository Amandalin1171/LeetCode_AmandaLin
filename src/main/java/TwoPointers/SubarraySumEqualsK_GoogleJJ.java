package TwoPointers;

/**
 * 给一个 array，求 array 里面最短的 subarry，使得 array 里面 element 的和等于 K
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 笔记：因为可能有负数，如果没有负数的话可以用sliding window
 * 解法：presum 求两段preSume的差等于K
 * 类似于LEETCODE 560. Subarray Sum Equals K
 */
public class SubarraySumEqualsK_GoogleJJ {
  public int[] subarraySum(int[] nums, int k) {
    Map<Integer, Integer> preSumMap = new HashMap<>();
    //key: preSum, value: right index of the preSum slot
    int preSum = 0;
    int idx1 = 0;
    int idx2 = 0;
    int length = Integer.MAX_VALUE;
    int resIdx1 = 0;
    int resIdx2 = 0;
    preSumMap.put(nums[0], 0);
    for(int i = 0; i < nums.length; i++) {
      preSum += nums[i];
      if(preSumMap.containsKey(preSum - k)) {
        idx1 = preSumMap.get(preSum - k);
        idx2 = i;
        if(length > idx2 - idx1) {
          resIdx1 = idx1;
          resIdx2 = idx2;
          length = idx2 - idx1;
        }
      } else {
        preSumMap.put(preSum, i);
        //Q1: 如果已经存在了同样值的preSum怎么做。
      }
    }
    int[] res = new int[length];
    for(int i = resIdx1; i <= resIdx2; i++) {
      res[i] = nums[i];
    }
    return res;
  }
}
