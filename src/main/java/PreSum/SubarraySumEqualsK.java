package PreSum;

/**
 * 560. Subarray Sum Equals K
 *
 * Given an array of integers and an integer k,
 * you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import java.util.HashMap;
import java.util.Map;

/**
 * 算有关subarray然后里面element的和是一个k这种题，一眼看过去就应该想到preSum
 * target[i, j] = preSum[j] - preSum[i - 1]
 *
 * 注意： preSum相关问题都要保持consistency，一开始map里要塞一个初始值，具体是什么，看你的map的含义
 */
public class SubarraySumEqualsK {
  public int subarraySum(int[] nums, int k) {
    //corner case
    if(nums == null || nums.length == 0) return 0;

    //count result
    int count = 0;
    //calculate presum
    int preSum = 0;
    //hashmap to build presum: key: presum; value: frequency of this presum
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); //keep consistency: initiate key: presum = 0; frequency = 1
    //find result
    for(int i = 0; i < nums.length; i++) {
      preSum += nums[i];
      if(map.containsKey(preSum - k)) {
        count += map.get(preSum - k);
      }
      //frequency calculate
      if(map.containsKey(preSum)) {
        map.put(preSum, map.get(preSum) + 1);
      } else {
        map.put(preSum, 1);
      }
    }
    return count;
  }

  //Test
  public static void main(String[] args) {
    SubarraySumEqualsK test = new SubarraySumEqualsK();
    System.out.println(test.subarraySum(new int[]{1, 1, 1}, 2));
  }

}
