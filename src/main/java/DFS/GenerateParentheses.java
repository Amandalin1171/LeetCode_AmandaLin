package DFS;

/**
 * 题目：
 * 22. Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 笔记：
 * 中规中矩就是dfs的方法去写
 * 还有一种方法是用stack做
 */
public class GenerateParentheses {
  //方法1： DFS using StringBuilder
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    dfs(res, new StringBuilder(), n, n);
    return res;
  }

  private void dfs(List<String> res, StringBuilder sb, int left, int right) {
    if(left == 0 && right == 0) {
      res.add(sb.toString());
      return;
    }

    int length = sb.length();

    if(left > 0) {
      sb.append('(');
      dfs(res, sb, left - 1, right);
      sb.setLength(length);
    }

    if(right > left) {
      sb.append(')');
      dfs(res, sb, left, right - 1);
      sb.setLength(length);
    }
  }

  //方法2：DFS just add String up
  //利用了String immutable的属性，每一层生成的cur都在这一层被存储，都是不一样的，
  // 所以会生成很多variable, 每次都是新的变量，不需要remove
  //下面看一下immutable的type有哪些：
  /**
   * Immutable class means that once an object is created,
   * we cannot change its content.
   * In Java, all the wrapper classes
   * (Character, Float, Double, Integer, Boolean, Byte, Short)
   * and String class is immutable.
   * We can create our own immutable class as well.
   */
  public List<String> generateParenthesis_2(int n) {
    List<String> res = new ArrayList<>();
    dfs_2(res, "", 0, 0, n);
    return res;
  }

  private void dfs_2(List<String> res, String cur, int open, int close, int max) {
    if(cur.length() == max * 2) {
      res.add(cur);
      return;
    }

    if(open < max) {
      dfs_2(res, cur + '(', open + 1, close, max);
    }
    if(close < open) {
      dfs_2(res, cur + ')', open, close + 1, max);
    }
  }

  //Test Case
  public static void main(String[] args) {
    GenerateParentheses testClass = new GenerateParentheses();
    List<String> res = testClass.generateParenthesis(3);
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}
