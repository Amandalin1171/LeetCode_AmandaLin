package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 *
 * Given a string S and a string T, find the minimum window in S which will contain
 * all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

/**
 * 一开始觉得记录两个map，然后根据distinct char去增减，挪动左指针的时候加回来frequency还要跟另一个控制变量本来的frequency对比
 * 但这种方法不对，因为 aa, a这个test case情况下会只返回一个a
 *
 * 正确流程：
 * 1. frequency map去记录T的每一个字母和出现的频率，移动窗口的时候，对遇到字母了，右指针频率--，左指针频率++
 * 2. 记录window中出现的frequency map字母的数量，当w这个数量与T的长度一致的时候，进行下一步压缩（就是重复的也算重复的次数）
 * 这样保证这个数量就是map所value的和嘛，一旦与T长度一致就说明刺客窗口中有T的全部character
 * 3. 当长度一致的时候，开始挪动左指针进行压缩，看是不是左指针前几个character不是T里面的字母，压缩的时候进行结算
 * 其中压缩就是看左指针的char在不在map中，在的话就frequency++,window中出现的frequency map字母的数量--
 * 这种情况就不满足条件了，就会在下一次继续挪动右指针
 * 挪动左指针的情况就是只有前面的字母T中没有
 *
 *
 */
public class MinimumWindowSubstring {
  public String minWindow(String s, String t) {
    //corner case
    if(s == null || s.length() == 0) return "";

    int l = 0; //left pointer
    int r = 0; //right pointer
    //Map<Character, Integer> freqMapOrigin = new HashMap<>(); //frequency map of char in t
    Map<Character, Integer> freqMap = new HashMap<>(); //manage map
    int count = 0; //count char in t showed in window
    //StringBuilder sb = new StringBuilder();
    int minLength = Integer.MAX_VALUE;
    String res = new String();

    //build map
    for(char c : t.toCharArray()) {
      //freqMapOrigin.put(c, freqMapOrigin.getOrDefault(c, 0) + 1);
      freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
    }

    //sliding window

    //1. move right pointer to find a valid window
    for(; r < s.length(); r++) {
      if(freqMap.containsKey(s.charAt(r))) {
        freqMap.put(s.charAt(r), freqMap.get(s.charAt(r)) - 1);
        if(freqMap.get(s.charAt(r)) >= 0) count++; //distinct valid char in t
      }
      //2. move left pointer to compress the length
      while(l <= r && count == t.length()) {
        if(minLength > r - l + 1) {
          minLength = r - l + 1;
          res = s.substring(l, r + 1);
        }
        if(freqMap.containsKey(s.charAt(l))) {
          freqMap.put(s.charAt(l), freqMap.get(s.charAt(l)) + 1);
          if(freqMap.get(s.charAt(l)) > 0) count--;
        }
        l++;
      }
    }

    return res;
  }

  //Test Case
  public static void main(String[] args) {
    MinimumWindowSubstring test = new MinimumWindowSubstring();
    System.out.println(test.minWindow("ADOBECODEBANC", "ABC"));
  }
}
