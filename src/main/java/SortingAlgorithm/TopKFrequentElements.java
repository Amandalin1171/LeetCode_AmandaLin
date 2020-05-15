package SortingAlgorithm;

/**
 * 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bucket sort
 * https://www.geeksforgeeks.org/bucket-sort-2/
 * Bucket sort is mainly useful when input is uniformly distributed over a range.
 * 当elements是小数的时候更需要用这种方法，因为无法用counting sort
 */
public class TopKFrequentElements {
  public int[] topKFrequent(int[] nums, int k) {
    List<Integer>[] buckets = new ArrayList[nums.length + 1];
    Map<Integer, Integer> freqMap = new HashMap<>(); //frequency map
    List<Integer> res = new ArrayList<>();

    //build map
    for(int num : nums) {
      freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    //bucket sort
    for(int i = 0; i < buckets.length; i++) buckets[i] = new ArrayList<>();
    for(int key : freqMap.keySet()) {
      int freq = freqMap.get(key);
      buckets[freq].add(key);
    }

    for(int i = buckets.length - 1; i >= 0; i--) {
      res.addAll(buckets[i]);
      if(res.size() >= k) break;
    }

    //list.stream().mapToInt(i -> i).toArray();
    return res.stream().mapToInt(i -> i).toArray();
  }

  //Test Case
  public static void main(String[] args) {
    TopKFrequentElements test = new TopKFrequentElements();
    int[] res = test.topKFrequent(new int[] {1,1,1,2,2,3}, 2);
    System.out.println(res.toString());
  }
}
