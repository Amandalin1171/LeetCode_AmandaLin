package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 39. Combination Sum
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    if(candidates == null || candidates.length == 0) return res;
    dfs(candidates, target, 0, res, new ArrayList<Integer>());
    return res;
  }

  private void dfs(int[] candidates, int remain, int start, List<List<Integer>> res, List<Integer> cur) {
    if(remain < 0) return; //退出到上一层的条件是sum > target, 也就是remain < 0
    else if(remain == 0) res.add(new ArrayList<Integer>(cur));
    for(int i = start; i < candidates.length; i++) {
      cur.add(candidates[i]);
      dfs(candidates, remain - candidates[i], i, res, cur);
      //这里也可以写成：
      //也就是可以每次传进去remain - candidates[i]，这样不改变本层的remain，
      //往上走的时候不需要remain = remain + candidates[i]

      //但是直接在本层改变，不是作为新一层call dfs function的参数传进去而是本层更改了remain的值的话
      //在网往上走的时候把remain加回变成原来的值就可以啦！！！

//      cur.add(candidates[i]);
//      remain = remain - candidates[i];
//      dfs(candidates, remain, i, res, cur);
//      remain = remain + candidates[i];
//      cur.remove(cur.size() - 1);

      //这里每次还是用i，因为可以用重复的元素
      cur.remove(cur.size() - 1);
    }
  }

  //Test Case
  public static void main(String[] args) {
    CombinationSum testClass = new CombinationSum();
    int[] candidates = {2, 3, 5};
    List<List<Integer>> res = testClass.combinationSum(candidates, 8);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> cur = res.get(i);
      for(int j = 0; j < cur.size(); j++) {
        System.out.print(cur.get(j));
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}
