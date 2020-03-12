package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：
 * 90. Subsets II
 *
 * Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */

/**
 * 笔记：
 * 去重要先排序，不明白？看看CombinationSum II
 * 去重的方法和Combination Sum一样，为啥，解释在代码附注中
 */
public class Subsets_II {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if(nums == null || nums.length == 0) return res;
    Arrays.sort(nums);
    dfs(nums, res, new ArrayList<Integer>(), 0);
    return res;
  }

  private void dfs(int[] nums, List<List<Integer>> res, ArrayList<Integer> curr, int start) {
    //注意哈，subsets的话，每一种结果都要放进去，所以上来就把上一层得到的结果放进去
    res.add(new ArrayList<Integer>(curr));
    for(int i = start; i < nums.length; i++) {
      //这里去重的方法和combinationSum II一模一样，就是控制住每一次我们只能让index在前的数字出现
      //比如2有两个，2自身就是一个子集，但我们只取index小的2（此刻i == start)，
      // 遇到后面的2就直接跳过(也就是i != start && nums[i] == nums[i - 1])
      if(i != start && nums[i - 1] == nums[i]) continue;
      curr.add(nums[i]);
      dfs(nums, res, curr, i + 1);
      curr.remove(curr.size() - 1);
    }
  }

  //Test Case
  public static void main(String[] args) {
    Subsets_II testClass = new Subsets_II();
    int[] nums = {2, 1, 2};
    List<List<Integer>> res = testClass.subsetsWithDup(nums);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.print(curr.get(j));
      }
      System.out.println();
    }
  }
}
