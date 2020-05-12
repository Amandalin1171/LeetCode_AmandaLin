package SlidingWindow;

/**
 * 438. Find All Anagrams in a String
 *
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Example 1:
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input:
 * s: "abab" p: "ab"
 *
 * Output:
 * [0, 1, 2]
 *
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

/**
 * 跟characters有关，可以用定长的array作为map，还有就是如果用map，map的size是distinct character 的数量
 * 不是我们要维持窗口的size，所以要用另一个变量来记录valid character，把每个出现的character的frequency都减为零才可以用
 *
 * 三种方法：
 *
 * 1. brute force
 * 利用242. Valid Anagram的code作为helper function O(n^2)
 *
 * 2. hash map
 * code量大，由于map需要放进去才能有这个key，一开始要多一层逻辑判断if containsKey()
 *
 * 3. array used as map
 * 由于是character规定了都是小写字母所以可以用array[26]来做，没规定大小写可以array[256]里面直接塞char
 * 有一题跟这个几乎一模一样： 567. Permutation in String
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
  //方法1： brute force
  public List<Integer> findAnagrams_BF(String s, String p) {
    List<Integer> res = new ArrayList<>();
    if (p == null || s == null || s.length() < p.length()) return res;
    int m = s.length(), n = p.length();
    for (int i = 0; i < m - n + 1; i++) {
      String cur = s.substring(i, i + n);
      if (isAnagram(cur, p)) res.add(i);
    }
    return res;
  }
  private boolean isAnagram(String s, String t) {
    //use hashtable
    int n = s.length();
    int m = t.length();
    if(n != m) return false;

    //count characters in s & t
    int[] count = new int[26];
    for(int i = 0; i < n; i++) {
      count[s.charAt(i) - 'a']++;
      count[t.charAt(i) - 'a']--;
    }

    //check
    for(int c : count) {
      if(c != 0) return false;
    }

    return true;
  }

  //方法2： hash map
  public List<Integer> findAnagrams_hm(String s, String p) {
    List<Integer> result = new ArrayList<>();
    if (s == null || s.length() == 0) {
      return result;
    }
    if (p.length() > s.length()) {
      return result;
    }
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < p.length(); i++) {
      char c = p.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      } else {
        map.put(c, 1);
      }
    }
    int match = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        map.put(c, map.get(c) - 1);
        if (map.get(c) == 0) {
          match++;
        }
      }
      if (i >= p.length()) {
        c = s.charAt(i - p.length());
        if (map.containsKey(c)) {
          map.put(c, map.get(c) + 1);
          if (map.get(c) == 1) {
            match--;
          }
        }
      }
      if (match == map.size()) {
        result.add(i - p.length() + 1);
      }
    }
    return result;
  }

  //方法三：最好的方法！！！
  //有一个题跟这个很像： 567. Permutation in String
  public List<Integer> findAnagrams_array(String s, String p) {
    List<Integer> res = new ArrayList<>();
    int[] dictionary = new int[26];
    int l = 0; //left pointer
    int r = 0; //right pointer
    int n = s.length();
    int k = p.length();
    int count = 0;

    //corner case
    if(s == null || s.length() == 0) return res;

    //build dictionary: frequency of each character
    for(char c : p.toCharArray()) {
      dictionary[c - 'a']++;
    }

    //sliding window
    while(r < n && l < n) {
      if(dictionary[s.charAt(r) - 'a'] > 0) {
        dictionary[s.charAt(r) - 'a']--;
        count++;
        r++;
      } else {
        dictionary[s.charAt(l) - 'a']++;
        count--;
        l++;
      }
      if(count == k) {
        res.add(l);
      }
    }

    return res;
  }

  //test case
  public static void main(String[] args) {
    FindAllAnagramsInAString test = new FindAllAnagramsInAString();
    List<Integer> res = test.findAnagrams_array("cbaebabacd", "abc");
    //这个打印方法也是无比之骚，记住LOL
    String resVisual = new String();
    for(int i : res) resVisual += i;
    System.out.println(resVisual);
  }
}
