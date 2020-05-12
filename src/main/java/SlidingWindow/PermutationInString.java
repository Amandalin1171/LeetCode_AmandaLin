package SlidingWindow;

/**
 * 567. Permutation in String
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Note:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */

/**
 * Similar to 438. Find All Anagrams in a String
 */
public class PermutationInString {
  //Similar to 438. Find All Anagrams in a String
  public boolean checkInclusion(String s1, String s2) {
    int[] dictionary = new int[26];
    int l = 0; //left pointer
    int r = 0; //right pointer
    int n = s2.length();
    int k = s1.length();
    int count = 0;

    //build dictionary
    for(char c : s1.toCharArray()) {
      dictionary[c - 'a']++;
    }

    //sliding window
    while(r < n && l < n) {
      if(dictionary[s2.charAt(r) - 'a'] > 0) {
        dictionary[s2.charAt(r) - 'a']--;
        count++;
        r++;
      } else {
        dictionary[s2.charAt(l) - 'a']++;
        count--;
        l++;
      }

      if(count == k) return true;
    }
    return false;
  }
}
