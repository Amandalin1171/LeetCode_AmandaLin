package TwoPointers;

/**
 * 524. Longest Word in Dictionary through Deleting
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 * If there are more than one possible results, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 这道题的想法是先对字典里的单词进行长度和字母顺序的排序
 * 遍历字典里的单词，看里面的单词是不是s的subsequence
 * 注意重写comparator的逻辑和查找是不是subsequence的方法O(n)
 * comparator反应两个参数的权重。1是前者权重大，-1是后者。最后按照权重由小到大排序。
 * o1=4, o2=6, 返回1表示4权重大，权重默认升序最后4排后面即数值降序。
 *
 * 说白了就是-1：就是想要的顺序，compare(o1, o2) o1是前者
 * (o1<o2) return -1: 从小到大
 * 记住这个就OK
 */
public class LongestWordInDictionaryThroughDeleting {
  public String findLongestWord(String s, List<String> d) {
    //corner case
    if(d == null || d.size() == 0) return "";

    //sort d by length and lexicographical order
    Collections.sort(d, new Comparator<String>() {
      //@Override
      public int compare(String s1, String s2) {
        if(s1.length() < s2.length()) return 1;
        else if(s1.length() > s2.length()) return -1;
        else return s1.compareTo(s2);
      }
    });

    //find result
    String res = new String();
    for(String dd : d) {
      if(isSubSequence(dd, s)) {
        res = dd;
        break;
      }
    }
    return res;
  }

  private boolean isSubSequence(String sub, String boss) {
    int m = sub.length();
    int n = boss.length();
    if(m > n) return false;

    int j = 0;
    for(int i = 0; i < n && j < m; i++) {
      if(sub.charAt(j) == boss.charAt(i)) j++;
    }
    return j == m;
  }
}
