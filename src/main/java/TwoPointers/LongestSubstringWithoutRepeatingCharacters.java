package TwoPointers;

/**
 * 题目： 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 笔记：
 * 解法1： 2 pointer, 用一个set去判断是否是重复的字母
 * 解法2： 2 pointer, 用长度为256的array去记录character
 * Total number of Character in ASCII table is 256 (0 to 255)
 * 此处直接用character的ASCII码当做int[]的index,所以不知道字母从哪个位置开始，所以就生成一个长度为256的
 * int[]是最稳妥的
 */
public class LongestSubstringWithoutRepeatingCharacters {

  //解法1：2 pointer + set
  public int lengthOfLongestSubstring(String s) {
    int left = 0;
    int right = 0;
    int res = 0;
    Set<Character> set = new HashSet<>();

    while(right < s.length()) {
      if(!set.contains(s.charAt(right))) {
        set.add(s.charAt(right));
        res = Math.max(res, right - left + 1);
        right++;
      } else {
        set.remove(s.charAt(left));
        left++;
      }
    }
    return res;
  }
  //解法2：2 pointer + 利用ASCII进行map记录
  public int lengthOfLongestSubstring2(String s) {
    int[] map = new int[256];
    Arrays.fill(map, -1);

    int left = 0;
    int right = 0;
    int res = 0;
    for(right = 0; right < s.length(); right++) {
      while(right < s.length() && map[s.charAt(right)] == -1) {
        map[s.charAt(right)] = 0;
        res = Math.max(res, right - left + 1);
        right++;
      }
      map[s.charAt(left)] = -1;
    }
    return res;
  }

  //Test Case
  public static void main(String[] args) {
    LongestSubstringWithoutRepeatingCharacters test = new LongestSubstringWithoutRepeatingCharacters();
    System.out.println(test.lengthOfLongestSubstring("abcabcbb"));
  }
}
