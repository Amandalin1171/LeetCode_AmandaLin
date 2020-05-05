package SlidingWindow;

/**
 * 930. Binary Subarrays With Sum
 *
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 * Example 1:
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *
 * Note:
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] is either 0 or 1.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 笔记：两种解法：
 * 1. preSum, 是 560. Subarray Sum Equals K 的简化版
 * store previous sum and the frequency of this sum,
 * because sum[i, j] = sum[0, j] - sum[0, i - 1], this is a very very important idea
 * 2. 2 pointer, 一定要学会写2 pointer啊啊啊
 */
public class BinarySubarraysWithSum {
  //方法1： preSum
  public int numSubarraysWithSum_preSum(int[] A, int S) {
    int sum = 0; //prefix sum
    int res = 0; //final result
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1); // fake starting point

    //preSum process
    for(int i = 1; i < A.length; i++) {
      A[i] += A[i - 1];
    }

    for(int i = 0; i < A.length; i++) {
      if(map.containsKey(A[i] - S)) {
        res += map.get()
      }
    }
  }

  public int numSubarraysWithSum_2Pointers(int[] A, int S) {

  }
}
