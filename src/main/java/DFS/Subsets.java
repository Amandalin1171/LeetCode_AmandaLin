package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 78. Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */

/**
 * 笔记：
 * subsets跟permutation的不同就是不用每次都全部遍历given数字，所以需要start index去记录从哪里开始遍历
 * permutation的每一层退出条件是这一层得到的list的长度跟given的array的长度一样
 * subsets就是每次得到的都加入，在dfs helper function里面第一句就加东西
 * 每一层都把上一层得到的结果加进去
 */
public class Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if(nums == null || nums.length == 0) return res;
    dfs(nums, 0, res, new ArrayList<Integer>());
    return res;
  }

  private void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> cur) {
    res.add(new ArrayList<Integer>(cur)); //每一层都把上一层得到的结果先加进去
    for(int i = start; i < nums.length; i++) {
      cur.add(nums[i]);
      dfs(nums, i + 1, res, cur); //一定要注意每一层更新start变成i + 1！！！
                                       //千万不是start + 1
      cur.remove(cur.size() - 1);
    }
  }

  //Test Case
  public static void main(String[] args) {
    Subsets testClass = new Subsets();
    int[] nums = {1, 2, 3};
    List<List<Integer>>res = testClass.subsets(nums);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> cur = res.get(i);
      for(int j = 0; j < cur.size(); j++) {
        System.out.print(cur.get(j)); //一个是print
      }
      System.out.println(); //一个是println：分行
    }
  }
}
