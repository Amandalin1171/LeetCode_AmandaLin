package DataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 784. Letter Case Permutation
 *
 * Given a string S, we can transform every letter individually to be
 * lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 * Note:
 *
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */

/**
 * 笔记：
 *
 * 需要记住的点：
 * 1. Character的几个manage公式：
 * List.add(new String(char[])); 一个char array可以直接转换成String被加到结果List中去的
 * Character.isDigit()
 * Character.isLetter()
 * Character.toUpperCase()
 * Character.toLowerCase()
 *
 * 2. 我觉得因人而异，虽然这题用原本的char可以节约代码，但是我还是觉得我分三类的写法很nice
 * 就记住char[] manage 和 String 处理会经常先把这玩意改成char[] ~~~
 */
public class LetterCasePermutation {
  //自己的解法：
  //很多重复的code
  public List<String> letterCasePermutation(String S) {
    List<String> res = new ArrayList<>();
    char[] strs = S.toCharArray();
    if(S == null || S.length() == 0) return res;
    dfs1(strs, new char[strs.length], 0, res);
    return res;
  }

  private void dfs1(char[] strs, char[] curr, int index, List<String> res) {
    if(index == strs.length) {
      res.add(new String(curr));
      return;
    }
    if(Character.isLetter(strs[index])) {
      //Lower Case Letter
      curr[index] = Character.toLowerCase(strs[index]);
      dfs1(strs, curr, index + 1, res);
      //Upper Case Letter
      curr[index] = Character.toUpperCase(strs[index]);
      dfs1(strs, curr, index + 1, res);
    } else {
      //Digit
      curr[index] = strs[index];
      dfs1(strs, curr, index + 1, res);
    }
  }

  //网友的解法：
  //区别在于无需生成一个新的char array去存储每一次的结果，直接manage给的S变成的char array即可
  public List<String> letterCasePermutationNew(String S) {
    List<String> res = new ArrayList<>();
    char[] ss = S.toCharArray();
    if(S == null || S.length() == 0) return res;
    dfs2(ss, 0, res);
    return res;
  }

  private void dfs2(char[] ss, int index, List<String> res) {
    if(index == ss.length) {
      res.add(new String(ss));
      return;
    } else if(Character.isLetter(ss[index])) {
      ss[index] = Character.toLowerCase(ss[index]);
      dfs2(ss, index + 1, res);
      ss[index] = Character.toUpperCase(ss[index]);
    }
    dfs2(ss, index + 1, res);
  }

  //Test Case
  public static void main(String[] args) {
    LetterCasePermutation test = new LetterCasePermutation();
    String S = "a1b2";
    List<String> res1 = test.letterCasePermutation(S);
    for(int i = 0; i < res1.size(); i++) {
      System.out.print(res1.get(i));
    }
    System.out.println("************************");
    List<String> res2 = test.letterCasePermutationNew(S);
    for(int i = 0; i < res2.size(); i++) {
      System.out.print(res2.get(i));
    }
  }
}
