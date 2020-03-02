package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 77. Combinations
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combinations {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if(n < 1) return res;
    dfs(n, k, 1, res, new ArrayList<Integer>());
    return res;
  }

  private void dfs(int n, int k, int start, List<List<Integer>> res, List<Integer> cur) {
    if(cur.size() == k) {
      res.add(new ArrayList<Integer>(cur));
      return;
    }

    for(int i = start; i <= n; i++) {
      cur.add(i);
      dfs(n, k, i + 1, res, cur);
      cur.remove(cur.size() - 1);
    }
  }

  //Test Case
  public static void main(String[] args) {
    Combinations testClass = new Combinations();
    List<List<Integer>> res = testClass.combine(4, 2);
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
