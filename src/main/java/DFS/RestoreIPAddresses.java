package DFS;

/**
 * 题目：
 * 93. Restore IP Addresses
 *
 * Given a string containing only digits, restore it by returning all
 * possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 笔记：
 * 关键是剥离出来重复的步骤，进行dfs部分的设计
 * 就是有一个section是valid的，然后加上它在append一个点点，最后长度够了的时候把最后一位抹去就可以
 * 也就是去掉加上的第四个点
 * 有一种情况是长度没走到input string的长度但是已经用了四个点了后面都是多余的步骤
 * 所以最上面加一个大于4个点的时候直接return到上一层 节约时间 减少不必要的运算
 * 退出条件是点的数量等于4且长度正好是input的string的长度的时候就加到result里面
 * 需要注意的部分就是for loop就是0到3就是255这个片段的合法范围
 * 然后还有就是进入dfs要判断是否index out of bound
 */
public class RestoreIPAddresses {
  public List<String> RestoreIPAddresses (String s) {
    List<String> res = new ArrayList<>();
    if(s.length() > 12) return res;
    dfs(s, res, new StringBuilder(), 0, 0);
    return res;
  }

  private void dfs(String s, List<String> res, StringBuilder sb, int dotIndex, int charIndex) {
    if(dotIndex > 4) return; //落下来四个点可能长度没有走完s的长度还会继续放点点，这是无用功，所以写这个
    if(dotIndex == 4 && charIndex == s.length()){
      sb.setLength(sb.length() - 1);
      res.add(sb.toString());
      return;
    }

    int length = sb.length();
    for(int i = 0; i < 3; i++) {
      if(charIndex + i < s.length()) { //这里特别重要，一开始一直卡在index out of bound
        String subString = s.substring(charIndex, charIndex + i + 1);
        if(isValid(subString)) {
          sb.append(subString);
          sb.append('.');
          dfs(s, res, sb, dotIndex + 1, charIndex + i + 1);
          sb.setLength(length);
        }
      }
    }
  }

  private boolean isValid(String a) {
    if(a.charAt(0) == '0') return a.length() == 1;
    else if(Integer.parseInt(a) > 255) return false;
    return true;
  }

  //Test Class
  public static void main(String[] args) {
    RestoreIPAddresses testClass = new RestoreIPAddresses();
    List<String> res = testClass.RestoreIPAddresses("25525511135");
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}
