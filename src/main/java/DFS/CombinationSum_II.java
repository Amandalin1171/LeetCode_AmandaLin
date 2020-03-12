package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目：
 * 40. Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 * 这句话的意思是每个组合里面就是每个和是target的括号的数字list里面一个数只能出现一次
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * 这句话的意思是比如有两个1和两个7, [1， 7]这个组合只能出现一次
 *
 * 上面这些附注的题意理解，在面试的时候都要与面试官再三确认！！！
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */

/**
 * 笔记：
 * 无论是CombinationSum_II, Permutation_II, Subsets_II,都说有相同的元素，并且不能出现同样的结果
 * 所以我们就会出现[1(index 0), 1(index 1), 2] & [1(index 1), 1(index 0), 2]这类长得一样，但是相同数字其实
 * 是取得不同位置的数字的情况，为了有效的避免这俩答案都被返回，需要做的第一个重要的步骤就是：
 * 排序啊！！！！！！！！！！！！！！！！！！！！Arrays.sort()!!!!!!!!!!!!!!!!!!
 * 之后如何跳过在相应位置有解释。
 * 既然看到这儿了，就去对比一下下Permutation_II和Subsets_II吧！！！
 */
public class CombinationSum_II {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(candidates);
    if(candidates == null || candidates.length == 0) return res;
    dfs(res, new ArrayList<Integer>(), candidates, target, 0);
    return res;
  }

  private void dfs(List<List<Integer>> res, ArrayList<Integer> curr, int[] candidates, int remain, int start) {
    if(remain < 0) return;
    if(remain == 0) {
      res.add(new ArrayList<>(curr));
      return;
    }

    for(int i = start; i < candidates.length; i++) {
      //最重要的就是这句话了！！！
      //此处跳过可以这么理解：
      //在第一次出现一个数字的时候，我们就会解决掉所有以这个数字开头的组合，就比如1(index 0),1(index 1),6.
      //我们不想再出现一个结果是这样的1(index 1), 1(index 0), 6.
      //所以相当于我们定义了相同数字出现在一个combination里面的时候我们要求只是index小的那个排在前面。
      //举例：from congchengchina @ Leetcode reply
      // Search in [1, 1, 1, 2, 2] for target 4, without the expression,
      // you will get three identical combinations:
      //[1, 1, 2, 2] from index [0, 1, 3, 4] of the candidates;
      //[1, 1, 2, 2] from index [0, 2, 3, 4] of the candidates;
      //[1, 1, 2, 2] from index [1, 2, 3, 4] of the candidates.
      if(i != start && candidates[i] == candidates[i - 1]) continue;
      curr.add(candidates[i]);
      remain = remain - candidates[i];
      dfs(res, curr, candidates, remain, i + 1);
      remain = remain + candidates[i];
      curr.remove(curr.size() - 1);
    }
  }

  //Test Case
  public static void main(String[] args) {
    CombinationSum_II testClass = new CombinationSum_II();
    int[] candidates = {10,1,2,7,6,1,5};
    int target = 8;
    List<List<Integer>> res = testClass.combinationSum2(candidates, target);
    for(int i = 0; i < res.size(); i++) {
      List<Integer> curr = res.get(i);
      for(int j = 0; j < curr.size(); j++) {
        System.out.print(curr.get(j));
      }
      System.out.println();
    }
  }
}
