package DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：205. Isomorphic Strings
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character
 * while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */

/**
 * 笔记：
 * 这题跟Word Pattern 简直一模一样，具体注意的问题和坑，word pattern更多一点，去看那道题吧！
 */
public class IsomorphicStrings {
  public boolean isIsomorphic(String s, String t) {
    char[] sc = s.toCharArray();
    char[] tc = t.toCharArray();
    if(sc.length != tc.length) return false;
    int n = sc.length;
    Map<Character, Integer> sMap = new HashMap<>();
    Map<Character, Integer> tMap = new HashMap<>();
    for(int i = 0; i < n; i++) {
      int idx_s = sMap.getOrDefault(sc[i], -1);
      int idx_t = tMap.getOrDefault(tc[i], -1);

      if(idx_s != idx_t) return false;

      sMap.put(sc[i], i);
      tMap.put(sc[i], i);
    }
    return true;
  }
}
