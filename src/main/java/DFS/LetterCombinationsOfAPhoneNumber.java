package DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目：
 * 17. Letter Combinations of a Phone Number
 *
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order,
 * your answer could be in any order you want.
 */
public class LetterCombinationsOfAPhoneNumber {
  public List<String> letterCombinations(String digits){
    List<String> res = new ArrayList<>();
    if(digits == null || digits.length() == 0) return res;
    Map<Character, String> phoneMap = new HashMap<>();
    phoneMap.put('2', "abc");
    phoneMap.put('3', "def");
    phoneMap.put('4', "ghi");
    phoneMap.put('5', "jkl");
    phoneMap.put('6', "mno");
    phoneMap.put('7', "pqrs");
    phoneMap.put('8', "tuv");
    phoneMap.put('9', "wxyz");
    dfs(digits, res, phoneMap, new StringBuilder(), 0);
    return res;
  }

  private void dfs(String digits, List<String> res, Map<Character, String> phoneMap, StringBuilder sb, int pos) {
    if(sb.length() == digits.length()) {
      res.add(sb.toString());
      return;
    }
    for(int i = pos; i < digits.length(); i++) {
      String curr = phoneMap.get(digits.charAt(i));
      for(int j = 0; j < curr.length(); j++) {
        int length = sb.length();
        sb.append(curr.charAt(j));
        dfs(digits, res, phoneMap, sb, i + 1); //这里易错，每次都会写成pos + 1我的天呐
        sb.setLength(length);
      }
    }
  }

  //Test Case
  public static void main(String[] args) {
    LetterCombinationsOfAPhoneNumber testClass = new LetterCombinationsOfAPhoneNumber();
    List<String> res = testClass.letterCombinations("23");
    for(int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}


