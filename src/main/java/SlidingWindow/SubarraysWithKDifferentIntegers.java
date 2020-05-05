package SlidingWindow;

/**
 * 992. Subarrays with K Different Integers
 * Given an array A of positive integers,
 * call a (contiguous, not necessarily distinct) subarray of A good
 * if the number of different integers in that subarray is exactly K.
 *
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of A.
 *
 * Example 1:
 *
 * Input: A = [1,2,1,2,3], K = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * Example 2:
 *
 * Input: A = [1,2,1,3,4], K = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Note:
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 解题思路：
 * 重要思想：Exactly K times = at most K times - at most K - 1 times
 *
 * 这题转换成sliding window就靠这个思想， 一般来说：
 * Generally, the sliding window problems have some kind of aggregate, atMost k,
 * largest substring, min substring with k etc. They're always "given an array or string,
 * find some computed sub problem" value.
 *
 * 所以要解决 EXACTLY K times, K length 等问题就是： EXACTLY K = atMost K - atMost K-1
 * 这道题的解题思路是：
 *     //计算一个subarray里面最多有k个不一样的integer。
 *     //hashmap: key: integer, value: frequency
 *     //挪动右指针，第一次加进map的数字使得k--，
 *     //while k < 0的时候，左指针的数字一个个放进map: nums[left], frequency - 1
 *     //如果frequency变成0了，k--
 *     //挪动左指针
 */
public class SubarraysWithKDifferentIntegers {
  //写法一，根据解题思想一步一步写的code
  public int subarraysWithKDistinct(int[] A, int K) {
    return atMost(A, K) - atMost(A, K - 1);
  }

  private int atMost(int[] A, int K) {
    int l = 0; //left pointer
    int res = 0; //final result
    Map<Integer, Integer> freqMap = new HashMap<>(); //frequency map
    for(int r = 0; r < A.length; r++) { //r: right pointer
      if(!freqMap.containsKey(A[r])) {
        freqMap.put(A[r], 1);
        K--;
      } else if(freqMap.get(A[r]) == 0) {
        freqMap.put(A[r], freqMap.get(A[r]) + 1);
        K--;
      } else {
        freqMap.put(A[r], freqMap.get(A[r]) + 1);
      }

      while(K < 0) {
        freqMap.put(A[l], freqMap.get(A[l]) - 1);
        if(freqMap.get(A[l]) == 0) {
          K++;
        }
        l++;
      }

      res += r - l + 1;
    }
    return res;
  }

  //写法二， 简洁的高级写法，利用map的一些语句，要熟练掌握
  public int subarraysWithKDistinct2(int[] A, int K) {
    return atMost2(A, K) - atMost2(A, K - 1);
  }

  private int atMost2(int[] A, int K) {
    int l = 0; //left pointer
    int res = 0; //final result
    Map<Integer, Integer> freqMap2 = new HashMap<>(); //frequency map
    for(int r = 0; r < A.length; r++) {
      if(freqMap2.getOrDefault(A[r], 0) == 0) {
        K--;
      }
      freqMap2.put(A[r], freqMap2.getOrDefault(A[r], 0) + 1);

      while(K < 0) {
        freqMap2.put(A[l], freqMap2.get(A[l]) - 1);
        if(freqMap2.get(A[l]) == 0) {
          K++;
        }
        l++;
      }
      res = res + r - l + 1;
    }
    return res;
  }
}
