package PreSum;

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
 *
 * [1,0,1,0, ]
 * [ ,0,1,0,1]
 * [ , ,1,0,1]
 * [1,0,1, , ]
 *
 * Note:
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] is either 0 or 1.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 笔记：这题只能用preSum做
 * !!! 感谢论坛网友的总结：
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/301242/General-summary-of-what-kind-of-problem-can-cannot-solved-by-Two-Pointers
 * !!!
 * 虽然网友质疑不断，但是这位道友的思想就是：一开始想brute forth只用一个指针pass一遍发现时间复杂度很高， 然后
 * when two pointers come into place to help us reduce the total cases we need to consider,
 * such that the corresponding time complexity will reduce too
 * 这种情况可以使用2 pointers， 就是减少需要考虑的情况同时减少时间复杂度
 *
 * 2 pointers 入门题： 3. Longest Substring Without Repeating Characters 去看看吧！
 *
 * 回归到这道题：
 * 解题思路：
 * preSum, 是 560. Subarray Sum Equals K 的简化版
 * store previous sum and the frequency of this sum,
 * because sum[i, j] = sum[0, j] - sum[0, i - 1], this is a very very important idea
 */
public class BinarySubarraysWithSum {
  //2020-6-3更新代码：
  //一边找，一边放k v pair
  public int numSubarraysWithSum_new(int[] A, int S) {
    Map<Integer, Integer> preSum = new HashMap<>();
    preSum.put(0, 1);
    int curr = 0;
    int count = 0;
    for(int i = 0; i < A.length; i++) {
      curr += A[i];
      if(preSum.containsKey(curr - S)) count += preSum.get(curr - S);
      if(preSum.containsKey(curr)) preSum.put(curr, preSum.get(curr) + 1);
      else if(!preSum.containsKey(curr)) preSum.put(curr, 1);
    }
    return count;
  }

  //方法1： preSum
  public int numSubarraysWithSum_preSum(int[] A, int S) {
    int sum = 0; //prefix sum
    int res = 0; //final result
    Map<Integer, Integer> map = new HashMap<>(); // key: preSum, value: frequency of this preSum
    map.put(0, 1); // fake starting point, preSum:0 show up 1 time to keep consistency

    //preSum process
    for(int i = 1; i < A.length; i++) {
      A[i] += A[i - 1];
    }

    //build frequency map, since 0 will make presum show up duplicate time, so frequency map
    for(int i = 0; i < A.length; i++) {
      if(map.containsKey(A[i] - S)) {
        res += map.get(A[i] - S);
      }
      if(map.containsKey(A[i])) {
        map.put(A[i], map.get(A[i]) + 1);
      } else {
        map.put(A[i], 1);
      }
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    BinarySubarraysWithSum test = new BinarySubarraysWithSum();
    System.out.println(test.numSubarraysWithSum_preSum(new int[] {1,0,1,0,1}, 2));
  }
}
