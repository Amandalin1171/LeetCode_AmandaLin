package DataStructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 387. First Unique Character in a String
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 *
 * Note: You may assume the string contain only lowercase English letters.
 */
public class FirstUniqueCharacterInAString {

  /**
   * 方法1： 用LinkedHashMap
   * 自己冥思苦想，想要拥有一个可以保留nsert key的顺序的map，搜了半天得到了LinkedHashMap
   */
  public int firstUniqChar(String s) {
    //k: Character, v: int[2]{freq, first appear index}
    Map<Character, int[]> freqMap = new LinkedHashMap<>();
    int res = -1;

    //build map
    for(int i = 0; i < s.length(); i++) {
      if(freqMap.containsKey(s.charAt(i))) {
        freqMap.get(s.charAt(i))[0]++;
      } else {
        freqMap.put(s.charAt(i), new int[] {1, i});
      }
    }

    //find first freq == 1 character index
    for(Character c : freqMap.keySet()) {
      if(freqMap.get(c)[0] == 1) {
        res = freqMap.get(c)[1];
        break;
      }
    }
    return res;
  }

  /**
   * 方法2： 用array去记录freq不就完事了吗
   * 制杖吗？？？
   */
  public int firstUniqChar2(String s) {
    int res = -1;
    int[] freq = new int[26];
    for(int i = 0; i < s.length(); i++) {
      freq[s.charAt(i) - 'a']++;
    }
    for(int i = 0; i < s.length(); i++) {
      if(freq[s.charAt(i) - 'a'] == 1) {
        res = i;
        break;
      }
    }
    return res;
  }

  /**
   * 更新：
   * 不用break啊，直接最后返回-1就可以
   */
  public int firstUniqChar3(String s) {
    //int res = -1;
    int[] freq = new int[26];
    for(int i = 0; i < s.length(); i++) {
      freq[s.charAt(i) - 'a']++;
    }
    for(int i = 0; i < s.length(); i++) {
      if(freq[s.charAt(i) - 'a'] == 1) {
        return i;
        //break;
      }
    }
    return -1;
  }
}
