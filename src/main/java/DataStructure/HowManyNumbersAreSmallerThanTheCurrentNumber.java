package DataStructure;

/**
 * 题目：
 * 1365. How Many Numbers Are Smaller Than the Current Number
 *
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 *
 * Return the answer in an array.
 *
 * Example 1:
 *
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 *
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 *
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 笔记：
 * HashMap的用法：
 * 首先我们发现：
 * ！！！当你将一根数组排序的时候，它的index就是比它小的数字的count！！！
 * 为了节约查询的时间，map中key是排序后数组的element，value是这个element的index
 * 这样查找的时候，根据key拿到value就是小于这个数字的count(一共有多少数小于这个数)
 *
 * HashMap的解法因为要求排序，所以时间复杂度是O(nlogn)
 * 另一种方法是counting sort，时间复杂度是O(k + n)
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {
  //方法1：HashMap记录排序后的element和index(一共有多少数小于这个数)
  public int[] smallerNumbersThanCurrent(int[] nums) {
    int n = nums.length;
    int[] sortedNums = Arrays.copyOf(nums, n);
    int[] res = new int[n];
    Arrays.sort(sortedNums);
    Map<Integer, Integer> countMap = new HashMap<>();
    for(int i = 0; i < sortedNums.length; i++) {
      if(!countMap.containsKey(sortedNums[i])) {
        countMap.put(sortedNums[i], i);
      }
    }
    for(int i = 0; i < res.length; i++) {
      res[i] = countMap.get(nums[i]);
    }
    return res;
  }

  //方法2： Counting Sort
  /**
   * 我们在Counting Sort总结的时候知道，用这个算法需要知道数组中每个元素的值的上限k
   * 这道题给了我们这个上限，就是100，所以生成的count数组长度为101即可完成
   * 三部曲：
   * 1. 统计频次；
   * 2. preSum；
   * 3. 这道题里根据index就可以啦！
   */
  public int[] smallerNumbersThanCurrent_2(int[] nums){
    int n = nums.length;
    int[] count = new int[101]; //根据Constraints知道上限是100
    int[] res = new int[n];

    for(int i = 0; i < n; i++) {
      count[nums[i]]++;
    }

    for(int i = 1; i < 100; i++) {
      count[i] += count[i - 1];
    }

    for(int i = 0; i < n; i++) {
      if(nums[i] == 0) res[i] = 0;
      else res[i] = count[nums[i] - 1];
    }
    return res;
  }
  //Test Case
  public static void main(String[] args) {
    HowManyNumbersAreSmallerThanTheCurrentNumber testClass = new HowManyNumbersAreSmallerThanTheCurrentNumber();
    int[] nums = {8,1,2,2,3};
    int[] res = testClass.smallerNumbersThanCurrent(nums);
    for(int i = 0; i < res.length; i++) {
      System.out.print(res[i]);
    }
  }
}
