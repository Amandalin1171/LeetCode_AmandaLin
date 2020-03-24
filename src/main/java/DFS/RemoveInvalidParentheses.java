package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 301. Remove Invalid Parentheses
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */

/**
 * 笔记：
 * 记录了两个解法，第一种更高级，利用了一部分的DP分类的思想，就是站在此刻这个时点，我有几个选择
 * 然后在进入dfs recursion循环的 base case 时候限制进入哪个选择。
 * 第二种比较基础，新手党方法，但是比较笨拙。
 * 方法1：类似DP的向左走向右走人生抉择法
 * 需要注意的点：
 * 在写dfs function的时候，要先写remove,再也keep，这样只需要在所有if else逻辑写完之后backtrack setLength还原长度就可以了
 * 如果先写keep,再也remove，由于keep会改变StringBuilder的长度，所以进入下一行的dfs的时候需要backtrack
 * 所以需要跟一句setLength
 * 见下面的代码就是这么写的，浪费代码，改成先remove,再 keep,就可以保证if else每一个条件下第一个dfs都不改变sb长度
 * 就只需要最后面写一个setLength就可以了
 *
 * 方法2：Based on Subsets_II
 * start指针返回各种subsets,然后去judge返回的是不是valid,valid就加入结果
 *
 * 所以有dfs() 和 isValid() 两个 helper function
 */
public class RemoveInvalidParentheses {
  //方法1：
  public List<String> removeInvalidParentheses_1(String s) {
    int left = 0; //invalid left parentheses count
    int right = 0; //invalid right parentheses count
    int open = 0; //valid pair count, when all valid, should be 0
    Set<String> set = new HashSet<>();
    //count invalid parentheses
    for(int i = 0; i < s.length(); i++) {
      if(s.charAt(i) == '(') left++;
      if(s.charAt(i) == ')') {
        if(left > 0) left--;
        else right++;
      }
    }

    dfs(set, s, left, right, open, 0, new StringBuilder());
    return new ArrayList<String>(set);
  }

  private void dfs(Set<String> set, String s, int left, int right, int open, int index, StringBuilder sb) {
    //base case
    if(index == s.length() && left == 0 && right == 0 && open == 0) {
      set.add(sb.toString());
      return;
    }
    //out of bound
    if(index >= s.length() || left < 0 || right < 0 || open < 0) return;

    int length = sb.length();

    if(s.charAt(index) == '(') {
      //keep current '('
      dfs(set, s, left, right, open + 1, index + 1, sb.append(s.charAt(index)));
      sb.setLength(length); //先写keep 再也remove就要keep之后加上backtrack的把长度还原
      //remove current '('
      dfs(set, s, left - 1, right, open, index + 1, sb);
    } else if(s.charAt(index) == ')') {
      //keep current ')'
      dfs(set, s, left, right, open - 1, index + 1, sb.append(s.charAt(index)));
      sb.setLength(length); //先写keep 再也remove就要keep之后加上backtrack的把长度还原
      //remove current ')'
      dfs(set, s, left, right - 1, open, index + 1, sb);
    } else {
      //just keep character which is not parentheses
      dfs(set, s, left, right, open, index + 1, sb.append(s.charAt(index)));
    }
    //backtracking
    sb.setLength(length);
  }


  //方法2 ：
  public List<String> removeInvalidParentheses_2(String s) {
    //Step 1 : count invalid parentheses
    int l = 0;
    int r = 0;
    for(int i = 0; i < s.length(); i++) {
      if(s.charAt(i) == '(') l++;
      else if(s.charAt(i) == ')') {
        if(l > 0) l--;
        else r++;
      }
    }

    //Step 2 : dfs to remove invalid parentheses
    List<String> ans= new ArrayList<>();
    dfs1(s, ans, l, r, 0);
    return ans;
  }

  //helper function to judge if String is valid
  private boolean isValid(String s) {
    int count = 0;
    for(char c : s.toCharArray()) {
      if(c == '(') count++;
      if(c == ')') count--;
      if(count < 0) return false;
    }
    return count == 0;
  }

  //dfs function
  private void dfs1(String s, List<String> ans, int left, int right, int start) {
    if(left == 0 && right == 0) {
      if(isValid(s)) {
        ans.add(new String(s));
        return;
      }

      for(int i = start; i < s.length(); i++) {
        if(i != start && s.toCharArray()[i] == s.toCharArray()[i - 1]) continue;
        if(s.toCharArray()[i] == '(' || s.toCharArray()[i] == ')') {
          String curr = s.substring(0, i) + s.substring(i + 1, s.length());
          if(left > 0 && s.toCharArray()[i] == '(') dfs1(curr, ans, left - 1, right, i);
          else if(right > 0 && s.toCharArray()[i] == ')') dfs1(curr, ans, left, right - 1, i);
        }
      }
    }
  }

  //Test Case
  public static void main(String[] args) {
    RemoveInvalidParentheses test = new RemoveInvalidParentheses();
//    List<String> res = test.removeInvalidParentheses_1("()())()");
//    for(int i = 0; i < res.size(); i++) {
//      System.out.println(res.get(i));
//    }

    List<String> ans = test.removeInvalidParentheses_2("(a)())()");
    for(int i = 0; i < ans.size(); i++) {
      System.out.println(ans.get(i));
    }
  }
}
