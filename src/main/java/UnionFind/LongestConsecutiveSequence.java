package UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */

/**
 * 方法一：unionfind稳得一笔：
 *
 * reference: https://leetcode.com/problems/longest-consecutive-sequence/discuss/166544/Union-Find-Thinking-Process
 * 转换成连续的放到一个团伙，找最大的团伙的人数
 * If we regard a consecutive sequence as a conected component (or a disjoint set), the problem becomes to get the size of largest connected component (or set).
 * A node is a value of an element in nums in this case.
 * Two nodes are connected if they are consecutive.
 * O(N) time complexity is reqruired, so given nums[i], we should tell index of nums[i] - 1 if any, and nums[i] + 1 if any in O(1). That can be achieved using a map that maps value to index.
 * Please note that for duplicate elements, we count only once.
 *
 * 方法二： tricky一点，用set，但是放入的时候保证先找小的，确保每一截连续的都是从最小数开始找到
 */
public class LongestConsecutiveSequence {
  class UF{
    private int[] parent;
    private int[] rank;

    public UF(int n) {
      parent = new int[n];
      rank = new int[n];
      for(int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    //quick find with path compression
    public int find(int x) {
      if(x != find(x)) {
        parent[x] = find(parent[x]);
        return parent[x];
      }
      return x;
    }

    //quick union by rank
    public void union(int x, int y) {
      int px = find(x);
      int py = find(y);
      if(px != py) {
        if(rank[px] > rank[py]) {
          parent[py] = px;
        } else if(rank[px] < rank[py]) {
          parent[px] = py;
        } else {
          parent[px] = py;
          rank[py]++;
        }
      }
    }

    //为了得到答案的class，也经常用
    //get size of largest component
    public int getLargestComponentSize() {
      int maxSize = 0;
      for(int i = 0; i < parent.length; i++) {
        if(parent[i] == i && rank[i] > maxSize) maxSize = rank[i];
      }
      return maxSize;
    }

  }
  public int longestConsecutive(int[] nums) {
    int n = nums.length;
    UF uf = new UF(n);
    Map<Integer, Integer> valToIndex = new HashMap<>();
    for(int i = 0; i < n; i++) {
      //把连续的数字放到一个团伙之中
      if(valToIndex.containsKey(nums[i] - 1)) uf.union(i, valToIndex.get(nums[i] - 1));
      if(valToIndex.containsKey(nums[i] + 1)) uf.union(i, valToIndex.get(nums[i] + 1));
      valToIndex.put(nums[i], i);
    }
    return uf.getLargestComponentSize();
  }

  public int longestConsecutiveII(int[] nums) {
    // consider no duplicates
    Set<Integer> numsset = new HashSet<>(); // set to store all integers in given array
    int longestLength = 0; // store result

    if(nums == null || nums.length == 0) return 0; // corner case

    for(int num : nums) {
      numsset.add(num);
    }

    for(int num : numsset) {
      if(!numsset.contains(num - 1)) {
        // make sure start from the smallest element of a longest sequence
        int currentNum = num;
        int currentLength = 1;

        while(numsset.contains(currentNum + 1)) {
          currentNum++;
          currentLength++;
        }
        longestLength = Math.max(longestLength, currentLength);
      }
    }
    return longestLength;
  }


}
