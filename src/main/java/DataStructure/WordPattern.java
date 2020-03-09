package DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 290. Word Pattern
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter
 * in pattern and a non-empty word in str.
 *
 * Example 1:
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 *
 * Example 2:
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 *
 * Example 4:
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 *
 * Notes:
 * You may assume pattern contains only lowercase letters,
 * and str contains lowercase letters that may be separated by a single space.
 */

/**
 * 笔记：
 * 1. 首先，记住，map的key是唯一的，value是不唯一的。
 * 1.1. 放进相同的key不同的value会覆盖上衣次放进去的pair
 * 1.2. 放进不同的key相同的value会让这两对儿pair同时存在的。
 * 在我没有加对map.getValue()进行判断的时候，上面四种test case的结果是：true/true/false/true
 * 第二个错是因为，最后放进去 a : fish这个pair时，会覆盖掉之前放进的 a : dog（对应1.1）
 * 第四个错是因为，a : dog 和 b : dog这两个pair会同时存在！（对应1.2）
 * 所以在！containsKey的时候，就是没有key的时候，要判断是否已经contain了这个value(第四个例子）
 * 用map保存String不会改变reference,讲道理用==是米问题的，但这里必须用equals()是因为：
 * 当你比较第三位的cat跟第一次put进去的a对应的cat的时候，他们在原始的strs里面的位置是不一样的！
 * 所以reference不一样，只是比较内容也就是长相是否一样的话要用equals()
 */
public class WordPattern {
  public boolean wordPattern(String pattern, String str) {
    char[] patterns = pattern.toCharArray();
    String[] strs = str.trim().split(" ");

    Map<Character, String> wordMap = new HashMap<>();
    if(patterns.length != strs.length) return false;
    for(int i = 0; i < strs.length; i++) {
      //System.out.println(patterns[i]);
      if(!wordMap.containsKey(patterns[i])) {
        if(wordMap.containsValue(strs[i])) return false;
        wordMap.put(patterns[i], strs[i]);
      }
      else if(!wordMap.get(patterns[i]).equals(strs[i])) {
        return false;
      }
    }
    return true;
  }

  //Test Case
  public static void main(String[] args) {
    WordPattern testClass = new WordPattern();
    String a = "abba";
    String a1 = "dog cat cat dog";
    String b = "abba";
    String b1 = "dog cat cat fish";
    String c = "aaaa";
    String c1 = "dog cat cat dog";
    String d = "abba";
    String d1 = "dog dog dog dog";

    System.out.println(testClass.wordPattern(a, a1));
    System.out.println(testClass.wordPattern(b, b1));
    System.out.println(testClass.wordPattern(c, c1));
    System.out.println(testClass.wordPattern(d, d1));
  }
}
